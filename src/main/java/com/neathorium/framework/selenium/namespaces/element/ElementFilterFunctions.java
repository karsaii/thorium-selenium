package com.neathorium.framework.selenium.namespaces.element;

import com.neathorium.framework.selenium.constants.SeleniumDataConstants;
import com.neathorium.framework.selenium.enums.ManyGetter;
import com.neathorium.framework.selenium.enums.SingleGetter;
import com.neathorium.framework.selenium.namespaces.Driver;
import com.neathorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.framework.selenium.namespaces.extensions.boilers.WebElementList;
import com.neathorium.framework.selenium.namespaces.factories.DriverFunctionFactory;
import com.neathorium.framework.selenium.namespaces.validators.SeleniumFormatter;
import com.neathorium.core.namespaces.DataExecutionFunctions;
import com.neathorium.core.namespaces.validators.CoreFormatter;
import com.neathorium.core.records.Data;
import com.neathorium.framework.core.abstracts.element.finder.BaseFilterParameters;
import com.neathorium.framework.core.namespaces.extensions.boilers.LazyLocatorList;
import com.neathorium.framework.selenium.namespaces.element.validators.ElementFilterParametersValidators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Map;
import java.util.function.Function;

import static com.neathorium.core.namespaces.DataExecutionFunctions.ifDependency;

public interface ElementFilterFunctions {
    private static <T> Function<T, Function<WebDriver, Data<WebElement>>> getFilteredElement(String nameof, BaseFilterParameters<WebDriver, ManyGetter, WebElementList> data, Function<T, Function<Data<WebElementList>, Data<WebElement>>> filterFunction, Function<T, String> valueGuard) {
        return value -> ifDependency(
            nameof,
            ElementFilterParametersValidators.isInvalidElementFilterParametersMessage(data) + valueGuard.apply(value),
            DataExecutionFunctions.validChain(data.getterMap.get(data.getter).apply(data.locators), filterFunction.apply(value), SeleniumDataConstants.NULL_ELEMENT),
            SeleniumDataConstants.NULL_ELEMENT
        );
    }

    static Function<Integer, Function<WebDriver, Data<WebElement>>> getIndexedElement(BaseFilterParameters<WebDriver, ManyGetter, WebElementList> data) {
        return getFilteredElement("getIndexedElement", data, Driver::getElementByIndex, CoreFormatter::isNegativeMessage);
    }

    static Function<String, Function<WebDriver, Data<WebElement>>> getContainedTextElement(BaseFilterParameters<WebDriver, ManyGetter, WebElementList> data) {
        return getFilteredElement("getContainedTextElement", data, Driver::getElementByContainedText, CoreFormatter::isBlankMessage);
    }

    static DriverFunction<WebElement> getElement(LazyLocatorList locators, Map<SingleGetter, Function<LazyLocatorList, Function<WebDriver, Data<WebElement>>>> getterMap, SingleGetter getter) {
        return DriverFunctionFactory.getFunction(DataExecutionFunctions.ifDependency("getElement via LazyElement parameters(" + getter.getName() + ")", SeleniumFormatter.getSingleGetterErrorMessage(getterMap, getter), getterMap.get(getter).apply(locators), SeleniumDataConstants.NULL_ELEMENT));
    }
}
