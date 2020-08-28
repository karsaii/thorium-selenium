package com.github.karsaii.framework.selenium.namespaces.scripter.injectable;

import com.github.karsaii.framework.selenium.constants.driver.devtools.DevtoolsConstants;

public interface Actions {
    String NS_BASE = "TU.FUNCTIONS.ELEMENT.";
    private static String getBooleanCommon(String base, String name, String selector) {
        return base + "('" + name + "', '" + selector + "').status" + DevtoolsConstants.ENTER_INPUT;
    }

    private static String getBooleanCommon(String base, String name, String selector, String valueSource) {
        return base + "('" + name + "', '" + selector + "', '" + valueSource + "').status" + DevtoolsConstants.ENTER_INPUT;
    }

    private static String getBooleanCommon(String base, String name, String selector, String valueSource, String assignedValue) {
        return base + "('" + name + "', '" + selector + "', '" + valueSource  + "', '" + assignedValue + "').status" + DevtoolsConstants.ENTER_INPUT;
    }

    private static String getStringCommon(String base, String name, String selector) {
        return base + "('" + name + "', '" + selector + "').object" + DevtoolsConstants.ENTER_INPUT;
    }

    private static String getStringCommon(String base, String name, String selector, String valueSource) {
        return base + "('" + name + "', '" + selector + "', '" + valueSource + "').object" + DevtoolsConstants.ENTER_INPUT;
    }

    static String getClick(String name, String selector) {
        return getBooleanCommon(NS_BASE + "CLICK_SELECTOR", name, selector);
    }

    static String getSetValue(String name, String selector, String value) {
        return getBooleanCommon(NS_BASE + "SET_VALUE_SELECTOR", name, selector, value);
    }

    static String getSetAttribute(String name, String selector, String attribute, String value) {
        return getBooleanCommon(NS_BASE + "SET_ATTRIBUTE_SELECTOR", name, selector, attribute, value);
    }

    static String getGetValue(String name, String selector) {
        return getStringCommon(NS_BASE + "GET_VALUE_SELECTOR", name, selector);
    }

    static String getGetText(String name, String selector) {
        return getStringCommon(NS_BASE + "GET_TEXT_SELECTOR", name, selector);
    }

    static String getGetInnerText(String name, String selector) {
        return getStringCommon(NS_BASE + "GET_INNER_TEXT_SELECTOR", name, selector);
    }

    static String getGetAttribute(String name, String selector, String attribute) {
        return getStringCommon(NS_BASE + "GET_ATTRIBUTE_SELECTOR", name, selector, attribute);
    }

    static String getGetCssValue(String name, String selector, String value) {
        return getStringCommon(NS_BASE + "GET_CSS_VALUE_SELECTOR", name, selector, value);
    }
}
