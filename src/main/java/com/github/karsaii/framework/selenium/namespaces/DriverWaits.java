package com.github.karsaii.framework.selenium.namespaces;

import com.github.karsaii.core.constants.CoreDataConstants;
import com.github.karsaii.core.constants.validators.CoreFormatterConstants;
import com.github.karsaii.core.extensions.namespaces.predicates.BasicPredicates;
import com.github.karsaii.core.namespaces.factories.wait.WaitDataFactory;
import com.github.karsaii.core.namespaces.factories.wait.WaitTimeDataFactory;
import com.github.karsaii.core.namespaces.wait.Wait;
import com.github.karsaii.core.records.wait.WaitData;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.DriverFunction;

import static com.github.karsaii.core.extensions.namespaces.CoreUtilities.areAll;
import static com.github.karsaii.core.extensions.namespaces.NullableFunctions.isNotNull;
import static com.github.karsaii.framework.selenium.namespaces.ExecutionCore.ifDriver;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public interface DriverWaits {
    static DriverFunction<Boolean> waitNavigatedTo(String url, String query, int interval, int timeout) {
        return ifDriver(
            "waitNavigatedTo",
            isNotBlank(url) && isNotNull(query) && areAll(BasicPredicates::isPositiveNonZero, interval, timeout) && (interval < timeout),
            driver -> Wait.core(
                WaitDataFactory.getWith(
                    ExpectedConditions.isUrlContains(url, query),
                    WaitPredicateFunctions::isTruthyData,
                    "Waiting for url",
                    WaitTimeDataFactory.getWithDefaultClock(interval, timeout)
                )).apply(driver),
            CoreDataConstants.PARAMETERS_NULL_BOOLEAN
        );
    }

    static DriverFunction<Boolean> navigateAndWait(String url, String query, int interval, int timeout) {
        return ifDriver(
            "navigateAndWait",
            isNotBlank(url) && isNotNull(query) && areAll(BasicPredicates::isPositiveNonZero, interval, timeout) && BasicPredicates.isSmallerThan(interval, timeout),
            SeleniumExecutor.execute(Driver.navigate(url, query), waitNavigatedTo(url, interval, timeout)),
            CoreDataConstants.PARAMETERS_NULL_BOOLEAN
        );
    }

    static DriverFunction<Boolean> waitNavigatedTo(String url, int interval, int timeout) {
        return waitNavigatedTo(url, CoreFormatterConstants.EMPTY, interval, timeout);
    }

    static DriverFunction<Boolean> navigateAndWait(String url, int interval, int timeout) {
        return navigateAndWait(url, CoreFormatterConstants.EMPTY, interval, timeout);
    }
}
