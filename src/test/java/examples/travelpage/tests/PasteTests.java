package examples.travelpage.tests;

import com.neathorium.framework.selenium.namespaces.Driver;
import com.neathorium.framework.selenium.namespaces.SeleniumExecutor;
import com.neathorium.framework.selenium.namespaces.element.Element;
import com.neathorium.framework.selenium.namespaces.element.ElementExpectedConditions;
import com.neathorium.core.namespaces.clipboard.ClipboardFunctions;
import com.neathorium.core.namespaces.executor.step.StepExecutor;
import examples.travelpage.constants.DuckDuckGoPageConstants;
import examples.travelpage.namespaces.FFDriverFunctions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.neathorium.core.namespaces.executor.step.StepFactory.step;
import static common.AssertionConstants.assertDataTrue;

public class PasteTests {
    @Test
    public void homepageTest() {
        final var value = "This is a clipboard item";
        assertDataTrue.accept(StepExecutor.execute(
            step(Driver.navigate("https://duckduckgo.com"), FFDriverFunctions.get()),
            step(ClipboardFunctions.copyToClipboard(), value),
            step(
                SeleniumExecutor.execute(
                    Element.inputWhenClickable(DuckDuckGoPageConstants.SEARCH_FIELD, Keys.chord(Keys.CONTROL, "v")),
                    ElementExpectedConditions.isAttributeValueTextEqual(DuckDuckGoPageConstants.SEARCH_FIELD, value)
                ),
                FFDriverFunctions.get()
            )
        ).apply());
    }

    @AfterAll
    public static void teardown() {
        FFDriverFunctions.unregister();
    }
}
