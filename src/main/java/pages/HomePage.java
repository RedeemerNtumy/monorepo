package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import java.util.stream.Collectors;

public class HomePage {
    private WebDriver driver;
    private By newInvoiceButton = By.cssSelector("button.create p");
    private int myTestCard = 4;
    private By invoiceIds = By.xpath("//div[@class='invoice-list-items']//p[@class='item-id']");
    private By invoiceDetailsButton = By.xpath("//div[contains(@class, 'invoice-list-items')][" + myTestCard + "]");
    private By filterOptionSelector = By.cssSelector(".filter-options input");
    private By allInvoicesSelector = By.cssSelector(".invoice-list-item");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
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

    public HomePage_CreateInvoice navigateToNewInvoicePage() {
        driver.findElement(newInvoiceButton).click();
        return new HomePage_CreateInvoice(driver);
    }

    public List<String> getAllInvoiceNumbers() {
        List<WebElement> invoiceNumbersElements = driver.findElements(invoiceIds);
        return invoiceNumbersElements.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public List<WebElement> getInvoicesByStatus(String status) {
        return driver.findElements(By.xpath("//div[contains(@class, 'invoice-list-item') and contains(@data-status, '" + status + "')]"));
    }

    public void clearAllFilters() {
        List<WebElement> checkedFilters = driver.findElements(By.cssSelector(".filter-options input:checked"));
        for (WebElement checkbox : checkedFilters) {
            checkbox.click();
        }
    }

    public List<WebElement> getAllInvoices() {
        return driver.findElements(allInvoicesSelector);
    }

    public WebElement getLastInvoice() {
        List<WebElement> invoices = driver.findElements(allInvoicesSelector);
        return invoices.get(invoices.size() - 1);
    }

    public String getInvoiceDetailFromLastInvoice(String detailClass) {
        WebElement lastInvoice = getLastInvoice();
        return lastInvoice.findElement(By.cssSelector("." + detailClass)).getText();
    }
}
