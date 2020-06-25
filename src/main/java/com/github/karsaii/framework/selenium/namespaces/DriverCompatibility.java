package com.github.karsaii.framework.selenium.namespaces;

import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.WebElementList;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.github.karsaii.framework.selenium.constants.SeleniumDataConstants;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static com.github.karsaii.framework.selenium.namespaces.ExecutionCore.ifDriver;

public interface DriverCompatibility {
    static DriverFunction<WebElement> getElementByCssSelector(String selector, String nameof) {
        return ifDriver(isNotBlank(nameof) ? nameof : "getElementByCssSelector", isNotBlank(selector), Driver.getElement(By.cssSelector(selector)), SeleniumDataConstants.NULL_ELEMENT);
    }

    static DriverFunction<WebElement> getElementByCssSelector(String selector) {
        return getElementByCssSelector(selector, null);
    }

    static DriverFunction<WebElementList> getElementsByCssSelector(String selector) {
        return ifDriver("getElementsByCssSelector", isNotBlank(selector), Driver.getElements(By.cssSelector(selector)), SeleniumDataConstants.NULL_LIST);
    }

    static DriverFunction<WebElement> getElementById(String id) {
        return ifDriver("getElementById", isNotBlank(id), Driver.getElement(By.id(id)), SeleniumDataConstants.NULL_ELEMENT);
    }

    static DriverFunction<WebElementList> getElementsByClass(String className) {
        return ifDriver("getElementsByClass", isNotBlank(className), Driver.getElements(By.className(className)), SeleniumDataConstants.NULL_LIST);
    }

    static DriverFunction<WebElement> getElementByClass(String className) {
        return ifDriver("getElementByClass", isNotBlank(className), Driver.getElement(By.className(className)), SeleniumDataConstants.NULL_ELEMENT);
    }

    static DriverFunction<WebElement> getElementByXpath(String xpath) {
        return ifDriver("getElementByXpath", isNotBlank(xpath), Driver.getElement(By.xpath(xpath)), SeleniumDataConstants.NULL_ELEMENT);
    }
}
