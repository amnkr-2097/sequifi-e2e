package HelperClasses;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class pgProduct extends pgGeneric {

    private static final By PRODUCT_TAB         = By.xpath("//*[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'product')]");
    private static final By ADD_NEW_BUTTON      = By.xpath("(//button[@id='Create_New_Position_btn'])[2]");
    private static final By NAME_INPUT          = By.cssSelector("input[name='name'][placeholder*='Product Name']");
    private static final By PRODUCT_IDS_INPUT   = By.cssSelector("input[name='product_ids']");
    private static final By DESCRIPTION_INPUT   = By.cssSelector("textarea[name='description']");
    private static final By MILESTONE_DROPDOWN  = By.cssSelector("[name='milestone_schema_id']");
    private static final By PRODUCT_REDLINE     = By.cssSelector("input[name='product_redline']");
    private static final By EFFECTIVE_DATE      = By.cssSelector("input[name='effective_date']");
    private static final By CREATE_BUTTON       = By.xpath("//button[contains(text(),'Create')]");
    private static final By SEARCH_INPUT        = By.id("position_table_Global_Search");
    private static final By SUCCESS_TOAST       = By.cssSelector(".toast-success, .Toastify__toast--success, [class*='success']");

    public void navigateToProductTab() {
        WebDriverWait wait = getExplicitWait(15);

        By settingsMenu = By.xpath("//a[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'settings')] | //span[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'settings')]/ancestor::a");
        WebElement settingsLink = wait.until(ExpectedConditions.elementToBeClickable(settingsMenu));
        settingsLink.click();
        test.log(Status.INFO, "Clicked Settings menu.");
        pause(2);

        WebElement productTab = wait.until(ExpectedConditions.elementToBeClickable(PRODUCT_TAB));
        productTab.click();
        test.log(Status.INFO, "Clicked Product tab.");
        pause(2);
    }

    public void clickAddNew() {
        WebDriverWait wait = getExplicitWait(10);
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(ADD_NEW_BUTTON));
        btn.click();
        test.log(Status.INFO, "Clicked 'Add New' product button.");
        pause(1);
    }

    public void enterProductName(String name) {
        WebDriverWait wait = getExplicitWait(10);
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(NAME_INPUT));
        field.clear();
        field.sendKeys(name);
        test.log(Status.INFO, "Entered product name: " + name);
    }

    public void enterProductId(String productId) {
        WebDriverWait wait = getExplicitWait(10);
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_IDS_INPUT));
        field.clear();
        field.sendKeys(productId);
        field.sendKeys(Keys.ENTER);
        test.log(Status.INFO, "Entered product ID: " + productId);
        pause(1);
    }

    public void enterProductDescription(String description) {
        WebDriverWait wait = getExplicitWait(10);
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(DESCRIPTION_INPUT));
        field.clear();
        field.sendKeys(description);
        test.log(Status.INFO, "Entered product description.");
    }

    public void enterProductRedline(String redline) {
        WebDriverWait wait = getExplicitWait(10);
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_REDLINE));
        field.clear();
        field.sendKeys(redline);
        test.log(Status.INFO, "Entered product redline: " + redline);
    }

    public void enterEffectiveDateToday() {
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        setReactDateField(EFFECTIVE_DATE, today);
        test.log(Status.INFO, "Entered product effective date: " + today);
    }

    public void clickCreateButton() {
        WebDriverWait wait = getExplicitWait(10);
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(CREATE_BUTTON));
        btn.click();
        test.log(Status.INFO, "Clicked Create product button.");
        pause(2);
    }

    public boolean verifyProductCreated(String productName) {
        WebDriverWait wait = getExplicitWait(10);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(SUCCESS_TOAST));
            test.log(Status.PASS, "Product '" + productName + "' created successfully.");
            return true;
        } catch (Exception e) {
            try {
                By productInList = By.xpath("//*[contains(text(),'" + productName + "')]");
                wait.until(ExpectedConditions.visibilityOfElementLocated(productInList));
                test.log(Status.PASS, "Product '" + productName + "' found in list.");
                return true;
            } catch (Exception ex) {
                test.log(Status.FAIL, "Product '" + productName + "' not found after creation.");
                return false;
            }
        }
    }

    public void searchProduct(String searchTerm) {
        WebDriverWait wait = getExplicitWait(10);
        WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_INPUT));
        searchField.clear();
        searchField.sendKeys(searchTerm);
        test.log(Status.INFO, "Searched for product: " + searchTerm);
        pause(2);
    }

    public boolean verifyProductInList(String productName) {
        WebDriverWait wait = getExplicitWait(10);
        try {
            By productRow = By.xpath("//tr[contains(.,'" + productName + "')] | //*[contains(@class,'row') and contains(.,'" + productName + "')] | //*[contains(@class,'card') and contains(.,'" + productName + "')]");
            wait.until(ExpectedConditions.visibilityOfElementLocated(productRow));
            test.log(Status.PASS, "Product '" + productName + "' found in list.");
            return true;
        } catch (Exception e) {
            test.log(Status.FAIL, "Product '" + productName + "' not found in list.");
            return false;
        }
    }
}
