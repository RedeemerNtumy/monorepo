package pages;

import org.openqa.selenium.WebDriver;

public class NewInvoicePage {
    private WebDriver driver;

    public NewInvoicePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getNewInvoicePageUrl(){
        return driver.getCurrentUrl();
    }
}
