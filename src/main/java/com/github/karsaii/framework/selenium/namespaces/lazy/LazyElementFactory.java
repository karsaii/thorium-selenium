package com.github.karsaii.framework.selenium.namespaces.lazy;

import com.github.karsaii.core.extensions.namespaces.CoreUtilities;
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

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Map.entry;
import static com.github.karsaii.framework.selenium.namespaces.utilities.SeleniumUtilities.getEntry;
import static com.github.karsaii.framework.selenium.namespaces.utilities.SeleniumUtilities.getEntryIndexed;

public interface LazyElementFactory {
    static <T> LazyElement getWithDefaultValidator(String name, Map<String, LazyFilteredElementParameters> parameters) {
        return new LazyElement(name, parameters, Invalids::defaultFalseValidator);
    }

    static <T> LazyElement getWithDefaultLocatorsAndValidator(String name) {
        return new LazyElement(name, new HashMap<>(), Invalids::defaultFalseValidator);
    }

    static LazyElement getWithFilterParameters(String name, boolean isIndexed, int index, LazyLocator locator, String getter) {
        final var lep = LazyIndexedElementFactory.getWithFilterParametersAndLocator(isIndexed, index, locator, getter);
        final var linkedHashMap = new LinkedHashMap<>(Map.ofEntries(entry(locator.strategy, lep)));
        final var synchronizedMap = Collections.synchronizedMap(linkedHashMap);

        return new LazyElement(name, synchronizedMap, ElementParameters::isValidLazyIndexedElement);
    }

    static LazyElement getWithFilterParameters(By locator, SingleGetter getter) {
        final var lep = getEntry(LazyIndexedElementFactory::getWithFilterParametersAndLocator, locator, getter.getName(), false);
        final var linkedHashMap = new LinkedHashMap<>(Map.ofEntries(lep));
        final var synchronizedMap = Collections.synchronizedMap(linkedHashMap);

        return new LazyElement(CoreUtilities.getIncrementalUUID(SeleniumCoreConstants.ATOMIC_COUNT), synchronizedMap, ElementParameters::isValidLazyIndexedElement);
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

    static LazyElement getWithFilterParameters(By locator) {
        return getWithFilterParameters(locator, SingleGetter.DEFAULT);
    }

    static LazyElement getWithFilterParameters(ElementFilterData<Integer> elementFilterData, By locator, ManyGetter getter) {
        final var lep = getEntryIndexed(LazyIndexedElementFactory::getWithFilterDataAndLocator, elementFilterData, locator, getter.getName());
        final var linkedHashMap = new LinkedHashMap<>(Map.ofEntries(lep));
        final var synchronizedMap = Collections.synchronizedMap(linkedHashMap);

        return new LazyElement(CoreUtilities.getIncrementalUUID(SeleniumCoreConstants.ATOMIC_COUNT), synchronizedMap, ElementParameters::isValidLazyIndexedElement);
    }

    static LazyElement getWithFilterParameters(String name, boolean isFiltered, String message, LazyLocator locator, String getter) {
        final var lep = LazyIndexedElementFactory.getWithFilterParametersAndLocator(isFiltered, message, locator, getter);
        final var linkedHashMap = new LinkedHashMap<>(Map.ofEntries(entry(locator.strategy, lep)));
        final var synchronizedMap = Collections.synchronizedMap(linkedHashMap);

        return new LazyElement(name, synchronizedMap, ElementParameters::isValidLazyTextFilteredElement);
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
}
