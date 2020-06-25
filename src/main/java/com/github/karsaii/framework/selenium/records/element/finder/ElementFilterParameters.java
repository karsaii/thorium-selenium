package com.github.karsaii.framework.selenium.records.element.finder;

import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.core.records.element.finder.BaseFilterParameters;
import com.github.karsaii.framework.selenium.enums.ManyGetter;
import com.github.karsaii.framework.core.namespaces.extensions.boilers.LazyLocatorList;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.WebElementList;
import org.openqa.selenium.WebDriver;

import java.util.Map;
import java.util.function.Function;

public class ElementFilterParameters extends BaseFilterParameters<WebDriver, ManyGetter, WebElementList> {
    public ElementFilterParameters(LazyLocatorList locators, Map<ManyGetter, Function<LazyLocatorList, Function<WebDriver, Data<WebElementList>>>> getterMap, ManyGetter getter) {
        super(locators, getterMap, getter);
    }
}
