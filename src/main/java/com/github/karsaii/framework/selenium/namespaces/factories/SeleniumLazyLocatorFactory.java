package com.github.karsaii.framework.selenium.namespaces.factories;

import com.github.karsaii.core.constants.validators.CoreFormatterConstants;
import com.github.karsaii.framework.core.namespaces.factory.LazyLocatorFactory;
import com.github.karsaii.framework.core.records.lazy.LazyLocator;
import com.github.karsaii.framework.selenium.constants.SeleniumLazyLocatorConstants;
import org.openqa.selenium.By;

import static com.github.karsaii.core.extensions.namespaces.NullableFunctions.isNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

public interface SeleniumLazyLocatorFactory {
    private static String getLocator(String locator) {
        if (isBlank(locator) || locator.length() < SeleniumLazyLocatorConstants.STRATEGY_MINIMUM_LENGTH) {
            return CoreFormatterConstants.EMPTY;
        }

        final var locatorStartIndex = locator.indexOf(CoreFormatterConstants.COLON) + SeleniumLazyLocatorConstants.LOCATOR_START_INDEX_OFFSET;
        return locator.substring(locatorStartIndex);
    }

    private static String getStrategy(String locator) {
        if (isBlank(locator) || locator.length() < SeleniumLazyLocatorConstants.STRATEGY_MINIMUM_LENGTH) {
            return CoreFormatterConstants.EMPTY;
        }

        final var colonIndex = locator.indexOf(CoreFormatterConstants.COLON);
        return locator.substring(3, colonIndex);
    }

    static LazyLocator getWithDefaults() {
        return LazyLocatorFactory.get(CoreFormatterConstants.EMPTY, SeleniumLazyLocatorConstants.DEFAULT_STRATEGY);
    }

    static LazyLocator getWithDefaultStrategy(String locator) {
        return LazyLocatorFactory.get(locator, SeleniumLazyLocatorConstants.DEFAULT_STRATEGY);
    }

    static LazyLocator get(String locator, String strategy) {
        return LazyLocatorFactory.get(locator, strategy);
    }

    static LazyLocator get(By locator) {
        if (isNull(locator)) {
            return getWithDefaults();
        }

        final var locatorString = locator.toString();
        return SeleniumLazyLocatorFactory.get(getLocator(locatorString), getStrategy(locatorString));
    }
}
