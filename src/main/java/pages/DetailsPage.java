package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DetailsPage {
    private WebDriver driver;
    private By deleteButton = By.xpath("//button[@id='delete-invoice']");
    private By saveButton = By.xpath("//button[@id='save-invoice']");
    private By confirmDeleteButton = By.xpath("//button[@id='confirm-delete']");
    private By cancelDeleteButton = By.xpath("//button[@id='cancel-delete']");
    private By invoiceDetail = By.id("invoice-detail");

    public DetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickDeleteButton() {
        driver.findElement(deleteButton).click();
    }

    public void clickSaveButton() {
        driver.findElement(saveButton).click();
    }

    public void clickConfirmDeleteButton() {
        driver.findElement(confirmDeleteButton).click();
    }

    public void clickCancelDeleteButton() {
        driver.findElement(cancelDeleteButton).click();
    }

//    public boolean isConfirmationModalDisplayed() {
//        // Implementation to check if modal is displayed
//    }
//
//    public void selectInvoice(String invoiceId) {
//        // Implementation to select an invoice
//    }
//
//    public void editInvoiceDetails(String invoiceNumber, String date, double amount, String status) {
//        // Implementation to edit invoice details
//    }
//
//    public boolean isInvoiceUpdated(String invoiceNumber, String date, double amount, String status) {
//        // Implementation to verify updated details
//    }
}
