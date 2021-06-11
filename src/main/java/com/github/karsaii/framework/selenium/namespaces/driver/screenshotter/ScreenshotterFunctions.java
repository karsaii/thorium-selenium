package com.github.karsaii.framework.selenium.namespaces.driver.screenshotter;

import com.github.karsaii.core.constants.CoreConstants;
import com.github.karsaii.core.constants.CoreDataConstants;
import com.github.karsaii.core.constants.validators.CoreFormatterConstants;
import com.github.karsaii.core.extensions.namespaces.CoreUtilities;
import com.github.karsaii.core.namespaces.DataExecutionFunctions;
import com.github.karsaii.core.namespaces.DataFactoryFunctions;
import com.github.karsaii.core.namespaces.validators.CoreFormatter;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.selenium.constants.FactoryConstants;
import com.github.karsaii.framework.selenium.namespaces.EnvironmentUtilities;
import com.github.karsaii.framework.selenium.namespaces.driver.typeconversion.DriverTypeConversionFunctions;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.github.karsaii.framework.selenium.namespaces.factories.DriverFunctionFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Function;

import static com.github.karsaii.core.extensions.namespaces.CoreUtilities.areNotNull;
import static com.github.karsaii.core.namespaces.DataExecutionFunctions.ifDependency;
import static com.github.karsaii.core.namespaces.DataFactoryFunctions.appendMessage;
import static com.github.karsaii.core.namespaces.DataFactoryFunctions.getBoolean;
import static com.github.karsaii.core.namespaces.predicates.DataPredicates.isInvalidOrFalse;
import static com.github.karsaii.framework.selenium.namespaces.ExecutionCore.ifDriver;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public interface ScreenshotterFunctions {
    private static TakesScreenshot getScreenshotter(WebDriver driver) {
        return (TakesScreenshot)driver;
    }

    static DriverFunction<TakesScreenshot> getScreenshotter() {
        return DriverTypeConversionFunctions.getSubtypeOfDriver(ScreenshotterFunctions::getScreenshotter, FactoryConstants.NULL_TAKES_SCREENSHOT);
    }

    private static Data<String> takeScreenshot(Data<TakesScreenshot> shotterData, String folderPath, String filename) {
        final var formattedPath = CoreFormatter.getScreenshotFileName(folderPath, filename);
        var exception = CoreConstants.EXCEPTION;
        final var shotter = shotterData.object;
        try {
            FileUtils.copyFile(shotter.getScreenshotAs(OutputType.FILE), new File(formattedPath));
        } catch (IOException ex) {
            exception = ex;
        }

        final var status = CoreUtilities.isNonException(exception);
        final var message = "" + (status ? "Successfully taken screenshot, as: " : "Exception occurred" + CoreFormatterConstants.NEW_LINE + "Couldn't take screenshot as: ") + formattedPath;
        return DataFactoryFunctions.getWith(formattedPath, status, message, exception);
    }

    private static Function<Data<TakesScreenshot>, Data<String>> takeScreenshot(String path, String fileName) {
        return shotter -> takeScreenshot(shotter, path, fileName);
    }

    static DriverFunction<String> takeScreenShot(String path, String fileName) {
        return ifDependency(
            "takeScreenShot",
            CoreFormatter.isBlankMessageWithName(path, "Path") + CoreFormatter.isBlankMessageWithName(fileName, "fileName"),
            DataExecutionFunctions.validChain(getScreenshotter(), ScreenshotterFunctions.takeScreenshot(path, fileName), CoreDataConstants.NULL_STRING),
            CoreDataConstants.NULL_STRING
        )::apply;
    }

    static <Actual> Consumer<WebDriver> takeScreenShotOnFailure(Consumer<Data<Actual>> assertion, Data<Actual> data, String path, String fileName) {
        return driver -> {
            try {
                assertion.accept(data);
            } catch (AssertionError ex) {
                final var ldata = ScreenshotterFunctions.takeScreenShot(EnvironmentUtilities.getUsersProjectRootDirectory() + path, fileName).apply(driver);
                throw new AssertionError("takeScreenShotOnFailure: " + ldata.message + "\nOriginal Exception message: " + ex.getMessage());
            }
        };
    }

    static <Actual> DriverFunction<String> takeScreenShotOnDataFailure(Consumer<Data<Actual>> assertion, Data<Actual> data, String path, String fileName) {
        return ifDriver(
            "takeScreenShotOnDataFailure",
            areNotNull(assertion, data) && isNotBlank(path),
            driver -> {
                var ldata = CoreDataConstants.NULL_STRING;
                if (isInvalidOrFalse(data)) {
                    ldata = appendMessage(ScreenshotterFunctions.takeScreenShot(
                        EnvironmentUtilities.getUsersProjectRootDirectory() + path, fileName).apply(driver),
                        data.message.formatter.apply(data.message.nameof, data.message.message)
                    );
                }

                assertion.accept(data);

                return ldata;
            },
            CoreDataConstants.NULL_STRING
        );
    }
}
