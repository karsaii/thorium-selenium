package com.neathorium.framework.selenium.namespaces;

import com.neathorium.framework.selenium.constants.ExpectedConditionConstants;
import com.neathorium.framework.selenium.constants.validators.SeleniumFormatterConstants;
import com.neathorium.framework.selenium.namespaces.driver.properties.DriverPropertyFunctions;
import com.neathorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.framework.selenium.namespaces.validators.SeleniumFormatter;
import com.neathorium.framework.selenium.records.lazy.LazyElement;
import com.neathorium.core.constants.CoreDataConstants;
import com.neathorium.core.constants.validators.CoreFormatterConstants;
import com.neathorium.core.extensions.namespaces.CoreUtilities;
import com.neathorium.core.extensions.namespaces.predicates.BasicPredicates;
import com.neathorium.core.namespaces.DataFactoryFunctions;
import com.neathorium.core.namespaces.StringUtilities;
import com.neathorium.core.namespaces.predicates.DataPredicates;
import com.neathorium.core.namespaces.validators.CoreFormatter;
import com.neathorium.core.records.Data;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

import static com.neathorium.framework.selenium.namespaces.ExecutionCore.ifDriver;
import static com.neathorium.core.namespaces.predicates.DataPredicates.isInvalidOrFalse;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public interface ExpectedConditions {
    private static Data<Boolean> isValuesDataCore(Data<String> data, String expected, String descriptor, String conditionDescriptor) {
        final var status = data.status;
        final var messageData = SeleniumFormatter.getIsValuesMessage(CoreFormatterConstants.isMessageMap, data, expected, status, descriptor, conditionDescriptor);
        return DataFactoryFunctions.getBoolean(status, messageData.message.formatter.apply(messageData.message.nameof, messageData.message.message));
    }

    private static Data<Boolean> isNumberOfWindowsEqualToCore(Data<Integer> handleData, int expected) {
        final var count = handleData.object;
        final var status = Objects.equals(count, expected);
        return DataFactoryFunctions.getBoolean(status, SeleniumFormatter.getNumberOfWindowsEqualToMessage(status, expected, count));
    }

    private static <T, V> Data<T> is(Data<T> data, T expected, BiFunction<T, T, V> checker) {
        if (isInvalidOrFalse(data) || CoreUtilities.areAnyNull(expected, checker)) {
            return DataFactoryFunctions.getWith(null, false, CoreFormatterConstants.PARAMETER_ISSUES);
        }

        final var object = data.object;
        var status = data.status;
        final var result = status ? object : expected;
        status = status && (Boolean) checker.apply(object, expected);
        return DataFactoryFunctions.getWith(result, status, "Checker with value(\"" + result + "\") against expected(\"" + expected + "\") was " + status + CoreFormatterConstants.END_LINE);
    }

    private static <T, V> Function<Data<T>, Data<T>> is(T expected, BiFunction<T, T, V> checker) {
        return data -> is(data, expected, checker);
    }

    private static Function<Data<Integer>, Data<Boolean>> isNumberOfWindowsEqualToCore(int expected) {
        return handleData -> isNumberOfWindowsEqualToCore(handleData, expected);
    }

    private static <T, V> DriverFunction<T> is(DriverFunction<T> getter, T expected, BiFunction<T, T, V> checker) {
        final var nameof = "is";
        final var negative = DataFactoryFunctions.getInvalidWith(expected, nameof, "expected guard");
        return ifDriver(nameof, CoreUtilities.areNotNull(expected, getter, checker), ExecutionCore.validChain(getter, is(expected, checker), negative), negative);
    }

    static Data<Boolean> isValuesData(String descriptor, String expected, Data<String> data, BiFunction<String, String, Boolean> checker, String conditionDescriptor) {
        final var nameof = "isValuesData";
        return (
            DataPredicates.isValidNonFalse(data) &&
            CoreUtilities.areNotNull(expected, checker) &&
            CoreUtilities.areNotBlank(descriptor, conditionDescriptor)
        ) ? (
            DataFactoryFunctions.replaceName(isValuesDataCore(is(data, expected, checker), expected, descriptor, conditionDescriptor), nameof)
        ) : DataFactoryFunctions.replaceMessage(CoreDataConstants.NULL_BOOLEAN, nameof, CoreFormatterConstants.PARAMETER_ISSUES);
    }

    static DriverFunction<Boolean> isValuesData(String descriptor, String expected, DriverFunction<String> getter, BiFunction<String, String, Boolean> checker, String conditionDescriptor) {
        final var nameof = "isValuesData";
        return ifDriver(
            nameof,
            CoreUtilities.areNotNull(expected, getter, checker) && CoreUtilities.areNotBlank(descriptor, conditionDescriptor),
            driver -> isValuesDataCore(is(getter, expected, checker).apply(driver), expected, descriptor, conditionDescriptor),
            DataFactoryFunctions.replaceMessage(CoreDataConstants.NULL_BOOLEAN, nameof, "Expected was: \"" + expected +"\" and actual was: \"" + getter + CoreFormatterConstants.END_LINE)
        );
    }

    static Data<Boolean> isValuesEqualData(Data<String> data, String expected, String descriptor) {
        return isValuesData(descriptor, expected, data, CoreUtilities::isEqual, "equal");
    }

    static Data<Boolean> isStartsWith(Data<String> data, String expected, String descriptor) {
        return isValuesData(descriptor, expected, data, StringUtilities::startsWithCaseSensitive, " starts with ");
    }

    static Data<Boolean> isContainsExpectedData(Data<String> data, String expected, String descriptor) {
        return isValuesData(descriptor, expected, data, StringUtils::contains, "contain");
    }

    static Data<Boolean> isValuesNotEqualData(Data<String> data, String expected, String descriptor) {
        return isValuesData(descriptor, expected, data, CoreUtilities::isNotEqual, "unequal");
    }

    static Data<Boolean> isStringNotContainsExpectedData(Data<String> data, String expected, String descriptor) {
        return isValuesData(descriptor, expected, data, StringUtilities::uncontains, "does not contain");
    }

    static DriverFunction<Boolean> isValuesEqualData(DriverFunction<String> getter, String expected, String descriptor) {
        return isValuesData(descriptor, expected, getter, CoreUtilities::isEqual, "equal");
    }

    static DriverFunction<Boolean> isStartsWith(DriverFunction<String> getter, String expected, String descriptor) {
        return isValuesData(descriptor, expected, getter, StringUtilities::startsWithCaseSensitive, " starts with ");
    }

    static DriverFunction<Boolean> isValuesNotEqualData(DriverFunction<String> getter, String expected, String descriptor) {
        return isValuesData(descriptor, expected, getter, CoreUtilities::isNotEqual, "unequal");
    }

    static DriverFunction<Boolean> isContainsExpectedData(DriverFunction<String> getter, String expected, String descriptor) {
        return isValuesData(descriptor, expected, getter, StringUtils::contains, "contain");
    }

    static DriverFunction<Boolean> isStringNotContainsExpectedData(DriverFunction<String> getter, String expected, String descriptor) {
        return isValuesData(descriptor, expected, getter, StringUtilities::uncontains, "does not contain");
    }

    static DriverFunction<Boolean> isTitleData(BiFunction<String, String, Boolean> checker, String expected, String conditionDescriptor) {
        return isValuesData(SeleniumFormatterConstants.TITLE_OF_WINDOW, expected, ExpectedConditionConstants.GET_TITLE, checker, conditionDescriptor);
    }

    static DriverFunction<Boolean> isUrlData(BiFunction<String, String, Boolean> checker, String expected, String conditionDescriptor) {
        return isValuesData("Current url ", expected, ExpectedConditionConstants.GET_URL, checker, conditionDescriptor);
    }

    static DriverFunction<Boolean> isTitleEqualsData(String expected) {
        return isValuesEqualData(ExpectedConditionConstants.GET_TITLE, SeleniumFormatterConstants.TITLE_OF_WINDOW, expected);
    }

    static DriverFunction<Boolean> isTitleContainsData(String expected) {
        return isContainsExpectedData(ExpectedConditionConstants.GET_TITLE, SeleniumFormatterConstants.TITLE_OF_WINDOW, expected);
    }

    static DriverFunction<Boolean> isUrlEqualsData(String expected) {
        return isUrlData(StringUtils::equals, expected, "equal");
    }

    static DriverFunction<Boolean> isUrlContainsData(String expected) {
        return isUrlData(StringUtils::contains, expected, "contain");
    }

    static DriverFunction<Boolean> isUrlContains(String url, String query) {
        return isNotBlank(query) ? SeleniumExecutor.execute(CoreFormatter::getExecutionEndMessageAggregate, isUrlContainsData(url), isUrlContainsData(query)) : isUrlContainsData(url);
    }

    static DriverFunction<Boolean> isUrlEqualsIgnoreCaseData(String expected) {
        return isUrlData(StringUtils::equalsIgnoreCase, expected, "case insensitive equal");
    }

    static DriverFunction<Boolean> isUrlContainsIgnoreCaseData(String expected) {
        return isUrlData(StringUtils::containsIgnoreCase, expected, "case insensitive contain");
    }

    static DriverFunction<Boolean> isUrlMatchesData(String pattern) {
        return isUrlData(CoreUtilities::isStringMatchesPattern, pattern, "match regex");
    }

    static DriverFunction<Boolean> isElementPresent(LazyElement data) {
        return Driver.isElementPresent(data);
    }
    static DriverFunction<Boolean> isElementDisplayed(LazyElement data) {
        return Driver.isElementDisplayed(data);
    }
    static DriverFunction<Boolean> isElementEnabled(LazyElement data) {
        return Driver.isElementEnabled(data);
    }
    static DriverFunction<Boolean> isElementClickable(LazyElement data) {
        return Driver.isElementClickable(data);
    }
    static DriverFunction<Boolean> isElementSelected(LazyElement data) {
        return Driver.isElementSelected(data);
    }
    static DriverFunction<Boolean> isElementAbsent(LazyElement data) {
        return Driver.isElementAbsent(data);
    }
    static DriverFunction<Boolean> isElementHidden(LazyElement data) {
        return Driver.isElementHidden(data);
    }
    static DriverFunction<Boolean> isElementDisabled(LazyElement data) {
        return Driver.isElementDisabled(data);
    }
    static DriverFunction<Boolean> isElementUnclickable(LazyElement data) {
        return Driver.isElementUnclickable(data);
    }
    static DriverFunction<Boolean> isElementUnselected(LazyElement data) {
        return Driver.isElementUnselected(data);
    }
    static DriverFunction<String> getElementText(LazyElement data) {
        return Driver.getElementText(data);
    }
    static DriverFunction<String> getElementTagName(LazyElement data) {
        return Driver.getElementTagName(data);
    }
    static DriverFunction<String> getElementAttributeValue(LazyElement data) {
        return Driver.getElementAttributeValue(data);
    }

    static DriverFunction<Boolean> isElementPresentData(By locator) {
        return Driver.isElementPresent(locator);
    }
    static DriverFunction<Boolean> isElementDisplayedData(By locator) {
        return Driver.isElementDisplayed(locator);
    }
    static DriverFunction<Boolean> isElementEnabledData(By locator) {
        return Driver.isElementEnabled(locator);
    }
    static DriverFunction<Boolean> isElementClickableData(By locator) {
        return Driver.isElementClickable(locator);
    }
    static DriverFunction<Boolean> isElementSelectedData(By locator) {
        return Driver.isElementSelected(locator);
    }
    static DriverFunction<Boolean> isElementAbsentData(By locator) {
        return Driver.isElementAbsent(locator);
    }
    static DriverFunction<Boolean> isElementHiddenData(By locator) {
        return Driver.isElementHidden(locator);
    }
    static DriverFunction<Boolean> isElementDisabledData(By locator) {
        return Driver.isElementDisabled(locator);
    }
    static DriverFunction<Boolean> isElementUnclickableData(By locator) {
        return Driver.isElementUnclickable(locator);
    }
    static DriverFunction<Boolean> isElementUnselectedData(By locator) {
        return Driver.isElementUnselected(locator);
    }
    static DriverFunction<String> getElementTextData(By locator) {
        return Driver.getElementText(locator);
    }
    static DriverFunction<String> getElementTagNameData(By locator) {
        return Driver.getElementTagName(locator);
    }
    static DriverFunction<String> getElementAttributeValueData(By locator) {
        return Driver.getElementAttributeValue(locator);
    }
    static DriverFunction<Boolean> isElementPresentData(String cssSelector) {
        return isElementPresentData(By.cssSelector(cssSelector));
    }
    static DriverFunction<Boolean> isElementDisplayedData(String cssSelector) {
        return isElementDisplayedData(By.cssSelector(cssSelector));
    }
    static DriverFunction<Boolean> isElementEnabledData(String cssSelector) {
        return isElementEnabledData(By.cssSelector(cssSelector));
    }
    static DriverFunction<Boolean> isElementClickableData(String cssSelector) {
        return isElementClickableData(By.cssSelector(cssSelector));
    }
    static DriverFunction<Boolean> isElementAbsentData(String cssSelector) {
        return isElementAbsentData(By.cssSelector(cssSelector));
    }
    static DriverFunction<Boolean> isElementHiddenData(String cssSelector) {
        return isElementHiddenData(By.cssSelector(cssSelector));
    }
    static DriverFunction<Boolean> isElementDisabledData(String cssSelector) {
        return isElementDisabledData(By.cssSelector(cssSelector));
    }
    static DriverFunction<Boolean> isElementUnclickableData(String cssSelector) {
        return isElementUnclickableData(By.cssSelector(cssSelector));
    }
    static DriverFunction<String> getElementTextData(String cssSelector) {
        return getElementTextData(By.cssSelector(cssSelector));
    }
    static DriverFunction<String> getElementTagNameData(String cssSelector) {
        return getElementTagNameData(By.cssSelector(cssSelector));
    }
    static DriverFunction<String> getElementAttributeValueData(String cssSelector) {
        return getElementAttributeValueData(By.cssSelector(cssSelector));
    }

    static Function<WebDriver, Boolean> isTitleEquals(String expected) {
        return driver -> isTitleEqualsData(expected).apply(driver).status;
    }

    static Function<WebDriver, Boolean> isTitleContains(String expected) {
        return driver -> isTitleContainsData(expected).apply(driver).status;
    }

    static DriverFunction<Boolean> isElementTextEqualData(LazyElement data, String expected) {
        return isValuesEqualData(Driver.getElementText(data), expected, "Element text");
    }

    static DriverFunction<Boolean> isElementTextContainsData(LazyElement data, String expected) {
        return isContainsExpectedData(Driver.getElementText(data), expected, "Element text");
    }

    static DriverFunction<Boolean> isElementAttributeValueTextEqualData(LazyElement data, String expected) {
        return isValuesEqualData(Driver.getElementAttributeValue(data), expected, "Element attribute value");
    }

    static DriverFunction<Boolean> isElementAttributeValueContainsData(LazyElement data, String expected) {
        return isContainsExpectedData(Driver.getElementAttributeValue(data), expected, "Element attribute value");
    }

    static DriverFunction<Boolean> isElementAttributeValueNotContainsData(LazyElement data, String expected) {
        return isContainsExpectedData(Driver.getElementAttributeValue(data), expected, "Element text");
    }

    static DriverFunction<Boolean> isNumberOfWindowsEqualTo(int expected) {
        return ifDriver(
            "isNumberOfWindowsEqualTo",
            BasicPredicates.isPositiveNonZero(expected),
            ExecutionCore.validChain(DriverPropertyFunctions.getWindowHandleAmount(), ExpectedConditions.isNumberOfWindowsEqualToCore(expected), CoreDataConstants.NULL_BOOLEAN),
            CoreDataConstants.NULL_BOOLEAN
        );
    }
}
