package com.github.karsaii.framework.selenium.constants;

import com.github.karsaii.framework.selenium.namespaces.Driver;
import com.github.karsaii.framework.selenium.namespaces.driver.properties.DriverPropertyFunctions;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.DriverFunction;

public abstract class ExpectedConditionConstants {
    public static final DriverFunction<String> GET_TITLE = DriverPropertyFunctions.getTitle();
    public static final DriverFunction<String> GET_URL = DriverPropertyFunctions.getUrl();
}
