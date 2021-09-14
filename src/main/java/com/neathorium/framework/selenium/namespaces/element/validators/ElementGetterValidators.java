package com.neathorium.framework.selenium.namespaces.element.validators;

import com.neathorium.core.extensions.DecoratedList;
import com.neathorium.core.namespaces.validators.CoreFormatter;
import com.neathorium.core.records.Data;

import java.util.function.Function;

import static com.neathorium.core.namespaces.validators.CoreFormatter.getNamedErrorMessageOrEmpty;

public interface ElementGetterValidators {
    private static <T, U extends DecoratedList<T>> String isInvalidElementList(Data<U> data) {
        return getNamedErrorMessageOrEmpty(
            "isInvalidElementList",
            CoreFormatter.getValidNonFalseAndValidContainedMessage(data, CoreFormatter::isNullOrEmptyListMessage)
        );
    }

    private static <T, U extends DecoratedList<T>> Function<U, String> getContainsIndexMessage(int index) {
        return list -> CoreFormatter.getContainsIndexMessage(list, index);
    }

    static <T, U extends DecoratedList<T>> String isInvalidElementByTextParameters(Data<U> data, String text) {
        return getNamedErrorMessageOrEmpty(
            "isInvalidElementByTextParameters",
            (
                CoreFormatter.getValidNonFalseAndValidContainedMessage(data, CoreFormatter::isNullOrEmptyListMessage) +
                CoreFormatter.isBlankMessageWithName(text, "Text")
            )
        );
    }

    static <T, U extends DecoratedList<T>> String isInvalidElementByIndexParameters(Data<U> data, int index) {
        return getNamedErrorMessageOrEmpty(
            "isInvalidElementByIndexParameters",
            (
                CoreFormatter.getValidNonFalseAndValidContainedMessage(data, getContainsIndexMessage(index)) +
                CoreFormatter.isMoreThanExpectedMessage(index, -1, "Index")
            )
        );
    }
}
