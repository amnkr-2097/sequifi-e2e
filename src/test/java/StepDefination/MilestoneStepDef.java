package StepDefination;

import HelperClasses.pgMilestone;
import HelperClasses.pgGeneric;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.*;
import org.testng.Assert;
import java.util.List;
import java.util.Map;

public class MilestoneStepDef extends pgGeneric {

    pgMilestone pgMilestoneObj = new pgMilestone();

    @Given("user navigates to Settings and opens the Milestone tab")
    public void userNavigatesToMilestoneTab() {
        pgMilestoneObj.navigateToMilestoneTab();
    }

    @When("user clicks the Add New milestone button")
    public void userClicksAddNewMilestone() {
        pgMilestoneObj.clickAddNew();
    }

    @And("user enters schema name {string}")
    public void userEntersSchemaName(String name) {
        pgMilestoneObj.enterSchemaName(name);
    }

    @And("user enters schema description {string}")
    public void userEntersSchemaDescription(String description) {
        pgMilestoneObj.enterSchemaDescription(description);
    }

    @And("user enters trigger name {string} at index {int}")
    public void userEntersTriggerName(String name, int index) {
        pgMilestoneObj.enterTriggerName(name, index);
    }

    @And("user selects trigger date {string} at index {int}")
    public void userSelectsTriggerDate(String triggerDate, int index) {
        pgMilestoneObj.selectTriggerDate(triggerDate, index);
    }

    @And("user captures all trigger data")
    public void userCapturesAllTriggerData() {
        pgMilestoneObj.captureAllTriggerData();
    }

    @And("user clicks add trigger button")
    public void userClicksAddTrigger() {
        pgMilestoneObj.clickAddTriggerButton();
    }

    @And("user clicks the Save milestone button")
    public void userClicksSaveMilestone() {
        pgMilestoneObj.clickSave();
    }

    @When("user searches for milestone")
    public void userSearchesForMilestone() {
        pgMilestoneObj.searchMilestone();
    }

    @Then("verify milestone is created successfully")
    public void verifyMilestoneCreated() {
        if (!pgMilestoneObj.verifyMilestoneInList()) {
            throw new AssertionError("Milestone was not created successfully.");
        }
    }

    @Then("verify milestone appears in the list")
    public void verifyMilestoneInList() {
        if (!pgMilestoneObj.verifyMilestoneInList()) {
            throw new AssertionError("Milestone not found in the list.");
        }
    }

    @And("user captures initial trigger data")
    public void userCapturesInitialTriggerData() {
        pgMilestoneObj.captureInitialTriggerData();
    }
}
