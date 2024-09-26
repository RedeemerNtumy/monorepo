package stepDefinitions;

import base.BaseTests;
import components.FilterByStatusComponent;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class FilterInvoicesStepDefinition extends BaseTests {
    private FilterByStatusComponent filterByStatusComponent;
    @Given("the user is on the invoices page")
    public void theUserIsOnTheInvoicesPage() {
        filterByStatusComponent = new FilterByStatusComponent(homePage.getDriver());
    }

    @When("the user selects {string} from the status filter")
    public void theUserSelectsFromTheStatusFilter(String status) throws IOException {
        filterByStatusComponent.hoverOverFilterByStatus();

        switch(status) {
            case "Draft":
                filterByStatusComponent.selectCheckbox(filterByStatusComponent.draftCheckbox);
                assertTrue("Draft checkbox should be selected",
                        filterByStatusComponent.isCheckboxSelected(filterByStatusComponent.draftCheckbox));
                break;
            case "Pending":
                filterByStatusComponent.selectCheckbox(filterByStatusComponent.pendingCheckbox);
                assertTrue("Pending checkbox should be selected",
                        filterByStatusComponent.isCheckboxSelected(filterByStatusComponent.pendingCheckbox));
                break;
            case "Paid":
                filterByStatusComponent.selectCheckbox(filterByStatusComponent.paidCheckbox);
                assertTrue("Paid checkbox should be selected",
                        filterByStatusComponent.isCheckboxSelected(filterByStatusComponent.paidCheckbox));
                break;
            default:
                throw new IllegalArgumentException("Invalid status: " + status);
        }
    }

    @Then("only {string} invoices are displayed")
    public void onlyInvoicesAreDisplayed(String status) throws IOException {
    }

    @Given("the user has applied a status filter")
    public void theUserHasAppliedAStatusFilter() {
    }

    @When("the user clears the filter")
    public void theUserClearsTheFilter() {
    }

    @Then("all invoices are displayed")
    public void allInvoicesAreDisplayed() {
    }

    @Given("the user has applied a {string} filter")
    public void theUserHasAppliedAFilter(String arg0) {
    }

    @When("the user changes the filter to {string}")
    public void theUserChangesTheFilterTo(String arg0) {
    }
}
