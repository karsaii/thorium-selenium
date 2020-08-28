package com.github.karsaii.framework.selenium.records.lazy;

import com.github.karsaii.framework.selenium.abstracts.AbstractWaitParameters;
import com.github.karsaii.framework.selenium.constants.ElementWaitDefaults;

public class LazyElementWaitParameters extends AbstractWaitParameters<LazyElement> {
    public LazyElementWaitParameters(LazyElement object, int interval, int duration) {
        super(object, interval, duration);
    }

    public LazyElementWaitParameters(LazyElement object, int duration) {
        super(object, ElementWaitDefaults.INTERVAL, duration);
    }

    public LazyElementWaitParameters(LazyElement object) {
        super(object, ElementWaitDefaults.INTERVAL, ElementWaitDefaults.DURATION);
    }
}
