package com.neathorium.framework.selenium.abstracts;

import com.neathorium.framework.selenium.namespaces.extensions.boilers.ScriptFunction;
import com.neathorium.core.records.ExecuteCommonData;

public abstract class ExecuteBaseData<ParameterType, ReturnType> implements ScriptFunction<ReturnType> {
    public final ExecuteCommonData<ParameterType> common;

    public ExecuteBaseData(ExecuteCommonData<ParameterType> common) {
        this.common = common;
    }
}
