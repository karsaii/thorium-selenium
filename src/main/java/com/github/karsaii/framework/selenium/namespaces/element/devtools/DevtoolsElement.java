package com.github.karsaii.framework.selenium.namespaces.element.devtools;

import com.github.karsaii.core.constants.validators.CoreFormatterConstants;
import com.github.karsaii.framework.selenium.constants.validators.SeleniumFormatterConstants;
import com.github.karsaii.framework.selenium.namespaces.driver.DevtoolsDriver;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.github.karsaii.framework.selenium.records.lazy.LazyElement;

import static com.github.karsaii.framework.selenium.namespaces.WaitConditions.waitWith;

public interface DevtoolsElement {
    static DriverFunction<Boolean> waitPresent(LazyElement data, int interval, int timeout) {
        return waitWith(data, DevtoolsDriver::isElementPresent, CoreFormatterConstants.OPTION_EMPTY, interval, timeout, SeleniumFormatterConstants.PRESENT);
    }

    static DriverFunction<Boolean> waitDisplayed(LazyElement data, int interval, int timeout) {
        return waitWith(data, DevtoolsDriver::isElementDisplayed, CoreFormatterConstants.OPTION_EMPTY, interval, timeout, SeleniumFormatterConstants.DISPLAYED);
    }

    static DriverFunction<Boolean> waitEnabled(LazyElement data, int interval, int timeout) {
        return waitWith(data, DevtoolsDriver::isElementEnabled, CoreFormatterConstants.OPTION_EMPTY, interval, timeout, SeleniumFormatterConstants.ENABLED);
    }

    static DriverFunction<Boolean> waitClickable(LazyElement data, int interval, int timeout) {
        return waitWith(data, DevtoolsDriver::isElementClickable, CoreFormatterConstants.OPTION_EMPTY, interval, timeout, SeleniumFormatterConstants.CLICKABLE);
    }

    static DriverFunction<Boolean> waitAbsent(LazyElement data, int interval, int timeout) {
        return waitWith(data, DevtoolsDriver::isElementAbsent, CoreFormatterConstants.OPTION_EMPTY, interval, timeout, SeleniumFormatterConstants.ABSENT);
    }

    static DriverFunction<Boolean> waitHidden(LazyElement data, int interval, int timeout) {
        return waitWith(data, DevtoolsDriver::isElementHidden, CoreFormatterConstants.OPTION_NOT, interval, timeout, SeleniumFormatterConstants.HIDDEN);
    }

    static DriverFunction<Boolean> waitDisabled(LazyElement data, int interval, int timeout) {
        return waitWith(data, DevtoolsDriver::isElementDisabled, CoreFormatterConstants.OPTION_NOT, interval, timeout, SeleniumFormatterConstants.DISABLED);
    }

    static DriverFunction<Boolean> waitUnclickable(LazyElement data, int interval, int timeout) {
        return waitWith(data, DevtoolsDriver::isElementUnclickable, CoreFormatterConstants.OPTION_NOT, interval, timeout, SeleniumFormatterConstants.UNCLICKABLE);
    }
}
