package com.github.karsaii.framework.selenium.constants;

import com.github.karsaii.core.namespaces.validators.CoreFormatter;
import com.github.karsaii.framework.selenium.constants.validators.SeleniumFormatterConstants;
import com.github.karsaii.framework.selenium.records.element.is.ElementFormatData;

public class ElementFormatDataConstants {
    public static final ElementFormatData<Boolean> PRESENT_DATA = new ElementFormatData<>(CoreFormatter::getConditionMessage, "isElementPresent", SeleniumFormatterConstants.PRESENT);
    public static final ElementFormatData<Boolean> DISPLAYED_DATA = new ElementFormatData<>(CoreFormatter::getConditionMessage, "isElementDisplayed", SeleniumFormatterConstants.DISPLAYED);
    public static final ElementFormatData<Boolean> ENABLED_DATA = new ElementFormatData<>(CoreFormatter::getConditionMessage, "isElementEnabled", SeleniumFormatterConstants.ENABLED);
    public static final ElementFormatData<Boolean> CLICKABLE_DATA = new ElementFormatData<>(CoreFormatter::getConditionMessage, "isElementClickable", SeleniumFormatterConstants.CLICKABLE);
    public static final ElementFormatData<Boolean> SELECTED_DATA = new ElementFormatData<>(CoreFormatter::getConditionMessage, "isElementSelected", SeleniumFormatterConstants.SELECTED);

    public static final ElementFormatData<Boolean> ABSENT_DATA = new ElementFormatData<>(CoreFormatter::getConditionMessage, "isElementAbsent", SeleniumFormatterConstants.ABSENT);
    public static final ElementFormatData<Boolean> HIDDEN_DATA = new ElementFormatData<>(CoreFormatter::getConditionMessage, "isElementHidden", SeleniumFormatterConstants.HIDDEN);
    public static final ElementFormatData<Boolean> DISABLED_DATA = new ElementFormatData<>(CoreFormatter::getConditionMessage, "isElementDisabled", SeleniumFormatterConstants.DISABLED);
    public static final ElementFormatData<Boolean> UNCLICKABLE_DATA = new ElementFormatData<>(CoreFormatter::getConditionMessage, "isElementUnclickable", SeleniumFormatterConstants.UNCLICKABLE);
    public static final ElementFormatData<Boolean> UNSELECTED_DATA = new ElementFormatData<>(CoreFormatter::getConditionMessage, "isElementUnselected", SeleniumFormatterConstants.UNSELECTED);

    public static final ElementFormatData<String> TEXT_DATA = new ElementFormatData<>(CoreFormatter::getElementValueMessage, "getElementText", SeleniumFormatterConstants.TEXT);
    public static final ElementFormatData<String> TAGNAME_DATA = new ElementFormatData<>(CoreFormatter::getElementValueMessage, "getElementTagName", SeleniumFormatterConstants.TAGNAME);
    public static final ElementFormatData<String> ATTRIBUTE_DATA = new ElementFormatData<>(CoreFormatter::getElementValueMessage, "getElementAttribute", SeleniumFormatterConstants.ATTRIBUTE);
    public static final ElementFormatData<String> VALUE_ATTRIBUTE_DATA = new ElementFormatData<>(CoreFormatter::getElementValueMessage, "getElementValueAttribute", SeleniumFormatterConstants.VALUE_ATTRIBUTE);
    public static final ElementFormatData<String> CSS_VALUE_DATA = new ElementFormatData<>(CoreFormatter::getElementValueMessage, "getElementCssValue", SeleniumFormatterConstants.CSS_VALUE);
}
