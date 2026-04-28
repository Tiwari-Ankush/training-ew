package com.ankush.InsurEdge;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class US17P4_21_TC04_ValidatePolicyNameSearchBehaviorForSpecialCharacters_NoResultsFound {
	public static void jsScroll(WebDriver driver, WebElement element) {
	    if (element == null) return;
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    try {
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

	    
	    public static boolean validation_US21tc04(WebDriver driver) {
	    	WebDriverWait wait = getWait(driver);
	    	try {
	    		if (!correctPageSanity_rph1(driver)) return false;
	    		
	    		// Open Policy Name Select2
	            WebElement policySelection = wait.until(
	                ExpectedConditions.elementToBeClickable(
	                    By.xpath("//span[@id='select2-ContentPlaceHolder_Admin_ddlPolicyName-container']"))
	            );
	            policySelection.click();

	            //Step 1-> Type special/non-alphanumeric characters
	            WebElement searchBox = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.select2-search__field")));
	            String special = "policy@12";
	            searchBox.sendKeys(special);

	            // Step 2-> Expect "No results foundd
	            WebElement noResults = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//li[contains(@class,'select2-results__message')]")
	            ));

	            String text = noResults.getText().trim();
	            if (!text.equalsIgnoreCase("No results found")) {
	                System.out.println("Fail: Expected 'No results found'. Found: " + text);
	                return false;
	            }

	            // Step 3-> Clear text and ensure list restores
	            searchBox.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
	            
	            WebElement anyOption = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//li[contains(@class,'select2-results__option') and @role='option']")));
	            String restoredText = anyOption.getText().trim();
	            
	            if (restoredText.isEmpty()) {
	                System.out.println("Pass: Options did not restore after clearing search.");
	                return false;
	            }

	            System.out.println("PASS: TC04 special characters accepted for search; 'No results found' displayed when unmatched; list restores after clear.");
	            return true;
	    	}catch(Exception e) {
	    		 System.out.println("validation_US21tc04 failed: " + e.getMessage());
	    		 return false;
	    	}
	    }
	    public static void main(String[] args) {
	    	WebDriver driver = new ChromeDriver();
	        try {
	            launchAndNavigation(driver);
	            login(driver);
	            navigation_rejectedPH(driver);
	            if (validation_US21tc04(driver)) {
	                System.out.println("US17P4_21_TC04: PASSED");
	            } else {
	                System.out.println("US17P4_21_TC04: FAILED");
	            }
	        } catch (Exception e) {
	            System.out.println("Test Execution error: " + e.getMessage());
	        } finally {
	            driver.quit();
	        }
	    }
	    }


