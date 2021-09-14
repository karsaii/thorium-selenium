package com.neathorium.framework.selenium.namespaces.scripter.injectable;

import com.neathorium.framework.selenium.constants.scripts.injectable.JSInitializerConstants;
import com.neathorium.framework.selenium.namespaces.driver.DevtoolsDriverFunctions;
import com.neathorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.core.constants.CoreDataConstants;
import com.neathorium.core.namespaces.DataFactoryFunctions;
import com.neathorium.core.namespaces.predicates.DataPredicates;
import com.neathorium.core.records.Data;
import com.neathorium.framework.selenium.namespaces.ExecutionCore;
import org.openqa.selenium.WebDriver;

import static com.neathorium.framework.selenium.namespaces.ExecutionCore.ifDriver;

public interface JSInitializer {
    static DriverFunction<Boolean> isJSDependenciesExistData() {
        return ExecutionCore.ifDriver("isJSDependenciesExistData", DevtoolsDriverFunctions.doBooleanCommand(JSInitializerConstants.DEPENDENCY_EXISTENCE_CHECKER), CoreDataConstants.NULL_BOOLEAN);
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

        return DataFactoryFunctions.replaceName(DevtoolsDriverFunctions.doBooleanCommand(JSInitializerScripts.getDefaultJSInitializer()).apply(driver), nameof);
    }

    private static DriverFunction<Boolean> setJSDependenciesCore() {
        return JSInitializer::setJSDependenciesCore;
    }

    static DriverFunction<Boolean> setJSDependencies() {
        return ExecutionCore.ifDriver("setJSDependencies", setJSDependenciesCore(), CoreDataConstants.NULL_BOOLEAN);
    }
}
