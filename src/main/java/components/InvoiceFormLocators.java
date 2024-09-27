package components;

import org.openqa.selenium.By;

public class InvoiceFormLocators {
    // Bill From
    public static final By SENDER_STREET = By.cssSelector("input[id='senderStreet']");
    public static final By SENDER_CITY = By.cssSelector("input[id='senderCity']");
    public static final By SENDER_POST_CODE = By.cssSelector("input[id='senderPostCode']");
    public static final By SENDER_COUNTRY = By.cssSelector("input[id='senderCountry']");

    // Bill To
    public static final By CLIENT_NAME = By.cssSelector("input[id='clientName']");
    public static final By CLIENT_EMAIL = By.cssSelector("input[id='clientEmail']");
    public static final By CLIENT_STREET = By.cssSelector("input[id='clientStreet']");
    public static final By CLIENT_CITY = By.cssSelector("input[id='clientCity']");
    public static final By CLIENT_POST_CODE = By.cssSelector("input[id='clientPostCode']");
    public static final By CLIENT_COUNTRY = By.cssSelector("input[id='clientCountry']");

    // Invoice Details
    public static final By INVOICE_DATE = By.cssSelector("input[id='createdAt']");
    public static final By PAYMENT_TERMS = By.cssSelector("select[id='paymentTerms']");
    public static final By DESCRIPTION = By.cssSelector("input[id='description']");

    // Buttons
    public static final By ADD_NEW_ITEM = By.cssSelector("button.new-item");
    public static final By SAVE_CHANGES = By.cssSelector("button.save");
    public static final By CANCEL = By.cssSelector("button.discard");
}
