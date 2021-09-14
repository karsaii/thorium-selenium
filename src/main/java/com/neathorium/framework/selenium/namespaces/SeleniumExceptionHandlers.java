package com.neathorium.framework.selenium.namespaces;

import com.neathorium.framework.selenium.constants.SeleniumExceptionHandlersConstants;
import com.neathorium.framework.selenium.constants.validators.SeleniumFormatterConstants;
import com.neathorium.core.constants.CoreConstants;
import com.neathorium.core.constants.validators.CoreFormatterConstants;
import com.neathorium.core.exceptions.MethodInvokeException;
import com.neathorium.core.extensions.namespaces.CoreUtilities;
import com.neathorium.core.namespaces.DataFactoryFunctions;
import com.neathorium.core.namespaces.validators.HandlerResultDataValidator;
import com.neathorium.core.records.Data;
import com.neathorium.core.records.HandleResultData;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public interface SeleniumExceptionHandlers {
    static <CastParameterType, ReturnType> Data<ReturnType> invokeHandler(HandleResultData<CastParameterType, ReturnType> data) {
        final var nameof = SeleniumExceptionHandlersConstants.INVOKE_HANDLER;
        final var defaultValue = data.defaultValue;
        final var errorMessage = HandlerResultDataValidator.isInvalidHandlerResultDataMessage(data);
        if (isNotBlank(errorMessage)) {
            return DataFactoryFunctions.getInvalidWith(defaultValue, nameof, errorMessage);
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

        final var status = CoreUtilities.isNonException(exception);
        final var message = status ? CoreFormatterConstants.INVOCATION_SUCCESSFUL : CoreFormatterConstants.INVOCATION_EXCEPTION;
        return DataFactoryFunctions.getWith(result, status, nameof, message, exception);
    }

    static <CastParameterType, ReturnType> Data<ReturnType> findElementsHandler(HandleResultData<CastParameterType, ReturnType> data) {
        final var nameof = SeleniumExceptionHandlersConstants.FIND_ELEMENTS_HANDLER;
        final var defaultValue = data.defaultValue;
        final var errorMessage = HandlerResultDataValidator.isInvalidHandlerResultDataMessage(data);
        if (isNotBlank(errorMessage)) {
            return DataFactoryFunctions.getInvalidWith(defaultValue, nameof, errorMessage);
        }

        var exception = CoreConstants.EXCEPTION;
        var result = defaultValue;
        try {
            result = data.caster.apply(data.parameter);
        } catch (NoSuchElementException | StaleElementReferenceException ex) {
            exception = ex;
        }

        final var status = CoreUtilities.isNonException(exception);
        final var message = status ? SeleniumFormatterConstants.FIND_ELEMENTS_SUCCESSFUL : SeleniumFormatterConstants.FIND_ELEMENTS_EXCEPTION;
        return DataFactoryFunctions.getWith(result, status, nameof, message, exception);
    }

    static <CastParameterType, ReturnType> Data<ReturnType> quitHandler(HandleResultData<CastParameterType, ReturnType> data) {
        final var nameof = SeleniumExceptionHandlersConstants.QUIT_HANDLER;
        final var defaultValue = data.defaultValue;
        final var errorMessage = HandlerResultDataValidator.isInvalidHandlerResultDataMessage(data);
        if (isNotBlank(errorMessage)) {
            return DataFactoryFunctions.getInvalidWith(defaultValue, nameof, errorMessage);
        }

        var exception = CoreConstants.EXCEPTION;
        var result = defaultValue;
        try {
            result = data.caster.apply(data.parameter);
        } catch (NoSuchElementException | StaleElementReferenceException ex) {
            exception = ex;
        }

        final var status = CoreUtilities.isNonException(exception);
        final var message = status ? SeleniumFormatterConstants.QUIT_DRIVER_SUCCESSFUL : SeleniumFormatterConstants.QUIT_DRIVER_EXCEPTION;
        return DataFactoryFunctions.getWith(result, status, nameof, message, exception);
    }
}
