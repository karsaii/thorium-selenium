package com.github.karsaii.framework.selenium.namespaces.factories;

import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.selenium.namespaces.element.ElementFilterFunctions;
import com.github.karsaii.framework.selenium.records.element.finder.ElementFilterParameters;
import com.github.karsaii.framework.selenium.records.lazy.filtered.ElementFilterData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.function.Function;

public interface ElementFilterDataFactory {
    static <T> ElementFilterData<T> getWith(
        boolean isFiltered,
        Function<ElementFilterParameters, Function<T, Function<WebDriver, Data<WebElement>>>> handler,
        T filterParameter
    ) {
        return new ElementFilterData<>(isFiltered, handler, filterParameter);
    }

    static ElementFilterData<String> getWithDefaultStringHandler(boolean isFiltered, String filterParameter) {
        return getWith(isFiltered, ElementFilterFunctions::getContainedTextElement, filterParameter);
    }

    static ElementFilterData<Integer> getWithDefaultIndexHandler(boolean isFiltered, int filterParameter) {
        return getWith(isFiltered, ElementFilterFunctions::getIndexedElement, filterParameter);
    }
}
