package com.github.karsaii.framework.selenium.namespaces.factories.lazy.dynamic;

import com.github.karsaii.core.extensions.namespaces.CoreUtilities;
import com.github.karsaii.core.extensions.namespaces.NullableFunctions;
import com.github.karsaii.core.extensions.namespaces.predicates.BasicPredicates;
import com.github.karsaii.framework.core.namespaces.validators.LazyLocatorValidators;
import com.github.karsaii.framework.core.records.lazy.LazyLocator;
import com.github.karsaii.framework.selenium.constants.RepositoryConstants;
import com.github.karsaii.framework.selenium.constants.SeleniumDataConstants;
import com.github.karsaii.framework.selenium.records.lazy.LazyElement;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Objects;

import static com.github.karsaii.framework.selenium.namespaces.factories.lazy.simple.SimpleLazyElementFactory.getSimple;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public interface DynamicLazyElementFactory {
    static <T> String getComplexKey(String name, T object) {
        return NullableFunctions.isNotNull(object) && StringUtils.isNotBlank(name) ? "Dynamic-" + Objects.hash(name, object) + "-element" : "";
    }

    static LazyElement getWith(Map<String, LazyElement> map, String name, String message, LazyLocator locator) {
        final var defaultElement = SeleniumDataConstants.NULL_LAZY_ELEMENT.object;
        if (
            NullableFunctions.isNull(map) ||
            CoreUtilities.areAnyBlank(name, message) ||
            isNotBlank(LazyLocatorValidators.isInvalidLazyLocator(locator))
        ) {
            return defaultElement;
        }

        final var key = getComplexKey(name, message);
        if (isBlank(key)) {
            return defaultElement;
        }

        if (map.containsKey(key)) {
            return map.get(key);
        }

        final var element = getSimple(name, message, locator);
        map.put(key, element);
        return map.getOrDefault(key, defaultElement);
    }

    static LazyElement getWith(Map<String, LazyElement> map, String name, int index, LazyLocator locator) {
        final var defaultElement = SeleniumDataConstants.NULL_LAZY_ELEMENT.object;
        if (
            BasicPredicates.isNegative(index) ||
            NullableFunctions.isNull(map) ||
            isBlank(name) ||
            isNotBlank(LazyLocatorValidators.isInvalidLazyLocator(locator))
        ) {
            return defaultElement;
        }

        final var key = getComplexKey(name, index);
        if (isBlank(key)) {
            return defaultElement;
        }

        if (map.containsKey(key)) {
            return map.get(key);
        }

        final var element = getSimple(name, index, locator);
        map.put(key, element);
        return map.getOrDefault(key, defaultElement);
    }

    static LazyElement getWith(String name, String message, LazyLocator locator) {
        return getWith(RepositoryConstants.FACTORY_ELEMENTS, name, message, locator);
    }

    static LazyElement getWith(String name, int index, LazyLocator locator) {
        return getWith(RepositoryConstants.FACTORY_ELEMENTS, name, index, locator);
    }
}
