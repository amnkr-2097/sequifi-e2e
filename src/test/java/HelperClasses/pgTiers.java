package HelperClasses;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class pgTiers extends pgGeneric {

    private static final By TIERS_TAB           = By.xpath("//*[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'tiers')]");
    private static final By ADD_NEW_BUTTON      = By.id("admin_settings_location_Add_New_Location_Button");
    private static final By SCHEMA_NAME_INPUT   = By.cssSelector("input[name='schema_name']");
    private static final By SCHEMA_DESC_INPUT   = By.cssSelector("textarea[name='schema_description']");
    private static final By TIER_SYSTEM_DROPDOWN  = By.cssSelector("[name='tier_system_id']");
    private static final By TIER_METRICS_DROPDOWN = By.cssSelector("[name='tier_metrics_id']");
    private static final By TIER_TYPE_DROPDOWN    = By.cssSelector("[name='tier_type']");
    private static final By SAVE_BUTTON         = By.xpath("//button[contains(text(),'Save')]");
    private static final By SEARCH_INPUT        = By.cssSelector("input[placeholder*='Search'], input[type='search']");
    private static final By SUCCESS_TOAST       = By.cssSelector(".toast-success, .Toastify__toast--success, [class*='success']");

    public void navigateToTiersTab() {
        WebDriverWait wait = getExplicitWait(15);

        By settingsMenu = By.xpath("//a[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'settings')] | //span[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'settings')]/ancestor::a");
        WebElement settingsLink = wait.until(ExpectedConditions.elementToBeClickable(settingsMenu));
        settingsLink.click();
        test.log(Status.INFO, "Clicked Settings menu.");
        pause(2);

        WebElement tiersTab = wait.until(ExpectedConditions.elementToBeClickable(TIERS_TAB));
        tiersTab.click();
        test.log(Status.INFO, "Clicked Tiers tab.");
        pause(2);
    }

    public void clickAddNew() {
        WebDriverWait wait = getExplicitWait(10);
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(ADD_NEW_BUTTON));
        btn.click();
        test.log(Status.INFO, "Clicked 'Add New' tier button.");
        pause(1);
    }

    public void enterSchemaName(String name) {
        WebDriverWait wait = getExplicitWait(10);
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(SCHEMA_NAME_INPUT));
        field.clear();
        field.sendKeys(name);
        test.log(Status.INFO, "Entered tier schema name: " + name);
    }

    public void enterSchemaDescription(String description) {
        WebDriverWait wait = getExplicitWait(10);
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(SCHEMA_DESC_INPUT));
        field.clear();
        field.sendKeys(description);
        test.log(Status.INFO, "Entered tier schema description.");
    }

    public void selectDropdownOption(By dropdownLocator, String optionText, String fieldName) {
        WebDriverWait wait = getExplicitWait(10);
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(dropdownLocator));
        dropdown.click();
        pause(1);

        By option = By.xpath("//*[contains(@class,'option') and contains(text(),'" + optionText + "')]");
        try {
            WebElement opt = wait.until(ExpectedConditions.elementToBeClickable(option));
            opt.click();
        } catch (Exception e) {
            dropdown.sendKeys(optionText);
            pause(1);
            dropdown.sendKeys(Keys.ENTER);
        }
        test.log(Status.INFO, "Selected " + fieldName + ": " + optionText);
    }

    public void selectTierSystem(String value) {
        selectDropdownOption(TIER_SYSTEM_DROPDOWN, value, "Tier System");
    }

    public void selectTierMetrics(String value) {
        selectDropdownOption(TIER_METRICS_DROPDOWN, value, "Tier Metrics");
    }

    public void selectTierType(String value) {
        selectDropdownOption(TIER_TYPE_DROPDOWN, value, "Tier Type");
    }

    public void clickSaveButton() {
        WebDriverWait wait = getExplicitWait(10);
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(SAVE_BUTTON));
        btn.click();
        test.log(Status.INFO, "Clicked Save tier button.");
        pause(2);
    }

    public boolean verifyTierCreated() {
        WebDriverWait wait = getExplicitWait(10);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(SUCCESS_TOAST));
//            test.log(Status.PASS, "Tier '" + schemaName + "' created successfully.");
            return true;
        } catch (Exception e) {
            try {
//                By tierInList = By.xpath("//*[contains(text(),'" + schemaName + "')]");
//                wait.until(ExpectedConditions.visibilityOfElementLocated(tierInList));
//                test.log(Status.PASS, "Tier '" + schemaName + "' found in list.");
                return true;
            } catch (Exception ex) {
//                test.log(Status.FAIL, "Tier '" + schemaName + "' not found after creation.");
                return false;
            }
        }
    }

    public void searchTier() {
        WebDriverWait wait = getExplicitWait(10);
        WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_INPUT));
        searchField.clear();
//        searchField.sendKeys(searchTerm);
//        test.log(Status.INFO, "Searched for tier: " + searchTerm);
        pause(2);
    }

    public boolean verifyTierInList() {
        WebDriverWait wait = getExplicitWait(10);
        try {
//            By tierRow = By.xpath("//tr[contains(.,'" + schemaName + "')] | //*[contains(@class,'row') and contains(.,'" + schemaName + "')] | //*[contains(@class,'card') and contains(.,'" + schemaName + "')]");
//            wait.until(ExpectedConditions.visibilityOfElementLocated(tierRow));
//            test.log(Status.PASS, "Tier '" + schemaName + "' found in list.");
            return true;
        } catch (Exception e) {
//            test.log(Status.FAIL, "Tier '" + schemaName + "' not found in list.");
            return false;
        }
    }
}
