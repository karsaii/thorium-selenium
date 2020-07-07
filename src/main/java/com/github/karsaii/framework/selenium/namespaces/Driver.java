package com.github.karsaii.framework.selenium.namespaces;

import com.github.karsaii.core.constants.CoreDataConstants;
import com.github.karsaii.core.extensions.namespaces.factories.DecoratedListFactory;
import com.github.karsaii.core.extensions.namespaces.predicates.BasicPredicates;
import com.github.karsaii.core.extensions.namespaces.predicates.CollectionPredicates;
import com.github.karsaii.core.namespaces.BaseExecutionFunctions;
import com.github.karsaii.core.namespaces.DataExecutionFunctions;
import com.github.karsaii.framework.core.abstracts.AbstractLazyResult;
import com.github.karsaii.framework.core.namespaces.Adjuster;
import com.github.karsaii.framework.core.namespaces.FrameworkFunctions;
import com.github.karsaii.framework.core.namespaces.validators.FrameworkCoreFormatter;
import com.github.karsaii.framework.core.records.GetByFilterFormatterData;
import com.github.karsaii.framework.core.records.lazy.ExternalSelectorData;
import com.github.karsaii.framework.selenium.constants.SeleniumInvokeConstants;
import com.github.karsaii.core.namespaces.validators.DataValidators;
import com.github.karsaii.core.namespaces.validators.MethodParametersDataValidators;
import com.github.karsaii.core.namespaces.validators.CoreFormatter;
import com.github.karsaii.core.constants.validators.CoreFormatterConstants;
import com.github.karsaii.framework.selenium.constants.validators.SeleniumFormatterConstants;
import com.github.karsaii.framework.selenium.namespaces.factories.DriverFunctionFactory;
import com.github.karsaii.framework.selenium.namespaces.factories.ElementFilterParametersFactory;
import com.github.karsaii.framework.selenium.namespaces.factories.LazyElementWithOptionsDataFactory;
import com.github.karsaii.framework.selenium.namespaces.factories.SeleniumDataFactory;
import com.github.karsaii.framework.selenium.namespaces.factories.SeleniumLazyLocatorFactory;
import com.github.karsaii.framework.selenium.namespaces.factories.WebElementListFactory;
import com.github.karsaii.framework.selenium.namespaces.validators.SeleniumFormatter;
import com.github.karsaii.framework.selenium.abstracts.AbstractElementFunctionParameters;
import com.github.karsaii.framework.selenium.constants.ElementFunctionConstants;
import com.github.karsaii.framework.selenium.constants.SeleniumInvokeFunctionDefaults;
import com.github.karsaii.core.extensions.DecoratedList;
import com.github.karsaii.core.extensions.boilers.StringSet;
import com.github.karsaii.framework.selenium.namespaces.element.ElementFilterFunctions;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.WebElementList;
import com.github.karsaii.framework.selenium.constants.FactoryConstants;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.github.karsaii.core.extensions.namespaces.CoreUtilities;
import com.github.karsaii.core.extensions.namespaces.NullableFunctions;
import com.github.karsaii.core.namespaces.DataFactoryFunctions;
import com.github.karsaii.core.namespaces.InvokeFunctions;
import com.github.karsaii.core.namespaces.repositories.MethodRepository;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.core.records.ExecuteCommonData;
import com.github.karsaii.core.records.HandleResultData;
import com.github.karsaii.core.records.MethodData;
import com.github.karsaii.framework.selenium.constants.MethodDefaults;
import com.github.karsaii.core.records.MethodMessageData;
import com.github.karsaii.core.records.MethodParametersData;
import com.github.karsaii.core.records.reflection.InvokerParameterizedParametersFieldData;
import com.github.karsaii.core.abstracts.reflection.BaseInvokerDefaultsData;
import com.github.karsaii.core.constants.CoreConstants;
import com.github.karsaii.core.records.reflection.message.InvokeCommonMessageParametersData;
import com.github.karsaii.core.reflection.message.ParameterizedMessageData;
import com.github.karsaii.core.reflection.message.RegularMessageData;
import com.github.karsaii.framework.selenium.records.GetElementByData;
import com.github.karsaii.framework.selenium.records.GetWithDriverData;
import com.github.karsaii.framework.selenium.records.lazy.filtered.LazyFilteredElementParameters;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebElement;
import com.github.karsaii.framework.core.selector.records.SelectorKeySpecificityData;
import com.github.karsaii.framework.core.constants.AdjusterConstants;
import com.github.karsaii.framework.selenium.constants.SeleniumDataConstants;
import com.github.karsaii.framework.selenium.constants.DriverFunctionConstants;
import com.github.karsaii.framework.selenium.constants.ElementFinderConstants;
import com.github.karsaii.framework.selenium.constants.ExecuteCoreDataConstants;
import com.github.karsaii.framework.selenium.constants.ExecuteCoreFunctionDataConstants;
import com.github.karsaii.framework.selenium.constants.SeleniumGetOrderConstants;
import com.github.karsaii.framework.selenium.constants.SeleniumCoreConstants;
import com.github.karsaii.framework.selenium.constants.SeleniumMethodDefaults;
import com.github.karsaii.framework.selenium.enums.SingleGetter;
import com.github.karsaii.framework.selenium.namespaces.lazy.LazyIndexedElementFactory;
import com.github.karsaii.framework.selenium.namespaces.scripter.Execute;
import com.github.karsaii.framework.selenium.namespaces.repositories.ElementRepository;
import com.github.karsaii.framework.selenium.namespaces.repositories.FunctionRepository;
import com.github.karsaii.framework.selenium.namespaces.repositories.LocatorRepository;
import com.github.karsaii.framework.selenium.namespaces.utilities.SeleniumUtilities;
import com.github.karsaii.framework.selenium.namespaces.validators.ExecuteCoreValidators;
import com.github.karsaii.framework.selenium.namespaces.validators.InvokeCoreValidator;
import com.github.karsaii.framework.selenium.namespaces.element.validators.WebElementValidators;
import com.github.karsaii.framework.selenium.records.element.is.ElementConditionParameters;
import com.github.karsaii.framework.selenium.records.element.is.ElementStringValueParameters;
import com.github.karsaii.framework.selenium.records.element.is.ElementParameterizedValueParameters;
import com.github.karsaii.framework.selenium.records.scripter.ExecuteCoreData;
import com.github.karsaii.framework.selenium.records.scripter.ExecuteCoreFunctionData;
import com.github.karsaii.framework.selenium.records.ExternalElementData;
import com.github.karsaii.framework.selenium.records.ExternalSeleniumSelectorData;
import com.github.karsaii.framework.core.records.InternalSelectorData;
import com.github.karsaii.framework.selenium.records.lazy.LazyElement;
import com.github.karsaii.framework.core.namespaces.extensions.boilers.LazyLocatorList;
import com.github.karsaii.framework.selenium.records.scripter.ParametersFieldDefaultsData;
import com.github.karsaii.framework.core.records.ProbabilityData;
import com.github.karsaii.framework.selenium.records.SwitchResultMessageData;
import com.github.karsaii.framework.selenium.records.lazy.LazyElementWithOptionsData;
import com.github.karsaii.framework.core.records.lazy.LazyLocator;
import com.github.karsaii.framework.selenium.records.scripter.ExecutorData;
import com.github.karsaii.framework.selenium.records.scripter.ExecutorParametersFieldData;
import com.github.karsaii.framework.selenium.namespaces.validators.ScriptExecutions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import static com.github.karsaii.core.extensions.namespaces.CoreUtilities.areAnyNull;
import static com.github.karsaii.core.extensions.namespaces.CoreUtilities.areNotNull;
import static com.github.karsaii.core.extensions.namespaces.NullableFunctions.isNotNull;
import static com.github.karsaii.core.extensions.namespaces.NullableFunctions.isNull;
import static com.github.karsaii.core.namespaces.DataExecutionFunctions.ifDependency;
import static com.github.karsaii.core.namespaces.DataFactoryFunctions.appendMessage;
import static com.github.karsaii.core.namespaces.DataFactoryFunctions.prependMessage;
import static com.github.karsaii.core.namespaces.DataFactoryFunctions.replaceMessage;
import static com.github.karsaii.core.namespaces.DataFactoryFunctions.replaceName;
import static com.github.karsaii.core.namespaces.predicates.DataPredicates.isInvalidOrFalse;
import static com.github.karsaii.core.namespaces.predicates.DataPredicates.isValidNonFalse;
import static com.github.karsaii.framework.selenium.namespaces.utilities.SeleniumUtilities.isInvalidLazyLocator;
import static com.github.karsaii.framework.selenium.namespaces.validators.SeleniumFormatter.getElementsParametersMessage;
import static com.github.karsaii.core.namespaces.validators.CoreFormatter.isBlankMessageWithName;
import static com.github.karsaii.core.namespaces.validators.CoreFormatter.isInvalidOrFalseMessage;
import static com.github.karsaii.core.namespaces.validators.CoreFormatter.isNegativeMessage;
import static com.github.karsaii.core.namespaces.validators.CoreFormatter.isNullMessage;
import static com.github.karsaii.core.namespaces.validators.CoreFormatter.isNullOrEmptyMessage;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static com.github.karsaii.framework.selenium.namespaces.ExecutionCore.ifData;
import static com.github.karsaii.framework.selenium.namespaces.ExecutionCore.ifDriver;
import static com.github.karsaii.framework.selenium.namespaces.ExecutionCore.ifDriverGuardData;
import static com.github.karsaii.framework.selenium.namespaces.ExecutionCore.validChain;
import static com.github.karsaii.framework.selenium.namespaces.utilities.SeleniumUtilities.getLocator;
import static com.github.karsaii.framework.selenium.namespaces.utilities.SeleniumUtilities.isNotNullLazyData;
import static com.github.karsaii.framework.selenium.namespaces.utilities.SeleniumUtilities.isNotNullLazyElement;
import static com.github.karsaii.framework.selenium.namespaces.utilities.SeleniumUtilities.isNotNullWebElement;
import static com.github.karsaii.framework.core.namespaces.FrameworkCoreUtilities.isNullLazyLocator;
import static com.github.karsaii.framework.selenium.namespaces.utilities.SeleniumUtilities.isNullWebElement;

public interface Driver {
    private static JavascriptExecutor getExecutor(WebDriver driver) {
        return (JavascriptExecutor)driver;
    }

    private static TakesScreenshot getScreenshotter(WebDriver driver) {
        return (TakesScreenshot)driver;
    }

    private static TargetLocator getTargetLocator(WebDriver driver) {
        return driver.switchTo();
    }

    private static <T extends SearchContext> SearchContext getSearchContextOf(T object) {
        return object;
    }

    private static <T, U> Data<U> getSubtypeOf(String dependencyName, T dependency, Function<T, U> getter, U defaultValue) {
        final var lDependencyName = isNotBlank(dependencyName) ? dependencyName : "Dependency";
        final var status = isNotNull(dependency);
        final var object = status ? getter.apply(dependency) : defaultValue;
        final var message = lDependencyName + (status ? CoreFormatterConstants.WASNT_NULL : CoreFormatterConstants.WAS_NULL);
        return DataFactoryFunctions.getWithNameAndMessage(object, status, "getSubtypeOf", message);
    }

    static <T extends SearchContext> Data<SearchContext> getSearchContextOf(String dependencyName, Data<T> data) {
        return (isValidNonFalse(data)) ? (
            getSubtypeOf(dependencyName, data.object, Driver::getSearchContextOf, FactoryConstants.NULL_SEARCH_CONTEXT)
        ) : FactoryConstants.NULL_SEARCH_CONTEXT_DATA;
    }

    static Data<SearchContext> getSearchContext(WebElement element) {
        return getSubtypeOf("Search Context (Element)", element, Driver::getSearchContextOf, FactoryConstants.NULL_SEARCH_CONTEXT);
    }

    static Data<SearchContext> getSearchContext(WebDriver driver) {
        return getSubtypeOf("Search Context (Driver)", driver, Driver::getSearchContextOf, FactoryConstants.NULL_SEARCH_CONTEXT);
    }

    private static <T> DriverFunction<T> getSubtypeOfDriver(Function<WebDriver, T> getter, T defaultValue) {
        return driver -> getSubtypeOf("Driver", driver, getter, defaultValue);
    }

    static DriverFunction<JavascriptExecutor> getExecutorData() {
        return getSubtypeOfDriver(Driver::getExecutor, FactoryConstants.NULL_JAVASCRIPT_EXECUTOR);
    }

    static DriverFunction<TakesScreenshot> getScreenshotter() {
        return getSubtypeOfDriver(Driver::getScreenshotter, FactoryConstants.NULL_TAKES_SCREENSHOT);
    }

    static DriverFunction<TargetLocator> getTargetLocator() {
        return getSubtypeOfDriver(Driver::getTargetLocator, FactoryConstants.NULL_TARGET_LOCATOR);
    }

    private static <HandlerType, ReturnType> Data<ReturnType> executeCore(WebDriver driver, ExecutorData<HandlerType, String, Boolean, ReturnType> data, HandlerType handler, String script) {
        final var nameof = "executeCore";
        final var castData = data.castData;
        final var executor = data.getter.apply(driver);
        final var defaultValue = castData.defaultValue.object;
        if (isInvalidOrFalse(executor)) {
            return DataFactoryFunctions.getWithNameAndMessage(defaultValue, false, nameof, "Executor" + CoreFormatterConstants.WAS_NULL);
        }

        final var parameters = new ExecuteCommonData<>(script, StringUtils::isNotBlank);
        final var exData = data.constructor.apply(parameters, handler);
        final var function = castData.caster.compose(exData.apply(executor.object));
        final var resultFunctions = data.resultHandlers;
        final var result = resultFunctions.castHandler.apply(new HandleResultData<>(function, script, defaultValue));
        final var status = result.status;
        var message = result.message.message;
        if (status) {
            message = resultFunctions.messageHandler.apply(status);
        }
        return DataFactoryFunctions.getWithNameAndMessage(result.object, status, nameof, message, result.exception);
    }

    private static <T> Data<T> getCore(WebDriver driver, String property, Predicate<T> guard, Function<WebDriver, T> function, T defaultValue) {
        final var result = function.apply(driver);
        final var nameof = "getCore";
        return guard.test(result) ? (
            DataFactoryFunctions.getWithNameAndMessage(result, true, nameof, property + " is: \"" + result + "\"" + CoreFormatterConstants.END_LINE)
        ) : DataFactoryFunctions.getWithNameAndMessage(defaultValue, false, nameof, property + CoreFormatterConstants.WAS_NULL);
    }

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

    private static <HandlerType, ReturnType> DriverFunction<ReturnType> executeCore(
        ExecuteCoreFunctionData<HandlerType> functionData,
        DriverFunction<ReturnType> negative,
        ExecutorData<HandlerType, String, Boolean, ReturnType> data,
        String script
    ) {
        final var isFunctionDataNotNull = isNotNull(functionData);
        final var name = isFunctionDataNotNull ? functionData.nameof : CoreFormatterConstants.EMPTY;
        final var nameof = isNotBlank(name) ? name : "executeCore";
        return ifDriver(
            nameof,
            isBlank(script) || !(isFunctionDataNotNull && ScriptExecutions.isValidConstructorData(data)),
            driver -> executeCore(driver, data, functionData.handler, script),
            negative
        );
    }

    private static <T> DriverFunction<T> getCore(String property, Predicate<T> guard, Function<WebDriver, T> function, T defaultValue) {
        final var isPropertyNotBlank = isNotBlank(property);
        return ifDriver(
            isPropertyNotBlank ? "get" + property : "getCore",
            isPropertyNotBlank && areNotNull(guard, function, defaultValue),
            driver -> getCore(driver, property, guard, function, defaultValue),
            DataFactoryFunctions.getWithMessage(defaultValue, false, SeleniumFormatterConstants.DRIVER_WAS_NULL)
        );
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

    static Data<Integer> getWindowHandleAmountData(Data<StringSet> data) {
        return FrameworkFunctions.getWindowHandleAmount(data, "window Handles");
    }

    static <T> Data<Integer> getCountOfElements(Data<WebElementList> data) {
        return FrameworkFunctions.getCountOfElements(SeleniumDataFactory.getUnwrapped(data), "Element");
    }

    static <T> DriverFunction<Integer> getCountOfElements(DriverFunction<WebElementList> getter) {
        return ifDriver(
            "getCountOfElements",
            isNotNull(getter),
            SeleniumCoreConstants.WEBELEMENT_LIST_VALIDATOR,
            getter.andThen(SeleniumDataFactory::getUnwrapped),
            FrameworkFunctions.getCountOfElements("Element"),
            SeleniumDataConstants.NULL_INTEGER_NULL_DRIVER
        );
    }



    private static DriverFunction<String> getString(String property, Function<WebDriver, String> function) {
        return getCore(property, NullableFunctions::isNotNull, function, CoreFormatterConstants.EMPTY);
    }

    static DriverFunction<String> getTitle() {
        return getString("Title", WebDriver::getTitle);
    }

    static DriverFunction<String> getWindowHandle() {
        return getString("WindowHandle", WebDriver::getWindowHandle);
    }

    static DriverFunction<String> getUrl() {
        return getCore("Url", StringUtils::isNotBlank, WebDriver::getCurrentUrl, CoreFormatterConstants.EMPTY);
    }

    static DriverFunction<StringSet> getWindowHandles() {
        final var getStringSetOfWindowHandles = BaseExecutionFunctions.conditionalChain(NullableFunctions::isNotNull, WebDriver::getWindowHandles, StringSet::new, CoreConstants.NULL_STRING_SET);
        return getCore("WindowHandles", NullableFunctions::isNotNull, getStringSetOfWindowHandles, CoreConstants.NULL_STRING_SET);
    }

    static DriverFunction<Integer> getWindowHandleAmount() {
        return ifDriverGuardData("getWindowHandleAmount", Driver.getWindowHandles(), Driver::getWindowHandleAmountData, CoreDataConstants.NULL_INTEGER);
    }

    static <HandlerType, ReturnType> DriverFunction<ReturnType> execute(ExecuteCoreFunctionData<HandlerType> functionData, ExecuteCoreData<HandlerType, ReturnType> data, String script) {
        return executeCore(functionData, FunctionRepository.get(data.functionMap, data.negativeKeyData), data.data, script);
    }

    static <ReturnType> DriverFunction<ReturnType> executeParameters(
        ExecuteCoreFunctionData<ParametersFieldDefaultsData> functionData,
        ExecuteCoreData<ExecutorParametersFieldData, ReturnType> data,
        String script,
        Object[] parameters
    ) {
        final var negative = FunctionRepository.get(data.functionMap, data.negativeKeyData);
        final var handlerData = functionData.handler;
        final var errorMessage = (
            ExecuteCoreValidators.isInvalidExecuteCoreFunctionData(functionData) +
            CoreFormatter.isNullMessageWithName(parameters, "Parameters") +
            CoreFormatter.isFalseMessageWithName(handlerData.validator.test(parameters), "Parameter validation")
        );
        if (isNotBlank(errorMessage)) {
            return DriverFunctionFactory.replaceMessage(negative, errorMessage);
        }

        final var fnData = new ExecuteCoreFunctionData<>(functionData.nameof, new ExecutorParametersFieldData(parameters, handlerData));
        return executeCore(fnData, negative, data.data, script);
    }

    static DriverFunction<Object> execute(String script) {
        return execute(ExecuteCoreFunctionDataConstants.EXECUTE, ExecuteCoreDataConstants.EXECUTE_RETURN_OBJECT, script);
    }

    static DriverFunction<Object> executeAsync(String script) {
        return execute(ExecuteCoreFunctionDataConstants.EXECUTE_ASYNC, ExecuteCoreDataConstants.EXECUTE_RETURN_OBJECT, script);
    }

    static DriverFunction<Object> executeParameters(String script, Object[] parameters) {
        return executeParameters(ExecuteCoreFunctionDataConstants.EXECUTE_PARAMETERS, ExecuteCoreDataConstants.EXECUTE_PARAMETERS_RETURN_OBJECT, script, parameters);
    }

    static DriverFunction<Object> executeAsyncParameters(String script, Object[] parameters) {
        return executeParameters(ExecuteCoreFunctionDataConstants.EXECUTE_ASYNC_PARAMETERS, ExecuteCoreDataConstants.EXECUTE_PARAMETERS_RETURN_OBJECT, script, parameters);
    }

    static DriverFunction<Object> executeSingleParameter(String script, Object[] parameter) {
        return executeParameters(ExecuteCoreFunctionDataConstants.EXECUTE_SINGLE_PARAMETER, ExecuteCoreDataConstants.EXECUTE_PARAMETERS_RETURN_OBJECT, script, parameter);
    }

    static DriverFunction<Object> executeAsyncSingleParameter(String script, Object[] parameter) {
        return executeParameters(ExecuteCoreFunctionDataConstants.EXECUTE_ASYNC_SINGLE_PARAMETER, ExecuteCoreDataConstants.EXECUTE_PARAMETERS_RETURN_OBJECT, script, parameter);
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
            return driver -> DataFactoryFunctions.getInvalidWithNameAndMessage(null, nameof, errorMessage);
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
            return driver -> DataFactoryFunctions.getInvalidWithNameAndMessage(null, nameof, errorMessage);
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
            return driver -> DataFactoryFunctions.getInvalidWithNameAndMessage(null, nameof, errorMessage);
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

    private static <T> Data<Boolean> isElementCore(String elementName, Data<T> data, ElementConditionParameters<Boolean> parameters) {
        final var status = parameters.inverter.apply(isValidNonFalse(data));
        final var formatData = parameters.formatData;
        var message = formatData.formatter.apply(elementName, formatData.descriptor, status);
        if (CoreUtilities.isFalse(status)) {
            message += data.message.getMessage();
        }

        return DataFactoryFunctions.getBoolean(status, formatData.conditionName, message);
    }

    private static Data<String> getElementValueCore(String elementName, Data<String> data, AbstractElementFunctionParameters<String, String> parameters) {
        final var status = isValidNonFalse(data);
        final var object = data.object;
        final var formatData = parameters.formatData;
        var message = formatData.formatter.apply(elementName, formatData.descriptor, object);
        if (CoreUtilities.isFalse(status)) {
            message += data.message.getMessage();
        }

        return DataFactoryFunctions.getWithNameAndMessage(object, status, formatData.conditionName, message);
    }

    private static <T> Function<Data<T>, Data<Boolean>> isElementCore(String elementName, ElementConditionParameters<Boolean> parameters) {
        return data -> isElementCore(elementName, data, parameters);
    }

    private static Function<Data<String>, Data<String>> getElementValueCore(String elementName, AbstractElementFunctionParameters<String, String> parameters) {
        return data -> getElementValueCore(elementName, data, parameters);
    }

    private static DriverFunction<Boolean> isElementPositive(LazyElement element, ElementConditionParameters<Boolean> parameters, Data<Boolean> guard) {
        return parameters.handler.apply(parameters.function.apply(element), isElementCore(element.name, parameters), replaceName(guard, "isElementPositive: "));
    }

    private static DriverFunction<String> getElementValuePositive(String name, DriverFunction<String> function, AbstractElementFunctionParameters<String, String> parameters, Data<String> guard) {
        return parameters.handler.apply(function, getElementValueCore(name, parameters), replaceName(guard, "getElementValuePositive: "));
    }

    private static DriverFunction<Boolean> isElement(LazyElement element, ElementConditionParameters<Boolean> parameters) {
        final var negative = CoreDataConstants.NULL_BOOLEAN;
        return ifDriver("isElement", SeleniumFormatter.isElementFunctionMessage(element, parameters), isElementPositive(element, parameters, negative), negative);
    }

    private static DriverFunction<String> getElementValue(LazyElement element, ElementStringValueParameters<String> parameters) {
        final var negative = CoreDataConstants.NULL_STRING;
        final var errorMessage = SeleniumFormatter.isElementFunctionMessage(element, parameters);
        if (isNotBlank(errorMessage)) {
            return DriverFunctionFactory.get(replaceMessage(negative, "getElementValue", errorMessage));
        }

        return getElementValuePositive(element.name, parameters.function.apply(element), parameters, negative);
    }

    private static DriverFunction<String> getElementValue(LazyElement element, String value, ElementParameterizedValueParameters<String> parameters) {
        final var negative = CoreDataConstants.NULL_STRING;
        final var errorMessage = SeleniumFormatter.isElementFunctionMessage(element, parameters) + CoreFormatter.isNullMessageWithName(value, "Value");
        if (isBlank(errorMessage)) {
            return DriverFunctionFactory.get(replaceMessage(negative, "getElementValue", errorMessage));
        }

        return getElementValuePositive(element.name, parameters.function.apply(element, value), parameters, negative);
    }

    static DriverFunction<Boolean> isElementPresent(LazyElement element) {
        return isElement(element, ElementFunctionConstants.PRESENT);
    }

    static DriverFunction<Boolean> isElementAbsent(LazyElement element) {
        return isElement(element, ElementFunctionConstants.ABSENT);
    }

    static DriverFunction<Boolean> isElementDisplayed(LazyElement element) {
        return isElement(element, ElementFunctionConstants.DISPLAYED);
    }

    static DriverFunction<Boolean> isElementEnabled(LazyElement element) {
        return isElement(element, ElementFunctionConstants.ENABLED);
    }

    static DriverFunction<Boolean> isElementClickable(LazyElement element) {
        return isElement(element, ElementFunctionConstants.CLICKABLE);
    }

    static DriverFunction<Boolean> isElementSelected(LazyElement element) {
        return isElement(element, ElementFunctionConstants.SELECTED);
    }

    static DriverFunction<Boolean> isElementHidden(LazyElement element) {
        return isElement(element, ElementFunctionConstants.HIDDEN);
    }

    static DriverFunction<Boolean> isElementDisabled(LazyElement element) {
        return isElement(element, ElementFunctionConstants.DISABLED);
    }

    static DriverFunction<Boolean> isElementUnclickable(LazyElement element) {
        return isElement(element, ElementFunctionConstants.UNCLICKABLE);
    }

    static DriverFunction<Boolean> isElementUnselected(LazyElement element) {
        return isElement(element, ElementFunctionConstants.UNSELECTED);
    }

    static DriverFunction<Boolean> isElement(Function<LazyElement, DriverFunction<Boolean>> elementCondition, LazyElement element) {
        return ifDriver("isElement", isNotNullLazyElement(element) && areNotNull(elementCondition), elementCondition.apply(element), CoreDataConstants.PARAMETERS_NULL_BOOLEAN);
    }

    static DriverFunction<Boolean> isElement(Function<LazyElement, DriverFunction<Boolean>> elementCondition, Data<LazyElement> data) {
        return isValidNonFalse(data) ? isElement(elementCondition, data.object) : DriverFunctionConstants.NULL_BOOLEAN;
    }

    static DriverFunction<String> getElementText(LazyElement element) {
        return getElementValue(element, ElementFunctionConstants.TEXT);
    }

    static DriverFunction<String> getElementTagName(LazyElement element) {
        return getElementValue(element, ElementFunctionConstants.TAGNAME);
    }

    static DriverFunction<String> getElementAttribute(LazyElement element, String value) {
        return getElementValue(element, value, ElementFunctionConstants.ATTRIBUTE);
    }

    static DriverFunction<String> getElementCssValue(LazyElement element, String value) {
        return getElementValue(element, value, ElementFunctionConstants.CSS_VALUE);
    }

    static DriverFunction<String> getElementAttributeValue(LazyElement element) {
        return getElementAttribute(element, "value");
    }

    static DriverFunction<String> getElementText(Data<LazyElement> data) {
        final var errorMessage = DataValidators.isInvalidOrFalseMessage(data);
        return isBlank(errorMessage) ? getElementText(data.object) : DriverFunctionFactory.get(replaceMessage(CoreDataConstants.NULL_STRING, "getElementText", errorMessage));
    }

    static DriverFunction<String> getElementTagName(Data<LazyElement> data) {
        final var errorMessage = DataValidators.isInvalidOrFalseMessage(data);
        return isBlank(errorMessage) ? getElementTagName(data.object) : DriverFunctionFactory.get(replaceMessage(CoreDataConstants.NULL_STRING, "getElementTagName", errorMessage));
    }

    static DriverFunction<String> getElementAttribute(Data<LazyElement> data, String value) {
        final var errorMessage = DataValidators.isInvalidOrFalseMessage(data) + isBlankMessageWithName(value, "Attribute value");
        return isBlank(errorMessage) ? getElementAttribute(data.object, value) : DriverFunctionFactory.get(replaceMessage(CoreDataConstants.NULL_STRING, "getElementAttribute", errorMessage));
    }

    static DriverFunction<String> getElementCssValue(Data<LazyElement> data, String value) {
        final var errorMessage = DataValidators.isInvalidOrFalseMessage(data) + isBlankMessageWithName(value, "Attribute value");
        return isBlank(errorMessage) ? getElementCssValue(data.object, value) : DriverFunctionFactory.get(replaceMessage(CoreDataConstants.NULL_STRING, "getElementCssValue", errorMessage));
    }

    static DriverFunction<String> getElementAttributeValue(Data<LazyElement> data) {
        return getElementAttribute(data, "value");
    }

    static DriverFunction<Boolean> isElementPresent(By locator, SingleGetter getter) {
        return isElement(Driver::isElementPresent, LocatorRepository.getIfContains(locator, getter));
    }
    static DriverFunction<Boolean> isElementDisplayed(By locator, SingleGetter getter) {
        return isElement(Driver::invokeElementDisplayed, LocatorRepository.getIfContains(locator, getter));
    }
    static DriverFunction<Boolean> isElementEnabled(By locator, SingleGetter getter) {
        return isElement(Driver::invokeElementEnabled, LocatorRepository.getIfContains(locator, getter));
    }
    static DriverFunction<Boolean> isElementClickable(By locator, SingleGetter getter) {
        return isElement(Driver::isElementClickable, LocatorRepository.getIfContains(locator, getter));
    }
    static DriverFunction<Boolean> isElementSelected(By locator, SingleGetter getter) {
        return isElement(Driver::invokeElementSelected, LocatorRepository.getIfContains(locator, getter));
    }
    static DriverFunction<Boolean> isElementAbsent(By locator, SingleGetter getter) {
        return isElement(Driver::isElementAbsent, LocatorRepository.getIfContains(locator, getter));
    }
    static DriverFunction<Boolean> isElementHidden(By locator, SingleGetter getter) {
        return isElement(Driver::isElementHidden, LocatorRepository.getIfContains(locator, getter));
    }
    static DriverFunction<Boolean> isElementDisabled(By locator, SingleGetter getter) {
        return isElement(Driver::isElementDisabled, LocatorRepository.getIfContains(locator, getter));
    }
    static DriverFunction<Boolean> isElementUnclickable(By locator, SingleGetter getter) {
        return isElement(Driver::isElementUnclickable, LocatorRepository.getIfContains(locator, getter));
    }
    static DriverFunction<Boolean> isElementUnselected(By locator, SingleGetter getter) {
        return isElement(Driver::isElementUnselected, LocatorRepository.getIfContains(locator, getter));
    }
    static DriverFunction<String> getElementText(By locator, SingleGetter getter) {
        return getElementText(LocatorRepository.getIfContains(locator, getter));
    }
    static DriverFunction<String> getElementTagName(By locator, SingleGetter getter) {
        return getElementTagName(LocatorRepository.getIfContains(locator, getter));
    }
    static DriverFunction<String> getElementAttribute(By locator, SingleGetter getter, String attribute) {
        return getElementAttribute(LocatorRepository.getIfContains(locator, getter), attribute);
    }
    static DriverFunction<String> getElementAttributeValue(By locator, SingleGetter getter) {
        return getElementAttributeValue(LocatorRepository.getIfContains(locator, getter));
    }
    static DriverFunction<String> getElementCssValue(By locator, SingleGetter getter, String cssValue) {
        return getElementCssValue(LocatorRepository.getIfContains(locator, getter), cssValue);
    }

    static DriverFunction<Boolean> isElementPresent(By locator) {
        return isElement(Driver::isElementPresent, LocatorRepository.getIfContains(locator));
    }
    static DriverFunction<Boolean> isElementDisplayed(By locator) {
        return isElement(Driver::isElementDisplayed, LocatorRepository.getIfContains(locator));
    }
    static DriverFunction<Boolean> isElementEnabled(By locator) {
        return isElement(Driver::isElementEnabled, LocatorRepository.getIfContains(locator));
    }
    static DriverFunction<Boolean> isElementClickable(By locator) {
        return isElement(Driver::isElementClickable, LocatorRepository.getIfContains(locator));
    }
    static DriverFunction<Boolean> isElementSelected(By locator) {
        return isElement(Driver::isElementSelected, LocatorRepository.getIfContains(locator));
    }
    static DriverFunction<Boolean> isElementAbsent(By locator) {
        return isElement(Driver::isElementAbsent, LocatorRepository.getIfContains(locator));
    }
    static DriverFunction<Boolean> isElementHidden(By locator) {
        return isElement(Driver::isElementHidden, LocatorRepository.getIfContains(locator));
    }
    static DriverFunction<Boolean> isElementDisabled(By locator) {
        return isElement(Driver::isElementDisabled, LocatorRepository.getIfContains(locator));
    }
    static DriverFunction<Boolean> isElementUnclickable(By locator) {
        return isElement(Driver::isElementUnclickable, LocatorRepository.getIfContains(locator));
    }
    static DriverFunction<Boolean> isElementUnselected(By locator) {
        return isElement(Driver::isElementUnselected, LocatorRepository.getIfContains(locator));
    }

    static DriverFunction<String> getElementText(By locator) {
        return getElementText(locator, SingleGetter.DEFAULT);
    }

    static DriverFunction<String> getElementTagName(By locator) {
        return getElementTagName(locator, SingleGetter.DEFAULT);
    }

    static DriverFunction<String> getElementAttribute(By locator, String attribute) {
        return getElementAttribute(locator, SingleGetter.DEFAULT, attribute);
    }

    static DriverFunction<String> getElementAttributeValue(By locator) {
        return getElementAttributeValue(locator, SingleGetter.DEFAULT);
    }

    static DriverFunction<String> getElementCssValue(By locator, String cssValue) {
        return getElementCssValue(locator, SingleGetter.DEFAULT, cssValue);
    }

    private static Data<WebElementList> getElementsCore(SearchContext context, By locator) {
        final var nameof = "getElementsCore";
        final Function<SearchContext, List<WebElement>> function = locator::findElements;
        final var composed = function.andThen(WebElementList::new);

        final var result = SeleniumExceptionHandlers.findElementsHandler(new HandleResultData<>(composed, context, SeleniumCoreConstants.NULL_ELEMENT_LIST));
        final var list = result.object;
        final var status = result.status;
        final var exception = result.exception;
        final var message = status ? SeleniumFormatter.getFindElementsMessage(locator.toString(), list.size()) : "An Exception(" + exception.getClass() + ") has occurred" + CoreFormatterConstants.END_LINE;
        return DataFactoryFunctions.getWithNameAndMessage(list, status, nameof, message, exception);
    }

    private static Data<WebElementList> getElementsCore(Data<SearchContext> contextData, LazyLocator locator) {
        final var nameof = "getElementsCore";
        final var negative = SeleniumCoreConstants.NULL_ELEMENT_LIST;
        var errorMessage = FrameworkCoreFormatter.isInvalidLazyLocatorMessage(locator, SeleniumUtilities::getLocator) + isInvalidOrFalseMessage(contextData);
        if (isNotBlank(errorMessage)) {
            return DataFactoryFunctions.getInvalidWithNameAndMessage(negative, nameof, errorMessage);
        }

        final var context = contextData.object;
        errorMessage = CoreFormatter.isNullMessageWithName(context, "Context");
        if (isNotBlank(errorMessage)) {
            return DataFactoryFunctions.getInvalidWithNameAndMessage(negative, nameof, errorMessage);
        }

        final var data = getLocator(locator);
        errorMessage = isInvalidOrFalseMessage(data);
        if (isNotBlank(errorMessage)) {
            return DataFactoryFunctions.getInvalidWithNameAndMessage(negative, nameof, errorMessage);
        }

        return getElementsCore(context, data.object);
    }

    private static Function<Data<SearchContext>, Data<WebElementList>> getElementsCore(LazyLocator locator) {
        return context -> getElementsCore(context, locator);
    }

    private static DriverFunction<WebElementList> getElementsIf(String errorMessage, DriverFunction<WebElementList> positive) {
        return ifDriver("getElements", errorMessage, SeleniumExecutor.execute(switchToDefaultContent(), positive), SeleniumDataConstants.NULL_LIST);
    }

    private static Data<WebElementList> getElements(WebDriver driver, LazyLocatorList locators, Function<LazyLocator, DriverFunction<WebElementList>> getter) {
        final var elementList = WebElementListFactory.getWithEmptyList();
        final var length = locators.size();
        Data<WebElementList> data;
        LazyLocator locator;
        WebElementList list;
        var message = new StringBuilder();
        var index = 0;
        for (; index < length; ++index) {
            locator = locators.get(index);
            if (isNull(locator)) {
                break;
            }

            data = getter.apply(locator).apply(driver);
            message.append(index + data.message.toString() + CoreFormatterConstants.END_LINE);
            if (isInvalidOrFalse(data)) {
                continue;
            }

            list = data.object;
            if (Objects.equals(locator.strategy, "id") && (list.isMany())) {
                message.append("There's more than one element with id(\"" + locator.locator + "\") - amount(\"" + list.size() + "\"). Returning" + CoreFormatterConstants.END_LINE);
                break;
            }

            elementList.addAllNullSafe(list);
        }

        return DataFactoryFunctions.getWithMessage(elementList, elementList.isNotNullAndNonEmpty() && (index == length), message.toString());
    }

    private static DriverFunction<WebElementList> getElements(DriverFunction<SearchContext> getter, LazyLocator locator) {
        return validChain(getter, getElementsCore(locator), SeleniumDataConstants.NULL_LIST);
    }

    static DriverFunction<WebElementList> getElements(LazyLocator locator) {
        return getElementsIf(FrameworkCoreFormatter.isInvalidLazyLocatorMessage(locator, SeleniumUtilities::getLocator), getElements(Driver::getSearchContext, locator));
    }

    static DriverFunction<WebElementList> getElements(By locator) {
        return getElementsIf(CoreFormatter.isNullMessageWithName(locator, "Locator"), getElements(Driver::getSearchContext, SeleniumLazyLocatorFactory.get(locator)));
    }

    static DriverFunction<WebElementList> getElements(LazyLocatorList locators, Function<LazyLocator, DriverFunction<WebElementList>> getter) {
        return getElementsIf(getElementsParametersMessage(locators, getter), driver -> getElements(driver, locators, getter));
    }

    static DriverFunction<WebElementList> getElements(LazyLocatorList locators) {
        return getElementsIf(getElementsParametersMessage(locators), driver -> getElements(driver, locators, Driver::getElements));
    }

    static <T> Data<WebElement> getElementBy(GetElementByData<T, WebElementList> defaults, Data<WebElementList> data, T filter) {
        final var errorMessage = defaults.validator.apply(data, filter);
        if (isNotBlank(errorMessage)) {
            return prependMessage(defaults.defaultValue, "getElementBy", errorMessage);
        }

        final var object = defaults.getter.apply(data, filter);
        final var status = WebElementValidators.isNotNull(object);
        final var message = defaults.formatter.apply(new GetByFilterFormatterData<>(filter, defaults.filterName, status, data.object.size(), data.message.toString()));
        return DataFactoryFunctions.getWithNameAndMessage(object, status, defaults.nameof, message);
    }

    static Data<WebElement> getElementByIndex(Data<WebElementList> data, int index) {
        return getElementBy(DriverFunctionConstants.BY_CONTAINED_INTEGER_CONSTANTS, data, index);
    }

    static Data<WebElement> getElementByContainedText(Data<WebElementList> data, String text) {
        return getElementBy(DriverFunctionConstants.BY_CONTAINED_TEXT_CONSTANTS, data, text);
    }

    static Function<Data<WebElementList>, Data<WebElement>> getElementByContainedText(String message) {
        return data -> getElementByContainedText(data, message);
    }

    static Function<Data<WebElementList>, Data<WebElement>> getElementByIndex(int index) {
        return data -> getElementByIndex(data, index);
    }

    static DriverFunction<WebElement> getElementByIndex(DriverFunction<WebElementList> getter, int index) {
        return ifDriver(
            "getElementByIndexFrom",
            isNotNull(getter) && BasicPredicates.isNonNegative(index),
            validChain(getter, getElementByIndex(index), SeleniumDataConstants.NULL_ELEMENT),
            SeleniumDataConstants.NULL_ELEMENT
        );
    }

    static DriverFunction<WebElement> getElementByIndex(By locator, int index) {
        return getElementByIndex(getElements(locator), index);
    }

    static DriverFunction<WebElement> getElementByContainedText(DriverFunction<WebElementList> getter, String message) {
        return ifDriver(
            "getElementByContainedText",
            isNotNull(getter) && isNotBlank(message),
            validChain(getter, getElementByContainedText(message), SeleniumDataConstants.NULL_ELEMENT),
            SeleniumDataConstants.NULL_ELEMENT
        );
    }

    static DriverFunction<WebElement> getElementByContainedText(By locator, String message) {
        return getElementByContainedText(getElements(locator), message);
    }

    static Data<WebElement> getElementFromSingle(Data<WebElementList> data) {
        final var nameof = "getElementFromSingle";
        return isValidNonFalse(data) ? getElementByIndex(data, 0) : prependMessage(SeleniumDataConstants.NULL_ELEMENT, nameof, "Data or index" + CoreFormatterConstants.WAS_NULL + data.toString());
    }

    static DriverFunction<WebElement> getElementFromSingle(DriverFunction<WebElementList> getter) {
        return ifDriver("getElementFromSingle", isNotNull(getter), validChain(getter, getElementByIndex(0), SeleniumDataConstants.NULL_ELEMENT), SeleniumDataConstants.NULL_ELEMENT);
    }

    static DriverFunction<Integer> getCountOfElements(By locator) {
        return ifDriver("getCountOfElements", CoreFormatter.isNullMessageWithName(locator, "By locator"), getCountOfElements(getElements(locator)), SeleniumDataConstants.NO_ELEMENTS_FOUND);
    }

    private static Data<WebElementList> getElementsAmountCore(Data<WebElementList> data, By locator, int expected) {
        final var nameof = "getElementsAmountCore";
        var errorMessage = isInvalidOrFalseMessage(data) + CoreFormatter.isNullMessageWithName(locator, "Locator") + isNegativeMessage(expected);
        if (isNotBlank(errorMessage)) {
            return replaceMessage(SeleniumDataConstants.NULL_LIST, nameof, errorMessage);
        }

        final var object = data.object;
        errorMessage = isNullOrEmptyMessage(object);
        if (isNotBlank(errorMessage)) {
            return appendMessage(data, errorMessage);
        }

        final var size = (
            data.status &&
            CollectionPredicates.isNonEmptyAndOfType(object, WebElement.class) &&
            CoreUtilities.isNotEqual(SeleniumDataConstants.NULL_ELEMENT.object, object.first())
        ) ? object.size() : 0;
        final var status = size == expected;
        return DataFactoryFunctions.getWithNameAndMessage(object, status, nameof, SeleniumFormatter.getElementsAmountMessage(locator, status, expected, size), data.exception);
    }

    static Function<Data<WebElementList>, Data<WebElementList>> getElementsAmountCore(By locator, int expected) {
        return data -> getElementsAmountCore(data, locator, expected);
    }

    static DriverFunction<WebElementList> getElementsAmount(DriverFunction<WebElementList> getter, LazyLocator locator, int expected) {
        return ifDriver(
            "getElementsAmount",
            isNotNull(getter) && isInvalidLazyLocator(locator) && BasicPredicates.isNonNegative(expected),
            validChain(getter, getElementsAmountCore(getLocator(locator).object, expected), SeleniumDataConstants.NULL_LIST),
            SeleniumDataConstants.NULL_LIST
        );
    }

    static DriverFunction<WebElementList> getElementsAmount(By locator, int expected) {
        final var lazyLocator = SeleniumLazyLocatorFactory.get(locator);
        return ifDriver(
            "getElementsAmount",
            isNotNullLazyData(lazyLocator) && BasicPredicates.isNonNegative(expected),
            getElementsAmount(getElements(lazyLocator), lazyLocator, expected),
            SeleniumDataConstants.NULL_LIST
        );
    }

    static DriverFunction<WebElementList> getElementsAmount(LazyLocator locator, int expected) {
        return ifDriver(
            "getElementsAmount",
            isNotNullLazyData(locator) && BasicPredicates.isNonNegative(expected),
            getElementsAmount(getElements(locator), locator, expected),
            SeleniumDataConstants.NULL_LIST
        );
    }

    static DriverFunction<WebElementList> getSingleElementList(By locator) {
        return getElementsAmount(locator, 1);
    }

    static DriverFunction<WebElementList> getSingleElementList(LazyLocator locator) {
        return getElementsAmount(locator, 1);
    }

    static DriverFunction<WebElement> getElement(By locator) {
        return ifDriver("getElement", isNullMessage(locator), getElementFromSingle(getSingleElementList(locator)), SeleniumDataConstants.NULL_ELEMENT);
    }

    static DriverFunction<WebElement> getElement(LazyLocator locator) {
        return ifDriver("getElement", FrameworkCoreFormatter.isInvalidLazyLocatorMessage(locator, SeleniumUtilities::getLocator), getElementFromSingle(getSingleElementList(locator)), SeleniumDataConstants.NULL_ELEMENT);
    }

    private static Data<WebElement> getShadowRootElementCore(Data<WebElement> data) {
        final var exception = data.exception;
        final var status = isNotNullWebElement(data);
        final var messageData = new MethodMessageData("getShadowRootElementCore", SeleniumFormatter.getShadowRootElementMessage(data.message.getMessage(), status));
        return CoreUtilities.isNonException(exception) ? (
            DataFactoryFunctions.getWithMethodMessage(data.object, status, messageData)
        ) : DataFactoryFunctions.getWithMethodMessage(SeleniumCoreConstants.STOCK_ELEMENT, false, messageData, exception);
    }

    private static DriverFunction<WebElement> getShadowRootElement(Data<WebElement> data) {
        return ifDriver(
            "getShadowRootElement",
            isInvalidOrFalseMessage(data),
            validChain(Execute.getShadowRootElement(data), Driver::getShadowRootElementCore, SeleniumDataConstants.NULL_ELEMENT),
            SeleniumDataConstants.NULL_ELEMENT
        );
    }
    static DriverFunction<WebElement> getShadowRootElement(DriverFunction<WebElement> getter) {
        return ifDriver("getShadowRootElement", CoreFormatter.isNullMessageWithName(getter, "Getter"), Execute.getShadowRoot(getter), SeleniumDataConstants.NULL_ELEMENT);
    }

    static DriverFunction<WebElement> getShadowRootElement(LazyElement element) {
        return getShadowRootElement(element.get());
    }

    static DriverFunction<WebElement> getShadowRootElement(By locator, SingleGetter getter) {
        return ifDriver("getShadowRootElement", areNotNull(locator, getter), getShadowRootElement(LocatorRepository.getIfContains(locator, getter).object), SeleniumDataConstants.NULL_ELEMENT);
    }

    static DriverFunction<WebElement> getShadowRootElement(By locator) {
        return getShadowRootElement(locator, SingleGetter.DEFAULT);
    }

    static DriverFunction<WebElement> getRootElementByInvokedElement(Data<WebElement> data, By locator) {
        final var nameof = "getRootElementByInvokedElement";
        return ifDriver(
            nameof,
            isNotNull(locator) && isValidNonFalse(data),
            getShadowRootElement(invokeGetElement(locator).apply(getSearchContextOf("Element data", data))),
            replaceMessage(SeleniumDataConstants.NULL_ELEMENT, nameof, "Parameters were wrong: locator or data.")
        );
    }

    static DriverFunction<WebElement> getRootElementByInvokedElement(LazyElement element, By locator) {
        final var nameof = "getRootElementByInvokedElement";
        return ifDriver(
            nameof,
            isNotNullLazyElement(element) && isNotNull(locator),
            getRootElementByInvokedElement(element, SeleniumLazyLocatorFactory.get(locator)),
            replaceMessage(SeleniumDataConstants.NULL_ELEMENT, nameof, "Parameters were wrong: locator or data.")
        );
    }

    static DriverFunction<WebElement> getRootElementByInvokedElement(Data<WebElement> data, LazyLocator locator) {
        final var nameof = "getRootElementByInvokedElement";
        return ifDriver(
            nameof,
            isNotNullWebElement(data) && isNotNullLazyData(locator),
            getShadowRootElement(invokeGetElement(getLocator(locator).object).apply(getSearchContextOf("Element data", data))),
            replaceMessage(SeleniumDataConstants.NULL_ELEMENT, nameof, "Parameters were wrong: locator or data.")
        );
    }

    static DriverFunction<WebElement> getRootElementByInvokedElement(LazyElement element, LazyLocator locator) {
        return ifDriver(
            "getRootElementByInvokedElement",
            isNotNullLazyElement(element) && isNotNullLazyData(locator),
            driver -> getShadowRootElement(invokeGetElement(getLocator(locator).object).apply(getSearchContextOf("Element data", element.get().apply(driver)))).apply(driver),
            SeleniumDataConstants.NULL_ELEMENT//, nameof, "Parameters were wrong: locator or data.")
        );
    }

    static DriverFunction<WebElement> getShadowRootElement(Data<WebElement> data, LazyLocatorList locators) {
        return ifDriver(
            "getShadowRootElement",
            isNotNullWebElement(data) && isNotNull(locators) && locators.isNotNullAndNonEmpty(),
            driver -> {
                if (locators.isSingle()) {
                    return getRootElementByInvokedElement(data, locators.first()).apply(driver);
                }

                var current = data;
                var message = "";
                for (var shadowLocator : locators) {
                    if (isNull(shadowLocator)) {
                        break;
                    }

                    current = getRootElementByInvokedElement(current, shadowLocator).apply(driver);
                    if (isNullWebElement(current)) {
                        break;
                    }

                    message += current.message;
                }

                return replaceMessage(current, message);
            },
            SeleniumDataConstants.NULL_ELEMENT
        );
    }

    static Function<Data<WebElement>, DriverFunction<WebElement>> getShadowRootElementFunction(LazyLocatorList locators) {
        return isNotNull(locators) && locators.isNotNullAndNonEmpty() ? (
            data -> getShadowRootElement(data, locators)
        ) : data -> DriverFunctionFactory.getWithMessage(SeleniumCoreConstants.STOCK_ELEMENT, false, SeleniumFormatterConstants.LOCATOR_WAS_NULL);
    }

    static DriverFunction<WebElement> getShadowRootElement(LazyLocatorList locators) {
        final var nameof = "getShadowRootElement";
        return ifDriver(
            nameof,
            isNotNull(locators) && locators.isNotNullAndNonEmpty() && isNotNullLazyData(locators.first()),
            driver -> {
                final var currentBy = locators.first();
                final var current = getShadowRootElement(getLocator(currentBy).object).apply(driver);
                if (isNullWebElement(current)) {
                    return prependMessage(SeleniumDataConstants.NULL_ELEMENT, "Current " + CoreFormatterConstants.WAS_NULL + current.message.toString());
                }

                return locators.isMany() ? getShadowRootElement(current, locators.tail()).apply(driver) : current;
            },
            prependMessage(SeleniumDataConstants.NULL_ELEMENT, nameof, "Shadow locators list" + CoreFormatterConstants.WAS_NULL)
        );
    }

    static DriverFunction<WebElement> getShadowRootElement(DriverFunction<WebElement> data, LazyLocatorList locators) {
        return ifDriver(
            "getShadowRootElement",
            areNotNull(data, locators) && locators.isNotNullAndNonEmpty(),
            driver -> {
                var current = data.apply(driver);
                return isNotNullWebElement(current) ? getShadowRootElement(current, locators).apply(driver) : SeleniumDataConstants.NULL_ELEMENT;
            },
            SeleniumDataConstants.NULL_ELEMENT
        );
    }

    static DriverFunction<WebElement> getShadowRootElement(LazyElement element, LazyLocatorList locators) {
        return ifDriver("getShadowRootElement", areNotNull(element, locators) && locators.isNotNullAndNonEmpty(), getShadowRootElement(element.get(), locators), SeleniumDataConstants.NULL_ELEMENT);
    }

    static Function<Data<SearchContext>, Data<WebElement>> getNestedElement(By locator) {
        final var nameof = "getNestedElement: ";
        return isNotNull(locator) ? (context -> {
            if (isNull(context)) {
                return replaceMessage(SeleniumDataConstants.NULL_ELEMENT, nameof, CoreFormatterConstants.PASSED_DATA_WAS_NULL);
            }

            final var nestedElement = getNestedElementsAmount(locator, 1).apply(context);
            if (isInvalidOrFalse(nestedElement)) {
                return replaceMessage(SeleniumDataConstants.NULL_ELEMENT, nameof, "Nested Element" + CoreFormatterConstants.WAS_NULL);
            }

            final var object = nestedElement.object;
            return isNotNull(object) && object.isNotNullAndNonEmpty() ? (
                getElementFromSingle(nestedElement)
            ) : replaceMessage(SeleniumDataConstants.NULL_ELEMENT, nameof, "Nested Element" + CoreFormatterConstants.WAS_NULL + nestedElement.message);
        }) : context -> replaceMessage(SeleniumDataConstants.NULL_ELEMENT, nameof, SeleniumFormatterConstants.LOCATOR_WAS_NULL);
    }

    static Function<Data<SearchContext>, Data<WebElementList>> getNestedElements(By locator) {
        return context -> {
            final var nameof = "getNestedElements";
            final var message = SeleniumFormatter.getNestedElementsErrorMessage(locator, context);
            return isBlank(message) ? (
                replaceName(getElementsCore(getSearchContextOf("Search Context", context), SeleniumLazyLocatorFactory.get(locator)), nameof)
            ) : replaceMessage(SeleniumDataConstants.NULL_LIST, nameof, message);
        };
    }

    static Function<Data<SearchContext>, Data<WebElement>> getNestedElement(LazyLocator locator) {
        final var nameof = "getNestedElement";
        return isNotNullLazyData(locator) ? getNestedElement(getLocator(locator).object) : data -> replaceMessage(SeleniumDataConstants.NULL_ELEMENT, nameof, SeleniumFormatterConstants.LOCATOR_WAS_NULL);
    }

    static Function<Data<SearchContext>, Data<WebElementList>> getNestedElements(LazyLocator locator) {
        final var nameof = "getNestedElements";
        if (isNullLazyLocator(locator)) {
            return context -> replaceMessage(SeleniumDataConstants.NULL_LIST, nameof + "Lazy locator" + CoreFormatterConstants.WAS_NULL);
        }

        final var locatorData = getLocator(locator);
        return isValidNonFalse(locatorData) ? getNestedElements(locatorData.object) : context -> replaceMessage(SeleniumDataConstants.NULL_LIST, nameof + "Locator data" + CoreFormatterConstants.WAS_NULL);
    }

    static Function<Data<SearchContext>, Data<WebElementList>> getNestedElementsAmount(By locator, int count) {
        final var nameof = "getNestedElementsAmount";
        return isNotNull(locator) && BasicPredicates.isNonNegative(count) ? (context -> {
            if (isNull(context)) {
                return replaceMessage(SeleniumDataConstants.NULL_LIST, CoreFormatterConstants.PASSED_DATA_WAS_NULL);
            }

            final var result = getNestedElements(locator).apply(context);
            return getElementsAmountCore(locator, count).apply(result);
        }) : context -> replaceMessage(SeleniumDataConstants.NULL_LIST, nameof + "Locator was null, or count was wrong. Locator: " + locator + ", count: " + count);
    }

    static Function<Data<SearchContext>, Data<Integer>> getCountOfNestedElements(By locator) {
        return isNotNull(locator) ? (
            context -> isNotNull(context) ? getCountOfElements(getNestedElements(locator).apply(context)) : CoreDataConstants.NO_ELEMENTS_FOUND_DATA_FALSE_OR_NULL
        ) : context -> SeleniumDataConstants.NO_ELEMENTS_FOUND;
    }

    private static <T> DriverFunction<T> getShadowNested(Function<LazyLocator, Function<Data<SearchContext>, Data<T>>> getter, LazyLocatorList locators, LazyLocator locator, T defaultValue) {
        final var nameof = "getShadowNested";
        return ifDriver(
            nameof,
            areNotNull(getter, locators, defaultValue) && locators.isNotNullAndNonEmpty() && isNotNullLazyData(locator),
            driver -> isValidNonFalse(switchToDefaultContent().apply(driver)) ? (
                getter.apply(locator).apply(getSearchContext(getShadowRootElement(locators).apply(driver).object))
            ) : DataFactoryFunctions.getInvalidWithNameAndMessage(defaultValue, nameof, "Couldn't switch to default content" + CoreFormatterConstants.END_LINE),
            DataFactoryFunctions.getWithMessage(defaultValue, false, "There were parameter issues" + CoreFormatterConstants.END_LINE)
        );
    }

    static DriverFunction<WebElement> getShadowNestedElement(LazyLocatorList locators, LazyLocator elementLocator) {
        return getShadowNested(Driver::getNestedElement, locators, elementLocator, SeleniumCoreConstants.STOCK_ELEMENT);
    }

    static DriverFunction<WebElementList> getShadowNestedElements(LazyLocatorList locators, LazyLocator elementLocator) {
        return getShadowNested(Driver::getNestedElements, locators, elementLocator, SeleniumCoreConstants.NULL_ELEMENT_LIST);
    }

    static DriverFunction<WebElement> getShadowNestedElement(LazyLocatorList locators, By locator) {
        return getShadowNestedElement(locators, SeleniumLazyLocatorFactory.get(locator));
    }

    static DriverFunction<WebElementList> getShadowNestedElements(LazyLocatorList locators, By locator) {
        return getShadowNestedElements(locators, SeleniumLazyLocatorFactory.get(locator));
    }

    static <T> Data<Boolean> switchTo(
        TargetLocator locator,
        Function<TargetLocator, T> operation,
        BiFunction<Boolean, SwitchResultMessageData<Void>, String> formatter,
        SwitchResultMessageData<Void> messageData
    ) {
        final var nameof = "switchTo";
        if (isNull(locator)) {
            DataFactoryFunctions.getBoolean(false, nameof, formatter.apply(false, messageData));
        }

        var exception = CoreConstants.EXCEPTION;
        try {
            operation.apply(locator);
        } catch (NoSuchFrameException ex) {
            exception = ex;
        }

        var status = CoreUtilities.isNonException(exception);
        return DataFactoryFunctions.getBoolean(status, nameof, formatter.apply(status, messageData), exception);
    }

    static <T, U> Data<Boolean> switchTo(
        T target,
        TargetLocator locator,
        boolean guardCondition,
        BiFunction<TargetLocator, T, U> operation,
        BiFunction<Boolean, SwitchResultMessageData<T>, String> formatter,
        SwitchResultMessageData<T> messageData
    ) {
        final var nameof = "switchTo";
        if (areAnyNull(target, locator) || !guardCondition) {
            DataFactoryFunctions.getInvalidBooleanWithNameAndMessage(nameof, formatter.apply(false, messageData));
        }

        var exception = CoreConstants.EXCEPTION;
        try {
            operation.apply(locator, target);
        } catch (NoSuchFrameException ex) {
            exception = ex;
        }

        var status = CoreUtilities.isNonException(exception);
        return DataFactoryFunctions.getBoolean(status, nameof, formatter.apply(status, messageData), exception);
    }

    static <T, U> Data<Boolean> switchTo(T target, TargetLocator locator, BiFunction<TargetLocator, T, U> operation, String type, String nameof) {
        return switchTo(target, locator, true, operation, SeleniumFormatter::getSwitchToMessage, new SwitchResultMessageData<T>(target, type, nameof));
    }

    static <T, U> Function<TargetLocator, Data<Boolean>> switchTo(T target, BiFunction<TargetLocator, T, U> operation, String type, String nameof) {
        return locator -> switchTo(target, locator, operation, type, nameof);
    }

    static Function<TargetLocator, Data<Boolean>> switchToFrame(Data<WebElement> data) {
        final var nameof = "switchToFrame(Data<WebElement> data): ";
        return target -> ifData(
            nameof,
            NullableFunctions::isNotNull,
            target,
            switchTo(data.object, TargetLocator::frame, "frame", nameof),
            DataFactoryFunctions.getBoolean(false, nameof, "Couldn't attempt, data was null or false" + CoreFormatterConstants.END_LINE)
        );
    }

    static Function<TargetLocator, Data<Boolean>> switchToFrameSingleList(Data<WebElementList> data) {
        final var nameof = "switchToFrame(Data<WebElement> data): ";
        if (isInvalidOrFalse(data)) {
            return SeleniumDataConstants.SWITCH_TO_NEGATIVE;
        }
        final var list = data.object;
        final var element = DataFactoryFunctions.getWithNameAndMessage(list.first(), list.isSingle(), nameof, data.message.message);
        return isValidNonFalse(data) ? switchToFrame(element) : SeleniumDataConstants.SWITCH_TO_NEGATIVE;
    }

    static DriverFunction<Boolean> switchToFrame(DriverFunction<WebElement> data) {
        final var nameof = "switchToFrame";
        return ifDriver(
            nameof,
            isNotNull(data),
            driver -> switchToFrame(data.apply(driver)).apply(driver.switchTo()),
            DataFactoryFunctions.getBoolean(false, nameof, "Data parameter " + CoreFormatterConstants.WAS_NULL)
        );
    }

    static DriverFunction<Boolean> switchToFrame(By locator, Function<By, DriverFunction<WebElement>> getter) {
        final var nameof = "switchToFrame";
        return ifDriver(
            nameof,
            areNotNull(locator, getter),
            switchToFrame(getter.apply(locator)),
            replaceMessage(CoreDataConstants.NULL_BOOLEAN, nameof, "Couldn't attempt switchToFrame.")
        );
    }

    static DriverFunction<Boolean> switchToFrame(Data<By> locator, Function<By, DriverFunction<WebElement>> getter) {
        final var nameof = "switchToFrame";
        return ifDriver(
            nameof,
            isValidNonFalse(locator) && isNotNull(getter),
            switchToFrame(getter.apply(locator.object)),
            replaceMessage(CoreDataConstants.NULL_BOOLEAN, nameof, "Couldn't attempt switchToFrame.")
        );
    }

    static DriverFunction<Boolean> switchToFrame(By locator) {
        return switchToFrame(locator, Driver::getElement);
    }

    static DriverFunction<Boolean> switchToFrame(LazyLocator locator) {
        return switchToFrame(getLocator(locator), Driver::getElement);
    }

    static DriverFunction<Boolean> switchToFrame(LazyLocatorList locator) {
        final var nameof = "switchToFrame";
        return ifDriver(
            nameof,
            isNotNull(locator) && locator.isSingle(),
            switchToFrame(getLocator(locator.first()), Driver::getElement),
            replaceMessage(CoreDataConstants.NULL_BOOLEAN, nameof, "Couldn't attempt switchToFrame.")
        );
    }

    static DriverFunction<Boolean> switchToFrameFromSingle(LazyLocatorList locators) {
        final var nameof = "switchToFrameFromSingle";
        return ifDriver(
            nameof,
            isNotNull(locators) && locators.isSingle(),
            switchToFrame(locators.first()),
            replaceMessage(CoreDataConstants.NULL_BOOLEAN, nameof, "Couldn't attempt switchToFrame. Non-singular list used in function")
        );
    }

    static DriverFunction<Boolean> switchToFrame(int target) {
        return driver -> switchTo(
            target,
            driver.switchTo(),
            BasicPredicates.isNonNegative(target),
            TargetLocator::frame,
            SeleniumFormatter::getSwitchToMessage,
            new SwitchResultMessageData<Integer>(target, "frame", "switchToFrame(int frameLocator): ")
        );
    }

    static DriverFunction<Boolean> switchToWindow(String target) {
        return driver -> switchTo(
            target,
            driver.switchTo(),
            isNotBlank(target),
            TargetLocator::frame,
            SeleniumFormatter::getSwitchToMessage,
            new SwitchResultMessageData<String>(target, "window", "switchToWindow(String target): ")
        );
    }

    static DriverFunction<Boolean> switchToParentFrame() {
        return driver -> switchTo(driver.switchTo(), TargetLocator::parentFrame, SeleniumFormatter::getSwitchToMessage, new SwitchResultMessageData<Void>(null, "parent frame", "switchToParentFrame: "));
    }

    static DriverFunction<Boolean> switchToAlert() {
        return driver -> switchTo(driver.switchTo(), TargetLocator::alert, SeleniumFormatter::getSwitchToMessage, new SwitchResultMessageData<Void>(null, "alert.", "switchToAlert: "));
    }

    static DriverFunction<Boolean> switchToDefaultContent() {
        return driver -> switchTo(driver.switchTo(), TargetLocator::defaultContent, SeleniumFormatter::getSwitchToMessage, new SwitchResultMessageData<Void>(null, "default content.", "switchToDefaultContent: "));
    }

    static <T> DriverFunction<T> switchToDefaultContentWith(DriverFunction<T> action) {
        return SeleniumExecutor.execute(switchToDefaultContent(), action);
    }


    static DriverFunction<Boolean> switchToNestedFrame(LazyLocatorList locators) {
        return ifDriver(
            "switchToNestedFrame",
            isNotNull(locators) && locators.isNotNullAndNonEmpty(),
            driver -> {
                if (isInvalidOrFalse(switchToDefaultContent().apply(driver))) {
                    return replaceMessage(CoreDataConstants.NULL_BOOLEAN, "Couldn't switch to default content.");
                }

                final var length = locators.size();
                var message = "";
                var index = 0;
                LazyLocator locator;
                Data<?> data;
                for(; index < length; ++index) {
                    locator = locators.get(index);
                    message += index + ".: ";
                    if (isNull(locator)) {
                        message += (SeleniumFormatterConstants.LOCATOR_WAS_NULL);
                        break;
                    }

                    data = switchToFrame(Driver.getElement(locator)).apply(driver);
                    message += data.message;
                    if (isValidNonFalse(data)) {
                        break;
                    }
                }

                return DataFactoryFunctions.getBoolean(index == length, message);
            },
            replaceMessage(CoreDataConstants.NULL_BOOLEAN, "Locators were empty.")
        );
    }

    static DriverFunction<WebElementList> getFrameNestedElements(LazyLocatorList locators) {
        return ifDriver(
            "getFrameNestedElements",
            isNotNull(locators) && locators.isNotNullAndNonEmpty(),
            driver -> {
                if (locators.isSingle()) {
                    return Driver.getElements(locators.first()).apply(driver);
                }

                final var data = switchToNestedFrame(locators.initials()).apply(driver);
                return isValidNonFalse(data) ? Driver.getElements(locators.last()).apply(driver) : SeleniumDataConstants.NULL_LIST;
            },
            SeleniumDataConstants.NULL_LIST
        );
    }

    static DriverFunction<WebElement> getFrameNestedElement(LazyLocatorList locators, Function<By, DriverFunction<WebElement>> getter) {
        return ifDriver(
            "getFrameNestedElement",
            areNotNull(getter, locators) && locators.isMany() && !locators.hasMoreThan(2),
            driver -> {
                final var frameData = locators.first();
                if (isNullLazyLocator(frameData)) {
                    return SeleniumDataConstants.NULL_ELEMENT;
                }

                final var frameLocator = getLocator(frameData).object;
                if (isInvalidOrFalse(getter.apply(frameLocator).apply(driver))) {
                    return SeleniumDataConstants.NULL_ELEMENT;
                }

                final var elementData = locators.last();
                if (isNullLazyLocator(elementData)) {
                    return SeleniumDataConstants.NULL_ELEMENT;
                }

                final var elementLocator = getLocator(elementData).object;
                if (isInvalidOrFalse(getter.apply(frameLocator).apply(driver))) {
                    return SeleniumDataConstants.NULL_ELEMENT;
                }

                final var data = switchToFrame(getter.apply(frameLocator).apply(driver)).apply(driver.switchTo());
                return isValidNonFalse(data) ? getter.apply(elementLocator).apply(driver) : SeleniumDataConstants.NULL_ELEMENT;
            },
            SeleniumDataConstants.NULL_ELEMENT
        );
    }

    static <T, U> DriverFunction<U> getWithLocator(GetWithDriverData<DecoratedList<T>, T, T, U> data) {
        return DriverFunctionFactory.getFunction(ifDependency("getWithLocator", areNotNull(data.locatorGetter, data.getter), data.getter.apply(data.locatorGetter.apply(data.locators)), data.guardData));
    }

    static <T> DriverFunction<T> getWithLazyLocator(GetWithDriverData<LazyLocatorList, LazyLocator, By, T> data) {
        return DriverFunctionFactory.getFunction(ifDependency("getWithLazyLocator", areNotNull(data.locatorGetter, data.getter), data.getter.apply(getLocator(data.locatorGetter.apply(data.locators)).object), data.guardData));
    }

    static <WE, BY, BYY extends By, W extends DecoratedList<BY>> DriverFunction<WE> getFromSingle(
        Function<GetWithDriverData<W, BY, BYY, WE>, DriverFunction<WE>> getter,
        GetWithDriverData<W, BY, BYY, WE> data,
        String nameof
    ) {
        return ifDriver(nameof, data.locators.isSingle(), getter.apply(data), data.guardData);
    }

    static DriverFunction<WebElement> getShadowNestedElement(LazyLocatorList locators) {
        if (isNull(locators) || !locators.hasAtleast(2)) {
            return DriverFunctionFactory.get(prependMessage(SeleniumDataConstants.NULL_ELEMENT, "Lazy Locator list doesn't have enough items" + CoreFormatterConstants.END_LINE));
        }

        final var start = locators.first();
        final var tail = locators.tail();
        return isNotNullLazyData(start) && isNotNull(tail) ? (
            getShadowNestedElement(tail, start)
        ) : DriverFunctionFactory.get(prependMessage(SeleniumDataConstants.NULL_ELEMENT, "Lazy locator item issues" + CoreFormatterConstants.END_LINE));
    }

    static DriverFunction<WebElement> getElementFromSingle(LazyLocatorList locator) {
        return getFromSingle(
            Driver::getWithLazyLocator,
            new GetWithDriverData<LazyLocatorList, LazyLocator, By, WebElement>(locator, LazyLocatorList::first, Driver::getElement, SeleniumDataConstants.NULL_ELEMENT),
            "getElementFromSingle"
        );
    }

    static DriverFunction<WebElement> getRootElementFromSingle(LazyLocatorList locator) {
        return getFromSingle(
            Driver::getWithLazyLocator,
            new GetWithDriverData<LazyLocatorList, LazyLocator, By, WebElement>(locator, LazyLocatorList::first, Driver::getShadowRootElement, SeleniumDataConstants.NULL_ELEMENT),
            "getRootElementFromSingle"
        );
    }

    static DriverFunction<WebElement> getNestedElement(LazyLocatorList locators) {
        final var nameof = "getNestedElement";
        return ifDriver(
            nameof,
            isNotNull(locators) && locators.hasAtleast(2) && isNotNullLazyData(locators.first()),
            driver -> {
                if (isInvalidOrFalse(switchToDefaultContent().apply(driver))) {
                    return DataFactoryFunctions.getWithNameAndMessage(SeleniumCoreConstants.STOCK_ELEMENT, false, nameof, "Driver was null or couldn't switch to default content" + CoreFormatterConstants.END_LINE);
                }

                var locator = locators.first();
                var data = Driver.getElement(locator).apply(driver);
                if (isNullWebElement(data)) {
                    return DataFactoryFunctions.getWithNameAndMessage(SeleniumCoreConstants.STOCK_ELEMENT, false, nameof, data.message.toString());
                }

                final var sublist = locators.tail();
                final var length = sublist.size();
                var index = 0;
                for(; index < length; ++index) {
                    locator = sublist.get(index);
                    if (isNullLazyLocator(locator)) {
                        break;
                    }

                    data = getNestedElement(locator).apply(getSearchContext(data.object));
                    if (isNullWebElement(data)) {
                        break;
                    }
                }

                return (index == length) ? data : appendMessage(SeleniumDataConstants.NULL_ELEMENT, nameof);
            },
            SeleniumDataConstants.NULL_ELEMENT
        );
    }

    static <T> DriverFunction<T> getFrameNested(Function<LazyLocator, DriverFunction<T>> getter, LazyLocatorList locators, Data<T> defaultValue, String nameof) {
        return ifDriver(
            nameof,
            areNotNull(getter, locators, defaultValue, nameof) && locators.hasAtleast(2) && isNotNullLazyData(locators.first()),
            driver -> {
                if (isInvalidOrFalse(switchToDefaultContent().apply(driver))) {
                    return replaceMessage(defaultValue, nameof, "Driver was null or couldn't switch to default content" + CoreFormatterConstants.END_LINE);
                }

                final var function = ElementFinderConstants.frameNestedStrategyMap.get("" + locators.hasMoreThan(2));
                if (isNull(function)) {
                    return replaceMessage(defaultValue, nameof, "Function from Frame nested Strategy map " + CoreFormatterConstants.WAS_NULL);
                }

                final var data = function.apply(locators.initials()).apply(driver);
                if (isInvalidOrFalse(data)) {
                    return replaceMessage(defaultValue, nameof, "Couldn't switch into frame. By list length: " + locators.size());
                }

                final var locator = locators.last();
                return isNotNull(locator) ? getter.apply(locator).apply(driver) : replaceMessage(defaultValue, "Locator was null" + CoreFormatterConstants.END_LINE);
            },
            replaceMessage(defaultValue, nameof, "Couldn't switch into frame. Guard.")
        );
    }

    static DriverFunction<WebElement> getFrameNestedElement(LazyLocatorList locators) {
        return getFrameNested(Driver::getElement, locators, SeleniumDataConstants.NULL_ELEMENT, "getFrameNestedElement");
    }

    static DriverFunction<WebElementList> getFrameNestedElementsFromLast(LazyLocatorList locators) {
        return getFrameNested(Driver::getElements, locators, SeleniumDataConstants.NULL_LIST, "getFrameNestedElementsFromLast");
    }

    static DriverFunction<WebElementList> getNestedElementsFromLast(LazyLocatorList locators) {
        return ifDriver(
            "getNestedElementsFromLast",
            isNotNull(locators) && locators.hasAtleast(2) && isNotNullLazyData(locators.first()), //TODO: Move to formatter, and do blank check instead
            driver -> {
                if (isInvalidOrFalse(switchToDefaultContent().apply(driver))) {
                    return replaceMessage(SeleniumDataConstants.NULL_LIST, "Couldn't switch to default content" + CoreFormatterConstants.END_LINE);
                }

                final var function = ElementFinderConstants.frameAmountStrategyMap.get("" + locators.hasMoreThan(2));
                final var element = function.apply(locators.initials()).apply(driver);
                if (isInvalidOrFalse(element)) {
                    return replaceMessage(SeleniumDataConstants.NULL_LIST, "Failed, nested com.github.karsaii.framework.selenium.element issue - sublist length(" + locators.initials().size() + "): " + element.message);
                }

                final var locator = locators.last();
                if (isNullLazyLocator(locator)) {
                    return replaceMessage(SeleniumDataConstants.NULL_LIST, "Locator was null" + CoreFormatterConstants.END_LINE);
                }

                final var data = getNestedElements(locator).apply(getSearchContext(element.object));
                final var nested = locators.hasAtleast(2);
                return isValidNonFalse(data) ? data : prependMessage(data, (nested ? "Nested " : "") + "Elements weren't found by locator: " + locator);
            },
            replaceMessage(SeleniumDataConstants.NULL_LIST, "Locators list was null or empty.")
        );
    }

    static DriverFunction<WebElementList> getShadowNestedElementsFromLast(LazyLocatorList locators) {
        return ifDriver(
            "getShadowNestedElementsFromLast",
            isNotNull(locators) && locators.isNotNullAndNonEmpty() && isNotNullLazyData(locators.last()),
            getShadowNestedElements(locators.initials(), locators.last()),
            replaceMessage(SeleniumDataConstants.NULL_LIST, "Locators were null" + CoreFormatterConstants.END_LINE)
        );
    }


    static <LazyElement extends AbstractLazyResult<LazyFilteredElementParameters>> DriverFunction<ExternalElementData> getLazyElementByExternal(LazyElement element, ExternalSelectorData<WebDriver> externalData, Map<String, DecoratedList<SelectorKeySpecificityData>> typeKeys) {
        //TODO: Validate all the parameters and provide overloads for defaults.
        final var nameof = "getLazyElementByExternal";
        final var defaultValue = SeleniumDataConstants.NULL_EXTERNAL_ELEMENT;
        return ifDriver(
            nameof,
            FrameworkCoreFormatter.getExternalSelectorDataErrorMessage(element, externalData, nameof),
            driver -> {
                final var types = new ArrayList<>(typeKeys.keySet());
                LazyLocator locator;
                var selector = externalData.defaultSelector;
                var parameterKey = "";
                var selectorType = externalData.selectorType;
                var currentElement = SeleniumDataConstants.NULL_ELEMENT;

                final var failedSelectors = DecoratedListFactory.getWith(String.class);
                final var length = externalData.limit;
                var index = 0;
                var message = replaceMessage(CoreDataConstants.NULL_STRING, nameof, "");
                var lep = LazyIndexedElementFactory.getWithFilterParametersAndLocator(false, 0, SeleniumLazyLocatorFactory.getWithDefaults());
                var getSelector = externalData.getSelector;
                for(; index < length; ++index) {
                    switchToDefaultContent().apply(driver);
                    selector = getSelector.apply(externalData.preferredProperties, failedSelectors.list).apply(driver);
                    if (isInvalidOrFalse(selector)) {
                        continue;
                    }

                    if (isBlank(selector.object)) {
                        appendMessage(message, "Empty selector returned, attempt: " + index + CoreFormatterConstants.END_LINE);
                        continue;
                    }

                    locator = SeleniumLazyLocatorFactory.get(selector.object, selectorType);
                    parameterKey = FrameworkCoreFormatter.getUniqueGeneratedName(selectorType, SeleniumCoreConstants.ATOMIC_COUNT);
                    lep = LazyIndexedElementFactory.getWithFilterParametersAndLocator(false, 0, locator);
                    currentElement = ElementFilterFunctions.getElement(lep.lazyLocators, ElementFinderConstants.singleGetterMap, SingleGetter.DEFAULT).apply(driver);
                    if (isNullWebElement(currentElement)) {
                        break;
                    }

                    failedSelectors.addNullSafe(selector.object);
                }

                element.parameters.putIfAbsent(parameterKey, lep);
                final var update = ElementRepository.updateTypeKeys(lep.lazyLocators, typeKeys, types, parameterKey);
                return isNotNullWebElement(currentElement) ? (
                    DataFactoryFunctions.getWithNameAndMessage(new ExternalElementData(typeKeys, currentElement), true, nameof, "External function yielded an com.github.karsaii.framework.selenium.element" + CoreFormatterConstants.END_LINE)
                ) : replaceMessage(defaultValue, nameof, "All(\"" + length + "\") approaches were tried" + CoreFormatterConstants.END_LINE + currentElement.message.toString());
            },
            defaultValue
        );
    }

    static <T> Data<Integer> getNextCachedKey(Map<String, T> parameterMap, Iterator<String> getOrder, Map<String, DecoratedList<SelectorKeySpecificityData>> typeKeys, int parameterIndex) {
        final var nameof = "getNextCachedKey";
        var type = typeKeys.getOrDefault(getOrder.next(), null);
        if (isNull(type)) {
            return replaceMessage(CoreDataConstants.NULL_INTEGER, nameof, "Type" + CoreFormatterConstants.WAS_NULL);
        }

        var index = parameterIndex;
        if (!type.hasIndex(index)) {
            if (!getOrder.hasNext()) {
                return replaceMessage(CoreDataConstants.NULL_INTEGER, nameof, "GetOrder doesn't have more entries" + CoreFormatterConstants.END_LINE);
            }

            type = typeKeys.get(getOrder.next());
            index = 0;
        }
        final var key = type.get(index).selectorKey;
        return (isNotNull(key) && parameterMap.containsKey(key) ? (
            DataFactoryFunctions.getWithMessage(index, true, key)
        ) : replaceMessage(CoreDataConstants.NULL_INTEGER, nameof, "The parameter map didn't contain an indexed com.github.karsaii.framework.core.selector-type it should have" + CoreFormatterConstants.END_LINE));
    }

    static Data<Integer> getNextKey(DecoratedList<String> keys, int parameterIndex) {
        return isNotNull(keys) && BasicPredicates.isNonNegative(parameterIndex) && keys.hasIndex(parameterIndex) ? (
            DataFactoryFunctions.getWithMessage(0, true, keys.get(parameterIndex))
        ) : replaceMessage(CoreDataConstants.NULL_INTEGER, "getNextKey", "The parameter map didn't contain an indexed com.github.karsaii.framework.core.selector-type it should have" + CoreFormatterConstants.END_LINE);
    }

    static <T> DriverFunction<WebElement> getLazyElement(LazyElementWithOptionsData data) {
        final var nameof = "getLazyElement";
        return ifDriver(
            nameof,
            FrameworkCoreFormatter.getLazyResultWithOptionsMessage(data, nameof),
            driver -> {
                final var getOrder = data.getOrder.iterator();
                final var dataElement = data.element;
                final var name = dataElement.name;
                final var cached2 = ElementRepository.containsElement(dataElement.name);
                var currentElement = SeleniumDataConstants.NULL_ELEMENT;
                if (isInvalidOrFalse(cached2)) {
                    return currentElement;
                }

                final var isCached = cached2.object;
                final var getResult = ElementRepository.getIfContains(dataElement);
                final var localElement = isCached ? getResult.object.element : dataElement;
                final var typeKeys = isCached ? getResult.object.typeKeys : ElementRepository.getInitializedTypeKeysMap();
                final var parameterMap = localElement.parameters;
                final var parameterKeys = DecoratedListFactory.getWith(parameterMap.keySet());
                final var types = DecoratedListFactory.getWith(typeKeys.keySet());
                var message = CoreDataConstants.NULL_STRING;
                var parameterIndex = 0;
                var index = 0;
                var switchData = CoreDataConstants.NULL_BOOLEAN;
                final var length = data.internalData.limit;
                for (; isNullWebElement(currentElement) && (index < length); ++index, ++parameterIndex) {
                    switchData = switchToDefaultContent().apply(driver);
                    if (isInvalidOrFalse(switchData)) {
                        return replaceMessage(currentElement, nameof, switchData.message.toString());
                    }

                    var keyData = isCached ? getNextCachedKey(parameterMap, getOrder, typeKeys, parameterIndex) : getNextKey(parameterKeys, parameterIndex);
                    if (isInvalidOrFalse(keyData)) {
                        return replaceMessage(currentElement, nameof, "Parameter key wasn't found in " + (isCached ? "cached" : "") + " keys" + CoreFormatterConstants.END_LINE);
                    }
                    parameterIndex = isCached ? keyData.object : parameterIndex;
                    var key = keyData.message.message;
                    var parameters = parameterMap.get(key);
                    if (isNull(parameters) || parameters.lazyLocators.isNullOrEmpty()) {
                        continue;
                    }

                    var locators = parameters.lazyLocators;
                    var update = ElementRepository.updateTypeKeys(name, locators, typeKeys, types, key);
                    if (isInvalidOrFalse(update)) {
                        continue;
                    }

                    var getter = parameters.getter;
                    var indexData = parameters.elementFilterData;
                    currentElement = (
                        indexData.isFiltered ? (
                            indexData.apply(ElementFilterParametersFactory.getWithManyGetterAndDefaultGetterMap(locators, getter))
                        ) : ElementFilterFunctions.getElement(locators, ElementFinderConstants.singleGetterMap, SingleGetter.getValueOf(getter))
                    ).apply(driver);
                    message = appendMessage(message, currentElement.message.toString());
                    message = appendMessage(message, Adjuster.adjustProbability(parameters, typeKeys, key, isValidNonFalse(currentElement), data.probabilityData).message.toString());

                }

                if (SeleniumUtilities.isNullWebElement(currentElement)) {
                    currentElement = SeleniumDataConstants.NULL_ELEMENT;
                }

                final var externalData = data.externalData;
                return ElementRepository.cacheValidLazyElement(
                    dataElement,
                    DataFactoryFunctions.getWithMessage(new ExternalElementData(typeKeys, currentElement), isValidNonFalse(currentElement), message.message.toString()),
                    isBlank(FrameworkCoreFormatter.getExternalSelectorDataMessage(externalData)) ? getLazyElementByExternal(dataElement, externalData, typeKeys).apply(driver) : SeleniumDataConstants.NULL_EXTERNAL_ELEMENT
                );
            },
            SeleniumDataConstants.NULL_ELEMENT
        );
    }

    static DriverFunction<WebElement> getLazyElement(LazyElement element, InternalSelectorData internalData, ExternalSeleniumSelectorData externalData, DecoratedList<String> getOrder, ProbabilityData probabilityData) {
        return getLazyElement(new LazyElementWithOptionsData(element, internalData, externalData, getOrder, probabilityData));
    }

    static DriverFunction<WebElement> getLazyElement(LazyElement element, InternalSelectorData internalData, ExternalSeleniumSelectorData externalData, DecoratedList<String> getOrder) {
        return getLazyElement(new LazyElementWithOptionsData(element, internalData, externalData, getOrder, AdjusterConstants.PROBABILITY_DATA));
    }

    static DriverFunction<WebElement> getLazyElement(LazyElement element, InternalSelectorData internalData, ExternalSeleniumSelectorData externalData, ProbabilityData probabilityData) {
        return getLazyElement(new LazyElementWithOptionsData(element, internalData, externalData, SeleniumGetOrderConstants.DEFAULT, probabilityData));
    }

    static DriverFunction<WebElement> getLazyElement(LazyElement element, InternalSelectorData internalData, ExternalSeleniumSelectorData externalData) {
        return getLazyElement(new LazyElementWithOptionsData(element, internalData, externalData, SeleniumGetOrderConstants.DEFAULT, AdjusterConstants.PROBABILITY_DATA));
    }

    static DriverFunction<WebElement> getLazyElement(LazyElement element) {
        return getLazyElement(LazyElementWithOptionsDataFactory.getWithSpecificLazyElement(element));
    }

    private static Data<Boolean> quitDriverCore(WebDriver driver) {
        var data = CoreDataConstants.NULL_BOOLEAN;
        try {
            driver.quit();
            data = DataFactoryFunctions.getBoolean(true, "Driver quit successfully" + CoreFormatterConstants.END_LINE);
        } catch (NullPointerException ex) {
            final var exMessage = ex.getMessage();
            data = DataFactoryFunctions.getBoolean(false, "Exception occurred while closing Driver. Exception:" + ex.getClass() + " Message: " +  exMessage, ex, exMessage);
        }

        return data;
    }

    private static Data<Boolean> navigateCore(WebDriver driver, String url) {
        var data = CoreDataConstants.NULL_BOOLEAN;
        try {
            driver.get(url);
            data = DataFactoryFunctions.getBoolean(true, "Driver navigated successfully to \"" + url + "\"" + CoreFormatterConstants.END_LINE);
        } catch (NullPointerException ex) {
            final var exMessage = ex.getMessage();
            data = DataFactoryFunctions.getBoolean(false, "Exception occurred while navigating to \"" + url + "\". Exception:" + ex.getClass() + " Message: " +  exMessage, ex, exMessage);
        }

        return data;
    }

    private static DriverFunction<Boolean> navigateCore(String url) {
        return driver -> navigateCore(driver, url);
    }

    static DriverFunction<Boolean> quitDriver() {
        return ifDriver("quitDriver", Driver::quitDriverCore, SeleniumDataConstants.DRIVER_WAS_NULL);
    }

    static DriverFunction<Boolean> navigate(String url) {
        return ifDriver("navigate", isBlankMessageWithName(url, "url"), navigateCore(url), SeleniumDataConstants.DRIVER_WAS_NULL);
    }
}
