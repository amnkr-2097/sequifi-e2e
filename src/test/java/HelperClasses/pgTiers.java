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
    private static final By TIER_SYSTEM_DROPDOWN  = By.xpath("//*[@name='tier_system_id']/ancestor::div[@data-pc-name='dropdown']");
    private static final By TIER_METRICS_DROPDOWN = By.xpath("//*[@name='tier_metrics_id']/ancestor::div[@data-pc-name='dropdown']");
    private static final By TIER_TYPE_DROPDOWN    = By.xpath("//input[@name='tier_type']/ancestor::div[@data-pc-name='dropdown']");
    private static final By SAVE_BUTTON         = By.xpath("//*[contains(text(),'Save')]");
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
        test.log(Status.PASS, "Clicked 'Add New' tier button.");
        pause(1);
    }

    public void enterSchemaName(String name) {
        WebDriverWait wait = getExplicitWait(10);
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(SCHEMA_NAME_INPUT));
        field.clear();
        String StrName  = name + (int) Math.floor(Math.random() * 100);
        field.sendKeys(StrName);
        pgProjectExpectedVariables.setTierSchemaName("Tier Schema Name" , StrName);
        test.log(Status.PASS, "Tier Scheme Name is Set as : " + StrName);
    }

    public void enterSchemaDescription(String description) {
        WebDriverWait wait = getExplicitWait(10);
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(SCHEMA_DESC_INPUT));
        field.clear();
        String  strDis = description + (int) Math.floor(Math.random() * 100);
        field.sendKeys(strDis);
        pgProjectExpectedVariables.setTierSchemaDescription("Tier Schema Description" , strDis);
        test.log(Status.PASS, "Tier Scheme Description is Set as : " + description);
    }

    public void selectTierSystem(String value) {
        if(value.equalsIgnoreCase("NA") || value.equalsIgnoreCase("N/A")) {
            pgProjectExpectedVariables.setTierTierSystem("Tier System" , value);
            test.log(Status.PASS, "Tier System is N/A");
        }
        else {
            selectByVisibleText(TIER_SYSTEM_DROPDOWN,value);
            pgProjectExpectedVariables.setTierTierSystem("Tier System" , value);
            test.log(Status.PASS, "Tier System is Set as: " + value);
        }
    }

    public void selectTierMetrics(String value) {

        if(value.equalsIgnoreCase("NA") || value.equalsIgnoreCase("N/A")) {
            pgProjectExpectedVariables.setTierTierMetrics("Tier Metrics" , value);
            test.log(Status.PASS, "Tier Metrics is N/A");
        }
        else {
            selectByVisibleText(TIER_METRICS_DROPDOWN,value);
            pgProjectExpectedVariables.setTierTierMetrics("Tier Metrics" , value);
            test.log(Status.PASS, "Tier Metrics is Set as: " + value);
        }
    }

    public void selectTierType(String value) {
        if(value.equalsIgnoreCase("NA") || value.equalsIgnoreCase("N/A")) {
            pgProjectExpectedVariables.setTierTierTypes("Tier Type" , value);
            test.log(Status.PASS, "Tier Type is N/A");
        }
        else {
            selectByVisibleText(TIER_TYPE_DROPDOWN,value);
            pgProjectExpectedVariables.setTierTierTypes("Tier Type" , value);
            test.log(Status.PASS, "Tier Type is Set as: " + value);
        }
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
            test.log(Status.PASS, "Tier '" + pgProjectExpectedVariables.getTierSchemaName("Tier Schema Name") + "' created successfully.");
            return true;
        } catch (Exception e) {
            try {
                By tierInList = By.xpath("//*[contains(text(),'" + pgProjectExpectedVariables.getTierSchemaName("Tier Schema Name") + "')]");
                wait.until(ExpectedConditions.visibilityOfElementLocated(tierInList));
                test.log(Status.PASS, "Tier '" + pgProjectExpectedVariables.getTierSchemaName("Tier Schema Name") + " found in list.");
                return true;
            } catch (Exception ex) {
                test.log(Status.FAIL, "Tier '" + pgProjectExpectedVariables.getTierSchemaName("Tier Schema Name") + " not found after creation.");
                return false;
            }
        }
    }

    public void searchTier() {
        WebDriverWait wait = getExplicitWait(10);
        WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_INPUT));
        searchField.clear();
        searchField.sendKeys(pgProjectExpectedVariables.getTierSchemaName("Tier Schema Name"));
        test.log(Status.PASS, "Searched for tier: " + pgProjectExpectedVariables.getTierSchemaName("Tier Schema Name"));
        pause(2);
    }

    public boolean verifyTierInList() {
        WebDriverWait wait = getExplicitWait(10);
        try {
            By tierRow = By.xpath("//tr[contains(.,'" + pgProjectExpectedVariables.getTierSchemaName("Tier Schema Name") + "')] | //*[contains(@class,'row') and contains(.,'" + pgProjectExpectedVariables.getTierSchemaName("Tier Schema Name") + "')] | //*[contains(@class,'card') and contains(.,'"
                    + pgProjectExpectedVariables.getTierSchemaName("Tier Schema Name") + "')]");
            wait.until(ExpectedConditions.visibilityOfElementLocated(tierRow));
            test.log(Status.PASS, "Tier '" + pgProjectExpectedVariables.getTierSchemaName("Tier Schema Name") + "' found in list.");
            return true;
        } catch (Exception e) {
            test.log(Status.FAIL, "Tier '" + pgProjectExpectedVariables.getTierSchemaName("Tier Schema Name") + "' not found in list.");
            return false;
        }
    }
}
