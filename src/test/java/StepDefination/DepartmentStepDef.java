package StepDefination;

import HelperClasses.pgDepartment;
import HelperClasses.pgGeneric;
import io.cucumber.java.en.*;

public class DepartmentStepDef extends pgGeneric {

    pgDepartment pgDeptObj = new pgDepartment();

    @Given("user navigates to Settings and opens the Department tab")
    public void userNavigatesToSettingsAndOpensDepartmentTab() {
        pgDeptObj.navigateToDepartmentTab();
    }

    @When("user clicks the Create New department button")
    public void userClicksCreateNewDepartmentButton() {
        pgDeptObj.clickCreateNew();
    }

    @And("user enters {string} department name {string}")
    public void userEntersDepartmentName(String dept , String name) {
        pgDeptObj.enterDepartmentName(dept, name);
    }

    @And("user clicks the Create department button")
    public void userClicksCreateDepartmentButton() {
        pgDeptObj.clickCreateButton();
    }

    @Then("verify department {string} is created successfully")
    public void verifyDepartmentCreatedSuccessfully(String dept) {
        if (!pgDeptObj.verifyDepartmentCreated(dept)) {
            throw new AssertionError("Department was not created successfully.");
        }
    }

    @When("user searches for department {string}")
    public void userSearchesForDepartment(String searchTerm) {
        pgDeptObj.searchDepartment(searchTerm);
    }

    @Then("verify department {string} appears in the department list")
    public void verifyDepartmentAppearsInList(String deptName) {
        if (!pgDeptObj.verifyDepartmentInList(deptName)) {
            throw new AssertionError("Department '" + deptName + "' not found in the list.");
        }
    }
}
