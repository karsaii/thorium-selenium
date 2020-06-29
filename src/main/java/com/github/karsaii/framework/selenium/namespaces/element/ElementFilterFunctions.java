package com.github.karsaii.framework.selenium.namespaces.element;

import com.github.karsaii.core.records.Data;
import com.github.karsaii.core.namespaces.validators.CoreFormatter;
import com.github.karsaii.framework.core.abstracts.element.finder.BaseFilterParameters;
import com.github.karsaii.framework.selenium.enums.ManyGetter;
import com.github.karsaii.framework.selenium.namespaces.factories.DriverFunctionFactory;
import com.github.karsaii.framework.selenium.namespaces.validators.SeleniumFormatter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.github.karsaii.framework.selenium.constants.SeleniumDataConstants;
import com.github.karsaii.framework.selenium.enums.SingleGetter;
import com.github.karsaii.framework.selenium.namespaces.Driver;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.github.karsaii.framework.core.namespaces.extensions.boilers.LazyLocatorList;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.WebElementList;

import java.util.Map;
import java.util.function.Function;

import static com.github.karsaii.core.namespaces.DataExecutionFunctions.ifDependency;
import static com.github.karsaii.framework.selenium.namespaces.ExecutionCore.validChain;
import static com.github.karsaii.framework.selenium.namespaces.element.validators.ElementFilterParametersValidators.isInvalidElementFilterParametersMessage;

public interface ElementFilterFunctions {
    private static <T> Function<T, Function<WebDriver, Data<WebElement>>> getFilteredElement(String nameof, BaseFilterParameters<WebDriver, ManyGetter, WebElementList> data, Function<T, Function<Data<WebElementList>, Data<WebElement>>> filterFunction, Function<T, String> valueGuard) {
        return value -> ifDependency(
            nameof,
            isInvalidElementFilterParametersMessage(data) + valueGuard.apply(value),
            validChain(data.getterMap.get(data.getter).apply(data.locators), filterFunction.apply(value), SeleniumDataConstants.NULL_ELEMENT),
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
        return DriverFunctionFactory.getFunction(ifDependency("getElement via LazyElement parameters", SeleniumFormatter.getSingleGetterErrorMessage(getterMap, getter), getterMap.get(getter).apply(locators), SeleniumDataConstants.NULL_ELEMENT));
    }
}
