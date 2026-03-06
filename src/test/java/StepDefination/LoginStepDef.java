package StepDefination;

import HelperClasses.pgGeneric;
import HelperClasses.pgLogin;
import com.aventstack.extentreports.Status;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;

import java.util.Map;

public class LoginStepDef extends pgGeneric {

    pgLogin pgLoginObj = new pgLogin();

    private static final Map<String, String> ENVIRONMENT_URLS = Map.of(
            "Fiber Stage",  "http://localhost:3000/auth",
            "Pest Stage",   "http://localhost:3000/auth",
            "Turf Stage",   "http://localhost:3000/auth",
            "Solar Stage",  "http://localhost:3000/auth"
            //            "Solar Stage",  "https://solarstage.sequifi.com/auth",
//        "Pest Stage",   "http://peststage.sequifi.com",
//        "Turf Stage",   "https://turfstage.sequifi.com/admin-dashboard",
//        "Fiber Stage",  "http://fiberstage.sequifi.com"
    );

    private static final Map<String, String> PASSWORD_KEYS = Map.of(
        "PW:Solar",  "Aveyo#123",
        "PW:Pest",   "Peststage#1190",
        "PW:Turf",   "Frdmturf#1190",
        "PW:Fiber",  "Embrase#1190",
        "PW:Empty",  ""
    );

    @Before
    public void setUp(Scenario scenario) {

        String scenarioName = scenario.getName();
        String[] tags = scenario.getSourceTagNames().toArray(new String[0]);

        initReport(scenarioName, tags);
        initDriver();
    }

    @Given("user navigates to {string} WebPage")
    public void userNavigatesToWebPage(String webPage) {
        String url = ENVIRONMENT_URLS.get(webPage);
        if (url == null) {
            test.log(Status.FAIL, "Unknown environment: " + webPage);
            throw new IllegalArgumentException("Unknown environment: " + webPage);
        }
        driver.get(url);
        test.log(Status.INFO, "Navigated to " + webPage + " (" + url + ")");
    }

    @When("user enters the {string} and {string} and Logs-In")
    public void userEntersCredentialsAndLogsIn(String userID, String passKey) {
        String password = PASSWORD_KEYS.get(passKey);
        if (password == null) {
            password = passKey;
        }
        pgLoginObj.log_in(userID, password);
        test.log(Status.INFO, "Submitted credentials for: " + userID);
    }

    @And("verify user is logged in")
    public void verifyUserIsLoggedIn() {
        if (!pgLoginObj.verifyLoggedIn()) {
            throw new AssertionError("User is not logged in — dashboard/profile menu not found.");
        }
    }

    @Then("user logs out from the website")
    public void userLogsOutFromTheWebsite() {
        pgLoginObj.logOut();
        test.log(Status.PASS, "User logged out successfully.");
    }

    @And("user close the window")
    public void userCloseTheWindow() {
        quitDriver();
        test.log(Status.INFO, "Browser window closed.");
    }

    @After
    public void tearDown() {
        flushReport();
        quitDriver();
    }
}
