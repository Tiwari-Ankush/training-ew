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
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

//@Listeners(testng_practice.MyListener.class)
public class tutorialsNinja_listenersReport {
	WebDriver driver;
    WebDriverWait wait;

    @BeforeClass(groups = {"smoke", "sanity", "regression"})
    void launch() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://tutorialsninja.com/demo/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test(priority = 1, groups = {"smoke", "sanity","regression"})
    void navToLogin() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='My Account']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Login']"))).click();
        Thread.sleep(2000);
    }

    @Test(priority = 2, groups = {"ui","regression"})
    void testLogo() {

        WebElement logo = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Qafox.com']")));

        Assert.assertTrue(logo.isDisplayed(), "❌ Logo is NOT displayed on the page!");
    }

    @Test(priority = 3, dependsOnMethods = { "navToLogin" }, groups = {"sanity", "login", "regression"})
    void login() throws IOException, InterruptedException {

//        String filepath = System.getProperty("user.dir") + "\\testdata\\apachePOIdemodata.xlsx";
//
//        String email = ExcelUtils.getCellData(filepath, "loginData", 1, 0);
//        String password = ExcelUtils.getCellData(filepath, "loginData", 1, 1);
        Thread.sleep(2000);
        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.id("input-email")));
        emailField.sendKeys("ankushgtiwari03@gmail.com");

        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.id("input-password")));
        passwordField.sendKeys("ankushtiwari");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Login']"))).click();

        Thread.sleep(1000);
    }

    @Test(priority = 4, dependsOnMethods = { "login" }, groups = {"regression"})
    void logout() throws InterruptedException {

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='My Account']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Logout']"))).click();

        Thread.sleep(1000);
    }

    @AfterClass(groups = {"sanity", "regression"})
    void tearDown() {
        driver.quit();
    }
}
