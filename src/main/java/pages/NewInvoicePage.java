package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewInvoicePage {
    private WebDriver driver;
    private By invoiceNumberInput = By.id("invoice-number");
    private By invoiceDateInput = By.id("invoice-date");
    private By invoiceAmountInput = By.id("invoice-amount");
    private By invoiceStatusSelect = By.id("invoice-status");
    private By createButton = By.id("create-invoice-button");

    public NewInvoicePage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterInvoiceDetails(String number, String date, double amount, String status) {
        driver.findElement(invoiceNumberInput).sendKeys(number);
        driver.findElement(invoiceDateInput).sendKeys(date);
        driver.findElement(invoiceAmountInput).sendKeys(String.valueOf(amount));
        driver.findElement(invoiceStatusSelect).sendKeys(status);
    }

    public void clickCreateButton() {
        driver.findElement(createButton).click();
    }

    public String getCreateInvoicePageUrl() {
        // This URL should be the exact URL for the create invoice page in your application
        return "http://yourapplication.com/create-invoice";
    }
}
