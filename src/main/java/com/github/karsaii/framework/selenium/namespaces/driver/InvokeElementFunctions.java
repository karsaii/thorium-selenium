package com.github.karsaii.framework.selenium.namespaces.driver;

import com.github.karsaii.core.abstracts.reflection.BaseInvokerDefaultsData;
import com.github.karsaii.core.constants.CoreConstants;
import com.github.karsaii.core.constants.CoreDataConstants;
import com.github.karsaii.core.constants.validators.CoreFormatterConstants;
import com.github.karsaii.core.extensions.namespaces.CoreUtilities;
import com.github.karsaii.core.extensions.namespaces.NullableFunctions;
import com.github.karsaii.core.implementations.reflection.message.ParameterizedMessageData;
import com.github.karsaii.core.implementations.reflection.message.RegularMessageData;
import com.github.karsaii.core.namespaces.DataExecutionFunctions;
import com.github.karsaii.core.namespaces.DataFactoryFunctions;
import com.github.karsaii.core.namespaces.InvokeFunctions;
import com.github.karsaii.core.namespaces.repositories.MethodRepository;
import com.github.karsaii.core.namespaces.validators.CoreFormatter;
import com.github.karsaii.core.namespaces.validators.MethodParametersDataValidators;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.core.records.HandleResultData;
import com.github.karsaii.core.records.MethodData;
import com.github.karsaii.core.records.MethodParametersData;
import com.github.karsaii.core.records.reflection.InvokerParameterizedParametersFieldData;
import com.github.karsaii.core.records.reflection.message.InvokeCommonMessageParametersData;
import com.github.karsaii.framework.core.namespaces.validators.FrameworkCoreFormatter;
import com.github.karsaii.framework.selenium.constants.MethodDefaults;
import com.github.karsaii.framework.selenium.constants.SeleniumCoreConstants;
import com.github.karsaii.framework.selenium.constants.SeleniumDataConstants;
import com.github.karsaii.framework.selenium.constants.SeleniumInvokeConstants;
import com.github.karsaii.framework.selenium.constants.SeleniumInvokeFunctionDefaults;
import com.github.karsaii.framework.selenium.constants.SeleniumMethodDefaults;
import com.github.karsaii.framework.selenium.namespaces.SeleniumExecutor;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.github.karsaii.framework.selenium.namespaces.factories.DriverFunctionFactory;
import com.github.karsaii.framework.selenium.namespaces.validators.InvokeCoreValidator;
import com.github.karsaii.framework.selenium.records.lazy.LazyElement;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.function.Function;

import static com.github.karsaii.core.extensions.namespaces.NullableFunctions.isNull;
import static com.github.karsaii.core.namespaces.DataExecutionFunctions.ifDependency;
import static com.github.karsaii.core.namespaces.DataFactoryFunctions.replaceName;
import static com.github.karsaii.core.namespaces.predicates.DataPredicates.isInvalidOrFalse;
import static com.github.karsaii.core.namespaces.predicates.DataPredicates.isValidNonFalse;
import static com.github.karsaii.core.namespaces.validators.CoreFormatter.isBlankMessageWithName;
import static com.github.karsaii.framework.selenium.namespaces.ExecutionCore.ifDriver;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public interface InvokeElementFunctions {
    private static <HandlerType, ParameterType, ReturnType> Data<ReturnType> invokeCore(
        Data<MethodData> data,
        BaseInvokerDefaultsData<ParameterType, HandlerType, ReturnType> defaults,
        Function<InvokeCommonMessageParametersData, Function<Exception, String>> messageHandler,
        HandlerType handler,
        ParameterType parameter
    ) {
        final var nameof = "invokeCore";
        final var castData = defaults.castData;
        final var methodData = data.object;
        final var method = methodData.method;
        final var function = castData.caster.compose(defaults.constructor.apply(handler).apply(method));
        final var result = defaults.castHandler.apply(new HandleResultData<>(function, parameter, castData.defaultValue));

        final var status = isValidNonFalse(result);
        final var message = (!status) ? (
            messageHandler
                .apply(new InvokeCommonMessageParametersData(data.message.toString(), methodData.returnType, methodData.methodParameterTypes))
                .apply(result.exception)
        ) : result.message.toString();

        return DataFactoryFunctions.getWithNameAndMessage(result.object, status, nameof, message, result.exception);
    }

    private static <HandlerType, ParameterType, ReturnType> Data<ReturnType> invokeCore(
        Data<MethodData> data,
        BaseInvokerDefaultsData<ParameterType, HandlerType, ReturnType> defaults,
        Function<InvokeCommonMessageParametersData, Function<Exception, String>> messageHandler,
        HandlerType handler,
        Data<ParameterType> parameter
    ) {
        return invokeCore(data, defaults, messageHandler, handler, parameter.object);
    }

    private static <ParameterType, HandlerType, ReturnType> Function<Data<ParameterType>, Data<ReturnType>> invokeCore(
        Data<MethodData> data,
        BaseInvokerDefaultsData<ParameterType, HandlerType, ReturnType> defaults,
        Function<InvokeCommonMessageParametersData, Function<Exception, String>> messageHandler,
        HandlerType handler
    ) {
        return parameter -> invokeCore(data, defaults, messageHandler, handler, parameter);
    }

    private static <ParameterType, HandlerType, ReturnType> Data<ReturnType> invokeCore(
        String name,
        Data<MethodData> data,
        BaseInvokerDefaultsData<ParameterType, HandlerType, ReturnType> defaults,
        Function<InvokeCommonMessageParametersData, Function<Exception, String>> messageHandler,
        HandlerType handler,
        ParameterType parameter
    ) {
        final var nameof = isNotBlank(name) ? name : "invokeCore";
        final var errorMessage = InvokeCoreValidator.isInvalidInvokeCoreParametersMessage(data, defaults, messageHandler, handler, parameter);
        return isBlank(errorMessage) ? (
            replaceName(invokeCore(data, defaults, messageHandler, handler, parameter), nameof)
        ) : DataFactoryFunctions.getWithNameAndMessage(null, false, nameof, errorMessage);
    }

    private static <ParameterType, HandlerType, ReturnType> DriverFunction<ReturnType> invokeCore(
        String name,
        Data<MethodData> data,
        BaseInvokerDefaultsData<ParameterType, HandlerType, ReturnType> defaults,
        Function<InvokeCommonMessageParametersData, Function<Exception, String>> messageHandler,
        HandlerType handler,
        DriverFunction<ParameterType> getter
    ) {
        final var nameof = isNotBlank(name) ? name : "invokeCore";
        final var errorMessage = InvokeCoreValidator.isInvalidInvokeCoreParametersMessage(data, defaults, messageHandler, handler, getter);
        final var negative = DataFactoryFunctions.getWithMessage((ReturnType)null, false, errorMessage);
        return DriverFunctionFactory.getFunction(ifDependency(nameof, errorMessage, DataExecutionFunctions.validChain(getter, invokeCore(data, defaults, messageHandler, handler), negative), negative));
    }

    private static Data<WebElement> invokeGetElement(Data<SearchContext> context, By locator) {
        if (isInvalidOrFalse(context) || isNull(locator)) {
            return SeleniumDataConstants.NULL_ELEMENT;
        }

        final var methodData = MethodRepository.getMethod(SeleniumCoreConstants.DEFAULT_WEB_ELEMENT_METHOD_PARAMETERS, SeleniumMethodDefaults.FIND_ELEMENT);
        final var handler = new InvokerParameterizedParametersFieldData<>(CoreUtilities.toSingleElementArray(locator), SeleniumInvokeFunctionDefaults.SEARCH_CONTEXT_SINGLE_PARAMETER);
        final var messageHandler = new ParameterizedMessageData(locator.toString(), CoreFormatter::getInvokeMethodParameterizedMessageFunction);
        return invokeCore("invokeGetElement", methodData, SeleniumInvokeFunctionDefaults.SEARCH_CONTEXT_PARAMETERS, messageHandler, handler, context.object);
    }

    static Function<Data<SearchContext>, Data<WebElement>> invokeGetElement(By locator) {
        final var message = CoreFormatter.isNullMessageWithName(locator, "Locator");
        return isBlank(message) ? (
            context -> invokeGetElement(context, locator)
        ) : context -> DataFactoryFunctions.getInvalidWithNameAndMessage(SeleniumCoreConstants.STOCK_ELEMENT, "invokeGetElement", message);
    }

    private static DriverFunction<Void> invokeElementVoidMethod(String name, LazyElement element, MethodParametersData parameterData) {
        final var nameof = isNotBlank(name) ? name : "invokeElementVoidMethod";
        final var errorMessage = FrameworkCoreFormatter.isNullLazyElementMessage(element) + MethodParametersDataValidators.isValid(parameterData);
        if (isNotBlank(errorMessage)) {
            return driver -> DataFactoryFunctions.getInvalidWithNameAndMessage(null, nameof, errorMessage);
        }

        final var methodData = MethodRepository.getMethod(SeleniumCoreConstants.DEFAULT_WEB_ELEMENT_METHOD_PARAMETERS, parameterData);
        final var messageHandler = new RegularMessageData(CoreFormatter::getInvokeMethodCommonMessageFunction);
        final var result = invokeCore(nameof, methodData, SeleniumInvokeFunctionDefaults.VOID_REGULAR, messageHandler, InvokeFunctions::invoke, element.get());
        return DriverFunctionFactory.prependMessage(result, parameterData.methodName + CoreFormatterConstants.COLON_SPACE);
    }

    private static DriverFunction<Boolean> invokeElementBooleanMethod(String name, LazyElement element, MethodParametersData parameterData) {
        final var nameof = isNotBlank(name) ? name : "invokeElementBooleanMethod";
        final var errorMessage = FrameworkCoreFormatter.isNullLazyElementMessage(element) + MethodParametersDataValidators.isValid(parameterData);
        if (isNotBlank(errorMessage)) {
            return driver -> DataFactoryFunctions.getInvalidWithNameAndMessage(false, nameof, errorMessage);
        }

        final var methodData = MethodRepository.getMethod(SeleniumCoreConstants.DEFAULT_WEB_ELEMENT_METHOD_PARAMETERS, parameterData);
        final var messageHandler = new RegularMessageData(CoreFormatter::getInvokeMethodCommonMessageFunction);
        final var result = invokeCore(nameof, methodData, SeleniumInvokeFunctionDefaults.BOOLEAN_REGULAR, messageHandler, InvokeFunctions::invoke, element.get());
        return DriverFunctionFactory.prependMessage(result, parameterData.methodName + CoreFormatterConstants.COLON_SPACE);
    }

    private static DriverFunction<String> invokeElementStringMethod(String name, LazyElement element, MethodParametersData parameterData) {
        final var nameof = isNotBlank(name) ? name : "invokeElementStringMethod";
        final var errorMessage = FrameworkCoreFormatter.isNullLazyElementMessage(element) + MethodParametersDataValidators.isValid(parameterData);
        if (isNotBlank(errorMessage)) {
            return driver -> DataFactoryFunctions.getInvalidWithNameAndMessage(CoreFormatterConstants.EMPTY, nameof, errorMessage);
        }

        final var methodData = MethodRepository.getMethod(SeleniumCoreConstants.DEFAULT_WEB_ELEMENT_METHOD_PARAMETERS, parameterData);
        final var messageHandler = new RegularMessageData(CoreFormatter::getInvokeMethodCommonMessageFunction);
        final var result = invokeCore(nameof, methodData, SeleniumInvokeFunctionDefaults.STRING_REGULAR, messageHandler, InvokeFunctions::invoke, element.get());
        return DriverFunctionFactory.prependMessage(result, parameterData.methodName + CoreFormatterConstants.COLON_SPACE);
    }

    private static DriverFunction<String> invokeElementStringMethod(String name, LazyElement element, String parameter, MethodParametersData parameterData) {
        final var nameof = isNotBlank(name) ? name : "invokeElementStringMethod";
        final var errorMessage = FrameworkCoreFormatter.isNullLazyElementMessage(element) + MethodParametersDataValidators.isValid(parameterData) + isBlankMessageWithName(parameter, "Execution parameter value");
        if (isNotBlank(errorMessage)) {
            return driver -> DataFactoryFunctions.getInvalidWithNameAndMessage(CoreFormatterConstants.EMPTY, nameof, errorMessage);
        }

        final var methodData = MethodRepository.getMethod(SeleniumCoreConstants.DEFAULT_WEB_ELEMENT_METHOD_PARAMETERS, parameterData);
        final var handler = new InvokerParameterizedParametersFieldData<>(CoreUtilities.toSingleElementArray(parameter, StringUtils::isNotBlank), SeleniumInvokeFunctionDefaults.SINGLE_PARAMETER);
        final var messageHandler = new ParameterizedMessageData(parameter, CoreFormatter::getInvokeMethodParameterizedMessageFunction);
        final var result = invokeCore(nameof, methodData, SeleniumInvokeFunctionDefaults.STRING_PARAMETERS, messageHandler, handler, element.get());
        return DriverFunctionFactory.prependMessage(result, parameterData.methodName + CoreFormatterConstants.COLON_SPACE);
    }

    static DriverFunction<Boolean> invokeElementDisplayed(LazyElement element) {
        return invokeElementBooleanMethod(SeleniumInvokeConstants.ELEMENT_DISPLAYED, element, MethodDefaults.IS_DISPLAYED);
    }

    static DriverFunction<Boolean> invokeElementEnabled(LazyElement element) {
        return invokeElementBooleanMethod(SeleniumInvokeConstants.ELEMENT_ENABLED, element, MethodDefaults.IS_ENABLED);
    }

    static DriverFunction<Boolean> invokeElementSelected(LazyElement element) {
        return invokeElementBooleanMethod(SeleniumInvokeConstants.ELEMENT_SELECTED, element, MethodDefaults.IS_SELECTED);
    }

    static DriverFunction<String> invokeGetElementText(LazyElement element) {
        return invokeElementStringMethod(SeleniumInvokeConstants.GET_ELEMENT_TEXT, element, MethodDefaults.GET_TEXT);
    }

    static DriverFunction<String> invokeGetElementTagname(LazyElement element) {
        return invokeElementStringMethod(SeleniumInvokeConstants.GET_ELEMENT_TAGNAME, element, MethodDefaults.GET_TAG_NAME);
    }

    static DriverFunction<String> invokeGetElementAttribute(LazyElement element, String attribute) {
        return invokeElementStringMethod(SeleniumInvokeConstants.GET_ELEMENT_ATTRIBUTE, element, attribute, MethodDefaults.GET_ATTRIBUTE);
    }

    static DriverFunction<String> invokeGetElementCssValue(LazyElement element, String cssValue) {
        return invokeElementStringMethod(SeleniumInvokeConstants.GET_ELEMENT_CSS_VALUE, element, cssValue, MethodDefaults.GET_CSS_VALUE);
    }

    static DriverFunction<Boolean> invokeElementClickable(LazyElement element) {
        return ifDriver(
            SeleniumInvokeConstants.ELEMENT_CLICKABLE,
            FrameworkCoreFormatter.isNullLazyElementMessage(element),
            SeleniumExecutor.execute(CoreFormatter::getExecutionEndMessageAggregate, invokeElementDisplayed(element), invokeElementEnabled(element)),
            CoreDataConstants.NULL_BOOLEAN
        );
    }

    static DriverFunction<Void> invokeElementClick(LazyElement element) {
        return invokeElementVoidMethod(SeleniumInvokeConstants.CLICK, element, MethodDefaults.CLICK);
    }

    static DriverFunction<Void> invokeElementClear(LazyElement element) {
        return invokeElementVoidMethod(SeleniumInvokeConstants.CLEAR, element, MethodDefaults.CLEAR);
    }

    static DriverFunction<Void> invokeElementSendKeys(LazyElement element, String parameter) {
        final var nameof = "invokeElementSendKeys";
        final var errorMessage = FrameworkCoreFormatter.isNullLazyElementMessage(element) + isBlankMessageWithName(parameter, "Send keys value");
        if (isNotBlank(errorMessage)) {
            return driver -> DataFactoryFunctions.getInvalidWithNameAndMessage(null, nameof, errorMessage);
        }

        final var methodParameterData = MethodDefaults.SEND_KEYS;
        final var methodData = MethodRepository.getMethod(SeleniumCoreConstants.DEFAULT_WEB_ELEMENT_METHOD_PARAMETERS, methodParameterData);
        final var handler = new InvokerParameterizedParametersFieldData<>(CoreUtilities.toSingleElementArray(new CharSequence[]{parameter}, NullableFunctions::isNotNull), SeleniumInvokeFunctionDefaults.SINGLE_PARAMETER);
        final var messageHandler = new ParameterizedMessageData(parameter, CoreFormatter::getInvokeMethodParameterizedMessageFunction);
        final var result = invokeCore(nameof, methodData, SeleniumInvokeFunctionDefaults.VOID_PARAMETERS, messageHandler, handler, element.get());
        return DriverFunctionFactory.prependMessage(result, methodParameterData.methodName + CoreFormatterConstants.COLON_SPACE);
    }
}
