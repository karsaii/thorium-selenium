package com.neathorium.framework.selenium.records.lazy;

import com.neathorium.framework.selenium.records.ExternalSeleniumSelectorData;
import com.neathorium.framework.selenium.records.lazy.filtered.LazyFilteredElementParameters;
import com.neathorium.core.extensions.DecoratedList;
import com.neathorium.framework.core.abstracts.AbstractLazyElementWithOptionsData;
import com.neathorium.framework.core.records.InternalSelectorData;
import com.neathorium.framework.core.records.ProbabilityData;
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
