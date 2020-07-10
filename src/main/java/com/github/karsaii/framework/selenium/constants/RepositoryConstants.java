package com.github.karsaii.framework.selenium.constants;

import org.openqa.selenium.By;
import com.github.karsaii.framework.selenium.records.lazy.CachedLazyElementData;

import java.util.HashMap;

public abstract class RepositoryConstants {
    public static final HashMap<String, CachedLazyElementData> ELEMENTS = new HashMap<>();
    public static final HashMap<By, String> LOCATOR_ELEMENTS = new HashMap<>();
    public static final HashMap<By, String> NAMED_LOCATORS = new HashMap<>();
}
