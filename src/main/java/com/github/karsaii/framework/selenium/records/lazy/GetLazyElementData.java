package com.github.karsaii.framework.selenium.records.lazy;

import com.github.karsaii.core.extensions.interfaces.functional.TriFunction;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.core.abstracts.AbstractLazyResult;
import com.github.karsaii.framework.core.abstracts.lazy.filtered.BaseFilterData;
import com.github.karsaii.framework.core.namespaces.extensions.boilers.LazyLocatorList;
import com.github.karsaii.framework.selenium.enums.ManyGetter;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.github.karsaii.framework.selenium.records.ExternalElementData;
import com.github.karsaii.framework.selenium.records.element.finder.ElementFilterParameters;
import com.github.karsaii.framework.selenium.records.lazy.filtered.LazyFilteredElementParameters;
import org.openqa.selenium.WebDriver;

import java.util.Objects;
import java.util.function.Predicate;

public class GetLazyElementData<ReturnType, ListType> {
    public final TriFunction<Data<ReturnType>, Integer, Integer, Boolean> exitCondition;
    public final TriFunction<AbstractLazyResult<LazyFilteredElementParameters>, Data<ExternalElementData>, Data<ExternalElementData>, Data<ReturnType>> cacheFunction;
    public final TriFunction<
        BaseFilterData<WebDriver, ManyGetter, ?, ElementFilterParameters, ListType, ReturnType>,
        LazyLocatorList,
        String,
        DriverFunction<ReturnType>
    > getter;
    public final Predicate<Data<ReturnType>> invalidator;
    public final Data<ReturnType> defaultValue;

    public GetLazyElementData(
        TriFunction<Data<ReturnType>, Integer, Integer, Boolean> exitCondition,
        TriFunction<AbstractLazyResult<LazyFilteredElementParameters>, Data<ExternalElementData>, Data<ExternalElementData>, Data<ReturnType>> cacheFunction,
        TriFunction<BaseFilterData<WebDriver, ManyGetter, ?, ElementFilterParameters, ListType, ReturnType>, LazyLocatorList, String, DriverFunction<ReturnType>> getter,
        Predicate<Data<ReturnType>> invalidator,
        Data<ReturnType> defaultValue
    ) {
        this.exitCondition = exitCondition;
        this.cacheFunction = cacheFunction;
        this.getter = getter;
        this.invalidator = invalidator;
        this.defaultValue = defaultValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final var that = (GetLazyElementData<?, ?>) o;
        return (
            Objects.equals(exitCondition, that.exitCondition) &&
            Objects.equals(cacheFunction, that.cacheFunction) &&
            Objects.equals(getter, that.getter) &&
            Objects.equals(invalidator, that.invalidator) &&
            Objects.equals(defaultValue, that.defaultValue)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(exitCondition, cacheFunction, getter, invalidator, defaultValue);
    }
}
