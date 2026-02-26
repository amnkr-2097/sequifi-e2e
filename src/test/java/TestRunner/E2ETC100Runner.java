package TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    tags = "@E2ETC1003",
    features = {"Feature File/E2ETC100.feature"},
    glue = {"StepDefination"},
    plugin = {
        "pretty",
        "html:Reports/CucumberReport.html"
    },
    monochrome = true
)
public class E2ETC100Runner extends AbstractTestNGCucumberTests {
}
