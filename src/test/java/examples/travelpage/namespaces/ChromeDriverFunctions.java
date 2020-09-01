package examples.travelpage.namespaces;

import com.github.karsaii.core.namespaces.factories.ApplicationDataFactory;
import com.github.karsaii.core.namespaces.process.ProcessFunctions;
import examples.travelpage.constants.DriverConstants;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;

public interface ChromeDriverFunctions {
    /*static WebDriver getDriver() {
        final var port = ProcessConstants.CHROME_PORT;
        final var options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:" + port);
        options.addArguments("--remote-debugging-port=" + port);

        final var appData = ApplicationDataFactory.getWith(ProcessConstants.CHROME_NAME, ProcessConstants.CHROME_PATH, "--remote-debugging-port=" + port);
        final var builderData = ProcessFunctions.getBuilderWithRedirects(appData, ProcessFunctions.getPlatform("Windows"));
        Process processInstance;
        try {
            processInstance = builderData.object.start();
        } catch (IOException ex) {
            throw new InvalidArgumentException("CHROME path was eiter invalid, or permission denied. IO Exception occured:" + ex.getMessage());
        }
        ProcessConstants.PROCESSES.putIfAbsent(appData.name, processInstance);

        System.setProperty(DriverConstants.CHROMEDRIVER_PROPERTY_NAME, DriverConstants.CHROMEDRIVER_PROPERTY_PATH);
        final var driver = new ChromeDriver(options);
        DriverConstants.DRIVERS.putIfAbsent(DriverConstants.CHROME_BROWSER, driver);
        return DriverConstants.DRIVERS.get(DriverConstants.CHROME_BROWSER);
    }

    static WebDriver get() {
        return DriverConstants.DRIVERS.containsKey(DriverConstants.CHROME_BROWSER) ? DriverConstants.DRIVERS.get(DriverConstants.CHROME_BROWSER) : getDriver();
    }*/
}
