package com.github.karsaii.framework.selenium.namespaces.scripter;

import com.github.karsaii.core.constants.CoreDataConstants;
import com.github.karsaii.core.extensions.namespaces.CoreUtilities;
import com.github.karsaii.core.namespaces.DataFactoryFunctions;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.core.namespaces.validators.FrameworkCoreFormatter;
import com.github.karsaii.framework.selenium.constants.SelectorStrategyNameConstants;
import com.github.karsaii.framework.selenium.constants.driver.devtools.DevtoolsConstants;
import com.github.karsaii.framework.selenium.namespaces.Driver;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.github.karsaii.framework.selenium.namespaces.factories.DriverFunctionFactory;
import com.github.karsaii.framework.selenium.namespaces.utilities.LazyElementUtilities;
import com.github.karsaii.framework.selenium.records.lazy.LazyElement;
import org.openqa.selenium.WebDriver;

import java.util.function.Function;

import static com.github.karsaii.core.namespaces.DataExecutionFunctions.ifDependency;

public interface MutationObserver {
    private static String getMutationObserver(String cssSelector) {
        return (
            "if ((typeof document['__test']) === (typeof {})) {" +
            "    return true;" +
            "}" +
            "document['__test'] = {};" +
            "document['__test']['consoleFocused'] = false;" +
            "document['__test']['observing'] = document.querySelectorAll('" + cssSelector + "')[0];" +
            "document['__test']['observerCore'] = function observerCoreFunction(mutations) {" +
            "    var length = mutations.length," +
            "        index = 0," +
            "        mutation = undefined;" +
            "    for(; index < length; ++index) {" +
            "        mutation = mutations[index];" +
            "        if (mutation.attributeName += 'class') {" +
            "            continue;" +
            "        }" +
            "        document['__test']['consoleFocused'] = mutation.target.classList.contains('CodeMirror-focused');" +
            "        break;" +
            "    }" +
            "" +
            "document['__test']['observer'] = new MutationObserver(document['__test']['observerCore']);" +
            "document['__test']['observer'].observe(document['__test']['observing'], {attributes: true});" +
            "return true;"
        );
    }

    private static Data<Boolean> setConsoleFocusedFunctionCore(WebDriver driver, LazyElement element) {
        final var locator = LazyElementUtilities.getCSSSelectorFromElement(element);
        final var script = getMutationObserver(locator);
        final var result = Driver.execute(script).apply(driver);
        final var status = CoreUtilities.castToBoolean(result.object);
        return DataFactoryFunctions.getBoolean(status, result.message.toString(), result.exception);
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
        return setConsoleFocusedFunction(DevtoolsConstants.CONSOLE_FOCUS);
    }

    private static Data<Boolean> isConsoleFocusedCore(WebDriver driver) {
        final var result = Driver.execute("return document['__test']['consoleFocused'];").apply(driver);
        final var status = CoreUtilities.castToBoolean(result.object);
        return DataFactoryFunctions.getBoolean(status, "isConsoleFocused", result.message.toString(), result.exception);
    }

    private static Function<WebDriver, Data<Boolean>> isConsoleFocusedCore() {
        return MutationObserver::isConsoleFocusedCore;
    }

    static DriverFunction<Boolean> isConsoleFocused() {
        return DriverFunctionFactory.getFunction(isConsoleFocusedCore());
    }
}
