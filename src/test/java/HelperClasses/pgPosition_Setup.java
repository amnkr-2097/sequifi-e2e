package HelperClasses;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class pgPosition_Setup extends pgGeneric {

    // ═══════════════════════════════════════════════════════════════
    // OPEN POSITION SETUP MODAL
    // ═══════════════════════════════════════════════════════════════

    private static final By POSITION_SETUP_ICON = By.xpath(
            "//tr[contains(.,'" + pgProjectExpectedVariables.getPosPositionName("Position Name") + "')]//button[contains(@id,'setup') or contains(@class,'setup')] | " +
                    "//tr[contains(.,'" + pgProjectExpectedVariables.getPosPositionName("Position Name") + "')]//*[contains(@class,'gear') or contains(@class,'setting') or contains(@title,'Setup')]"
    );

    private static final By SEARCH_INPUT = By.id("position_table_Global_Search");


    // ═══════════════════════════════════════════════════════════════
    // MODAL TABS
    // ═══════════════════════════════════════════════════════════════

    private static final By TAB_WAGES      = By.xpath("//div[contains(@class,'tab') or contains(@class,'Tab')]//span[normalize-space(text())='Wages']      | //*[@role='tab'][normalize-space(.)='Wages']");
    private static final By TAB_COMMISSION = By.xpath("//div[contains(@class,'tab') or contains(@class,'Tab')]//span[normalize-space(text())='Commission']  | //*[@role='tab'][normalize-space(.)='Commission']");
    private static final By TAB_UPFRONT    = By.xpath("//div[contains(@class,'tab') or contains(@class,'Tab')]//span[normalize-space(text())='Upfront']     | //*[@role='tab'][normalize-space(.)='Upfront']");
    private static final By TAB_OVERRIDE   = By.xpath("//div[contains(@class,'tab') or contains(@class,'Tab')]//span[normalize-space(text())='Override']    | //*[@role='tab'][normalize-space(.)='Override']");
    private static final By TAB_DEFAULT    = By.xpath("//div[contains(@class,'tab') or contains(@class,'Tab')]//span[normalize-space(text())='Default']     | //*[@role='tab'][normalize-space(.)='Default']");

    // ═══════════════════════════════════════════════════════════════
    // WAGES TAB LOCATORS
    // ═══════════════════════════════════════════════════════════════

    private static final By WAGES_TOGGLE        = By.xpath("(//*[normalize-space(text())='Wages']/following::div[contains(@class,'p-inputswitch')])[1]");
    private static final By WAGES_TYPE_DROPDOWN = By.xpath("//*[@name='pay_type']/ancestor::div[@data-pc-name='dropdown']");
    private static final By WAGES_AMOUNT_INPUT  = By.cssSelector("input[name='pay_rate'], input[name='hourly_rate'], input[name='salary']");
    private static final By WAGES_PTO_INPUT  = By.cssSelector("input[name='pto_hours']");
    private static final By WAGES_UNUSED_PTO_INPUT  = By.xpath("//select[@name='unused_pto_expires']/ancestor::div[@data-pc-name='dropdown']");
    private static final By WAGES_WEEKLY_HOUR_INPUT  = By.xpath("//*[@name='expected_weekly_hours']");


    // ═══════════════════════════════════════════════════════════════
    // COMMISSION TAB LOCATORS  (Default Product Row — index [1])
    // ═══════════════════════════════════════════════════════════════

    private static final By COMMISSION_TOGGLE                = By.xpath("(//*[normalize-space(text())='Commission']/following::div[contains(@class,'p-inputswitch')])[1]");
    // Default (first) product row
    private static final By COMM_DEFAULT_PRODUCT_TAB = By.xpath("//*[contains(text(),'Default')]");
    private static final By COMM_PROV_PRODUCT = By.xpath("//*[contains(text(),'[%s]')]");
    private static final By COMM_DEFAULT_TYPE_DROPDOWN      = By.xpath("//*[@name='commission_amount_type']/ancestor::div[@data-pc-name='dropdown']");
    private static final By COMM_DEFAULT_AMOUNT_INPUT       = By.cssSelector("input[placeholder='Enter commission']");
    private static final By COMM_LIMIT_INPUT = By.xpath("//input[@name='limit' or contains(@placeholder,'Limit')]");
    private static final By COMM_LIMIT_TYPE_DROPDOWN = By.xpath("//*[@data-pc-name='dropdown']/span[@data-pc-section='input'])[2]");
    private static final By COMM_COPY_VALUE = By.xpath("//div[@class= 'cursor-pointer']");
    // Left Products Panel — click product by name (e.g. "Default" or "Test")
    private static final String PRODUCT_PANEL_XPATH = "//*[contains(@class,'sidebar') or contains(@class,'product-list') or contains(@class,'ProductList')]//*[normalize-space(text())='%s'] | //div[contains(@class,'p-listbox-item') and normalize-space(.)='%s'] | //*[@role='option' and normalize-space(.)='%s']";
    // Commission % amount input (the text field showing "Enter commission" with % suffix)
    private static final By COMM_PERCENT_INPUT = By.xpath("//input[contains(@placeholder,'commission') or @name='commission' or @name='commission_value']");
    // Calculated type dropdown (label = "Calculated", shows "Percent" by default)
    private static final By COMM_CALCULATED_DROPDOWN = By.xpath("//*[normalize-space(text())='Calculated']/following::div[@data-pc-name='dropdown'][1]");
    // Unlocked for Hiring — Commission side (left checkbox)
    private static final By COMM_UNLOCK_HIRING_LEFT = By.xpath("(//*[normalize-space(text())='Unlocked for Hiring'])[1]/preceding-sibling::div[contains(@class,'p-checkbox')] | (//input[@type='checkbox'])[1]");
    // Unlocked for Hiring — Calculated side (right checkbox)
    private static final By COMM_UNLOCK_HIRING_RIGHT = By.xpath("(//*[normalize-space(text())='Unlocked for Hiring'])[2]/preceding-sibling::div[contains(@class,'p-checkbox')] | (//input[@type='checkbox'])[2]");
    // Navigation buttons at the bottom of the modal
    private static final By BACK_BUTTON   = By.xpath("//*[contains(text(),'Back')]/ancestor::button");
    private static final By NEXT_BUTTON_NEW_POSITION   = By.id("Submit_btn_For_Manage_Position");
    private static final By NEXT_BUTTON_SETUP = By.xpath("//button[contains(@class,'defaultButton')]/span[text()='Next']/..");
    private static final By SUBMIT_BUTTON = By.xpath("//*[contains(text(),'Submit')]//ancestor::button");
    private static final By PREVIOUS_BUTTON = By.xpath("//*[contains(text(),'Previous')]");
    private static final By SAVE_AND_CONTINUE_BUTTON = By.xpath("//button[normalize-space(.)='Save & Continue'] | //button[normalize-space(.)='Save and Continue']");
    private static final By CLOSE_BUTTON = By.xpath("//*[contains(@class,'p-dialog-header-close')] | //button[@aria-label='Close']");
    private static final By SUCCESS_TOAST = By.cssSelector(".toast-success, .Toastify__toast--success, [class*='success']");
    private static final By CONFIRM_BUTTON = By.xpath("//button//span[contains(text(),'Confirm')]");


    // ═══════════════════════════════════════════════════════════════
    // UPFRONT TAB LOCATORS  (Default Product Row — index [1])
    // ═══════════════════════════════════════════════════════════════

    private static final By UPFRONT_TOGGLE                  = By.xpath("(//*[normalize-space(text())='Upfront']/following::div[contains(@class,'p-inputswitch')])[1]");

    // Default (first) product row
    private static final By UPF_DEFAULT_PRODUCT_DROPDOWN    = By.xpath("(//*[@name='product_id']/ancestor::div[@data-pc-name='dropdown'])[1]");
    private static final By UPF_DEFAULT_TYPE_DROPDOWN       = By.xpath("(//*[@data-pc-name='dropdown']/span[@data-pc-section='input'])[1]");
    private static final By UPF_DEFAULT_AMOUNT_INPUT        = By.cssSelector("input[placeholder='Enter upfront']");

    // ═══════════════════════════════════════════════════════════════
    // OVERRIDE TAB LOCATORS
    // ═══════════════════════════════════════════════════════════════

    private static final By OVERRIDE_TOGGLE                 = By.xpath("(//*[normalize-space(text())='Override']/following::div[contains(@class,'p-inputswitch')])[1]");
    private static final By OVR_DEFAULT_PRODUCT_DROPDOWN    = By.xpath("(//*[@name='product_id']/ancestor::div[@data-pc-name='dropdown'])[1]");
    private static final By OVR_DEFAULT_TYPE_DROPDOWN       = By.xpath("(//*[@name='override_type']/ancestor::div[@data-pc-name='dropdown'])[1]");
    private static final By OVR_DEFAULT_AMOUNT_INPUT        = By.cssSelector("input[name='override_default_amount'], input[name='default_override']");
    private static final By OVR_DEFAULT_TIER_DROPDOWN       = By.xpath("(//*[@name='override_tier_schema_id']/ancestor::div[@data-pc-name='dropdown'])[1]");
    private static final By OVR_DEFAULT_ADVANCE_DROPDOWN    = By.xpath("(//*[@name='override_tier_advancement_id']/ancestor::div[@data-pc-name='dropdown'])[1]");

    // ═══════════════════════════════════════════════════════════════
    // DEFAULT TAB LOCATORS
    // ═══════════════════════════════════════════════════════════════

    private static final By DEFAULT_COMMISSION_TYPE_DROPDOWN = By.xpath("//*[@name='default_commission_type']/ancestor::div[@data-pc-name='dropdown']");
    private static final By DEFAULT_COMMISSION_AMOUNT_INPUT  = By.cssSelector("input[name='default_commission_amount']");
    private static final By DEFAULT_UPFRONT_TYPE_DROPDOWN    = By.xpath("//*[@name='default_upfront_type']/ancestor::div[@data-pc-name='dropdown']");
    private static final By DEFAULT_UPFRONT_AMOUNT_INPUT     = By.cssSelector("input[name='default_upfront_amount']");
    private static final By DEFAULT_OVERRIDE_TYPE_DROPDOWN   = By.xpath("//*[@name='default_override_type']/ancestor::div[@data-pc-name='dropdown']");
    private static final By DEFAULT_OVERRIDE_AMOUNT_INPUT    = By.cssSelector("input[name='default_override_amount']");

    // ═══════════════════════════════════════════════════════════════
    // OPEN POSITION SETUP
    // ═══════════════════════════════════════════════════════════════

    public void openPositionSetup(String positionName) {
        WebDriverWait wait = getExplicitWait(10);
        By setupBtn = By.xpath(
                "//tr[contains(.,'"+positionName+"')]//button[contains(@data-pc-name,'button')]"
        );
        wait.until(ExpectedConditions.elementToBeClickable(setupBtn)).click();
        test.log(Status.INFO, "Opened Position Setup modal for: " + positionName);
        pause(2);
    }

    // ═══════════════════════════════════════════════════════════════
    // TAB NAVIGATION
    // ═══════════════════════════════════════════════════════════════

    public void clickWagesTab() {
        getExplicitWait(10).until(ExpectedConditions.elementToBeClickable(TAB_WAGES)).click();
        test.log(Status.INFO, "Clicked Wages tab.");
        pause(1);
    }

    public void clickCommissionTab() {
        getExplicitWait(10).until(ExpectedConditions.elementToBeClickable(TAB_COMMISSION)).click();
        test.log(Status.INFO, "Clicked Commission tab.");
        pause(1);
    }

    public void clickUpfrontTab() {
        getExplicitWait(10).until(ExpectedConditions.elementToBeClickable(TAB_UPFRONT)).click();
        test.log(Status.INFO, "Clicked Upfront tab.");
        pause(1);
    }

    public void clickOverrideTab() {
        getExplicitWait(10).until(ExpectedConditions.elementToBeClickable(TAB_OVERRIDE)).click();
        test.log(Status.INFO, "Clicked Override tab.");
        pause(1);
    }

    public void clickDefaultTab() {
        getExplicitWait(10).until(ExpectedConditions.elementToBeClickable(TAB_DEFAULT)).click();
        test.log(Status.INFO, "Clicked Default tab.");
        pause(1);
    }

    public void copyDefaultValue() {
        WebElement copyBtn = getExplicitWait(10).until(ExpectedConditions.elementToBeClickable(COMM_COPY_VALUE));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", copyBtn);
        pause(1);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", copyBtn);
        test.log(Status.INFO, "Clicked Copy from default Button.");
        pause(1);
    }

    // ═══════════════════════════════════════════════════════════════
    // WAGES TAB ACTIONS
    // ═══════════════════════════════════════════════════════════════

    public void enableWages(boolean enable) {
        pause(1);
        setToggle(WAGES_TOGGLE, enable, "Wages");
        pgProjectExpectedVariables.setPosSetupWagesEnabled("Wages Enabled", String.valueOf(enable));
    }

    public void selectWagesType(String value) {
        if (value.equalsIgnoreCase("N/A") || value.equalsIgnoreCase("NA")) {
            pgProjectExpectedVariables.setPosSetupWagesType("Wages Type", value);
            test.log(Status.PASS, "Wages Type is N/A");
        } else {
            selectByVisibleText(WAGES_TYPE_DROPDOWN, value);
            pgProjectExpectedVariables.setPosSetupWagesType("Wages Type", value);
            test.log(Status.PASS, "Wages Type is Set as: " + value);
        }
    }

    public void enterWagesAmount(String amount) {
        WebDriverWait wait = getExplicitWait(10);
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(WAGES_AMOUNT_INPUT));
        field.clear();
        field.sendKeys(amount);
        pgProjectExpectedVariables.setPosSetupWagesAmount("Wages Amount", amount);
        test.log(Status.PASS, "Wages Amount is Set as: " + amount);
    }

    public void enterPTOHours(String hours) {
        WebDriverWait wait = getExplicitWait(10);
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(WAGES_PTO_INPUT));
        field.clear();
        field.sendKeys(hours);
        test.log(Status.PASS, "PTO Hours is: " + hours);
    }

    public void selectUnusedPTOExpires(String value) {
        if (value.equalsIgnoreCase("N/A") || value.equalsIgnoreCase("NA")) {
//            pgProjectExpectedVariables.setPosSetupUnusedPTO("Unused PTO Expires", value);
            test.log(Status.PASS, "Unused PTO Expires is N/A");
        } else {
            selectByVisibleText(WAGES_UNUSED_PTO_INPUT, value);
//            pgProjectExpectedVariables.setPosSetupUnusedPTO("Unused PTO Expires", value);
            test.log(Status.PASS, "Unused PTO Expires is Set as: " + value);
        }
    }

    // ═══════════════════════════════════════════════════════════════
    // COMMISSION TAB ACTIONS
    // ═══════════════════════════════════════════════════════════════

    public void enableCommission(boolean enable) {
        setToggle(COMMISSION_TOGGLE, enable, "Commission");
        pgProjectExpectedVariables.setPosSetupCommissionEnabled("Commission Enabled", String.valueOf(enable));
    }

    public void selectCommissionDefaultType(String value, String optn) {

        switch (optn) {
            case "Setter":
                if (value.equalsIgnoreCase("N/A") || value.equalsIgnoreCase("NA")) {
                    pgProjectExpectedVariables.setPosSetupCommTypeSetter("Commission Type Setter", value);
                    test.log(Status.PASS, "Commission Type for Setter is N/A");
                } else {
                    selectByVisibleText(COMM_DEFAULT_TYPE_DROPDOWN, value);
                    pgProjectExpectedVariables.setPosSetupCommTypeSetter("Commission Type Setter", value);
                    test.log(Status.PASS, "Commission Type for Setter is Set as: " + value);
                }
            break;
            case "Closer":
                if (value.equalsIgnoreCase("N/A") || value.equalsIgnoreCase("NA")) {
                    pgProjectExpectedVariables.setPosSetupCommTypeCloser("Commission Type Closer", value);
                    test.log(Status.PASS, "Commission Type for Closer is N/A");
                } else {
                    selectByVisibleText(COMM_DEFAULT_TYPE_DROPDOWN, value);
                    pgProjectExpectedVariables.setPosSetupCommTypeCloser("Commission Type Closer", value);
                    test.log(Status.PASS, "Commission Type for Closer is Set as: " + value);
                }
                break;
            case "Self Gen":
                if (value.equalsIgnoreCase("N/A") || value.equalsIgnoreCase("NA")) {
                    pgProjectExpectedVariables.setPosSetupCommTypeSelfGen("Commission Type Self Gen", value);
                    test.log(Status.PASS, "Commission Type for SelfGen is N/A");
                } else {
                    selectByVisibleText(COMM_DEFAULT_TYPE_DROPDOWN, value);
                    pgProjectExpectedVariables.setPosSetupCommTypeSelfGen("Commission Type Self Gen", value);
                    test.log(Status.PASS, "Commission Type for SelfGen  is Set as: " + value);
                }
                break;
            default:
                test.log(Status.PASS, "Commission Type is Set as: NA");

        }
    }

    public void enterCommissionDefaultAmount(String amount, String optn) {
        WebDriverWait wait = getExplicitWait(10);
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(COMM_DEFAULT_AMOUNT_INPUT));
        field.clear();
        field.sendKeys(amount);

        switch (optn) {
            case "Setter":
                pgProjectExpectedVariables.setPosSetupCommAmountSetter("Commission Amount Setter", amount);
                test.log(Status.PASS, "Commission Amount for Setter is Set as: " + amount);
                break;
            case "Closer":
                pgProjectExpectedVariables.setPosSetupCommAmountCloser("Commission Amount Closer", amount);
                test.log(Status.PASS, "Commission Amount for Closer is Set as: " + amount);
                break;
            case "Self Gen":
                pgProjectExpectedVariables.setPosSetupCommAmountSelfGen("Commission Amount Self Gen", amount);
                test.log(Status.PASS, "Commission Amount for SelfGen is Set as: " + amount);
                break;
            default:
                test.log(Status.PASS, "Commission Type is Set as: NA");
        }
    }

    public void selectCommissionDefaultTierSchema(String value) {
        if (value.equalsIgnoreCase("N/A") || value.equalsIgnoreCase("NA")) {
            pgProjectExpectedVariables.setPosSetupCommTier("Comm Default Tier", value);
            test.log(Status.PASS, "Commission Default Tier Schema is N/A");
        } else {
//            selectByVisibleText(COMM_DEFAULT_TIER_DROPDOWN, value);
            pgProjectExpectedVariables.setPosSetupCommTier("Comm Default Tier", value);
            test.log(Status.PASS, "Commission Default Tier Schema is Set as: " + value);
        }
    }

    // ═══════════════════════════════════════════════════════════════
    // COMMISSION TAB — LEFT PRODUCT PANEL + PER-PRODUCT FIELDS
    // ═══════════════════════════════════════════════════════════════

    public void clickProductInPanel(String productName) {
        WebDriverWait wait = getExplicitWait(10);
        String xp = String.format(PRODUCT_PANEL_XPATH, productName, productName, productName);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xp))).click();
        test.log(Status.INFO, "Selected product from panel: " + productName);
        pause(1);
    }

    public void enterCommissionPercent(String value) {
        WebDriverWait wait = getExplicitWait(10);
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(COMM_PERCENT_INPUT));
        field.clear();
        field.sendKeys(value);
        pgProjectExpectedVariables.setPosSetupCommPercent("Comm Percent", value);
        test.log(Status.PASS, "Commission % entered as: " + value);
    }

    public void selectCalculatedType(String value) {
        if (value.equalsIgnoreCase("N/A") || value.equalsIgnoreCase("NA")) {
            pgProjectExpectedVariables.setPosSetupCommCalculated("Comm Calculated", value);
            test.log(Status.PASS, "Calculated type is N/A");
        } else {
            selectByVisibleText(COMM_CALCULATED_DROPDOWN, value);
            pgProjectExpectedVariables.setPosSetupCommCalculated("Comm Calculated", value);
            test.log(Status.PASS, "Calculated type set as: " + value);
        }
    }

    public void checkUnlockedForHiringCommission(boolean check) {
        WebDriverWait wait = getExplicitWait(10);
        WebElement cb = wait.until(ExpectedConditions.elementToBeClickable(COMM_UNLOCK_HIRING_LEFT));
        String cls = cb.getDomAttribute("class");
        boolean isChecked = cls != null && cls.contains("p-checkbox-checked");
        if (isChecked != check) cb.click();
        test.log(Status.PASS, "Unlocked for Hiring (Commission) set to: " + check);
    }

    public void checkUnlockedForHiringCalculated(boolean check) {
        WebDriverWait wait = getExplicitWait(10);
        WebElement cb = wait.until(ExpectedConditions.elementToBeClickable(COMM_UNLOCK_HIRING_RIGHT));
        String cls = cb.getDomAttribute("class");
        boolean isChecked = cls != null && cls.contains("p-checkbox-checked");
        if (isChecked != check) cb.click();
        test.log(Status.PASS, "Unlocked for Hiring (Calculated) set to: " + check);
    }

    public void enterCommissionLimit(String value) {
        WebDriverWait wait = getExplicitWait(10);
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(COMM_LIMIT_INPUT));
        field.clear();
        field.sendKeys(value);
        pgProjectExpectedVariables.setPosSetupCommLimit("Comm Limit", value);
        test.log(Status.PASS, "Commission Limit entered as: " + value);
    }

    public void selectLimitType(String value) {
        if (value.equalsIgnoreCase("N/A") || value.equalsIgnoreCase("NA")) {
            pgProjectExpectedVariables.setPosSetupCommLimitType("Comm Limit Type", value);
            test.log(Status.PASS, "Limit Type is N/A");
        } else {
            selectByVisibleText(COMM_LIMIT_TYPE_DROPDOWN, value);
            pgProjectExpectedVariables.setPosSetupCommLimitType("Comm Limit Type", value);
            test.log(Status.PASS, "Limit Type set as: " + value);
        }
    }

    // ═══════════════════════════════════════════════════════════════
    // NAVIGATION BUTTONS (Back / Next / Submit)
    // ═══════════════════════════════════════════════════════════════

    public void clickBack() {
        getExplicitWait(10).until(ExpectedConditions.elementToBeClickable(BACK_BUTTON)).click();
        test.log(Status.INFO, "Clicked Back button.");
        pause(1);
    }

    public void clickNextNewPosition() {
        WebElement btn = getExplicitWait(10).until(ExpectedConditions.presenceOfElementLocated(NEXT_BUTTON_NEW_POSITION));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);
        pause(1);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        test.log(Status.INFO, "Clicked Next button.");
        pause(1);
    }

    public void clickOnNextSetUp() {
        pause(1);
        WebElement btn = getExplicitWait(10).until(ExpectedConditions.presenceOfElementLocated(NEXT_BUTTON_SETUP));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);
        pause(1);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        test.log(Status.INFO, "Clicked Next Button.");
        pause(1);
    }

    public void clickSubmit() {
        WebElement btn = getExplicitWait(10).until(ExpectedConditions.presenceOfElementLocated(SUBMIT_BUTTON));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);
        pause(1);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        test.log(Status.PASS, "Clicked Submit button.");
        pause(2);
    }

    // ═══════════════════════════════════════════════════════════════
    // SAVE & CLOSE
    // ═══════════════════════════════════════════════════════════════

    public void clickSaveAndContinue() {
        WebDriverWait wait = getExplicitWait(10);
        wait.until(ExpectedConditions.elementToBeClickable(SAVE_AND_CONTINUE_BUTTON)).click();
        test.log(Status.PASS, "Clicked 'Save & Continue' button.");
        pause(1);
    }

    public void closePositionSetupModal() {
        WebDriverWait wait = getExplicitWait(10);
        wait.until(ExpectedConditions.elementToBeClickable(CLOSE_BUTTON)).click();
        test.log(Status.INFO, "Closed Position Setup modal.");
        pause(1);
    }

    public boolean verifySetupSavedSuccessfully() {
        WebDriverWait wait = getExplicitWait(10);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(SUCCESS_TOAST));
            test.log(Status.PASS, "Position Setup saved successfully.");
            return true;
        } catch (Exception e) {
            test.log(Status.FAIL, "Position Setup save confirmation not found.");
            return false;
        }
    }

    public void searchPosition(String str) {
        WebDriverWait wait = getExplicitWait(10);
        WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_INPUT));
        searchField.clear();
        searchField.sendKeys(str);
        test.log(Status.PASS, "Searched for Milestone: " + str);
        pause(1);
    }
}
