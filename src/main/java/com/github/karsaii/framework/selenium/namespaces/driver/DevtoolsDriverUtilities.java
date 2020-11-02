package com.github.karsaii.framework.selenium.namespaces.driver;

import com.github.karsaii.core.constants.CoreConstants;
import com.github.karsaii.core.constants.CoreDataConstants;
import com.github.karsaii.core.constants.validators.CoreFormatterConstants;
import com.github.karsaii.core.namespaces.DataFactoryFunctions;
import com.github.karsaii.core.namespaces.validators.CoreFormatter;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.selenium.constants.driver.devtools.DevtoolsViewConstants;
import com.github.karsaii.framework.selenium.namespaces.SeleniumExecutor;
import com.github.karsaii.framework.selenium.namespaces.element.Element;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.github.karsaii.framework.selenium.namespaces.factories.DriverFunctionFactory;
import com.github.karsaii.framework.selenium.namespaces.scripter.MutationObserver;
import org.apache.commons.lang3.BooleanUtils;
import org.openqa.selenium.WebDriver;

import java.util.function.Function;

import static com.github.karsaii.core.extensions.namespaces.CoreUtilities.isNonException;
import static com.github.karsaii.framework.selenium.namespaces.ExecutionCore.ifDriver;

public interface DevtoolsDriverUtilities {
    private static Data<Boolean> sleepCore(WebDriver driver, int milliseconds) {
        var exception = CoreConstants.EXCEPTION;
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException ex) {
            exception = ex;
        }

        final var status = isNonException(exception);
        return DataFactoryFunctions.getBoolean(status, "sleep", "Thread sleep " + (status ? "done" : "interrupted") + "(\"" + milliseconds + " milliseconds\")" + CoreFormatterConstants.END_LINE);
    }

    private static Function<WebDriver, Data<Boolean>> sleepCore(int milliseconds) {
        return driver -> sleepCore(driver, milliseconds);
    }

    private static DriverFunction<Boolean> sleep(int milliseconds) {
        return ifDriver(
            "sleep",
            CoreFormatter.isMoreThanExpectedMessage(milliseconds, DevtoolsViewConstants.TAB_SLEEP_MILLIS_LIMIT, "Milliseconds of sleep"),
            DriverFunctionFactory.getFunction(sleepCore(milliseconds)),
            CoreDataConstants.NULL_BOOLEAN
        );
    }

    static DriverFunction<Boolean> sleep() {
        return sleep(DevtoolsViewConstants.TAB_SLEEP_MILLIS);
    }

    static DriverFunction<Boolean> noResultInConsole() {
        return SeleniumExecutor.execute(
            "noResultInConsole",
            Element.waitAbsent(DevtoolsViewConstants.RESULT_FIELD, 300, 3000)
        );
    }

    static DriverFunction<Boolean> consoleResultDisplayed() {
        return SeleniumExecutor.execute(
            "consoleResultDisplayed",
            Element.waitDisplayed(DevtoolsViewConstants.RESULT_FIELD, 300, 3000)
        );
    }

    static DriverFunction<String> getConsoleResult() {
        return SeleniumExecutor.execute(
            "getConsoleResult",
            consoleResultDisplayed(),
            Element.getText(DevtoolsViewConstants.RESULT_FIELD)
        );
    }

    private static Data<Boolean> getBooleanConsoleResultCore(WebDriver driver) {
        final var result = getConsoleResult().apply(driver);
        final var status = BooleanUtils.toBoolean(result.object);
        return DataFactoryFunctions.getBoolean(status, "getBooleanConsoleResultCore", result.message.toString(), result.exception, result.exceptionMessage);
    }

    private static Function<WebDriver, Data<Boolean>> getBooleanConsoleResultCore() {
        return DevtoolsDriverUtilities::getBooleanConsoleResultCore;
    }

    static DriverFunction<Boolean> getBooleanConsoleResult() {
        return DriverFunctionFactory.getFunction(getBooleanConsoleResultCore());
    }

    static DriverFunction<Boolean> clearConsole() {
        return SeleniumExecutor.execute(
            "clearConsole",
            MutationObserver.setConsoleFocusedFunction(),
            DevtoolsDriverTabbing.tabToBeforeClear(),
            DevtoolsDriverTabbing.inputTabAndEnter(),
            DevtoolsDriverTabbing.tabToConsoleAfterClear(),
            MutationObserver.isConsoleFocused(),
            noResultInConsole()
        );
    }

    private static <T> DriverFunction<T> doCommandCore(String command, DriverFunction<T> resultFunction) {
        return SeleniumExecutor.execute(
            "doCommandCore",
            clearConsole(),
            DevtoolsDriverTabbing.repeatTab(11),
            DevtoolsDriverTabbing.inputTabAndCommand(command),
            resultFunction
        );
    }

    static DriverFunction<String> doCommand(String command) {
        final var nameof = "doCommand";
        return ifDriver(nameof, CoreFormatter.isBlankMessageWithName(command, "Command"), doCommandCore(command, getConsoleResult()), CoreDataConstants.NULL_STRING);
    }

    static DriverFunction<Boolean> doBooleanCommand(String command) {
        final var nameof = "doBooleanCommand";
        return ifDriver(nameof, CoreFormatter.isBlankMessageWithName(command, "Command"), doCommandCore(command, getBooleanConsoleResult()), CoreDataConstants.NULL_BOOLEAN);
    }
}
