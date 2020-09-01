package com.github.karsaii.framework.selenium.constants.page;

import com.github.karsaii.framework.selenium.namespaces.factories.SeleniumLazyLocatorFactory;
import com.github.karsaii.framework.selenium.namespaces.factories.lazy.simple.SimpleLazyElementFactory;
import com.github.karsaii.framework.selenium.records.lazy.LazyElement;

public abstract class DefaultElementConstants {
    public static final String NAME = "Defaults - ";

    public static final String BODY_NAME = NAME + "Body element";

    public static final String BODY_SELECTOR = "body";

    public static final LazyElement BODY = SimpleLazyElementFactory.getSimple(BODY_NAME, SeleniumLazyLocatorFactory.getCSSSelector(BODY_SELECTOR));
}
