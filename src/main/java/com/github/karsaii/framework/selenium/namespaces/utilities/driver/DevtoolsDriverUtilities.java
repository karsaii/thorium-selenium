package com.github.karsaii.framework.selenium.namespaces.utilities.driver;

import com.github.karsaii.core.namespaces.DataFactoryFunctions;
import com.github.karsaii.core.namespaces.wait.Wait;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.selenium.constants.driver.devtools.DevtoolsDriverFunctionConstants;
import com.github.karsaii.framework.selenium.constants.driver.devtools.DevtoolsViewConstants;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import org.apache.commons.lang3.BooleanUtils;
import org.openqa.selenium.WebDriver;

public interface DevtoolsDriverUtilities {
    private static Data<Boolean> sleepAndAction(WebDriver driver, DriverFunction<Boolean> action) {
        Wait.sleep(DevtoolsViewConstants.TAB_SLEEP_MILLIS);
        return action.apply(driver);
    }

    static DriverFunction<Boolean> sleepAndAction(DriverFunction<Boolean> action) {
        return driver -> sleepAndAction(driver, action);
    }

    static Data<Boolean> getBooleanConsoleResultCore(WebDriver driver) {
        final var result = DevtoolsDriverFunctionConstants.GET_CONSOLE_RESULT.apply(driver);
        final var status = BooleanUtils.toBoolean(result.object);
        return DataFactoryFunctions.getBoolean(status, "getBooleanConsoleResultCore", result.message.toString(), result.exception, result.exceptionMessage);
    }

    static DriverFunction<Boolean> getBooleanConsoleResult() {
        return DevtoolsDriverUtilities::getBooleanConsoleResultCore;
    }
}
