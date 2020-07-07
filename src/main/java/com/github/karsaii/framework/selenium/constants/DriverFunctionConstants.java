package com.github.karsaii.framework.selenium.constants;

import com.github.karsaii.core.constants.CoreDataConstants;
import com.github.karsaii.core.namespaces.StringUtilities;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.core.records.TypedEnumKeyData;
import com.github.karsaii.core.extensions.boilers.StringSet;
import com.github.karsaii.framework.core.namespaces.validators.FrameworkCoreFormatter;
import com.github.karsaii.framework.selenium.namespaces.element.validators.ElementGetterValidators;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.WebElementList;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.github.karsaii.framework.selenium.records.GetElementByData;
import org.openqa.selenium.WebElement;
import com.github.karsaii.core.enums.TypeKey;
import com.github.karsaii.framework.selenium.namespaces.factories.DriverFunctionFactory;

import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Map.entry;

public abstract class DriverFunctionConstants {
    public static final DriverFunction<Void> NULL_VOID = DriverFunctionFactory.get(CoreDataConstants.NULL_VOID);
    public static final DriverFunction<Object> NULL_OBJECT = DriverFunctionFactory.get(CoreDataConstants.NULL_OBJECT);
    public static final DriverFunction<Boolean> NULL_BOOLEAN = DriverFunctionFactory.get(CoreDataConstants.NULL_BOOLEAN);
    public static final DriverFunction<String> NULL_STRING = DriverFunctionFactory.get(CoreDataConstants.NULL_STRING);
    public static final DriverFunction<Integer> NULL_INTEGER = DriverFunctionFactory.get(CoreDataConstants.NULL_INTEGER);
    public static final DriverFunction<StringSet> NULL_STRINGSET = DriverFunctionFactory.get(CoreDataConstants.NULL_STRING_SET);
    public static final DriverFunction<Boolean> LAZY_ELEMENT_WAIT_PARAMETERS_WERE_NULL = DriverFunctionFactory.get(SeleniumDataConstants.LAZY_ELEMENT_WAIT_PARAMETERS_WERE_NULL);
    public static final DriverFunction<Boolean> LAZY_ELEMENT_WAS_NULL = DriverFunctionFactory.get(SeleniumDataConstants.LAZY_ELEMENT_WAS_NULL);
    public static final DriverFunction<WebElement> NULL_WEBELEMENT = DriverFunctionFactory.get(SeleniumDataConstants.NULL_ELEMENT);
    public static final DriverFunction<WebElementList> NULL_LIST = DriverFunctionFactory.get(SeleniumDataConstants.NULL_LIST);

    public static final Map<TypeKey, DriverFunction<?>> FUNCTION_MAP = Collections.unmodifiableMap(
        new EnumMap<>(
            Map.ofEntries(
                entry(TypeKey.BOOLEAN, NULL_BOOLEAN),
                entry(TypeKey.INTEGER, NULL_INTEGER),
                entry(TypeKey.VOID, NULL_VOID),
                entry(TypeKey.WEB_ELEMENT, NULL_WEBELEMENT),
                entry(TypeKey.STRING, NULL_STRING),
                entry(TypeKey.STRING_SET, NULL_STRINGSET),
                entry(TypeKey.WEB_ELEMENT_LIST, NULL_LIST),
                entry(TypeKey.OBJECT, NULL_OBJECT)
            )
        )
    );

    public static final TypedEnumKeyData<Boolean> BOOLEAN_FUNCTION_KEY = new TypedEnumKeyData<>(TypeKey.BOOLEAN, Boolean.class);
    public static final TypedEnumKeyData<Integer> INTEGER_FUNCTION_KEY = new TypedEnumKeyData<>(TypeKey.INTEGER, Integer.class);
    public static final TypedEnumKeyData<String> STRING_FUNCTION_KEY = new TypedEnumKeyData<>(TypeKey.STRING, String.class);
    public static final TypedEnumKeyData<StringSet> STRING_SET_FUNCTION_KEY = new TypedEnumKeyData<>(TypeKey.STRING_SET, StringSet.class);
    public static final TypedEnumKeyData<Object> OBJECT_FUNCTION_KEY = new TypedEnumKeyData<>(TypeKey.OBJECT, Object.class);
    public static final TypedEnumKeyData<Void> VOID_FUNCTION_KEY = new TypedEnumKeyData<>(TypeKey.VOID, Void.class);
    public static final TypedEnumKeyData<WebElement> WEB_ELEMENT_FUNCTION_KEY = new TypedEnumKeyData<>(TypeKey.WEB_ELEMENT, WebElement.class);
    public static final TypedEnumKeyData<WebElementList> WEB_ELEMENT_LIST_FUNCTION_KEY = new TypedEnumKeyData<>(TypeKey.WEB_ELEMENT_LIST, WebElementList.class);

    public static final Map<Class<?>, TypeKey> TYPE_MAP = Collections.unmodifiableMap(
        new LinkedHashMap<>(
            Map.ofEntries(
                entry(String.class, TypeKey.STRING),
                entry(Boolean.class, TypeKey.BOOLEAN)
            )
        )
    );

    private static WebElement getByIndex(Data<WebElementList> data, int index) {
        return data.object.get(index);
    }

    private static WebElement getByContainedText(Data<WebElementList> data, String text) {
        final var list = data.object;
        final var size = list.size();
        var object = SeleniumCoreConstants.STOCK_ELEMENT;
        var index = 0;
        for (; (index < size); ++index) {
            object = list.get(index);
            if (StringUtilities.contains(object.getText(), text)) {
                break;
            }
        }

        return object;
    }

    public static final GetElementByData<String, WebElementList> BY_CONTAINED_TEXT_CONSTANTS = new GetElementByData<>(
        "getElementByContainedText",
        ElementGetterValidators::isInvalidElementByTextParameters,
        DriverFunctionConstants::getByContainedText,
        FrameworkCoreFormatter::getByFilterMessage,
        SeleniumDataConstants.NULL_ELEMENT,
        "Text"
    );

    public static final GetElementByData<Integer, WebElementList> BY_CONTAINED_INTEGER_CONSTANTS = new GetElementByData<>(
        "getElementByIndex",
        ElementGetterValidators::isInvalidElementByIndexParameters,
        DriverFunctionConstants::getByIndex,
        FrameworkCoreFormatter::getByFilterMessage,
        SeleniumDataConstants.NULL_ELEMENT,
        "Index"
    );
}
