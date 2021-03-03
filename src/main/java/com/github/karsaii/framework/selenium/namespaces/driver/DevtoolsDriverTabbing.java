package com.github.karsaii.framework.selenium.namespaces.driver;

import com.github.karsaii.core.namespaces.clipboard.ClipboardFunctions;
import com.github.karsaii.framework.selenium.constants.driver.devtools.DevtoolsDriverFunctionConstants;
import com.github.karsaii.framework.selenium.namespaces.SeleniumExecutor;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.github.karsaii.framework.selenium.namespaces.factories.DriverFunctionFactory;
import com.github.karsaii.framework.selenium.namespaces.utilities.driver.DevtoolsDriverUtilities;

public interface DevtoolsDriverTabbing {
    private static DriverFunction<Boolean> copyCommand(String command) {
        return DriverFunctionFactory.getFunction((driver) -> ClipboardFunctions.copyToClipboard(command));
    }

    static DriverFunction<Boolean> inputTabAndCommand(String command) {
        return SeleniumExecutor.execute("inputTabAndCommand", copyCommand(command), DevtoolsDriverUtilities.sleepAndAction(DevtoolsDriverFunctionConstants.PASTE_FUNCTION));
    }
}
