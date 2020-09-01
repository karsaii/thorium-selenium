package com.github.karsaii.framework.selenium.constants;

import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.core.namespaces.extensions.boilers.LazyLocatorList;
import com.github.karsaii.framework.selenium.enums.ManyGetter;
import com.github.karsaii.framework.selenium.enums.SingleGetter;
import com.github.karsaii.framework.selenium.namespaces.Driver;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.WebElementList;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

import static java.util.Map.entry;

public abstract class ElementFinderConstants {
    public static final Map<SingleGetter, Function<LazyLocatorList, Function<WebDriver, Data<WebElement>>>> singleGetterMap = Collections.unmodifiableMap(
        new EnumMap<>(
            Map.ofEntries(
                entry(SingleGetter.GET_ELEMENT, Driver::getElementFromSingle),
                entry(SingleGetter.GET_ROOT_ELEMENT, Driver::getRootElementFromSingle),
                entry(SingleGetter.GET_NESTED_ELEMENT, Driver::getNestedElement),
                entry(SingleGetter.GET_FRAME_NESTED_ELEMENT, Driver::getFrameNestedElement),
                entry(SingleGetter.GET_SHADOW_ROOT_ELEMENT, Driver::getShadowRootElement),
                entry(SingleGetter.GET_SHADOW_NESTED_ELEMENT, Driver::getShadowNestedElement)
            )
        )
    );

    public static final Map<ManyGetter, Function<LazyLocatorList, Function<WebDriver, Data<WebElementList>>>> manyGetterMap = Collections.unmodifiableMap(
        new EnumMap<>(
            Map.ofEntries(
                entry(ManyGetter.GET_ELEMENTS, Driver::getElements),
                entry(ManyGetter.GET_NESTED_ELEMENTS_FROM_LAST, Driver::getNestedElementsFromLast),
                entry(ManyGetter.GET_FRAME_NESTED_ELEMENTS, Driver::getFrameNestedElementsFromLast),
                entry(ManyGetter.GET_SHADOW_NESTED_ELEMENTS, Driver::getShadowNestedElementsFromLast)
            )
        )
    );

    public static final Map<String, Function<LazyLocatorList, DriverFunction<WebElement>>> frameAmountStrategyMap = Collections.unmodifiableMap(
        new LinkedHashMap<>(
            Map.ofEntries(
                entry("true", Driver::getNestedElement),
                entry("false", Driver::getElementFromSingle)
            )
        )
    );

    public static final Map<String, Function<LazyLocatorList, DriverFunction<Boolean>>> frameNestedStrategyMap = Collections.unmodifiableMap(
        new LinkedHashMap<>(
            Map.ofEntries(
                entry("true", Driver::switchToNestedFrame),
                entry("false", Driver::switchToFrameFromSingle)
            )
        )
    );
}
