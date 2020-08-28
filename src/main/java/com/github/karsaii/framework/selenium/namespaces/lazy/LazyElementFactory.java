package com.github.karsaii.framework.selenium.namespaces.lazy;

import com.github.karsaii.core.extensions.namespaces.CoreUtilities;
import com.github.karsaii.framework.core.abstracts.AbstractLazyResult;
import com.github.karsaii.framework.core.namespaces.extensions.boilers.LazyLocatorList;
import com.github.karsaii.framework.selenium.namespaces.factories.SeleniumLazyLocatorFactory;
import com.github.karsaii.framework.selenium.namespaces.utilities.SeleniumUtilities;
import org.openqa.selenium.By;
import com.github.karsaii.framework.selenium.constants.SeleniumCoreConstants;
import com.github.karsaii.framework.selenium.enums.ManyGetter;
import com.github.karsaii.framework.selenium.enums.SingleGetter;
import com.github.karsaii.framework.selenium.records.lazy.filtered.ElementFilterData;
import com.github.karsaii.framework.selenium.records.lazy.LazyElement;
import com.github.karsaii.framework.selenium.records.lazy.filtered.LazyFilteredElementParameters;
import com.github.karsaii.framework.core.records.lazy.LazyLocator;
import com.github.karsaii.framework.selenium.namespaces.element.validators.ElementParameters;
import com.github.karsaii.framework.core.namespaces.validators.Invalids;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface LazyElementFactory {
    static LazyElement getWith(String name, Map<String, LazyFilteredElementParameters> parameters, Predicate<LazyFilteredElementParameters> validator) {
        return new LazyElement(name, parameters, validator);
    }

    static LazyElement getWith(AbstractLazyResult<LazyFilteredElementParameters> element) {
        return getWith(element.name, SeleniumUtilities.getParametersCopy(element.parameters), element.validator);
    }

    static LazyElement getWithInvalidAlwaysFalseValidator(String name, Map<String, LazyFilteredElementParameters> parameters) {
        return getWith(name, parameters, Invalids::defaultFalseValidator);
    }

    static LazyElement getWithInvalidData(String name) {
        return getWithInvalidAlwaysFalseValidator(name, new HashMap<>());
    }

    static LazyElement getWithDefaultValidator(String name, Map<String, LazyFilteredElementParameters> parameters) {
        return getWith(name, parameters, ElementParameters::isValidLazyFilteredElement);
    }

    static LazyElement getWithDefaultNameAndValidator(Map<String, LazyFilteredElementParameters> parameters) {
        return getWithDefaultValidator(CoreUtilities.getIncrementalUUID(SeleniumCoreConstants.ATOMIC_COUNT), parameters);
    }

    static LazyElement getWithFilterParameters(String name, boolean isIndexed, int index, LazyLocator locator, String getter) {
        final var parameters = LazyFilteredElementParametersFactory.getWithFilterParametersAndLocator(isIndexed, index, locator, getter);
        final var map = LazyElementParameterMapFactory.getWithLocatorAndParameters(locator.strategy, parameters);

        return getWithDefaultValidator(name, map);
    }

    static LazyElement getWithFilterParameters(String name, boolean isFiltered, String message, LazyLocator locator, String getter) {
        final var parameters = LazyFilteredElementParametersFactory.getWithFilterParametersAndLocator(isFiltered, message, locator, getter);
        final var map = LazyElementParameterMapFactory.getWithLocatorAndParameters(locator.strategy, parameters);

        return getWithDefaultValidator(name, map);
    }

    static LazyElement getWithFilterParameters(By locator, SingleGetter getter) {
        final var lazyLocator = SeleniumLazyLocatorFactory.get(locator);
        final var parameters = LazyFilteredElementParametersFactory.getWithFilterParametersAndLocator(false, lazyLocator, getter.getName());
        final var map = LazyElementParameterMapFactory.getWithLocatorAndParameters(lazyLocator.strategy, parameters);

        return getWithDefaultNameAndValidator(map);
    }

    static LazyElement getWithFilterParameters(ElementFilterData<Integer> elementFilterData, By locator, ManyGetter getter) {
        final var lazyLocator = SeleniumLazyLocatorFactory.get(locator);
        final var parameters = LazyFilteredElementParametersFactory.getWithFilterDataAndLocator(elementFilterData, lazyLocator, getter.getName());
        final var map = LazyElementParameterMapFactory.getWithLocatorAndParameters(lazyLocator.strategy, parameters);

        return getWithDefaultNameAndValidator(map);
    }

    static LazyElement getWithFilterParameters(String name, boolean isIndexed, LazyLocator locator, String getter) {
        return getWithFilterParameters(name, isIndexed, 0, locator, getter);
    }

    static LazyElement getWithFilterParameters(String name, int index, LazyLocator locator, String getter) {
        return getWithFilterParameters(name, true, index, locator, getter);
    }

    static LazyElement getWithFilterParameters(String name, boolean isIndexed, int index, LazyLocator locator) {
        return getWithFilterParameters(name, isIndexed, index, locator, SingleGetter.DEFAULT.getName());
    }

    static LazyElement getWithFilterParameters(String name, boolean isIndexed, LazyLocator locator) {
        return getWithFilterParameters(name, isIndexed, 0, locator, SingleGetter.DEFAULT.getName());
    }

    static LazyElement getWithFilterParameters(String name, int index, LazyLocator locator) {
        return getWithFilterParameters(name, true, index, locator, SingleGetter.DEFAULT.getName());
    }

    static LazyElement getWithFilterParameters(String name, LazyLocator locator) {
        return getWithFilterParameters(name, true, 0, locator, SingleGetter.DEFAULT.getName());
    }

    static LazyElement getWithFilterParameters(String name, LazyLocatorList locators) {
        final var parameterList = new ArrayList<LazyFilteredElementParameters>();
        final var keyList = new ArrayList<String>();
        for(var locator : locators) {
            parameterList.add(LazyFilteredElementParametersFactory.getWithFilterParametersAndLocator(true, 0, locator, SingleGetter.DEFAULT.getName()));
            keyList.add(locator.strategy + "-" + SeleniumCoreConstants.ELEMENT_ATOMIC_COUNT.getAndIncrement() + "-generated");
        }

        final var map = LazyElementParameterMapFactory.getWith(IntStream.range(0, keyList.size()).boxed().collect(Collectors.toMap(keyList::get, parameterList::get)));

        return getWithDefaultNameAndValidator(map);
    }

    static LazyElement getWithFilterParameters(By locator) {
        return getWithFilterParameters(locator, SingleGetter.DEFAULT);
    }

    static LazyElement getWithFilterParameters(String name, String message, LazyLocator locator, String getter) {
        return getWithFilterParameters(name, true, message, locator, getter);
    }

    static LazyElement getWithFilterParameters(String name, boolean isFiltered, String message, LazyLocator locator) {
        return getWithFilterParameters(name, isFiltered, message, locator, SingleGetter.DEFAULT.getName());
    }

    static LazyElement getWithFilterParameters(String name, String message, LazyLocator locator) {
        return getWithFilterParameters(name, true, message, locator, SingleGetter.DEFAULT.getName());
    }

    static LazyElement getSimple(String name, LazyLocator locator) {
        return getWithFilterParameters(name, locator);
    }

    static LazyElement getSimple(String name, int index, LazyLocator locator) {
        return getWithFilterParameters(name, index, locator);
    }
}
