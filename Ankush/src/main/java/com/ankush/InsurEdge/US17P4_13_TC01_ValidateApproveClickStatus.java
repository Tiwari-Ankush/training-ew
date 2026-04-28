package com.ankush.InsurEdge;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class US17P4_13_TC01_ValidateApproveClickStatus {

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
    
    public static boolean validation_US13tc01(WebDriver driver) {
    	WebDriverWait wait = getWait(driver);
        try {
        	 WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ContentPlaceHolder_Admin_gvPendingHolders")));
             List<WebElement> dataRows = table.findElements(By.xpath(".//tbody/tr[td and not(td[@colspan])]"));

             if (dataRows.isEmpty()) {
                 System.out.println("fail: no data rows found.");
                 return false;
             }

          // Work with first row
             WebElement firstRow = dataRows.get(0);
             // Optional: capture cells if you need to assert values
//             List<WebElement> cells = (dataRows.get(0)).findElements(By.tagName("td"));

             WebElement approveBtn = firstRow.findElement(By.xpath(".//input[@value='Approve']"));
             wait.until(ExpectedConditions.elementToBeClickable(approveBtn));
             jsScrollAndClick(driver,approveBtn);
             
             
             WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                     By.id("ContentPlaceHolder_Admin_lblMessage")));
             if(!(msg.isDisplayed())) {
             	System.out.println("fail : message not matching");
             	return false;
             }else {
             	System.out.println("pass:  approve workflow validated");
             }
        return true;
     }catch (NoSuchElementException nse) {
            System.out.println("validation_US13tc01 failed (element missing): " + nse.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("validation_US13tc01 failed: " + e.getMessage());
            return false;
        }

}
    public static void main(String[] args) {
    	WebDriver driver = new ChromeDriver();
        try {
            launchAndNavigation(driver);
            login(driver);
            navigation_pendingPH(driver);
            if (validation_US13tc01(driver)) {
                System.out.println("US17P4_13_TC01: PASSED");
            } else {
                System.out.println("US17P4_13_TC01: FAILED");
            }
        } catch (Exception e) {
            System.out.println("Test Execution error: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}

    