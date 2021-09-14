package com.neathorium.framework.selenium.records;

import com.neathorium.core.extensions.DecoratedList;
import com.neathorium.core.records.Data;
import com.neathorium.framework.core.abstracts.AbstractExternalElementData;
import com.neathorium.framework.core.selector.records.SelectorKeySpecificityData;
import org.openqa.selenium.WebElement;

import java.util.Map;

public class ExternalElementData extends AbstractExternalElementData<WebElement> {
    public ExternalElementData(Map<String, DecoratedList<SelectorKeySpecificityData>> typeKeys, Data<WebElement> found) {
        super(typeKeys, found);
    }
}
