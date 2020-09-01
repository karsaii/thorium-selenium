package examples.travelpage.namespaces;

import examples.travelpage.constants.DriverConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public interface FFDriverFunctions {
    static WebDriver getFirefoxDriver() {
        System.setProperty(DriverConstants.GECKODRIVER_PROPERTY_NAME, DriverConstants.GECKODIRVER_PROPERTY_PATH);

        final var binary = new FirefoxBinary();
        //binary.addCommandLineOptions("--headless");
        final var driver = new FirefoxDriver(new FirefoxOptions().setBinary(binary));
        DriverConstants.DRIVERS.putIfAbsent(DriverConstants.FF_BROWSER, driver);
        return DriverConstants.DRIVERS.get(DriverConstants.FF_BROWSER);
    }

    static WebDriver get() {
        return DriverConstants.DRIVERS.containsKey(DriverConstants.FF_BROWSER) ? DriverConstants.DRIVERS.get(DriverConstants.FF_BROWSER) : getFirefoxDriver();
    }
}
