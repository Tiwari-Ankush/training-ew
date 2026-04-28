package tutorialsninja;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class login_tutorialNinja {

	public static void main(String[] args) throws IOException, InterruptedException {


		String filepath = System.getProperty("user.dir") + "\\testdata\\apachePOIdemodata.xlsx";
//
		int rows = ExcelUtils.getRowCount(filepath, "loginData");

		// pass above data into application


		for (int i = 1; i <= rows; i++) {
			WebDriver driver = new ChromeDriver();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			driver.get("https://tutorialsninja.com/demo/");
			driver.manage().window().maximize();
			// read data from excel
			WebElement myAccount = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='My Account']")));
			myAccount.click();
			
			WebElement loginNav = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Login']")));
			loginNav.click();

			String email = ExcelUtils.getCellData(filepath, "loginData", i, 0);
			String password = ExcelUtils.getCellData(filepath, "loginData", i, 1);

			WebElement emailField = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='input-email']")));
			emailField.sendKeys(email);

			WebElement passwordField = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='input-password']")));
			passwordField.sendKeys(password);

			WebElement loginBtn = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Login']")));
			loginBtn.click();

			driver.quit();
			// validation
			Thread.sleep(2000);

		}

	}

}
