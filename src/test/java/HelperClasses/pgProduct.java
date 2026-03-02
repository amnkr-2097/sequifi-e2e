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
    private static final By MILESTONE_DROPDOWN  = By.xpath("//*[@name='milestone_schema_id']/ancestor::div[@data-pc-name='dropdown']");
    private static final By PRODUCT_REDLINE     = By.cssSelector("input[name='product_redline']");
    private static final By OVERRIDE_ELIGIBILITY = By.cssSelector("div[id='Override_Eligiblity_Dropdown']");
    private static final By EFFECTIVE_DATE      = By.cssSelector("input[name='effective_date']");
    private static final By CREATE_BUTTON       = By.xpath("//*[contains(text(),'Create')]");
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
        refreshPage();
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(ADD_NEW_BUTTON));
        btn.click();
        test.log(Status.INFO, "Clicked 'Add New' product button.");
        pause(1);
    }

    public void enterProductName(String name) {
        WebDriverWait wait = getExplicitWait(10);
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(NAME_INPUT));
        field.clear();
        String StrName  = name + (int) Math.floor(Math.random() * 100);
        field.sendKeys(StrName);
        pgProjectExpectedVariables.setProdName("Product Name" , StrName);
        test.log(Status.INFO, "Entered product name: " + name);
    }

    public void enterProductId(String productId) {
        WebDriverWait wait = getExplicitWait(10);
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_IDS_INPUT));
        field.clear();
        String StrID  = productId + (int) Math.floor(Math.random() * 1000);
        field.sendKeys(StrID);
        field.sendKeys(StrID);
        field.sendKeys(Keys.ENTER);
        pgProjectExpectedVariables.setProdProductId("Product ID", StrID);
        test.log(Status.INFO, "Entered product ID: " + StrID);
        pause(1);
    }

    public void enterProductDescription(String description) {
        WebDriverWait wait = getExplicitWait(10);
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(DESCRIPTION_INPUT));
        field.clear();
        String strDes = description + (int) Math.floor(Math.random()*100);
        field.sendKeys(strDes);
        pgProjectExpectedVariables.setProdDescription("Product Description", strDes);
        test.log(Status.INFO, "Entered product description: " + strDes);
    }

    public void enterProductRedline(String redline) {
        WebDriverWait wait = getExplicitWait(10);
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_REDLINE));
        field.clear();
        field.sendKeys(redline);
        pgProjectExpectedVariables.setProdRedline("Product Redline", redline);
        test.log(Status.INFO, "Entered product redline: " + redline);
    }

    public void selectMilestoneSchema() {
        selectByPartialText(MILESTONE_DROPDOWN, pgProjectExpectedVariables.getMileSchemaName("Milestone Schema Name"));
        test.log(Status.PASS, "Milestone Schema is Set for the product as: " + pgProjectExpectedVariables.getMileSchemaName("Milestone Schema Name"));
    }


    public void selectOverrideEligibility() {
        selectByVisibleText(OVERRIDE_ELIGIBILITY, pgProjectExpectedVariables.getMileMilestoneName_Initial("Milestone Trigger Name 1"));
        test.log(Status.PASS, "Override Eligibility is Set for the product as: " + pgProjectExpectedVariables.getMileMilestoneName_Initial("Milestone Trigger Name 1"));
    }

    public void enterEffectiveDateToday() {
        selectPastDate(EFFECTIVE_DATE,14);
        pause(2);
        pgProjectExpectedVariables.setProdEffectiveDate("Product Effective Date", daysFromToday(-14));
        test.log(Status.PASS, "Entered product effective date: " + daysFromToday(-14));
    }

    public void clickCreateButton() {
        WebDriverWait wait = getExplicitWait(10);
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(CREATE_BUTTON));
        btn.click();
        test.log(Status.INFO, "Clicked Create product button.");
        pause(2);
    }

    public boolean verifyProductCreated() {
        WebDriverWait wait = getExplicitWait(10);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(SUCCESS_TOAST));
            test.log(Status.PASS, "Product '" + pgProjectExpectedVariables.getProdName("Product Name") + "' created successfully.");
            return true;
        } catch (Exception e) {
            try {
                By productInList = By.xpath("//*[contains(text(),'" + pgProjectExpectedVariables.getProdName("Product Name") + "')]");
                wait.until(ExpectedConditions.visibilityOfElementLocated(productInList));
                test.log(Status.PASS, "Product '" + pgProjectExpectedVariables.getProdName("Product Name") + "' found in list.");
                return true;
            } catch (Exception ex) {
                test.log(Status.FAIL, "Product '" + pgProjectExpectedVariables.getProdName("Product Name") + "' not found after creation.");
                return false;
            }
        }
    }

    public void searchProduct() {
        WebDriverWait wait = getExplicitWait(10);
        WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_INPUT));
        searchField.clear();
        searchField.sendKeys(pgProjectExpectedVariables.getProdName("Product Name"));
        test.log(Status.PASS, "Searched for product: " + pgProjectExpectedVariables.getProdName("Product Name"));
        pause(2);
    }

    public boolean verifyProductInList() {
        WebDriverWait wait = getExplicitWait(10);
        try {
            By productRow = By.xpath("//tr[contains(.,'" + pgProjectExpectedVariables.getProdName("Product Name") + "')] | //*[contains(@class,'row') and contains(.,'"
                    + pgProjectExpectedVariables.getProdName("Product Name") + "')] | //*[contains(@class,'card') and contains(.,'"
                    + pgProjectExpectedVariables.getProdName("Product Name") + "')]");
            wait.until(ExpectedConditions.visibilityOfElementLocated(productRow));
            test.log(Status.PASS, "Product '" + pgProjectExpectedVariables.getProdName("Product Name") + "' found in list.");
            return true;
        } catch (Exception e) {
            test.log(Status.FAIL, "Product '" + pgProjectExpectedVariables.getProdName("Product Name") + "' not found in list.");
            return false;
        }
    }
}
