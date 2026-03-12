package TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    tags = "@E2ETC1001",
    features =
//       {"Feature File/SolarEndToEnd.feature"},
//       {"Feature File/E2ETC200.feature"},
         {"Feature File/E2ETC100.feature"},
    glue = {"StepDefination"},
    plugin = {
        "pretty",
        "html:Reports/Settings_Report.html",
    },
    monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
