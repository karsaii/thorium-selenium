package com.github.karsaii.framework.selenium.namespaces.validators;

import com.github.karsaii.framework.selenium.records.GetCachedElementData;

import static com.github.karsaii.core.namespaces.validators.CoreFormatter.getNamedErrorMessageOrEmpty;
import static com.github.karsaii.core.namespaces.validators.CoreFormatter.isBlankMessageWithName;
import static com.github.karsaii.core.namespaces.validators.CoreFormatter.isNullMessageWithName;
import static org.apache.commons.lang3.StringUtils.isBlank;

public interface GetCachedElementDataValidators {
    static String getValidCachedElementDataMessage(GetCachedElementData data) {
        var message = isNullMessageWithName(data, "Get Cached Element Data");
        if (isBlank(message)) {
            message += (
                isBlankMessageWithName(data.nameof, "Function name") +
                isNullMessageWithName(data.validator, "Validator function") +
                isNullMessageWithName(data.getter, "Getter function") +
                isNullMessageWithName(data.formatter, "Formatter function") +
                isNullMessageWithName(data.defaultValue, "Default value") +
                isNullMessageWithName(data.repository, "Element Repository")
            );
        }

        return getNamedErrorMessageOrEmpty("getValidCachedElementDataMessage", message);
    }
}
