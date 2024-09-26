package base;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import pages.HomePage;

public class BaseTests {
    private WebDriver driver;
    protected HomePage homePage;


    @BeforeAll
    public void setUp() {
        System.setProperty("webdriver.edge.driver","resources/msedgedriver");
        driver = new EdgeDriver();
        goHome();
        homePage = new HomePage(driver);
    }

    @AfterAll
    public void tearDown(){
        driver.quit();
    }

    @BeforeEach
    public void goHome() {
        driver.get("https://invoice-app-6rkf.vercel.app/");
    }


}
