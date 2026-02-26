package HelperClasses;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class pgLogin extends pgGeneric {

    private static final By EMAIL_FIELD        = By.cssSelector("input[name='email'], input[type='email']");
    private static final By PASSWORD_FIELD     = By.cssSelector("input[name='password'], input[type='password']");
    private static final By LOGIN_BUTTON       = By.cssSelector("button[type='submit']");
    private static final By PROFILE_MENU       = By.xpath("//div[@data-kt-menu-attach = 'parent']/div");
    private static final By LOGOUT_BUTTON      = By.xpath("//*[contains(text(),'Logout') or contains(text(),'Log Out') or contains(text(),'Sign Out')]");
    private static final By DASHBOARD_INDICATOR = By.xpath("//*[contains(text(),'Dashboard') or contains(text(),'dashboard')]");
    private static final By ERROR_MESSAGE      = By.cssSelector(".alert-danger, .error-message, .toast-error, [role='alert'], .text-danger");
    private static final By ERROR_MESSAGE_INVALID_EMAIL_PWD     = By.xpath("//div[@class='text-sqError500 text-sm-regular']");
    private static final By SWITCH_TO_ADMIN    = By.xpath("//*[contains(text(),'switch to admin') or contains(text(),'Switch to Admin')]");
    private static final By SWITCH_TO_STANDARD = By.xpath("//*[contains(text(),'switch to standard') or contains(text(),'Switch to Standard')]");

    public void log_in(String userID, String password) {
        WebDriverWait wait = getExplicitWait(20);

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(EMAIL_FIELD));
        emailField.clear();
        emailField.sendKeys(userID);
        test.log(Status.INFO, "Entered user ID: " + userID);

        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(PASSWORD_FIELD));
        passwordField.clear();
        passwordField.sendKeys(password);
        test.log(Status.INFO, "Entered password: " + password);

        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(LOGIN_BUTTON));
        loginBtn.click();
        test.log(Status.INFO, "Clicked Sign In button.");
    }

    public boolean verifyLoggedIn() {
        try {
            WebDriverWait wait = getExplicitWait(15);
            wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(DASHBOARD_INDICATOR),
                ExpectedConditions.visibilityOfElementLocated(PROFILE_MENU)
            ));
            test.log(Status.PASS, "User is logged in — dashboard or profile menu visible.");
            return true;
        } catch (Exception e) {
            test.log(Status.FAIL, "Login verification failed: " + e.getMessage());
            return false;
        }
    }

    public void logOut() {
        WebDriverWait wait = getExplicitWait(15);

        WebElement profileMenu = wait.until(ExpectedConditions.elementToBeClickable(PROFILE_MENU));
        profileMenu.click();
        test.log(Status.INFO, "Opened profile menu.");

        pause(1);

        WebElement logoutBtn = wait.until(ExpectedConditions.elementToBeClickable(LOGOUT_BUTTON));
        logoutBtn.click();
        test.log(Status.PASS, "Clicked Logout.");
    }

    public boolean verifyLoginErrorDisplayed() {
        WebDriverWait wait = getExplicitWait(10);
        try {
            WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(ERROR_MESSAGE));
            String errorText = error.getText();
            test.log(Status.PASS, "Login error displayed: " + errorText);
            return true;
        } catch (Exception e) {
            WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(ERROR_MESSAGE_INVALID_EMAIL_PWD));
            String errorText = error.getText();
            boolean stillOnLogin = driver.getCurrentUrl().contains("auth") || driver.getCurrentUrl().contains("login");
            if (stillOnLogin) {
                test.log(Status.PASS, "Login failed as expected — still on login page.");
                test.log(Status.INFO, "Login error displayed: " + errorText);
                return true;
            }
            test.log(Status.FAIL, "No error message and not on login page.");
            return false;
        }
    }

    public boolean verifyOnLoginPage() {
        try {
            WebDriverWait wait = getExplicitWait(10);
            wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("auth"),
                ExpectedConditions.urlContains("login"),
                ExpectedConditions.visibilityOfElementLocated(EMAIL_FIELD)
            ));
            test.log(Status.PASS, "On login page. URL: " + driver.getCurrentUrl());
            return true;
        } catch (Exception e) {
            test.log(Status.FAIL, "Not on login page. URL: " + driver.getCurrentUrl());
            return false;
        }
    }

    public boolean verifyModuleVisible(String moduleName) {
        WebDriverWait wait = getExplicitWait(10);
        try {

            String xPathModule = "//*[contains(@class,'nav') or contains(@class,'menu') or contains(@class,'sidebar')]" +
                    "//*[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'" +
                    moduleName.toLowerCase() + "')]";

            if(moduleName.equalsIgnoreCase("Hiring")||moduleName.equalsIgnoreCase("Referrals")||
                    moduleName.equalsIgnoreCase("My Earnings")||moduleName.equalsIgnoreCase("Calender")||
                    moduleName.equalsIgnoreCase("Management")||moduleName.equalsIgnoreCase("Requests & Approvals"))
            {
                pause(5);
                wait.until(ExpectedConditions.visibilityOfElementLocated(SWITCH_TO_STANDARD)).click();
                test.log(Status.INFO,"Toggle Switched to Admin");
                pause(3);
                By moduleLocator = By.xpath(xPathModule);
                wait.until(ExpectedConditions.visibilityOfElementLocated(moduleLocator));
                test.log(Status.PASS, "Module '" + moduleName + "' is visible.");
                return true;

            }
            else
            {
                By moduleLocator = By.xpath(xPathModule);
                wait.until(ExpectedConditions.visibilityOfElementLocated(moduleLocator));
                test.log(Status.PASS, "Module '" + moduleName + "' is visible.");
                return true;
            }

        } catch (Exception e) {
            test.log(Status.WARNING, "Module '" + moduleName + "' not found: " + e.getMessage());
            return false;
        }
    }

    public void navigatesToModule(String moduleName) {
        WebDriverWait wait = getExplicitWait(15);
        try {

            String xPathModule = "//a[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'" +
                    moduleName.toLowerCase() + "')] | " +
                    "//span[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'" +
                    moduleName.toLowerCase() + "')]/ancestor::a";
            if(moduleName.equalsIgnoreCase("Hiring")||moduleName.equalsIgnoreCase("Referrals")||
                    moduleName.equalsIgnoreCase("My Earnings")||moduleName.equalsIgnoreCase("Calender")||
                    moduleName.equalsIgnoreCase("Management")||moduleName.equalsIgnoreCase("Requests & Approvals"))
            {
                pause(5);
                wait.until(ExpectedConditions.visibilityOfElementLocated(SWITCH_TO_STANDARD)).click();
                test.log(Status.INFO,"Toggle Switched to Admin");
                pause(3);
                By moduleLink = By.xpath(xPathModule);
                WebElement link = wait.until(ExpectedConditions.elementToBeClickable(moduleLink));
                link.click();
                test.log(Status.INFO, "Clicked on '" + moduleName + "' module.");
                pause(2);
            }
            else
            {
                By moduleLink = By.xpath(xPathModule);
                WebElement link = wait.until(ExpectedConditions.elementToBeClickable(moduleLink));
                link.click();
                test.log(Status.INFO, "Clicked on '" + moduleName + "' module.");
                pause(2);
            }
        } catch (Exception e) {
            test.log(Status.FAIL, "Could not navigate to '" + moduleName + "': " + e.getMessage());
            throw new AssertionError("Navigation to " + moduleName + " failed.");
        }
    }
}
