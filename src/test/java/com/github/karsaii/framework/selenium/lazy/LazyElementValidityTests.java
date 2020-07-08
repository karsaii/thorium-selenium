package com.github.karsaii.framework.selenium.lazy;

import com.github.karsaii.core.constants.validators.CoreFormatterConstants;
import com.github.karsaii.framework.core.namespaces.validators.FrameworkCoreFormatter;
import com.github.karsaii.framework.core.records.lazy.LazyLocator;
import com.github.karsaii.framework.selenium.namespaces.lazy.LazyElementFactory;
import com.github.karsaii.framework.selenium.records.lazy.LazyElement;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class LazyElementValidityTests {
    private static Predicate<String> blank = StringUtils::isBlank;
    private static Predicate<String> notBlank = StringUtils::isNotBlank;
    @Test
    public void elementValidityTest() {
        final var data = LazyElementFactory.getWithFilterParameters("Whatever", new LazyLocator("X", "id"));
        final var result = FrameworkCoreFormatter.isNullLazyElementMessage(data);

        Assertions.assertTrue(isBlank(result), result);
    }

    @Test
    public void elementInvalidityTest() {
        final var data = LazyElementFactory.getWith(null, null, null);
        final var result = FrameworkCoreFormatter.isNullLazyElementMessage(data);

        Assertions.assertTrue(isNotBlank(result), result);
    }

    public static Stream<Arguments> elementProvider() {
        return Stream.of(
            Arguments.of("Basic valid Lazy Element - a name, a lazy locator", LazyElementFactory.getWithFilterParameters("Whatever", new LazyLocator("X", "id")), blank, true, CoreFormatterConstants.EMPTY),
            Arguments.of("Basic invalid Lazy Element - all nulls", LazyElementFactory.getWith(null, null, null), notBlank, true, "isNullLazyElementMessage: There were parameter issue(s):\nElement  name parameter was blank, empty or null.\nElement  parameters parameter was null.\n")
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("elementProvider")
    public void parameterizedElementValidityTests(String name, LazyElement element, Predicate<CharSequence> test, boolean expected, String expectedMessage) {
        final var result = FrameworkCoreFormatter.isNullLazyElementMessage(element);
        Assertions.assertTrue(Objects.equals(test.test(result), expected) && Objects.equals(result, expectedMessage), result);
    }
}
