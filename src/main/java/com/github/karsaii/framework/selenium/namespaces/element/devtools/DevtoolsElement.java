package com.github.karsaii.framework.selenium.namespaces.element.devtools;

import com.github.karsaii.core.constants.CoreDataConstants;
import com.github.karsaii.core.constants.validators.CoreFormatterConstants;
import com.github.karsaii.core.extensions.interfaces.functional.TriFunction;
import com.github.karsaii.core.namespaces.DataExecutionFunctions;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.core.namespaces.validators.FrameworkCoreFormatter;
import com.github.karsaii.framework.selenium.constants.page.DefaultElementConstants;
import com.github.karsaii.framework.selenium.constants.validators.SeleniumFormatterConstants;
import com.github.karsaii.framework.selenium.namespaces.Driver;
import com.github.karsaii.framework.selenium.namespaces.SeleniumExecutor;
import com.github.karsaii.framework.selenium.namespaces.driver.DevtoolsDriver;
import com.github.karsaii.framework.selenium.namespaces.element.Element;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.github.karsaii.framework.selenium.namespaces.factories.DriverFunctionFactory;
import com.github.karsaii.framework.selenium.namespaces.validators.SeleniumFormatter;
import com.github.karsaii.framework.selenium.records.lazy.LazyElement;

import java.util.function.BiFunction;
import java.util.function.Function;

import static com.github.karsaii.core.namespaces.predicates.DataPredicates.isValidNonFalse;
import static com.github.karsaii.framework.selenium.namespaces.ExecutionCore.ifDriver;
import static com.github.karsaii.framework.selenium.namespaces.WaitConditions.waitWith;

public interface DevtoolsElement {
    static DriverFunction<Boolean> waitPresent(LazyElement element, int interval, int timeout) {
        return waitWith(element, DevtoolsDriver::isElementPresent, CoreFormatterConstants.OPTION_EMPTY, interval, timeout, SeleniumFormatterConstants.PRESENT);
    }

    static DriverFunction<Boolean> waitDisplayed(LazyElement element, int interval, int timeout) {
        return waitWith(element, DevtoolsDriver::isElementDisplayed, CoreFormatterConstants.OPTION_EMPTY, interval, timeout, SeleniumFormatterConstants.DISPLAYED);
    }

    static DriverFunction<Boolean> waitEnabled(LazyElement element, int interval, int timeout) {
        return waitWith(element, DevtoolsDriver::isElementEnabled, CoreFormatterConstants.OPTION_EMPTY, interval, timeout, SeleniumFormatterConstants.ENABLED);
    }

    static DriverFunction<Boolean> waitClickable(LazyElement element, int interval, int timeout) {
        return waitWith(element, DevtoolsDriver::isElementClickable, CoreFormatterConstants.OPTION_EMPTY, interval, timeout, SeleniumFormatterConstants.CLICKABLE);
    }

    static DriverFunction<Boolean> waitAbsent(LazyElement element, int interval, int timeout) {
        return waitWith(element, DevtoolsDriver::isElementAbsent, CoreFormatterConstants.OPTION_EMPTY, interval, timeout, SeleniumFormatterConstants.ABSENT);
    }

    static DriverFunction<Boolean> waitHidden(LazyElement element, int interval, int timeout) {
        return waitWith(element, DevtoolsDriver::isElementHidden, CoreFormatterConstants.OPTION_NOT, interval, timeout, SeleniumFormatterConstants.HIDDEN);
    }

    static DriverFunction<Boolean> waitDisabled(LazyElement element, int interval, int timeout) {
        return waitWith(element, DevtoolsDriver::isElementDisabled, CoreFormatterConstants.OPTION_NOT, interval, timeout, SeleniumFormatterConstants.DISABLED);
    }

    static DriverFunction<Boolean> waitUnclickable(LazyElement element, int interval, int timeout) {
        return waitWith(element, DevtoolsDriver::isElementUnclickable, CoreFormatterConstants.OPTION_NOT, interval, timeout, SeleniumFormatterConstants.UNCLICKABLE);
    }

    static DriverFunction<Boolean> waitPresentDefaults(LazyElement element) {
        return waitPresent(element, 300, 3000);
    }

    static DriverFunction<Boolean> waitDisplayedDefaults(LazyElement element) {
        return waitDisplayed(element, 300, 3000);
    }

    static DriverFunction<Boolean> waitEnabledDefaults(LazyElement element) {
        return waitEnabled(element, 300, 3000);
    }

    static DriverFunction<Boolean> waitClickableDefaults(LazyElement element) {
        return waitClickable(element, 300, 3000);
    }

    static DriverFunction<Boolean> waitAbsentDefaults(LazyElement element) {
        return waitAbsent(element, 300, 3000);
    }

    static DriverFunction<Boolean> waitHiddenDefaults(LazyElement element) {
        return waitHidden(element, 300, 3000);
    }

    static DriverFunction<Boolean> waitDisabledDefaults(LazyElement element) {
        return waitDisabled(element, 300, 3000);
    }

    static DriverFunction<Boolean> waitUnclickableDefaults(LazyElement element) {
        return waitUnclickable(element, 300, 3000);
    }

    private static DriverFunction<Boolean> clickCore(LazyElement element) {
        return SeleniumExecutor.execute(
            DevtoolsDriver.isElementPresent(element),
            DevtoolsDriver.click(element)
        );
    }

    private static DriverFunction<Boolean> clearCore(LazyElement element) {
        return SeleniumExecutor.execute(
            DevtoolsDriver.isElementPresent(element),
            DevtoolsDriver.click(element),
            DevtoolsDriver.setValue(element, CoreFormatterConstants.EMPTY),
            DevtoolsDriver.click(DefaultElementConstants.BODY)
        );
    }

    private static DriverFunction<Boolean> setValueCore(LazyElement element, String input) {
        return SeleniumExecutor.execute(
            DevtoolsDriver.isElementPresent(element),
            DevtoolsDriver.click(element),
            DevtoolsDriver.setValue(element, input),
            DevtoolsDriver.click(DefaultElementConstants.BODY)
        );
    }

    static DriverFunction<Boolean> click(LazyElement element) {
        return ifDriver("click", FrameworkCoreFormatter.isNullLazyElementMessage(element), clickCore(element), CoreDataConstants.NULL_BOOLEAN);
    }

    static DriverFunction<Boolean> clear(LazyElement element) {
        return ifDriver("clear", FrameworkCoreFormatter.isNullLazyElementMessage(element), setValueCore(element, CoreFormatterConstants.EMPTY), CoreDataConstants.NULL_BOOLEAN);
    }

    static DriverFunction<Boolean> setValue(LazyElement element, String input) {
        return ifDriver("setValue", SeleniumFormatter.getSendKeysNotSendingMessage(element, input), setValueCore(element, input), CoreDataConstants.NULL_BOOLEAN);
    }

    private static DriverFunction<Boolean> actionWhenCore(LazyElement element, Function<LazyElement, DriverFunction<Boolean>> action, TriFunction<LazyElement, Integer, Integer, DriverFunction<Boolean>> condition, int interval, int timeout) {
        return SeleniumExecutor.execute(condition.apply(element, interval, timeout), action.apply(element));
    }

    private static DriverFunction<Boolean> actionWhenCore(LazyElement element, BiFunction<LazyElement, String, DriverFunction<Boolean>> action, String value, TriFunction<LazyElement, Integer, Integer, DriverFunction<Boolean>> condition, int interval, int timeout) {
        return SeleniumExecutor.execute(condition.apply(element, interval, timeout), action.apply(element, value));
    }

    private static DriverFunction<Boolean> clickWhenCore(LazyElement element, TriFunction<LazyElement, Integer, Integer, DriverFunction<Boolean>> condition, int interval, int timeout) {
        return actionWhenCore(element, DevtoolsElement::click, condition, interval, timeout);
    }

    private static DriverFunction<Boolean> clearWhenCore(LazyElement element, TriFunction<LazyElement, Integer, Integer, DriverFunction<Boolean>> condition, int interval, int timeout) {
        return actionWhenCore(element, DevtoolsElement::clear, condition, interval, timeout);
    }

    private static DriverFunction<Boolean> setValueWhenCore(LazyElement element, String value, TriFunction<LazyElement, Integer, Integer, DriverFunction<Boolean>> condition, int interval, int timeout) {
        return actionWhenCore(element, DevtoolsElement::setValue, value, condition, interval, timeout);
    }

    static DriverFunction<Boolean> clickWhenPresent(LazyElement element, int interval, int timeout) {
        return clickWhenCore(element, DevtoolsElement::waitPresent, interval, timeout);
    }

    static DriverFunction<Boolean> clickWhenDisplayed(LazyElement element, int interval, int timeout) {
        return clickWhenCore(element, DevtoolsElement::waitDisplayed, interval, timeout);
    }

    static DriverFunction<Boolean> clickWhenEnabled(LazyElement element, int interval, int timeout) {
        return clickWhenCore(element, DevtoolsElement::waitEnabled, interval, timeout);
    }

    static DriverFunction<Boolean> clickWhenClickable(LazyElement element, int interval, int timeout) {
        return clickWhenCore(element, DevtoolsElement::waitClickable, interval, timeout);
    }

    static DriverFunction<Boolean> clickWhenPresentDefaults(LazyElement element) {
        return clickWhenPresent(element, 300, 3000);
    }

    static DriverFunction<Boolean> clickWhenDisplayedDefaults(LazyElement element) {
        return clickWhenCore(element, DevtoolsElement::waitDisplayed, 300, 3000);
    }

    static DriverFunction<Boolean> clickWhenEnabledDefaults(LazyElement element) {
        return clickWhenCore(element, DevtoolsElement::waitEnabled, 300, 3000);
    }

    static DriverFunction<Boolean> clickWhenClickableDefaults(LazyElement element) {
        return clickWhenCore(element, DevtoolsElement::waitClickable, 300, 3000);
    }

    static DriverFunction<Boolean> clearWhenPresent(LazyElement element, int interval, int timeout) {
        return clearWhenCore(element, DevtoolsElement::waitPresent, interval, timeout);
    }

    static DriverFunction<Boolean> clearWhenDisplayed(LazyElement element, int interval, int timeout) {
        return clearWhenCore(element, DevtoolsElement::waitDisplayed, interval, timeout);
    }

    static DriverFunction<Boolean> clearWhenEnabled(LazyElement element, int interval, int timeout) {
        return clearWhenCore(element, DevtoolsElement::waitEnabled, interval, timeout);
    }

    static DriverFunction<Boolean> clearWhenClickable(LazyElement element, int interval, int timeout) {
        return clearWhenCore(element, DevtoolsElement::waitClickable, interval, timeout);
    }

    static DriverFunction<Boolean> clearWhenPresentDefaults(LazyElement element) {
        return clearWhenCore(element, DevtoolsElement::waitPresent, 300, 3000);
    }

    static DriverFunction<Boolean> clearWhenDisplayedDefaults(LazyElement element) {
        return clearWhenCore(element, DevtoolsElement::waitDisplayed, 300, 3000);
    }

    static DriverFunction<Boolean> clearWhenEnabledDefaults(LazyElement element) {
        return clearWhenCore(element, DevtoolsElement::waitEnabled, 300, 3000);
    }

    static DriverFunction<Boolean> clearWhenClickableDefaults(LazyElement element) {
        return clearWhenCore(element, DevtoolsElement::waitClickable, 300, 3000);
    }

    static DriverFunction<Boolean> setValueWhenPresent(LazyElement element, String value, int interval, int timeout) {
        return setValueWhenCore(element, value, DevtoolsElement::waitPresent, interval, timeout);
    }

    static DriverFunction<Boolean> setValueWhenDisplayed(LazyElement element, String value, int interval, int timeout) {
        return setValueWhenCore(element, value, DevtoolsElement::waitDisplayed, interval, timeout);
    }

    static DriverFunction<Boolean> setValueWhenEnabled(LazyElement element, String value, int interval, int timeout) {
        return setValueWhenCore(element, value, DevtoolsElement::waitEnabled, interval, timeout);
    }

    static DriverFunction<Boolean> setValueWhenClickable(LazyElement element, String value, int interval, int timeout) {
        return setValueWhenCore(element, value, DevtoolsElement::waitClickable, interval, timeout);
    }

    static DriverFunction<Boolean> setValueWhenPresentDefaults(LazyElement element, String value) {
        return setValueWhenCore(element, value, DevtoolsElement::waitPresent, 300, 3000);
    }

    static DriverFunction<Boolean> setValueWhenDisplayedDefaults(LazyElement element, String value) {
        return setValueWhenCore(element, value, DevtoolsElement::waitDisplayed, 300, 3000);
    }

    static DriverFunction<Boolean> setValueWhenEnabledDefaults(LazyElement element, String value) {
        return setValueWhenCore(element, value, DevtoolsElement::waitEnabled, 300, 3000);
    }

    static DriverFunction<Boolean> setValueWhenClickableDefaults(LazyElement element, String value) {
        return setValueWhenCore(element, value, DevtoolsElement::waitClickable, 300, 3000);
    }
}
