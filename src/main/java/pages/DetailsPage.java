package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class DetailsPage {
    private WebDriver driver;
    private By deleteButton = By.xpath("//div[contains(@class, 'controls-top')]//button[contains(@class, 'delete-btn')]");//By.cssSelector(".controls-bottom .btn.delete-btn");
    private By editButton = By.xpath("//div[contains(@class, 'controls-top')]//button[contains(@class, 'edit-btn')]");
    private By confirmDeleteButton = By.xpath("//div[@class='delete-item-container show-modal']//button[@class='btn delete-btn']\n");
    private By cancelDeleteButton = By.xpath("//div[@class='delete-item-container show-modal']//button[@class='btn edit-btn']\n");
    private By goBackButton = By.className("go-back");

    public DetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickDeleteButton() {
        driver.findElement(deleteButton).click();
    }

    public EditInvoicePage clickEditButton() {
        driver.findElement(editButton).click();
        return new EditInvoicePage(driver);
    }

    public void clickGoBackButton() {
        driver.findElement(goBackButton).click();
    }

    public void clickConfirmDeleteButton() {
        driver.findElement(confirmDeleteButton).click();
    }

    public void clickCancelDeleteButton() {
        driver.findElement(cancelDeleteButton).click();
    }

    public String getDetailsPageUrl() {
        return driver.getCurrentUrl();
    }


}
