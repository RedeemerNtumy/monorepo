package authentication;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTests {

    @Test
    public void testLoginWithValidEmailAndPassword() {
        System.setProperty("webdriver.edge.driver","resources/msedgedriver");
        WebDriver driver = new EdgeDriver();
        goToLoginPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        assertTrue(loginPage.isOnLoginPage());

        loginPage.enterEmail("tomsmith");
        loginPage.enterPassword("SuperSecretPassword!");
        loginPage.clickLoginButton();

        // Assertions after login could be added here depending on the behavior
        // For example, checking for a logout button or successful login message.

        driver.quit();
    }

    public void testLoginWithInvalidEmail() {}
    public void testLoginWithInvalidPassword() {}

    public void goToLoginPage(WebDriver driver) {
        driver.get("http://the-internet.herokuapp.com/login");
    }
}
