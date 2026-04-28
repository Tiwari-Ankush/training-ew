package com.ankush.InsurEdge;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class US17P4_13_TC03_ValidateApprovalSuccessMessage {
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
    
    public static boolean validation_US13tc03(WebDriver driver) {
    	 WebDriverWait wait = getWait(driver);
         boolean allPass;
         try {
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
//            Click Approve on first row using JS executor
            WebElement approveBtn = firstPendingRow.findElement(By.xpath(".//input[@value='Approve']"));
            wait.until(ExpectedConditions.elementToBeClickable(approveBtn));
            jsScrollAndClick(driver,approveBtn);
            
            //wait for the message
            WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.id("ContentPlaceHolder_Admin_lblMessage")));
            
            String color = msg.getCssValue("color").toLowerCase();
            System.out.println(color);

            String msgTxt = msg.getText().trim().toLowerCase();
            System.out.println(msgTxt);
            
            // filterrow require for the position verification
            WebElement filterRow = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='frmPendingPolicyHolder']/div[4]")));
            
            boolean textOk = msgTxt.contains("policy for")&& msgTxt.contains(customerName.toLowerCase()) && msgTxt.contains("has been approved");
            if(textOk){
            	System.out.println("PASS: Correct approval mssge showing.");
            }else {
            	System.out.println("FAIL: Message not matched, something went wrong.");
            }
            
            boolean colorOk = color.contains("25, 135, 84")|| color.contains("0, 128, 0") || color.contains("#198754") || color.contains("green");            
            if(colorOk) {
            	System.out.println("PASS: Approval message having correct color.");
                
            }else {
            	System.out.println("FAIL: Message not in proper color");
            }
            
            
            boolean isJustBelow = filterRow.findElements(By.xpath("//*[@id='frmPendingPolicyHolder']/div[4]/following-sibling::div/span")).size() > 0;
            if(isJustBelow) {
            	System.out.println("Pass: Success message is just below the filter container.");
            }else {
            	System.out.println("Fail: Success message is not in the right position.");
            }
            
            allPass = textOk && colorOk && isJustBelow;
            
         }catch(Exception e) {
        	 System.out.println("validation_US13tc02 failed: " + e.getMessage());
             return false;
         }
         return allPass;
         
    }
    
    public static void main(String[] args) throws InterruptedException {
    	WebDriver driver = new ChromeDriver();
        try {
            launchAndNavigation(driver);
            login(driver);
            navigation_pendingPH(driver);
            
            if (validation_US13tc03(driver)) {
                System.out.println("US17P4_13_TC03: PASSED");
            } else {
                System.out.println("US17P4_13_TC03: FAILED");
            }
        } catch (Exception e) {
            System.out.println("Test Execution error: " + e.getMessage());
        } finally {
            driver.quit();
        }

    }}


