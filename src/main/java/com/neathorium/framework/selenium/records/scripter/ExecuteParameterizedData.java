package com.neathorium.framework.selenium.records.scripter;

import com.neathorium.framework.selenium.abstracts.ExecuteBaseData;
import com.neathorium.framework.selenium.namespaces.extensions.boilers.ScriptFunction;
import com.neathorium.core.constants.validators.CoreFormatterConstants;
import com.neathorium.core.extensions.namespaces.NullableFunctions;
import com.neathorium.core.records.ExecuteCommonData;
import org.openqa.selenium.JavascriptExecutor;

import java.security.InvalidParameterException;
import java.util.Objects;
import java.util.function.Function;

public class ExecuteParameterizedData<T> extends ExecuteBaseData<T, Function<String, Object>> implements ScriptFunction<Function<String, Object>> {
    public final ExecutorParametersFieldData parameterData;

    public ExecuteParameterizedData(ExecuteCommonData<T> commonData, ExecutorParametersFieldData parameterData) {
        super(commonData);
        this.parameterData = parameterData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final var that = (ExecuteParameterizedData<?>) o;
        return Objects.equals(parameterData, that.parameterData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parameterData);
    }

    @Override
    public Function<String, Object> apply(JavascriptExecutor executor) {
        if (NullableFunctions.isNull(executor)) {
            throw new InvalidParameterException("Executor parameter" + CoreFormatterConstants.WAS_NULL);
        }

        final var parameters = parameterData.parameters;
        return script -> parameterData.handler.apply(executor).apply(script, parameters);
    }
}
