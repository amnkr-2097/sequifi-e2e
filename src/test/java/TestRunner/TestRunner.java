package TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    tags = "@department",
    features = {"Feature File/E2ETC200.feature"},
    glue = {"StepDefination"},
    plugin = {
        "pretty",
        "html:Reports/Settings_Report.html",
    },
    monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
