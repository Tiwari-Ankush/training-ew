//package testng_practice;
//
//import java.io.IOException;
//import java.time.Duration;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//
//public class tutorialsNinjaSite_2 {
//
//	WebDriver driver;
//	WebDriverWait wait;
//
//	@BeforeClass(groups = { "smoke", "sanity", "regression" })
//	void launch() {
//		driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.get("https://tutorialsninja.com/demo/");
//		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//	}
//
//	@Test(priority = 1, groups = { "smoke", "sanity", "regression" })
//	void navToLogin() {
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='My Account']"))).click();
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Login']"))).click();
//	}
//
//	@Test(priority = 2, groups = { "ui", "regression" })
//	void testLogo() {
//		WebElement logo = wait
//				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Qafox.com']")));
//		Assert.assertTrue(logo.isDisplayed(), "❌ Logo is NOT displayed on the page!");
//	}
//
////	@Test(dataProvider = "dp", priority = 3, dependsOnMethods = { "navToLogin" }, groups = { "sanity", "login",
////			"regression" })
////	void testLogin(String email, String pwd) throws InterruptedException {
////		driver.get("https://tutorialsninja.com/demo/index.php?route=account/login");
////		driver.manage().window().maximize();
////		Thread.sleep(1000);
////		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(email);
////		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys(pwd);
////		driver.findElement(By.xpath("//input[@value='Login']")).click();
////		Thread.sleep(2000);
////		boolean status = driver.findElement(By.xpath("//h2[normalize-space()='My Account']")).isDisplayed();
////		if (status == true) {
////			driver.findElement(By.xpath("//a[@class='list-group-item'][normalize-space()='Logout']")).click();
////			Assert.assertTrue(true);
////		} else {
////			Assert.fail();
////		}
////
////	}
//
//	@Test(dataProvider = "dp_excel", priority = 3, dependsOnMethods = { "navToLogin" }, groups = { "sanity", "login",
//			"regression" })
//	void testLogin(String email, String pwd) throws InterruptedException {
//		driver.get("https://tutorialsninja.com/demo/index.php?route=account/login");
//		driver.manage().window().maximize();
//		Thread.sleep(1000);
//		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(email);
//		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys(pwd);
//		driver.findElement(By.xpath("//input[@value='Login']")).click();
//		Thread.sleep(2000);
//		boolean status = driver.findElement(By.xpath("//h2[normalize-space()='My Account']")).isDisplayed();
//		if (status == true) {
//			driver.findElement(By.xpath("//a[@class='list-group-item'][normalize-space()='Logout']")).click();
//			Assert.assertTrue(true);
//		} else {
//			Assert.fail();
//		}
//
//	}
//
////    @Test(priority = 4, dependsOnMethods = {"login"}, groups = {"regression"})
////    void logout() throws InterruptedException {
////
////        // Try logout only if user is logged in
////        try {
////            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='My Account']"))).click();
////            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Logout']"))).click();
////        } catch (Exception e) {
////            System.out.println("User was not logged in. Logout skipped.");
////        }
////
////        Thread.sleep(1000);
////    }
//
//	@AfterClass(groups = { "sanity", "regression" })
//	void tearDown() {
//		driver.quit();
//	}
//
//	@DataProvider(name = "dp", indices = { 0, 1, 2 })
//	Object[][] loginData() {
//		Object[][] data = { { "ankushgtiwari03@gmail.com", "ankushtiwari" },
//				{ "ankushtiwari@gmail.com", "ankushtiwari" }, { "testerankush@gmail.com", "ankushtester" },
//				{ "randomuser@gmail.com", "randomuser" } };
//		return data;
//	}
//
//	@DataProvider(name = "dp_excel")
//	public Object[][] loginData_excel() throws IOException {
//
//		String filepath = System.getProperty("user.dir") + "\\testdata\\apachePOIdemodata.xlsx";
//		String sheetName = "loginData";
//		int rows = ExcelUtils.getRowCount(filepath, sheetName);
//		int cols = ExcelUtils.getCellCount(filepath, sheetName, 0);
//		Object[][] data = new Object[rows][cols];
//		for (int i = 1; i <= rows; i++) {
//			for (int j = 0; j < cols; j++) {
//				data[i - 1][j] = ExcelUtils.getCellData(filepath, sheetName, i, j);
//			}
//		}
//
//		return data;
//
//	}
//
//}
//
//
//
//
//


package testng_practice;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class tutorialsNinjaSite_2 {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass(groups = { "smoke", "sanity", "regression" })
    void launch() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://tutorialsninja.com/demo/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test(priority = 1, groups = { "smoke", "sanity", "regression" })
    void navToLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='My Account']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Login']"))).click();
    }

    @Test(priority = 2, groups = { "ui", "regression" })
    void testLogo() {
        WebElement logo = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Qafox.com']")));
        Assert.assertTrue(logo.isDisplayed(), "❌ Logo is NOT displayed on the page!");
    }

    @Test(dataProvider = "dp", priority = 3, dependsOnMethods = { "navToLogin" }, groups = { "sanity", "login",
            "regression" })
    void testLogin(String email, String pwd) throws InterruptedException {
        driver.get("https://tutorialsninja.com/demo/index.php?route=account/login");
        driver.manage().window().maximize();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys(pwd);
        driver.findElement(By.xpath("//input[@value='Login']")).click();
        Thread.sleep(2000);

        // Keep your original logic/flow but avoid NoSuchElementException on negative cases
        boolean status = driver.findElements(By.xpath("//h2[normalize-space()='My Account']")).size() > 0;

        if (status == true) {
            driver.findElement(By.xpath("//a[@class='list-group-item'][normalize-space()='Logout']")).click();
            Assert.assertTrue(true);
        } else {
            Assert.fail();
        }
    }

    @AfterClass(groups = { "sanity", "regression" })
    void tearDown() {
        driver.quit();
    }

    @DataProvider(name = "dp", indices = { 0, 1, 2 })
    Object[][] loginData() {
        Object[][] data = { 
            { "ankushgtiwari03@gmail.com", "ankushtiwari" },
            { "ankushtiwari@gmail.com",    "ankushtiwari" },
            { "testerankush@gmail.com",    "ankushtester" },
            { "randommuser@gmail.com",     "randommuser" }
        };
        return data;
    }

    @DataProvider(name = "dp_excel")
    public Object[][] loginData_excel() throws IOException {

        String filepath = System.getProperty("user.dir") + "\\testdata\\apachePOIdemodata.xlsx";
        String sheetName = "loginData";

        int rows = ExcelUtils.getRowCount(filepath, sheetName);
        int cols = ExcelUtils.getCellCount(filepath, sheetName, 0);

        Object[][] data = new Object[rows][cols];

        for (int i = 1; i <= rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i - 1][j] = ExcelUtils.getCellData(filepath, sheetName, i, j);
            }
        }
        return data;
    }
}