package com.neathorium.framework.selenium.namespaces.validators;

import com.neathorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.core.abstracts.reflection.BaseInvokerDefaultsData;
import com.neathorium.core.namespaces.validators.CoreFormatter;
import com.neathorium.core.records.Data;
import com.neathorium.core.records.MethodData;
import com.neathorium.core.records.reflection.message.InvokeCommonMessageParametersData;

import java.util.function.Function;

public interface InvokeCoreValidator {
    private static <ParameterType, HandlerType, ReturnType> String isInvalidInvokeCoreParametersCommonMessage(
        Data<MethodData> data,
        BaseInvokerDefaultsData<ParameterType, HandlerType, ReturnType> defaults,
        Function<InvokeCommonMessageParametersData, Function<Exception, String>> messageHandler,
        HandlerType handler
    ) {
        return (
            CoreFormatter.isInvalidOrFalseMessage(data) +
            CoreFormatter.isNullMessageWithName(handler, "Handler") +
            CoreFormatter.isNullMessageWithName(messageHandler, "Message Handler") +
            ScriptExecutions.isInvalidInvokerDefaultsMessage(defaults) +
            CoreFormatter.isFalseMessageWithName(defaults.guard.test(handler), "Guard tested handler")
        );
    }
    static <ParameterType, HandlerType, ReturnType> String isInvalidInvokeCoreParametersMessage(
        Data<MethodData> data,
        BaseInvokerDefaultsData<ParameterType, HandlerType, ReturnType> defaults,
        Function<InvokeCommonMessageParametersData, Function<Exception, String>> messageHandler,
        HandlerType handler,
        DriverFunction<ParameterType> getter
    ) {
        return isInvalidInvokeCoreParametersCommonMessage(data, defaults, messageHandler, handler) + CoreFormatter.isNullMessageWithName(getter, "Parameter Getter");
    }

    static <ParameterType, HandlerType, ReturnType> String isInvalidInvokeCoreParametersMessage(
        Data<MethodData> data,
        BaseInvokerDefaultsData<ParameterType, HandlerType, ReturnType> defaults,
        Function<InvokeCommonMessageParametersData, Function<Exception, String>> messageHandler,
        HandlerType handler,
        ParameterType parameter
    ) {
        return isInvalidInvokeCoreParametersCommonMessage(data, defaults, messageHandler, handler) + CoreFormatter.isNullMessageWithName(parameter, "Parameter");
    }
}
