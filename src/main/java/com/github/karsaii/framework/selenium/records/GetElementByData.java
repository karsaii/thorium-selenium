package com.github.karsaii.framework.selenium.records;

import com.github.karsaii.core.extensions.DecoratedList;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.core.records.GetByFilterFormatterData;
import org.openqa.selenium.WebElement;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;


public class GetElementByData<T, U extends DecoratedList<WebElement>> {
    public final String nameof;
    public final BiFunction<Data<U>, T, String> validator;
    public final BiFunction<Data<U>, T, WebElement> getter;
    public final Function<GetByFilterFormatterData<T>, String> formatter;
    public final Data<WebElement> defaultValue;
    public final String filterName;

    public GetElementByData(
        String nameof,
        BiFunction<Data<U>, T, String> validator,
        BiFunction<Data<U>, T, WebElement> getter,
        Function<GetByFilterFormatterData<T>, String> formatter,
        Data<WebElement> defaultValue,
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final var that = (GetElementByData<?, ?>) o;
        return (
            Objects.equals(nameof, that.nameof) &&
            Objects.equals(validator, that.validator) &&
            Objects.equals(getter, that.getter) &&
            Objects.equals(formatter, that.formatter) &&
            Objects.equals(defaultValue, that.defaultValue) &&
            Objects.equals(filterName, that.filterName)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameof, validator, getter, formatter, defaultValue, filterName);
    }
}
