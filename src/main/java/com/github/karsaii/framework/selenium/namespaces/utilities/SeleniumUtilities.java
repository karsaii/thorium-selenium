package com.github.karsaii.framework.selenium.namespaces.utilities;

import com.github.karsaii.core.extensions.interfaces.functional.TriFunction;
import com.github.karsaii.core.extensions.namespaces.EmptiableFunctions;
import com.github.karsaii.core.extensions.namespaces.predicates.BasicPredicateFunctions;
import com.github.karsaii.core.namespaces.DataFactoryFunctions;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.core.constants.lazy.LazyLocatorConstants;
import com.github.karsaii.framework.core.namespaces.factory.LazyLocatorListFactory;
import com.github.karsaii.framework.selenium.constants.validators.SeleniumFormatterConstants;
import com.github.karsaii.framework.selenium.namespaces.factories.SeleniumLazyLocatorFactory;
import com.github.karsaii.framework.selenium.namespaces.validators.SeleniumLazyLocatorValidators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.github.karsaii.framework.core.abstracts.AbstractLazyResult;
import com.github.karsaii.framework.selenium.abstracts.AbstractWaitParameters;
import com.github.karsaii.framework.selenium.constants.SeleniumDataConstants;
import com.github.karsaii.framework.selenium.constants.ElementStrategyMapConstants;
import com.github.karsaii.framework.selenium.enums.SeleniumSelectorStrategy;
import com.github.karsaii.framework.selenium.namespaces.lazy.LazyIndexedElementFactory;
import com.github.karsaii.framework.selenium.records.element.ElementWaitParameters;
import com.github.karsaii.framework.selenium.records.lazy.filtered.ElementFilterData;
import com.github.karsaii.framework.core.namespaces.extensions.boilers.LazyLocatorList;
import com.github.karsaii.framework.selenium.records.lazy.LazyElementWaitParameters;
import com.github.karsaii.framework.selenium.records.lazy.filtered.LazyFilteredElementParameters;
import com.github.karsaii.framework.core.records.lazy.LazyLocator;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

import static com.github.karsaii.core.extensions.namespaces.CoreUtilities.areAll;
import static com.github.karsaii.core.extensions.namespaces.CoreUtilities.areAny;
import static com.github.karsaii.core.extensions.namespaces.CoreUtilities.areAnyNull;
import static com.github.karsaii.core.extensions.namespaces.CoreUtilities.isEqual;
import static com.github.karsaii.core.extensions.namespaces.NullableFunctions.isNull;
import static com.github.karsaii.core.namespaces.validators.DataValidators.isInvalidOrFalse;
import static java.util.Map.entry;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public interface SeleniumUtilities {
    static boolean isInvalidLazyLocator(LazyLocator data) {
        return isNull(data) || isBlank(data.locator) || isNull(data.strategy);
    }

    static boolean areNullLazyData(LazyLocator... data) {
        return areAll(SeleniumUtilities::isInvalidLazyLocator, data);
    }

    static boolean areNullLazyData(List<LazyLocator> data) {
        return areNullLazyData(data.toArray(LazyLocatorConstants.NULL_LAZY_LOCATOR_ARRAY));
    }

    static boolean isNullLazyDataList(LazyLocatorList list) {
        return EmptiableFunctions.isNullOrEmpty(list) || areNullLazyData(list.list);
    }

    static boolean isNotNullLazyData(LazyLocator data) {
        return !isInvalidLazyLocator(data);
    }

    static <T> boolean isNullAbstractLazyElementParametersList(Collection<T> data, Predicate<T> validator) {
        for(T params : data) {
            if (validator.test(params)) {
                return false;
            }
        }

        return true;
    }

    static <T> boolean isNullLazyElement(AbstractLazyResult<T> element) {
        return (
            isNull(element) ||
            isBlank(element.name) ||
            areAnyNull(element.parameters, element.validator) ||
            element.parameters.isEmpty() ||
            isNullAbstractLazyElementParametersList(element.parameters.values(), element.validator)
        );
    }

    static <T> boolean isNotNullLazyElement(AbstractLazyResult<T> data) {
        return !isNullLazyElement(data);
    }

    static boolean isNullWebElement(WebElement element) {
        return (
            isNull(element) ||
            Objects.equals(SeleniumDataConstants.NULL_ELEMENT.object, element) ||
            isEqual(element.getAttribute("id"), SeleniumFormatterConstants.NULL_ELEMENT_ID)
        );
    }

    static boolean isNotNullWebElement(WebElement element) {
        return !isNullWebElement(element);
    }

    static boolean isNullWebElement(Data<WebElement> element) {
        return isInvalidOrFalse(element) || Objects.equals(SeleniumDataConstants.NULL_ELEMENT, element) || isNullWebElement(element.object);
    }

    static boolean isNotNullWebElement(Data<WebElement> element) {
        return !isNullWebElement(element);
    }

    static <T> boolean isNullCommonWaitParametersData(AbstractWaitParameters<T> data) {
        return isNull(data) || areAny(BasicPredicateFunctions::isNegative, data.duration, data.interval);
    }

    static boolean isNullElementWaitParametersData(ElementWaitParameters data) {
        return isNullCommonWaitParametersData(data) || isNull(data.object);
    }

    static boolean isNotNullElementWaitParametersData(ElementWaitParameters data) {
        return !isNullElementWaitParametersData(data);
    }

    static boolean isNullLazyElementWaitParametersData(LazyElementWaitParameters data) {
        return isNullCommonWaitParametersData(data) || isNullLazyElement(data.object);
    }

    static boolean isNotNullLazyElementWaitParametersData(LazyElementWaitParameters data) {
        return !isNullLazyElementWaitParametersData(data);
    }


    static LazyLocatorList getLazyLocatorList(By locator) {
        return LazyLocatorListFactory.getWithSingleItem(SeleniumLazyLocatorFactory.get(locator));
    }

    static <T> Map.Entry<String, T> getEntry(TriFunction<Boolean, LazyLocator, String, T> constructor, By locator, String getter, boolean isIndexed) {
        final var lazyLocator = SeleniumLazyLocatorFactory.get(locator);
        return entry(lazyLocator.strategy, constructor.apply(isIndexed, lazyLocator, getter));
    }

    static <T, V> Map.Entry<String, LazyFilteredElementParameters> getEntryIndexed(TriFunction<ElementFilterData, LazyLocator, String, LazyFilteredElementParameters> constructor, ElementFilterData<?> elementFilterData, By locator, String getter) {
        final var lazyLocator = SeleniumLazyLocatorFactory.get(locator);
        return entry(lazyLocator.strategy, constructor.apply(elementFilterData, lazyLocator, getter));
    }

    static Data<By> getLocator(Map<SeleniumSelectorStrategy, Function<String, By>> strategyMap, LazyLocator data) {
        final var errorMessage = SeleniumLazyLocatorValidators.isInvalidLazyLocator(data);
        if (isNotBlank(errorMessage)) {
            return SeleniumDataConstants.NULL_BY;
        }

        final var locator = data.locator;
        final var strategy = data.strategy;
        return DataFactoryFunctions.getWithMessage(strategyMap.get(SeleniumSelectorStrategy.getValueOf(strategy)).apply(locator), true, "Locator: By " + strategy + " with locator: " + locator);
    }

    static Data<By> getLocator(LazyLocator data) {
        return getLocator(ElementStrategyMapConstants.STRATEGY_MAP, data);
    }

    static <T> Map<T, LazyFilteredElementParameters> getParametersCopy(Map<T, LazyFilteredElementParameters> source) {
        final var keys = source.keySet().iterator();
        final var values = source.values().iterator();

        final var map = Collections.synchronizedMap(new LinkedHashMap<T, LazyFilteredElementParameters>());
        LazyFilteredElementParameters lep;
        while(keys.hasNext() && values.hasNext()) {
            lep = values.next();
            map.putIfAbsent(keys.next(), LazyIndexedElementFactory.getWithFilterDataAndLocatorList((ElementFilterData<?>) lep.elementFilterData, lep.probability, lep.lazyLocators, lep.getter));
        }

        return map;
    }
}
