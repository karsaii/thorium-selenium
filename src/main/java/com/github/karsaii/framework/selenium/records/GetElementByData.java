package com.github.karsaii.framework.selenium.records;

import com.github.karsaii.core.extensions.DecoratedList;
import com.github.karsaii.core.extensions.namespaces.CoreUtilities;
import com.github.karsaii.core.extensions.namespaces.NullableFunctions;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.core.records.GetByFilterFormatterData;
import org.openqa.selenium.WebElement;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

public class GetElementByData<T, ElementType, ListType extends DecoratedList<ElementType>> {
    public final String nameof;
    public final BiFunction<Data<ListType>, T, String> validator;
    public final BiFunction<Data<ListType>, T, ElementType> getter;
    public final Function<GetByFilterFormatterData<T>, String> formatter;
    public final Data<ElementType> defaultValue;
    public final String filterName;

    public GetElementByData(
        String nameof,
        BiFunction<Data<ListType>, T, String> validator,
        BiFunction<Data<ListType>, T, ElementType> getter,
        Function<GetByFilterFormatterData<T>, String> formatter,
        Data<ElementType> defaultValue,
        String filterName
    ) {
        this.nameof = nameof;
        this.validator = validator;
        this.getter = getter;
        this.formatter = formatter;
        this.defaultValue = defaultValue;
        this.filterName = filterName;
    }

    @Override
    public boolean equals(Object o) {
        if (CoreUtilities.isEqual(this, o)) {
            return true;
        }
        
        if (NullableFunctions.isNull(o) || CoreUtilities.isNotEqual(getClass(), o.getClass())) {
            return false;
        }

        final var that = (GetElementByData<?, ?, ?>) o;
        return (
            CoreUtilities.isEqual(nameof, that.nameof) &&
            CoreUtilities.isEqual(validator, that.validator) &&
            CoreUtilities.isEqual(getter, that.getter) &&
            CoreUtilities.isEqual(formatter, that.formatter) &&
            CoreUtilities.isEqual(defaultValue, that.defaultValue) &&
            CoreUtilities.isEqual(filterName, that.filterName)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameof, validator, getter, formatter, defaultValue, filterName);
    }
}
