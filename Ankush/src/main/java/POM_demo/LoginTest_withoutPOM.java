package POM_demo;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest_withoutPOM {
	
	WebDriver driver;
	
	@BeforeClass
	void setup() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().window().maximize();
	}
	
	@Test
	void testLogin() {
		loginPage_withoutPOM lp = new loginPage_withoutPOM(driver);
		lp.setUsername("Admin");
		lp.setPassword("admin123");
		lp.clickLogin();
	}
	
	@AfterClass
	void tearDown() {
		driver.quit();
	}

}
