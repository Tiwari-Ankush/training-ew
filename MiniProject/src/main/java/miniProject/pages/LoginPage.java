package miniProject.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import miniProject.utils.ElementActions;

public class LoginPage {
    private final WebDriver driver;
    private final ElementActions act;

    private final By createAccountLink = By.cssSelector("a[title='Create new Rediffmail account']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.act = new ElementActions(driver);
    }

    public RegistrationPage goToCreateAccount() {
        act.click(createAccountLink);
        return new RegistrationPage(driver);
    }
}