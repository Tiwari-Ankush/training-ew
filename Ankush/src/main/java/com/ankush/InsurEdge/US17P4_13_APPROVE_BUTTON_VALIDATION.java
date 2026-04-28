package com.ankush.InsurEdge;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class US17P4_13_APPROVE_BUTTON_VALIDATION {

    static WebDriver driver;
    static WebDriverWait wait;

    // change only these urls if your app uses .aspx
    static final String LOGIN_URL   = "https://qeaskillhub.cognizant.com/LoginPage";
    static final String PENDING_URL = "https://qeaskillhub.cognizant.com/AdminPendingPolicyHolder";
    static final String APPROVED_URL= "https://qeaskillhub.cognizant.com/AdminApprovedPolicyHolder";

    public static void main(String[] args) {

        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        driver.manage().window().maximize();

        boolean anyFail = false;

        try {
            // login once
            driver.get(LOGIN_URL);
            ensureLoggedIn();

            anyFail |= !runTest("us17p4_13_tc02_validate_record_moved_to_approved", () -> tc02_recordMovedToApproved());
            anyFail |= !runTest("us17p4_13_tc03_validate_approval_success_message", () -> tc03_successMessageGreen());

            if (anyFail) throw new AssertionError("fail: one or more tests failed.");
            System.out.println("pass: all us17p4_13 approve workflow tests passed.");

        } finally {
            driver.quit();
        }
    }

    interface TestBlock { void run(); }

    static boolean runTest(String name, TestBlock t) {
        try {
            t.run();
            System.out.println("pass: " + name);
            return true;
        } catch (Throwable e) {
            System.out.println("fail: " + name + " -> " + e.getMessage());
            return false;
        }
    }

    static void ensureLoggedIn() {

        // if already logged in, sidebar-nav will exist
        if (driver.findElements(By.id("sidebar-nav")).size() > 0) {
            return;
        }

        // if not logged in, login page should have username
        if (driver.findElements(By.id("txtUsername")).size() > 0) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtUsername"))).clear();
            driver.findElement(By.id("txtUsername")).sendKeys("admin_user");
            driver.findElement(By.name("txtPassword")).sendKeys("testadmin", Keys.ENTER);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sidebar-nav")));
            return;
        }

        // fallback: go to login page once and login
        driver.get(LOGIN_URL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtUsername"))).clear();
        driver.findElement(By.id("txtUsername")).sendKeys("admin_user");
        driver.findElement(By.name("txtPassword")).sendKeys("testadmin", Keys.ENTER);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sidebar-nav")));
    }

    static WebElement openPendingTable() {
        ensureLoggedIn();
        driver.get(PENDING_URL);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ContentPlaceHolder_Admin_gvPendingHolders")));
    }

    static WebElement openApprovedTable() {
        ensureLoggedIn();
        driver.get(APPROVED_URL);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//table[contains(@class,'table')])[1]")));
    }

    static List<WebElement> dataRows(WebElement table) {
        return table.findElements(By.xpath(".//tbody/tr[td and not(td[@colspan])]"));
    }

    static void safeJsClick(WebElement el) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", el);
        wait.until(ExpectedConditions.visibilityOf(el));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
    }

    static boolean rowExistsInTable(WebElement table, String name, String email) {
        List<WebElement> rows = dataRows(table);
        for (WebElement r : rows) {
            List<WebElement> c = r.findElements(By.tagName("td"));
            if (c.size() > 2) {
                String n = c.get(0).getText().trim();
                String e = c.get(2).getText().trim();
                if (n.equalsIgnoreCase(name) && e.equalsIgnoreCase(email)) return true;
            }
        }
        return false;
    }

    static void goToNextPageIfPresent(WebElement table) {
        List<WebElement> nextLinks = table.findElements(By.xpath(".//a[contains(@href,'Page$')]"));
        if (nextLinks.size() > 0) safeJsClick(nextLinks.get(0));
    }

    // tc02: approve from pending and verify record is visible in approved (check first few pages)
    static void tc02_recordMovedToApproved() {

        WebElement pendingTable = openPendingTable();
        List<WebElement> pendingRows = dataRows(pendingTable);
        if (pendingRows.size() == 0) throw new AssertionError("fail: no pending rows to approve.");

        List<WebElement> cells = pendingRows.get(0).findElements(By.tagName("td"));
        String customerName = cells.get(0).getText().trim();
        String email = cells.get(2).getText().trim();

        WebElement approveBtn = pendingRows.get(0).findElement(By.xpath(".//input[@value='Approve']"));
        safeJsClick(approveBtn);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ContentPlaceHolder_Admin_lblMessage")));

        WebElement approvedTable = openApprovedTable();

        boolean found = false;
        for (int i = 0; i < 5; i++) {
            try {
                if (rowExistsInTable(approvedTable, customerName, email)) {
                    found = true;
                    break;
                }
                WebElement firstRow = null;
                List<WebElement> rows = dataRows(approvedTable);
                if (rows.size() > 0) firstRow = rows.get(0);

                goToNextPageIfPresent(approvedTable);

                if (firstRow != null) {
                    wait.until(ExpectedConditions.stalenessOf(firstRow));
                }
                approvedTable = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//table[contains(@class,'table')])[1]")));

            } catch (StaleElementReferenceException ignored) {
                approvedTable = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//table[contains(@class,'table')])[1]")));
            }
        }

        if (!found) {
            throw new AssertionError("fail: record not found in approved section (checked first pages).");
        }
    }

    // tc03: approve and validate success message text + green
    static void tc03_successMessageGreen() {

        WebElement pendingTable = openPendingTable();
        List<WebElement> rows = dataRows(pendingTable);
        if (rows.size() == 0) throw new AssertionError("fail: no pending rows to approve.");

        String customerName = rows.get(0).findElements(By.tagName("td")).get(0).getText().trim();

        WebElement approveBtn = rows.get(0).findElement(By.xpath(".//input[@value='Approve']"));
        safeJsClick(approveBtn);

        WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ContentPlaceHolder_Admin_lblMessage")));
        wait.until(d -> msg.getText().toLowerCase().contains("has been approved"));

        String text = msg.getText().trim().toLowerCase();
        if (!(text.contains("policy for") && text.contains(customerName.toLowerCase()) && text.contains("has been approved"))) {
            throw new AssertionError("fail: message text invalid -> [" + msg.getText().trim() + "]");
        }

        String color = msg.getCssValue("color").toLowerCase();
        String cls = msg.getAttribute("class");

        boolean greenOk = color.contains("25, 135, 84") || color.contains("0, 128, 0") || color.contains("green");
        if (cls != null) greenOk = greenOk || cls.toLowerCase().contains("text-success") || cls.toLowerCase().contains("alert-success");

        if (!greenOk) {
            throw new AssertionError("fail: message not green. css=" + color + " class=" + cls);
        }
    }
}