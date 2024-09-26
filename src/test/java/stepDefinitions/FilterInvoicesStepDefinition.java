package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FilterInvoicesStepDefinition {
    @Given("the user is on the invoices page")
    public void theUserIsOnTheInvoicesPage() {
    }

    @When("the user selects {string} from the status filter")
    public void theUserSelectsFromTheStatusFilter(String arg0) {
    }

    @Then("only {string} invoices are displayed")
    public void onlyInvoicesAreDisplayed(String arg0) {
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
