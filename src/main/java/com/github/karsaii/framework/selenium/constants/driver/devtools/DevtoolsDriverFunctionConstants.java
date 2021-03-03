package com.github.karsaii.framework.selenium.constants.driver.devtools;

import com.github.karsaii.core.constants.systemidentity.BasicSystemIdentityConstants;
import com.github.karsaii.core.namespaces.systemidentity.BasicSystemIdentityFunctions;
import com.github.karsaii.framework.selenium.namespaces.SeleniumExecutor;
import com.github.karsaii.framework.selenium.namespaces.element.Element;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import org.openqa.selenium.Keys;

import java.util.Map;

import static com.github.karsaii.framework.selenium.namespaces.utilities.driver.DevtoolsDriverUtilities.sleepAndAction;
import static java.util.Map.entry;

public abstract class DevtoolsDriverFunctionConstants {
    public static final DriverFunction<Boolean> TAB = SeleniumExecutor.execute("inputTab", sleepAndAction(Element.sendKeys(DevtoolsViewConstants.BODY, DevtoolsViewConstants.TAB_INPUT)));

    public static final DriverFunction<Boolean> FIVE_TABS = SeleniumExecutor.repeat("repeatTab", TAB, 5);
    public static final DriverFunction<Boolean> SIX_TABS = SeleniumExecutor.repeat("repeatTab", TAB, 6);
    public static final DriverFunction<Boolean> ELEVEN_TABS = SeleniumExecutor.repeat("repeatTab", TAB, 11);

    public static final DriverFunction<Boolean> TAB_AND_ENTER = SeleniumExecutor.execute("tabAndEnter", sleepAndAction(Element.sendKeys(DevtoolsViewConstants.BODY, Keys.chord(DevtoolsViewConstants.TAB_INPUT, Keys.ENTER))));
    public static final DriverFunction<Boolean> TAB_TO_BEFORE_CLEAR_BUTTON = SeleniumExecutor.execute("tabToClear", Element.clickWhenClickable(DevtoolsViewConstants.BODY), FIVE_TABS);
    public static final DriverFunction<Boolean> CLEAR_CONSOLE = SeleniumExecutor.execute("clearConsole", TAB_TO_BEFORE_CLEAR_BUTTON, TAB_AND_ENTER);
    public static final DriverFunction<Boolean> TAB_TO_CONSOLE_AFTER_CLEAR = SeleniumExecutor.execute("tabToConsoleAfterClear", SIX_TABS);
    public static final DriverFunction<Boolean> NO_RESULT_IN_CONSOLE = SeleniumExecutor.execute("noResultInConsole", Element.waitAbsent(DevtoolsViewConstants.RESULT_FIELD, 300, 3000));
    public static final DriverFunction<Boolean> CONSOLE_RESULT_DISPLAYED = SeleniumExecutor.execute("consoleResultDisplayed", Element.waitDisplayed(DevtoolsViewConstants.RESULT_FIELD, 300, 3000));
    public static final DriverFunction<String> GET_CONSOLE_RESULT = SeleniumExecutor.execute("getConsoleResult", DevtoolsDriverFunctionConstants.CONSOLE_RESULT_DISPLAYED, Element.getText(DevtoolsViewConstants.RESULT_FIELD));


    private static final DriverFunction<Boolean> WINDOWS_KEYBOARD_PASTE = Element.sendKeys(DevtoolsViewConstants.BODY, Keys.chord(DevtoolsViewConstants.TAB_INPUT, Keys.chord(Keys.CONTROL, "v"), Keys.END, Keys.ENTER));
    private static final DriverFunction<Boolean> MAC_KEYBOARD_PASTE = Element.sendKeys(DevtoolsViewConstants.BODY, Keys.chord(DevtoolsViewConstants.TAB_INPUT, Keys.chord(Keys.SHIFT, Keys.INSERT), Keys.END, Keys.ENTER));

    private static final Map<String, DriverFunction<Boolean>> PASTE_FUNCTIONS = Map.copyOf(Map.ofEntries(
        entry(BasicSystemIdentityConstants.WINDOWS, WINDOWS_KEYBOARD_PASTE),
        entry(BasicSystemIdentityConstants.LINUX, WINDOWS_KEYBOARD_PASTE),
        entry(BasicSystemIdentityConstants.MAC, MAC_KEYBOARD_PASTE)
    ));

    public static final DriverFunction<Boolean> PASTE_FUNCTION = DevtoolsDriverFunctionConstants.PASTE_FUNCTIONS.get(BasicSystemIdentityFunctions.getSystemTypeOrUnknown());
}
