package com.github.karsaii.framework.selenium.constants;

import com.github.karsaii.core.extensions.DecoratedList;
import com.github.karsaii.core.extensions.namespaces.factories.DecoratedListFactory;

import java.util.Arrays;

public abstract class SeleniumGetOrderConstants {
    public static final DecoratedList<String> ID_CSSSELECTOR = DecoratedListFactory.getWith(Arrays.asList("id", "cssSelector", "class", "xpath"), String.class);
    public static final DecoratedList<String> CSSSELECTOR_ID = DecoratedListFactory.getWith(Arrays.asList("cssSelector", "id", "class", "xpath"), String.class);
    public static final DecoratedList<String> DEFAULT = ID_CSSSELECTOR;
}
