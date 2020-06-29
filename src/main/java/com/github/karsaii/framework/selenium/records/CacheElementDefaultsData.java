package com.github.karsaii.framework.selenium.records;

import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.selenium.records.lazy.CachedLazyElementData;

import java.util.Map;
import java.util.Objects;

public class CacheElementDefaultsData {
    public final String name;
    public final Map<String, CachedLazyElementData> repository;
    public final Data<Boolean> defaultValue;

    public CacheElementDefaultsData(String name, Map<String, CachedLazyElementData> repository, Data<Boolean> defaultValue) {
        this.name = name;
        this.repository = repository;
        this.defaultValue = defaultValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final var that = (CacheElementDefaultsData) o;
        return Objects.equals(name, that.name) && Objects.equals(repository, that.repository) && Objects.equals(defaultValue, that.defaultValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, repository, defaultValue);
    }
}
