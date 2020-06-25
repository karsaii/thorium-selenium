package com.github.karsaii.framework.selenium.records;

import com.github.karsaii.framework.core.records.lazy.ExternalSelectorData;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.core.records.command.CommandRangeData;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ExternalSeleniumSelectorData extends ExternalSelectorData<WebDriver> {
    public ExternalSeleniumSelectorData(BiFunction<String, List<String>, Function<WebDriver, Data<String>>> getSelector, String preferredProperties, String selectorType, CommandRangeData range, int limit, Data<String> defaultSelector) {
        super(getSelector, preferredProperties, selectorType, range, limit, defaultSelector);
    }
}
