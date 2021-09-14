package com.neathorium.framework.selenium.namespaces;

import com.neathorium.framework.selenium.namespaces.extensions.boilers.WebElementList;
import com.neathorium.core.extensions.namespaces.DecoratedListFunctions;
import com.neathorium.core.extensions.namespaces.NullableFunctions;
import com.neathorium.core.namespaces.predicates.DataPredicates;
import com.neathorium.core.records.Data;

import java.util.function.Predicate;


public interface SeleniumDataFunctions {
    private static <T> boolean isOfTypeNonEmpty(Data<WebElementList> listData, Class<T> clazz) {
        return (
            DataPredicates.isValidNonFalse(listData) &&
            NullableFunctions.isNotNull(clazz) &&
            DecoratedListFunctions.isOfTypeNonEmpty(listData.object, clazz)
        );
    }

    static <T> Predicate<Data<WebElementList>> isOfTypeNonEmpty(Class<T> clazz) {
        return list -> isOfTypeNonEmpty(list, clazz);
    }
}
