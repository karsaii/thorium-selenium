package com.github.karsaii.framework.selenium.abstracts;

import com.github.karsaii.core.records.ExecuteCommonData;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.ScriptFunction;

public abstract class ExecuteBaseData<ParameterType, ReturnType> implements ScriptFunction<ReturnType> {
    public final ExecuteCommonData<ParameterType> common;

    public ExecuteBaseData(ExecuteCommonData<ParameterType> common) {
        this.common = common;
    }
}
