package com.neathorium.framework.selenium.namespaces.factories.lazy.simple;

import com.neathorium.framework.selenium.enums.SingleGetter;
import com.neathorium.framework.selenium.records.lazy.LazyElement;
import com.neathorium.framework.core.namespaces.factory.LazyLocatorListFactory;
import com.neathorium.framework.core.records.lazy.LazyLocator;
import com.neathorium.framework.selenium.namespaces.factories.lazy.LazyElementFactory;

import static com.neathorium.framework.selenium.namespaces.factories.lazy.LazyElementFactory.getWithFilterParameters;
import static com.neathorium.framework.selenium.namespaces.factories.lazy.LazyElementFactory.getWithFilterParametersAndNestedLocator;

public interface SimpleLazyElementFactory {
    static LazyElement getSimple(String name, LazyLocator locator, String getter) {
        return LazyElementFactory.getWithFilterParametersAndDefaultIndex(name, locator, getter);
    }

    static LazyElement getSimple(String name, LazyLocator locator) {
        return LazyElementFactory.getWithFilterParameters(name, locator);
    }

    static LazyElement getSimple(String name, int index, LazyLocator locator) {
        return LazyElementFactory.getWithFilterParameters(name, index, locator);
    }

    static LazyElement getSimple(String name, String message, LazyLocator locator) {
        return LazyElementFactory.getWithFilterParameters(name, message, locator);
    }

    static LazyElement getSimpleNested(String name, LazyLocator... locators) {
        return LazyElementFactory.getWithFilterParametersAndNestedLocator(name, LazyLocatorListFactory.getWith(locators), SingleGetter.GET_NESTED_ELEMENT.getName());
    }

    static LazyElement getSimpleFrameNested(String name, LazyLocator... locators) {
        return LazyElementFactory.getWithFilterParametersAndNestedLocator(name, LazyLocatorListFactory.getWith(locators), SingleGetter.GET_FRAME_NESTED_ELEMENT.getName());
    }

    static LazyElement getSimpleShadowNested(String name, LazyLocator... locators) {
        return LazyElementFactory.getWithFilterParametersAndNestedLocator(name, LazyLocatorListFactory.getWith(locators), SingleGetter.GET_SHADOW_NESTED_ELEMENT.getName());
    }
}
