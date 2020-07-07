package com.github.karsaii.framework.selenium.namespaces;

import com.github.karsaii.core.extensions.namespaces.CoreUtilities;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.selenium.namespaces.utilities.SeleniumUtilities;
import com.github.karsaii.framework.selenium.records.lazy.LazyElement;

import static com.github.karsaii.core.namespaces.predicates.DataPredicates.isInvalidOrFalse;
import static com.github.karsaii.core.namespaces.predicates.DataPredicates.isValidNonFalse;

public interface WaitPredicateFunctions {
    static <T> boolean isFalsyData(T object) {
        return (
            ((object instanceof Data) && (isInvalidOrFalse((Data<?>) object))) ||
            ((object instanceof LazyElement) && (SeleniumUtilities.isNullLazyElement((LazyElement)object)))
        ) || CoreUtilities.isFalse(object);
    }

    static <T> boolean isTruthyData(T object) {
        return (
            ((object instanceof Data) && (isValidNonFalse((Data<?>) object))) ||
            ((object instanceof LazyElement) && (SeleniumUtilities.isNotNullLazyElement((LazyElement)object)))
        ) || CoreUtilities.isTrue(object);
    }
}
