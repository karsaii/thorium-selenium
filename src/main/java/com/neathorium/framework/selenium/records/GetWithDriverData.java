package com.neathorium.framework.selenium.records;

import com.neathorium.core.records.Data;
import com.neathorium.framework.core.records.lazy.GetWithBaseData;
import org.openqa.selenium.WebDriver;

import java.util.function.Function;

public class GetWithDriverData<T, U, V, W> extends GetWithBaseData<WebDriver, T, U, V, W> {
    public GetWithDriverData(T locators, Function<T, U> locatorGetter, Function<V, Function<WebDriver, Data<W>>> getter, Data<W> guardData) {
        super(locators, locatorGetter, getter, guardData);
    }
}
