package com.github.karsaii.framework.selenium.namespaces.validators;

import com.github.karsaii.core.namespaces.validators.CoreFormatter;
import com.github.karsaii.framework.selenium.enums.SeleniumSelectorStrategy;

import static com.github.karsaii.core.namespaces.validators.CoreFormatter.getNamedErrorMessageOrEmpty;
import static org.apache.commons.lang3.StringUtils.isBlank;

public interface SeleniumStrategyValidators {
    static String isValidStrategy(String value) {
        final var nameof = "isValidStrategy: ";
        var message = CoreFormatter.isBlankMessageWithName(value, "Strategy value");
        if (isBlank(message)) {
            final var status = !SeleniumSelectorStrategy.contains(value);
            if (status) {
                message += "Selenium selector strategies didn't contain \"" + value + "\"";
            }
        }

        return getNamedErrorMessageOrEmpty(nameof, message);
    }
}
