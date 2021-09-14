package com.neathorium.framework.selenium.records.lazy.filtered;

import com.neathorium.framework.selenium.enums.ManyGetter;
import com.neathorium.framework.selenium.namespaces.extensions.boilers.WebElementList;
import com.neathorium.framework.selenium.records.element.finder.ElementFilterParameters;
import com.neathorium.framework.core.abstracts.lazy.filtered.AbstractLazyFilteredElementParameters;
import com.neathorium.framework.core.namespaces.extensions.boilers.LazyLocatorList;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LazyFilteredElementParameters extends AbstractLazyFilteredElementParameters<WebDriver, ManyGetter, ElementFilterParameters, LazyLocatorList, WebElementList, WebElement> {
    public LazyFilteredElementParameters(ElementFilterData<?> elementFilterData, Class<?> clazz, double probability, LazyLocatorList lazyLocators, String getter) {
        super(elementFilterData, clazz, probability, lazyLocators, getter);
    }
}
