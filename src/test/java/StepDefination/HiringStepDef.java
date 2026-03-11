package StepDefination;

import HelperClasses.pgGeneric;
import HelperClasses.pgHiring;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HiringStepDef extends pgGeneric {

    pgHiring pgHiringObj = new pgHiring();

    // ── Navigation ──

    @Given("user navigates to Hiring and opens Onboarding Employees tab")
    public void userNavigatesToHiringAndOpensOnboardingEmployeesTab() {
        refreshPage();
        pgHiringObj.navigateToOnboardingEmployees();
        pause(3);
        refreshPageShortcut();
        pause(1);
    }

    @When("user clicks the Hire New button")
    public void userClicksHireNewButton() {
        pgHiringObj.clickHireNew();
    }

    // ── Tab 1: Details ──

    @And("user enters first name {string}")
    public void userEntersFirstName(String firstName) {
        pgHiringObj.enterFirstName(firstName);
    }

    @And("user enters last name {string}")
    public void userEntersLastName(String lastName) {
        pgHiringObj.enterLastName(lastName);
    }

    @And("user enters personal email {string}")
    public void userEntersPersonalEmail(String emailPrefix) {
        pgHiringObj.enterPersonalEmail(emailPrefix);
    }

    @And("user enters phone number")
    public void userEntersPhoneNumber() {
        pgHiringObj.enterPhoneNumber();
    }

    @And("user selects office state {string}")
    public void userSelectsOfficeState(String state) {
        pgHiringObj.selectState(state);
    }

    @And("user selects office name {string}")
    public void userSelectsOfficeName(String offName) {
        pgHiringObj.selectOffice(offName);
    }

    @And("user clicks Save and Continue on Details tab")
    public void userClicksSaveAndContinueOnDetailsTab() {
        pgHiringObj.clickSaveAndContinue();
    }

    // ── Tab 2: Organization ──

    @And("user selects department")
    public void userSelectsDepartment() {
        pgHiringObj.selectDepartment();
    }

    @And("user selects position")
    public void userSelectsPosition() {
        pgHiringObj.selectPosition();
    }

    @And("user selects manager first available")
    public void userSelectsManagerFirstAvailable() {
        pgHiringObj.selectManager();
    }

    @And("user {string} manager checkbox")
    public void selectCheckboxManager(String check) {
        pause(2);
        pgHiringObj.checkManager(check);
        pause(2);
    }

    @And("user clicks Save and Continue on Organization tab")
    public void userClicksSaveAndContinueOnOrganizationTab() {
        pgHiringObj.clickSaveAndContinue();
    }

    // ── Tab 3: Redline ──

    @And("user fills redline for {string} with value {string} and type {string}")
    public void userFillsRedlineForAllRoles(String role, String value, String type) {
        pgHiringObj.fillRedlineForAllRoles(role, value, type);
    }

    @And("user clicks Save and Continue on Redline tab")
    public void userClicksSaveAndContinueOnRedlineTab() {
        pgHiringObj.clickSaveAndContinue();
    }

    @And("user clicks Save and Continue on Commission tab")
    public void userClicksSaveAndContinueOnCommissionTab() {
        pgHiringObj.clickSaveAndContinue();
    }

    // ── Tab 4: Commission ──

    @And("user clicks Next on Commission tab")
    public void userClicksNextOnCommissionTab() {
        pgHiringObj.clickNext();
    }

    // ── Tab 5: Upfront ──

    @And("user clicks Next on Upfront tab")
    public void userClicksNextOnUpfrontTab() {
        pgHiringObj.clickNext();
    }

    // ── Tab 6: Overrides ──

    @And("user clicks Next on Overrides tab")
    public void userClicksNextOnOverridesTab() {
        pgHiringObj.clickNext();
    }

    // ── Tab 7: Agreement ──

    @And("user fills agreement details")
    public void userFillsAgreementDetails() {
        pgHiringObj.fillAgreementDetails();
    }

    @And("user clicks Save and Continue on Agreement tab")
    public void userClicksSaveAndContinueOnAgreementTab() {
        pgHiringObj.clickSaveAndContinue();
    }

    // ── Tab 8: Additional Information ──

    @And("user clicks Save and Continue on Additional Information tab")
    public void userClicksSaveAndContinueOnAdditionalInfoTab() {
        pgHiringObj.clickSaveAndContinue();
    }

    // ── Tab 9: Review & Finish ──

    @And("user complete Review and Finish for hiring")
    public void userClicksFinishToCompleteHiring() {
        pgHiringObj.finishReviewTask();
    }

    // ── Verification ──

    @Then("verify rep is hired successfully")
    public void verifyRepIsHiredSuccessfully() {
        if (!pgHiringObj.verifyRepHired()) {
            throw new AssertionError("Rep was not hired successfully.");
        }
    }

    @When("user searches for hired rep")
    public void userSearchesForHiredRep() {
        pgHiringObj.searchHiredRep();
    }

    @Then("verify hired rep appears in the onboarding list")
    public void verifyHiredRepAppearsInOnboardingList() {
        if (!pgHiringObj.verifyRepInOnboardingList()) {
            throw new AssertionError("Hired rep not found in the onboarding list.");
        }
    }
}
