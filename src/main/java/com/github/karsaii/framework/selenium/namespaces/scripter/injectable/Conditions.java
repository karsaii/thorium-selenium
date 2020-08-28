package com.github.karsaii.framework.selenium.namespaces.scripter.injectable;

public interface Conditions {
    String NS_BASE = "TU.FUNCTIONS.ELEMENT.CONDITIONS.IS_SELECTOR_";

    private static String getCommon(String base, String name, String selector) {
        return base + "('" + name + "', '" + selector + "').status";
    }

    static String getIsPresent(String name, String selector) {
        return getCommon(NS_BASE + "PRESENT", name, selector);
    }

    static String getIsAbsent(String name, String selector) {
        return getCommon(NS_BASE + "ABSENT", name, selector);
    }

    static String getIsDisplayed(String name, String selector) {
        return getCommon(NS_BASE + "DISPLAYED", name, selector);
    }

    static String getIsHidden(String name, String selector) {
        return getCommon(NS_BASE + "HIDDEN", name, selector);
    }

    static String getIsEnabled(String name, String selector) {
        return getCommon(NS_BASE + "ENABLED", name, selector);
    }

    static String getIsDisabled(String name, String selector) {
        return getCommon(NS_BASE + "DISABLED", name, selector);
    }

    static String getIsClickable(String name, String selector) {
        return getCommon(NS_BASE + "CLICKABLE", name, selector);
    }

    static String getIsUnclickable(String name, String selector) {
        return getCommon(NS_BASE + "UNCLICKABLE", name, selector);
    }
}
