package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;

public class TestUtilities {

    public static WebDriver setupDriver() {
        String seleniumHubUrl = System.getenv("SELENIUM_REMOTE_URL");

        if (seleniumHubUrl != null && !seleniumHubUrl.isEmpty()) {
            // Running inside Docker, use RemoteWebDriver
            try {
                ChromeOptions options = new ChromeOptions();
                options.setCapability("platformName", "linux");

                // Debug: Print the Selenium Hub URL
                System.out.println("Connecting to Selenium Hub at: " + seleniumHubUrl);

                WebDriver driver = new RemoteWebDriver(new URL(seleniumHubUrl), options);
                return driver;
            } catch (MalformedURLException e) {
                throw new RuntimeException("The URL provided for the Selenium Hub is malformed: " + e.getMessage());
            } catch (Exception e) {
                throw new RuntimeException("Failed to connect to Selenium Hub: " + e.getMessage(), e);
            }
        } else {
            // Running locally, use EdgeDriver
            try {
                // Set the path to your local EdgeDriver executable if necessary
                // System.setProperty("webdriver.edge.driver", "path/to/msedgedriver");

                EdgeOptions options = new EdgeOptions();
                WebDriver driver = new EdgeDriver(options);
                return driver;
            } catch (Exception e) {
                throw new RuntimeException("Failed to initialize EdgeDriver: " + e.getMessage(), e);
            }
        }
    }
}
