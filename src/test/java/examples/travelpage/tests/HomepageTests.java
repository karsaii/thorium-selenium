package examples.travelpage.tests;

import com.github.karsaii.framework.selenium.namespaces.Driver;
import com.github.karsaii.framework.selenium.namespaces.DriverWaits;
import com.github.karsaii.framework.selenium.namespaces.SeleniumExecutor;
import com.github.karsaii.framework.selenium.namespaces.element.Element;
import examples.travelpage.constants.HomepageConstants;
import examples.travelpage.namespaces.FFDriverFunctions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HomepageTests {
    @Test
    public void homepageTest() {
        final var result = SeleniumExecutor.execute(
            "Go to Homepage",
            DriverWaits.navigateAndWait(System.getProperty("page"), 300, 3000),
            Element.inputWhenClickable(HomepageConstants.DESTINATION_FIELD_COMPLEX, "XYZ")
        ).apply(FFDriverFunctions.get());
        Assertions.assertTrue(result.status, result.message.toString());
    }

    @AfterAll
    public static void teardown() {
        Driver.quitDriver().apply(FFDriverFunctions.get());
    }
}
