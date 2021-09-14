package com.neathorium.framework.selenium.namespaces.validators;

import com.neathorium.framework.selenium.namespaces.element.validators.WebElementValidators;
import com.neathorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.framework.selenium.records.lazy.LazyElement;
import com.neathorium.core.constants.CoreDataConstants;
import com.neathorium.core.namespaces.DataExecutionFunctions;
import com.neathorium.core.records.Data;
import com.neathorium.framework.core.namespaces.validators.FrameworkCoreFormatter;
import com.neathorium.framework.selenium.namespaces.factories.DriverFunctionFactory;
import org.openqa.selenium.WebElement;

import java.util.function.Function;

public interface SeleniumDataValidators {
    private static DriverFunction<Boolean> isConditionCore(LazyElement element, String nameof, Function<Data<WebElement>, Data<Boolean>> condition) {
        return DriverFunctionFactory.getFunction(DataExecutionFunctions.ifDependency(
            nameof,
            FrameworkCoreFormatter.isNullLazyElementMessage(element),
            DataExecutionFunctions.validChain(element.get(), condition, CoreDataConstants.DATA_PARAMETER_WAS_NULL),
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
