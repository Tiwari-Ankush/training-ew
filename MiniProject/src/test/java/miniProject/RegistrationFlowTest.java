package miniProject;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import miniProject.base.BaseTest;
import miniProject.pages.LoginPage;
import miniProject.pages.RegistrationPage;
import miniProject.utils.ConfigReader;

public class RegistrationFlowTest extends BaseTest {

    private LoginPage loginPage;
    private RegistrationPage regPage;

    @Parameters({"browser", "headless"})
    @BeforeClass(alwaysRun = true)
    public void setUp(@Optional String browser, @Optional String headless) {
        String br = (browser == null || browser.isBlank()) ? ConfigReader.get("browser", "chrome") : browser;
        boolean hd = (headless == null || headless.isBlank()) ? ConfigReader.getBool("headless", false) : Boolean.parseBoolean(headless);
        String url = ConfigReader.get("url", "https://mail.rediff.com/cgi-bin/login.cgi");

        startDriver(br, hd, url);
        loginPage = new LoginPage(getDriver());
        getTest().log(Status.INFO, "Launched URL: " + url + " on browser: " + br + " headless: " + hd);
    }

    @Test(priority = 1, description = "Navigate to Create Account page")
    public void t01_redirectToCreateAccount() {
        regPage = loginPage.goToCreateAccount();
        getTest().info("Clicked on 'Create new Rediffmail account'");
        Assert.assertNotNull(regPage, "Registration page object is null!");
    }

    @Test(priority = 2, description = "Enter full name")
    public void t02_enterFullName() {
        regPage.enterFullName("Kamal");
        getTest().pass("Entered Full Name: Kamal");
    }

    @Test(priority = 3, description = "Enter email ID")
    public void t03_enterEmailId() {
        regPage.enterEmailId("kamal1234");
        getTest().pass("Entered Email: kamal1234");
    }

    @Test(priority = 4, description = "Check availability")
    public void t04_checkAvailability() {
        regPage.clickCheckAvailability();
        getTest().pass("Clicked 'Check availability'");
    }

    @Test(priority = 5, description = "Choose first suggested email, if any")
    public void t05_chooseSuggestion() {
        String suggestion = regPage.chooseFirstSuggestion();
        getTest().info("Suggested email chosen: " + suggestion);
    }

    @Test(priority = 6, description = "Set password & confirm")
    public void t06_setPasswords() {
        regPage.setPasswords("Kamal@1234");
        getTest().pass("Password & Confirm Password set");
    }

    @Test(priority = 7, description = "Toggle alternate ID checkbox")
    public void t07_toggleAltIdCheckbox() {
        regPage.toggleAltIdCheckbox();
        getTest().pass("Alternate ID checkbox toggled");
    }

    @Test(priority = 8, description = "Select Date of Birth")
    public void t08_selectDOB() {
        // day=20, month by typing J U N, year=2000
        regPage.selectDOB("20", "JUN", "2000");
        getTest().pass("DOB selected: 20-JUN-2000");
    }

    @Test(priority = 9, description = "Open Country dropdown")
    public void t09_openCountryDropdown() {
        regPage.openCountryDropdown();
        getTest().pass("Country dropdown opened");
    }

    @Test(priority = 10, description = "Print all countries")
    public void t10_printCountries() {
        List<WebElement> all = regPage.getAllCountries();
        System.out.println("All Countries:");
        for (WebElement e : all) {
            System.out.println(e.getText());
        }
        System.out.println("Total Countries = " + all.size());
        getTest().info("Countries printed on console. Count: " + all.size());
        Assert.assertTrue(all.size() > 0, "No countries found in dropdown!");
    }

    @Test(priority = 11, description = "Select India and verify")
    public void t11_selectCountryAndVerify() {
        regPage.selectCountry("India");
        String selected = regPage.getSelectedCountry();
        getTest().info("Selected Country: " + selected);
        Assert.assertTrue("India".equalsIgnoreCase(selected), "Country selection mismatch: " + selected);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        stopDriver();
    }
}