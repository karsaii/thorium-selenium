package com.neathorium.framework.selenium.namespaces.validators;

import com.neathorium.framework.selenium.namespaces.extensions.boilers.ScriptHandlerFunction;
import com.neathorium.framework.selenium.records.scripter.ExecutorData;
import com.neathorium.framework.selenium.records.scripter.ExecutorParametersFieldData;
import com.neathorium.framework.selenium.records.scripter.ExecutorResultFunctionsData;
import com.neathorium.framework.selenium.records.scripter.ScriptParametersData;
import com.neathorium.core.abstracts.reflection.BaseInvokerDefaultsData;
import com.neathorium.core.constants.CastDataConstants;
import com.neathorium.core.extensions.namespaces.CoreUtilities;
import com.neathorium.core.extensions.namespaces.NullableFunctions;
import com.neathorium.core.namespaces.validators.CoreFormatter;
import com.neathorium.core.records.caster.BasicCastData;
import com.neathorium.core.records.caster.WrappedCastData;
import com.neathorium.core.records.reflection.InvokerParameterizedParametersFieldData;

import java.util.Objects;

import static com.neathorium.core.namespaces.validators.CoreFormatter.getNamedErrorMessageOrEmpty;
import static org.apache.commons.lang3.StringUtils.isBlank;

public interface ScriptExecutions {
    static boolean isValidExecutorParametersData(ExecutorParametersFieldData data) {
        return NullableFunctions.isNotNull(data) && CoreUtilities.areNotNull(data.handler, data.parameters, data.validator);
    }

    static <T> boolean isValidInvokerParameterizedData(InvokerParameterizedParametersFieldData<T> data) {
        return NullableFunctions.isNotNull(data) && CoreUtilities.areNotNull(data.handler, data.parameters, data.validator);
    }

    static <T> boolean isValidExecutorRegularData(ScriptHandlerFunction handler) {
        return NullableFunctions.isNotNull(handler);
    }

    static <T> boolean isValidScriptParametersData(ScriptParametersData<T> data) {
        return NullableFunctions.isNotNull(data) && CoreUtilities.areNotNull(data.converter, data.parameters, data.validator);
    }

    static <T> boolean isValidCastData(WrappedCastData<T> data) {
        return NullableFunctions.isNotNull(data) && CoreUtilities.areNotNull(data.caster, data.defaultValue);
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
        return NullableFunctions.isNotNull(data) && CoreUtilities.areNotNull(data.castHandler, data.messageHandler);
    }

    static <T, U, V, W> boolean isValidConstructorData(ExecutorData<T, U, V, W> data) {
        final var dataNotNull = NullableFunctions.isNotNull(data);
        if (!dataNotNull) {
            return false;
        }

        return (
            CoreUtilities.areNotNull(data.constructor, data.getter, data.guard) &&
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
