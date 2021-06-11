package com.github.karsaii.framework.selenium.records.element.regular;

import com.github.karsaii.core.extensions.namespaces.CoreUtilities;
import com.github.karsaii.core.extensions.namespaces.NullableFunctions;
import com.github.karsaii.framework.selenium.enums.SingleGetter;
import com.github.karsaii.framework.selenium.namespaces.element.Element;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.github.karsaii.framework.selenium.records.lazy.LazyElement;
import org.openqa.selenium.By;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ClearData {
    public final Function<LazyElement, DriverFunction<Boolean>> clearLazy;
    public final BiFunction<By, SingleGetter, DriverFunction<Boolean>> clearByWithGetter;
    public final Function<By, DriverFunction<Boolean>> clearBy;

    public ClearData(
        Function<LazyElement, DriverFunction<Boolean>> clearLazy,
        BiFunction<By, SingleGetter, DriverFunction<Boolean>> clearByWithGetter,
        Function<By, DriverFunction<Boolean>> clearBy
    ) {
        this.clearLazy = clearLazy;
        this.clearByWithGetter = clearByWithGetter;
        this.clearBy = clearBy;
    }

    public ClearData() {
        this.clearLazy = Element::clear;
        this.clearByWithGetter = Element::clear;
        this.clearBy = Element::clear;
    }

    @Override
    public boolean equals(Object o) {
        if (CoreUtilities.isEqual(this, o)) {
            return true;
        }

        if (NullableFunctions.isNull(o) || CoreUtilities.isNotEqual(getClass(), o.getClass())) {
            return false;
        }

        final var clearData = (ClearData) o;
        return (
            CoreUtilities.isEqual(clearLazy, clearData.clearLazy) &&
            CoreUtilities.isEqual(clearByWithGetter, clearData.clearByWithGetter) &&
            CoreUtilities.isEqual(clearBy, clearData.clearBy)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(clearLazy, clearByWithGetter, clearBy);
    }
}
