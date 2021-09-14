package com.neathorium.framework.selenium.records.element.is.regular;

import com.neathorium.framework.selenium.abstracts.regular.AbstractElementValueParameters;
import com.neathorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.framework.selenium.records.element.is.ElementFormatData;
import com.neathorium.framework.selenium.records.lazy.LazyElement;
import com.neathorium.core.extensions.interfaces.functional.TriFunction;
import com.neathorium.core.records.Data;

import java.util.function.Function;

public class ElementBooleanValueParameters<ReturnType> extends AbstractElementValueParameters<Boolean, ReturnType> {
    public ElementBooleanValueParameters(
        TriFunction<DriverFunction<Boolean>, Function<Data<Boolean>, Data<ReturnType>>, Data<ReturnType>, DriverFunction<ReturnType>> handler,
        ElementFormatData<ReturnType> formatData,
        Function<LazyElement, DriverFunction<ReturnType>> function
    ) {
        super(handler, formatData, function);
    }
}
