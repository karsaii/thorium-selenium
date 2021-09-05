package com.github.karsaii.framework.selenium.namespaces.utilities;

import com.github.karsaii.core.constants.validators.CoreFormatterConstants;
import com.github.karsaii.core.extensions.namespaces.CoreUtilities;
import com.github.karsaii.core.extensions.namespaces.NullableFunctions;
import com.github.karsaii.core.extensions.namespaces.predicates.BasicPredicates;
import com.github.karsaii.core.namespaces.DataFactoryFunctions;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.core.abstracts.lazy.filtered.BaseFilterData;
import com.github.karsaii.framework.core.namespaces.extensions.boilers.LazyLocatorList;
import com.github.karsaii.framework.core.namespaces.validators.FrameworkCoreFormatter;
import com.github.karsaii.framework.selenium.constants.ElementFinderConstants;
import com.github.karsaii.framework.selenium.constants.SelectorStrategyNameConstants;
import com.github.karsaii.framework.selenium.enums.ManyGetter;
import com.github.karsaii.framework.selenium.enums.SingleGetter;
import com.github.karsaii.framework.selenium.namespaces.element.ElementFilterFunctions;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.WebElementList;
import com.github.karsaii.framework.selenium.namespaces.factories.DriverFunctionFactory;
import com.github.karsaii.framework.selenium.namespaces.factories.ElementFilterDataFactory;
import com.github.karsaii.framework.selenium.namespaces.factories.ElementFilterParametersFactory;
import com.github.karsaii.framework.selenium.records.element.finder.ElementFilterParameters;
import com.github.karsaii.framework.selenium.records.lazy.LazyElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.function.BiPredicate;

import static com.github.karsaii.framework.selenium.namespaces.utilities.SeleniumUtilities.isNullWebElement;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public interface LazyElementUtilities {
    static Data<String> getIndexedData(LazyElement element) {
        final var nameof = "getIndexedData";
        final var errorMessage = FrameworkCoreFormatter.isNullLazyElementMessage(element);
        if (isNotBlank(errorMessage)) {
            return DataFactoryFunctions.getInvalidWith("", nameof, "TU.GE");
        }

        final var parameters = element.parameters;
        final var selectorData = parameters.get(SelectorStrategyNameConstants.CSS_SELECTOR);
        if (NullableFunctions.isNull(selectorData)) {
            return DataFactoryFunctions.getInvalidWith("", nameof, "TU.GE");
        }

        final var status = selectorData.ELEMENT_FILTER_DATA.isFiltered;
        if (!status) {
            return DataFactoryFunctions.getInvalidWith("", nameof, "TU.GE");
        }

        final String object = String.valueOf(selectorData.ELEMENT_FILTER_DATA.filterParameter);
        var handler = "TU.GEBI";
        try {
            Integer.parseInt(object);
        } catch (NumberFormatException ex) {
            handler = "TU.GEBT";
        }
        return DataFactoryFunctions.getWith(object, true, nameof, handler);
    }

    static String getCSSSelectorFromElement(LazyElement element) {
        final var errorMessage = FrameworkCoreFormatter.isNullLazyElementMessage(element);
        if (isNotBlank(errorMessage)) {
            return CoreFormatterConstants.EMPTY;
        }

        final var parameters = element.parameters;
        final var selectorData = parameters.get(SelectorStrategyNameConstants.CSS_SELECTOR);
        if (NullableFunctions.isNull(selectorData)) {
            return CoreFormatterConstants.EMPTY;
        }
        final var lazyLocators = selectorData.LAZY_LOCATORS;
        final var lazyLocator = lazyLocators.first();
        return lazyLocator.LOCATOR;
    }

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
