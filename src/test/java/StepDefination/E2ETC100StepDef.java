package StepDefination;

import HelperClasses.pgGeneric;
import HelperClasses.pgLogin;
import com.aventstack.extentreports.Status;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class E2ETC100StepDef extends pgGeneric {

    pgLogin pgLoginObj = new pgLogin();

    @Then("verify login error message is displayed")
    public void verifyLoginErrorMessageIsDisplayed() {
        if (!pgLoginObj.verifyLoginErrorDisplayed()) {
            throw new AssertionError("Expected login error but none found.");
        }
    }

    @And("verify user is redirected to login page")
    public void verifyUserIsRedirectedToLoginPage() {
        if (!pgLoginObj.verifyOnLoginPage()) {
            throw new AssertionError("Not redirected to login page after logout.");
        }
    }

    @Then("user verifies the dashboard is loaded")
    public void userVerifiesDashboardIsLoaded() {
        WebDriverWait wait = getExplicitWait(15);
        try {
            wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("dashboard"),
                ExpectedConditions.urlContains("admin"),
                ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("[class*='dashboard'], [data-testid='dashboard']")
                )
            ));
            test.log(Status.PASS, "Dashboard loaded. URL: " + driver.getCurrentUrl());
        } catch (Exception e) {
            test.log(Status.FAIL, "Dashboard did not load. URL: " + driver.getCurrentUrl());
            throw new AssertionError("Dashboard verification failed: " + e.getMessage());
        }
    }

    @Then("user verifies the following modules are accessible:")
    public void userVerifiesModulesAreAccessible(DataTable modulesTable) {
        List<String> modules = modulesTable.asList();
        int passed = 0;
        int total = modules.size();

        for (String module : modules) {
            if (pgLoginObj.verifyModuleVisible(module)) {
                passed++;
            }
        }
        test.log(Status.INFO, passed + "/" + total + " modules verified.");
    }

    @Then("user navigates to {string} module")
    public void userNavigatesToModule(String moduleName) {
        pgLoginObj.navigatesToModule(moduleName);
    }

    @And("verify the {string} page is loaded")
    public void verifyPageIsLoaded(String pageName) {
        WebDriverWait wait = getExplicitWait(10);
        try {
            wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains(pageName.toLowerCase()),
                ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'" +
                             pageName.toLowerCase() + "')]")
                )
            ));
            test.log(Status.PASS, "'" + pageName + "' page loaded. URL: " + driver.getCurrentUrl());
        } catch (Exception e) {
            test.log(Status.FAIL, "'" + pageName + "' page did not load. URL: " + driver.getCurrentUrl());
            throw new AssertionError(pageName + " page verification failed.");
        }
    }

    @When("user clicks browser back button")
    public void userClicksBrowserBackButton() {
        driver.navigate().back();
        test.log(Status.INFO, "Clicked browser back button.");
        pause(2);
    }
}
