package com.github.karsaii.framework.selenium.records.lazy;

import com.github.karsaii.framework.core.abstracts.AbstractLazyResult;
import com.github.karsaii.framework.selenium.namespaces.Driver;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.IElement;
import com.github.karsaii.framework.selenium.records.lazy.filtered.LazyFilteredElementParameters;
import org.openqa.selenium.WebElement;

import java.util.Map;
import java.util.function.Predicate;

public class LazyElement extends AbstractLazyResult<LazyFilteredElementParameters> implements IElement {
    public LazyElement(String name, Map<String, LazyFilteredElementParameters> parameters, Predicate<LazyFilteredElementParameters> validator) {
        super(name, parameters, validator);
    }

    public DriverFunction<WebElement> get() {
        return Driver.getLazyElement(this);
    }
}
