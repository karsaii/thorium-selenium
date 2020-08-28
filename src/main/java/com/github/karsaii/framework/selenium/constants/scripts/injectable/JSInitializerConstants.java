package com.github.karsaii.framework.selenium.constants.scripts.injectable;

public abstract class JSInitializerConstants {
    private static final String BASE = "https://cdn.jsdelivr.net/gh/karsaii/thorium-selenium/js-utils/";
    public static final String STRINGULARITY_TYPE_UTILS = BASE + "stringularity-type-utils-nodocs-rollup.js";
    public static final String THORIUM_UTILITIES = BASE + "thoriumutilities.js";

    public static final String DEPENDENCY_EXISTENCE_CHECKER = "return ((typeof STU) === (typeof SU) === (typeof {}))";
}
