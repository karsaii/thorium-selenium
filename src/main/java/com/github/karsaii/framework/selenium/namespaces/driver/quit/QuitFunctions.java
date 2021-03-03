package com.github.karsaii.framework.selenium.namespaces.driver.quit;

import com.github.karsaii.core.extensions.namespaces.CoreUtilities;
import com.github.karsaii.core.namespaces.DataFactoryFunctions;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.core.records.HandleResultData;
import com.github.karsaii.framework.selenium.constants.SeleniumDataConstants;
import com.github.karsaii.framework.selenium.namespaces.SeleniumExceptionHandlers;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import org.openqa.selenium.WebDriver;

import static com.github.karsaii.core.extensions.namespaces.CoreUtilities.isNonException;
import static com.github.karsaii.framework.selenium.namespaces.ExecutionCore.ifDriver;

public interface QuitFunctions {
    private static Void quit(WebDriver driver) {
        driver.quit();
        return null;
    }

    private static Data<Boolean> quitDriverCore(WebDriver driver) {
        final var data = new HandleResultData<>(QuitFunctions::quit, driver, null);
        final var result = SeleniumExceptionHandlers.quitHandler(data);
        final var exception = result.exception;
        final var status = isNonException(exception);
        var message = result.message.message;
        if (CoreUtilities.isFalse(status)) {
            message += "Exception:" + exception.getClass() + " Message: " + exception.getMessage();
        }

        return DataFactoryFunctions.getBoolean(status, message, exception);
    }

    static DriverFunction<Boolean> quitDriver() {
        return ifDriver("quitDriver", QuitFunctions::quitDriverCore, SeleniumDataConstants.DRIVER_WAS_NULL);
    }
}
