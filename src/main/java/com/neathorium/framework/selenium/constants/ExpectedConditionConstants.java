package com.neathorium.framework.selenium.constants;

import com.neathorium.framework.selenium.namespaces.driver.properties.DriverPropertyFunctions;
import com.neathorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;

public abstract class ExpectedConditionConstants {
    public static final DriverFunction<String> GET_TITLE = DriverPropertyFunctions.getTitle();
    public static final DriverFunction<String> GET_URL = DriverPropertyFunctions.getUrl();
}
