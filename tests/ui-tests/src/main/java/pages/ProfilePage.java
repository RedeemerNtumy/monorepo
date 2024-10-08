package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ProfilePage {
    private WebDriver driver;

    // Locators
    By institutionNameInput = By.id("institution_name_input");
    By institutionAddressInput = By.id("institution_address_input");
    By countrySelectInput = By.id("country_select_input");
    By qualificationLevelSelectInput = By.id("qualification_level_select_input");
    By programNameInput = By.id("program_name_input");
    By programStatusSelectInput = By.id("program_status_select_input");
    By dateCommencementInput = By.id("date_commencement_input");
    By dateCompletedInput = By.id("date_completed_input");
    By saveChangesButton = By.id("save_changes_button");
    By successMessage = By.xpath("//*[contains(text(),'success')]");

    // Constructor
    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    // Page Actions
    public void enterInstitutionName(String institutionName) {
        driver.findElement(institutionNameInput).sendKeys(institutionName);
    }

    public void enterInstitutionAddress(String address) {
        driver.findElement(institutionAddressInput).sendKeys(address);
    }

    public void selectCountry(String country) {
        Select countryDropdown = new Select(driver.findElement(countrySelectInput));
        countryDropdown.selectByVisibleText(country);
    }

    public void selectQualificationLevel(String qualification) {
        Select qualificationDropdown = new Select(driver.findElement(qualificationLevelSelectInput));
        qualificationDropdown.selectByVisibleText(qualification);
    }

    public void enterProgramName(String programName) {
        driver.findElement(programNameInput).sendKeys(programName);
    }

    public void selectProgramStatus(String status) {
        Select statusDropdown = new Select(driver.findElement(programStatusSelectInput));
        statusDropdown.selectByVisibleText(status);
    }

    public void enterDateCommencement(String date) {
        driver.findElement(dateCommencementInput).sendKeys(date);
    }

    public void enterDateCompleted(String date) {
        driver.findElement(dateCompletedInput).sendKeys(date);
    }

    public void clickSaveChanges() {
        driver.findElement(saveChangesButton).click();
    }

    public boolean isSuccessMessageDisplayed() {
        return driver.findElement(successMessage).isDisplayed();
    }
}
