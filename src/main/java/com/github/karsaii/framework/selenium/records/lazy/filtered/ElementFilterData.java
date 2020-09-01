package com.github.karsaii.framework.selenium.records.lazy.filtered;

import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.core.abstracts.element.finder.BaseFilterParameters;
import com.github.karsaii.framework.core.abstracts.lazy.filtered.BaseFilterData;
import com.github.karsaii.framework.selenium.enums.ManyGetter;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.WebElementList;
import com.github.karsaii.framework.selenium.namespaces.factories.DriverFunctionFactory;
import com.github.karsaii.framework.selenium.records.element.finder.ElementFilterParameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.function.Function;

public class ElementFilterData<T> extends BaseFilterData<WebDriver, ManyGetter, T, ElementFilterParameters, WebElementList, WebElement> {
    public ElementFilterData(
        boolean isFiltered,
        Function<ElementFilterParameters, Function<T, Function<WebDriver, Data<WebElement>>>> handler,
        T filterParameter
    ) {
        super(isFiltered, handler, filterParameter);
    }

    public DriverFunction<WebElement> apply(ElementFilterParameters parameters) {
        return DriverFunctionFactory.getFunction(handler.apply(parameters).apply(filterParameter));
    }

    @Override
    public Function<WebDriver, Data<WebElement>> apply(BaseFilterParameters<WebDriver, ManyGetter, WebElementList> parameters) {
        return handler.apply((ElementFilterParameters) parameters).apply(filterParameter);
    }
}
