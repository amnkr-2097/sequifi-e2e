package HelperClasses;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class pgPosition extends pgGeneric {

    // ── Navigation ──
    private static final By SETTINGS_MENU       = By.xpath("//a[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'settings')] | //span[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'settings')]/ancestor::a");
    private static final By POSITION_TAB         = By.xpath("//*[contains(text(),'Positions')]");

    // ── Add New / Create button ──
    private static final By ADD_NEW_BUTTON = By.id("Create_New_Position_btn");

    // ── Form Fields: Section 1 - Basic Info ──
    private static final By NEW_POSITION_TAB = By.xpath("//*[@id='pr_id_2030_content']");
    private static final By POSITION_NAME_INPUT = By.cssSelector("input[name='position_name'], input[name='name'][placeholder*='Position']");
    private static final By POSITION_DESCRIPTION_INPUT = By.cssSelector("textarea[name='description'], textarea[name='position_description']");

    // ── Form Fields: Section 2 - Pay Type / Compensation ──
    private static final By PAY_FREQUENCY_DROPDOWN = By.xpath("//*[@id='Pay_Frequency']");
    private static final By UPFRONT_PAY_INPUT = By.cssSelector("input[name='upfront_pay'], input[name='upfront_amount'], input[name='commission_rate']");

    // ── Form Fields: Section 3 - Associations (Dropdowns) ──
    private static final By DEPARTMENT_DROPDOWN = By.xpath("//*[@id='Department_Dropdown']");
    private static final By PRODUCT_DROPDOWN = By.xpath("//*[@name='product_id']/ancestor::div[@data-pc-section = 'hiddeninputwrapper']/following-sibling::div[@data-pc-section= 'labelcontainer']");
    private static final By PERMISSION_DROPDOWN = By.xpath("//*[@id='Permission_Group_Dropdown']");
    private static final By MAIN_ROLE_DROPDOWN = By.xpath("//*[@id='Main_Role_Dropdown']");
    private static final By WORKER_TYPE = By.xpath("//*[@id='Worker_Type']");


    private static final By TIER_SCHEMA_DROPDOWN = By.xpath("//*[@name='tier_schema_id']/ancestor::div[@data-pc-name='dropdown']");
    private static final By MILESTONE_SCHEMA_DROPDOWN = By.xpath("//*[@name='milestone_schema_id']/ancestor::div[@data-pc-name='dropdown']");

    // ── Submit ──
    private static final By NEXT_BUTTON = By.xpath("//button[@id ='Submit_btn_For_Manage_Position']");
    private static final By SAVE_BUTTON = By.xpath("//button[contains(text(),'Save')] | //button[contains(text(),'Create')]");

    // ── Search ──
    private static final By SEARCH_INPUT = By.id("position_table_Global_Search");

    // ── Feedback ──
    private static final By SUCCESS_TOAST = By.cssSelector(".toast-success, .Toastify__toast--success, [class*='success']");

    // ── Modal ────────────────────────────────────────────────────────────────
    private static final By MODAL_TITLE = By.xpath("//*[normalize-space(text())='Select Tier Schemas']");
    private static final By CLOSE_BUTTON = By.xpath("//button[./*[local-name()='svg' or @aria-label='Close']] | //*[contains(@class,'modal')]//button[contains(.,'×') or contains(@aria-label,'close')]");
    private static final By CREATE_BUTTON = By.xpath("//*[normalize-space(text())='Create']");

    // ── Commission Tiers ─────────────────────────────────────────────────────
//    private static final By COMMISSION_TOGGLE = By.xpath("//input[@name='tiers_commission_status']");
    private static final By COMMISSION_TOGGLE = By.xpath("(//*[@data-pc-name='inputswitch'])[1]");

    private static final By COMMISSION_SCHEMA_DROPDOWN = By.xpath("(//*[@data-pc-name = 'dropdown']/span[@data-pc-section= 'input'])[8]");
    private static final By COMMISSION_ADVANCE_DROPDOWN= By.xpath("(//*[@data-pc-name = 'dropdown']/span[@data-pc-section= 'input'])[9]");

    // ── Upfront Tiers ────────────────────────────────────────────────────────
//    private static final By UPFRONT_TOGGLE = By.xpath("//input[@name='tiers_upfront_status']");
    private static final By UPFRONT_TOGGLE = By.xpath("(//*[@data-pc-name='inputswitch'])[2]");
    private static final By UPFRONT_SCHEMA_DROPDOWN = By.xpath("(//*[@data-pc-name = 'dropdown']/span[@data-pc-section= 'input'])[10]");
    private static final By UPFRONT_ADVANCE_DROPDOWN = By.xpath("(//*[@data-pc-name = 'dropdown']/span[@data-pc-section= 'input'])[11]");

    // ── Override Tiers ───────────────────────────────────────────────────────
//    private static final By OVERRIDE_TOGGLE = By.xpath("//input[@name='tiers_override_status']");
    private static final By OVERRIDE_TOGGLE = By.xpath("(//*[@data-pc-name='inputswitch'])[3]");
    private static final By OVERRIDE_SCHEMA_DROPDOWN = By.xpath("(//*[@data-pc-name = 'dropdown']/span[@data-pc-section= 'input'])[12]");
    private static final By OVERRIDE_ADVANCE_DROPDOWN = By.xpath("(//*[@data-pc-name = 'dropdown']/span[@data-pc-section= 'input'])[13]");
    // ==================== Navigation ====================

    public void navigateToPositionTab() {
        WebDriverWait wait = getExplicitWait(30);

//        WebElement settingsLink = wait.until(ExpectedConditions.elementToBeClickable(SETTINGS_MENU));
//        settingsLink.click();
//        test.log(Status.INFO, "Clicked Settings menu.");
//        pause(2);

        WebElement positionTab = wait.until(ExpectedConditions.elementToBeClickable(POSITION_TAB));
        positionTab.click();
        test.log(Status.INFO, "Clicked Position tab.");
        pause(2);
    }

    // ==================== Form Actions ====================

    public void clickAddNew() {
        WebDriverWait wait = getExplicitWait(10);
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(ADD_NEW_BUTTON));
        btn.click();
        test.log(Status.PASS, "Clicked 'Add New' position button.");
        pause(1);
    }

    public void enterPositionName(String name) {
        WebDriverWait wait = getExplicitWait(10);
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(POSITION_NAME_INPUT));
        field.clear();
        String strName = name + (int) Math.floor(Math.random() * 10000);
        field.sendKeys(strName);
        pgProjectExpectedVariables.setPosPositionName("Position Name", strName);
        test.log(Status.PASS, "Position Name is Set as: " + strName);
    }

    public void enterPositionDescription(String description) {
        WebDriverWait wait = getExplicitWait(10);
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(POSITION_DESCRIPTION_INPUT));
        field.clear();
        String strDesc = description + (int) Math.floor(Math.random() * 100);
        field.sendKeys(strDesc);
        pgProjectExpectedVariables.setPosDescription("Position Description", strDesc);
        test.log(Status.PASS, "Position Description is Set as: " + strDesc);
    }

    public void selectPayFrequency(String value) {
        if (value.equalsIgnoreCase("NA") || value.equalsIgnoreCase("N/A")) {
            pgProjectExpectedVariables.setPosPayType("Pay Type", value);
            test.log(Status.PASS, "Pay Type is N/A");
        } else {
            selectByVisibleText(PAY_FREQUENCY_DROPDOWN, value);
            pgProjectExpectedVariables.setPosPayType("Pay Type", value);
            test.log(Status.PASS, "Pay Type is Set as: " + value);
        }
    }

    public void enterUpfrontPay(String amount) {
        WebDriverWait wait = getExplicitWait(10);
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(UPFRONT_PAY_INPUT));
        field.clear();
        field.sendKeys(amount);
        pgProjectExpectedVariables.setPosUpfrontPay("Upfront Pay", amount);
        test.log(Status.PASS, "Upfront Pay is Set as: " + amount);
    }

    public void selectDepartment() {
        selectByVisibleText(DEPARTMENT_DROPDOWN, pgProjectExpectedVariables.getDeptParentDepartment("Parent Department Name"));
//        selectByPartialText(DEPARTMENT_DROPDOWN, "HR");
        test.log(Status.PASS, "Product is Set as: " + pgProjectExpectedVariables.getDeptParentDepartment("Parent Department Name"));
    }

    public void selectProduct() {
        selectByVisibleText(PRODUCT_DROPDOWN, pgProjectExpectedVariables.getProdName("Product Name"));
//        selectByVisibleText(PRODUCT_DROPDOWN, "Test");
        click(PRODUCT_DROPDOWN);
        test.log(Status.PASS, "Product is Set as: " + pgProjectExpectedVariables.getProdName("Product Name"));
    }

    public void selectTierSchema(String value) {
        if (value.equalsIgnoreCase("NA") || value.equalsIgnoreCase("N/A")) {
            test.log(Status.PASS, "Tier Schema is N/A");
        } else {
            selectByVisibleText(TIER_SCHEMA_DROPDOWN, value);
            test.log(Status.PASS, "Tier Schema is Set as: " + value);
        }
    }

    public void selectMilestoneSchema(String value) {
        if (value.equalsIgnoreCase("NA") || value.equalsIgnoreCase("N/A")) {
            test.log(Status.PASS, "Milestone Schema is N/A");
        } else {
            selectByVisibleText(MILESTONE_SCHEMA_DROPDOWN, value);
            test.log(Status.PASS, "Milestone Schema is Set as: " + value);
        }
    }

    public void enterEffectiveDateToday() {
        pause(1);
        selectTodayByLabel("Effective");
        pgProjectExpectedVariables.setPosEffectiveDate("Position Eff Date", today());
        test.log(Status.PASS, "Position Effective Date set as: " + today());
    }

    // ==================== Submit ====================

    public void clickSaveButton() {
        WebDriverWait wait = getExplicitWait(10);
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(SAVE_BUTTON));
        btn.click();
        test.log(Status.INFO, "Clicked Save/Create position button.");
        pause(1);
    }

    public void clickNextButton() {
        WebDriverWait wait = getExplicitWait(10);
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(NEXT_BUTTON));
        btn.click();
        test.log(Status.INFO, "Clicked Next position button.");
        pause(1);
    }

    // ==================== Verification ====================

    public boolean verifyPositionCreated() {
        WebDriverWait wait = getExplicitWait(10);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(SUCCESS_TOAST));
            test.log(Status.PASS, "Position '" +
                pgProjectExpectedVariables.getPosPositionName("Position Name") +
                "' created successfully.");
            return true;
        } catch (Exception e) {
            try {
                By posInList = By.xpath("//*[contains(text(),'" +
                    pgProjectExpectedVariables.getPosPositionName("Position Name") + "')]");
                wait.until(ExpectedConditions.visibilityOfElementLocated(posInList));
                test.log(Status.PASS, "Position '" +
                    pgProjectExpectedVariables.getPosPositionName("Position Name") +
                    "' found in list.");
                return true;
            } catch (Exception ex) {
                test.log(Status.FAIL, "Position '" +
                    pgProjectExpectedVariables.getPosPositionName("Position Name") +
                    "' not found after creation.");
                return false;
            }
        }
    }

    // ==================== Search ====================

    public void searchPosition() {
        WebDriverWait wait = getExplicitWait(10);
        WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_INPUT));
        searchField.clear();
        searchField.sendKeys(pgProjectExpectedVariables.getPosPositionName("Position Name"));
        test.log(Status.PASS, "Searched for position: " +
            pgProjectExpectedVariables.getPosPositionName("Position Name"));
        pause(3);
    }

    public boolean verifyPositionInList() {
        WebDriverWait wait = getExplicitWait(10);
        try {
            By posRow = By.xpath(
                "//tr[contains(.,'" + pgProjectExpectedVariables.getPosPositionName("Position Name") +
                "')] | //*[contains(@class,'row') and contains(.,'" +
                pgProjectExpectedVariables.getPosPositionName("Position Name") +
                "')] | //*[contains(@class,'card') and contains(.,'" +
                pgProjectExpectedVariables.getPosPositionName("Position Name") + "')]"
            );
            wait.until(ExpectedConditions.visibilityOfElementLocated(posRow));
            test.log(Status.PASS, "Position '" +
                pgProjectExpectedVariables.getPosPositionName("Position Name") +
                "' found in list.");
            return true;
        } catch (Exception e) {
            test.log(Status.FAIL, "Position '" +
                pgProjectExpectedVariables.getPosPositionName("Position Name") +
                "' not found in list.");
            return false;
        }
    }



// ── Actions ───────────────────────────────────────────────────────────────

    public boolean isModalVisible() {
        WebDriverWait wait = getExplicitWait(10);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(MODAL_TITLE));
            test.log(Status.INFO, "Select Tier Schemas modal is visible.");
            return true;
        } catch (Exception e) {
            test.log(Status.FAIL, "Select Tier Schemas modal is NOT visible.");
            return false;
        }
    }

    public void closeModal() {
        WebDriverWait wait = getExplicitWait(10);
        wait.until(ExpectedConditions.elementToBeClickable(CLOSE_BUTTON)).click();
        test.log(Status.INFO, "Closed the Select Tier Schemas modal.");
        pause(1);
    }



    public void enableCommissionTiers(boolean enable) {
        setToggle(COMMISSION_TOGGLE, enable, "Commission");
    }
    public void enableUpfrontTiers(boolean enable) {
        setToggle(UPFRONT_TOGGLE, enable, "Upfront");
    }
    public void enableOverrideTiers(boolean enable) {
        setToggle(OVERRIDE_TOGGLE, enable, "Override");
    }

// ── Commission Tiers ──────────────────────────────────────────────────────

    public void selectCommissionTierSchema() {
//        selectByVisibleText(COMMISSION_SCHEMA_DROPDOWN, pgProjectExpectedVariables.getTierSchemaName("Tier Schema Name"));
        selectByVisibleText(COMMISSION_SCHEMA_DROPDOWN, "TR-CA-SF");
        test.log(Status.PASS, "Commission Tier Schema selected: " + pgProjectExpectedVariables.getTierSchemaName("Tier Schema Name"));
    }

    public void selectCommissionTierAdvancement(String value) {
        selectByVisibleText(COMMISSION_ADVANCE_DROPDOWN, value);
        test.log(Status.PASS, "Commission Tier Advancement selected: " + value);
    }

// ── Upfront Tiers ─────────────────────────────────────────────────────────

    public void selectUpfrontTierSchema() {
//        selectByVisibleText(UPFRONT_SCHEMA_DROPDOWN, pgProjectExpectedVariables.getTierSchemaName("Tier Schema Name"));
        selectByVisibleText(UPFRONT_SCHEMA_DROPDOWN, "TR-CA-SF");
        test.log(Status.PASS, "Upfront Tier Schema selected: " + pgProjectExpectedVariables.getTierSchemaName("Tier Schema Name"));
    }

    public void selectUpfrontTierAdvancement(String value) {
        selectByVisibleText(UPFRONT_ADVANCE_DROPDOWN, value);
        test.log(Status.PASS, "Upfront Tier Advancement selected: " + value);
    }

// ── Override Tiers ────────────────────────────────────────────────────────

    public void selectOverrideTierSchema() {
//        selectByVisibleText(OVERRIDE_SCHEMA_DROPDOWN, pgProjectExpectedVariables.getTierSchemaName("Tier Schema Name"));
        selectByVisibleText(OVERRIDE_SCHEMA_DROPDOWN, "TR-CA-SF");
        test.log(Status.PASS, "Override Tier Schema selected: " + pgProjectExpectedVariables.getTierSchemaName("Tier Schema Name"));
    }

    public void selectOverrideTierAdvancement(String value) {
        selectByVisibleText(OVERRIDE_ADVANCE_DROPDOWN, value);
        test.log(Status.PASS, "Override Tier Advancement selected: " + value);
    }

// ── Submit ────────────────────────────────────────────────────────────────

    public void clickCreate() {
        WebDriverWait wait = getExplicitWait(10);
        wait.until(ExpectedConditions.elementToBeClickable(CREATE_BUTTON)).click();
        test.log(Status.PASS, "Clicked 'Create' button on Select Tier Schemas modal.");
        pause(2);
    }

    public void selectWorkerType(String type) {
        WebDriverWait wait = getExplicitWait(10);
        wait.until(ExpectedConditions.elementToBeClickable(WORKER_TYPE));
        selectByVisibleText(WORKER_TYPE , type);
        test.log(Status.PASS, "Worker Type for position is set as: " + type);
        pause(2);
    }

    public void selectMainRole(String role) {
        WebDriverWait wait = getExplicitWait(10);
        wait.until(ExpectedConditions.elementToBeClickable(MAIN_ROLE_DROPDOWN));
        selectByVisibleText(MAIN_ROLE_DROPDOWN , role);
        test.log(Status.PASS, "Main Role for position is set as: " + role);
        pause(2);
    }

    public void selectPermissionGroup(String permission) {
        WebDriverWait wait = getExplicitWait(10);
        wait.until(ExpectedConditions.elementToBeClickable(PERMISSION_DROPDOWN));
        selectByVisibleText(PERMISSION_DROPDOWN , permission);
        test.log(Status.PASS, "Permission Group for position is set as: " + permission);
        pause(2);
    }

}
