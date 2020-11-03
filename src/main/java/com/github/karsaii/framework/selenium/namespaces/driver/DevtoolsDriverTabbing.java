package com.github.karsaii.framework.selenium.namespaces.driver;

import com.github.karsaii.core.namespaces.clipboard.ClipboardFunctions;
import com.github.karsaii.core.namespaces.systemidentity.BasicSystemIdentityFunctions;
import com.github.karsaii.framework.selenium.constants.clipboard.CopyPasteConstants;
import com.github.karsaii.framework.selenium.constants.driver.devtools.DevtoolsViewConstants;
import com.github.karsaii.framework.selenium.namespaces.SeleniumExecutor;
import com.github.karsaii.framework.selenium.namespaces.element.Element;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.github.karsaii.framework.selenium.namespaces.factories.DriverFunctionFactory;
import org.openqa.selenium.Keys;

public interface DevtoolsDriverTabbing {
    private static DriverFunction<Boolean> copyCommand(String command) {
        return DriverFunctionFactory.getFunction((driver) -> ClipboardFunctions.copyToClipboard(command));
    }

    static DriverFunction<Boolean> inputTab() {
        return SeleniumExecutor.execute(
            "inputTab",
            DevtoolsDriverUtilities.sleep(),
            Element.sendKeys(DevtoolsViewConstants.BODY, DevtoolsViewConstants.TAB_INPUT)
        );
    }

    static DriverFunction<Boolean> inputTabAndCommand(String command) {
        return SeleniumExecutor.execute(
            "inputTabAndCommand",
            DevtoolsDriverUtilities.sleep(),
            copyCommand(command),
            BasicSystemIdentityFunctions.isMac() ? (
                Element.sendKeys(DevtoolsViewConstants.BODY, Keys.chord(DevtoolsViewConstants.TAB_INPUT, Keys.chord(Keys.COMMAND, "v"), Keys.END, Keys.ENTER))
            ) : Element.sendKeys(DevtoolsViewConstants.BODY, Keys.chord(DevtoolsViewConstants.TAB_INPUT, Keys.chord(Keys.CONTROL, "v"), Keys.END, Keys.ENTER))
        );
    }

    static DriverFunction<Boolean> inputTabAndEnter() {
        return SeleniumExecutor.execute(
            "inputTabAndCommand",
            DevtoolsDriverUtilities.sleep(),
            Element.sendKeys(DevtoolsViewConstants.BODY, Keys.chord(DevtoolsViewConstants.TAB_INPUT, Keys.ENTER))
        );
    }

    static DriverFunction<Boolean> repeatTab(int amount) {
        return SeleniumExecutor.repeat("repeatTab", inputTab(), amount);
    }

    static DriverFunction<Boolean> tabToBeforeClear() {
        return SeleniumExecutor.execute(
            "tabToClear",
            Element.clickWhenClickable(DevtoolsViewConstants.BODY),
            repeatTab(5)
        );
    }

    static DriverFunction<Boolean> tabToConsoleAfterClear() {
        return SeleniumExecutor.execute(
            "tabToConsoleAfterClear",
            repeatTab(6)
        );
    }

}
