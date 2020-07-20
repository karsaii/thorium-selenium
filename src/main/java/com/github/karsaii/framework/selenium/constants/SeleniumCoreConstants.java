package com.github.karsaii.framework.selenium.constants;

import com.github.karsaii.core.constants.CoreDataConstants;
import com.github.karsaii.core.extensions.DecoratedList;
import com.github.karsaii.core.namespaces.validators.CoreFormatter;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.selenium.namespaces.factories.WebElementListFactory;
import com.github.karsaii.framework.selenium.namespaces.lazy.LazyElementFactory;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.WebElementList;
import com.github.karsaii.core.records.MethodSourceData;
import com.github.karsaii.framework.selenium.records.lazy.CachedLookupKeysData;
import org.openqa.selenium.WebElement;
import com.github.karsaii.framework.core.selector.records.SelectorKeySpecificityData;
import com.github.karsaii.core.constants.CoreConstants;
import com.github.karsaii.framework.selenium.namespaces.repositories.ElementRepository;
import com.github.karsaii.framework.selenium.namespaces.utilities.BasicTypeUtilities;
import com.github.karsaii.framework.selenium.records.ExternalElementData;
import com.github.karsaii.framework.selenium.records.lazy.LazyElement;
import com.github.karsaii.framework.selenium.records.lazy.CachedLazyElementData;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import static com.github.karsaii.core.extensions.namespaces.CoreUtilities.getIncrementalUUID;

public abstract class SeleniumCoreConstants {
    public static final Method[] WEBELEMENT_METHODS = WebElement.class.getMethods();
    public static final WebElement STOCK_ELEMENT = BasicTypeUtilities.getStockElement();
    public static final WebElementList NULL_ELEMENT_LIST = WebElementListFactory.getWithEmptyList();
    public static final List<Method> WEB_ELEMENT_METHOD_LIST = Arrays.asList(WEBELEMENT_METHODS);

    public static AtomicInteger ATOMIC_COUNT = new AtomicInteger();
    public static AtomicInteger ELEMENT_ATOMIC_COUNT = new AtomicInteger();
    public static final LazyElement NULL_LAZY_ELEMENT = LazyElementFactory.getWithInvalidData("Null Lazy Element " + getIncrementalUUID(ATOMIC_COUNT));
    public static final Map<String, DecoratedList<SelectorKeySpecificityData>> NULL_CACHED_KEYS = ElementRepository.getInitializedTypeKeysMap();
    public static final CachedLazyElementData NULL_CACHED_LAZY_ELEMENT_DATA = new CachedLazyElementData(NULL_LAZY_ELEMENT, NULL_CACHED_KEYS);
    public static final CachedLookupKeysData INITIAL_UNCACHED_DATA = new CachedLookupKeysData("", "", "", 0);
    public static final ExternalElementData NULL_EXTERNAL_ELEMENT_DATA = new ExternalElementData(NULL_CACHED_KEYS, SeleniumDataConstants.NULL_ELEMENT);

    public static final List<Class<?>> CLASSES_OF_GET_MECHANISMS = Arrays.asList(WebElementList.class, WebElement.class);
    public static final MethodSourceData DEFAULT_WEB_ELEMENT_METHOD_PARAMETERS = new MethodSourceData(CoreConstants.METHODS, SeleniumCoreConstants.WEB_ELEMENT_METHOD_LIST, CoreDataConstants.NULL_METHODDATA);
    public static final Function<Object, WebElement> WEB_ELEMENT_CASTER_FUNCTION = WebElement.class::cast;

    public static final Function<Data<DecoratedList<?>>, String> WEBELEMENT_LIST_VALIDATOR = CoreFormatter.isValidTypedNonEmptyListMessage(WebElement.class);
}
