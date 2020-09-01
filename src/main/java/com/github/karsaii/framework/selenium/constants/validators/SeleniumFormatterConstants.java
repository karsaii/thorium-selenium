package com.github.karsaii.framework.selenium.constants.validators;

import com.github.karsaii.core.constants.validators.CoreFormatterConstants;
import org.openqa.selenium.Keys;

public abstract class SeleniumFormatterConstants {
    public static final String CLICKABLE = "clickable";
    public static final String UNCLICKABLE = "unclickable";
    public static final String DISPLAYED = "displayed";
    public static final String HIDDEN = "hidden";
    public static final String ENABLED = "enabled";
    public static final String DISABLED = "disabled";
    public static final String PRESENT = "present";
    public static final String ABSENT = "absent";
    public static final String SELECTED = "selected";
    public static final String UNSELECTED = "unselected";
    public static final String TEXT = "Text";
    public static final String TAGNAME = "TagName";
    public static final String ATTRIBUTE = "Attribute";
    public static final String VALUE_ATTRIBUTE = "Attribute(Value)";
    public static final String CSS_VALUE = "CSS Value";
    public static final String FIND_ELEMENT = "findElement";
    public static final String DEFAULT_GETTER_ELEMENT = "getElement";
    public static final String DEFAULT_GETTER_ELEMENTS = "getElements";
    public static final String ELEMENT_PARAMETERS = CoreFormatterConstants.ELEMENT + " parameters";
    public static final String LAZY_ELEMENT = "Lazy " + CoreFormatterConstants.ELEMENT;
    public static final String LAZY_ELEMENT_WAS_NULL = LAZY_ELEMENT + CoreFormatterConstants.WAS_NULL;
    public static final String LAZY_ELEMENT_WAIT_PARAMETERS_WERE_NULL = LAZY_ELEMENT + " wait parameters" + CoreFormatterConstants.WERE_NULL;
    public static final String LOCATOR = "Locator ";
    public static final String CLASSES = "Classes";
    public static final String ELEMENTS = "Elements";
    public static final String IDS = "IDs";
    public static final String WINDOWS = "windows";
    public static final String ELEMENT_TEXT = CoreFormatterConstants.ELEMENT + "text";
    public static final String ELEMENT_ATTRIBUTE = CoreFormatterConstants.ELEMENT + "attribute";
    public static final String ELEMENT_ATTRIBUTE_VALUE = ELEMENT_ATTRIBUTE + " value";
    public static final String TITLE_OF_WINDOW = "Title of window ";
    public static final String NULL_ELEMENT_ID = "NULL_ELEMENT_ID";
    public static final String COULDNT_SWITCH_TO = "Couldn't switch to ";
    public static final String SUCCESSFULLY_SWITCHED_TO = "Switched to ";
    public static final String ELEMENT_CLICKABLE = " " + CoreFormatterConstants.ELEMENT + "is clickable" + CoreFormatterConstants.END_LINE;
    public static final String ELEMENT_WAS_FOUND = "Element was found" + CoreFormatterConstants.END_LINE;
    public static final String DRIVER_WAS_NULL = "Driver" + CoreFormatterConstants.WAS_NULL;
    public static final String GET_FORMATTED_ELEMENT_VALUE_ERROR = "Element value not found. Parameters were wrong" + CoreFormatterConstants.END_LINE;
    public static final String RAW_WEBELEMENT_PASSED = "Raw WebElement passed, anything might happen" + CoreFormatterConstants.END_LINE;
    public static final String ELEMENT_LIST_EMPTY_OR_NULL = "No elements found, or element list " + CoreFormatterConstants.WAS_NULL;
    public static final String LOCATOR_WAS_NULL = "Locator" + CoreFormatterConstants.WAS_NULL;
    public static final String FIND_ELEMENTS_SUCCESSFUL = "Find elements execution successful" + CoreFormatterConstants.END_LINE;
    public static final String FIND_ELEMENTS_EXCEPTION = "Exception occurred during finding elements" + CoreFormatterConstants.END_LINE;
    public static final String SELECT_ALL = Keys.chord(Keys.CONTROL, "a", Keys.BACK_SPACE);
    public static final String NONE = "none";
    public static final String TYPE_NOT_IN_CACHE_MAP = "The parameter map didn't contain an expected, indexed selector-type" + CoreFormatterConstants.END_LINE;

    public static final String CLEAR_CONSOLE = Keys.chord(Keys.CONTROL, "l");
}
