package com.github.karsaii.framework.selenium.namespaces.driver.screenshotter;

import com.github.karsaii.framework.selenium.constants.FactoryConstants;
import com.github.karsaii.framework.selenium.namespaces.Driver;
import com.github.karsaii.framework.selenium.namespaces.driver.typeconversion.DriverTypeConversionFunctions;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public interface ScreenshotterFunctions {
    private static TakesScreenshot getScreenshotter(WebDriver driver) {
        return (TakesScreenshot)driver;
    }

    static DriverFunction<TakesScreenshot> getScreenshotter() {
        return DriverTypeConversionFunctions.getSubtypeOfDriver(ScreenshotterFunctions::getScreenshotter, FactoryConstants.NULL_TAKES_SCREENSHOT);
    }
}
