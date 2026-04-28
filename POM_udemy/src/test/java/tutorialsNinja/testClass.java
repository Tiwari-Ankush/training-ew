package tutorialsNinja;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class testClass {

WebDriver driver;
    WebDriverWait wait;
    homePage hp;
    loginPage lp;

    @BeforeClass
    void Setup() {  // <-- remove the WebDriver parameter
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://tutorialsninja.com/demo/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        hp = new homePage(driver);
        lp = new loginPage(driver);
    }


    @Test(priority = 1, groups = { "smoke", "sanity", "regression" })
	void navToLoginPage() {
		hp.clickMyAccount();
		hp.clickLoginOption();
	}
	
    @Test(priority = 2, groups = { "ui", "regression" })
	void testLogo() {
		if(hp.checkLogoDisp()) {
			System.out.println("Logo is Displayed.");
		};

	}

	@Test(priority=3, dependsOnMethods= {"navToLoginPage"},groups = { "sanity", "login", "regression" })
	void testLogin() {
		lp.setUsername("ankushgtiwari03@gmail.com");
		lp.setPassword("ankushtiwari");
		lp.clickLoginBtn();
		
		Assert.assertEquals(driver.getTitle(), "My Account");
	}

	@AfterClass
	void tearDown() {
		driver.quit();
	}

}
