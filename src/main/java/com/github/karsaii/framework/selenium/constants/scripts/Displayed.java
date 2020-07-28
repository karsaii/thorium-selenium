package com.github.karsaii.framework.selenium.constants.scripts;

import static com.github.karsaii.framework.selenium.namespaces.scripter.General.IF_RETURN;
import static com.github.karsaii.framework.selenium.namespaces.scripter.General.RETURN;

public abstract class Displayed {
    public static final String DISPLAYED_FUNCTION_NAME = "isDisplayed";

    public static final String IS_ELEMENT_DISPLAYED = DISPLAYED_FUNCTION_NAME + "(element)";

    public static final String DISPLAYED_CORE = (
        "var " + DISPLAYED_FUNCTION_NAME + " = function isDisplayedFunction(element) {" +
        "    var isElement = element instanceof Element;" +
        "    var displayNotNone = getComputedStyle(element).display !== 'none';" +
        "    var visibilityNotHidden = getComputedStyle(element).visibility !== 'hidden';" +
        "    var clientHeightNotZero = element.clientHeight !== 0;" +
        "    var rectsLengthNotZero = element.getClientRects().length !== 0;" +
        "" +
        "    return (isElement && displayNotNone && visibilityNotHidden && clientHeightNotZero && rectsLengthNotZero);" +
        "}\r"
    );

    public static final String IS_DISPLAYED_DISPATCHER = (
        IF_RETURN("arguments.length < 1", "false") + DISPLAYED_CORE + RETURN(DISPLAYED_FUNCTION_NAME + "(arguments[0])")
    );

    public static final String IS_DISPLAYED_CONSOLE_DISPATCHER = (
        IF_RETURN("!element instanceof Element", "false") + DISPLAYED_CORE + RETURN(IS_ELEMENT_DISPLAYED)
    );
}
