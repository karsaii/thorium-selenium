package com.github.karsaii.framework.selenium.namespaces.repositories;

import com.github.karsaii.core.constants.CoreDataConstants;
import com.github.karsaii.core.extensions.DecoratedList;
import com.github.karsaii.core.extensions.namespaces.CoreUtilities;
import com.github.karsaii.core.extensions.namespaces.NullableFunctions;
import com.github.karsaii.core.namespaces.DataFactoryFunctions;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.core.constants.validators.CoreFormatterConstants;
import com.github.karsaii.framework.core.abstracts.AbstractLazyResult;
import com.github.karsaii.framework.core.namespaces.FrameworkCoreUtilities;
import com.github.karsaii.framework.core.namespaces.repositories.CoreElementRepository;
import com.github.karsaii.framework.core.namespaces.validators.FrameworkCoreFormatter;
import com.github.karsaii.framework.selenium.constants.CacheElementDefaults;
import com.github.karsaii.framework.selenium.constants.validators.SeleniumFormatterConstants;
import com.github.karsaii.core.namespaces.validators.CoreFormatter;
import com.github.karsaii.framework.core.selector.records.SelectorKeySpecificityData;
import com.github.karsaii.framework.selenium.namespaces.validators.SeleniumFormatter;
import com.github.karsaii.framework.selenium.records.CacheElementDefaultsData;
import com.github.karsaii.framework.selenium.records.ExternalElementData;
import com.github.karsaii.framework.selenium.records.lazy.filtered.LazyFilteredElementParameters;
import org.openqa.selenium.WebElement;
import selectorSpecificity.Specificity;
import selectorSpecificity.constants.Strategy;
import selectorSpecificity.tuples.SpecificityData;
import com.github.karsaii.framework.selenium.constants.SeleniumDataConstants;
import com.github.karsaii.framework.selenium.constants.ElementStrategyMapConstants;
import com.github.karsaii.framework.selenium.constants.RepositoryConstants;
import com.github.karsaii.framework.selenium.enums.SeleniumSelectorStrategy;
import com.github.karsaii.framework.selenium.namespaces.utilities.SeleniumUtilities;
import com.github.karsaii.framework.selenium.namespaces.element.validators.ElementRepositoryValidators;
import com.github.karsaii.framework.selenium.records.lazy.LazyElement;
import com.github.karsaii.framework.core.namespaces.extensions.boilers.LazyLocatorList;
import com.github.karsaii.framework.selenium.records.lazy.CachedLazyElementData;

import java.util.List;
import java.util.Map;

import static com.github.karsaii.core.extensions.namespaces.CoreUtilities.areAnyNull;
import static com.github.karsaii.core.namespaces.DataFactoryFunctions.appendMessage;
import static com.github.karsaii.core.namespaces.predicates.DataPredicates.isInvalidOrFalse;
import static com.github.karsaii.core.namespaces.predicates.DataPredicates.isValidNonFalse;
import static com.github.karsaii.core.namespaces.DataFactoryFunctions.prependMessage;
import static com.github.karsaii.core.namespaces.DataFactoryFunctions.replaceMessage;

import static com.github.karsaii.framework.selenium.namespaces.utilities.SeleniumUtilities.isNotNullWebElement;
import static com.github.karsaii.framework.selenium.namespaces.validators.SeleniumFormatter.getNotCachedMessage;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public interface ElementRepository {
    private static Data<Boolean> cacheElement(Map<String, CachedLazyElementData> repository, CachedLazyElementData data) {
        final var name = data.element.name;
        final var returnValue = repository.put(name, data);
        final var errorMessage = getNotCachedMessage(repository, name);
        final var status = NullableFunctions.isNull(returnValue) && isBlank(errorMessage);
        final var message = status ? SeleniumFormatterConstants.LAZY_ELEMENT + "with name(\"" + name + "\") added to cache" + CoreFormatterConstants.END_LINE : errorMessage;
        return DataFactoryFunctions.getBoolean(status, CacheElementDefaults.FUNCTION_NAME, message);
    }

    static Data<Boolean> cacheElement(CacheElementDefaultsData defaults, CachedLazyElementData data) {
        var errorMessage = SeleniumFormatter.getCacheElementDefaultsDataInvalidMessage(defaults);
        if (isNotBlank(errorMessage)) {
            return replaceMessage(CoreDataConstants.NULL_BOOLEAN, "cacheElement", errorMessage);
        }

        errorMessage = SeleniumFormatter.getCacheElementParametersErrorMessage(defaults, data);
        return isBlank(errorMessage) ? cacheElement(defaults.repository, data) : replaceMessage(defaults.defaultValue, defaults.name, errorMessage);
    }

    static Data<Boolean> cacheElement(LazyElement element, Map<String, DecoratedList<SelectorKeySpecificityData>> typeKeys) {
        return cacheElement(CacheElementDefaults.CACHE_DEFAULT, new CachedLazyElementData(element, typeKeys));
    }

    static Data<Boolean> containsElement(Map<String, CachedLazyElementData> elementRepository, String name, Data<Boolean> defaultValue) {
        final var nameof = "containsElement";
        final var errorMessage = ElementRepositoryValidators.isInvalidContainsElementMessage(elementRepository, name, defaultValue);
        if (isNotBlank(errorMessage)) {
            return replaceMessage(defaultValue, nameof, errorMessage);
        }

        final var defaultObject = defaultValue.object;
        final var object = elementRepository.containsKey(name);
        final var message = SeleniumFormatterConstants.LAZY_ELEMENT + CoreFormatter.getOptionMessage(object) + " found by name(\"" + name + "\")" + CoreFormatterConstants.END_LINE;

        return DataFactoryFunctions.getWithNameAndMessage(object, true, nameof, message);
    }

    static Data<Boolean> containsElement(String name, Data<Boolean> defaultValue) {
        return containsElement(RepositoryConstants.elements, name, defaultValue);
    }

    static Data<Boolean> containsElement(String name) {
        return containsElement(RepositoryConstants.elements, name, CoreDataConstants.NULL_BOOLEAN);
    }

    static Data<CachedLazyElementData> getElement(Map<String, CachedLazyElementData> elementRepository, String name, Data<CachedLazyElementData> defaultValue) {
        final var nameof = "getElement";
        final var errorMessage = ElementRepositoryValidators.isInvalidContainsElementMessage(elementRepository, name, defaultValue);
        if (isNotBlank(errorMessage)) {
            return replaceMessage(defaultValue, nameof, errorMessage);
        }

        final var defaultObject = defaultValue.object;
        final var object = elementRepository.getOrDefault(name, defaultObject);
        final var status = CoreUtilities.isNotEqual(object, defaultObject);
        final var message = SeleniumFormatterConstants.LAZY_ELEMENT + CoreFormatter.getOptionMessage(status) + " found by name(\"" + name + "\")" + CoreFormatterConstants.END_LINE;

        return DataFactoryFunctions.getWithNameAndMessage(object, status, nameof, message);
    }

    static Data<CachedLazyElementData> getElement(String name, Data<CachedLazyElementData> defaultValue) {
        return getElement(RepositoryConstants.elements, name, defaultValue);
    }

    static Data<CachedLazyElementData> getElement(Map<String, CachedLazyElementData> elementRepository, String name) {
        return getElement(elementRepository, name, SeleniumDataConstants.NULL_CACHED_LAZY_ELEMENT);
    }

    static Data<CachedLazyElementData> getElement(String name) {
        return getElement(RepositoryConstants.elements, name);
    }

    static <T> String cacheIfAbsent(AbstractLazyResult<LazyFilteredElementParameters> element, Map<String, DecoratedList<SelectorKeySpecificityData>> typeKeys) {
        final var cached = ElementRepository.containsElement(element.name);

        var message = "";
        if (isValidNonFalse(cached) && !cached.object) {
            final var cachedElement = new LazyElement(element.name, SeleniumUtilities.getParametersCopy(element.parameters), element.validator);
            message = ElementRepository.cacheElement(cachedElement, typeKeys).message.toString();
        }

        return message;
    }

    static Map<String, DecoratedList<SelectorKeySpecificityData>> getInitializedTypeKeysMap() {
        return CoreElementRepository.getInitializedTypeKeysMap(ElementStrategyMapConstants.STRATEGY_MAP_KEY_SET, String.class);
    }

    static <T> Data<CachedLazyElementData> getIfContains(AbstractLazyResult<T> element) {
        final var name = element.name;
        return isValidNonFalse(ElementRepository.containsElement(name)) ? ElementRepository.getElement(name) : SeleniumDataConstants.ELEMENT_WAS_NOT_CACHED;
    }

    static Data<Boolean> updateTypeKeys(LazyLocatorList locators, Map<String, DecoratedList<SelectorKeySpecificityData>> typeKeys, List<String> types, String key) {
        final var nameof = "updateTypeKeys";
        if (NullableFunctions.isNull(key)) {
            return replaceMessage(CoreDataConstants.NULL_BOOLEAN, nameof, "Strategy passed" + CoreFormatterConstants.WAS_NULL);
        }

        final var typeKey = types.stream().filter(key::contains).findFirst();
        if (typeKey.isEmpty()) {
            return replaceMessage(CoreDataConstants.NULL_BOOLEAN, nameof, "Types didn't contain type key(\"" + typeKey + "\")" + CoreFormatterConstants.END_LINE);
        }

        final var type = typeKeys.get(typeKey.get());
        final var selectorKeySpecificityData = getSpecificityForSelector(locators, key);
        if (NullableFunctions.isNotNull(type)) {
            type.addNullSafe(selectorKeySpecificityData);
        }

        final var status = type.contains(selectorKeySpecificityData);
        final var message = "Key(\"" + key + "\") with specificity was " + CoreFormatter.getOptionMessage(status) + " added" + CoreFormatterConstants.END_LINE;
        return DataFactoryFunctions.getBoolean(status, message);
    }

    static Data<Boolean> updateTypeKeys(String name, LazyLocatorList locators, Map<String, DecoratedList<SelectorKeySpecificityData>> typeKeys, List<String> types, String key) {
        final var nameof = "updateTypeKeys";
        final var cached = containsElement(name);
        if (isInvalidOrFalse(cached)) {
            return prependMessage(cached, nameof, "There were parameter issues" + CoreFormatterConstants.END_LINE);
        }

        return !cached.object ? (updateTypeKeys(locators, typeKeys, types, key)) : DataFactoryFunctions.getBoolean(true, nameof, "Element(\"" + name + "\") was already cached" + CoreFormatterConstants.END_LINE);
    }

    static SelectorKeySpecificityData getSpecificityForSelector(LazyLocatorList list, String key) {
        return new SelectorKeySpecificityData(
            key,
            Specificity.reduce(
                list.stream().map(
                    locator -> Specificity.getSelectorSpecificity(locator.locator, (CoreUtilities.isEqual(locator.strategy, SeleniumSelectorStrategy.CSS_SELECTOR) ? Strategy.CSS_SELECTOR : Strategy.XPATH)).specifics
                ).toArray(SpecificityData[]::new)
            )
        );
    }

    static Data<WebElement> cacheValidLazyElement(AbstractLazyResult<LazyFilteredElementParameters> element, Data<ExternalElementData> regular, Data<ExternalElementData> external) {
        final var nameof = "cacheValidLazyElement";
        if (isNotBlank(FrameworkCoreFormatter.isNullLazyElementMessage(element)) || areAnyNull(regular, external)) {
            return replaceMessage(SeleniumDataConstants.NULL_ELEMENT, nameof, CoreFormatterConstants.PARAMETER_ISSUES + CoreFormatterConstants.WAS_NULL);
        }

        final var regularStatus = isValidNonFalse(regular);
        final var externalStatus = isValidNonFalse(external);
        if (!(regularStatus || externalStatus)) {
            return SeleniumDataConstants.NULL_ELEMENT;
        }

        final var externalElement = (externalStatus ? external : regular).object;
        final var currentElement = externalElement.found;
        return isNotNullWebElement(currentElement) ? (
            appendMessage(currentElement, ElementRepository.cacheIfAbsent(element, FrameworkCoreUtilities.getKeysCopy(externalElement.typeKeys)))
        ) : prependMessage(currentElement, "All approaches were tried" + CoreFormatterConstants.END_LINE);
    }
}
