package HelperClasses;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.format.DateTimeFormatter;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class pgGeneric {

    protected static WebDriver driver;
    protected static ExtentReports report;
    protected static ExtentTest test;
    protected static ExtentSparkReporter sparkReporter;
    protected static WebDriverWait wait;
    protected static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    public static void initReport(String testName, String[] tags) {
        if (report == null) {
            report = new ExtentReports();
            sparkReporter = new ExtentSparkReporter("Reports/report.html");
            sparkReporter.config().setDocumentTitle("Sequifi E2ETC100 Regression Suite");
            sparkReporter.config().setReportName("E2ETC100 Test Results");
            report.attachReporter(sparkReporter);
        }
        test = report.createTest(testName);
    }

    public static void flushReport() {
        if (report != null) {
            report.flush();
        }
    }

    public static void initDriver() {
        if (driver == null || driver.toString().contains("null")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--disable-notifications");
            options.addArguments("--remote-allow-origins=*");

            // Headless mode for CI (GitHub Actions)
            String ci = System.getenv("CI");
            if (ci != null && ci.equalsIgnoreCase("true")) {
                options.addArguments("--headless=new");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--disable-gpu");
            }

            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        }
    }

    public static WebElement getElement(String xpath, String text) {
        String resolvedXpath = xpath.replace("[%s]", text);
        return getExplicitWait(10).until(ExpectedConditions.elementToBeClickable(
            By.xpath(resolvedXpath)
        ));
    }

    public static void click(By locator) {
        WebDriverWait wait = getExplicitWait(10);
        WebElement clickLocator  = wait.until(ExpectedConditions.elementToBeClickable(locator));
        clickLocator.click();
        test.log(Status.INFO, "Clicked : " + clickLocator.getText() );

    }

    public static void refreshPage() {
        driver.navigate().refresh();
    }

    public static void refreshPageShortcut() {
        ((JavascriptExecutor) driver).executeScript("location.reload()");
        pause(3);
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public static WebDriverWait getExplicitWait(int timeInSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
    }

    public static void pause(int timeInSeconds) {
        try {
            Thread.sleep(timeInSeconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Sets a date value on a PrimeReact Calendar input field.
     * Standard sendKeys doesn't work because React ignores native DOM changes.
     * This uses JS to set the value via React's internal value setter and
     * dispatches proper events. Falls back to clicking today on the calendar overlay.
     */
    public static void setReactDateField(By dateFieldLocator, String dateValue) {
        WebDriverWait wait = getExplicitWait(10);
        WebElement dateField = wait.until(ExpectedConditions.visibilityOfElementLocated(dateFieldLocator));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            // Use React's internal value setter to bypass the synthetic event system
            js.executeScript(
                "var input = arguments[0];" +
                "var nativeInputValueSetter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;" +
                "nativeInputValueSetter.call(input, arguments[1]);" +
                "input.dispatchEvent(new Event('input', { bubbles: true }));" +
                "input.dispatchEvent(new Event('change', { bubbles: true }));",
                dateField, dateValue
            );
            pause(1);

            String actualValue = dateField.getDomAttribute("value");
            if (actualValue != null && !actualValue.isEmpty()) {
                if (test != null) test.log(Status.INFO, "Set date via JS: " + actualValue);
                return;
            }
        } catch (Exception ignored) {
        }

        // Fallback: click the field to open the calendar, then click today's date
        try {
            dateField.click();
            pause(1);

            int today = LocalDate.now().getDayOfMonth();
            By todayCell = By.xpath(
                "//td[contains(@class,'p-datepicker-today')]//span | " +
                "//td[@data-date='" + today + "' and not(contains(@class,'disabled'))]//span"
            );
            WebElement todayBtn = wait.until(ExpectedConditions.elementToBeClickable(todayCell));
            todayBtn.click();
            if (test != null) test.log(Status.INFO, "Selected today from calendar overlay.");
            pause(1);
        } catch (Exception e) {
            // Last resort: triple-click to select all, then type
            dateField.click();
            js.executeScript("arguments[0].select();", dateField);
            dateField.sendKeys(dateValue);
            dateField.sendKeys(org.openqa.selenium.Keys.TAB);
            if (test != null) test.log(Status.WARNING, "Used select-all + sendKeys fallback for date.");
        }
    }

//    ----------------------------

    public void selectByVisibleText(By locator, String visibleText) {
        WebDriverWait wait = getExplicitWait(10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));

        if (element.getTagName().equalsIgnoreCase("select")) {
            // Native <select>
            new Select(element).selectByVisibleText(visibleText);
        } else {
            // Custom dropdown — scroll into view first to avoid sticky bar interception
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", element);
            pause(1);
            element.click();
            pause(1);
            WebElement option = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//li[normalize-space(.)='" + visibleText + "']")
            ));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
        }

        test.log(Status.INFO, "Selected: " + visibleText);
    }

    public void selectDropdownFromOption(By dropdownTrigger, String optionText) {
        WebDriverWait wait = getExplicitWait(10);
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(dropdownTrigger));
        dropdown.click();

        WebElement option = findOption(dropdown, optionText);
        option.click();

        test.log(Status.INFO, "Selected: " + optionText);
    }

    public void selectByPartialText(By locator, String partialText) {
        WebDriverWait wait = getExplicitWait(10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//li[contains(normalize-space(.), '" + partialText + "')]")
        )).click();
        test.log(Status.INFO, "Selected (partial match): " + partialText);
    }


    public String selectRandom(By dropdownTrigger) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(dropdownTrigger));
        dropdown.click();

        List<WebElement> options = findAllOptions(dropdown);

        WebElement picked = options.get(new Random().nextInt(options.size()));
        String text = picked.getText().trim();
        picked.click();

        test.log(Status.INFO, "Randomly selected: " + text);
        return text;
    }

    private WebElement findOption(WebElement dropdown, String optionText) {
        // Strategy 1: aria-controls link
        String panelId = dropdown.getDomAttribute("aria-controls");
        if (panelId != null && !panelId.isEmpty()) {
            return wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id='" + panelId + "']//li[normalize-space(.)='" + optionText + "']")
            ));
        }

        // Strategy 2: role=listbox (common in ARIA-compliant frameworks)
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@role='listbox']//li[normalize-space(.)='" + optionText + "']")
            ));
        } catch ( Exception e) {}

        // Strategy 3: any visible ul > li
        return wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//ul[not(contains(@style,'display: none'))]//li[normalize-space(.)='" + optionText + "']")
        ));
    }

    private List<WebElement> findAllOptions(WebElement dropdown) {
        String panelId = dropdown.getDomAttribute("aria-controls");
        if (panelId != null && !panelId.isEmpty()) {
            return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                    By.xpath("//*[@id='" + panelId + "']//li")
            ));
        }

        try {
            return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                    By.xpath("//*[@role='listbox']//li")
            ));
        } catch (TimeoutException ignored) {}

        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("//ul[not(contains(@style,'display: none'))]//li")
        ));
    }

    // ----------------------------------------------------Date Method--------------------------------------------------
    /**
        If date to be ahead of today then enter +ve integer
        If date to be behind of today then enter -ve integer
     **/
    public static String today() {
        return LocalDate.now().format(DATE_FORMAT);
    }

    public static String daysFromToday(int days) {
        return LocalDate.now().plusDays(days).format(DATE_FORMAT);
    }

    public static String weeksFromToday(int weeks) {
        return LocalDate.now().plusWeeks(weeks).format(DATE_FORMAT);
    }

    public static String monthsFromToday(int months) {
        return LocalDate.now().plusMonths(months).format(DATE_FORMAT);
    }

    // ---- Core: enter date into PrimeReact Calendar input ----

    public void enterDate(By fieldLocator, String date) {
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement input = wait1.until(ExpectedConditions.elementToBeClickable(fieldLocator));
        input.click();
        input.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        input.sendKeys(date);
        input.sendKeys(Keys.TAB);
    }

    // ---- Convenience: find by label text ----

    public void enterDateByLabel(String label, String date) {
        By inputLocator = By.xpath(
                "//label[normalize-space(.)='" + label + "']//input | " +
                        "//*[normalize-space(text())='" + label + "']/ancestor::div[1]//input | " +
                        "//*[normalize-space(text())='" + label + "']/following::input[1]"
        );
        enterDate(inputLocator, date);
    }

    // ---- Shortcut methods ----

    public void selectToday(By fieldLocator) {
        enterDate(fieldLocator, today());
        test.log(Status.INFO, "Entered date: " + today());
    }

    public void selectPastDate(By fieldLocator, int daysBack) {
        enterDate(fieldLocator, daysFromToday(-daysBack));
        test.log(Status.INFO, "Entered date: " + daysFromToday(-daysBack));
    }

    public void selectFutureDate(By fieldLocator, int daysAhead) {
        enterDate(fieldLocator, daysFromToday(daysAhead));
        test.log(Status.INFO, "Entered date: " + daysFromToday(daysAhead));
    }

    public void selectTodayByLabel(String label) {
        enterDateByLabel(label, today());
        test.log(Status.INFO, "Entered date: " + today());
    }

    public void selectPastDateByLabel(String label, int daysBack) {
        enterDateByLabel(label, daysFromToday(-daysBack));
        test.log(Status.INFO, "Entered date: " + daysFromToday(-daysBack));
    }

    public void selectFutureDateByLabel(String label, int daysAhead) {
        enterDateByLabel(label, daysFromToday(daysAhead));
        test.log(Status.INFO, "Entered date: " + daysFromToday(daysAhead));
    }


    //-----------------------------------Switch Frame -----------------------------------------
    /**
     * Switch into a frame by its name or id attribute
     */
    public static void switchToFrameByNameOrId(String nameOrId) {
        WebDriverWait wait = getExplicitWait(10);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameOrId));
        test.log(Status.INFO, "Switched to frame by name/id: " + nameOrId);
    }

    /**
     * Switch into a frame by locating its WebElement first
     */
    public static void switchToFrameByLocator(By frameLocator) {
        WebDriverWait wait = getExplicitWait(10);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
        test.log(Status.INFO, "Switched to frame by locator: " + frameLocator);
    }

    /**
     * switch to given locator
     */
    public static void switchToLocator(By locator) {
        WebDriverWait wait = getExplicitWait(10);
        WebElement goLocator = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        goLocator.click();
        test.log(Status.INFO, "Switched to locator: " + locator);
    }

    /**
     * Switch back to the main page (exit all frames)
     */
    public static void switchToDefaultContent() {
        driver.switchTo().defaultContent();
        test.log(Status.INFO, "Switched back to default content (main page).");
    }

    /**
     * Switch to the immediate parent frame
     * (use when nested inside multiple frames)
     */
    public static void switchToParentFrame() {
        driver.switchTo().parentFrame();
        test.log(Status.INFO, "Switched to parent frame.");
    }

    // ── Toggle helpers ────────────────────────────────────────────────────────

    public boolean isToggleOn(By toggleLocator) {
        WebElement toggle = driver.findElement(toggleLocator);
        String cls = toggle.getDomAttribute("aria-checked");
        return cls != null && (cls.contains("checked") || cls.contains("active") || cls.contains("on") || cls.contains("true"));
    }

    public void setToggle(By toggleLocator, boolean enable, String label) {
        WebDriverWait wait = getExplicitWait(10);
        WebElement toggle = wait.until(ExpectedConditions.elementToBeClickable(toggleLocator));
        if (isToggleOn(toggleLocator) != enable) {
            toggle.click();
            pause(1);
        }
        test.log(Status.PASS, label + " Tiers toggle set to: " + (enable ? "ON" : "OFF"));
    }

    public void scrollToElement(By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        pause(1);
    }

    /**
     * Logs multiple Expected vs Actual rows in ONE table.
     * Usage: compareAndLog(new String[][] {
     *     {"Total Commission", "320000.00", "$ 0.00"},
     *     {"Final Payment",    "320000.00", "$ 0.00"}
     * });
     */
    public static boolean compareAndLog(String[][] rows) {
        boolean allPassed = true;

        StringBuilder table = new StringBuilder();
        table.append("<table style='border-collapse:collapse;'>")
                .append("<tr style='background:#1a73e8;color:white;'>")
                .append("<th style='padding:6px;border:1px solid #ddd;'>Field</th>")
                .append("<th style='padding:6px;border:1px solid #ddd;'>Expected</th>")
                .append("<th style='padding:6px;border:1px solid #ddd;'>Actual</th>")
                .append("<th style='padding:6px;border:1px solid #ddd;'>Status</th></tr>");

        for (String[] row : rows) {
            String field    = row[0];
            String expected = row[1];
            String actual   = row[2];

            String expClean = expected.replaceAll("[^0-9.]", "");
            String actClean = actual.replaceAll("[^0-9.]", "");
            boolean match   = expClean.equals(actClean);

            String label;
            String color;

            if (match) {
                label = "PASS"; color = "#e6f4ea";
            } else {
                label = "FAIL"; color = "#fce8e6"; allPassed = false;
            }

            table.append("<tr style='background:").append(color).append(";'>")
                    .append("<td style='padding:6px;border:1px solid #ddd;'><b>").append(field).append("</b></td>")
                    .append("<td style='padding:6px;border:1px solid #ddd;'>").append(expected).append("</td>")
                    .append("<td style='padding:6px;border:1px solid #ddd;'>").append(actual).append("</td>")
                    .append("<td style='padding:6px;border:1px solid #ddd;'><b>").append(label).append("</b></td>")
                    .append("</tr>");
        }

        table.append("</table>");
        test.log(allPassed ? Status.PASS : Status.FAIL, table.toString());
        return allPassed;
    }

}
