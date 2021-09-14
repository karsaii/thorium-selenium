package com.neathorium.framework.selenium.constants.lazy;

import com.neathorium.framework.selenium.constants.SeleniumDataConstants;
import com.neathorium.framework.selenium.namespaces.element.validators.WebElementValidators;
import com.neathorium.framework.selenium.namespaces.extensions.boilers.WebElementList;
import com.neathorium.framework.selenium.namespaces.repositories.ElementRepository;
import com.neathorium.framework.selenium.namespaces.utilities.LazyElementUtilities;
import com.neathorium.framework.selenium.records.lazy.GetLazyElementData;
import org.openqa.selenium.WebElement;

public abstract class GetLazyElementConstants {
    public static final GetLazyElementData<WebElement, WebElementList> REGULAR_LAZY_CONSTANTS = new GetLazyElementData<>(
        LazyElementUtilities::lazyExitConditionCore,
        ElementRepository::cacheValidLazyElement,
        LazyElementUtilities::getCurrentLazyElement,
        WebElementValidators::isNullWebElement,
        SeleniumDataConstants.NULL_ELEMENT
    );
}
