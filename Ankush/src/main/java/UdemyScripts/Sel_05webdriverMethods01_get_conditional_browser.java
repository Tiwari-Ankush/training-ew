package UdemyScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Sel_05webdriverMethods01_get_conditional_browser {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
//		driver.get("https://demoqa.com/automation-practice-form");
		driver.manage().window().maximize();
		
		//get methods
		//getTitle() - returns title of the page
//		System.out.println(driver.getTitle());  //OrangeHRM
//
//		//getCurrentUrl() - returns URL of the page
//		System.out.println(driver.getCurrentUrl());
//		
//		//getPageSource()- returns source code of the page
//		System.out.println(driver.getPageSource());
//				
//		//getWindowHandle() - returns ID of the single Browser window
//		String windowid=driver.getWindowHandle();
//		System.out.println("Window ID:"+ windowid); 

		//getWindowHandles() - retuns ID's of the multiple browser windows
//		driver.get("https://www.facebook.com/");
//		driver.findElement(By.linkText("Forgotten password?")).click(); //this opens new window
//		Set<String> windowIds = driver.getWindowHandles();
//		System.out.println(windowIds);
////

///		conditional methods
//		driver.get("https://demo.nopcommerce.com/register");
		
		//isDisplayed()
		
		//WebElement logo=driver.findElement(By.xpath("//img[@alt='nopCommerce demo store']"));
		//System.out.println("Display status of logo:"+logo.isDisplayed()); //true
		
		//boolean status=driver.findElement(By.xpath("//img[@alt='nopCommerce demo store']")).isDisplayed();
		//System.out.println("Display status:"+ status);
		
		
		//isEnabled()
		//boolean status=driver.findElement(By.xpath("//input[@id='FirstName']")).isEnabled();
		//System.out.println("Enable status:"+status);  //true
		
//		//isSelected
//		WebElement male_rd=driver.findElement(By.xpath("//input[@id='gender-male']"));
//		WebElement female_rd=driver.findElement(By.xpath("//input[@id='gender-female']"));
//		
//		System.out.println("Before selection...............");
//		System.out.println(male_rd.isSelected()); //false
//		System.out.println(female_rd.isSelected()); //false
//		
//		System.out.println("After selecting male...");
//		male_rd.click(); // select male radio button
//		
//		System.out.println(male_rd.isSelected()); // true
//		System.out.println(female_rd.isSelected()); //false
//		
//		
//		System.out.println("After selecting female...");
//		female_rd.click(); // select female radio button
//		
//		System.out.println(male_rd.isSelected()); // false
//		System.out.println(female_rd.isSelected()); //true
//		
//		
//		boolean newsletterstatus=driver.findElement(By.xpath("//input[@class='form-check-input' and @type='checkbox']")).isSelected();
//		System.out.println("News letter checkbox status:"+newsletterstatus );  //true
		
		
		
////	browser methods
///
///		navigational methods
		driver.navigate().to("https://www.facebook.com");
		driver.findElement(By.xpath("//a[@role='button' and  @data-testid='open-registration-form-button']")).click();
		
		Thread.sleep(2000);
		driver.navigate().refresh();
		driver.manage().window().minimize();
		driver.navigate().back();
		Thread.sleep(3000);
		driver.navigate().forward();
		driver.manage().window().maximize();
		Thread.sleep(3000);
		
		
		
		driver.quit();

	}

}
