package com.github.karsaii.framework.selenium.namespaces.factories;

import com.github.karsaii.core.extensions.DecoratedList;
import com.github.karsaii.framework.core.constants.AdjusterConstants;
import com.github.karsaii.framework.selenium.constants.SeleniumGetOrderConstants;
import com.github.karsaii.framework.core.constants.SelectorDataConstants;
import com.github.karsaii.framework.selenium.constants.SeleniumCoreConstants;
import com.github.karsaii.framework.selenium.records.ExternalSeleniumSelectorData;
import com.github.karsaii.framework.core.records.InternalSelectorData;
import com.github.karsaii.framework.core.records.ProbabilityData;
import com.github.karsaii.framework.selenium.records.lazy.LazyElement;
import com.github.karsaii.framework.selenium.records.lazy.LazyElementWithOptionsData;

public interface LazyElementWithOptionsDataFactory {
    static LazyElementWithOptionsData get(
        LazyElement element,
        InternalSelectorData internalData,
        ExternalSeleniumSelectorData externalData,
        DecoratedList<String> getOrder,
        ProbabilityData probabilityData
    ) {
       return new LazyElementWithOptionsData(element, internalData, externalData, getOrder, probabilityData);
    }

    static LazyElementWithOptionsData getWithDefaultProbabilityData(LazyElement element, InternalSelectorData internalData, ExternalSeleniumSelectorData externalData, DecoratedList<String> getOrder) {
        return get(element, internalData, externalData, getOrder, AdjusterConstants.PROBABILITY_DATA);
    }

    static LazyElementWithOptionsData getWithDefaultGetOrder(LazyElement element, InternalSelectorData internalData, ExternalSeleniumSelectorData externalData, ProbabilityData probabilityData) {
        return get(element, internalData, externalData, SeleniumGetOrderConstants.DEFAULT, probabilityData);
    }

    static LazyElementWithOptionsData getWithDefaultGetOrderAndProbabilityData(LazyElement element, InternalSelectorData internalData, ExternalSeleniumSelectorData externalData) {
        return getWithDefaultProbabilityData(element, internalData, externalData, SeleniumGetOrderConstants.DEFAULT);
    }

    static LazyElementWithOptionsData getWithDefaultInternalSelectorDataGetOrderAndProbabilityData(LazyElement element, ExternalSeleniumSelectorData externalData) {
        return getWithDefaultGetOrderAndProbabilityData(element, SelectorDataConstants.INTERNAL_SELECTOR_DATA, externalData);
    }

    static LazyElementWithOptionsData getWithDefaultInternalSelectorDataAndGetOrder(LazyElement element, ExternalSeleniumSelectorData externalData, DecoratedList<String> getOrder) {
        return getWithDefaultProbabilityData(element, SelectorDataConstants.INTERNAL_SELECTOR_DATA, externalData, getOrder);
    }

    static LazyElementWithOptionsData getWithDefaultInternalSelectorDataAndProbabilityData(LazyElement element, ExternalSeleniumSelectorData externalData, ProbabilityData probabilityData) {
        return getWithDefaultGetOrder(element, SelectorDataConstants.INTERNAL_SELECTOR_DATA, externalData, probabilityData);
    }

    static LazyElementWithOptionsData getWithSpecificLazyElement(LazyElement element) {
        return getWithDefaultInternalSelectorDataGetOrderAndProbabilityData(element, null);
    }

    static LazyElementWithOptionsData getWithDefaults() {
        return getWithSpecificLazyElement(SeleniumCoreConstants.NULL_LAZY_ELEMENT);
    }
}
