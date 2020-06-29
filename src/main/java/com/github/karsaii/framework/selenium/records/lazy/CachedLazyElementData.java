package com.github.karsaii.framework.selenium.records.lazy;

import com.github.karsaii.core.extensions.DecoratedList;
import com.github.karsaii.framework.core.records.lazy.CachedLazyData;
import com.github.karsaii.framework.core.selector.records.SelectorKeySpecificityData;
import com.github.karsaii.framework.selenium.records.lazy.filtered.LazyFilteredElementParameters;

import java.util.Map;

public class CachedLazyElementData extends CachedLazyData<LazyFilteredElementParameters, LazyElement> {
    public CachedLazyElementData(LazyElement element, Map<String, DecoratedList<SelectorKeySpecificityData>> typeKeys) {
        super(element, typeKeys);
    }
}
