package com.github.karsaii.framework.selenium.namespaces.scripter.injectable;

import com.github.karsaii.core.constants.CoreDataConstants;
import com.github.karsaii.core.extensions.namespaces.predicates.ExecutorPredicates;
import com.github.karsaii.core.namespaces.DataFactoryFunctions;
import com.github.karsaii.framework.selenium.constants.scripts.injectable.JSInitializerConstants;
import com.github.karsaii.framework.selenium.namespaces.SeleniumExecutor;
import com.github.karsaii.framework.selenium.namespaces.driver.DevtoolsDriverUtilities;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.github.karsaii.framework.selenium.namespaces.validators.SeleniumFormatter;

import static com.github.karsaii.core.namespaces.predicates.DataPredicates.isValidNonFalse;
import static com.github.karsaii.framework.selenium.namespaces.ExecutionCore.ifDriver;

public interface JSInitializer {
    static DriverFunction<Boolean> isJSDependenciesExistData() {
        return ifDriver("isJSDependenciesExistData", DevtoolsDriverUtilities.doBooleanCommand(JSInitializerConstants.DEPENDENCY_EXISTENCE_CHECKER), CoreDataConstants.NULL_BOOLEAN);
    }

    static DriverFunction<Boolean> setJSDependencies() {
        return ifDriver(
            "setJSDependencies",
            driver -> {
                final var result = SeleniumExecutor.conditionalSequence(ExecutorPredicates::isFalseStatus, isJSDependenciesExistData(), DevtoolsDriverUtilities.doBooleanCommand(JSInitializerScripts.getDefaultJSInitializer())).apply(driver);
                final var status = isValidNonFalse(result);
                return DataFactoryFunctions.getBoolean(status, SeleniumFormatter.getJSDependenciesMessage(result.message.getMessage(), status));
            },
            CoreDataConstants.NULL_BOOLEAN
        );
    }
}
