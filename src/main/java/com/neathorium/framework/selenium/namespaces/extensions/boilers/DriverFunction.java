package com.neathorium.framework.selenium.namespaces.extensions.boilers;

import com.neathorium.core.records.Data;
import org.openqa.selenium.WebDriver;

import java.util.function.Function;

@FunctionalInterface
public interface DriverFunction<T> extends Function<WebDriver, Data<T>> {
    default Function<WebDriver, Data<T>> get() {
        return this;
    }
}
