package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class HomePage {
    private WebDriver driver;
    private By newInvoiceButton = By.cssSelector(".btn.new-invoice-btn");
    private int myTestCard = 4;
    private By invoiceIds = By.xpath("//div[@class='invoice-list-items']//p[@class='item-id']");
    private By invoiceDetailsButton = By.xpath("//div[contains(@class, 'invoice-list-items')][" + myTestCard + "]");
    private By filterOptionSelector = By.cssSelector(".filter-options input");  // Generic selector for all checkboxes in the filter
    private By allInvoicesSelector = By.cssSelector(".invoice-list-item");  // Assuming each invoice item has this class

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public String getHomeUrl() {
        return driver.getCurrentUrl();
    }

    public String getChildFromInvoiceCard(String child) {
        WebElement parentCard = driver.findElement(invoiceDetailsButton);
        return parentCard.findElement(By.xpath(".//p[contains(@class,'" + child + "')]")).getText();
    }

    public String getStatusFromInvoiceCard() {
        WebElement parentCard = driver.findElement(invoiceDetailsButton);
        return parentCard.findElement(By.xpath(".//p[contains(@class, 'item-status')]//span")).getText();
    }

    public DetailsPage navigateToDetailsPage() {
        driver.findElement(invoiceDetailsButton).click();
        return new DetailsPage(driver);
    }

    public NewInvoicePage navigateToNewInvoicePage() {
        driver.findElement(newInvoiceButton).click();
        return new NewInvoicePage(driver);
    }

    public List<String> getAllInvoiceNumbers() {
        List<WebElement> invoiceNumbersElements = driver.findElements(invoiceIds);
        return invoiceNumbersElements.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    // Method to retrieve invoices by status
    public List<WebElement> getInvoicesByStatus(String status) {
        return driver.findElements(By.xpath("//div[contains(@class, 'invoice-list-item') and contains(@data-status, '" + status + "')]"));
    }

    // Method to clear all filters
    public void clearAllFilters() {
        List<WebElement> checkedFilters = driver.findElements(By.cssSelector(".filter-options input:checked"));
        for (WebElement checkbox : checkedFilters) {
            checkbox.click();  // Assuming clicking again will uncheck and remove the filter
        }
    }

    // Method to get all invoices
    public List<WebElement> getAllInvoices() {
        return driver.findElements(allInvoicesSelector);
    }
}
