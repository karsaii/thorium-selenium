package com.github.karsaii.framework.selenium.records.lazy;

import com.github.karsaii.core.extensions.DecoratedList;
import com.github.karsaii.framework.core.abstracts.AbstractLazyElementWithOptionsData;
import com.github.karsaii.framework.core.records.InternalSelectorData;
import com.github.karsaii.framework.core.records.ProbabilityData;
import com.github.karsaii.framework.selenium.records.ExternalSeleniumSelectorData;
import com.github.karsaii.framework.selenium.records.lazy.filtered.LazyFilteredElementParameters;
import org.openqa.selenium.WebDriver;

public class LazyElementWithOptionsData extends AbstractLazyElementWithOptionsData<LazyFilteredElementParameters, LazyElement, WebDriver, ExternalSeleniumSelectorData> {
    public LazyElementWithOptionsData(
        LazyElement element,
        InternalSelectorData internalData,
        ExternalSeleniumSelectorData externalData,
        DecoratedList<String> getOrder,
        ProbabilityData probabilityData
    ) {
        super(element, internalData, externalData, getOrder, probabilityData);
    }
}
