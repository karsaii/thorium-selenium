package com.github.karsaii.framework.selenium.namespaces.validators;

import com.github.karsaii.framework.core.namespaces.validators.LazyLocatorValidators;
import com.github.karsaii.framework.core.records.lazy.LazyLocator;

import static com.github.karsaii.core.namespaces.validators.CoreFormatter.getNamedErrorMessageOrEmpty;
import static org.apache.commons.lang3.StringUtils.isBlank;

public interface SeleniumLazyLocatorValidators {
    static String isInvalidLazyLocator(LazyLocator data) {
        var message = LazyLocatorValidators.isInvalidLazyLocatorCommon(data);
        if (isBlank(message)) {
            message += (
                SeleniumStrategyValidators.isValidStrategy(data.STRATEGY)
            );
        }

        return getNamedErrorMessageOrEmpty("isInvalidLazyLocator", message);
    }
}
