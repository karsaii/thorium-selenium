package com.github.karsaii.framework.selenium.namespaces;

import com.github.karsaii.core.constants.CoreConstants;
import com.github.karsaii.core.constants.validators.CoreFormatterConstants;
import com.github.karsaii.core.exceptions.MethodInvokeException;
import com.github.karsaii.core.namespaces.DataFactoryFunctions;
import com.github.karsaii.core.namespaces.validators.HandlerResultDataValidator;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.core.records.HandleResultData;
import com.github.karsaii.framework.selenium.constants.SeleniumExceptionHandlersConstants;
import com.github.karsaii.framework.selenium.constants.validators.SeleniumFormatterConstants;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;

import static com.github.karsaii.core.extensions.namespaces.CoreUtilities.isNonException;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public interface SeleniumExceptionHandlers {
    static <CastParameterType, ReturnType> Data<ReturnType> invokeHandler(HandleResultData<CastParameterType, ReturnType> data) {
        final var nameof = SeleniumExceptionHandlersConstants.INVOKE_HANDLER;
        final var defaultValue = data.defaultValue;
        final var errorMessage = HandlerResultDataValidator.isInvalidHandlerResultDataMessage(data);
        if (isNotBlank(errorMessage)) {
            return DataFactoryFunctions.getInvalidWithNameAndMessage(defaultValue, nameof, errorMessage);
        }

        var exception = CoreConstants.EXCEPTION;
        var result = defaultValue;
        try {
            result = data.caster.apply(data.parameter);
        } catch (
            IllegalArgumentException |
            NoSuchElementException |
            StaleElementReferenceException |
            MethodInvokeException ex
        ) {
            exception = ex;
        }

        final var status = isNonException(exception);
        final var message = status ? CoreFormatterConstants.INVOCATION_SUCCESSFUL : CoreFormatterConstants.INVOCATION_EXCEPTION;
        return DataFactoryFunctions.getWithNameAndMessage(result, status, nameof, message, exception);
    }

    static <CastParameterType, ReturnType> Data<ReturnType> findElementsHandler(HandleResultData<CastParameterType, ReturnType> data) {
        final var nameof = SeleniumExceptionHandlersConstants.FIND_ELEMENTS_HANDLER;
        final var defaultValue = data.defaultValue;
        final var errorMessage = HandlerResultDataValidator.isInvalidHandlerResultDataMessage(data);
        if (isNotBlank(errorMessage)) {
            return DataFactoryFunctions.getInvalidWithNameAndMessage(defaultValue, nameof, errorMessage);
        }

        var exception = CoreConstants.EXCEPTION;
        var result = defaultValue;
        try {
            result = data.caster.apply(data.parameter);
        } catch (NoSuchElementException | StaleElementReferenceException ex) {
            exception = ex;
        }

        final var status = isNonException(exception);
        final var message = status ? SeleniumFormatterConstants.FIND_ELEMENTS_SUCCESSFUL : SeleniumFormatterConstants.FIND_ELEMENTS_EXCEPTION;
        return DataFactoryFunctions.getWithNameAndMessage(result, status, nameof, message, exception);
    }

    static <CastParameterType, ReturnType> Data<ReturnType> quitHandler(HandleResultData<CastParameterType, ReturnType> data) {
        final var nameof = SeleniumExceptionHandlersConstants.QUIT_HANDLER;
        final var defaultValue = data.defaultValue;
        final var errorMessage = HandlerResultDataValidator.isInvalidHandlerResultDataMessage(data);
        if (isNotBlank(errorMessage)) {
            return DataFactoryFunctions.getInvalidWithNameAndMessage(defaultValue, nameof, errorMessage);
        }

        var exception = CoreConstants.EXCEPTION;
        var result = defaultValue;
        try {
            result = data.caster.apply(data.parameter);
        } catch (NoSuchElementException | StaleElementReferenceException ex) {
            exception = ex;
        }

        final var status = isNonException(exception);
        final var message = status ? SeleniumFormatterConstants.QUIT_DRIVER_SUCCESSFUL : SeleniumFormatterConstants.QUIT_DRIVER_EXCEPTION;
        return DataFactoryFunctions.getWithNameAndMessage(result, status, nameof, message, exception);
    }
}
