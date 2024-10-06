package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;
    // Locators
    private By emailLocator = By.id("username"); //username
    private By passwordLocator = By.id("password");
    private By loginButtonLocator = By.cssSelector("button[type='submit']");//"button[type='submit']"


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    public void enterEmail(String username) {
        driver.findElement(emailLocator).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordLocator).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButtonLocator).click();
    }

    public boolean isOnLoginPage() {
        return driver.findElement(loginButtonLocator).isDisplayed();
    }
}