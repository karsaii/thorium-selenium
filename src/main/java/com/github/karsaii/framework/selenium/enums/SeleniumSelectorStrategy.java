package com.github.karsaii.framework.selenium.enums;

import java.util.HashMap;
import java.util.Map;

public enum SeleniumSelectorStrategy {
    ID("id"),
    CSS_SELECTOR("cssSelector"),
    CLASS("class"),
    XPATH("xpath"),
    TAG_NAME("tagName"),
    NAME("name"),
    PARTIAL_LINK_TEXT("partialLinkText"),
    LINK_TEXT("linkText"),
    NONE("none"),
    DEFAULT("id");

    private static final Map<String, SeleniumSelectorStrategy> VALUES = new HashMap<>();
    private static final Map<Integer, SeleniumSelectorStrategy> INDICES = new HashMap<>();
    private String name;

    SeleniumSelectorStrategy(String name) {
        this.name = name;
    }

    static {
        for(var getter : values()) {
            VALUES.putIfAbsent(getter.name, getter);
            INDICES.putIfAbsent(getter.ordinal(), getter);
        }
    }
    public String getName() {
        return name;
    }

    public static SeleniumSelectorStrategy getValueOf(String name) {
        return VALUES.getOrDefault(name, SeleniumSelectorStrategy.DEFAULT);
    }

    public static boolean contains(String name) {
        return VALUES.containsKey(name);
    }
}
