package tutorialsNinja;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class loginPage {

	WebDriver driver;

//	================  with pagefactory =============

	// constructor
	loginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this); //mandatory thing
	}

	// locators
	@FindBy(id = "input-email") WebElement emailField;
	@FindBy(id = "input-password")
	WebElement passwdField;
	@FindBy(xpath = "//input[@value='Login']")
	WebElement loginBtn;

	// actionmethods
	public void setUsername(String user) {
		emailField.sendKeys(user);
	}

	public void setPassword(String pass) {
		passwdField.sendKeys(pass);
	}

	public void clickLoginBtn() {
		loginBtn.click();
	}

////	================  without pagefactory =============
//	// constructor
//	loginPage(WebDriver driver) {
//		this.driver = driver;
//	}
//	// locators
//	By emailField = By.id("input-email");
//	By passwdField = By.id("input-password");
//	By loginBtn = By.xpath("//input[@value='Login']");
//
//	// action methods
//	public void setUsername(String user) {
//		driver.findElement(emailField).sendKeys(user);
//	}
//
//	public void setPassword(String pass) {
//		driver.findElement(passwdField).sendKeys(pass);
//	}
//
//	public void clickLoginBtn() {
//		driver.findElement(loginBtn).click();
//	}

}
