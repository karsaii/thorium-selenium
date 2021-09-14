package com.neathorium.framework.selenium.constants;

import com.neathorium.framework.selenium.namespaces.ScriptExecuteFunctions;
import com.neathorium.framework.selenium.namespaces.driver.executor.ExecutorFunctions;
import com.neathorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.framework.selenium.namespaces.validators.ScriptExecutions;
import com.neathorium.framework.selenium.namespaces.validators.SeleniumFormatter;
import com.neathorium.framework.selenium.records.scripter.ExecuteParameterizedData;
import com.neathorium.framework.selenium.records.scripter.ExecuteRegularData;
import com.neathorium.framework.selenium.records.scripter.ExecutorWrappedResultFunctionsData;
import com.neathorium.framework.selenium.records.scripter.ParameterizedExecutorData;
import com.neathorium.framework.selenium.records.scripter.ParametersFieldDefaultsData;
import com.neathorium.framework.selenium.records.scripter.RegularExecutorData;
import com.neathorium.core.constants.CastDataConstants;
import com.neathorium.core.extensions.namespaces.predicates.AmountPredicates;
import com.neathorium.core.namespaces.ExceptionHandlers;
import com.neathorium.core.records.HandleResultData;
import org.openqa.selenium.JavascriptExecutor;

public abstract class ScriptExecutorDefaults {
    public static final ParametersFieldDefaultsData SINGLE_PARAMETER_DEFAULTS = new ParametersFieldDefaultsData(AmountPredicates::isSingle, ScriptExecuteFunctions.executeScriptWithParameters());
    public static final ParametersFieldDefaultsData SINGLE_PARAMETER_ASYNC_DEFAULTS = new ParametersFieldDefaultsData(AmountPredicates::isSingle, ScriptExecuteFunctions.executeAsyncScriptWithParameters());
    public static final ParametersFieldDefaultsData PARAMETERS_DEFAULTS = new ParametersFieldDefaultsData(AmountPredicates::isNonZero, ScriptExecuteFunctions.executeScriptWithParameters());
    public static final ParametersFieldDefaultsData PARAMETERS_ASYNC_DEFAULTS = new ParametersFieldDefaultsData(AmountPredicates::isNonZero, ScriptExecuteFunctions.executeAsyncScriptWithParameters());
    public static final ExecutorWrappedResultFunctionsData<HandleResultData<String, Object>, Boolean, Object> OBJECT_RESULT_HANDLER = new ExecutorWrappedResultFunctionsData<>(SeleniumFormatter::getScriptExecutionMessage, ExceptionHandlers::classCastHandler);
    public static final ExecutorWrappedResultFunctionsData<HandleResultData<String, String>, Boolean, String> STRING_RESULT_HANDLER = new ExecutorWrappedResultFunctionsData<>(SeleniumFormatter::getScriptExecutionMessage, ExceptionHandlers::classCastHandler);
    public static final DriverFunction<JavascriptExecutor> JAVASCRIPT_EXECUTOR_GETTER = ExecutorFunctions.getExecutor();
    public static final RegularExecutorData<Object> OBJECT_REGULAR_DEFAULTS = new RegularExecutorData<>(
            JAVASCRIPT_EXECUTOR_GETTER,
            ExecuteRegularData::new,
            ScriptExecutions::isValidExecutorRegularData,
            CastDataConstants.WRAPPED_OBJECT,
            OBJECT_RESULT_HANDLER
    );
    public static final RegularExecutorData<String> STRING_REGULAR_DEFAULTS = new RegularExecutorData<>(
            JAVASCRIPT_EXECUTOR_GETTER,
            ExecuteRegularData::new,
            ScriptExecutions::isValidExecutorRegularData,
            CastDataConstants.WRAPPED_STRING,
            STRING_RESULT_HANDLER
    );
    public static final ParameterizedExecutorData<Object> OBJECT_PARAMETERS_DEFAULTS = new ParameterizedExecutorData<>(
            JAVASCRIPT_EXECUTOR_GETTER,
            ExecuteParameterizedData::new,
            ScriptExecutions::isValidExecutorParametersData,
            CastDataConstants.WRAPPED_OBJECT,
            OBJECT_RESULT_HANDLER
    );
    public static final ParameterizedExecutorData<String> STRING_PARAMETERS_DEFAULTS = new ParameterizedExecutorData<>(
            JAVASCRIPT_EXECUTOR_GETTER,
            ExecuteParameterizedData::new,
            ScriptExecutions::isValidExecutorParametersData,
            CastDataConstants.WRAPPED_STRING,
            STRING_RESULT_HANDLER
    );
}
