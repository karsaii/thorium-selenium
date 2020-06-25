package com.github.karsaii.framework.selenium.records;

import com.github.karsaii.core.extensions.DecoratedList;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.core.abstracts.AbstractExternalElementData;
import org.openqa.selenium.WebElement;
import com.github.karsaii.framework.core.selector.records.SelectorKeySpecificityData;

import java.util.Map;

public class ExternalElementData extends AbstractExternalElementData<WebElement> {
    public ExternalElementData(Map<String, DecoratedList<SelectorKeySpecificityData>> typeKeys, Data<WebElement> found) {
        super(typeKeys, found);
    }
}
