package StepDefination;

import HelperClasses.pgGeneric;
import HelperClasses.pgPosition;
import io.cucumber.java.en.*;

public class PositionStepDef extends pgGeneric {

    pgPosition pgPositionObj = new pgPosition();

    @Given("user navigates to Settings and opens the Position tab")
    public void userNavigatesToSettingsAndOpensPositionTab() {
        pgPositionObj.navigateToPositionTab();
    }

    @When("user clicks the Add New position button")
    public void userClicksAddNewPositionButton() {
        pgPositionObj.clickAddNew();
    }

    @And("user enters position name {string}")
    public void userEntersPositionName(String name) {
        pgPositionObj.enterPositionName(name);
    }

    @And("user enters position description {string}")
    public void userEntersPositionDescription(String description) {
        pgPositionObj.enterPositionDescription(description);
    }

    @And("user selects pay type {string}")
    public void userSelectsPayType(String value) {
        pgPositionObj.selectPayType(value);
    }

    @And("user enters upfront pay {string}")
    public void userEntersUpfrontPay(String amount) {
        pgPositionObj.enterUpfrontPay(amount);
    }

    @And("user selects position department {string}")
    public void userSelectsPositionDepartment(String value) {
        pgPositionObj.selectDepartment(value);
    }

    @And("user selects position product {string}")
    public void userSelectsPositionProduct(String value) {
        pgPositionObj.selectProduct(value);
    }

    @And("user selects position tier schema {string}")
    public void userSelectsPositionTierSchema(String value) {
        pgPositionObj.selectTierSchema(value);
    }

    @And("user selects position milestone schema {string}")
    public void userSelectsPositionMilestoneSchema(String value) {
        pgPositionObj.selectMilestoneSchema(value);
    }

    @And("user enters position effective date as today")
    public void userEntersPositionEffectiveDateAsToday() {
        pgPositionObj.enterEffectiveDateToday();
    }

    @And("user clicks the Save position button")
    public void userClicksSavePositionButton() {
        pgPositionObj.clickSaveButton();
    }

    @Then("verify position is created successfully")
    public void verifyPositionCreatedSuccessfully() {
        if (!pgPositionObj.verifyPositionCreated()) {
            throw new AssertionError("Position was not created successfully.");
        }
    }

    @When("user searches for position")
    public void userSearchesForPosition() {
        pgPositionObj.searchPosition();
    }

    @Then("verify position appears in the position list")
    public void verifyPositionAppearsInList() {
        if (!pgPositionObj.verifyPositionInList()) {
            throw new AssertionError("Position not found in the list.");
        }
    }
}
