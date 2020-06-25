package com.github.karsaii.framework.selenium.namespaces.lazy;

import com.github.karsaii.framework.core.namespaces.factory.LazyLocatorListFactory;
import com.github.karsaii.framework.selenium.namespaces.element.ElementFilterFunctions;
import com.github.karsaii.framework.core.namespaces.extensions.boilers.LazyLocatorList;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.WebElementList;
import com.github.karsaii.framework.selenium.records.lazy.filtered.ElementFilterData;
import com.github.karsaii.framework.selenium.records.lazy.filtered.LazyFilteredElementParameters;
import com.github.karsaii.framework.core.records.lazy.LazyLocator;
import org.openqa.selenium.WebElement;

import static com.github.karsaii.framework.core.constants.lazy.CommonLazyIndexedFactoryConstants.FIRST_INDEX;
import static com.github.karsaii.framework.core.constants.lazy.CommonLazyIndexedFactoryConstants.PROBABILITY;
import static com.github.karsaii.framework.selenium.constants.LazyIndexedElementFactoryConstants.GETTER;


public interface LazyIndexedElementFactory {
    static LazyFilteredElementParameters getWithFilterDataAndLocatorList(ElementFilterData<?> data, double probability, LazyLocatorList lazyLocators, String getter) {
        return new LazyFilteredElementParameters(data, data.isFiltered ? WebElement.class : WebElementList.class, probability, lazyLocators, getter);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocatorList(boolean isFiltered, String message, double probability, LazyLocatorList lazyLocators, String getter) {
        return getWithFilterDataAndLocatorList(new ElementFilterData<String>(isFiltered, ElementFilterFunctions::getContainedTextElement, message), probability, lazyLocators, getter);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocatorList(boolean isFiltered, int index, double probability, LazyLocatorList lazyLocators, String getter) {
        return getWithFilterDataAndLocatorList(new ElementFilterData<>(isFiltered, ElementFilterFunctions::getIndexedElement, index), probability, lazyLocators, getter);
    }

    static LazyFilteredElementParameters getWithFilterDataAndLocatorList(ElementFilterData<?> data, double probability, LazyLocatorList lazyLocators) {
        return getWithFilterDataAndLocatorList(data, probability, lazyLocators, GETTER);
    }

    static LazyFilteredElementParameters getWithFilterDataAndLocatorList(ElementFilterData<?> data, LazyLocatorList lazyLocators, String getter) {
        return getWithFilterDataAndLocatorList(data, PROBABILITY, lazyLocators, getter);
    }

    static LazyFilteredElementParameters getWithFilterDataAndLocatorList(ElementFilterData<?> data, LazyLocatorList lazyLocators) {
        return getWithFilterDataAndLocatorList(data, PROBABILITY, lazyLocators, GETTER);
    }

    static LazyFilteredElementParameters getWithFilterDataAndLocator(ElementFilterData<?> data, double probability, LazyLocator lazyLocator) {
        return getWithFilterDataAndLocatorList(data, probability, LazyLocatorListFactory.getWithSingleItem(lazyLocator), GETTER);
    }

    static LazyFilteredElementParameters getWithFilterDataAndLocator(ElementFilterData<?> data, double probability, LazyLocator lazyLocator, String getter) {
        return getWithFilterDataAndLocatorList(data, probability, LazyLocatorListFactory.getWithSingleItem(lazyLocator), getter);
    }

    static LazyFilteredElementParameters getWithFilterDataAndLocator(ElementFilterData<?> data, LazyLocator lazyLocator, String getter) {
        return getWithFilterDataAndLocatorList(data, LazyLocatorListFactory.getWithSingleItem(lazyLocator), getter);
    }

    static LazyFilteredElementParameters getWithFilterDataAndLocator(ElementFilterData<?> data, LazyLocator lazyLocator) {
        return getWithFilterDataAndLocatorList(data, LazyLocatorListFactory.getWithSingleItem(lazyLocator), GETTER);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocatorList(boolean isFiltered, int index, double probability, LazyLocatorList lazyLocators) {
        return getWithFilterParametersAndLocatorList(isFiltered, index, probability, lazyLocators, GETTER);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocatorList(int index, double probability, LazyLocatorList lazyLocators, String getter) {
        return getWithFilterParametersAndLocatorList(true, index, probability, lazyLocators, getter);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocatorList(boolean isFiltered, double probability, LazyLocatorList lazyLocators, String getter) {
        return getWithFilterParametersAndLocatorList(isFiltered, FIRST_INDEX, probability, lazyLocators, getter);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocatorList(boolean isFiltered, int index, LazyLocatorList lazyLocators) {
        return getWithFilterParametersAndLocatorList(isFiltered, index, PROBABILITY, lazyLocators, GETTER);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocatorList(boolean isFiltered, double probability, LazyLocatorList lazyLocators) {
        return getWithFilterParametersAndLocatorList(isFiltered, FIRST_INDEX, probability, lazyLocators, GETTER);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocatorList(int index, double probability, LazyLocatorList lazyLocators) {
        return getWithFilterParametersAndLocatorList(true, index, probability, lazyLocators, GETTER);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocatorList(boolean isFiltered, LazyLocatorList lazyLocators) {
        return getWithFilterParametersAndLocatorList(isFiltered, FIRST_INDEX, PROBABILITY, lazyLocators, GETTER);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocatorList(int index, LazyLocatorList lazyLocators) {
        return getWithFilterParametersAndLocatorList(true, index, PROBABILITY, lazyLocators, GETTER);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocator(boolean isFiltered, int index, double probability, LazyLocator lazyLocator, String getter) {
        return getWithFilterParametersAndLocatorList(isFiltered, index, probability, LazyLocatorListFactory.getWithSingleItem(lazyLocator), getter);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocator(boolean isFiltered, int index, LazyLocator lazyLocator, String getter) {
        return getWithFilterParametersAndLocatorList(isFiltered, index, PROBABILITY, LazyLocatorListFactory.getWithSingleItem(lazyLocator), getter);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocator(boolean isFiltered, LazyLocator lazyLocator, String getter) {
        return getWithFilterParametersAndLocatorList(isFiltered, FIRST_INDEX, PROBABILITY, LazyLocatorListFactory.getWithSingleItem(lazyLocator), getter);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocator(boolean isFiltered, int index, double probability, LazyLocator lazyLocator) {
        return getWithFilterParametersAndLocatorList(isFiltered, index, probability, LazyLocatorListFactory.getWithSingleItem(lazyLocator), GETTER);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocator(boolean isFiltered, int index, LazyLocator lazyLocator) {
        return getWithFilterParametersAndLocatorList(isFiltered, index, PROBABILITY, LazyLocatorListFactory.getWithSingleItem(lazyLocator), GETTER);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocator(int index, double probability, LazyLocator lazyLocator, String getter) {
        return getWithFilterParametersAndLocatorList(index, probability, LazyLocatorListFactory.getWithSingleItem(lazyLocator), getter);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocator(boolean isFiltered, double probability, LazyLocator lazyLocator, String getter) {
        return getWithFilterParametersAndLocatorList(isFiltered, probability, LazyLocatorListFactory.getWithSingleItem(lazyLocator), getter);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocator(int index, double probability, LazyLocator lazyLocator) {
        return getWithFilterParametersAndLocator(index, probability, lazyLocator, GETTER);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocator(boolean isFiltered, double probability, LazyLocator lazyLocator) {
        return getWithFilterParametersAndLocator(isFiltered, FIRST_INDEX, probability, lazyLocator, GETTER);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocator(int index, LazyLocator lazyLocator, String getter) {
        return getWithFilterParametersAndLocator(index, PROBABILITY, lazyLocator, getter);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocator(boolean isFiltered, LazyLocator lazyLocator) {
        return getWithFilterParametersAndLocator(isFiltered, FIRST_INDEX, PROBABILITY, lazyLocator, GETTER);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocator(int index, LazyLocator lazyLocator) {
        return getWithFilterParametersAndLocator(index, PROBABILITY, lazyLocator, GETTER);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocatorList(boolean isFiltered, String message, double probability, LazyLocatorList lazyLocators) {
        return getWithFilterParametersAndLocatorList(isFiltered, message, probability, lazyLocators, GETTER);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocatorList(String message, double probability, LazyLocatorList lazyLocators, String getter) {
        return getWithFilterParametersAndLocatorList(true, message, probability, lazyLocators, getter);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocatorList(boolean isFiltered, String message, LazyLocatorList lazyLocators) {
        return getWithFilterParametersAndLocatorList(isFiltered, message, PROBABILITY, lazyLocators, GETTER);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocatorList(String message, double probability, LazyLocatorList lazyLocators) {
        return getWithFilterParametersAndLocatorList(true, message, probability, lazyLocators, GETTER);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocatorList(String message, LazyLocatorList lazyLocators) {
        return getWithFilterParametersAndLocatorList(true, message, PROBABILITY, lazyLocators, GETTER);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocator(boolean isFiltered, String message, double probability, LazyLocator lazyLocator, String getter) {
        return getWithFilterParametersAndLocatorList(isFiltered, message, probability, LazyLocatorListFactory.getWithSingleItem(lazyLocator), getter);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocator(boolean isFiltered, String message, LazyLocator lazyLocator, String getter) {
        return getWithFilterParametersAndLocatorList(isFiltered, message, PROBABILITY, LazyLocatorListFactory.getWithSingleItem(lazyLocator), getter);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocator(boolean isFiltered, String message, double probability, LazyLocator lazyLocator) {
        return getWithFilterParametersAndLocatorList(isFiltered, message, probability, LazyLocatorListFactory.getWithSingleItem(lazyLocator), GETTER);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocator(boolean isFiltered, String message, LazyLocator lazyLocator) {
        return getWithFilterParametersAndLocatorList(isFiltered, message, PROBABILITY, LazyLocatorListFactory.getWithSingleItem(lazyLocator), GETTER);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocator(String message, double probability, LazyLocator lazyLocator, String getter) {
        return getWithFilterParametersAndLocatorList(message, probability, LazyLocatorListFactory.getWithSingleItem(lazyLocator), getter);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocator(String message, double probability, LazyLocator lazyLocator) {
        return getWithFilterParametersAndLocator(message, probability, lazyLocator, GETTER);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocator(String message, LazyLocator lazyLocator, String getter) {
        return getWithFilterParametersAndLocator(message, PROBABILITY, lazyLocator, getter);
    }

    static LazyFilteredElementParameters getWithFilterParametersAndLocator(String message, LazyLocator lazyLocator) {
        return getWithFilterParametersAndLocator(message, PROBABILITY, lazyLocator, GETTER);
    }
}