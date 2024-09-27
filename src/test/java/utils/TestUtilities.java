package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;

public class TestUtilities {

    public static WebDriver setupDriver() {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("browserName", "MicrosoftEdge");
            WebDriver driver = new RemoteWebDriver(new URL(System.getenv("SELENIUM_REMOTE_URL")), capabilities);
            return driver;
        } catch (Exception e) {
            throw new RuntimeException("Failed to connect to Selenium Hub: " + e.getMessage());
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