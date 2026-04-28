package rediff.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {

    WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Full name
    @FindBy(css = "input[placeholder='Enter your full name']")
    private WebElement fullName;

    // Rediff ID
    @FindBy(css = "input[placeholder='Enter Rediffmail ID']")
    private WebElement rediffId;

    // Check availability
    @FindBy(css = "input[value='Check availability']")
    private WebElement checkAvailabilityBtn;

    // Suggestion section
    @FindBy(css = "#recommend_text")
    private WebElement recommendTextBox;

    // Password
    @FindBy(id = "newpasswd")
    private WebElement password;

    // Re-enter password
    @FindBy(id = "newpasswd1")
    private WebElement rePassword;

    // Checkbox (alternate email msg area)
    @FindBy(xpath = "//span[@id='altid_msg']/preceding-sibling::input")
    private WebElement altEmailCheckbox;

    // DOB dropdowns
    @FindBy(css = "select.day")
    private WebElement dayDropdown;

    @FindBy(css = "select.month")
    private WebElement monthDropdown;

    @FindBy(css = "select.year")
    private WebElement yearDropdown;

    // Country dropdown
    @FindBy(id = "country")
    private WebElement countryDropdown;

    // -------- Actions / Methods --------

    public void enterFullName(String name) {
        fullName.clear();
        fullName.sendKeys(name);
    }

    public void enterRediffId(String id) {
        rediffId.clear();
        rediffId.sendKeys(id);
    }

    public void clickCheckAvailability() {
        checkAvailabilityBtn.click();
    }

    // selects first suggestion if present
    public void selectFirstSuggestionIfAvailable() {
        try {
            WebElement firstRadio = recommendTextBox.findElement(By.cssSelector("input[type='radio']"));
            firstRadio.click();
            System.out.println("[INFO] Selected suggested ID: " + firstRadio.getAttribute("value"));
        } catch (Exception e) {
            System.out.println("[INFO] No suggestion radio found (may already be available).");
        }
    }

    public void enterPassword(String pwd) {
        password.clear();
        password.sendKeys(pwd);
    }

    public void enterRePassword(String pwd) {
        rePassword.clear();
        rePassword.sendKeys(pwd);
    }

    

    public void clickAltEmailCheckbox() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Scroll into view
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", altEmailCheckbox);

        // Wait until clickable
        wait.until(ExpectedConditions.elementToBeClickable(altEmailCheckbox));

        // Click
        if (!altEmailCheckbox.isSelected()) {
            altEmailCheckbox.click();
        }
    }

    public void setDOB(String day, String month, String year) {
        new Select(dayDropdown).selectByVisibleText(day);
        new Select(monthDropdown).selectByVisibleText(month);
        new Select(yearDropdown).selectByVisibleText(year);
    }

    public void printAllCountries() {
        Select s = new Select(countryDropdown);
        List<WebElement> options = s.getOptions();
        System.out.println("All Countries:");
        for (WebElement opt : options) {
            System.out.println(opt.getText());
        }
        System.out.println("Total Count of countries = " + options.size());
    }

    public void selectCountry(String countryName) {
        Select s = new Select(countryDropdown);
        s.selectByVisibleText(countryName);

        String selected = s.getFirstSelectedOption().getText();
        if (selected.equalsIgnoreCase(countryName)) {
            System.out.println("Country selected as " + selected);
        } else {
            System.out.println("Country selection mismatch: " + selected);
        }
    }
}