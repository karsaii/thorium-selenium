package com.github.karsaii.framework.selenium.namespaces.utilities;

import com.github.karsaii.core.extensions.namespaces.predicates.BasicPredicates;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.core.abstracts.lazy.filtered.BaseFilterData;
import com.github.karsaii.framework.core.namespaces.extensions.boilers.LazyLocatorList;
import com.github.karsaii.framework.selenium.constants.ElementFinderConstants;
import com.github.karsaii.framework.selenium.enums.ManyGetter;
import com.github.karsaii.framework.selenium.enums.SingleGetter;
import com.github.karsaii.framework.selenium.namespaces.element.ElementFilterFunctions;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.WebElementList;
import com.github.karsaii.framework.selenium.namespaces.factories.DriverFunctionFactory;
import com.github.karsaii.framework.selenium.namespaces.factories.ElementFilterParametersFactory;
import com.github.karsaii.framework.selenium.records.element.finder.ElementFilterParameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.function.BiPredicate;

import static com.github.karsaii.framework.selenium.namespaces.utilities.SeleniumUtilities.isNullWebElement;

public interface LazyElementUtilities {
    static boolean lazyExitConditionCore(Data<WebElement> element, int index, int attempts) {
        return isNullWebElement(element) && BasicPredicates.isSmallerThan(index, attempts);
    }

    static BiPredicate<Data<WebElement>, Integer> lazyExitCondition(int attempts) {
        return (element, value) -> lazyExitConditionCore(element, value, attempts);
    }

    static <T> DriverFunction<WebElement> getCurrentLazyElement(
        BaseFilterData<WebDriver, ManyGetter, T, ElementFilterParameters, WebElementList, WebElement> data,
        LazyLocatorList locators,
        String getter
    ) {
        return data.isFiltered ? (
            DriverFunctionFactory.getFunction(data.apply(ElementFilterParametersFactory.getWithManyGetterAndDefaultGetterMap(locators, getter)))
        ) : ElementFilterFunctions.getElement(locators, ElementFinderConstants.singleGetterMap, SingleGetter.getValueOf(getter));
    }
}
