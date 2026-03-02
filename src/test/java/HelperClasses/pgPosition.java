package HelperClasses;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class pgPosition extends pgGeneric {

    // ── Navigation ──
    private static final By POSITION_TAB = By.xpath(
        "//*[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'position')]"
    );

    // ── Add New / Create button ──
    private static final By ADD_NEW_BUTTON = By.id("Create_New_Position_btn");

    // ── Form Fields: Section 1 - Basic Info ──
    private static final By POSITION_NAME_INPUT = By.cssSelector(
        "input[name='position_name'], input[name='name'][placeholder*='Position']"
    );
    private static final By POSITION_DESCRIPTION_INPUT = By.cssSelector(
        "textarea[name='description'], textarea[name='position_description']"
    );

    // ── Form Fields: Section 2 - Pay Type / Compensation ──
    private static final By PAY_TYPE_DROPDOWN = By.xpath(
        "//*[@name='pay_type']/ancestor::div[@data-pc-name='dropdown']"
    );
    private static final By UPFRONT_PAY_INPUT = By.cssSelector(
        "input[name='upfront_pay'], input[name='upfront_amount'], input[name='commission_rate']"
    );

    // ── Form Fields: Section 3 - Associations (Dropdowns) ──
    private static final By DEPARTMENT_DROPDOWN = By.xpath(
        "//*[@name='department_id']/ancestor::div[@data-pc-name='dropdown']"
    );
    private static final By PRODUCT_DROPDOWN = By.xpath(
        "//*[@name='product_id']/ancestor::div[@data-pc-name='dropdown']"
    );
    private static final By TIER_SCHEMA_DROPDOWN = By.xpath(
        "//*[@name='tier_schema_id']/ancestor::div[@data-pc-name='dropdown']"
    );
    private static final By MILESTONE_SCHEMA_DROPDOWN = By.xpath(
        "//*[@name='milestone_schema_id']/ancestor::div[@data-pc-name='dropdown']"
    );

    // ── Submit ──
    private static final By SAVE_BUTTON = By.xpath(
        "//button[contains(text(),'Save')] | //button[contains(text(),'Create')]"
    );

    // ── Search ──
    private static final By SEARCH_INPUT = By.id("position_table_Global_Search");

    // ── Feedback ──
    private static final By SUCCESS_TOAST = By.cssSelector(
        ".toast-success, .Toastify__toast--success, [class*='success']"
    );


    // ==================== Navigation ====================

    public void navigateToPositionTab() {
        WebDriverWait wait = getExplicitWait(15);

        By settingsMenu = By.xpath(
            "//a[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'settings')] | " +
            "//span[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'settings')]/ancestor::a"
        );
        WebElement settingsLink = wait.until(ExpectedConditions.elementToBeClickable(settingsMenu));
        settingsLink.click();
        test.log(Status.INFO, "Clicked Settings menu.");
        pause(2);

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
        String strName = name + (int) Math.floor(Math.random() * 100);
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

    public void selectPayType(String value) {
        if (value.equalsIgnoreCase("NA") || value.equalsIgnoreCase("N/A")) {
            pgProjectExpectedVariables.setPosPayType("Pay Type", value);
            test.log(Status.PASS, "Pay Type is N/A");
        } else {
            selectByVisibleText(PAY_TYPE_DROPDOWN, value);
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

    public void selectDepartment(String value) {
        if (value.equalsIgnoreCase("NA") || value.equalsIgnoreCase("N/A")) {
            pgProjectExpectedVariables.setPosDepartment("Department", value);
            test.log(Status.PASS, "Department is N/A");
        } else {
            selectByVisibleText(DEPARTMENT_DROPDOWN, value);
            pgProjectExpectedVariables.setPosDepartment("Department", value);
            test.log(Status.PASS, "Department is Set as: " + value);
        }
    }

    public void selectProduct(String value) {
        if (value.equalsIgnoreCase("NA") || value.equalsIgnoreCase("N/A")) {
            pgProjectExpectedVariables.setPosProduct("Product", value);
            test.log(Status.PASS, "Product is N/A");
        } else {
            selectByVisibleText(PRODUCT_DROPDOWN, value);
            pgProjectExpectedVariables.setPosProduct("Product", value);
            test.log(Status.PASS, "Product is Set as: " + value);
        }
    }

    public void selectTierSchema(String value) {
        if (value.equalsIgnoreCase("NA") || value.equalsIgnoreCase("N/A")) {
            pgProjectExpectedVariables.setPosTierSchema("Tier Schema", value);
            test.log(Status.PASS, "Tier Schema is N/A");
        } else {
            selectByVisibleText(TIER_SCHEMA_DROPDOWN, value);
            pgProjectExpectedVariables.setPosTierSchema("Tier Schema", value);
            test.log(Status.PASS, "Tier Schema is Set as: " + value);
        }
    }

    public void selectMilestoneSchema(String value) {
        if (value.equalsIgnoreCase("NA") || value.equalsIgnoreCase("N/A")) {
            pgProjectExpectedVariables.setPosMilestoneSchema("Milestone Schema", value);
            test.log(Status.PASS, "Milestone Schema is N/A");
        } else {
            selectByVisibleText(MILESTONE_SCHEMA_DROPDOWN, value);
            pgProjectExpectedVariables.setPosMilestoneSchema("Milestone Schema", value);
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
        pause(2);
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
}
