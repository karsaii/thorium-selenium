package com.github.karsaii.framework.selenium.records.element.is;

import com.github.karsaii.core.extensions.interfaces.functional.TriFunction;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.selenium.abstracts.ElementValueParameters;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.github.karsaii.framework.selenium.records.lazy.LazyElement;

import java.util.function.Function;

public class ElementBooleanValueParameters<ReturnType> extends ElementValueParameters<Boolean, ReturnType> {
    public ElementBooleanValueParameters(
        TriFunction<DriverFunction<Boolean>, Function<Data<Boolean>, Data<ReturnType>>, Data<ReturnType>, DriverFunction<ReturnType>> handler,
        ElementFormatData<ReturnType> formatData,
        Function<LazyElement, DriverFunction<ReturnType>> function
    ) {
        super(handler, formatData, function);
    }
}
