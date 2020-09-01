package com.github.karsaii.framework.selenium.namespaces.scripter.injectable;

import com.github.karsaii.core.constants.validators.CoreFormatterConstants;
import com.github.karsaii.core.extensions.namespaces.CoreUtilities;
import com.github.karsaii.framework.selenium.constants.driver.devtools.DevtoolsConstants;
import com.github.karsaii.framework.selenium.namespaces.utilities.LazyElementUtilities;
import com.github.karsaii.framework.selenium.records.lazy.LazyElement;

import static com.github.karsaii.core.namespaces.predicates.DataPredicates.isValidNonFalse;

public interface MetaCore {
    String BOOLEAN_COMMAND_END = ").status" + DevtoolsConstants.ENTER_INPUT;
    String STRING_COMMAND_END = ").object" + DevtoolsConstants.ENTER_INPUT;

    static String get(String base, LazyElement element, String optional, String end) {
        final var selector =  LazyElementUtilities.getCSSSelectorFromElement(element);
        final var filterData = LazyElementUtilities.getIndexedData(element);
        var elementString = filterData.message.message + "('" + element.name + "', '" + selector;
        if (isValidNonFalse(filterData)) {
            elementString += "', " + (CoreUtilities.isEqual(filterData.message.message, "TU.GETI") ? filterData.object : "'" + filterData.object + "'") + ")";
        } else {
            elementString = "')";
        }
        return base + "(" + elementString + optional + end;
    }

    static String getBoolean(String base, LazyElement element) {
        return MetaCore.get(base, element, CoreFormatterConstants.EMPTY, BOOLEAN_COMMAND_END);
    }

    static String getBoolean(String base, LazyElement element, String valueSource) {
        return MetaCore.get(base, element, "', '" + valueSource + "'", BOOLEAN_COMMAND_END);
    }

    static String getBoolean(String base, LazyElement element, String valueSource, String assignedValue) {
        return MetaCore.get(base, element, "', '" + valueSource  + "', '" + assignedValue + "'", BOOLEAN_COMMAND_END);
    }

    static String getString(String base, LazyElement element) {
        return MetaCore.get(base, element, CoreFormatterConstants.EMPTY, STRING_COMMAND_END);
    }

    static String getString(String base, LazyElement element, String valueSource) {
        return MetaCore.get(base, element, "', '" + valueSource + "'", STRING_COMMAND_END);
    }
}
