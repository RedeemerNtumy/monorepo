package hooks;

import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import org.openqa.selenium.WebDriver;
import utils.TestUtilities;

public class TestHooks {

    private static WebDriver driver;

    @BeforeAll
    public static void globalSetup() {
        // Setup WebDriver or any other shared setup
        driver = TestUtilities.setupDriver();
    }

    @AfterAll
    public static void globalTeardown() {
        // Cleanup WebDriver or other resources
        if (driver != null) {
            driver.quit();
        }
    }

    @Before
    public void openHomePage() {
        driver.get("https://invoice-app-6rkf.vercel.app/");
    }
}
