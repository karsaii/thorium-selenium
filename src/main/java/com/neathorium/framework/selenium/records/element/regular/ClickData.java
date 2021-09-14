package com.neathorium.framework.selenium.records.element.regular;

import com.neathorium.framework.selenium.enums.SingleGetter;
import com.neathorium.framework.selenium.namespaces.element.Element;
import com.neathorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.framework.selenium.records.lazy.LazyElement;
import org.openqa.selenium.By;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ClickData {
    public final Function<LazyElement, DriverFunction<Boolean>> clickLazy;
    public final BiFunction<By, SingleGetter, DriverFunction<Boolean>> clickByWithGetter;
    public final Function<By, DriverFunction<Boolean>> clickBy;

    public ClickData(
        Function<LazyElement, DriverFunction<Boolean>> clickLazy,
        BiFunction<By, SingleGetter, DriverFunction<Boolean>> clickByWithGetter,
        Function<By, DriverFunction<Boolean>> clickBy
    ) {
        this.clickLazy = clickLazy;
        this.clickByWithGetter = clickByWithGetter;
        this.clickBy = clickBy;
    }

    public ClickData() {
        this.clickLazy = Element::click;
        this.clickByWithGetter = Element::click;
        this.clickBy = Element::click;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var clickData = (ClickData) o;
        return Objects.equals(clickLazy, clickData.clickLazy) && Objects.equals(clickByWithGetter, clickData.clickByWithGetter) && Objects.equals(clickBy, clickData.clickBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clickLazy, clickByWithGetter, clickBy);
    }
}
