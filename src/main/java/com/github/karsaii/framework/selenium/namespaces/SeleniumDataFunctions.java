package com.github.karsaii.framework.selenium.namespaces;

import com.github.karsaii.core.extensions.namespaces.DecoratedListFunctions;
import com.github.karsaii.core.extensions.namespaces.NullableFunctions;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.WebElementList;

import java.util.function.Predicate;

import static com.github.karsaii.core.namespaces.predicates.DataPredicates.isValidNonFalse;

public interface SeleniumDataFunctions {
    private static <T> boolean isOfTypeNonEmpty(Data<WebElementList> listData, Class<T> clazz) {
        return (
            isValidNonFalse(listData) &&
            NullableFunctions.isNotNull(clazz) &&
            DecoratedListFunctions.isOfTypeNonEmpty(listData.object, clazz)
        );
    }

    static <T> Predicate<Data<WebElementList>> isOfTypeNonEmpty(Class<T> clazz) {
        return list -> isOfTypeNonEmpty(list, clazz);
    }
}
