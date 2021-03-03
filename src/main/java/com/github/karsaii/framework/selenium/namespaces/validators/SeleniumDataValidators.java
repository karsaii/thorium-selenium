package com.github.karsaii.framework.selenium.namespaces.validators;

import com.github.karsaii.core.constants.CoreDataConstants;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.core.namespaces.validators.FrameworkCoreFormatter;
import com.github.karsaii.framework.selenium.namespaces.element.validators.WebElementValidators;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.github.karsaii.framework.selenium.records.lazy.LazyElement;
import org.openqa.selenium.WebElement;

import java.util.function.Function;

import static com.github.karsaii.core.namespaces.DataExecutionFunctions.ifDependency;
import static com.github.karsaii.core.namespaces.DataExecutionFunctions.validChain;
import static com.github.karsaii.framework.selenium.namespaces.factories.DriverFunctionFactory.getFunction;

public interface SeleniumDataValidators {
    private static DriverFunction<Boolean> isConditionCore(LazyElement element, String nameof, Function<Data<WebElement>, Data<Boolean>> condition) {
        return getFunction(ifDependency(
            nameof,
            FrameworkCoreFormatter.isNullLazyElementMessage(element),
            validChain(element.get(), condition, CoreDataConstants.DATA_PARAMETER_WAS_NULL),
            CoreDataConstants.DATA_PARAMETER_WAS_NULL
        ));
    }
    static DriverFunction<Boolean> isNotNull(LazyElement element) {
        return isConditionCore(element, "isNotNull", WebElementValidators::isInitialized);
    }

    static DriverFunction<Boolean> isValidElement(LazyElement element) {
        return isConditionCore(element, "isValidLazyElement", WebElementValidators::isValidWebElement);
    }
}
