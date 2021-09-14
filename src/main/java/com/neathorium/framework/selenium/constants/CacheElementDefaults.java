package com.neathorium.framework.selenium.constants;

import com.neathorium.framework.selenium.records.CacheElementDefaultsData;
import com.neathorium.core.constants.CoreDataConstants;

public abstract class CacheElementDefaults {
    public static final String FUNCTION_NAME = "cacheElement";
    public static final CacheElementDefaultsData CACHE_DEFAULT = new CacheElementDefaultsData(FUNCTION_NAME, RepositoryConstants.CACHED_ELEMENTS, CoreDataConstants.NULL_BOOLEAN);
}
