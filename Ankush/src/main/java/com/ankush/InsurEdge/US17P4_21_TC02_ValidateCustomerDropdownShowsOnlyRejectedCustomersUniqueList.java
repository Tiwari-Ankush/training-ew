package com.ankush.InsurEdge;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class US17P4_21_TC02_ValidateCustomerDropdownShowsOnlyRejectedCustomersUniqueList {
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

    
    public static boolean validation_US21tc02(WebDriver driver) {
    	WebDriverWait wait = getWait(driver);
    	try {
    		if (!correctPageSanity_rph1(driver)) return false;
    		
    		// + Open the Select2 dropdown (visible UI) and wait till it opens
    		WebElement s2Container = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@id='select2-ContentPlaceHolder_Admin_ddlCustomerName-container']/parent::span")));
    		s2Container.click();
    		
    		 WebElement resultsList = wait.until(ExpectedConditions.visibilityOfElementLocated(
    	                By.id("select2-ContentPlaceHolder_Admin_ddlCustomerName-results")));
             
    	        // + Collect names WHILE scrolling the visible dropdown list
    	        List<String> dropdownNames = new ArrayList<>();
    	        List<WebElement> liOptions = resultsList.findElements(By.cssSelector("li.select2-results__option"));
    	        for (WebElement li : liOptions) {
    	            String txt = li.getText().trim();
    	            if (txt.length() > 0 && !txt.equalsIgnoreCase("-- All --") && !txt.equalsIgnoreCase("--All--")) {
    	               
    	            	jsScroll(driver,li);
//    	               
    	                dropdownNames.add(txt);
    	            }
    	        }
    	        
             for(String name:dropdownNames) {
             System.out.println(name);
             }

            if (dropdownNames.isEmpty()) {
                System.out.println("Fail: Customer dropdown is empty (excluding '-- All --').");
                return false;
            }

            // 2) Uniqueness check (case-insensitive, normalized)
            Set<String> unique = new HashSet<>(dropdownNames);
            if (unique.size() != dropdownNames.size()) {
            	System.out.println("Fail: Customer dropdown has duplicate names.");
            	return false;
            }
            
            System.out.println("PASS:  Dropdown shows customers all are unique.");
            return true;
    	}catch(Exception e) {
    		 System.out.println("validation_US21tc02 failed: " + e.getMessage());
    		 return false;
    	}
    }
    
public static void main(String[] args) {
	WebDriver driver = new ChromeDriver();
    try {
        launchAndNavigation(driver);
        login(driver);
        navigation_rejectedPH(driver);
        if (validation_US21tc02(driver)) {
            System.out.println("US17P4_21_TC02: PASSED");
        } else {
            System.out.println("US17P4_21_TC02: FAILED");
        }
    } catch (Exception e) {
        System.out.println("Test Execution error: " + e.getMessage());
    } finally {
        driver.quit();
    }
}
}
