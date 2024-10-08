package talent;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import pages.LoginPage;
import pages.ProfilePage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProfileManagementTests {
    private static WebDriver driver;
    private static LoginPage loginPage;
    private static ProfilePage profilePage;

    @BeforeAll
    public static void setup() {
        // Set up the Edge WebDriver
        System.setProperty("webdriver.edge.driver", "resources/msedgedriver");

        // Initialize the WebDriver
        driver = new EdgeDriver();

        // Go to the login page
        goToLoginPage(driver);

        // Create a LoginPage object
        loginPage = new LoginPage(driver);

        // Log in (Assuming you have a method in LoginPage to perform login)
        loginPage.enterEmail("email@email.com");
        loginPage.enterPassword("password");
        ProfilePage profilePage=loginPage.clickLoginButton();
    }

    public static void goToLoginPage(WebDriver driver) {
        driver.get("http://the-internet.herokuapp.com/login");
    }

    @Test
    public void testProfileInformationEntry() {
        // Fill in the profile form using the ProfilePage object
        profilePage.enterInstitutionName("University of Ghana");
        profilePage.enterInstitutionAddress("XV15 street, Legon, Accra");
        profilePage.selectCountry("Ghana");
        profilePage.selectQualificationLevel("Bachelor of Science");
        profilePage.enterProgramName("Computer Science");
        profilePage.selectProgramStatus("Graduated");
        profilePage.enterDateCommencement("October 14, 1985");
        profilePage.enterDateCompleted("October 14, 1985");

        // Click the save changes button
        profilePage.clickSaveChanges();

        // Verify if the success message is displayed
        assertTrue(profilePage.isSuccessMessageDisplayed(), "Success message should be displayed.");
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            // Close the browser after test completion
            driver.quit();
        }
    }
}
