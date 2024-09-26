//package stepDefinitions;
//
//import base.BaseTests;
//import org.openqa.selenium.WebDriver;
//import pages.DetailsPage;
//import pages.HomePage;
//import pages.InvoiceCreationPage;
//import pages.InvoiceListPage;
//import org.junit.Assert;
//
//import io.cucumber.java.en.And;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import pages.NewInvoicePage;
//
//import static java.sql.DriverManager.getDriver;
//
//public class ManageInvoicesStepDefinition extends BaseTests {
//    private WebDriver driver = getDriver();
//    private NewInvoicePage createInvoicePage = new NewInvoicePage(driver);
//    private HomePage invoiceListPage = new HomePage(driver);
//    private DetailsPage detailsPage = new DetailsPage(driver);
//
//    @Given("the user is on the create invoice page")
//    public void theUserIsOnTheCreateInvoicePage() {
//        driver.navigate().to(createInvoicePage.getCreateInvoicePageUrl());
//    }
//
//    @When("the user fills in all required fields")
//    public void theUserFillsInAllRequiredFields() {
//        createInvoicePage.enterInvoiceDetails("1234", "2024-09-20", 1500.00, "Pending");
//    }
//
//    @And("the user clicks the {string} button")
//    public void theUserClicksTheButton(String buttonName) {
//        switch (buttonName) {
//            case "Create Invoice":
//                createInvoicePage.clickCreateButton();
//                break;
//            case "Save":
//                detailsPage.clickSaveButton();
//                break;
//            case "Delete":
//                detailsPage.clickDeleteButton();
//                break;
//        }
//    }
//
//    @Then("a new invoice is created with the entered details")
//    public void aNewInvoiceIsCreatedWithTheEnteredDetails() {
//        Assert.assertTrue(invoiceListPage.isInvoicePresent("1234"));
//    }
//
//    @When("the user loads the page")
//    public void theUserLoadsThePage() {
//        driver.navigate().to(invoiceListPage.getInvoicesPageUrl());
//    }
//
//    @Then("a list of all invoices is displayed with details such as invoice number, date, amount, and status")
//    public void aListOfAllInvoicesIsDisplayedWithDetailsSuchAsInvoiceNumberDateAmountAndStatus() {
//        Assert.assertTrue(invoiceListPage.isInvoiceListDisplayed());
//    }
//
//    @When("the user selects an invoice to edit")
//    public void theUserSelectsAnInvoiceToEdit() {
//        detailsPage.selectInvoice("1234");
//    }
//
//    @And("the user makes changes to the invoice")
//    public void theUserMakesChangesToTheInvoice() {
//        detailsPage.editInvoiceDetails("1234", "2024-09-21", 1600.00, "Paid");
//    }
//
//    @Then("the invoice is updated with the new details")
//    public void theInvoiceIsUpdatedWithTheNewDetails() {
//        Assert.assertTrue(detailsPage.isInvoiceUpdated("1234", "2024-09-21", 1600.00, "Paid"));
//    }
//
//    @Then("the system prompts the user for confirmation")
//    public void theSystemPromptsTheUserForConfirmation() {
//        Assert.assertTrue(detailsPage.isConfirmationModalDisplayed());
//    }
//
//    @When("the user confirms the deletion")
//    public void theUserConfirmsTheDeletion() {
//        detailsPage.clickConfirmDeleteButton();
//    }
//
//    @Then("the invoice is deleted from the system")
//    public void theInvoiceIsDeletedFromTheSystem() {
//        Assert.assertFalse(invoiceListPage.isInvoicePresent("1234"));
//    }
//
//    @When("the user cancels the deletion")
//    public void theUserCancelsTheDeletion() {
//        detailsPage.clickCancelDeleteButton();
//    }
//
//    @Then("the invoice is not deleted")
//    public void theInvoiceIsNotDeleted() {
//        Assert.assertTrue(invoiceListPage.isInvoicePresent("1234"));
//    }
//}
