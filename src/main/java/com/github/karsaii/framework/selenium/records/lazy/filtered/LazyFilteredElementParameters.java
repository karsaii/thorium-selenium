package com.github.karsaii.framework.selenium.records.lazy.filtered;

import com.github.karsaii.framework.core.abstracts.lazy.filtered.AbstractLazyFilteredElementParameters;
import com.github.karsaii.framework.core.namespaces.extensions.boilers.LazyLocatorList;
import com.github.karsaii.framework.selenium.enums.ManyGetter;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.WebElementList;
import com.github.karsaii.framework.selenium.records.element.finder.ElementFilterParameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LazyFilteredElementParameters extends AbstractLazyFilteredElementParameters<WebDriver, ManyGetter, ElementFilterParameters, WebElementList, WebElement> {
    public LazyFilteredElementParameters(ElementFilterData<?> elementFilterData, Class clazz, double probability, LazyLocatorList lazyLocators, String getter) {
        super(elementFilterData, clazz, probability, lazyLocators, getter);
    }
}
