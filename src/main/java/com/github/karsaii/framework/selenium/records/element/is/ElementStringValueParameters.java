package com.github.karsaii.framework.selenium.records.element.is;

import com.github.karsaii.core.extensions.interfaces.functional.TriFunction;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.selenium.abstracts.ElementValueParameters;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.github.karsaii.framework.selenium.records.lazy.LazyElement;

import java.util.function.Function;

public class ElementStringValueParameters<ReturnType> extends ElementValueParameters<String, ReturnType> {
    public ElementStringValueParameters(
        TriFunction<DriverFunction<String>, Function<Data<String>, Data<ReturnType>>, Data<ReturnType>, DriverFunction<ReturnType>> handler,
        ElementFormatData<ReturnType> formatData,
        Function<LazyElement, DriverFunction<ReturnType>> function
    ) {
        super(handler, formatData, function);
    }
}
