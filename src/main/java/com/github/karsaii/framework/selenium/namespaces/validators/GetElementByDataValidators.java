package com.github.karsaii.framework.selenium.namespaces.validators;

import com.github.karsaii.framework.selenium.records.GetElementByData;

import static com.github.karsaii.core.namespaces.validators.CoreFormatter.getNamedErrorMessageOrEmpty;
import static com.github.karsaii.core.namespaces.validators.CoreFormatter.isBlankMessageWithName;
import static com.github.karsaii.core.namespaces.validators.CoreFormatter.isNullMessageWithName;
import static org.apache.commons.lang3.StringUtils.isBlank;

public interface GetElementByDataValidators {
    static String getValidGetElementByDataMessage(GetElementByData<?, ?, ?> data) {
        var message = isNullMessageWithName(data, "Get Element By Data");
        if (isBlank(message)) {
            message += (
                isBlankMessageWithName(data.nameof, "Name of the function") +
                isNullMessageWithName(data.validator, "Validator function") +
                isNullMessageWithName(data.getter, "Getter function") +
                isNullMessageWithName(data.formatter, "Formatter function") +
                isNullMessageWithName(data.defaultValue, "Default data value") +
                isBlankMessageWithName(data.filterName, "Filter type name")
            );
        }

        return getNamedErrorMessageOrEmpty("getValidGetElementByDataMessage", message);
    }
}
