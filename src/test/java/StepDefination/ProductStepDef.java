package StepDefination;

import HelperClasses.pgGeneric;
import HelperClasses.pgProduct;
import io.cucumber.java.en.*;

public class ProductStepDef extends pgGeneric {

    pgProduct pgProductObj = new pgProduct();

    @Given("user navigates to Settings and opens the Product tab")
    public void userNavigatesToSettingsAndOpensProductTab() {
        pgProductObj.navigateToProductTab();
    }

    @When("user clicks the Add New product button")
    public void userClicksAddNewProductButton() {
        pgProductObj.clickAddNew();
    }

    @And("user enters product name {string}")
    public void userEntersProductName(String name) {
        pgProductObj.enterProductName(name);
    }

    @And("user enters product ID {string}")
    public void userEntersProductId(String productId) {
        pgProductObj.enterProductId(productId);
    }

    @And("user enters product description {string}")
    public void userEntersProductDescription(String description) {
        pgProductObj.enterProductDescription(description);
    }

    @And("user enters product redline {string}")
    public void userEntersProductRedline(String redline) {
        pgProductObj.enterProductRedline(redline);
    }

    @And("user enters product effective date as today")
    public void userEntersProductEffectiveDateAsToday() {
        pgProductObj.enterEffectiveDateToday();
    }

    @And("user clicks the Create product button")
    public void userClicksCreateProductButton() {
        pgProductObj.clickCreateButton();
    }

    @Then("verify product {string} is created successfully")
    public void verifyProductCreatedSuccessfully(String productName) {
        if (!pgProductObj.verifyProductCreated(productName)) {
            throw new AssertionError("Product '" + productName + "' was not created successfully.");
        }
    }

    @When("user searches for product {string}")
    public void userSearchesForProduct(String searchTerm) {
        pgProductObj.searchProduct(searchTerm);
    }

    @Then("verify product {string} appears in the product list")
    public void verifyProductAppearsInList(String productName) {
        if (!pgProductObj.verifyProductInList(productName)) {
            throw new AssertionError("Product '" + productName + "' not found in the list.");
        }
    }
}
