package com.neathorium.framework.selenium.constants.scripts.general;

import com.neathorium.framework.selenium.namespaces.scripter.General;

import static com.neathorium.framework.selenium.namespaces.scripter.General.IF_RETURN;

public abstract class ClickFunctions {
    public static final String CLICK_DISPATCHER = (
        General.IF_RETURN("arguments.length < 1", "false") +
        "arguments[0].dispatchEvent(new MouseEvent('click', {view: window, bubbles:true, cancelable: true}))" +
        General.RETURN("true")
    );
}
