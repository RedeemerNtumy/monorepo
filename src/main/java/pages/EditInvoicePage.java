package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditInvoicePage {
    private WebDriver driver;
    private By invoiceNumberInput = By.id("invoice-number");
    private By invoiceDateInput = By.id("invoice-date");
    private By invoiceAmountInput = By.id("invoice-amount");
    private By invoiceStatusSelect = By.id("invoice-status");
    private By saveButton = By.id("save-invoice-button");

    public EditInvoicePage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterInvoiceDetails(String number, String date, double amount, String status) {
        driver.findElement(invoiceNumberInput).clear();
        driver.findElement(invoiceNumberInput).sendKeys(number);
        driver.findElement(invoiceDateInput).clear();
        driver.findElement(invoiceDateInput).sendKeys(date);
        driver.findElement(invoiceAmountInput).clear();
        driver.findElement(invoiceAmountInput).sendKeys(String.valueOf(amount));
        driver.findElement(invoiceStatusSelect).sendKeys(status);
    }

    public void clickSaveButton() {
        driver.findElement(saveButton).click();
    }

    public String getEditInvoicePageUrl() {
        // This URL should be dynamically retrieved if it changes based on the invoice being edited
        return driver.getCurrentUrl();
    }
}
