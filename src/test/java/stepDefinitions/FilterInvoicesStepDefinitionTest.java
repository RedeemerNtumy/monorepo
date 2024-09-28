package stepDefinitions;

import components.FilterByStatusComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.cucumber.java.en.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import pages.HomePage;
import utils.TestUtilities;
import java.io.IOException;
import java.util.List;
import static org.junit.Assert.assertTrue;

public class FilterInvoicesStepDefinitionTest {
    private WebDriver driver;
    private HomePage homePage;
    private FilterByStatusComponent filterByStatusComponent;

    @Before
    public void setUp() {
        this.driver = TestUtilities.setupDriver();
        this.homePage = new HomePage(driver);
        this.filterByStatusComponent = new FilterByStatusComponent(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    @Given("the user is on the invoices page")
    public void theUserIsOnTheInvoicesPage() {
        driver.get("https://invoice-app-6rkf.vercel.app/");
        filterByStatusComponent = new FilterByStatusComponent(driver);
    }

    @When("the user selects {string} from the status filter")
    public void theUserSelectsFromTheStatusFilter(String status) throws IOException {
        filterByStatusComponent.hoverOverFilterByStatus();
        WebElement checkbox;

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
        assertTrue("Invoices displayed do not match the filter",
                invoices.stream().allMatch(invoice -> invoice.getAttribute("data-status").equals(status)));
    }
}
