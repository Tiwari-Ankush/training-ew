package com.ankush.InsurEdge;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class US17P4_21_TC01_ValidateCustomerNameDropdownDefaultSelection {
	public static void jsScroll(WebDriver driver, WebElement element) {
	    if (element == null) return;
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    try {
	        // Primary: center the element, then nudge up to avoid sticky headers
	        js.executeScript("arguments[0].scrollIntoView({block:'center', inline:'nearest'});", element);
	        js.executeScript("window.scrollBy(0, -80);"); // adjust offset as needed
	    } catch (Exception e){
	    	System.out.println("ElementClickInterceptedException: "+e);
	    }
	}


	public static void jsClick(WebDriver driver, WebElement element) {
	    if (element == null) return;
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    jsScroll(driver, element);
	    try {
	        js.executeScript("arguments[0].click();", element);
	        return;
	    } catch (Exception e1) {
	    	System.out.println("js click fail: "+e1);
	    }
	    try {
	        element.click();
	        return;
	    } catch (Exception e2) {
	    	System.out.println("ElementClickInterceptedException: "+e2);
	    }
	}
	
	public static WebDriverWait getWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }

	public static boolean correctPageSanity_rph1(WebDriver driver) {
		  WebDriverWait wait = getWait(driver);
		  try {
			  String title = wait.until(ExpectedConditions.visibilityOfElementLocated(
                 By.xpath("//div[@class='pagetitle']/h1"))).getText().trim();
			  if (!title.equalsIgnoreCase("Rejected Policy Holders")) {
				  System.out.println("Fail: Page title mismatch. Found: " + title);
				  return false;
			  }
			  return true;
		  }catch(Exception e) {
			  System.out.println("Navigation is Incorrect");
			  return false;
		  }
         
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
    public static void navigation_rejectedPH(WebDriver driver) {
        WebDriverWait wait = getWait(driver);
        try {
            WebElement sidebar = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='sidebar-nav']/li[5]/a")));
            sidebar.click();
            WebElement rejectedPH = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='policyHolder-nav']/li[4]")));
            rejectedPH.click();
        } catch (Exception e) {
            System.out.println("navigation failed: " + e.getMessage());
        }
    }
    
    public static boolean validation_US21tc01(WebDriver driver) throws InterruptedException {
    	WebDriverWait wait = getWait(driver);
    	try {
    		if (!correctPageSanity_rph1(driver)) return false;
    		 WebElement ddlCustomer = wait.until(ExpectedConditions.visibilityOfElementLocated(
                     By.id("ContentPlaceHolder_Admin_ddlCustomerName")));
    		 
//             System.out.println(ddlCustomer.isDisplayed());
             Select selCustomer = new Select(ddlCustomer);
             
             String selected = selCustomer.getFirstSelectedOption().getText().trim();
//             System.out.println(selected);
             if (!selected.equalsIgnoreCase("-- All --") && !selected.equalsIgnoreCase("--All--")) {
                 System.out.println("Fail: Default customer not '-- All --'. Found");
                 return false;
             }
            	 System.out.println("Pass: Default cutomer is right '-- All--'");
            	 return true;
    	}catch(Exception e){
    		System.out.println("validation_US21tc01 failed: " + e.getMessage());
            return false;
    	}
    
    }
    public static void main(String[] args)  {
    	WebDriver driver = new ChromeDriver();
        try {
            launchAndNavigation(driver);
            login(driver);
            navigation_rejectedPH(driver);
            if (validation_US21tc01(driver)) {
                System.out.println("US17P4_21_TC01: PASSED");
            } else {
                System.out.println("US17P4_21_TC01: FAILED");
            }
        } catch (Exception e) {
            System.out.println("Test Execution error: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
    	
 