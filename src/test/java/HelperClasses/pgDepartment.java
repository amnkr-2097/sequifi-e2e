package HelperClasses;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class pgDepartment extends pgGeneric {

    private static final By DEPARTMENT_TAB     = By.xpath("//*[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'department')]");
    private static final By CREATE_NEW_BUTTON  = By.id("Create_New_Department");
    private static final By PARENT_DROPDOWN    = By.id("Parent_Department_select_dropdown");
    private static final By DEPT_NAME_INPUT    = By.cssSelector("input[placeholder*='Enter'][placeholder*='Name']");
    private static final By CREATE_BUTTON      = By.id("Create_New_Department_Btn");
    private static final By SEARCH_INPUT       = By.id("Department_Gobal_Search_Id");
    private static final By SUCCESS_TOAST      = By.cssSelector(".toast-success, .Toastify__toast--success, [class*='success']");

    public void navigateToDepartmentTab() {
        WebDriverWait wait = getExplicitWait(15);

//        By settingsMenu = By.xpath("//a[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'settings')] | //span[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'settings')]/ancestor::a");
//        WebElement settingsLink = wait.until(ExpectedConditions.elementToBeClickable(settingsMenu));
//        settingsLink.click();
//        test.log(Status.PASS, "Clicked Settings menu.");
//        pause(2);

        WebElement deptTab = wait.until(ExpectedConditions.elementToBeClickable(DEPARTMENT_TAB));
        deptTab.click();
        test.log(Status.PASS, "Clicked Department tab.");
        pause(2);
    }

    public void clickCreateNew() {
        WebDriverWait wait = getExplicitWait(10);
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(CREATE_NEW_BUTTON));
        btn.click();
        test.log(Status.PASS, "Clicked 'Create New' department button.");
        pause(1);
    }

    public void enterDepartmentName(String dept, String name) {
        WebDriverWait wait = getExplicitWait(10);
        if (dept.contains("Parent")) {
            WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(DEPT_NAME_INPUT));
            field.clear();
            String strName = name + (int) Math.floor(Math.random() * 10000);
            field.sendKeys(strName);
            pgProjectExpectedVariables.setDeptParentDepartment("Parent Department Name", strName);
            test.log(Status.PASS, "Parent Department name set as : " + pgProjectExpectedVariables.getDeptParentDepartment("Parent Department Name"));
        }
        else if (dept.contains("Child")) {
            selectByVisibleText(PARENT_DROPDOWN, pgProjectExpectedVariables.getDeptParentDepartment("Parent Department Name"));
            pause(3);
            WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(DEPT_NAME_INPUT));
            field.clear();
            String strName = name + (int) Math.floor(Math.random() * 10000);
            field.sendKeys(strName);
            pgProjectExpectedVariables.setDeptChildDepartment("Child Department Name", strName);
            test.log(Status.PASS, "Child Department name set as : " + pgProjectExpectedVariables.getDeptChildDepartment("Child Department Name"));
        }
    }

    public void clickCreateButton() {
        WebDriverWait wait = getExplicitWait(10);
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(CREATE_BUTTON));
        btn.click();
        test.log(Status.INFO, "Clicked Create department button.");
        pause(2);
    }

    public boolean verifyDepartmentCreated(String dept) {
        WebDriverWait wait = getExplicitWait(10);

        if (dept.contains("Parent")) {
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(SUCCESS_TOAST));
                test.log(Status.PASS, "Department '" + pgProjectExpectedVariables.getDeptParentDepartment("Parent Department Name") + "' created successfully.");
                return true;
            } catch (Exception e) {
                try {
                    By deptInList = By.xpath("//*[contains(text(),'" + pgProjectExpectedVariables.getDeptParentDepartment("Parent Department Name") + "')]");
                    wait.until(ExpectedConditions.visibilityOfElementLocated(deptInList));
                    test.log(Status.PASS, "Department '" + pgProjectExpectedVariables.getDeptParentDepartment("Parent Department Name") + "' found in list.");
                    return true;
                } catch (Exception ex) {
                    test.log(Status.FAIL, "Department '" + pgProjectExpectedVariables.getDeptParentDepartment("Parent Department Name") + "' not found after creation.");
                    return false;
                }
            }
        } else if (dept.contains("Child")) {
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(SUCCESS_TOAST));
                test.log(Status.PASS, "Department '" + pgProjectExpectedVariables.getDeptChildDepartment("Child Department Name") + "' created successfully.");
                return true;
            } catch (Exception e) {
                try {
                    By deptInList = By.xpath("//*[contains(text(),'" + pgProjectExpectedVariables.getDeptChildDepartment("Child Department Name") + "')]");
                    wait.until(ExpectedConditions.visibilityOfElementLocated(deptInList));
                    test.log(Status.PASS, "Department '" + pgProjectExpectedVariables.getDeptChildDepartment("Child Department Name") + "' found in list.");
                    return true;
                } catch (Exception ex) {
                    test.log(Status.FAIL, "Department '" + pgProjectExpectedVariables.getDeptChildDepartment("Child Department Name") + "' not found after creation.");
                    return false;
                }
            }
        }
        else {
            test.log(Status.FAIL, "Department not found after creation.");
            return false;
        }
    }

    public void searchDepartment(String searchTerm) {
        WebDriverWait wait = getExplicitWait(10);
        WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_INPUT));
        if(searchTerm.contains("Parent")){
            searchField.clear();
            searchField.sendKeys(pgProjectExpectedVariables.getDeptParentDepartment("Parent Department Name"));
            test.log(Status.PASS, "Searched for Parent department: " + pgProjectExpectedVariables.getDeptParentDepartment("Parent Department Name"));
            pause(2);
        } else if (searchTerm.contains("Child")) {
            searchField.clear();
            searchField.sendKeys(pgProjectExpectedVariables.getDeptChildDepartment("Child Department Name"));
            test.log(Status.PASS, "Searched for Child department: " + pgProjectExpectedVariables.getDeptChildDepartment("Child Department Name"));
            pause(2);
        }
        else {
            test.log(Status.FAIL, "Searched for department not Present");
            pause(2);
        }
    }

    public boolean verifyDepartmentInList(String deptName) {
        WebDriverWait wait = getExplicitWait(10);
        if (deptName.contains("Parent")) {
            try {
                By deptRow = By.xpath("//tr[contains(.,'" + pgProjectExpectedVariables.getDeptParentDepartment("Parent Department Name") + "')] | //*[contains(@class,'row') and contains(.,'"
                        + pgProjectExpectedVariables.getDeptParentDepartment("Parent Department Name") + "')] | //*[contains(@class,'card') and contains(.,'"
                        + pgProjectExpectedVariables.getDeptParentDepartment("Parent Department Name") + "')]");
                wait.until(ExpectedConditions.visibilityOfElementLocated(deptRow));
                test.log(Status.PASS, "Department '" + pgProjectExpectedVariables.getDeptParentDepartment("Parent Department Name") + "' found in list.");
                return true;
            } catch (Exception e) {
                test.log(Status.FAIL, "Department '" + pgProjectExpectedVariables.getDeptParentDepartment("Parent Department Name") + "' not found in list.");
                return false;
            }
        } else if (deptName.contains("Child")) {
            try {
                By deptRow = By.xpath("//tr[contains(.,'" + pgProjectExpectedVariables.getDeptChildDepartment("Child Department Name") + "')] | //*[contains(@class,'row') and contains(.,'"
                        + pgProjectExpectedVariables.getDeptChildDepartment("Child Department Name") + "')] | //*[contains(@class,'card') and contains(.,'"
                        + pgProjectExpectedVariables.getDeptChildDepartment("Child Department Name") + "')]");
                wait.until(ExpectedConditions.visibilityOfElementLocated(deptRow));
                test.log(Status.PASS, "Department '" + pgProjectExpectedVariables.getDeptChildDepartment("Child Department Name") + "' found in list.");
                return true;
            } catch (Exception e) {
                test.log(Status.FAIL, "Department '" + pgProjectExpectedVariables.getDeptChildDepartment("Child Department Name") + "' not found in list.");
                return false;
            }
        }else {
            test.log(Status.FAIL, "Department not found in list.");
            return false;
        }
    }
}
