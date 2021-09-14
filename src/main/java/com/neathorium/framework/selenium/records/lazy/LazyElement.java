package com.neathorium.framework.selenium.records.lazy;

import com.neathorium.framework.selenium.namespaces.Driver;
import com.neathorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.framework.selenium.namespaces.extensions.boilers.IElement;
import com.neathorium.framework.selenium.records.lazy.filtered.LazyFilteredElementParameters;
import com.neathorium.framework.core.abstracts.AbstractLazyResult;
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
