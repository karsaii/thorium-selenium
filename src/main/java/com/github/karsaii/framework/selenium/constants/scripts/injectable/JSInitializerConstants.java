package com.github.karsaii.framework.selenium.constants.scripts.injectable;

public abstract class JSInitializerConstants {
    private static final String BASE = "https://cdn.jsdelivr.net/gh/karsaii/thorium-selenium@25010741512f1599cc321b0287627ca8904a2193/js-utils/";
    public static final String STRINGULARITY_TYPE_UTILS = BASE + "stringularity-type-utils-nodocs-rollup.js";
    public static final String THORIUM_UTILITIES = BASE + "thoriumutilities.js";

    public static final String DEPENDENCY_EXISTENCE_CHECKER = (
        "var isStu = typeof STU === 'object';" +
        "var isTu = typeof TU === 'object';" +
        "var objectType = typeof {} === 'object';" +
        "isStu && isTu && objectType;"
    );
}
