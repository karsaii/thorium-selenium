package com.neathorium.framework.selenium.namespaces.repositories;

import com.neathorium.framework.selenium.constants.RepositoryConstants;
import com.neathorium.framework.selenium.constants.SeleniumDataConstants;
import com.neathorium.framework.selenium.constants.validators.SeleniumFormatterConstants;
import com.neathorium.framework.selenium.enums.SingleGetter;
import com.neathorium.framework.selenium.namespaces.factories.lazy.LazyElementFactory;
import com.neathorium.framework.selenium.records.lazy.CachedLazyElementData;
import com.neathorium.framework.selenium.records.lazy.LazyElement;
import com.neathorium.core.constants.CoreDataConstants;
import com.neathorium.core.constants.validators.CoreFormatterConstants;
import com.neathorium.core.namespaces.DataFactoryFunctions;
import com.neathorium.core.namespaces.validators.CoreFormatter;
import com.neathorium.core.records.Data;
import org.openqa.selenium.By;

import java.util.Map;

import static com.neathorium.core.namespaces.DataFactoryFunctions.replaceMessage;
import static org.apache.commons.lang3.StringUtils.isBlank;

public interface LocatorRepository {

    static Data<LazyElement> getIfContains(Map<By, String> locatorRepository, Map<String, CachedLazyElementData> elementRepository, By locator, SingleGetter getter) {
        final var nameof = "getIfContains";
        final var element = locatorRepository.containsKey(locator) ? ElementRepository.getElement(locatorRepository.get(locator)).object.element : LazyElementFactory.getWithFilterParameters(locator, getter);
        final var result = cacheLocator(locatorRepository, locator, element.name, CoreDataConstants.NULL_BOOLEAN);
        final var status = result.object;
        return status ?
            DataFactoryFunctions.getWith(element, result.status, nameof, result.message.formatter.apply(result.message.nameof, result.message.message)) :
            DataFactoryFunctions.replaceMessage(SeleniumDataConstants.NULL_LAZY_ELEMENT, nameof, "Locator was " + CoreFormatter.getOptionMessage(status) + " found" + CoreFormatterConstants.END_LINE);
    }

    static Data<LazyElement> getIfContains(Map<By, String> locatorRepository, Map<String, CachedLazyElementData> elementRepository, By locator) {
        return getIfContains(locatorRepository, elementRepository, locator, SingleGetter.DEFAULT);
    }

    static Data<LazyElement> getIfContains(By locator, SingleGetter getter) {
        return getIfContains(RepositoryConstants.LOCATOR_ELEMENTS, RepositoryConstants.CACHED_ELEMENTS, locator, getter);
    }

    static Data<LazyElement> getIfContains(By locator) {
        return getIfContains(RepositoryConstants.LOCATOR_ELEMENTS, RepositoryConstants.CACHED_ELEMENTS, locator, SingleGetter.DEFAULT);
    }

    static Data<Boolean> cacheLocator(Map<By, String> locatorRepository, By locator, String name, Data<Boolean> defaultValue) {
        final var nameof = "cacheLocator";
        if (isBlank(name)) {
            return replaceMessage(defaultValue, nameof, SeleniumFormatterConstants.LOCATOR + "with name " + CoreFormatterConstants.WAS_NULL);
        }

        if (locatorRepository.containsKey(locator)) {
            return DataFactoryFunctions.getWith(true, false, nameof, SeleniumFormatterConstants.LOCATOR + " with name(\"" + name + "\") was already stored" + CoreFormatterConstants.END_LINE);
        }

        locatorRepository.putIfAbsent(locator, name);
        final var status = locatorRepository.containsKey(locator);
        final var message = "New Lazy Element, by locator, with name(\""    + name + "\") " + CoreFormatter.getOptionMessage(status) + " added to cache" + CoreFormatterConstants.END_LINE;
        return DataFactoryFunctions.getBoolean(status, nameof, message);
    }

    static Data<Boolean> cacheLocator(Map<By, String> locatorRepository, By locator, String name) {
        return cacheLocator(locatorRepository, locator, name, CoreDataConstants.NULL_BOOLEAN);
    }

    static Data<Boolean> cacheLocator(By locator, String name, Data<Boolean> defaultValue) {
        return cacheLocator(RepositoryConstants.LOCATOR_ELEMENTS, locator, name, defaultValue);
    }

    static Data<Boolean> cacheLocator(By locator, String name) {
        return cacheLocator(RepositoryConstants.LOCATOR_ELEMENTS, locator, name, CoreDataConstants.NULL_BOOLEAN);
    }
}