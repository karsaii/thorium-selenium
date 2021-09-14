package com.neathorium.framework.selenium.records.scripter;

import com.neathorium.framework.selenium.abstracts.ExecuteBaseData;
import com.neathorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.core.records.ExecuteCommonData;
import com.neathorium.core.records.HandleResultData;
import com.neathorium.core.records.caster.WrappedCastData;
import org.openqa.selenium.JavascriptExecutor;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class ParameterizedExecutorData<ReturnType> extends ExecutorData<ExecutorParametersFieldData, String, Boolean, ReturnType> {
    public ParameterizedExecutorData(
        DriverFunction<JavascriptExecutor> getter,
        BiFunction<ExecuteCommonData<String>, ExecutorParametersFieldData, ExecuteBaseData<String, Function<String, Object>>> constructor,
        Predicate<ExecutorParametersFieldData> guard,
        WrappedCastData<ReturnType> castData,
        ExecutorWrappedResultFunctionsData<HandleResultData<String, ReturnType>, Boolean, ReturnType> resultHandlers
    ) {
        super(getter, constructor, guard, castData, resultHandlers);
    }
}
