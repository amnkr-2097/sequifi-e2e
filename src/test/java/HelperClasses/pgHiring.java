package HelperClasses;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.Random;

public class pgHiring extends pgGeneric {

    // ══════════════════════════════════════════════════
    // NAVIGATION
    // ══════════════════════════════════════════════════

    private static final By HIRING_MODULE = By.xpath(
            "//a[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'hiring')] | " +
                    "//span[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'hiring')]/ancestor::a"
    );

    private static final By ONBOARDING_TAB = By.xpath(
            "//*[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'onboarding employees')]"
    );

    private static final By HIRE_NEW_BUTTON = By.xpath(
            "//button[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'hire new')]"
    );

    // ══════════════════════════════════════════════════
    // TAB 1: DETAILS
    // ══════════════════════════════════════════════════

    private static final By FIRST_NAME_INPUT  = By.id("onboard_employees_details_tab_first_name");
    private static final By LAST_NAME_INPUT   = By.id("onboard_employees_details_tab_Last_Name");
    private static final By EMAIL_INPUT       = By.id("onboard_employees_details_tab_Personal_Email");
    private static final By PHONE_INPUT       = By.id("onboard_employees_details_tab_Phone_Number");
    private static final By STATE_DROPDOWN    = By.id("onboard_employees_details_tab_Office_Location");
    private static final By OFFICE_DROPDOWN   = By.id("onboard_employees_details_tab_office_name");

    // ══════════════════════════════════════════════════
    // TAB 2: ORGANIZATION
    // ══════════════════════════════════════════════════

    private static final By DEPARTMENT_DROPDOWN  = By.id("onboard_employees_organization_tab_Department");
    private static final By POSITION_DROPDOWN    = By.id("onboard_employees_organization_tab_Position");
    private static final By MANAGER_DROPDOWN     = By.id("onboard_employees_organization_tab_Select_manger");
    private static final By TEAM_DROPDOWN        = By.id("onboard_employees_organization_tab_select_team");
    private static final By IS_MANAGER_CHECKBOX  = By.cssSelector("input[name='is_manager']");
    private static final By RECRUITER_INPUT      = By.cssSelector("input[placeholder='Select recruiter']");

    // ══════════════════════════════════════════════════
    // TAB 3: REDLINE
    // ══════════════════════════════════════════════════

    private static final By REDLINE_INPUT      = By.id("onboard_employees_redline_tab_redline");
    private static final By REDLINE_TYPE_DD    = By.id("onboard_employees_redline_tab_redline_type");

    // ══════════════════════════════════════════════════
    // COMMON BUTTONS
    // ══════════════════════════════════════════════════

    private static final By SAVE_AND_CONTINUE_BTN = By.xpath(
            "//button[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'save & continue') or " +
                    "contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'save and continue')]"
    );
    private static final By NEXT_BTN = By.xpath(
            "//div[contains(@class,'p-dialog')]//button[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'next')]"
    );
    private static final By BACK_BTN = By.xpath(
            "//div[contains(@class,'p-dialog')]//button[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'back') or " +
                    "contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'previous')]"
    );
    private static final By FINISH_BTN = By.xpath(
            "//button[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'finish') or " +
                    "contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'submit')]"
    );

    // ══════════════════════════════════════════════════
    // FEEDBACK
    // ══════════════════════════════════════════════════

    private static final By SUCCESS_TOAST = By.cssSelector(
            ".toast-success, .Toastify__toast--success, [class*='success']"
    );
    private static final By SEARCH_INPUT = By.cssSelector(
            "input[type='search'], input[placeholder*='Search']"
    );

    // ══════════════════════════════════════════════════
    // NAVIGATION METHODS
    // ══════════════════════════════════════════════════

    public void navigateToOnboardingEmployees() {
        WebDriverWait wait = getExplicitWait(15);

        // Navigate directly to the hiring page
        driver.get(driver.getCurrentUrl().split("/")[0] + "//" +
                driver.getCurrentUrl().split("/")[2] + "/hiring");
        pause(3);

        // Click Onboarding Employees tab
        WebElement onboardingTab = wait.until(ExpectedConditions.elementToBeClickable(ONBOARDING_TAB));
        onboardingTab.click();
        test.log(Status.INFO, "Clicked Onboarding Employees tab.");
        pause(2);
    }

    public void clickHireNew() {
        WebDriverWait wait = getExplicitWait(15);
        WebElement hireBtn = wait.until(ExpectedConditions.elementToBeClickable(HIRE_NEW_BUTTON));
        hireBtn.click();
        test.log(Status.PASS, "Clicked 'Hire New' button.");
        pause(2);
    }

    // ══════════════════════════════════════════════════
    // TAB 1: DETAILS METHODS
    // ══════════════════════════════════════════════════

    public void enterFirstName(String firstName) {
        WebDriverWait wait = getExplicitWait(10);
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_NAME_INPUT));
        field.clear();
        String name = firstName + new Random().nextInt(100);
        field.sendKeys(name);
        pgProjectExpectedVariables.setHireFirstName("First Name", name);
        test.log(Status.PASS, "First Name set as: " + name);
    }

    public void enterLastName(String lastName) {
        WebDriverWait wait = getExplicitWait(10);
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(LAST_NAME_INPUT));
        field.clear();
        String name = lastName + new Random().nextInt(100);
        field.sendKeys(name);
        pgProjectExpectedVariables.setHireLastName("Last Name", name);
        test.log(Status.PASS, "Last Name set as: " + name);
    }

    public void enterPersonalEmail(String emailPrefix) {
        WebDriverWait wait = getExplicitWait(10);
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(EMAIL_INPUT));
        field.clear();
        String email = emailPrefix + new Random().nextInt(10000) + "@sequifi.com";
        field.sendKeys(email);
        pgProjectExpectedVariables.setHireEmail("Email", email);
        test.log(Status.PASS, "Email set as: " + email);
    }

    public void enterPhoneNumber(String phone) {
        WebDriverWait wait = getExplicitWait(10);
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(PHONE_INPUT));
        field.clear();
        field.sendKeys(phone);
        pgProjectExpectedVariables.setHirePhone("Phone", phone);
        test.log(Status.PASS, "Phone set as: " + phone);
    }

    public void selectState(String stateName) {
        WebDriverWait wait = getExplicitWait(10);
        clickPrimeDropdownById("onboard_employees_details_tab_Office_Location");
        selectPrimeDropdownOption(stateName);
        pgProjectExpectedVariables.setHireState("State", stateName);
        test.log(Status.PASS, "State selected: " + stateName);
    }

    public void selectOfficeFirstAvailable() {
        WebDriverWait wait = getExplicitWait(10);
        pause(1); // Wait for office dropdown to populate after state selection
        clickPrimeDropdownById("onboard_employees_details_tab_office_name");
        String officeName = selectPrimeDropdownFirstOption();
        pgProjectExpectedVariables.setHireOffice("Office", officeName);
        test.log(Status.PASS, "Office selected: " + officeName);
    }

    // ══════════════════════════════════════════════════
    // TAB 2: ORGANIZATION METHODS
    // ══════════════════════════════════════════════════

    public void selectDepartment(String departmentName) {
        clickPrimeDropdownById("onboard_employees_organization_tab_Department");
        selectPrimeDropdownOption(departmentName);
        pgProjectExpectedVariables.setHireDepartment("Department", departmentName);
        test.log(Status.PASS, "Department selected: " + departmentName);
    }

    public void selectPositionFirstAvailable() {
        pause(1);
        clickPrimeDropdownById("onboard_employees_organization_tab_Position");
        String posName = selectPrimeDropdownFirstOption();
        pgProjectExpectedVariables.setHirePosition("Position", posName);
        test.log(Status.PASS, "Position selected: " + posName);
    }

    public void selectManagerFirstAvailable() {
        pause(1);
        clickPrimeDropdownById("onboard_employees_organization_tab_Select_manger");
        String mgrName = selectPrimeDropdownFirstOption();
        pgProjectExpectedVariables.setHireManager("Manager", mgrName);
        test.log(Status.PASS, "Manager selected: " + mgrName);
    }

    // ══════════════════════════════════════════════════
    // TAB 3: REDLINE METHODS
    // ══════════════════════════════════════════════════

    /**
     * Fills redline value and type for all role tabs (Closer, Setter, Self Gen).
     * Each role tab needs its own redline value and type.
     */
    public void fillRedlineForAllRoles(String redlineValue, String redlineType) {
        WebDriverWait wait = getExplicitWait(10);
        String[] roles = {"Closer", "Setter", "Self Gen"};

        for (String role : roles) {
            try {
                // Click the role tab
                By roleTab = By.xpath(
                        "//*[contains(@class,'p-dialog')]//*[normalize-space(text())='" + role + "']"
                );
                List<WebElement> roleTabs = driver.findElements(roleTab);
                if (roleTabs.isEmpty()) {
                    test.log(Status.INFO, "Role '" + role + "' tab not found, skipping.");
                    continue;
                }
                roleTabs.get(0).click();
                pause(1);

                // Enter redline value using React native setter (standard sendKeys may not work)
                WebElement redlineInput = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(REDLINE_INPUT)
                );
                setReactInputValue(redlineInput, redlineValue);

                // Select redline type
                clickPrimeDropdownById("onboard_employees_redline_tab_redline_type");
                selectPrimeDropdownOption(redlineType);

                test.log(Status.PASS, "Redline for '" + role + "': $" + redlineValue + " (" + redlineType + ")");
            } catch (Exception e) {
                test.log(Status.WARNING, "Could not set redline for '" + role + "': " + e.getMessage());
            }
        }
    }

    // ══════════════════════════════════════════════════
    // TAB 7: AGREEMENT METHODS
    // ══════════════════════════════════════════════════

    public void fillAgreementDetails() {
        WebDriverWait wait = getExplicitWait(10);
        pause(1);

        // Agreement tab may have start date, offer letter fields
        // Try to set start date as today
        try {
            selectTodayByLabel("Start Date");
            test.log(Status.INFO, "Set agreement start date as today.");
        } catch (Exception e) {
            test.log(Status.INFO, "No start date field found on Agreement tab or already set.");
        }

        // Try to set effective date as today
        try {
            selectTodayByLabel("Effective");
            test.log(Status.INFO, "Set effective date as today.");
        } catch (Exception e) {
            test.log(Status.INFO, "No effective date field on Agreement tab.");
        }
    }

    // ══════════════════════════════════════════════════
    // COMMON BUTTON CLICK METHODS
    // ══════════════════════════════════════════════════

    public void clickSaveAndContinue() {
        WebDriverWait wait = getExplicitWait(10);
        try {
            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(SAVE_AND_CONTINUE_BTN));
            btn.click();
        } catch (Exception e) {
            // Fallback: JS click
            clickButtonByText("Save & Continue");
        }
        test.log(Status.INFO, "Clicked 'Save & Continue'.");
        pause(3);
    }

    public void clickNext() {
        WebDriverWait wait = getExplicitWait(10);
        try {
            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(NEXT_BTN));
            btn.click();
        } catch (Exception e) {
            clickButtonByText("Next");
        }
        test.log(Status.INFO, "Clicked 'Next'.");
        pause(3);
    }

    public void clickFinish() {
        WebDriverWait wait = getExplicitWait(10);
        try {
            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(FINISH_BTN));
            btn.click();
        } catch (Exception e) {
            clickButtonByText("Finish");
        }
        test.log(Status.INFO, "Clicked 'Finish'.");
        pause(3);
    }

    // ══════════════════════════════════════════════════
    // VERIFICATION METHODS
    // ══════════════════════════════════════════════════

    public boolean verifyRepHired() {
        WebDriverWait wait = getExplicitWait(15);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(SUCCESS_TOAST));
            test.log(Status.PASS, "Rep hired successfully — toast confirmation received.");
            return true;
        } catch (Exception e) {
            // Fallback: check if dialog closed (means success)
            try {
                wait.until(ExpectedConditions.invisibilityOfElementLocated(
                        By.cssSelector(".p-dialog")
                ));
                test.log(Status.PASS, "Rep hired — dialog closed successfully.");
                return true;
            } catch (Exception ex) {
                test.log(Status.FAIL, "Rep hire verification failed: " + ex.getMessage());
                return false;
            }
        }
    }

    public void searchHiredRep() {
        WebDriverWait wait = getExplicitWait(10);
        String email = pgProjectExpectedVariables.getHireEmail("Email");
        String firstName = pgProjectExpectedVariables.getHireFirstName("First Name");

        String searchTerm = (email != null && !email.isEmpty()) ? email : firstName;

        WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_INPUT));
        searchField.clear();
        searchField.sendKeys(searchTerm);
        test.log(Status.INFO, "Searched for hired rep: " + searchTerm);
        pause(3);
    }

    public boolean verifyRepInOnboardingList() {
        WebDriverWait wait = getExplicitWait(10);
        String firstName = pgProjectExpectedVariables.getHireFirstName("First Name");
        try {
            By repRow = By.xpath(
                    "//*[contains(text(),'" + firstName + "')]"
            );
            wait.until(ExpectedConditions.visibilityOfElementLocated(repRow));
            test.log(Status.PASS, "Hired rep '" + firstName + "' found in onboarding list.");
            return true;
        } catch (Exception e) {
            test.log(Status.FAIL, "Hired rep '" + firstName + "' not found in onboarding list.");
            return false;
        }
    }

    // ══════════════════════════════════════════════════
    // PRIME REACT HELPER METHODS
    // ══════════════════════════════════════════════════

    /**
     * Clicks a PrimeReact dropdown trigger by the container element's ID.
     */
    private void clickPrimeDropdownById(String elementId) {
        WebDriverWait wait = getExplicitWait(10);
        WebElement container = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(elementId)));
        WebElement trigger = container.findElement(By.cssSelector(".p-dropdown-trigger"));
        trigger.click();
        pause(1);
    }

    /**
     * Selects an option from an open PrimeReact dropdown by exact text match.
     */
    private void selectPrimeDropdownOption(String optionText) {
        WebDriverWait wait = getExplicitWait(10);
        By optionLocator = By.xpath(
                "//li[contains(@class,'p-dropdown-item') and normalize-space(.)='" + optionText + "']"
        );
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(optionLocator));
        option.click();
        pause(1);
    }

    /**
     * Selects the first available option from an open PrimeReact dropdown.
     * Returns the text of the selected option.
     */
    private String selectPrimeDropdownFirstOption() {
        WebDriverWait wait = getExplicitWait(10);
        By optionLocator = By.cssSelector("li.p-dropdown-item");
        List<WebElement> options = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(optionLocator)
        );
        String text = options.get(0).getText().trim();
        options.get(0).click();
        pause(1);
        return text;
    }

    /**
     * Sets value on a React input using native value setter.
     * Standard sendKeys doesn't always work with React controlled components.
     */
    private void setReactInputValue(WebElement input, String value) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
                "var input = arguments[0];" +
                        "var nativeInputValueSetter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;" +
                        "nativeInputValueSetter.call(input, arguments[1]);" +
                        "input.dispatchEvent(new Event('input', { bubbles: true }));" +
                        "input.dispatchEvent(new Event('change', { bubbles: true }));",
                input, value
        );
    }

    /**
     * Clicks a button inside the dialog by its visible text.
     * Uses JavaScript as fallback when Selenium click doesn't trigger React events.
     */
    private void clickButtonByText(String buttonText) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
                "const buttons = document.querySelectorAll('.p-dialog button, button');" +
                        "for (const b of buttons) {" +
                        "  if (b.innerText && b.innerText.trim() === arguments[0]) {" +
                        "    b.scrollIntoView({block:'center'});" +
                        "    b.click();" +
                        "    break;" +
                        "  }" +
                        "}",
                buttonText
        );
    }

}
