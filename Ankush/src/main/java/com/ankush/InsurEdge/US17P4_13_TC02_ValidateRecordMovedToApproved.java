package com.ankush.InsurEdge;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class US17P4_13_TC02_ValidateRecordMovedToApproved {

	public static void jsScrollAndClick(WebDriver driver, WebElement element) {
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    try{
	    	js.executeScript("arguments[0].scrollIntoView({block:'center', inline:'center'});", element);
	    	js.executeScript("arguments[0].click();", element);
	    }catch(Exception e) {
	    	element.click();
	    }
	}
	
	public static WebDriverWait getWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public static void launchAndNavigation(WebDriver driver) {
        driver.get("https://qeaskillhub.cognizant.com/LoginPage");
        driver.manage().window().maximize();
    }

    public static void login(WebDriver driver) {
        WebDriverWait wait = getWait(driver);
        try {
            WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtUsername")));
            username.sendKeys("admin_user");
            WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtPassword")));
            password.sendKeys("testadmin");
            WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("BtnLogin")));
            loginBtn.click();
        } catch (Exception e) {
            System.out.println("login failed: " + e.getMessage());
        }
    }

    public static void navigation_pendingPH(WebDriver driver) {
        WebDriverWait wait = getWait(driver);
        try {
            WebElement sidebar = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='sidebar-nav']/li[5]/a")));
            sidebar.click();
            WebElement pendingPH = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='policyHolder-nav']/li[3]")));
            pendingPH.click();
        } catch (Exception e) {
            System.out.println("navigation failed: " + e.getMessage());
        }
    }
    
    public static void navigation_approve(WebDriver driver) {
    	WebDriverWait wait = getWait(driver);
        try {
            WebElement sidebar = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='sidebar-nav']/li[5]/a")));
            sidebar.click();
            WebElement pendingPH = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='policyHolder-nav']/li[2]")));
            pendingPH.click();
        } catch (Exception e) {
            System.out.println("navigation failed: " + e.getMessage());
        }
    }

    public static boolean validation_US13tc02(WebDriver driver) {
        WebDriverWait wait = getWait(driver);
        
        try {
            navigation_pendingPH(driver);

            // Wait for pending table
            WebElement pendingTable = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.id("ContentPlaceHolder_Admin_gvPendingHolders"))
            );
            // 2) Extract first-row values: Customer Name (td[1]), Policy Name (td[4]), Sub Category (td[6])
            List<WebElement> pendingRows = pendingTable.findElements(By.xpath(".//tbody/tr[td]"));
            if (pendingRows.isEmpty()) {
                System.out.println("FAIL: No rows in pending table.");
                return false;
            }
            
            WebElement firstPendingRow = pendingRows.get(0);
            String customerName = firstPendingRow.findElement(By.xpath(".//td[1]")).getText().trim();
            String policyName   = firstPendingRow.findElement(By.xpath(".//td[4]")).getText().trim();
            String subCategory  = firstPendingRow.findElement(By.xpath(".//td[6]")).getText().trim();

            System.out.println("Captured from Pending -> Customer: " + customerName);
            System.out.println("Captured from Pending -> Policy: " + policyName);
            System.out.println("Captured from Pending -> SubCategory: " + subCategory);

            // 3) Click Approve on first row using JS executor
            WebElement approveBtn = firstPendingRow.findElement(By.xpath(".//input[@value='Approve']"));
            wait.until(ExpectedConditions.elementToBeClickable(approveBtn));
            jsScrollAndClick(driver,approveBtn);

            Thread.sleep(2000);
            // 4) Navigate to Approved page
            navigation_approve(driver);
///////////////////
            // 5) Apply filters by sendKeys (Select2):
            // Customer Name (open select2 and type)
            WebElement customerSel = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[@id='select2-ContentPlaceHolder_Admin_ddlCustomerName-container']/parent::span")
            ));
            
            //customer name, policy name and sub category
            customerSel.click(); 
            WebElement s2Input = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@class='select2-search__field' and @aria-controls='select2-ContentPlaceHolder_Admin_ddlCustomerName-results']")
            ));
            s2Input.sendKeys(customerName);
            s2Input.sendKeys(Keys.ENTER);
            
            WebElement policySel = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[@id='select2-ContentPlaceHolder_Admin_ddlPolicyName-container']/parent::span")
            ));
            policySel.click();
            s2Input = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@class='select2-search__field' and @aria-controls='select2-ContentPlaceHolder_Admin_ddlPolicyName-results']")
            ));
            s2Input.sendKeys(policyName);
            s2Input.sendKeys(Keys.ENTER);

            WebElement subCatSel = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[@id='select2-ContentPlaceHolder_Admin_ddlSubCategory-container']/parent::span")
            ));
            subCatSel.click();
            s2Input = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@class='select2-search__field' and @aria-controls='select2-ContentPlaceHolder_Admin_ddlSubCategory-results']")
            ));
            s2Input.sendKeys(subCategory);
            s2Input.sendKeys(Keys.ENTER);
//////////////////////////////////
            // Click Search
            WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("ContentPlaceHolder_Admin_btnSearch")
            ));
            jsScrollAndClick(driver,searchBtn);

            // Verify the record is shown in Approved grid
            WebElement approvedGrid = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("ContentPlaceHolder_Admin_gvApprovedHolders")
            ));

            List<WebElement> matchedRows = approvedGrid.findElements(By.xpath(".//tbody/tr[td]"));
            
            WebElement firstMatchingRow = matchedRows.get(0);

            String customerName_matched = firstMatchingRow.findElement(By.xpath(".//td[1]")).getText().trim();
            String policyName_matched   = firstMatchingRow.findElement(By.xpath(".//td[4]")).getText().trim();
            String subCategory_matched  = firstMatchingRow.findElement(By.xpath(".//td[6]")).getText().trim();
            
            System.out.println(customerName_matched);
            System.out.println(policyName_matched);
            System.out.println(subCategory_matched);
            
            if (!customerName.equals(customerName_matched) || !policyName.equals(policyName_matched)|| !subCategory.equals(subCategory_matched)) {
            	System.out.println("FAIL: Values do not match between Pending and Approved.");
            	return false;
            	} else {
            	    System.out.println("PASS: Record found (all fields match).");
            	}
        }catch (Exception e) {
            System.out.println("validation_US13tc02 failed: " + e.getMessage());
            return false;
        }
		return true;
    }
                
    public static void main(String[] args) throws InterruptedException {
    	WebDriver driver = new ChromeDriver();
        try {
            launchAndNavigation(driver);
            login(driver);
            
            if (validation_US13tc02(driver)) {
                System.out.println("US17P4_13_TC02: PASSED");
            } else {
                System.out.println("US17P4_13_TC02: FAILED");
            }
        } catch (Exception e) {
            System.out.println("Test Execution error: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
    }
    	
    	
    	