package com.github.karsaii.framework.selenium.constants.lazy;

import com.github.karsaii.framework.selenium.constants.SeleniumDataConstants;
import com.github.karsaii.framework.selenium.namespaces.element.validators.WebElementValidators;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.WebElementList;
import com.github.karsaii.framework.selenium.namespaces.repositories.ElementRepository;
import com.github.karsaii.framework.selenium.namespaces.utilities.LazyElementUtilities;
import com.github.karsaii.framework.selenium.records.lazy.GetLazyElementData;
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
