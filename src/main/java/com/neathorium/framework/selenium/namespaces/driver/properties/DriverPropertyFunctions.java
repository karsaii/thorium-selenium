package com.neathorium.framework.selenium.namespaces.driver.properties;

import com.neathorium.framework.selenium.constants.validators.SeleniumFormatterConstants;
import com.neathorium.framework.selenium.namespaces.Driver;
import com.neathorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.core.constants.CoreConstants;
import com.neathorium.core.constants.CoreDataConstants;
import com.neathorium.core.constants.validators.CoreFormatterConstants;
import com.neathorium.core.extensions.boilers.StringSet;
import com.neathorium.core.extensions.namespaces.CoreUtilities;
import com.neathorium.core.extensions.namespaces.NullableFunctions;
import com.neathorium.core.namespaces.BaseExecutionFunctions;
import com.neathorium.core.namespaces.DataFactoryFunctions;
import com.neathorium.core.records.Data;
import com.neathorium.framework.selenium.namespaces.ExecutionCore;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;

import java.util.function.Function;
import java.util.function.Predicate;

import static com.neathorium.framework.selenium.namespaces.ExecutionCore.ifDriver;
import static com.neathorium.framework.selenium.namespaces.ExecutionCore.ifDriverGuardData;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public interface DriverPropertyFunctions {
    private static <T> Data<T> getPropertyCore(WebDriver driver, String property, Predicate<T> guard, Function<WebDriver, T> function, T defaultValue) {
        final var result = function.apply(driver);
        final var status = guard.test(result);
        final var object = status ? result : defaultValue;
        final var message = (status ? " is: \"" + result + "\"" + CoreFormatterConstants.END_LINE : CoreFormatterConstants.WAS_NULL);
        return DataFactoryFunctions.getWith(object, status, "getCore", property + message);
    }

    private static <T> DriverFunction<T> getPropertyCore(String property, Predicate<T> guard, Function<WebDriver, T> function, T defaultValue) {
        return driver -> getPropertyCore(driver, property, guard, function, defaultValue);
    }

    private static <T> DriverFunction<T> getProperty(String property, Predicate<T> guard, Function<WebDriver, T> function, T defaultValue) {
        var nameof = "getProperty";
        final var isPropertyNotBlank = isNotBlank(property);
        if (isPropertyNotBlank) {
            nameof += " - (\"" + property + "\")";
        }

        final var status = isPropertyNotBlank && CoreUtilities.areNotNull(guard, function, defaultValue);
        return ifDriver(nameof, status, getPropertyCore(property, guard, function, defaultValue), DataFactoryFunctions.getWith(defaultValue, false, SeleniumFormatterConstants.DRIVER_WAS_NULL));
    }

    private static DriverFunction<String> getString(String property, Function<WebDriver, String> function) {
        return getProperty(property, NullableFunctions::isNotNull, function, CoreFormatterConstants.EMPTY);
    }

    static DriverFunction<String> getTitle() {
        return getString("Title", WebDriver::getTitle);
    }

    static DriverFunction<String> getWindowHandle() {
        return getString("WindowHandle", WebDriver::getWindowHandle);
    }

    static DriverFunction<String> getUrl() {
        return getProperty("Url", StringUtils::isNotBlank, WebDriver::getCurrentUrl, CoreFormatterConstants.EMPTY);
    }

    static DriverFunction<StringSet> getWindowHandles() {
        final var getStringSetOfWindowHandles = BaseExecutionFunctions.conditionalChain(NullableFunctions::isNotNull, WebDriver::getWindowHandles, StringSet::new, CoreConstants.NULL_STRING_SET);
        return getProperty("WindowHandles", NullableFunctions::isNotNull, getStringSetOfWindowHandles, CoreConstants.NULL_STRING_SET);
    }

    static DriverFunction<Integer> getWindowHandleAmount() {
        return ExecutionCore.ifDriverGuardData("getWindowHandleAmount", getWindowHandles(), Driver::getWindowHandleAmountData, CoreDataConstants.NULL_INTEGER);
    }
}
