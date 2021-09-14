package com.neathorium.framework.selenium.namespaces.extensions.boilers;

import com.neathorium.core.extensions.interfaces.functional.boilers.IContainedData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@FunctionalInterface
public interface IElement extends IContainedData<WebDriver, WebElement> {
    DriverFunction<WebElement> get();
}
