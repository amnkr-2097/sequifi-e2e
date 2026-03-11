package StepDefination;

import HelperClasses.pgAddingSales;
import HelperClasses.pgGeneric;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddingSalesStepDef extends pgGeneric {

    pgAddingSales pgSaleObj = new pgAddingSales();

    @Given("user navigates to the Sales page")
    public void userNavigatesToSalesPage() {
        pgSaleObj.navigateToSalesPage();
    }

    @When("user clicks the Add Sale button")
    public void userClicksAddSaleButton() {
        pgSaleObj.clickAddSale();
    }


    @When("user enter {string} as {string}")
    public void userEntersSaleDetails(String option, String values) {
        pgSaleObj.enterSalesData(option, values);
    }

    @When("user selects the product")
    public void user_selects_the_product() {
        pgSaleObj.selectProduct();
    }

    @When("user selects the Customer state and Location Code")
    public void user_selects_the_customer_state() {
        pgSaleObj.selectLocation();
    }

    @When("user enter the {string}")
    public void userEnterDate(String date) {
        pgSaleObj.enterDate(date);
        pause(2);
    }

    @Then("user select {string}")
    public void useSelectSetterCloser(String value) {
        pgSaleObj.selectSetterCloser(value);
    }

    @Then("user clicks Submit sale button")
    public void userClicksSaveSale() {
        pgSaleObj.clickSubmit();
    }

    @Then("verify sale is created successfully")
    public void verifySalIsCreatedSuccessfully() {
        if (!pgSaleObj.verifySaleCreated()) {
            throw new AssertionError("Sale was not created successfully.");
        }
    }

    @When("user searches for the sale")
    public void userSearchesForSale() {
        pgSaleObj.searchSale();
    }

    @Then("verify sale appears in the sales list")
    public void verifySaleAppearsInTheSalesList() {
        if (!pgSaleObj.verifySaleInList()) {
            throw new AssertionError("Sale is not present in list.");
        }
    }

    @And("user opens the sale")
    public void userOpensTheSale() {
        pgSaleObj.opensTheSale();
    }

    @And("verify commission is calculated correctly")
    public void verifyCommissionIsCalculatedCorrectlyWithRate() {
        pgSaleObj.validateCommission();
    }
}
