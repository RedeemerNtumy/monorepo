package stepDefinitions;

import components.FilterByStatusComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.cucumber.java.en.*;
import pages.HomePage;
import utils.TestUtilities;
import java.io.IOException;
import java.util.List;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class FilterInvoicesStepDefinition {
    private FilterByStatusComponent filterByStatusComponent;
    private WebDriver driver;
    private HomePage homePage;

    public FilterInvoicesStepDefinition() {
        this.driver = TestUtilities.setupDriver();
        this.homePage = new HomePage(driver);  // Initialize HomePage with the driver
    }

    @Given("the user is on the invoices page")
    public void theUserIsOnTheInvoicesPage() {
        driver.get("https://invoice-app-6rkf.vercel.app/");
        filterByStatusComponent = new FilterByStatusComponent(driver);
    }

    @When("the user selects {string} from the status filter")
    public void theUserSelectsFromTheStatusFilter(String status) throws IOException {
        filterByStatusComponent.hoverOverFilterByStatus();
        WebElement checkbox = null;

        switch (status) {
            case "Draft":
                checkbox = filterByStatusComponent.draftCheckbox;
                break;
            case "Pending":
                checkbox = filterByStatusComponent.pendingCheckbox;
                break;
            case "Paid":
                checkbox = filterByStatusComponent.paidCheckbox;
                break;
            default:
                throw new IllegalArgumentException("Invalid status: " + status);
        }

        filterByStatusComponent.selectCheckbox(checkbox);
        assertTrue(status + " checkbox should be selected", filterByStatusComponent.isCheckboxSelected(checkbox));
    }

    @Then("only {string} invoices are displayed")
    public void onlyInvoicesAreDisplayed(String status) throws IOException {
        List<WebElement> invoices = homePage.getInvoicesByStatus(status);
        assertTrue("Invoices displayed do not match the filter", invoices.stream().allMatch(invoice -> invoice.getAttribute("data-status").equals(status)));
    }

    @Given("the user has applied a status filter")
    public void theUserHasAppliedAStatusFilter() {
        filterByStatusComponent.selectCheckbox(filterByStatusComponent.paidCheckbox);
    }

    @When("the user clears the filter")
    public void theUserClearsTheFilter() {
        homePage.clearAllFilters();
    }

    @Then("all invoices are displayed")
    public void allInvoicesAreDisplayed() {
        List<WebElement> invoices = homePage.getAllInvoices();
        assertFalse("Not all invoices are displayed", invoices.isEmpty());
    }

    @Given("the user has applied a {string} filter")
    public void theUserHasAppliedAFilter(String status) {
        WebElement checkbox = null;
        switch (status) {
            case "Draft":
                checkbox = filterByStatusComponent.draftCheckbox;
                break;
            case "Pending":
                checkbox = filterByStatusComponent.pendingCheckbox;
                break;
            case "Paid":
                checkbox = filterByStatusComponent.paidCheckbox;
                break;
            default:
                throw new IllegalArgumentException("Invalid status: " + status);
        }
        filterByStatusComponent.selectCheckbox(checkbox);
    }

    @When("the user changes the filter to {string}")
    public void theUserChangesTheFilterTo(String newStatus) throws IOException {
        homePage.clearAllFilters();
        theUserSelectsFromTheStatusFilter(newStatus);
    }
}
