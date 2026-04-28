package rediff.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginLandingPage {

    WebDriver driver;

    public LoginLandingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Create new account link
    @FindBy(css = "a[title='Create new Rediffmail account']")
    private WebElement createAccountLink;

    public void clickCreateAccount() {
        createAccountLink.click();
    }
}