package StepDefination;

import HelperClasses.pgGeneric;
import HelperClasses.pgLocation;
import com.aventstack.extentreports.Status;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

import java.util.List;
import java.util.Map;

public class LocationStepDef extends pgGeneric {

    pgLocation pgLocationObj = new pgLocation();

    @Given("user navigates to Settings and opens the Location tab")
    public void userNavigatesToSettingsAndOpensLocationTab() {
        pgLocationObj.navigateToLocationTab();
    }

    @When("user clicks the Add New location button")
    public void userClicksAddNewLocationButton() {
        pgLocationObj.clickAddNew();
    }

    @And("user selects state {string} from the state dropdown")
    public void userSelectsState(String stateName) {
        pgLocationObj.selectState(stateName);
    }

    @And("user enters location code {string}")
    public void userEntersLocationCode(String code) {
        pgLocationObj.enterLocationCode(code);
    }

    @And("user checks the Office checkbox")
    public void userChecksOfficeCheckbox() {
        pgLocationObj.checkOfficeCheckbox();
    }

    @And("user enters office name {string}")
    public void userEntersOfficeName(String name) {
        pgLocationObj.enterOfficeName(name);
    }

    @And("user enters office address {string}")
    public void userEntersOfficeAddress(String address) {
        pgLocationObj.enterOfficeAddress(address);
    }

    @And("user enters redline values:")
    public void userEntersRedlineValues(DataTable redlineTable) {
        List<Map<String, String>> rows = redlineTable.asMaps();
        Map<String, String> values = rows.get(0);

        String min = values.getOrDefault("Min", "");
        String standard = values.get("Standard");
        String max = values.getOrDefault("Max", "");

        pgLocationObj.enterRedlineValues(min, standard, max);
    }

    @And("user enters effective date as today")
    public void userEntersEffectiveDateAsToday() {
        pgLocationObj.enterEffectiveDateToday();
    }

    @And("user clicks the Add Location button")
    public void userClicksAddLocationButton() {
        pgLocationObj.clickAddLocationButton();
    }

    @Then("verify location is created successfully")
    public void verifyLocationCreatedSuccessfully() {
        if (!pgLocationObj.verifyLocationCreated()) {
            throw new AssertionError("Location was not created successfully.");
        }
    }

    @Then("verify validation error is shown for {string}")
    public void verifyValidationErrorShown(String fieldLabel) {
        if (!pgLocationObj.verifyValidationError(fieldLabel)) {
            throw new AssertionError("Expected validation error for '" + fieldLabel + "' but none found.");
        }
    }

    @When("user searches for location")
    public void userSearchesForLocation() {
        pgLocationObj.searchLocation();
    }

    @Then("verify location appears in the location list")
    public void verifyLocationAppearsInList() {
        pgLocationObj.verifyLocationInList();
    }
}
