package StepDefination;

import HelperClasses.pgGeneric;
import HelperClasses.pgTiers;
import io.cucumber.java.en.*;

import java.util.Objects;

public class TiersStepDef extends pgGeneric {

    pgTiers pgTiersObj = new pgTiers();

    @Given("user navigates to Settings and opens the Tiers tab")
    public void userNavigatesToSettingsAndOpensTiersTab() {
        pgTiersObj.navigateToTiersTab();
    }

    @When("user clicks the Add New tier button")
    public void userClicksAddNewTierButton() {
        pgTiersObj.clickAddNew();
    }

    @And("user enters tier schema name {string}")
    public void userEntersTierSchemaName(String name) {
        pgTiersObj.enterSchemaName(name);
    }

    @And("user enters tier schema description {string}")
    public void userEntersTierSchemaDescription(String description) {
        pgTiersObj.enterSchemaDescription(description);
    }

    @And("user selects tier system {string}")
    public void userSelectsTierSystem(String value) {
        pgTiersObj.selectTierSystem(value);
    }

    @And("user selects tier metrics {string}")
    public void userSelectsTierMetrics(String value) {
        pgTiersObj.selectTierMetrics(value);
    }

    @And("user selects tier type {string}")
    public void userSelectsTierType(String value) {
        pgTiersObj.selectTierType(value);
    }

    @And("user clicks the Save tier button")
    public void userClicksSaveTierButton() {
        pgTiersObj.clickSaveButton();
    }

    @Then("verify tier is created successfully")
    public void verifyTierCreatedSuccessfully() {
        if (!pgTiersObj.verifyTierCreated()) {
            throw new AssertionError("Tier was not created successfully.");
        }
    }

    @When("user searches for tier")
    public void userSearchesForTier() {
        pgTiersObj.searchTier();
    }

    @Then("verify tier appears in the tier list")
    public void verifyTierAppearsInList() {
        if (!pgTiersObj.verifyTierInList()) {
            throw new AssertionError("Tier not found in the list.");
        }
    }
}
