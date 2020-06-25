package com.github.karsaii.framework.selenium.namespaces.validators;

import com.github.karsaii.core.extensions.namespaces.CoreUtilities;
import com.github.karsaii.core.namespaces.DataFactoryFunctions;
import com.github.karsaii.core.namespaces.validators.CoreFormatter;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.core.constants.validators.CoreFormatterConstants;
import com.github.karsaii.framework.core.namespaces.validators.FrameworkCoreFormatter;
import com.github.karsaii.framework.selenium.constants.validators.SeleniumFormatterConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.github.karsaii.framework.selenium.abstracts.ElementValueParameters;
import com.github.karsaii.framework.selenium.constants.SeleniumCoreConstants;
import com.github.karsaii.framework.selenium.constants.SeleniumDataConstants;
import com.github.karsaii.framework.selenium.enums.ManyGetter;
import com.github.karsaii.framework.selenium.enums.SingleGetter;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.github.karsaii.framework.core.namespaces.extensions.boilers.LazyLocatorList;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.WebElementList;
import com.github.karsaii.framework.selenium.records.SwitchResultMessageData;
import com.github.karsaii.framework.selenium.records.element.is.ElementFormatData;
import com.github.karsaii.framework.selenium.records.element.is.ElementParameterizedValueParameters;
import com.github.karsaii.framework.selenium.records.lazy.LazyElement;
import com.github.karsaii.framework.core.records.lazy.LazyLocator;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static com.github.karsaii.framework.selenium.namespaces.utilities.SeleniumUtilities.getLocator;

public interface SeleniumFormatter {
    static Data<String> getIsValuesMessage(Map<String, String> map, Data<String> object, String expected, Boolean keyCondition, String descriptor, String conditionDescriptor) {
        final var valuesMessage = "Values ";
        final var defaultMessage = "getIsValuesMessage: ";
        final var errorMessage = (
            CoreFormatter.isBlankMessageWithName(conditionDescriptor, valuesMessage + "conditionDescriptor") +
            CoreFormatter.isNullMessageWithName(descriptor, valuesMessage + "descriptor") +
            CoreFormatter.isNullMessageWithName(keyCondition, valuesMessage + "boolean") +
            CoreFormatter.isNullMessageWithName(map, valuesMessage + " map") +
            CoreFormatter.isNullMessageWithName(expected, valuesMessage + "expected value") +
            CoreFormatter.isInvalidOrFalseMessageWithName(object, valuesMessage + "object")
        );

        if (isNotBlank(errorMessage)) {
            return DataFactoryFunctions.getWithMessage(CoreFormatterConstants.EMPTY, false, defaultMessage + " " + conditionDescriptor + CoreFormatterConstants.PARAMETER_ISSUES + CoreFormatterConstants.DEFAULT_ERROR_MESSAGE_STRING + errorMessage);
        }

        final var key = String.valueOf(keyCondition);
        final var localObject = map.getOrDefault(key, CoreFormatterConstants.EMPTY);
        final var status = isNotBlank(localObject);
        final var message = defaultMessage + (descriptor + "(" + object + ") " + localObject + conditionDescriptor + " expected (\"" + expected + "\")" + CoreFormatterConstants.END_LINE);
        return DataFactoryFunctions.getWithMessage(message, status, message);
    }

    static String isValidElementFormatData(ElementFormatData<?> parameters) {
        final var baseName = "Element Function Parameters";
        var message = CoreFormatter.isNullMessageWithName(parameters, baseName);
        if (isBlank(message)) {
            message += (
                CoreFormatter.isNullMessageWithName(parameters.formatter, baseName + " Formatter") +
                CoreFormatter.isBlankMessageWithName(parameters.conditionName, baseName + " Name") +
                CoreFormatter.isBlankMessageWithName(parameters.descriptor, baseName + " Descriptor")
            );
        }

        return CoreFormatter.getNamedErrorMessageOrEmpty("isValidElementFormatData: ", message);
    }

    static String isValidElementValueParametersMessage(ElementValueParameters<?, ?> parameters) {
        final var baseName = "Element Value Parameters";
        var message = CoreFormatter.isNullMessageWithName(parameters, baseName);
        if (isBlank(message)) {
            message += (
                CoreFormatter.isNullMessageWithName(parameters.function, baseName + " Function") +
                CoreFormatter.isNullMessageWithName(parameters.handler, baseName + " Handler") +
                isValidElementFormatData(parameters.formatData)
            );
        }

        return CoreFormatter.getNamedErrorMessageOrEmpty("isValidElementValueParametersMessage: ", message);
    }

    static String isValidElementParameterizedValueParametersMessage(ElementParameterizedValueParameters<?> parameters) {
        final var baseName = "Element Parameterized Value Parameters";
        var message = CoreFormatter.isNullMessageWithName(parameters, baseName);
        if (isBlank(message)) {
            message += (
                CoreFormatter.isNullMessageWithName(parameters.function, baseName + " Function") +
                CoreFormatter.isNullMessageWithName(parameters.handler, baseName + " Handler") +
                isValidElementFormatData(parameters.formatData)
            );
        }

        return CoreFormatter.getNamedErrorMessageOrEmpty("isValidElementParameterizedValueParametersMessage: ", message);
    }

    static String getScrollIntoViewMessage(String message, boolean status) {
        return CoreFormatter.getExecuteFragment(status) + " Scroll into view: " + message + CoreFormatterConstants.END_LINE;
    }

    static String getScriptExecutionMessage(boolean status) {
        return CoreFormatter.getExecuteFragment(status) + " script" + CoreFormatterConstants.END_LINE;
    }

    static String getLocatorErrorMessage(By locator) {
        return CoreFormatter.isNullMessageWithName(locator, "By locator");
    }

    static String getNestedElementsErrorMessage(By locator, Data<SearchContext> context) {
        return getLocatorErrorMessage(locator) + CoreFormatter.isInvalidOrFalseMessageWithName(context, "Search Context");
    }

    static String getActionMessage(String message, String dataMessage, boolean status) {
        return CoreFormatterConstants.ELEMENT + CoreFormatter.getOptionMessage(status) + " " + message + CoreFormatterConstants.END_LINE + dataMessage + CoreFormatterConstants.END_LINE;
    }

    static String getLocatorAndGetterErrorMessage(By locator, SingleGetter getter) {
        return getLocatorErrorMessage(locator) + CoreFormatter.isNullMessageWithName(getter, "Getter");
    }

    static String getSendKeysErrorMessage(String message) {
        return isNotBlank(message) ? "getSendKeysErrorMessage: " + CoreFormatterConstants.PARAMETER_ISSUES_LINE + message + "Not sending" + CoreFormatterConstants.END_LINE : CoreFormatterConstants.EMPTY;
    }

    static String getSendKeysNotSendingMessage(By locator, String input, SingleGetter getter) {
        return getSendKeysErrorMessage(getLocatorAndGetterErrorMessage(locator, getter) + CoreFormatter.getInputErrorMessage(input));
    }

    static String getSendKeysNotSendingMessage(By locator, String input) {
        return getSendKeysErrorMessage(getLocatorErrorMessage(locator) + CoreFormatter.getInputErrorMessage(input));
    }

    static String getSendKeysNotSendingMessage(LazyElement data, String input) {
        return getSendKeysErrorMessage(FrameworkCoreFormatter.isNullLazyElementMessage(data) + CoreFormatter.getInputErrorMessage(input));
    }

    static String getShadowRootElementMessage(String message, boolean status) {
        return message + " Root com.github.karsaii.framework.selenium.element " + CoreFormatter.getOptionMessage(status) + "found" + CoreFormatterConstants.END_LINE;
    }

    static String getElementsAmountMessage(By locator, boolean status, int expectedSize, int size) {
        return (status ? expectedSize : (size > 0 ? "Wrong(" + expectedSize + ") amount of" : "No")) + " elements found by: " + locator.toString() + CoreFormatterConstants.END_LINE;
    }

    static String getFindElementsMessage(String locator, int size) {
        return (size > 0 ? size + " amount of" : "No") + " elements found by: " + locator + CoreFormatterConstants.END_LINE;
    }

    static String getNumberOfWindowsEqualToMessage(boolean status, int expected, int count) {
        return "Number of browser windows are " + CoreFormatter.getOptionMessage(status)+ " equal. Actual: " + count + ", Expected: " + expected + CoreFormatterConstants.END_LINE;
    }

    static <T> String getSwitchToMessage(boolean status, SwitchResultMessageData<T> data) {
        final var target = data.target;
        var message = data.nameof + (status ? SeleniumFormatterConstants.SUCCESSFULLY_SWITCHED_TO : SeleniumFormatterConstants.COULDNT_SWITCH_TO) + data.type;

        if (!Objects.isNull(target)) {
            message += "(\"" + target + "\")";
        }

        return message + CoreFormatterConstants.END_LINE;
    }

    static String isElementFunctionMessage(LazyElement element, ElementValueParameters<?, ?> parameters) {
        final var message = FrameworkCoreFormatter.isNullLazyElementMessage(element) + isValidElementValueParametersMessage(parameters);
        return CoreFormatter.getNamedErrorMessageOrEmpty("isElementFunctionMessage: ", message);
    }

    static String isElementFunctionMessage(LazyElement element, ElementParameterizedValueParameters<?> parameters) {
        final var message = FrameworkCoreFormatter.isNullLazyElementMessage(element) + isValidElementParameterizedValueParametersMessage(parameters);
        return CoreFormatter.getNamedErrorMessageOrEmpty("isElementFunctionMessage: ", message);
    }

    static <T> String getManyGetterErrorMessage(Map<ManyGetter, Function<LazyLocatorList, Function<WebDriver, Data<T>>>> getterMap, ManyGetter key) {
        final var nameof = "getManyGetterErrorMessage";
        final var parameterName = "Getter map";
        var message = CoreFormatter.isNullMessageWithName(getterMap, parameterName);
        if (isNotBlank(message)) {
            return nameof + message;
        }

        if (getterMap.isEmpty()) {
            message += parameterName + " was empty" + CoreFormatterConstants.END_LINE;
        } else {
            if (!getterMap.containsKey(key)) {
                message += "Getter was not found in map with " + key.getName();
            }
        }

        return isNotBlank(message) ? nameof + message : CoreFormatterConstants.EMPTY;
    }

    static <T> String getSingleGetterErrorMessage(Map<SingleGetter, Function<LazyLocatorList, Function<WebDriver, Data<T>>>> getterMap, SingleGetter key) {
        final var nameof = "getSingleGetterErrorMessage";
        final var parameterName = "Getter map";
        var message = CoreFormatter.isNullMessageWithName(getterMap, parameterName);
        if (isNotBlank(message)) {
            return nameof + message;
        }

        if (getterMap.isEmpty()) {
            message += parameterName + " was empty" + CoreFormatterConstants.END_LINE;
        } else {
            if (!getterMap.containsKey(key)) {
                message += "Getter was not found in map with " + key.getName();
            }
        }

        return isNotBlank(message) ? nameof + message : CoreFormatterConstants.EMPTY;
    }

    static String isNullLazyDataMessage(LazyLocator locator) {
        final var parameterName = "Lazy locator";
        var message = CoreFormatter.isNullMessageWithName(locator, parameterName);
        if (isBlank(message)) {
            message += (
                CoreFormatter.isNullMessageWithName(locator.locator, parameterName + " value") +
                CoreFormatter.isNullMessageWithName(locator.strategy, parameterName + " strategy")
            );
        }

        if (isBlank(message)) {
            message += CoreFormatter.isNullMessageWithName(getLocator(locator), "Actual locator from locator");
        }

        return isNotBlank(message) ? "isNotNullLazyDataMessage: " + message : CoreFormatterConstants.EMPTY;
    }

    static String getProbabilityAdjustmentMessage(String key, double original, double adjusted, boolean increase, boolean generated, boolean belowThreshold) {
        var message = (increase ? "Increased" : "Reduced") + " probability of com.github.karsaii.framework.core.selector(\"" + original + "\") to \"" + adjusted + "\"" + CoreFormatterConstants.END_LINE;
        if (belowThreshold) {
            message += (generated ? "External" : "Regular") + "com.github.karsaii.framework.core.selector by key(\"" + key + "\") is below threshold(\"" + adjusted + "\"), set to \"0.0\"" + CoreFormatterConstants.END_LINE;
        }

        return message;
    }

    static String getElementAttributeMessage(Data<LazyElement> data, String value, String parameterName) {
        final var name = isBlank(parameterName) ? "Value" : parameterName;
        var message = CoreFormatter.isInvalidOrFalseMessageWithName(data, "Element data");
        if (isBlank(message)) {
            message += (
                FrameworkCoreFormatter.isNullLazyElementMessage(data.object) +
                CoreFormatter.isBlankMessageWithName(value, name)
            );
        }

        return isNotBlank(message) ? "getElementAttributeMessage: " + CoreFormatterConstants.PARAMETER_ISSUES_LINE + message : CoreFormatterConstants.EMPTY;
    }

    static String isNullWebElementMessage(WebElement element) {
        var message = CoreFormatter.isNullMessageWithName(element, "Element");
        if (isBlank(message)) {
            message += (
                CoreFormatter.isEqualMessage(SeleniumCoreConstants.STOCK_ELEMENT, "Null Selenium Element", element, "Element Parameter") +
                CoreFormatter.isEqualMessage(element.getAttribute("id"), "Element ID", SeleniumFormatterConstants.NULL_ELEMENT_ID, "Null Element ID")
            );
        }

        return isNotBlank(message) ? "isNullWebElementMessage: " + CoreFormatterConstants.PARAMETER_ISSUES_LINE + message + CoreFormatterConstants.END_LINE : CoreFormatterConstants.EMPTY;
    }

    static String isNullWebElementDataMessage(Data<WebElement> element) {
        var message = CoreFormatter.isInvalidOrFalseMessage(element);
        if (isBlank(message)) {
            message += (
                CoreFormatter.isEqualMessage(SeleniumDataConstants.NULL_ELEMENT, "Null Selenium Element Data", element, "Element Data Parameter") +
                isNullWebElementMessage(element.object)
            );
        }

        return isNotBlank(message) ? "isNullWebElementMessage: " + CoreFormatterConstants.PARAMETER_ISSUES_LINE + message + CoreFormatterConstants.END_LINE : CoreFormatterConstants.EMPTY;
    }

    static String getElementsParametersMessage(LazyLocatorList locators, Function<LazyLocator, DriverFunction<WebElementList>> getter) {
        return CoreFormatter.getNamedErrorMessageOrEmpty("getElementsParametersMessage: ", CoreFormatter.isNullOrEmptyMessageWithName(locators, "Lazy Locators List") + CoreFormatter.isNullMessageWithName(getter, "Getter"));
    }

    static String getElementsParametersMessage(LazyLocatorList locators) {
        return CoreFormatter.getNamedErrorMessageOrEmpty("getElementsParametersMessage: ", CoreFormatter.isNullOrEmptyMessageWithName(locators, "Lazy Locators List"));
    }
}
