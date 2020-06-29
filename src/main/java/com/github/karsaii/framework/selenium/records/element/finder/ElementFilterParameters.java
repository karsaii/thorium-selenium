package com.github.karsaii.framework.selenium.records.element.finder;

import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.core.abstracts.element.finder.BaseFilterParameters;
import com.github.karsaii.framework.core.namespaces.extensions.boilers.LazyLocatorList;
import org.openqa.selenium.WebDriver;

import java.util.Map;
import java.util.function.Function;

public class ElementFilterParameters<T, U> extends BaseFilterParameters<WebDriver, T, U> {
    public ElementFilterParameters(LazyLocatorList locators, Map<T, Function<LazyLocatorList, Function<WebDriver, Data<U>>>> getterMap, T getter) {
        super(locators, getterMap, getter);
    }
}
