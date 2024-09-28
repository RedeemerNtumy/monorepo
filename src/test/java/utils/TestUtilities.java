package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;

public class TestUtilities {

    public static WebDriver setupDriver() {
        try {
            ChromeOptions options = new ChromeOptions();

            // Set platformName to 'linux' to match the Node configuration
            options.setCapability("platformName", "linux");

            // Optionally, set browserVersion or other capabilities if necessary
            // options.setCapability("browserVersion", "129.0");

            // Retrieve the Selenium Hub URL from environment variables
            String seleniumHubUrl = System.getenv("SELENIUM_REMOTE_URL");
            if (seleniumHubUrl == null || seleniumHubUrl.isEmpty()) {
                throw new IllegalStateException("SELENIUM_REMOTE_URL is not set in the environment variables.");
            }

            // Debug: Print the Selenium Hub URL
            System.out.println("Connecting to Selenium Hub at: " + seleniumHubUrl);

            WebDriver driver = new RemoteWebDriver(new URL(seleniumHubUrl), options);
            return driver;
        } catch (MalformedURLException e) {
            throw new RuntimeException("The URL provided for the Selenium Hub is malformed: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Failed to connect to Selenium Hub: " + e.getMessage(), e);
        }
    }
}



//package utils;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.remote.RemoteWebDriver;
//import java.net.URL;
//
//public class TestUtilities {
//
//    public static WebDriver setupDriver() {
//        try {
//            DesiredCapabilities capabilities = new DesiredCapabilities();
//            capabilities.setCapability("browserName", "MicrosoftEdge");
//            WebDriver driver = new RemoteWebDriver(new URL(System.getenv("SELENIUM_REMOTE_URL")), capabilities);
//            return driver;
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to connect to Selenium Hub: " + e.getMessage());
//        }
//    }
//}