package com.github.karsaii.framework.selenium.constants;

import org.openqa.selenium.By;
import com.github.karsaii.framework.selenium.enums.SeleniumSelectorStrategy;

import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import static java.util.Map.entry;

public abstract class ElementStrategyMapConstants {
    public static final Map<SeleniumSelectorStrategy, Function<String, By>> STRATEGY_MAP = Collections.unmodifiableMap(
        new EnumMap<>(
            Map.ofEntries(
                entry(SeleniumSelectorStrategy.ID, By::id),
                entry(SeleniumSelectorStrategy.CSS_SELECTOR, By::cssSelector),
                entry(SeleniumSelectorStrategy.CLASS, By::className),
                entry(SeleniumSelectorStrategy.XPATH, By::xpath),
                entry(SeleniumSelectorStrategy.TAG_NAME, By::tagName),
                entry(SeleniumSelectorStrategy.NAME, By::name),
                entry(SeleniumSelectorStrategy.PARTIAL_LINK_TEXT, By::partialLinkText),
                entry(SeleniumSelectorStrategy.LINK_TEXT, By::linkText)
            )
        )
    );

    public static final Set<SeleniumSelectorStrategy> STRATEGY_MAP_KEY_SET = EnumSet.allOf(SeleniumSelectorStrategy.class);
}
