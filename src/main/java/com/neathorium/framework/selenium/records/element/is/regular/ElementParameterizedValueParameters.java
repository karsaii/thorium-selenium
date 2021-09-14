package com.neathorium.framework.selenium.records.element.is.regular;

import com.neathorium.framework.selenium.abstracts.regular.AbstractElementFunctionParameters;
import com.neathorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.framework.selenium.records.element.is.ElementFormatData;
import com.neathorium.framework.selenium.records.lazy.LazyElement;
import com.neathorium.core.extensions.interfaces.functional.TriFunction;
import com.neathorium.core.extensions.namespaces.CoreUtilities;
import com.neathorium.core.extensions.namespaces.NullableFunctions;
import com.neathorium.core.records.Data;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ElementParameterizedValueParameters<ReturnType> extends AbstractElementFunctionParameters<String, ReturnType> {
    public final BiFunction<LazyElement, String, DriverFunction<ReturnType>> function;

    public ElementParameterizedValueParameters(
        TriFunction<DriverFunction<String>, Function<Data<String>, Data<ReturnType>>, Data<ReturnType>, DriverFunction<ReturnType>> handler,
        ElementFormatData<ReturnType> formatData,
        BiFunction<LazyElement, String, DriverFunction<ReturnType>> function
    ) {
        super(handler, formatData);
        this.function = function;
    }

    @Override
    public boolean equals(Object o) {
        if (CoreUtilities.isEqual(this, o)) {
            return true;
        }

        if (NullableFunctions.isNull(o) || CoreUtilities.isNotEqual(getClass(), o.getClass()) || CoreUtilities.isFalse(super.equals(o))) {
            return false;
        }

        final var that = (ElementParameterizedValueParameters<?>) o;
        return CoreUtilities.isEqual(function, that.function);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), function);
    }

    @Override
    public String toString() {
        return (
            "ElementParameterizedValueParameters{" +
            "handler=" + handler +
            ", formatData=" + formatData +
            ", function=" + function +
            '}'
        );
    }
}
