package com.github.karsaii.framework.selenium.namespaces.validators;

import com.github.karsaii.core.abstracts.reflection.BaseInvokerDefaultsData;
import com.github.karsaii.core.constants.CastDataConstants;
import com.github.karsaii.core.namespaces.validators.CoreFormatter;
import com.github.karsaii.core.records.caster.BasicCastData;
import com.github.karsaii.core.records.caster.WrappedCastData;
import com.github.karsaii.core.records.reflection.InvokerParameterizedParametersFieldData;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.ScriptHandlerFunction;
import com.github.karsaii.framework.selenium.records.scripter.ExecutorData;
import com.github.karsaii.framework.selenium.records.scripter.ExecutorParametersFieldData;
import com.github.karsaii.framework.selenium.records.scripter.ExecutorResultFunctionsData;
import com.github.karsaii.framework.selenium.records.scripter.ScriptParametersData;

import java.util.Objects;

import static com.github.karsaii.core.extensions.namespaces.CoreUtilities.areNotNull;
import static com.github.karsaii.core.extensions.namespaces.NullableFunctions.isNotNull;
import static com.github.karsaii.core.namespaces.validators.CoreFormatter.getNamedErrorMessageOrEmpty;
import static org.apache.commons.lang3.StringUtils.isBlank;

public interface ScriptExecutions {
    static boolean isValidExecutorParametersData(ExecutorParametersFieldData data) {
        return isNotNull(data) && areNotNull(data.handler, data.parameters, data.validator);
    }

    static <T> boolean isValidInvokerParameterizedData(InvokerParameterizedParametersFieldData<T> data) {
        return isNotNull(data) && areNotNull(data.handler, data.parameters, data.validator);
    }

    static <T> boolean isValidExecutorRegularData(ScriptHandlerFunction handler) {
        return isNotNull(handler);
    }

    static <T> boolean isValidScriptParametersData(ScriptParametersData<T> data) {
        return isNotNull(data) && areNotNull(data.converter, data.parameters, data.validator);
    }

    static <T> boolean isValidCastData(WrappedCastData<T> data) {
        return isNotNull(data) && areNotNull(data.caster, data.defaultValue);
    }

    static <T> String isInvalidCastDataMessage(BasicCastData<T> data) {
        final var baseName = "Basic Cast Data";
        var message = CoreFormatter.isNullMessageWithName(data, baseName);
        if (isBlank(message)) {
            message += (
                CoreFormatter.isNullMessageWithName(data.caster, baseName + "Caster") +
                CoreFormatter.isNullMessageWithName(data.defaultValue, baseName + "Default value")
            );
        }

        return getNamedErrorMessageOrEmpty("isInvalidCastDataMessage: ", message);
    }

    static <T> String isInvalidVoidCastDataMessage(BasicCastData<T> data) {
        final var baseName = "Basic Cast Data(Void)";
        var message = CoreFormatter.isNullMessageWithName(data, baseName);
        if (isBlank(message)) {
            message += CoreFormatter.isNullMessageWithName(data.caster, baseName + "Caster");
        }

        return getNamedErrorMessageOrEmpty("isInvalidCastDataMessage: ", message);
    }

    static <T, U, V> boolean isValidExecutorResultFunctionsData(ExecutorResultFunctionsData<T, U, V> data) {
        return isNotNull(data) && areNotNull(data.castHandler, data.messageHandler);
    }

    static <T, U, V, W> boolean isValidConstructorData(ExecutorData<T, U, V, W> data) {
        final var dataNotNull = isNotNull(data);
        if (!dataNotNull) {
            return false;
        }

        return (
            areNotNull(data.constructor, data.getter, data.guard) &&
            isValidCastData(data.castData) &&
            isValidExecutorResultFunctionsData(data.resultHandlers)
        );
    }


    static <T, U, V> String isInvalidInvokerDefaultsMessage(BaseInvokerDefaultsData<T, U, V> data) {
        final var baseName = "Invoker Defaults Data";
        var message = CoreFormatter.isNullMessageWithName(data, baseName);
        if (isBlank(message)) {
            final var castMessage = Objects.equals(CastDataConstants.VOID, data.castData) ? isInvalidVoidCastDataMessage(data.castData) : isInvalidCastDataMessage(data.castData);
            message += (
                CoreFormatter.isNullMessageWithName(data.constructor, baseName + " Constructor") +
                castMessage +
                CoreFormatter.isNullMessageWithName(data.guard, baseName + " Guard")
            );
        }

        return getNamedErrorMessageOrEmpty("isInvalidInvokerDefaultsMessage: ", message);
    }



}
