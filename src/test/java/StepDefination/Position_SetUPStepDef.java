package StepDefination;

import HelperClasses.pgGeneric;
import HelperClasses.pgPosition_Setup;
import io.cucumber.java.en.*;

public class Position_SetUPStepDef extends pgGeneric {

    pgPosition_Setup pgPosSetup = new pgPosition_Setup();

    // ── Open Modal ───────────────────────────────────────────────────────────
    @When("user opens Position Setup for {string}")
    public void userOpensPositionSetup(String posName) {
        pgPosSetup.openPositionSetup(posName);
    }

    // ── Tab Navigation ───────────────────────────────────────────────────────
    @When("user clicks the Wages tab in position setup")
    public void userClicksWagesTab()      {
        pgPosSetup.clickWagesTab();
    }

    @When("user clicks the Commission tab in position setup")
    public void userClicksCommissionTab() {
        pgPosSetup.clickCommissionTab();
    }

    @When("user clicks the Upfront tab in position setup")
    public void userClicksUpfrontTab()    {
        pgPosSetup.clickUpfrontTab();
    }

    @When("user clicks the Override tab in position setup")
    public void userClicksOverrideTab()   {
        pgPosSetup.clickOverrideTab();
    }

    @When("user clicks the Default tab in position setup")
    public void userClicksDefaultTab()    {
        pgPosSetup.clickDefaultTab();
    }

    // ── Wages Tab ────────────────────────────────────────────────────────────
    @And("user {string} the Wages toggle in position setup")
    public void userSetsWagesToggle(String state) {
        pause(2);
        pgPosSetup.enableWages(state.equalsIgnoreCase("enables"));
    }

    @And("user selects wages type {string} in position setup")
    public void userSelectsWagesType(String val)       {
        pgPosSetup.selectWagesType(val);
    }

    @And("user enters wages amount {string} in position setup")
    public void userEntersWagesAmount(String amount)   {
        pgPosSetup.enterWagesAmount(amount);
    }

    // ── Commission Tab — Default Product ─────────────────────────────────────
    @And("user {string} the Commission toggle in position setup")
    public void userSetsCommissionToggle(String state) {
        pgPosSetup.enableCommission(state.equalsIgnoreCase("enables")); }

    @And("user selects commission default type {string} for {string}")
    public void userSelectsCommDefaultType(String val, String option)     {
        pause(2);
        pgPosSetup.selectCommissionDefaultType(val, option);
    }

    @And("user enters commission default amount {string} for {string}")
    public void userEntersCommDefaultAmount(String amount, String option) {
        pause(2);
        pgPosSetup.enterCommissionDefaultAmount(amount,option);
    }

    @And("user selects commission default tier schema {string}")
    public void userSelectsCommDefaultTier(String val)     {
        pgPosSetup.selectCommissionDefaultTierSchema(val);
    }

    // ── Commission Tab — Products Left Panel + Per-Product Fields ────────────

    @And("user clicks product {string} in the products panel")
    public void userClicksProductInPanel(String name)          {
        pgPosSetup.clickProductInPanel(name);
    }

    @And("user enters commission percent {string}")
    public void userEntersCommissionPercent(String val)        {
        pgPosSetup.enterCommissionPercent(val);
    }

    @And("user selects calculated type {string}")
    public void userSelectsCalculatedType(String val)          {
        pgPosSetup.selectCalculatedType(val);
    }

    @And("user {string} unlocked for hiring on commission")
    public void userSetsUnlockHiringCommission(String state)   {
        pgPosSetup.checkUnlockedForHiringCommission(state.equalsIgnoreCase("checks"));
    }

    @And("user {string} unlocked for hiring on calculated")
    public void userSetsUnlockHiringCalculated(String state)   {
        pgPosSetup.checkUnlockedForHiringCalculated(state.equalsIgnoreCase("checks"));
    }

    @And("user enters commission limit {string}")
    public void userEntersCommissionLimit(String val)          {
        pgPosSetup.enterCommissionLimit(val);
    }

    @And("user selects commission limit type {string}")
    public void userSelectsCommissionLimitType(String val)     {
        pgPosSetup.selectLimitType(val);
    }

    // ── Navigation Buttons ───────────────────────────────────────────────────

    @And("user clicks Back button in position setup")
    public void userClicksBack()                               {
        pgPosSetup.clickBack();
    }

    @And("user clicks Next button in position setup")
    public void userClicksNext()                               {
        pgPosSetup.clickOnNextSetUp();
    }

    @And("user clicks Submit button in position setup")
    public void userClicksSubmit()                             {
        pgPosSetup.clickSubmit();
    }

    // ── Save & Close ─────────────────────────────────────────────────────────
    @And("user clicks Save and Continue in position setup")
    public void userClicksSaveAndContinue()  {
        pgPosSetup.clickSaveAndContinue();
    }

    @And("user closes the Position Setup modal")
    public void userClosesPositionSetup()    {
        pgPosSetup.closePositionSetupModal();
    }

    @Then("verify position setup is saved successfully")
    public void verifyPositionSetupSaved() {
        if (!pgPosSetup.verifySetupSavedSuccessfully()) {
            throw new AssertionError("Position Setup was not saved successfully.");
        }
    }

    /**
     * SELF MADE
     * @param str
     */

    @And("user select unused pto as {string}")
    public void userSelectUnusedPtoAs(String str) {
        pgPosSetup.selectUnusedPTOExpires(str);
    }

    @And("user enters pto hours as {string}")
    public void userEntersPtoHoursAs(String hrs) {
        pgPosSetup.enterPTOHours(hrs);
    }

    @And("user search for position {string}")
    public void userSearchForPosition(String str) {
        pgPosSetup.searchPosition(str);
    }

    @And("user enters next button for new position")
    public void userClickOnNextNewPosition() {
        pause(2);
        pgPosSetup.clickNextNewPosition();
    }

    @And("user clicks on Next in position setup")
    public void userClickOnNext() {
        pause(2);
        pgPosSetup.clickOnNextSetUp();
    }
}
