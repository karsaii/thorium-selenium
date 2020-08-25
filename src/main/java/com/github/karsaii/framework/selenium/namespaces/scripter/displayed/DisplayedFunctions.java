package com.github.karsaii.framework.selenium.namespaces.scripter.displayed;

import com.github.karsaii.core.constants.CoreDataConstants;
import com.github.karsaii.core.constants.validators.CoreFormatterConstants;
import com.github.karsaii.core.extensions.namespaces.CoreUtilities;
import com.github.karsaii.core.extensions.namespaces.NullableFunctions;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.selenium.constants.scripts.element.Displayed;
import com.github.karsaii.framework.selenium.enums.SingleGetter;
import com.github.karsaii.framework.selenium.namespaces.Driver;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.github.karsaii.framework.selenium.namespaces.factories.DriverFunctionFactory;
import com.github.karsaii.framework.selenium.namespaces.repositories.LocatorRepository;
import com.github.karsaii.framework.selenium.namespaces.scripter.Execute;
import com.github.karsaii.framework.selenium.records.lazy.LazyElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.github.karsaii.core.namespaces.DataFactoryFunctions.getWithDefaultExceptionData;
import static com.github.karsaii.core.namespaces.DataFactoryFunctions.replaceMessage;
import static com.github.karsaii.core.namespaces.predicates.DataPredicates.isInvalidOrFalse;
import static com.github.karsaii.core.namespaces.predicates.DataPredicates.isValidNonFalse;
import static com.github.karsaii.framework.selenium.namespaces.ExecutionCore.ifDriver;
import static com.github.karsaii.framework.selenium.namespaces.ExecutionCore.ifDriverFunction;
import static com.github.karsaii.framework.selenium.namespaces.utilities.SeleniumUtilities.isNotNullLazyElement;
import static com.github.karsaii.framework.selenium.namespaces.utilities.SeleniumUtilities.isNotNullWebElement;

public interface DisplayedFunctions {
    private static DriverFunction<Boolean> isDisplayedCore(Data<WebElement> data) {
        return ifDriver(
            "isDisplayed",
            isNotNullWebElement(data),
            driver -> {
                final var parameter = Execute.handleDataParameterDefault(data);
                if(isInvalidOrFalse(parameter)) {
                    return replaceMessage(CoreDataConstants.NULL_BOOLEAN, parameter.message.toString());
                }

                final var result = Driver.executeSingleParameter(Displayed.IS_DISPLAYED_DISPATCHER, parameter.object).apply(driver);
                return isValidNonFalse(result) ? (
                    getWithDefaultExceptionData(CoreUtilities.castToBoolean(result.object), result.status, result.message)
                ) : replaceMessage(CoreDataConstants.NULL_BOOLEAN, result.message.toString());
            },
            CoreDataConstants.NULL_BOOLEAN
        );
    }


    static DriverFunction<Boolean> isDisplayed(DriverFunction<WebElement> getter) {
        return ifDriverFunction("isDisplayed", NullableFunctions::isNotNull, getter, DisplayedFunctions::isDisplayedCore, CoreDataConstants.NULL_BOOLEAN);
    }

    static DriverFunction<Boolean> isDisplayed(LazyElement element) {
        return isNotNullLazyElement(element) ? (
                isDisplayed(element.get())
        ) : DriverFunctionFactory.get(replaceMessage(CoreDataConstants.NULL_BOOLEAN, "isDisplayed", "LazyElement element" + CoreFormatterConstants.WAS_NULL));
    }

    static DriverFunction<Boolean> isDisplayed(Data<LazyElement> data) {
        return ifDriver("isDisplayed", isValidNonFalse(data), isDisplayed(data.object), CoreDataConstants.NULL_BOOLEAN);
    }

    static DriverFunction<Boolean> isDisplayed(By locator, SingleGetter getter) {
        return isDisplayed(LocatorRepository.getIfContains(locator, getter));
    }

    static DriverFunction<Boolean> isDisplayed(By locator) {
        return isDisplayed(locator, SingleGetter.DEFAULT);
    }
}
