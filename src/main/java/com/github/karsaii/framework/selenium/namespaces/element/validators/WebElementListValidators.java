package com.github.karsaii.framework.selenium.namespaces.element.validators;

import com.github.karsaii.core.extensions.namespaces.CoreUtilities;
import com.github.karsaii.core.extensions.namespaces.NullableFunctions;
import com.github.karsaii.core.extensions.namespaces.predicates.CollectionPredicates;
import com.github.karsaii.core.namespaces.predicates.DataPredicates;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.selenium.constants.SeleniumCoreConstants;
import com.github.karsaii.framework.selenium.constants.SeleniumDataConstants;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.WebElementList;
import org.openqa.selenium.WebElement;

public interface WebElementListValidators {
    static boolean isInvalid(WebElementList list) {
        return (
            NullableFunctions.isNull(list) ||
            CollectionPredicates.isEmptyOrNotOfType(list, WebElement.class) ||
            CoreUtilities.isEqual(SeleniumCoreConstants.NULL_ELEMENT_LIST, list) ||
            CoreUtilities.isEqual(SeleniumCoreConstants.STOCK_ELEMENT, list.first())
        );
    }

    static boolean isValid(WebElementList list) {
        return !isInvalid(list);
    }

    static boolean isInvalid(Data<WebElementList> data) {
        return (
            DataPredicates.isInvalidOrFalse(data) ||
            CoreUtilities.isEqual(SeleniumDataConstants.NULL_LIST, data) ||
            isInvalid(data.object)
        );
    }

    static boolean isValid(Data<WebElementList> data) {
        return !isInvalid(data);
    }


}
