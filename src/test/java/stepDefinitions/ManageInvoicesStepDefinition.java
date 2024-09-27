//package stepDefinitions;
//
//
//import io.cucumber.java.en.*;
//import org.openqa.selenium.WebDriver;
//import pages.HomePage;
//import pages.HomePage_CreateInvoice;
//import utils.TestUtilities;
//
//import static org.junit.Assert.assertEquals;
//
//public class ManageInvoicesStepDefinition{
//    private WebDriver driver;
//    private HomePage_CreateInvoice createInvoicePage;
//    private HomePage mainPage;
//
//    public ManageInvoicesStepDefinition() {
//        this.driver = TestUtilities.setupDriver();
//        this.mainPage = new HomePage(driver);  // Initialize HomePage with the driver
//    }
//
//    @Given("the user is on the create invoice page")
//    public void theUserIsOnTheCreateInvoicePage() {
//        driver.get("https://invoice-app-6rkf.vercel.app/");
//        createInvoicePage = mainPage.navigateToNewInvoicePage();
//    }
//
//    @When("the user fills in all required fields")
//    public void theUserFillsInAllRequiredFields() {
//        createInvoicePage
//                .fillBillFrom("123 Sender St", "Sender City", "12345", "Sender Country")
//                .fillBillTo("John Doe", "john@example.com", "456 Client St", "Client City", "67890", "Client Country")
//                .fillInvoiceDetails("9/13/2024", "Net 30 Days", "Test Invoice")
//                .addNewItem();
//    }
//
//    @And("the user clicks the {string} button")
//    public void theUserClicksTheButton(String buttonName) {
//        switch (buttonName) {
//            case "Create Invoice":
//                createInvoicePage = mainPage.navigateToNewInvoicePage();
//                break;
//            case "Save":
//                mainPage = createInvoicePage.saveChanges();
//                break;
////            case "Delete":
////                detailsPage.clickDeleteButton();
////                break;
//        }
//    }
//
//    @Then("a new invoice is created with the entered details")
//    public void aNewInvoiceIsCreatedWithTheEnteredDetails() {
//        String expectedClientName = "John Doe";
//        String expectedStatus = "Pending"; // Example status
//        String expectedInvoiceAmount = "£1,000.00"; // Example amount
//
//        mainPage = new HomePage(driver); // Assuming driver is initialized and navigated to the homepage
//        String actualClientName = mainPage.getInvoiceDetailFromLastInvoice("client-name-class"); // Replace 'client-name-class' with actual class/identifier
//        String actualStatus = mainPage.getInvoiceDetailFromLastInvoice("status-class"); // Replace 'status-class' with actual class/identifier
//        String actualInvoiceAmount = mainPage.getInvoiceDetailFromLastInvoice("amount-class"); // Replace 'amount-class' with actual class/identifier
//
//        assertEquals(actualClientName, expectedClientName, "Client name does not match on the last invoice.");
//        assertEquals(actualStatus, expectedStatus, "Invoice status does not match on the last invoice.");
//        assertEquals(actualInvoiceAmount, expectedInvoiceAmount, "Invoice amount does not match on the last invoice.");
//    }
//
////    @When("the user loads the page")
////    public void theUserLoadsThePage() {
////        driver.navigate().to(invoiceListPage.getInvoicesPageUrl());
////    }
////
////    @Then("a list of all invoices is displayed with details such as invoice number, date, amount, and status")
////    public void aListOfAllInvoicesIsDisplayedWithDetailsSuchAsInvoiceNumberDateAmountAndStatus() {
////        Assert.assertTrue(invoiceListPage.isInvoiceListDisplayed());
////    }
////
////    @When("the user selects an invoice to edit")
////    public void theUserSelectsAnInvoiceToEdit() {
////        detailsPage.selectInvoice("1234");
////    }
////
////    @And("the user makes changes to the invoice")
////    public void theUserMakesChangesToTheInvoice() {
////        detailsPage.editInvoiceDetails("1234", "2024-09-21", 1600.00, "Paid");
////    }
////
////    @Then("the invoice is updated with the new details")
////    public void theInvoiceIsUpdatedWithTheNewDetails() {
////        Assert.assertTrue(detailsPage.isInvoiceUpdated("1234", "2024-09-21", 1600.00, "Paid"));
////    }
////
////    @Then("the system prompts the user for confirmation")
////    public void theSystemPromptsTheUserForConfirmation() {
////        Assert.assertTrue(detailsPage.isConfirmationModalDisplayed());
////    }
////
////    @When("the user confirms the deletion")
////    public void theUserConfirmsTheDeletion() {
////        detailsPage.clickConfirmDeleteButton();
////    }
////
////    @Then("the invoice is deleted from the system")
////    public void theInvoiceIsDeletedFromTheSystem() {
////        Assert.assertFalse(invoiceListPage.isInvoicePresent("1234"));
////    }
////
////    @When("the user cancels the deletion")
////    public void theUserCancelsTheDeletion() {
////        detailsPage.clickCancelDeleteButton();
////    }
////
////    @Then("the invoice is not deleted")
////    public void theInvoiceIsNotDeleted() {
////        Assert.assertTrue(invoiceListPage.isInvoicePresent("1234"));
////    }
//}
