package StepDefination;

import HelperClasses.pgGeneric;
import HelperClasses.pgPosition;
import io.cucumber.java.PendingException;
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

    @And("user selects pay frequency {string}")
    public void userSelectsPayType(String value) {
        pgPositionObj.selectPayFrequency(value);
    }

    @And("user selects position department")
    public void userSelectsPositionDepartment() {
        pgPositionObj.selectDepartment();
    }

    @And("user enters custom products created")
    public void userSelectsPositionProduct() {
        pgPositionObj.selectProduct();
    }

//    @And("user selects position tier schema {string}")
//    public void userSelectsPositionTierSchema(String value) {
//        pgPositionObj.selectTierSchema(value);
//    }
//
//    @And("user selects position milestone schema {string}")
//    public void userSelectsPositionMilestoneSchema(String value) {
//        pgPositionObj.selectMilestoneSchema(value);
//    }

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

    @And("user selects Worker type {string}")
    public void userSelectsWorkerType(String type) {
        pgPositionObj.selectWorkerType(type);
    }

    @And("user selects position main role {string}")
    public void userSelectsPositionMainRole(String role) {
        pgPositionObj.selectMainRole(role);
    }

    @And("user selects position permission group {string}")
    public void userSelectsPositionPermissionGroup(String permission) {
        pgPositionObj.selectPermissionGroup(permission);
    }

    @Then("verify the Select Tier Schemas modal is displayed")
    public void verifyModalDisplayed() {
        if (!pgPositionObj.isModalVisible()) {
            throw new AssertionError("Select Tier Schemas modal was not displayed.");
        }
    }

    @When("user closes the Select Tier Schemas modal")
    public void userClosesModal() {
        pgPositionObj.closeModal();
    }

    // ── Toggles ────────────────────────────────────────────────────────────────

    @And("user {string} Commission Tiers")
    public void userSetsCommissionTiers(String state) {
        pgPositionObj.enableCommissionTiers(state.equalsIgnoreCase("enables"));
    }

    @And("user {string} Upfront Tiers")
    public void userSetsUpfrontTiers(String state) {
        pgPositionObj.enableUpfrontTiers(state.equalsIgnoreCase("enables"));
    }

    @And("user {string} Override Tiers")
    public void userSetsOverrideTiers(String state) {
        pgPositionObj.enableOverrideTiers(state.equalsIgnoreCase("enables"));
    }

    // ── Commission dropdowns ───────────────────────────────────────────────────

    @And("user selects Commission Tier Schema")
    public void selectCommissionSchema() {
        pgPositionObj.selectCommissionTierSchema();
    }

    @And("user selects Commission Tier Advancement {string}")
    public void selectCommissionAdvancement(String value) {
        pgPositionObj.selectCommissionTierAdvancement(value);
    }

    // ── Upfront dropdowns ─────────────────────────────────────────────────────

    @And("user selects Upfront Tier Schema")
    public void selectUpfrontSchema() {
        pgPositionObj.selectUpfrontTierSchema();
    }

    @And("user selects Upfront Tier Advancement {string}")
    public void selectUpfrontAdvancement(String value) {
        pgPositionObj.selectUpfrontTierAdvancement(value);
    }

    // ── Override dropdowns ────────────────────────────────────────────────────

    @And("user selects Override Tier Schema")
    public void selectOverrideSchema() {
        pgPositionObj.selectOverrideTierSchema();
    }

    @And("user selects Override Tier Advancement {string}")
    public void selectOverrideAdvancement(String value) {
        pgPositionObj.selectOverrideTierAdvancement(value);
    }

    // ── Submit ─────────────────────────────────────────────────────────────────

    @And("user clicks Create on Select Tier Schemas modal")
    public void userClicksCreate() {
        pgPositionObj.clickCreate();
    }

    @And("user enters next button")
    public void userEntersNextButton() {
        pgPositionObj.clickNextButton();
        pause(3);
    }
}
