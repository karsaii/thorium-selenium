package com.github.karsaii.framework.selenium.namespaces.factories;

import com.github.karsaii.core.extensions.DecoratedList;
import com.github.karsaii.core.namespaces.DataFactoryFunctions;
import com.github.karsaii.core.records.Data;

import java.util.function.Function;

public interface SeleniumDataFactory {
    static <T, V extends DecoratedList<?>> Data<DecoratedList<?>> getUnwrapped(Data<V> data) {
        return DataFactoryFunctions.getWith(data.object.get(), data.status, data.message, data.exception, data.exceptionMessage);
    }

    static <T, V extends DecoratedList<T>> Function<Data<V>, Data<DecoratedList<?>>> getUnwrapped() {
        return data -> DataFactoryFunctions.getWith(data.object.get(), data.status, data.message, data.exception, data.exceptionMessage);
    }
}
