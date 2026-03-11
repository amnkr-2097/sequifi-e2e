package HelperClasses;

import com.aventstack.extentreports.Status;
import io.cucumber.java.it.Ma;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

public class pgAddingSales extends pgGeneric {

    // ══════════════ NAVIGATION ══════════════
    private static final By SWITCH_TO_ADMIN = By.xpath("(//*[contains(text(),'switch to admin') or contains(text(),'Switch to Admin')])[1]");
    private static final By SWITCH_TO_STANDARD = By.xpath("(//*[contains(text(),'switch to standard') or contains(text(),'Switch to Standard')])[1]");
    private static final By REPORT_MODULE = By.xpath("//*[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'reports')]");
    private static final By SALES_MODULE = By.xpath("//*[@id='Reports_Header_Sales']");
    private static final By ADD_SALE_BUTTON = By.xpath("//*[@id='Sales_Table_Add_Sales_Btn']");

    // ══════════════ FORM FIELDS ══════════════\

    private static final By REP_ID = By.xpath("//*[@placeholder='Enter ID']");
    private static final By REP_CUSTOMER_NAME = By.xpath("//*[@id= 'Manage_Sales_Customer_Name_Input']");
    private static final By REP_CUSTOMER_STATE = By.xpath("//*[@id = 'Manage_Sales_Customer_State_Input']");
    private static final By REP_LOCATION_CODE = By.xpath("//*[@id='Manage_Sales_General_Code_Input']");
    private static final By PRODUCT_DROPDOWN = By.xpath("//select[@name='product_id']/ancestor::div[@data-pc-name='dropdown']");
    private static final By REP_KW = By.xpath("//*[@id = 'Manage_Sales_KW_Input']");
    private static final By REP_EPC = By.xpath("//*[@id = 'Manage_Sales_Customer_Epc_Input']");
    private static final By REP_NET_EPC = By.xpath("//*[@placeholder ='Enter Net EPC']");
    private static final By REP_CLOSER = By.xpath("//*[@id='Manage_Sales_Rep_id_Input']");
    private static final By REP_SETTER = By.xpath("//*[@id='Manage_Sales_Setter_1_Input']");

    private static final By REP_SALE_DATE = By.xpath("//*[@id = 'Manage_Sales_Approved_Date_Input']//input");
    private static final By REP_M1_DATE = By.xpath("//*[@name = 'M1 Date']");
    private static final By REP_FINAL_PAYMENT = By.xpath("//*[@name = 'Final Payment']");
    private static final By REP_SAVE_SALE = By.xpath("//*[@id= 'Manage_Sales_Save_sale_Btn']//span");

    private static final By SUBMIT_BUTTON = By.xpath(
            "//button[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'submit') or " +
                    "contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'save')]"
    );

    // ══════════════ FEEDBACK ══════════════
    private static final By SUCCESS_TOAST = By.cssSelector(".toast-success, .Toastify__toast--success, [class*='success']");
    private static final By SEARCH_INPUT = By.cssSelector("input[type='search'], input[placeholder*='Search']");
    private static final By COMMISSION_CELL = By.xpath("//td[contains(@class,'commission')] | //*[contains(text(),'Commission')]//following::td[1]");

    private static final By TOTAL_COMMISSION = By.xpath("(//span[contains(text(),'Total Commission')]/ancestor::div[contains(@class,'row col')]//span[contains(@class,'text-sm-semibold')])[1]");
    private static final By FINAL_PAYMENT = By.xpath("(//span[contains(text(),'Final Payment')]/ancestor::div[contains(@class,'row col')]//span[contains(@class,'text-sm-semibold')])[1]");
    private static final By COMP_RATE  = By.xpath("(//span[contains(text(),'Comp Rate')]/ancestor::div[contains(@class,'row col')]//span[contains(@class,'text-sm-semibold')]/span)[1]");
    private static final By ID_ON_SEARCH = By.xpath("//tr[.//a[contains(@id, 'Sales_Table_Redirect_To_Customer_Info_')]]");


    // ══════════════ NAVIGATION METHODS ══════════════
    WebDriverWait wait = getExplicitWait(10);

    public void navigateToSalesPage() {
//        pause(1);
        click(SWITCH_TO_ADMIN);
        pause(1);
        click(REPORT_MODULE);
        pause(1);
        click(SALES_MODULE);
        pause(1);
    }

    public void clickAddSale() {
        pause(2);
        wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_SALE_BUTTON)).click();
        pause(2);
    }

    // ══════════════ FORM FILL METHODS ══════════════

    public void enterSalesData(String option, String value) {
        switch (option.toUpperCase()) {
            case "KW":
                wait.until(ExpectedConditions.visibilityOfElementLocated(REP_KW)).sendKeys(value);
                pgProjectExpectedVariables.setSaleKW("KW", value);
                test.log(Status.PASS, "KW is set as: " + value);
                break;
            case "NET EPC":
                wait.until(ExpectedConditions.visibilityOfElementLocated(REP_NET_EPC)).sendKeys(value);
                pgProjectExpectedVariables.setSaleNetEPC("Net EPC", value);
                test.log(Status.PASS, "Net EPC is set as: " + value);
                break;
            case "EPC":
                wait.until(ExpectedConditions.visibilityOfElementLocated(REP_EPC)).sendKeys(value);
                pgProjectExpectedVariables.setSaleEPC("EPC", value);
                test.log(Status.PASS, "EPC is set as: " + value);
                break;
            case "CUSTOMER NAME":
                String name = value + (int) (Math.random() * 10000);
                wait.until(ExpectedConditions.visibilityOfElementLocated(REP_CUSTOMER_NAME)).sendKeys(name);
                pgProjectExpectedVariables.setSaleCustomerName("Customer Name", name);
                test.log(Status.PASS, "Customer Name is set as: " + name);
                break;
            case "ID":
                String value1 = value + (int) (Math.random() * 10000);
                wait.until(ExpectedConditions.visibilityOfElementLocated(REP_ID)).sendKeys(value1);
                pgProjectExpectedVariables.setSaleID("ID", value1);
                test.log(Status.PASS, "ID is set as: " + value1);
                break;
            default:
                test.log(Status.PASS, "No Element Found");
                break;

        }
    }

    public void enterDate(String date) {
        pause(2);
        switch (date.toUpperCase()) {
            case "SALE DATE":
                selectPastDate(REP_SALE_DATE, 12);
                pause(2);
                break;
            case "M1 DATE":
                selectPastDate(REP_M1_DATE, 10);
                pause(2);
                break;
            case "FINAL PAYMENT":
                selectPastDate(REP_FINAL_PAYMENT, 8);
                pause(2);
                break;
            default:
                test.log(Status.INFO, "No element Present");
                break;
        }
    }

    public void selectSetterCloser(String value) {
//        String name = pgProjectExpectedVariables.getHireFirstName("First Name") + " " +pgProjectExpectedVariables.getHireLastName("Last Name");
        String name = "QA_First80 Last70";
                switch (value.toUpperCase()) {
            case "SETTER":
                selectByPartialText(REP_SETTER, name);
                test.log(Status.PASS, "Setter selected as : " + name);
                break;
            case "CLOSER":
                selectByPartialText(REP_CLOSER, name);
                test.log(Status.PASS, "Closer selected as : " + name);
                break;
            default:
        }
    }


    public void selectProduct() {
        String prodName = pgProjectExpectedVariables.getProdName("Product Name");
        selectByPartialText(PRODUCT_DROPDOWN, prodName);
//        selectByPartialText(PRODUCT_DROPDOWN, "QA Panel80");
//        test.log(Status.PASS, "Selected product: " + "ABC");
        test.log(Status.PASS, "Selected product: " + prodName);
    }

    public void selectLocation() {
        // Customer State
        pause(1);
        selectByVisibleText(REP_CUSTOMER_STATE, "California");
        // Location Code
        pause(1);
        selectByVisibleText(REP_LOCATION_CODE, "California | CA-TEST01460");

    }

    public void clickSubmit() {
        pause(2);
         wait.until(ExpectedConditions.elementToBeClickable(REP_SAVE_SALE)).click();
        test.log(Status.INFO, "Clicked Submit sale button.");
        pause(1);
    }

    // ══════════════ VERIFICATION METHODS ══════════════

    public boolean verifySaleCreated() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(SUCCESS_TOAST));
            test.log(Status.PASS, "Sale created successfully — toast received.");
            return true;
        } catch (Exception e) {
            test.log(Status.FAIL, "Sale creation verification failed: " + e.getMessage());
            return false;
        }
    }

    public void searchSale() {
        WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_INPUT));
        search.clear();
        search.sendKeys(pgProjectExpectedVariables.getSaleID("ID"));
//        search.sendKeys("102020202");
        pause(2);
    }

    public boolean verifySaleInList() {
        pause(2);
        String id = pgProjectExpectedVariables.getSaleID("ID");
//        String id = "102020202";
        try {
            By saleRow = By.xpath("//*[contains(text(),'" + id + "')]");
            wait.until(ExpectedConditions.visibilityOfElementLocated(saleRow));
            test.log(Status.PASS, "Sale for '" + id + "' found in list.");
            return true;
        } catch (Exception e) {
            test.log(Status.FAIL, "Sale for '" + id + "' not found.");
            return false;
        }
    }

    public void opensTheSale() {
            pause(1);

        String strID = pgProjectExpectedVariables.getSaleID("ID");
//        String strID = "102020202";

        By saleRow = By.xpath("(//tr[contains(.,'" + strID + "')] | //*[contains(text(),'" + strID + "')])[2]");
        wait.until(ExpectedConditions.elementToBeClickable(saleRow)).click();
        test.log(Status.INFO, "Clicked on sale row for: " + strID);
        pause(3);
    }


    // ══════════════ COMMISSION VALIDATION ══════════════

    /**
     * Validates commission based on KW: Value =({[Net EPC - Calculated RedLine] * CM} * KW * 1000 * Commission) / 100
     * Reads the actual commission from the sale row and compares with expected.
     */
    public boolean validateCommission() {

        pause(2);
        int intExpectedCommission;
        String  strExpectedRedline;
        String  strCompRate;
        String strActualFinalPayment;
        String strActualCommission;
        String strExpectedCommission;
        int netEPC = Integer.parseInt(pgProjectExpectedVariables.getSaleNetEPC("Net EPC"));
        int commission = Integer.parseInt(pgProjectExpectedVariables.getPosSetupCommAmountSelfGen("Commission Amount Self Gen"));
        int kw = Integer.parseInt(pgProjectExpectedVariables.getSaleKW("KW"));
        int redline = Integer.parseInt(pgProjectExpectedVariables.getHireSelfGenRedline("SelfGen Redline"));

//        int netEPC = 100;
//        int commission = 10;
//        int kw = 40;
//        int redline = 20;

        //Expected Total Commission And Final Payment
        intExpectedCommission = (((netEPC - redline)) * kw * 1000 * commission) / 100;
        strExpectedCommission = intExpectedCommission + ".00";
        test.log(Status.PASS, "Expected Commission Calculated" + strExpectedCommission);

        //Redline
        strExpectedRedline = redline + ".00";
        test.log(Status.PASS, "Expected Commission Calculated" + strExpectedRedline);

        //$ 320,000.00 - Total Commission
        strActualCommission = wait.until(ExpectedConditions.visibilityOfElementLocated(TOTAL_COMMISSION)).getText();
        test.log(Status.PASS, "Actual Total Commission present in Application" + strActualCommission);
        strActualCommission = strActualCommission.replace("$ ", "").replace(",", "");

        //$ 320,000.00 - Final Payment
        strActualFinalPayment = wait.until(ExpectedConditions.visibilityOfElementLocated(FINAL_PAYMENT)).getText();
        test.log(Status.PASS, "Actual Final Payment present in Application" + strActualFinalPayment);
        strActualFinalPayment = strActualFinalPayment.replace("$ ", "").replace(",", "");

        //$20 | Fixed -> Comp Rate -> Redline
        strCompRate = wait.until(ExpectedConditions.visibilityOfElementLocated(COMP_RATE)).getText();
        test.log(Status.PASS, "Actual Final Payment present in Application" + strCompRate);
        strCompRate = strCompRate.replace("$ ", "").replace(",", "");

        compareAndLog(new String[][] {
                {"Total Commission", strExpectedCommission, strActualCommission},
                {"Final Payment",    strExpectedCommission, strActualCommission},
                {"Redline", strExpectedRedline, strCompRate}
        });

        return true;
    }

}
