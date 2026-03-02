package TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    tags = "@E2ETC2008",
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
