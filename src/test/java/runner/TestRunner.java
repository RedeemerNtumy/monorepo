package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features", // Path to your feature files
        glue = {"stepDefinitions"}, // Path to your step definitions
        plugin = {"pretty", "html:target/cucumber-reports"}
)
public class TestRunner {
    // This class will be empty. It serves as the entry point for the test framework.
}
