package com.github.karsaii.framework.selenium.namespaces;

import com.github.karsaii.core.constants.CoreDataConstants;
import com.github.karsaii.core.constants.validators.CoreFormatterConstants;
import com.github.karsaii.core.namespaces.factories.wait.WaitDataFactory;
import com.github.karsaii.core.namespaces.factories.wait.WaitTimeDataFactory;
import com.github.karsaii.core.namespaces.wait.Wait;
import com.github.karsaii.core.records.wait.WaitData;
import com.github.karsaii.framework.selenium.constants.DriverFunctionConstants;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.github.karsaii.framework.selenium.namespaces.factories.DriverFunctionFactory;
import com.github.karsaii.framework.selenium.records.element.regular.ElementWaitParameters;
import com.github.karsaii.framework.selenium.records.lazy.LazyElement;
import com.github.karsaii.framework.selenium.records.lazy.LazyElementWaitParameters;
import org.openqa.selenium.By;

import java.util.function.Function;

import static com.github.karsaii.core.extensions.namespaces.NullableFunctions.isNotNull;
import static com.github.karsaii.core.namespaces.DataFactoryFunctions.appendMessage;
import static com.github.karsaii.core.namespaces.DataFactoryFunctions.prependMessage;
import static com.github.karsaii.framework.selenium.namespaces.ExecutionCore.ifDriver;
import static com.github.karsaii.framework.selenium.namespaces.utilities.SeleniumUtilities.isNotNullElementWaitParametersData;
import static com.github.karsaii.framework.selenium.namespaces.utilities.SeleniumUtilities.isNotNullLazyElementWaitParametersData;
import static org.apache.commons.lang3.StringUtils.isBlank;

public interface WaitConditions {
    static DriverFunction<Boolean> waitWith(By locator, Function<By, DriverFunction<Boolean>> conditionGetter, String option, int interval, int timeout, String message) {
        return ifDriver(
            "waitConditionCore",
            isNotNull(conditionGetter),
            Wait.core(WaitDataFactory.getWith(
                conditionGetter.apply(locator),
                isBlank(option) ? WaitPredicateFunctions::isTruthyData : WaitPredicateFunctions::isFalsyData,
                "Element located by: " + locator + " to be " + (isBlank(message) ? "clickable" : message) + CoreFormatterConstants.END_LINE,
                WaitTimeDataFactory.getWithDefaultClock(interval, timeout)
            ))::apply,
            appendMessage(CoreDataConstants.NULL_BOOLEAN, "Condition Getter" + CoreFormatterConstants.WAS_NULL)
        );
    }

    static DriverFunction<Boolean> waitWith(LazyElement data, Function<LazyElement, DriverFunction<Boolean>> conditionGetter, String option, int interval, int timeout, String message) {
        return ifDriver(
            "waitConditionCore",
            isNotNull(conditionGetter),
            DriverFunctionFactory.prependMessage(
                Wait.core(WaitDataFactory.getWith(
                    conditionGetter.apply(data),
                    isBlank(option) ? WaitPredicateFunctions::isTruthyData : WaitPredicateFunctions::isFalsyData,
                    data.name + " " + message,
                    WaitTimeDataFactory.getWithDefaultClock(interval, timeout)
                ))::apply,
                CoreFormatterConstants.ELEMENT + data.name
            ),
            prependMessage(CoreDataConstants.NULL_BOOLEAN, CoreFormatterConstants.ELEMENT + data.name)
        );
    }

    static DriverFunction<Boolean> waitWith(ElementWaitParameters data, Function<By, DriverFunction<Boolean>> conditionGetter, String option, String message) {
        return isNotNullElementWaitParametersData(data) ? waitWith(data.object, conditionGetter, option, data.interval, data.duration, message) : DriverFunctionConstants.NULL_BOOLEAN;
    }

    static DriverFunction<Boolean> waitWith(LazyElementWaitParameters data, Function<LazyElement, DriverFunction<Boolean>> conditionGetter, String option, String message) {
        return isNotNullLazyElementWaitParametersData(data) ? waitWith(data.object, conditionGetter, option, data.interval, data.duration, message) : DriverFunctionConstants.NULL_BOOLEAN;
    }
}
