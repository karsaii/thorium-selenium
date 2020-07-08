package com.github.karsaii.framework.selenium.namespaces.element.validators;

import com.github.karsaii.core.records.Data;
import com.github.karsaii.core.namespaces.validators.CoreFormatter;
import com.github.karsaii.framework.selenium.records.lazy.CachedLazyElementData;

import java.util.Map;

import static com.github.karsaii.core.namespaces.validators.CoreFormatter.getNamedErrorMessageOrEmpty;

public interface ElementRepositoryValidators {
    static <T> String isInvalidContainsElementMessage(Map<String, CachedLazyElementData> elementRepository, String name, Data<?> defaultValue) {
        return getNamedErrorMessageOrEmpty(
            "isInvalidContainsElementMessage: ",
            (
                CoreFormatter.isNullMessageWithName(elementRepository, "Element Repository") +
                CoreFormatter.isBlankMessageWithName(name, "Name") +
                CoreFormatter.isNullMessageWithName(defaultValue, "Default Data Value")
            )
        );
    }
}
