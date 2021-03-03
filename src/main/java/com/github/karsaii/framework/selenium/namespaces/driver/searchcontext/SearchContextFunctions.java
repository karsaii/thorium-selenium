package com.github.karsaii.framework.selenium.namespaces.driver.searchcontext;

import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.selenium.constants.FactoryConstants;
import com.github.karsaii.framework.selenium.namespaces.driver.typeconversion.DriverTypeConversionFunctions;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.github.karsaii.core.namespaces.predicates.DataPredicates.isValidNonFalse;

public interface SearchContextFunctions {
    private static <T extends SearchContext> SearchContext getSearchContextOf(T object) {
        return object;
    }

    static <T extends SearchContext> Data<SearchContext> getSearchContextOf(String dependencyName, Data<T> data) {
        return isValidNonFalse(data) ? (
            DriverTypeConversionFunctions.getSubtypeOf(dependencyName, data.object, SearchContextFunctions::getSearchContextOf, FactoryConstants.NULL_SEARCH_CONTEXT)
        ) : FactoryConstants.NULL_SEARCH_CONTEXT_DATA;
    }

    static Data<SearchContext> getSearchContext(WebElement element) {
        return DriverTypeConversionFunctions.getSubtypeOf("Search Context (Element)", element, SearchContextFunctions::getSearchContextOf, FactoryConstants.NULL_SEARCH_CONTEXT);
    }

    static Data<SearchContext> getSearchContext(WebDriver driver) {
        return DriverTypeConversionFunctions.getSubtypeOf("Search Context (Driver)", driver, SearchContextFunctions::getSearchContextOf, FactoryConstants.NULL_SEARCH_CONTEXT);
    }
}
