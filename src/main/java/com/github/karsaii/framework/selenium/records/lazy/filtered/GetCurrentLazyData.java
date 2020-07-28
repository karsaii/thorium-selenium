package com.github.karsaii.framework.selenium.records.lazy.filtered;

import com.github.karsaii.framework.core.abstracts.lazy.filtered.BaseFilterData;
import com.github.karsaii.framework.core.namespaces.extensions.boilers.LazyLocatorList;
import com.github.karsaii.framework.selenium.enums.ManyGetter;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.WebElementList;
import com.github.karsaii.framework.selenium.records.element.finder.ElementFilterParameters;
import com.github.karsaii.framework.selenium.records.lazy.CachedLookupKeysData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Objects;

public class GetCurrentLazyData {
    public final CachedLookupKeysData data;
    public final BaseFilterData<WebDriver, ManyGetter, ?, ElementFilterParameters, WebElementList, WebElement> elementFilterData;
    public final LazyLocatorList locators;
    public final String getter;

    public GetCurrentLazyData(CachedLookupKeysData data, BaseFilterData<WebDriver, ManyGetter, ?, ElementFilterParameters, WebElementList, WebElement> elementFilterData, LazyLocatorList locators, String getter) {
        this.data = data;
        this.elementFilterData = elementFilterData;
        this.locators = locators;
        this.getter = getter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final var that = (GetCurrentLazyData) o;
        return Objects.equals(data, that.data) &&
                Objects.equals(elementFilterData, that.elementFilterData) &&
                Objects.equals(locators, that.locators) &&
                Objects.equals(getter, that.getter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, elementFilterData, locators, getter);
    }
}
