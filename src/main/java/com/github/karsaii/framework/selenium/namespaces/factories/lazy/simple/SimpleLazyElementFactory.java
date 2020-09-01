package com.github.karsaii.framework.selenium.namespaces.factories.lazy.simple;

import com.github.karsaii.framework.core.namespaces.factory.LazyLocatorListFactory;
import com.github.karsaii.framework.core.records.lazy.LazyLocator;
import com.github.karsaii.framework.selenium.enums.SingleGetter;
import com.github.karsaii.framework.selenium.records.lazy.LazyElement;

import static com.github.karsaii.framework.selenium.namespaces.factories.lazy.LazyElementFactory.getWithFilterParameters;
import static com.github.karsaii.framework.selenium.namespaces.factories.lazy.LazyElementFactory.getWithFilterParametersAndDefaultIndex;
import static com.github.karsaii.framework.selenium.namespaces.factories.lazy.LazyElementFactory.getWithFilterParametersAndNestedLocator;

public interface SimpleLazyElementFactory {
    static LazyElement getSimple(String name, LazyLocator locator, String getter) {
        return getWithFilterParametersAndDefaultIndex(name, locator, getter);
    }

    static LazyElement getSimple(String name, LazyLocator locator) {
        return getWithFilterParameters(name, locator);
    }

    static LazyElement getSimple(String name, int index, LazyLocator locator) {
        return getWithFilterParameters(name, index, locator);
    }

    static LazyElement getSimple(String name, String message, LazyLocator locator) {
        return getWithFilterParameters(name, message, locator);
    }

    static LazyElement getSimpleNested(String name, LazyLocator... locators) {
        return getWithFilterParametersAndNestedLocator(name, LazyLocatorListFactory.getWith(locators), SingleGetter.GET_NESTED_ELEMENT.getName());
    }

    static LazyElement getSimpleFrameNested(String name, LazyLocator... locators) {
        return getWithFilterParametersAndNestedLocator(name, LazyLocatorListFactory.getWith(locators), SingleGetter.GET_FRAME_NESTED_ELEMENT.getName());
    }

    static LazyElement getSimpleShadowNested(String name, LazyLocator... locators) {
        return getWithFilterParametersAndNestedLocator(name, LazyLocatorListFactory.getWith(locators), SingleGetter.GET_SHADOW_NESTED_ELEMENT.getName());
    }
}
