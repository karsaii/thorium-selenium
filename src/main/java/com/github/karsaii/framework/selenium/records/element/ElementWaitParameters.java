package com.github.karsaii.framework.selenium.records.element;

import org.openqa.selenium.By;
import com.github.karsaii.framework.selenium.abstracts.AbstractWaitParameters;
import com.github.karsaii.framework.selenium.constants.ElementWaitDefaults;

public class ElementWaitParameters extends AbstractWaitParameters<By> {
    public ElementWaitParameters(By locator, int interval, int duration) {
        super(locator, interval, duration);
    }

    public ElementWaitParameters(By locator, int duration) {
        super(locator, ElementWaitDefaults.INTERVAL, duration);
    }

    public ElementWaitParameters(By locator) {
        super(locator, ElementWaitDefaults.INTERVAL, ElementWaitDefaults.DURATION);
    }
}
