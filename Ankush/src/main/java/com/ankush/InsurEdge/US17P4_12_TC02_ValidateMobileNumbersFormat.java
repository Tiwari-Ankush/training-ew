package com.ankush.InsurEdge;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class US17P4_12_TC02_ValidateMobileNumbersFormat{
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
		}catch(Exception e){
			System.out.println("login failed: "+e.getMessage());
		}
	}
	
	public static void navigation_pendingPH(WebDriver driver) {
		WebDriverWait wait = getWait(driver);
		try {
			WebElement sidebar = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='sidebar-nav']/li[5]/a")));
	        sidebar.click();
	        WebElement pendingPH = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='policyHolder-nav']/li[3]")));
	        pendingPH.click();
		}catch (Exception e) {
			System.out.println("navigation failed: "+e.getMessage());
		}
		
	}
	
	public static boolean validation_tc02(WebDriver driver) {
		WebDriverWait wait = getWait(driver);
		try {
			WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ContentPlaceHolder_Admin_gvPendingHolders")));
	        List<WebElement> rows = table.findElements(By.xpath(".//tbody/tr[td and not(td[@colspan])]"));

	        // If no data rows found
	        if (rows.size() == 0) {
	        	System.out.println("FAIL: No data rows found in the table.");
	        	return false;
	        }
	        // Check only first 10 data rows (rows start from index 1)
	        int limit = Math.min(10,rows.size());
	        for (int i = 0; i <limit; i++) {
	        	 List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));
		            if (cells.size() == 0) continue;  // Skip non-data rows (pagination)
		            String mobile = cells.get(1).getText().trim();

		            if (!mobile.matches("\\d{10}")) {
		                System.out.println("FAIL: Row " + (i+1) + " mobile "+ mobile);
		                return false;
		            } else {
		                System.out.println("PASS: Row " + (i+1) + " mobile "+ mobile);
		            }
	        		}
	        return true;
		}catch(Exception e) {
			System.out.println("validation_tc02 failed: "+e.getMessage());
			return false;
		}
	}
	
    public static void main(String[] args) {
    	WebDriver driver=new ChromeDriver();
		try {
			launchAndNavigation(driver);
			login(driver);
			navigation_pendingPH(driver);
			
			if(validation_tc02(driver)){
                System.out.println("US17P4_12_TC02: PASSED");
            } else {
                System.out.println("US17P4_12_TC02: FAILED");
            }
		}catch (Exception e){
			System.out.println("Test Execution error: "+e.getMessage());
		}
		finally {
			driver.quit();		
		}
    }
}
