package com.github.karsaii.framework.selenium.namespaces.scripter.injectable;

import com.github.karsaii.framework.selenium.records.lazy.LazyElement;
public interface Actions {
    String NS_BASE = "TU.FUNCTIONS.ELEMENT.";

    static String getClick(LazyElement element) {
        return MetaCore.getBoolean(NS_BASE + "CLICK", element);
    }

    static String getSetValue(LazyElement element, String value) {
        return MetaCore.getBoolean(NS_BASE + "SET_VALUE", element, value);
    }

    static String getSetAttribute(LazyElement element, String attribute, String value) {
        return MetaCore.getBoolean(NS_BASE + "SET_ATTRIBUTE", element, attribute, value);
    }

    static String getGetValue(LazyElement element) {
        return MetaCore.getString(NS_BASE + "GET_VALUE", element);
    }

    static String getGetText(LazyElement element) {
        return MetaCore.getString(NS_BASE + "GET_TEXT", element);
    }

    static String getGetInnerText(LazyElement element) {
        return MetaCore.getString(NS_BASE + "GET_INNER_TEXT", element);
    }

    static String getGetAttribute(LazyElement element, String attribute) {
        return MetaCore.getString(NS_BASE + "GET_ATTRIBUTE", element, attribute);
    }

    static String getGetCssValue(LazyElement element, String value) {
        return MetaCore.getString(NS_BASE + "GET_CSS_VALUE", element, value);
    }
}
