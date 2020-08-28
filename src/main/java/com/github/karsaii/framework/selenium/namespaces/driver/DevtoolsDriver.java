package com.github.karsaii.framework.selenium.namespaces.driver;

import com.github.karsaii.core.constants.CoreDataConstants;
import com.github.karsaii.core.namespaces.validators.CoreFormatter;
import com.github.karsaii.framework.core.namespaces.validators.FrameworkCoreFormatter;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.github.karsaii.framework.selenium.namespaces.scripter.injectable.Actions;
import com.github.karsaii.framework.selenium.namespaces.scripter.injectable.Conditions;
import com.github.karsaii.framework.selenium.namespaces.utilities.LazyElementUtilities;
import com.github.karsaii.framework.selenium.records.lazy.LazyElement;

import java.util.function.BiFunction;

import static com.github.karsaii.framework.selenium.namespaces.ExecutionCore.ifDriver;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public interface DevtoolsDriver {
    private static String invokeElementPresent(String name, String selector) {
        return Conditions.getIsPresent(name, selector);
    }

    private static String invokeElementAbsent(String name, String selector) {
        return Conditions.getIsAbsent(name, selector);
    }

    private static String invokeElementDisplayed(String name, String selector) {
        return Conditions.getIsDisplayed(name, selector);
    }

    private static String invokeElementHidden(String name, String selector) {
        return Conditions.getIsHidden(name, selector);
    }

    private static String invokeElementEnabled(String name, String selector) {
        return Conditions.getIsEnabled(name, selector);
    }

    private static String invokeElementDisabled(String name, String selector) {
        return Conditions.getIsDisabled(name, selector);
    }

    private static String invokeElementClickable(String name, String selector) {
        return Conditions.getIsClickable(name, selector);
    }

    private static String invokeElementUnclickable(String name, String selector) {
        return Conditions.getIsUnclickable(name, selector);
    }

    private static String invokeClick(String name, String selector) {
        return Actions.getClick(name, selector);
    }

    private static String invokeSetValue(String name, String selector, String value) {
        return Actions.getSetValue(name, selector, value);
    }

    private static String invokeGetValue(String name, String selector) {
        return Actions.getGetValue(name, selector);
    }

    private static String invokeSetAttribute(String name, String selector, String attribute, String value) {
        return Actions.getSetAttribute(name, selector, attribute, value);
    }

    private static String invokeGetAttribute(String name, String selector, String attribute) {
        return Actions.getGetAttribute(name, selector, attribute);
    }

    private static String invokeGetCssValue(String name, String selector, String value) {
        return Actions.getGetCssValue(name, selector, value);
    }

    private static String invokeGetText(String name, String selector) {
        return Actions.getGetText(name, selector);
    }

    private static String invokeGetInnerText(String name, String selector) {
        return Actions.getGetInnerText(name, selector);
    }

    static DriverFunction<Boolean> elementAction(String name, LazyElement element, BiFunction<String, String, String> action) {
        final var nameof = isNotBlank(name) ? name : "elementAction";
        return ifDriver(
            nameof,
            FrameworkCoreFormatter.isNullLazyElementMessage(element),
            DevtoolsDriverUtilities.doBooleanCommand(action.apply(element.name, LazyElementUtilities.getCSSSelectorFromElement(element))),
            CoreDataConstants.NULL_BOOLEAN
        );
    }

    static DriverFunction<Boolean> isElementPresent(LazyElement element) {
        return elementAction("isElementPresent", element, DevtoolsDriver::invokeElementPresent);
    }

    static DriverFunction<Boolean> isElementAbsent(LazyElement element) {
        return elementAction("isElementAbsent", element, DevtoolsDriver::invokeElementAbsent);
    }

    static DriverFunction<Boolean> isElementDisplayed(LazyElement element) {
        return elementAction("isElementDisplayed", element, DevtoolsDriver::invokeElementDisplayed);
    }

    static DriverFunction<Boolean> isElementHidden(LazyElement element) {
        return elementAction("isElementHidden", element, DevtoolsDriver::invokeElementHidden);
    }

    static DriverFunction<Boolean> isElementEnabled(LazyElement element) {
        return elementAction("isElementEnabled", element, DevtoolsDriver::invokeElementEnabled);
    }

    static DriverFunction<Boolean> isElementDisabled(LazyElement element) {
        return elementAction("isElementDisabled", element, DevtoolsDriver::invokeElementDisabled);
    }

    static DriverFunction<Boolean> isElementClickable(LazyElement element) {
        return elementAction("isElementClickable", element, DevtoolsDriver::invokeElementClickable);
    }

    static DriverFunction<Boolean> isElementUnclickable(LazyElement element) {
        return elementAction("isElementUnclickable", element, DevtoolsDriver::invokeElementUnclickable);
    }

    static DriverFunction<Boolean> click(LazyElement element) {
        return elementAction("click", element, DevtoolsDriver::invokeClick);
    }

    static DriverFunction<Boolean> getValue(LazyElement element) {
        return elementAction("getValue", element, DevtoolsDriver::invokeGetValue);
    }

    static DriverFunction<Boolean> getText(LazyElement element) {
        return elementAction("getText", element, DevtoolsDriver::invokeGetText);
    }

    static DriverFunction<Boolean> getInnerText(LazyElement element) {
        return elementAction("getInnerText", element, DevtoolsDriver::invokeGetInnerText);
    }

    static DriverFunction<Boolean> setValue(LazyElement element, String value) {
        return ifDriver(
            "setValue",
            FrameworkCoreFormatter.isNullLazyElementMessage(element) + CoreFormatter.isBlankMessageWithName(value, "Value"),
            DevtoolsDriverUtilities.doBooleanCommand(invokeSetValue(element.name, LazyElementUtilities.getCSSSelectorFromElement(element), value)),
            CoreDataConstants.NULL_BOOLEAN
        );
    }

    static DriverFunction<Boolean> getAttribute(LazyElement element, String attribute) {
        return ifDriver(
            "getAttribute",
            FrameworkCoreFormatter.isNullLazyElementMessage(element) + CoreFormatter.isBlankMessageWithName(attribute, "Attribute"),
            DevtoolsDriverUtilities.doBooleanCommand(invokeGetAttribute(element.name, LazyElementUtilities.getCSSSelectorFromElement(element), attribute)),
            CoreDataConstants.NULL_BOOLEAN
        );
    }

    static DriverFunction<Boolean> getCssValue(LazyElement element, String value) {
        return ifDriver(
            "getCssValue",
            FrameworkCoreFormatter.isNullLazyElementMessage(element) + CoreFormatter.isBlankMessageWithName(value, "CSS Value"),
            DevtoolsDriverUtilities.doBooleanCommand(invokeGetCssValue(element.name, LazyElementUtilities.getCSSSelectorFromElement(element), value)),
            CoreDataConstants.NULL_BOOLEAN
        );
    }

    static DriverFunction<Boolean> setAttribute(LazyElement element, String attribute, String value) {
        return ifDriver(
            "setAttribute",
            (
                FrameworkCoreFormatter.isNullLazyElementMessage(element) +
                CoreFormatter.isBlankMessageWithName(attribute, "attribute") +
                CoreFormatter.isBlankMessageWithName(value, "Value")
            ),
            DevtoolsDriverUtilities.doBooleanCommand(invokeSetAttribute(element.name, LazyElementUtilities.getCSSSelectorFromElement(element), attribute, value)),
            CoreDataConstants.NULL_BOOLEAN
        );
    }
}
