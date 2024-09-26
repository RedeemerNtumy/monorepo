package stepDefinitions;

import base.BaseTests;
import components.FilterByStatusComponent;
import org.openqa.selenium.WebElement;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.IOException;
import java.util.List;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class FilterInvoicesStepDefinition extends BaseTests {
    private FilterByStatusComponent filterByStatusComponent;

    @Given("the user is on the invoices page")
    public void theUserIsOnTheInvoicesPage() {
        filterByStatusComponent = new FilterByStatusComponent(homePage.getDriver());
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
        assertTrue(status + " checkbox should be selected",
                filterByStatusComponent.isCheckboxSelected(checkbox));
    }

    @Then("only {string} invoices are displayed")
    public void onlyInvoicesAreDisplayed(String status) throws IOException {
        // Simulated method call to retrieve invoices based on status
        List<WebElement> invoices = homePage.getInvoicesByStatus(status);
        assertTrue("Invoices displayed do not match the filter",
                invoices.stream().allMatch(invoice -> invoice.getAttribute("data-status").equals(status)));
    }

    @Given("the user has applied a status filter")
    public void theUserHasAppliedAStatusFilter() {
        // This could simulate setting up a pre-condition where a filter is already applied
        // Assume 'Paid' filter is applied for setup
        filterByStatusComponent.selectCheckbox(filterByStatusComponent.paidCheckbox);
    }

    @When("the user clears the filter")
    public void theUserClearsTheFilter() {
        // Assuming there's a method to clear all filters, which you should implement
        homePage.clearAllFilters();
    }

    @Then("all invoices are displayed")
    public void allInvoicesAreDisplayed() {
        // Assuming you can check if all invoices are displayed
        List<WebElement> invoices = homePage.getAllInvoices();
        assertFalse("Not all invoices are displayed", invoices.isEmpty());
    }

    @Given("the user has applied a {string} filter")
    public void theUserHasAppliedAFilter(String status) {
        // Setup the environment as if a filter has already been applied
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
        // Clear previous filters first
        homePage.clearAllFilters();

        // Then apply the new status filter
        theUserSelectsFromTheStatusFilter(newStatus);
    }
}
