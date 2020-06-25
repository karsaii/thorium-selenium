package com.github.karsaii.framework.selenium.records;

import com.github.karsaii.framework.core.records.lazy.GetWithBaseData;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.github.karsaii.core.records.Data;
import org.openqa.selenium.WebDriver;

import java.util.function.Function;

public class GetWithDriverData<T, U, V, W> extends GetWithBaseData<WebDriver, T, U, V, W> {
    public GetWithDriverData(T locators, Function<T, U> locatorGetter, Function<V, Function<WebDriver, Data<W>>> getter, Data<W> guardData) {
        super(locators, locatorGetter, getter, guardData);
    }
}
