package com.github.karsaii.framework.selenium.constants.scripter;

public abstract class DevtoolsConstants {
    public static final String GUARD = "return ((typeof document['__test']) === (typeof {}))";
    public static final String CONSOLE_FOCUSED_CHECK = (
        "var isFocused = Boolean(document['__test']['consoleFocused']) || false;" +
        "if (document['__test']['consoleFocused']) {" +
        "    document['__test']['consoleFocused'] = false;" +
        "}" +
        "" +
        "return isFocused;"
    );
}
