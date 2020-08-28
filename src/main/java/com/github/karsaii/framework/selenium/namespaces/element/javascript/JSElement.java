package com.github.karsaii.framework.selenium.namespaces.element.javascript;

import com.github.karsaii.core.constants.validators.CoreFormatterConstants;
import com.github.karsaii.framework.selenium.constants.validators.SeleniumFormatterConstants;
import com.github.karsaii.framework.selenium.namespaces.Driver;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.github.karsaii.framework.selenium.records.lazy.LazyElement;

import static com.github.karsaii.framework.selenium.namespaces.WaitConditions.waitWith;

public interface JSElement {
    static DriverFunction<Boolean> waitPresent(LazyElement data, int interval, int timeout) {
        return waitWith(data, Driver::isElementPresent, CoreFormatterConstants.OPTION_EMPTY, interval, timeout, SeleniumFormatterConstants.PRESENT);
    }

    static DriverFunction<Boolean> waitDisplayed(LazyElement data, int interval, int timeout) {
        return waitWith(data, Driver::isElementDisplayed, CoreFormatterConstants.OPTION_EMPTY, interval, timeout, SeleniumFormatterConstants.DISPLAYED);
    }

    static DriverFunction<Boolean> waitEnabled(LazyElement data, int interval, int timeout) {
        return waitWith(data, Driver::isElementEnabled, CoreFormatterConstants.OPTION_EMPTY, interval, timeout, SeleniumFormatterConstants.ENABLED);
    }

    static DriverFunction<Boolean> waitSelected(LazyElement data, int interval, int timeout) {
        return waitWith(data, Driver::isElementSelected, CoreFormatterConstants.OPTION_EMPTY, interval, timeout, SeleniumFormatterConstants.SELECTED);
    }

    static DriverFunction<Boolean> waitClickable(LazyElement data, int interval, int timeout) {
        return waitWith(data, Driver::isElementClickable, CoreFormatterConstants.OPTION_EMPTY, interval, timeout, SeleniumFormatterConstants.CLICKABLE);
    }

    static DriverFunction<Boolean> waitAbsent(LazyElement data, int interval, int timeout) {
        return waitWith(data, Driver::isElementAbsent, CoreFormatterConstants.OPTION_EMPTY, interval, timeout, SeleniumFormatterConstants.ABSENT);
    }

    static DriverFunction<Boolean> waitHidden(LazyElement data, int interval, int timeout) {
        return waitWith(data, Driver::isElementHidden, CoreFormatterConstants.OPTION_NOT, interval, timeout, SeleniumFormatterConstants.HIDDEN);
    }

    static DriverFunction<Boolean> waitDisabled(LazyElement data, int interval, int timeout) {
        return waitWith(data, Driver::isElementDisabled, CoreFormatterConstants.OPTION_NOT, interval, timeout, SeleniumFormatterConstants.DISABLED);
    }

    static DriverFunction<Boolean> waitUnselected(LazyElement data, int interval, int timeout) {
        return waitWith(data, Driver::isElementUnselected, CoreFormatterConstants.OPTION_NOT, interval, timeout, SeleniumFormatterConstants.UNSELECTED);
    }

    static DriverFunction<Boolean> waitUnclickable(LazyElement data, int interval, int timeout) {
        return waitWith(data, Driver::isElementUnclickable, CoreFormatterConstants.OPTION_NOT, interval, timeout, SeleniumFormatterConstants.UNCLICKABLE);
    }
}
