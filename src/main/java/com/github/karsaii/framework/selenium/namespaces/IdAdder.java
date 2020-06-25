package com.github.karsaii.framework.selenium.namespaces;

import com.github.karsaii.framework.selenium.records.lazy.LazyElement;

public interface IdAdder {
    static void addTemporary(LazyElement element) {
        /*Before loop: var id = getLocatorValueOfElement(com.github.karsaii.framework.selenium.element, "id").object;
        Inside loop end: if (isBlank(id)) {
            id = getLocatorValueByStrategy(locatorList.last(), com.github.karsaii.framework.selenium.element.name, "id").object;
        }
        After loop: if (
            returnElement.status &&
            isNotEqual(currentKey, Strings.PRIMARY_STRATEGY) &&
            Execute.setId(returnElement, id).apply(driver).status
        ) {
            returnElement = appendMessage(returnElement, nameof, "Set com.github.karsaii.framework.selenium.element(\"" + com.github.karsaii.framework.selenium.element.name + "\") id to: " + id);
        }*/
    }
}
