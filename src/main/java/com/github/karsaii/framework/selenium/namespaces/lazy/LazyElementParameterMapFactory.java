package com.github.karsaii.framework.selenium.namespaces.lazy;

import com.github.karsaii.framework.selenium.records.lazy.filtered.LazyFilteredElementParameters;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Map.entry;

public interface LazyElementParameterMapFactory {
    static Map<String, LazyFilteredElementParameters> getWith(Map<String, LazyFilteredElementParameters> map) {
        return Collections.synchronizedMap(new LinkedHashMap<>(map));
    }

    static Map<String, LazyFilteredElementParameters> getWithEntry(Map.Entry<String, LazyFilteredElementParameters> entry) {
        return getWith(Map.ofEntries(entry));
    }

    static Map<String, LazyFilteredElementParameters> getWithLocatorAndParameters(String locator, LazyFilteredElementParameters parameters) {
        return getWithEntry(entry(locator, parameters));
    }
}
