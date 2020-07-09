package examples.travelpage.namespaces;

import examples.travelpage.constants.DriverConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public interface FFDriverFunctions {
    static WebDriver getFirefoxDriver() {
        System.setProperty(DriverConstants.GECKODRIVER_PROPERTY_NAME, DriverConstants.GECKODIRVER_PROPERTY_PATH);

        final var driver = new FirefoxDriver();
        DriverConstants.drivers.putIfAbsent(DriverConstants.BROWSER, driver);
        return DriverConstants.drivers.get(DriverConstants.BROWSER);
    }

    static WebDriver get() {
        return DriverConstants.drivers.containsKey(DriverConstants.BROWSER) ? DriverConstants.drivers.get(DriverConstants.BROWSER) : getFirefoxDriver();
    }
}
