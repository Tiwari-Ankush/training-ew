package testng_practice;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;

public class tutorialsNinjaSite_1 {

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

	@Test(priority = 3, dependsOnMethods = { "navToLogin" }, groups = { "sanity", "login", "regression" })
	void login() throws IOException, InterruptedException {

		String filepath = System.getProperty("user.dir") + "\\testdata\\apachePOIdemodata.xlsx";

		String email = ExcelUtils.getCellData(filepath, "loginData", 1, 0);
		String password = ExcelUtils.getCellData(filepath, "loginData", 1, 1);

		WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.id("input-email")));
		emailField.sendKeys(email);

		WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.id("input-password")));
		passwordField.sendKeys(password);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Login']"))).click();

		Thread.sleep(1000);
	}

	@Test(priority = 4, dependsOnMethods = { "login" }, groups = { "regression" })
	void logout() throws InterruptedException {
		
		Thread.sleep(1000);
		System.out.println(driver.getTitle());
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='My Account']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Logout']"))).click();

		Thread.sleep(1000);
	}

	@AfterClass(groups = { "sanity", "regression" })
	void tearDown() {
		driver.quit();
	}
}