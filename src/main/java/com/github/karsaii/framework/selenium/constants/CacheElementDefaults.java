package com.github.karsaii.framework.selenium.constants;

import com.github.karsaii.core.constants.CoreDataConstants;
import com.github.karsaii.framework.selenium.records.CacheElementDefaultsData;

public abstract class CacheElementDefaults {
    public static final String FUNCTION_NAME = "cacheElement";
    public static final CacheElementDefaultsData CACHE_DEFAULT = new CacheElementDefaultsData(FUNCTION_NAME, RepositoryConstants.CACHED_ELEMENTS, CoreDataConstants.NULL_BOOLEAN);
}
