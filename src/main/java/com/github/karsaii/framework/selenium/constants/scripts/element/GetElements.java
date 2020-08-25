package com.github.karsaii.framework.selenium.constants.scripts.element;

import com.github.karsaii.core.constants.CoreDataConstants;
import com.github.karsaii.core.extensions.namespaces.predicates.ExecutorPredicates;
import com.github.karsaii.core.namespaces.DataFactoryFunctions;
import com.github.karsaii.core.namespaces.predicates.DataPredicates;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.selenium.constants.DriverFunctionConstants;
import com.github.karsaii.framework.selenium.constants.scripts.general.ScrollIntoView;
import com.github.karsaii.framework.selenium.enums.SingleGetter;
import com.github.karsaii.framework.selenium.namespaces.Driver;
import com.github.karsaii.framework.selenium.namespaces.ScriptExecuteFunctions;
import com.github.karsaii.framework.selenium.namespaces.SeleniumExecutor;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.github.karsaii.framework.selenium.namespaces.repositories.LocatorRepository;
import com.github.karsaii.framework.selenium.namespaces.utilities.SeleniumUtilities;
import com.github.karsaii.framework.selenium.namespaces.validators.SeleniumFormatter;
import com.github.karsaii.framework.selenium.records.lazy.LazyElement;
import com.github.karsaii.framework.selenium.records.scripter.ScriptParametersData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.github.karsaii.core.extensions.namespaces.NullableFunctions.isNotNull;
import static com.github.karsaii.core.namespaces.DataFactoryFunctions.getWithDefaultExceptionData;
import static com.github.karsaii.core.namespaces.predicates.DataPredicates.isValidNonFalse;
import static com.github.karsaii.framework.selenium.namespaces.ExecutionCore.ifDriver;
import static com.github.karsaii.framework.selenium.namespaces.utilities.SeleniumUtilities.isNotNullLazyElement;

public abstract class GetElements {
    /*static DriverFunction<Boolean> isGetElementsExistsData() {
        return ifDriver(
            "isGetElementsExistsData",
            driver -> {
                final var result = Driver.execute(ScrollIntoView.IS_EXISTS).apply(driver);
                return getWithDefaultExceptionData(Boolean.valueOf(result.object.toString()), result.status, result.message);
            },
            CoreDataConstants.NULL_BOOLEAN
        );
    }

    static DriverFunction<Boolean> scrollIntoViewExecutor(LazyElement data) {
        return isNotNullLazyElement(data) ? scrollIntoViewExecutor(data.get()) : DriverFunctionConstants.NULL_BOOLEAN;
    }

    static DriverFunction<Boolean> scrollIntoViewExecutor(DriverFunction<WebElement> getter) {
        return ifDriver(
                "scrollIntoViewExecutor",
                isNotNull(getter),
                driver -> {
                    final var parameters = new ScriptParametersData<>(getter.apply(driver), DataPredicates::isValidNonFalse, SeleniumUtilities::unwrapToArray);
                    final var result = Driver.executeSingleParameter(ScrollIntoView.EXECUTE, ScriptExecuteFunctions.handleDataParameter(parameters)).apply(driver);
                    return getWithDefaultExceptionData(isNotNull(result.object), result.status, result.message);
                },
                CoreDataConstants.NULL_BOOLEAN
        );
    }

    static DriverFunction<Boolean> setScrollIntoView() {
        return ifDriver(
                "setScrollIntoView",
                driver -> {
                    final var result = SeleniumExecutor.conditionalSequence(ExecutorPredicates::isFalseStatus, isScrollIntoViewExistsData(), Driver.execute(ScrollIntoView.SET_FUNCTIONS)).apply(driver);
                    final var status = isValidNonFalse(result);
                    return DataFactoryFunctions.getBoolean(status, SeleniumFormatter.getScrollIntoViewMessage(result.message.getMessage(), status));
                },
                CoreDataConstants.NULL_BOOLEAN
        );
    }

    static DriverFunction<Boolean> scrollIntoView(LazyElement data) {
        return SeleniumExecutor.execute(Driver.isElementHidden(data), setScrollIntoView(), scrollIntoViewExecutor(data));
    }

    static DriverFunction<Boolean> scrollIntoView(Data<LazyElement> data) {
        return ifDriver("scrollIntoView", isValidNonFalse(data), scrollIntoView(data.object), CoreDataConstants.NULL_BOOLEAN);
    }

    static DriverFunction<Boolean> scrollIntoView(By locator, SingleGetter getter) {
        return scrollIntoView(LocatorRepository.getIfContains(locator, getter));
    }

    static DriverFunction<Boolean> scrollIntoView(By locator) {
        return scrollIntoView(locator, SingleGetter.DEFAULT);
    }*/


}
