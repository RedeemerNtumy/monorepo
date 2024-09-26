package pages;

import org.openqa.selenium.WebDriver;

public class EditInvoicePage {
    private WebDriver driver;

    public EditInvoicePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getEditInvoicePageUrl(){
        return driver.getCurrentUrl();
    }
}
