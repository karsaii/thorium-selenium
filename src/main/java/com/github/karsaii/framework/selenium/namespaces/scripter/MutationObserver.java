package com.github.karsaii.framework.selenium.namespaces.scripter;

import com.github.karsaii.core.constants.CoreDataConstants;
import com.github.karsaii.core.constants.validators.CoreFormatterConstants;
import com.github.karsaii.core.extensions.namespaces.CoreUtilities;
import com.github.karsaii.core.extensions.namespaces.predicates.ExecutorPredicates;
import com.github.karsaii.core.namespaces.DataFactoryFunctions;
import com.github.karsaii.core.namespaces.predicates.DataPredicates;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.core.namespaces.validators.FrameworkCoreFormatter;
import com.github.karsaii.framework.selenium.constants.driver.devtools.DevtoolsViewConstants;
import com.github.karsaii.framework.selenium.constants.scripter.DevtoolsConstants;
import com.github.karsaii.framework.selenium.namespaces.Driver;
import com.github.karsaii.framework.selenium.namespaces.SeleniumExecutor;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.github.karsaii.framework.selenium.namespaces.factories.DriverFunctionFactory;
import com.github.karsaii.framework.selenium.namespaces.utilities.LazyElementUtilities;
import com.github.karsaii.framework.selenium.records.lazy.LazyElement;
import org.openqa.selenium.WebDriver;

import java.util.function.Function;

import static com.github.karsaii.core.namespaces.DataExecutionFunctions.ifDependency;

public interface MutationObserver {
    private static String getMutationObserverScript(String locator) {
        return (
            "document['__test'] = {};" +
            "document['__test']['consoleFocused'] = false;" +
            "document['__test']['observing'] = document.querySelectorAll('" + locator + "')[0];" +
            "document['__test']['observerCore'] = function observerCoreFunction(mutations) {" +
            "    var length = mutations.length," +
            "        index = 0," +
            "        mutation;" +
            "    for(; index < length; ++index) {" +
            "        mutation = mutations[index];" +
            "        if (mutation.attributeName != 'class') {" +
            "            continue;" +
            "        }" +
            "        document['__test']['consoleFocused'] = mutation.target.classList.contains('CodeMirror-focused');" +
            "        break;" +
            "    }" +
            "};" +
            "document['__test']['observer'] = new MutationObserver(document['__test']['observerCore']);" +
            "document['__test']['observer'].observe(document['__test']['observing'], {attributes: true});" +
            "return true;"
        );
    }

    private static Data<Boolean> isConsoleFocusedObserverSet(WebDriver driver) {
        final var result = Driver.execute(DevtoolsConstants.GUARD).apply(driver);
        final var object = result.object instanceof String ? (String) result.object : CoreFormatterConstants.EMPTY;
        final var status = Boolean.parseBoolean(object);
        final var message = "Observer was" + (status ? "" : "not") + " set" + CoreFormatterConstants.END_LINE;
        return DataFactoryFunctions.getBoolean(status, "isConsoleFocusedObserverSet", message, result.exception);
    }

    private static Data<Boolean> isConsoleFocusedCore(WebDriver driver) {
        final var isSetData = isConsoleFocusedObserverSet(driver);
        if (DataPredicates.isInvalidOrFalse(isSetData)) {
            return isSetData;
        }

        final var result = Driver.execute(DevtoolsConstants.CONSOLE_FOCUSED_CHECK).apply(driver);
        final var status = CoreUtilities.castToBoolean(result.object);
        final var message = "Console was" + (status ? "" : "not") + " focused" + CoreFormatterConstants.END_LINE;
        return DataFactoryFunctions.getBoolean(status, "isConsoleFocused", message, result.exception);
    }

    private static Data<Boolean> setConsoleFocusedFunctionCore(WebDriver driver, LazyElement element) {
        final var isSetData = isConsoleFocusedObserverSet(driver);
        if (DataPredicates.isValidNonFalse(isSetData)) {
            return isSetData;
        }

        final var locator = LazyElementUtilities.getCSSSelectorFromElement(element);
        final var script = getMutationObserverScript(locator);
        final var result = Driver.execute(script).apply(driver);
        final var status = DataPredicates.isValidNonFalse(result);
        final var message = "Observer was" + (status ? "" : "not") + " set" + CoreFormatterConstants.END_LINE;
        return DataFactoryFunctions.getBoolean(status, message, result.exception);
    }

    private static Function<WebDriver, Data<Boolean>> setConsoleFocusedFunctionCore(LazyElement element) {
        return driver -> setConsoleFocusedFunctionCore(driver, element);
    }

    static DriverFunction<Boolean> setConsoleFocusedFunction(LazyElement element) {
        return DriverFunctionFactory.getFunction(ifDependency(
            "setConsoleFocusedFunction",
            FrameworkCoreFormatter.isNullLazyElementMessage(element),
            setConsoleFocusedFunctionCore(element),
            CoreDataConstants.NULL_BOOLEAN
        ));
    }

    static DriverFunction<Boolean> setConsoleFocusedFunction() {
        return setConsoleFocusedFunction(DevtoolsViewConstants.CONSOLE_FOCUS);
    }

    private static DriverFunction<Boolean> isConsoleFocusedObserverSet() {
        return MutationObserver::isConsoleFocusedObserverSet;
    }

    static DriverFunction<Boolean> isConsoleFocused() {
        return MutationObserver::isConsoleFocusedCore;
    }
}
