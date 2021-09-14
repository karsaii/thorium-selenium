package com.neathorium.framework.selenium.records.lazy;

import com.neathorium.framework.selenium.records.lazy.filtered.LazyFilteredElementParameters;
import com.neathorium.core.extensions.DecoratedList;
import com.neathorium.framework.core.records.lazy.CachedLazyData;
import com.neathorium.framework.core.selector.records.SelectorKeySpecificityData;

import java.util.Map;

public class CachedLazyElementData extends CachedLazyData<LazyFilteredElementParameters, LazyElement> {
    public CachedLazyElementData(LazyElement element, Map<String, DecoratedList<SelectorKeySpecificityData>> typeKeys) {
        super(element, typeKeys);
    }
}
