package tutorialsNinja;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class homePage {

	WebDriver driver;

//	================  with pagefactory =============

//constructor
	homePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this); //mandatory thing
	}

	// locators
	@FindBy(xpath = "//span[normalize-space()='My Account']")
	WebElement myAccount_txt;
	@FindBy(xpath = "//a[normalize-space()='Login']")
	WebElement loginOption_text;
	@FindBy(xpath = "//a[normalize-space()='Qafox.com']")
	WebElement logo_path;

	// actionmethods
	public void clickMyAccount() {
		myAccount_txt.click();
	}

	public void clickLoginOption() {
		loginOption_text.click();
	}

	public boolean checkLogoDisp() {
		return logo_path.isDisplayed();
	}

//	//how to capture like how to use findbyelementS here;;
////	@FindBy(tagName ="a")
////	List<WebElement> list;

	//// ================ without pagefactory =============
	// constructor
//	homePage(WebDriver driver) {
//		this.driver = driver;
//	}
//	// locators
//	By myAccount_txt = By.xpath("//span[normalize-space()='My Account']");
//	By loginOption_text = By.xpath("//a[normalize-space()='Login']");
//	By logo_path = By.xpath("//a[normalize-space()='Qafox.com']");
//
//	// action methods
//	public void clickMyAccount() {
//		driver.findElement(myAccount_txt).click();
//	}
//	
//	public void clickLoginOption() {
//		driver.findElement(loginOption_text).click();
//	}
//	
//	public boolean checkLogoDisp() {
//		return driver.findElement(logo_path).isDisplayed();
//	}

}
