package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class TestUtilities {

    public static WebDriver setupDriver() {
        System.setProperty("webdriver.edge.driver","resources/msedgedriver");
        return new EdgeDriver();
    }
}
