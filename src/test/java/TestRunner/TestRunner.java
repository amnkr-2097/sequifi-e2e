package TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    tags = "@SolarE2E",
    features = {"Feature File/SolarEndToEnd.feature"},
//        features = {"Feature File/E2ETC200.feature"},
    glue = {"StepDefination"},
    plugin = {
        "pretty",
        "html:Reports/Settings_Report.html",
    },
    monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
