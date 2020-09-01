package com.github.karsaii.framework.selenium.namespaces.scripter.injectable;

import com.github.karsaii.core.constants.CoreDataConstants;
import com.github.karsaii.core.extensions.namespaces.predicates.ExecutorPredicates;
import com.github.karsaii.core.namespaces.DataFactoryFunctions;
import com.github.karsaii.core.namespaces.predicates.DataPredicates;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.selenium.constants.scripts.injectable.JSInitializerConstants;
import com.github.karsaii.framework.selenium.namespaces.SeleniumExecutor;
import com.github.karsaii.framework.selenium.namespaces.driver.DevtoolsDriverUtilities;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.github.karsaii.framework.selenium.namespaces.validators.SeleniumFormatter;
import org.openqa.selenium.WebDriver;

import static com.github.karsaii.core.namespaces.predicates.DataPredicates.isValidNonFalse;
import static com.github.karsaii.framework.selenium.namespaces.ExecutionCore.ifDriver;

public interface JSInitializer {
    static DriverFunction<Boolean> isJSDependenciesExistData() {
        return ifDriver("isJSDependenciesExistData", DevtoolsDriverUtilities.doBooleanCommand(JSInitializerConstants.DEPENDENCY_EXISTENCE_CHECKER), CoreDataConstants.NULL_BOOLEAN);
    }

    private static Data<Boolean> setJSDependenciesCore(WebDriver driver) {
        final var nameof = "setJSDependenciesCore";
        final var isAlreadySet = isJSDependenciesExistData().apply(driver);
        if (DataPredicates.isInvalid(isAlreadySet)) {
            return DataFactoryFunctions.getBoolean(false, nameof, isAlreadySet.message.message, isAlreadySet.exception, isAlreadySet.exceptionMessage);
        }

        if (DataPredicates.isValidNonFalse(isAlreadySet)) {
            return DataFactoryFunctions.getBoolean(true, nameof, isAlreadySet.message.message, isAlreadySet.exception, isAlreadySet.exceptionMessage);
        }

        return DataFactoryFunctions.replaceName(DevtoolsDriverUtilities.doBooleanCommand(JSInitializerScripts.getDefaultJSInitializer()).apply(driver), nameof);
    }

    private static DriverFunction<Boolean> setJSDependenciesCore() {
        return JSInitializer::setJSDependenciesCore;
    }

    static DriverFunction<Boolean> setJSDependencies() {
        return ifDriver("setJSDependencies", setJSDependenciesCore(), CoreDataConstants.NULL_BOOLEAN);
    }
}
