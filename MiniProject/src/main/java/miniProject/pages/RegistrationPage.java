package miniProject.pages;

import miniProject.utils.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class RegistrationPage {
    private final WebDriver driver;
    private final ElementActions act;

    // Locators based on your original code
    private final By fullName = By.cssSelector("input[placeholder='Enter your full name']");
    private final By emailId = By.cssSelector("div.emailCnt input[placeholder='Enter Rediffmail ID']");
    private final By checkAvailabilityBtn = By.cssSelector("input[value='Check availability']");
    private final By suggestionsBox = By.cssSelector("#recommend_text");
    private final By firstSuggestionRadio = By.cssSelector("#recommend_text input[type='radio']");
    private final By password = By.id("newpasswd");
    private final By rePassword = By.id("newpasswd1");
    private final By altIdCheckBox = By.xpath("//span[@id='altid_msg']/preceding-sibling::input");
    private final By daySelect = By.cssSelector("select.day");
    private final By monthSelect = By.cssSelector("select.month");
    private final By yearSelect = By.cssSelector("select.year");
    private final By countrySelect = By.id("country");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.act = new ElementActions(driver);
    }

    public RegistrationPage enterFullName(String name) {
        act.type(fullName, name, true);
        return this;
    }

    public RegistrationPage enterEmailId(String id) {
        act.type(emailId, id, true);
        return this;
    }

    public RegistrationPage clickCheckAvailability() {
        act.click(checkAvailabilityBtn);
        return this;
    }

    public String chooseFirstSuggestion() {
        act.waitVisible(suggestionsBox);
        WebElement first = act.waitClickable(firstSuggestionRadio);
        String val = first.getAttribute("value");
        first.click();
        return val;
    }

    public RegistrationPage setPasswords(String pwd) {
        act.type(password, pwd, true);
        act.type(rePassword, pwd, true);
        return this;
    }

    public RegistrationPage toggleAltIdCheckbox() {
        act.click(altIdCheckBox);
        return this;
    }

    public RegistrationPage selectDOB(String day, String monthPartial, String year) {
        // Your original used sendKeys (“20” / “J” “U” “N”). Matching that behavior:
        act.click(daySelect);
        act.type(daySelect, day, false);
        act.press(daySelect, Keys.ENTER);
        act.press(daySelect, Keys.ESCAPE);

        act.click(monthSelect);
        for (char c : monthPartial.toCharArray()) {
            act.type(monthSelect, String.valueOf(c), false);
        }
        act.press(monthSelect, Keys.ENTER);
        act.press(monthSelect, Keys.ESCAPE);

        act.click(yearSelect);
        act.type(yearSelect, year, false);
        act.press(yearSelect, Keys.ENTER);
        act.press(yearSelect, Keys.ESCAPE);

        return this;
    }

    public RegistrationPage openCountryDropdown() {
        act.click(countrySelect);
        return this;
    }

    public List<WebElement> getAllCountries() {
        return act.getOptions(countrySelect);
    }

    public RegistrationPage selectCountry(String countryName) {
        act.selectBySendKeys(countrySelect, countryName);
        return this;
    }

    public String getSelectedCountry() {
        return act.getFirstSelectedOptionText(countrySelect);
    }
}