package com.neathorium.framework.selenium.namespaces.element;

import com.neathorium.framework.selenium.constants.validators.SeleniumFormatterConstants;
import com.neathorium.framework.selenium.enums.SingleGetter;
import com.neathorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.neathorium.framework.selenium.namespaces.repositories.LocatorRepository;
import com.neathorium.framework.selenium.namespaces.scripter.Execute;
import com.neathorium.framework.selenium.records.lazy.LazyElement;
import com.neathorium.core.constants.CoreDataConstants;
import com.neathorium.core.records.Data;
import com.neathorium.framework.selenium.namespaces.ExecutionCore;
import com.neathorium.framework.selenium.namespaces.utilities.SeleniumUtilities;
import org.openqa.selenium.By;

import static com.neathorium.framework.selenium.namespaces.ExecutionCore.ifDriver;
import static com.neathorium.core.namespaces.predicates.DataPredicates.isValidNonFalse;

public interface ElementAlternatives {
    static DriverFunction<Boolean> clearWithSelectAll(LazyElement element) {
        return ifDriver("clearWithSelectAll", SeleniumUtilities.isNotNullLazyElement(element), Element.sendKeys(element, SeleniumFormatterConstants.SELECT_ALL), CoreDataConstants.NULL_BOOLEAN);
    }

    static DriverFunction<Boolean> clearWithSelectAll(Data<LazyElement> data) {
        return ExecutionCore.ifDriver("clearWithSelectAll", isValidNonFalse(data), clearWithSelectAll(data.object), CoreDataConstants.NULL_BOOLEAN);
    }

    static DriverFunction<Boolean> clearWithSelectAll(By locator, SingleGetter getter) {
        return clearWithSelectAll(LocatorRepository.getIfContains(locator, getter));
    }

    static DriverFunction<Boolean> clearWithSelectAll(By locator) {
        return clearWithSelectAll(LocatorRepository.getIfContains(locator));
    }

    static DriverFunction<Boolean> clickWithEventDispatcher(LazyElement element) {
        return ExecutionCore.ifDriver("clickWithEventDispatcher", SeleniumUtilities.isNotNullLazyElement(element), Execute.clickEventDispatcher(element), CoreDataConstants.NULL_BOOLEAN);
    }

    static DriverFunction<Boolean> clickWithEventDispatcher(Data<LazyElement> data) {
        return ExecutionCore.ifDriver("clickWithEventDispatcher", isValidNonFalse(data), clickWithEventDispatcher(data.object), CoreDataConstants.NULL_BOOLEAN);
    }

    static DriverFunction<Boolean> clickWithEventDispatcher(By locator, SingleGetter getter) {
        return clickWithEventDispatcher(LocatorRepository.getIfContains(locator, getter));
    }

    static DriverFunction<Boolean> clickWithEventDispatcher(By locator) {
        return clickWithEventDispatcher(LocatorRepository.getIfContains(locator));
    }
}
