package HelperClasses;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public class pgMilestone extends pgGeneric{

    private final By MILESTONE_TAB = By.xpath("//a[contains(text(),'Milestone')] | //*[contains(@class,'nav-link') and contains(text(),'Milestone')]");
    private final By milestonePageTitle = By.xpath("//*[contains(text(),'Milestones')]");
    private final By ADD_NEW_BUTTON = By.id("admin_settings_location_Add_New_Location_Button");
    private final By SEARCH_INPUT = By.id("admin_settings_location_global_search");
    private final By auditLogButton = By.id("admin_settings_location_toggle_map_and_list");
    private final By tableRows = By.xpath("//table//tbody//tr");
    private final By SCHEMA_NAME_COLUMN = By.xpath("//table//tbody//tr/td[1]");
    private final By DESCRIPTION_COLUMN = By.xpath("//table//tbody//tr/td[2]");
    private final By TRIGGER_COUNT_COLUMN = By.xpath("//table//tbody//tr/td[3]");
    private final By LINKED_PRODUCT_COLUMN = By.xpath("//table//tbody//tr/td[4]");
    private final By modalTitle = By.xpath("//*[contains(@class,'modal') or @role='dialog']//*[contains(text(),'Add New Milestone') or contains(text(),'Edit Milestone')]");
    private final By SCHEMA_NAME_INPUT = By.id("Schema_Name_Input");
    private final By SCHEMA_DESCRIPTION = By.id("Position_Name_Input");
    private final By SAVE_BUTTON = By.xpath("//*[contains(text(),'Save')]");
    private final By TRIGGER_NAME_INPUT = By.xpath("//*[@name='name' and @placeholder='Enter Milestone Name']");
    private final By TRIGGER_DATE_DROPDOWN = By.xpath("//*[@name='on_trigger']/ancestor::div[@data-pc-name='dropdown']");
    private final By TRIGGER_DATE_DROPDOWN_ENABLE = By.xpath("//*[@name='on_trigger']/ancestor::div[@data-pc-name='dropdown' and @data-p-disabled='false']");
    private final By ADD_TRIGGER_BUTTON = By.xpath("(//*[contains(@class,'modal') or @role='dialog']//*[contains(@class,'cursor-pointer')]//*[contains(@class,'w-35px')])[last()]");
    private final By SUCCESS_TOAST = By.xpath("//*[contains(@class,'Toastify')]//*[contains(text(),'Successfully') or contains(text(),'success')]");
    private final By ERROR_TOAST = By.xpath("//*[contains(@class,'Toastify')]//*[contains(text(),'error') or contains(text(),'Error') or contains(text(),'already') or contains(text(),'unique')]");
    private final By SCHEMA_NAME_ERROR = By.xpath("//*[contains(@class,'text-sqError500') and (contains(text(),'name') or contains(text(),'required'))]");
    private final By CONFIRM_BUTTON = By.xpath("//*[contains(@class,'modal') or @role='dialog']//button[contains(text(),'Yes') or contains(text(),'Confirm') or contains(text(),'Ok')]");
    private final By CANCEL_BUTTON = By.xpath("//*[contains(@class,'modal') or @role='dialog']//button[contains(text(),'Cancel') or contains(text(),'No')]");
    private final By FILTER_BUTTON = By.xpath("//*[contains(@class,'filter') or contains(text(),'Filter')]");
    private static final String TRIGGER_ROW_AT = "(//div[contains(@class,'row') and .//input[@placeholder='Enter Milestone Name']])[%d]";
    private static final String TRIGGER_NAME_AT = "(//input[@name='name' and @placeholder='Enter Milestone Name'])[%d]";
    private static final String TRIGGER_DATE_AT = "(//*[@name='on_trigger']/ancestor::div[@data-pc-name='dropdown'])[%d]";
    private static final String TRIGGER_DATE_ENABLED_AT = "(//*[@name='on_trigger']/ancestor::div[@data-pc-name='dropdown' and @data-p-disabled='false'])[%d]";
    private static final String TRIGGER_DATE_VALUE_AT = "(//select[@name='on_trigger'])[%d]";

    public By triggerNameAt(int index) {
        return By.xpath(String.format(TRIGGER_NAME_AT, index));
    }

    public By triggerDateAt(int index) {
        return By.xpath(String.format(TRIGGER_DATE_AT, index));
    }

    public By triggerDateEnabledAt(int index) {
        return By.xpath(String.format(TRIGGER_DATE_ENABLED_AT, index));
    }

    // ==================== Navigation ====================

    public void navigateToMilestoneTab() {
        WebDriverWait wait = getExplicitWait(15);

        By settingsMenu = By.xpath("//a[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'settings')] | //span[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'settings')]/ancestor::a");
        WebElement settingsLink = wait.until(ExpectedConditions.elementToBeClickable(settingsMenu));
        settingsLink.click();
        test.log(Status.INFO, "Clicked Settings menu.");
        pause(2);

        WebElement milestoneTab = wait.until(ExpectedConditions.elementToBeClickable(MILESTONE_TAB));
        milestoneTab.click();
        test.log(Status.INFO, "Clicked Milestone tab.");
        pause(2);
    }

    public void clickAddNew() {
        WebDriverWait wait = getExplicitWait(10);
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(ADD_NEW_BUTTON));
        btn.click();
        test.log(Status.PASS, "Clicked 'Add New' Milestone button.");
        pause(1);
    }

    public void enterSchemaName(String name) {
        WebDriverWait wait = getExplicitWait(10);
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(SCHEMA_NAME_INPUT));
        field.clear();
        String StrName  = name + (int) Math.floor(Math.random() * 100);
        field.sendKeys(StrName);
        pgProjectExpectedVariables.setMileSchemaName("Milestone Schema Name" , StrName);
        test.log(Status.PASS, "Milestone Scheme Name is Set as : " + StrName);
    }

    public void enterSchemaDescription(String description) {
        WebDriverWait wait = getExplicitWait(10);
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(SCHEMA_DESCRIPTION));
        field.clear();
        String  strDis = description + (int) Math.floor(Math.random() * 100);
        field.sendKeys(strDis);
        pgProjectExpectedVariables.setMileSchemaDescription("Milestone Schema Description" , strDis);
        test.log(Status.PASS, "Milestone Scheme Description is Set as : " + description);
    }

    public void captureInitialTriggerData() {
        WebDriverWait wait = getExplicitWait(10);

        // ── Capture initial trigger name ──
        List<WebElement> nameInputs = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(TRIGGER_NAME_INPUT)
        );
        if (!nameInputs.isEmpty()) {
            String initialName = nameInputs.get(0).getDomAttribute("value");
            pgProjectExpectedVariables.setMileMilestoneName_Initial(
                    "Milestone Trigger Name 1", initialName
            );
            test.log(Status.PASS, "Initial Trigger Name captured: " + initialName);
        }

        // ── Capture initial trigger date from the disabled dropdown ──
        List<WebElement> dateDropdowns = driver.findElements(TRIGGER_DATE_DROPDOWN);
        if (!dateDropdowns.isEmpty()) {
            // Read from the hidden <select> inside the PrimeReact dropdown
            WebElement hiddenSelect = dateDropdowns.get(0).findElement(By.cssSelector("select[name='on_trigger'] option"));
            String initialDate = hiddenSelect.getDomAttribute("value");

            pgProjectExpectedVariables.setMileTriggerDate_Initial(
                    "Milestone Trigger Date 1", initialDate
            );
            test.log(Status.PASS, "Initial Trigger Date captured: " + initialDate);
        }
    }

    public void clickAddTriggerButton() {
        List<WebElement> addButtons = driver.findElements(ADD_TRIGGER_BUTTON);
        if (!addButtons.isEmpty()) {
            addButtons.get(addButtons.size() - 1).click();
            pause(1);
        }
        test.log(Status.PASS, "Add trigger button clicked");
    }

    public void clickSave() {
        WebDriverWait wait = getExplicitWait(10);
        wait.until(ExpectedConditions.elementToBeClickable(SAVE_BUTTON)).click();
        test.log(Status.PASS, "Save Button clicked");
    }

    public void searchMilestone() {
        WebDriverWait wait = getExplicitWait(10);
        WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_INPUT));
        searchField.clear();
        searchField.sendKeys(pgProjectExpectedVariables.getMileSchemaName("Milestone Schema Name"));
        test.log(Status.PASS, "Searched for Milestone: " + pgProjectExpectedVariables.getMileSchemaName("Milestone Schema Name"));
        pause(2);
    }

    public boolean verifyMilestoneCreated() {
        WebDriverWait wait = getExplicitWait(10);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(SUCCESS_TOAST));
            test.log(Status.PASS, "Milestone '" + pgProjectExpectedVariables.getTierSchemaName("Milestone Schema Name") + "' created successfully.");
            return true;
        } catch (Exception e) {
            try {
                By milestoneInList = By.xpath("//*[contains(text(),'" + pgProjectExpectedVariables.getMileSchemaName("Milestone Schema Name") + "')]");
                wait.until(ExpectedConditions.visibilityOfElementLocated(milestoneInList));
                test.log(Status.PASS, "Milestone '" + pgProjectExpectedVariables.getMileSchemaName("Milestone Schema Name") + " found in list.");
                return true;
            } catch (Exception ex) {
                test.log(Status.FAIL, "Milestone '" + pgProjectExpectedVariables.getMileSchemaName("Milestone Schema Name") + " not found after creation.");
                return false;
            }
        }
    }

    public boolean verifyMilestoneInList() {
        WebDriverWait wait = getExplicitWait(10);
        try {
            By milestoneRow = By.xpath("//tr[contains(.,'" + pgProjectExpectedVariables.getMileSchemaName("Milestone Schema Name")
                    + "')] | //*[contains(@class,'row') and contains(.,'" + pgProjectExpectedVariables.getMileSchemaName("Milestone Schema Name") + "')] | //*[contains(@class,'card') and contains(.,'"
                    + pgProjectExpectedVariables.getMileSchemaName("Milestone Schema Name") + "')]");
            wait.until(ExpectedConditions.visibilityOfElementLocated(milestoneRow));
            test.log(Status.PASS, "Milestone '" + pgProjectExpectedVariables.getMileSchemaName("Milestone Schema Name") + "' found in list.");
            return true;
        } catch (Exception e) {
            test.log(Status.FAIL, "Milestone '" + pgProjectExpectedVariables.getMileSchemaName("Milestone Schema Name") + "' not found in list.");
            return false;
        }
    }

    public boolean isSuccessToastDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(SUCCESS_TOAST));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Iterates over every trigger row in the modal.
     * Disabled rows → stored as _Initial (pre-existing).
     * Enabled rows  → stored as _Final  (user-created).
     */
    public void captureAllTriggerData() {
        WebDriverWait wait = getExplicitWait(10);

        List<WebElement> nameInputs  = driver.findElements(TRIGGER_NAME_INPUT);
        List<WebElement> dateDropdowns = driver.findElements(TRIGGER_DATE_DROPDOWN);

        int count = Math.min(nameInputs.size(), dateDropdowns.size());

        for (int i = 0; i < count; i++) {
            int displayIndex = i + 1;

            // ── Read milestone name ──
            String name = nameInputs.get(i).getDomAttribute("value");

            // ── Read trigger date from hidden <select> ──
            WebElement hiddenSelect = dateDropdowns.get(i).findElement(
                    By.cssSelector("select[name='on_trigger']")
            );
            String dateValue = hiddenSelect.getDomAttribute("value");

            // ── Determine disabled (initial) vs enabled (final) ──
            String cls = dateDropdowns.get(i).getDomAttribute("class");
            boolean isDisabled = cls != null && cls.contains("p-disabled");

            if (isDisabled) {
                pgProjectExpectedVariables.setMileMilestoneName_Initial(
                        "Milestone Trigger Name " + displayIndex, name
                );
                pgProjectExpectedVariables.setMileTriggerDate_Initial(
                        "Milestone Trigger Date " + displayIndex, dateValue
                );
                test.log(Status.INFO,
                        "Initial trigger [" + displayIndex + "]: " + name + " → " + dateValue);
            } else {
                pgProjectExpectedVariables.setMileMilestoneName_Final(
                        "Milestone Trigger Name " + displayIndex, name
                );
                pgProjectExpectedVariables.setMileTriggerDate_Final(
                        "Milestone Trigger Date " + displayIndex, dateValue
                );
                test.log(Status.INFO,
                        "Final trigger [" + displayIndex + "]: " + name + " → " + dateValue);
            }
        }
        test.log(Status.PASS, "Total triggers captured: " + count);
    }

    public void enterTriggerName(String name, int index) {
        WebDriverWait wait = getExplicitWait(10);

        // Generic: target the Nth milestone name input directly
        WebElement input = wait.until(
                ExpectedConditions.visibilityOfElementLocated(triggerNameAt(index) )
        );
        input.click();
        String os = System.getProperty("os.name").toLowerCase();
        Keys selectAllKey = os.contains("mac") ? Keys.META : Keys.CONTROL;
        input.sendKeys(Keys.chord(selectAllKey, "a"), Keys.DELETE);
        input.sendKeys(name);

        pgProjectExpectedVariables.setMileMilestoneName_Final(
                "Milestone Trigger Name " + index, name
        );
        test.log(Status.PASS, "Final Trigger Name set as: " + name + " at index " + index);
    }


    public void selectTriggerDate(String triggerDateLabel, int index) {
        WebDriverWait wait = getExplicitWait(15);

        // Generic: target the Nth dropdown directly, wait for it to be enabled
        WebElement targetDropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(triggerDateAt(index))
        );

        // Wait for enabled
        wait.until(d -> {
            String cls = targetDropdown.getDomAttribute("class");
            return cls != null && !cls.contains("p-disabled");
        });

        // Scroll into view
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", targetDropdown
        );
        pause(1);

        // Click & select option
        targetDropdown.click();
        By option = By.xpath(
                "//ul[@data-pc-section='list']/li[normalize-space(.)='" + triggerDateLabel + "']"
        );
        wait.until(ExpectedConditions.elementToBeClickable(option)).click();

        pgProjectExpectedVariables.setMileTriggerDate_Final(
                "Milestone Trigger Date " + index, triggerDateLabel
        );
        test.log(Status.PASS, "Final Trigger Date set as: " + triggerDateLabel + " at index " + index);
    }


}
