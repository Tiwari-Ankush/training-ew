package com.ankush.InsurEdge;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class US17P4_12_FORMATVALIDATION {

    static WebDriver driver;
    static WebDriverWait wait;

    static boolean pendingPageOpened = false;

    public static void main(String[] args) {

        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(8));

        boolean anyFailure = false;

        try {
            driver.get("https://qeaskillhub.cognizant.com/LoginPage");
            driver.manage().window().maximize();

            login();

            // open pending page once
            goToPendingPolicyHolders();

            anyFailure |= !runTest("tc01_validate_customer_name_format", () -> tc01_validateCustomerName());
            anyFailure |= !runTest("tc02_validate_mobile_number_format", () -> tc02_validateMobile());
            anyFailure |= !runTest("tc03_validate_email_format", () -> tc03_validateEmail());
            anyFailure |= !runTest("tc04_validate_policy_name_format", () -> tc04_validatePolicyName());
            anyFailure |= !runTest("tc05_validate_main_category_format", () -> tc05_validateMainCategory());
            anyFailure |= !runTest("tc06_validate_sub_category_format", () -> tc06_validateSubCategory());
            anyFailure |= !runTest("tc07_validate_sum_assured_format", () -> tc07_validateSumAssured());
            anyFailure |= !runTest("tc08_validate_premium_format", () -> tc08_validatePremium());
            anyFailure |= !runTest("tc09_validate_tenure_format", () -> tc09_validateTenure());
            anyFailure |= !runTest("tc10_validate_applied_on_date_format", () -> tc10_validateAppliedOn());
            anyFailure |= !runTest("tc11_validate_actions_buttons_display", () -> tc11_validateActionsButtons());

            if (anyFailure) {
                throw new AssertionError("fail: one or more format validation test cases failed.");
            } else {
                System.out.println("pass: all us17p4_12 format validation test cases passed.");
            }

        } finally {
            driver.quit();
        }
    }

    // ---------------- helper methods ----------------

    static void login() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='txtUsername']")))
                .sendKeys("admin_user");
        driver.findElement(By.xpath("//input[@name='txtPassword']")).sendKeys("testadmin", Keys.ENTER);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sidebar-nav")));
    }

    static void goToPendingPolicyHolders() {
        if (pendingPageOpened) return;

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='sidebar-nav']/li[5]/a/i[2]"))).click();
        WebElement pending = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id='policyHolder-nav']/li[3]/a/span")));
        pending.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ContentPlaceHolder_Admin_gvPendingHolders")));
        pendingPageOpened = true;
    }

    static WebElement getPendingTable() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("ContentPlaceHolder_Admin_gvPendingHolders")));
    }

    static List<WebElement> getDataRows(WebElement table) {
        List<WebElement> rows = table.findElements(By.xpath(".//tbody/tr[td and not(td[@colspan])]"));
        if (rows.size() == 0) throw new AssertionError("fail: no data rows found.");
        return rows;
    }

    static int limit10(List<WebElement> rows) {
        return Math.min(10, rows.size());
    }

    static void scrollToElement(WebElement el) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center', inline:'center'});", el);
    }

    interface TestBlock { void run(); }

    static boolean runTest(String name, TestBlock block) {
        try {
            block.run();
            System.out.println("pass: " + name);
            return true;
        } catch (Throwable t) {
            System.out.println("fail: " + name + " -> " + t.getMessage());
            return false;
        }
    }

    // ---------------- tc01 - tc11 modules ----------------

    static void tc01_validateCustomerName() {
        WebElement table = getPendingTable();
        List<WebElement> rows = getDataRows(table);
        int limit = limit10(rows);

        for (int i = 0; i < limit; i++) {
            WebElement cell = rows.get(i).findElements(By.tagName("td")).get(0);
            scrollToElement(cell);
            String v = cell.getText().trim();

            if (v.isEmpty() || v.equalsIgnoreCase("null") || v.contains("�") || v.contains("???")) {
                throw new AssertionError("row " + (i + 1) + " customer name invalid -> [" + v + "]");
            }
        }
    }

    static void tc02_validateMobile() {
        WebElement table = getPendingTable();
        List<WebElement> rows = getDataRows(table);
        int limit = limit10(rows);

        for (int i = 0; i < limit; i++) {
            WebElement cell = rows.get(i).findElements(By.tagName("td")).get(1);
            scrollToElement(cell);
            String v = cell.getText().trim();

            if (!v.matches("\\d{10}")) {
                throw new AssertionError("row " + (i + 1) + " mobile invalid -> [" + v + "]");
            }
        }
    }

    static void tc03_validateEmail() {
        WebElement table = getPendingTable();
        List<WebElement> rows = getDataRows(table);
        int limit = limit10(rows);

        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        for (int i = 0; i < limit; i++) {
            WebElement cell = rows.get(i).findElements(By.tagName("td")).get(2);
            scrollToElement(cell);
            String v = cell.getText().trim();

            if (v.isEmpty() || v.contains(" ") || !v.matches(emailRegex)) {
                throw new AssertionError("row " + (i + 1) + " email invalid -> [" + v + "]");
            }
        }
    }

    static void tc04_validatePolicyName() {
        WebElement table = getPendingTable();
        List<WebElement> rows = getDataRows(table);
        int limit = limit10(rows);

        for (int i = 0; i < limit; i++) {
            WebElement cell = rows.get(i).findElements(By.tagName("td")).get(3);
            scrollToElement(cell);
            String v = cell.getText().trim();

            if (v.isEmpty() || v.equalsIgnoreCase("null") || v.contains("�") || v.contains("???")) {
                throw new AssertionError("row " + (i + 1) + " policy name invalid -> [" + v + "]");
            }
        }
    }

    static void tc05_validateMainCategory() {
        WebElement table = getPendingTable();
        List<WebElement> rows = getDataRows(table);
        int limit = limit10(rows);

        for (int i = 0; i < limit; i++) {
            WebElement cell = rows.get(i).findElements(By.tagName("td")).get(4);
            scrollToElement(cell);
            String v = cell.getText().trim();

            if (v.isEmpty() || v.equalsIgnoreCase("null") || v.contains("�") || v.contains("???")) {
                throw new AssertionError("row " + (i + 1) + " main category invalid -> [" + v + "]");
            }
        }
    }

    static void tc06_validateSubCategory() {
        WebElement table = getPendingTable();
        List<WebElement> rows = getDataRows(table);
        int limit = limit10(rows);

        for (int i = 0; i < limit; i++) {
            WebElement cell = rows.get(i).findElements(By.tagName("td")).get(5);
            scrollToElement(cell);
            String v = cell.getText().trim();

            if (v.isEmpty() || v.equalsIgnoreCase("null") || v.contains("�") || v.contains("???")) {
                throw new AssertionError("row " + (i + 1) + " sub category invalid -> [" + v + "]");
            }
        }
    }

    static void tc07_validateSumAssured() {
        WebElement table = getPendingTable();
        List<WebElement> rows = getDataRows(table);
        int limit = limit10(rows);

        for (int i = 0; i < limit; i++) {
            WebElement cell = rows.get(i).findElements(By.tagName("td")).get(6);
            scrollToElement(cell);
            String v = cell.getText().trim();

            if (!v.matches("\\d+\\.\\d{2}")) {
                throw new AssertionError("row " + (i + 1) + " sum assured invalid -> [" + v + "]");
            }
        }
    }

    static void tc08_validatePremium() {
        WebElement table = getPendingTable();
        List<WebElement> rows = getDataRows(table);
        int limit = limit10(rows);

        for (int i = 0; i < limit; i++) {
            WebElement cell = rows.get(i).findElements(By.tagName("td")).get(7);
            scrollToElement(cell);
            String v = cell.getText().trim();

            if (!v.matches("\\d+\\.\\d{2}")) {
                throw new AssertionError("row " + (i + 1) + " premium invalid -> [" + v + "]");
            }
        }
    }

    static void tc09_validateTenure() {
        WebElement table = getPendingTable();
        List<WebElement> rows = getDataRows(table);
        int limit = limit10(rows);

        for (int i = 0; i < limit; i++) {
            WebElement cell = rows.get(i).findElements(By.tagName("td")).get(8);
            scrollToElement(cell);
            String v = cell.getText().trim();

            boolean ok = v.matches("\\d+");
            if (ok) {
                int tenure = Integer.parseInt(v);
                if (tenure <= 0) ok = false;
            }

            if (!ok) {
                throw new AssertionError("row " + (i + 1) + " tenure invalid -> [" + v + "]");
            }
        }
    }

    static void tc10_validateAppliedOn() {
        WebElement table = getPendingTable();
        List<WebElement> rows = getDataRows(table);
        int limit = limit10(rows);

        for (int i = 0; i < limit; i++) {
            WebElement cell = rows.get(i).findElements(By.tagName("td")).get(9);
            scrollToElement(cell);
            String v = cell.getText().trim();

            boolean ok = v.matches("\\d{4}-\\d{2}-\\d{2}");
            if (ok) {
                try { LocalDate.parse(v); }
                catch (Exception e) { ok = false; }
            }

            if (!ok) {
                throw new AssertionError("row " + (i + 1) + " applied on invalid -> [" + v + "]");
            }
        }
    }

    static void tc11_validateActionsButtons() {
        WebElement table = getPendingTable();
        List<WebElement> rows = getDataRows(table);
        int limit = limit10(rows);

        for (int i = 0; i < limit; i++) {
            WebElement actionsCell = rows.get(i).findElements(By.tagName("td")).get(10);
            scrollToElement(actionsCell);

            List<WebElement> approveBtns = actionsCell.findElements(By.xpath(".//input[@value='Approve']"));
            List<WebElement> rejectBtns = actionsCell.findElements(By.xpath(".//input[@value='Reject']"));

            boolean approveOk = approveBtns.size() > 0 && approveBtns.get(0).isDisplayed();
            boolean rejectOk = rejectBtns.size() > 0 && rejectBtns.get(0).isDisplayed();

            if (!approveOk || !rejectOk) {
                throw new AssertionError("row " + (i + 1) + " approve/reject missing");
            }
        }
    }
}