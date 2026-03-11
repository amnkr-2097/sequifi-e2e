package HelperClasses;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class pgLocation extends pgGeneric {

    // Navigation
    private static final By SETTINGS_MENU       = By.xpath("//a[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'settings')] | //span[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'settings')]/ancestor::a");
    private static final By LOCATION_TAB         = By.xpath("//*[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'location')]");

    // Add New button
    private static final By ADD_NEW_BUTTON       = By.id("admin_settings_location_Add_New_Location_Button");

    // Form fields
    private static final By STATE_DROPDOWN       = By.id("state_dropdown");
    private static final By STATE_DROPDOWN_TXT   = By.xpath("//*[@data-pc-section = 'filterinput']");
    private static final By LOCATION_CODE_INPUT  = By.id("General_Code_text_input");
    private static final By OFFICE_CHECKBOX      = By.id("office_location_checkbox");
    private static final By OFFICE_NAME_INPUT    = By.id("Office_Name_Input");
    private static final By OFFICE_ADDRESS_INPUT = By.id("Auto_Office_Address_Input");
    private static final By MIN_REDLINE_INPUT    = By.id("min_redline_input");
    private static final By STD_REDLINE_INPUT    = By.id("Standard_Redline_input");
    private static final By MAX_REDLINE_INPUT    = By.id("Max_Redline_input");
    private static final By EFFECTIVE_DATE_INPUT = By.id("Effective_From_Date_Input");
    private static final By SUBMIT_BUTTON        = By.id("Add_Update_Button");

    // Search
    private static final By SEARCH_INPUT         = By.xpath("//*[@id = 'admin_settings_location_global_search']");

    // Feedback
    private static final By SUCCESS_TOAST        = By.cssSelector(".toast-success, .Toastify__toast--success, [class*='success']");
    private static final By VALIDATION_ERROR     = By.cssSelector(".text-danger, .error-message, .invalid-feedback, [class*='error']");

    public void navigateToLocationTab() {
        WebDriverWait wait = getExplicitWait(15);

        WebElement settingsLink = wait.until(ExpectedConditions.elementToBeClickable(SETTINGS_MENU));
        settingsLink.click();
        test.log(Status.INFO, "Clicked Settings menu.");
        pause(2);

        WebElement locationTab = wait.until(ExpectedConditions.elementToBeClickable(LOCATION_TAB));
        locationTab.click();
        test.log(Status.INFO, "Clicked Location tab.");
        pause(2);
    }

    public void clickAddNew() {
        WebDriverWait wait = getExplicitWait(10);
        WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(ADD_NEW_BUTTON));
        addBtn.click();
        test.log(Status.INFO, "Clicked 'Add New' location button.");
        pause(1);
    }

    public void selectState(String stateName) {
        selectByVisibleText(STATE_DROPDOWN,stateName);
        pgProjectExpectedVariables.setLocState("State" , stateName);
        test.log(Status.PASS, "State set as: " + pgProjectExpectedVariables.getLocState("State"));
    }

    public void enterLocationCode(String code) {
        WebDriverWait wait = getExplicitWait(10);
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(LOCATION_CODE_INPUT));
        field.clear();
        String strCode = code + (int) Math.floor(Math.random() * 10000);
        field.sendKeys(strCode);
        pgProjectExpectedVariables.setLocLocationCode("Location Code" , field.getDomAttribute("value"));
        test.log(Status.PASS, "Location code set as: " + pgProjectExpectedVariables.getLocLocationCode("Location Code"));
    }

    public void checkOfficeCheckbox() {
        WebDriverWait wait = getExplicitWait(10);
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(OFFICE_CHECKBOX));
        checkbox.click();
        test.log(Status.INFO, "Checked Office checkbox.");
        pause(1);
    }

    public void enterOfficeName(String name) {
        WebDriverWait wait = getExplicitWait(10);
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(OFFICE_NAME_INPUT));
        field.clear();
        field.sendKeys(name);
        pgProjectExpectedVariables.setLocOfficeNamee("Office Name" , name );
        test.log(Status.PASS, "Office Name set as: " + pgProjectExpectedVariables.getLocOfficeName("Office Name"));
    }

    public void enterOfficeAddress(String address) {
        WebDriverWait wait = getExplicitWait(10);
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(OFFICE_ADDRESS_INPUT));
        field.clear();
        field.sendKeys(address);
        pause(2);
        // Select first autocomplete suggestion
        field.sendKeys(Keys.ARROW_DOWN);
        field.sendKeys(Keys.ENTER);
        pgProjectExpectedVariables.setLocOfficeAddress("Office Address" , address );
        test.log(Status.PASS, "Office Address set as: " + pgProjectExpectedVariables.getLocOfficeAddress("Office Address"));
        pause(1);
    }

    public void enterRedlineValues(String min, String standard, String max) {
        WebDriverWait wait = getExplicitWait(10);

        if (min != null && !min.isEmpty()) {
            WebElement minField = wait.until(ExpectedConditions.visibilityOfElementLocated(MIN_REDLINE_INPUT));
            minField.clear();
            minField.sendKeys(min);
            pgProjectExpectedVariables.setLocRedlineMin("Min Redline" , min );
            test.log(Status.PASS, "Min Redline set as: " + pgProjectExpectedVariables.getLocRedlineMin("Min Redline"));

        }

        WebElement stdField = wait.until(ExpectedConditions.visibilityOfElementLocated(STD_REDLINE_INPUT));
        stdField.clear();
        stdField.sendKeys(standard);
        pgProjectExpectedVariables.setLocRedlineStd("Std Redline" , standard );
        test.log(Status.PASS, "Std Redline set as: " + pgProjectExpectedVariables.getLocRedlineStd("Std Redline"));


        if (max != null && !max.isEmpty()) {
            WebElement maxField = wait.until(ExpectedConditions.visibilityOfElementLocated(MAX_REDLINE_INPUT));
            maxField.clear();
            maxField.sendKeys(max);
            pgProjectExpectedVariables.setLocRedlineMax("Max Redline" , min );
            test.log(Status.PASS, "Max Redline set as: " + pgProjectExpectedVariables.getLocRedlineMax("Max Redline"));

        }
    }

    public void enterEffectiveDateToday() {
        pause(1);
        selectTodayByLabel("Effective From");
        pgProjectExpectedVariables.setLocEffectiveDate("Location Eff Date" , today() );
        test.log(Status.PASS, "Location Effective Date set as : " + pgProjectExpectedVariables.getLocEffectiveDate("Location Eff Date"));
    }

    public void clickAddLocationButton() {
        WebDriverWait wait = getExplicitWait(10);
        WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(SUBMIT_BUTTON));
        submitBtn.click();
        test.log(Status.PASS, "Clicked Add/Update Location button.");
        pause(2);
    }

    public boolean verifyLocationCreated() {
        WebDriverWait wait = getExplicitWait(10);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(SUCCESS_TOAST));
            test.log(Status.PASS, "Location '" + pgProjectExpectedVariables.getLocLocationCode("Location Code") + "' created successfully (success toast visible).");
            return true;
        } catch (Exception e) {
            // Fallback: check if location appears in the list
            try {
                By locationInList = By.xpath("//*[contains(text(),'" + pgProjectExpectedVariables.getLocLocationCode("Location Code") + "')]");
                wait.until(ExpectedConditions.visibilityOfElementLocated(locationInList));
                test.log(Status.PASS, "Location '" + pgProjectExpectedVariables.getLocLocationCode("Location Code") + "' found in location list.");
                return true;
            } catch (Exception ex) {
                test.log(Status.FAIL, "Location '" + pgProjectExpectedVariables.getLocLocationCode("Location Code") + "' not found after creation.");
                return false;
            }
        }
    }

    public boolean verifyValidationError(String fieldLabel) {
        WebDriverWait wait = getExplicitWait(5);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(VALIDATION_ERROR));
            test.log(Status.PASS, "Validation error displayed for: " + fieldLabel);
            return true;
        } catch (Exception e) {
            test.log(Status.FAIL, "No validation error found for: " + fieldLabel);
            return false;
        }
    }

    public void searchLocation() {
        WebDriverWait wait = getExplicitWait(10);
        WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_INPUT));
        pause(2);
        searchField.clear();
        test.log(Status.INFO, "location: " + pgProjectExpectedVariables.getLocLocationCode("Location Code"));
        searchField.click();
        String code  = pgProjectExpectedVariables.getLocLocationCode("Location Code");
        searchField.sendKeys(code);
        test.log(Status.INFO, "Searched for location: " + pgProjectExpectedVariables.getLocLocationCode("Location Code"));
        pause(2);
    }

    public void verifyLocationInList() {
        WebDriverWait wait = getExplicitWait(10);
        try {
            By locationRow = By.xpath("//tr[contains(.,'" + pgProjectExpectedVariables.getLocLocationCode("Location Code") + "')] | //*[contains(@class,'row') and contains(.,'"
                    + pgProjectExpectedVariables.getLocLocationCode("Location Code") + "')]");
            wait.until(ExpectedConditions.visibilityOfElementLocated(locationRow));
            test.log(Status.PASS, "Location '" + pgProjectExpectedVariables.getLocLocationCode("Location Code") + "' found in list.");
        } catch (Exception e) {
            test.log(Status.FAIL, "Location '" + pgProjectExpectedVariables.getLocLocationCode("Location Code") + "' not found in list.");
            throw new AssertionError("Location '" + pgProjectExpectedVariables.getLocLocationCode("Location Code") + "' not found in the list.");
        }
    }
}
