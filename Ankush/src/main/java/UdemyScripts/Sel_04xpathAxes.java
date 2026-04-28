package UdemyScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Sel_04xpathAxes {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		driver.get("https://demoqa.com/automation-practice-form");
		driver.manage().window().maximize();
		
//		self
		// driver.findElement(By.xpath("//input[@id='userEmail']/self::input")).sendKeys("Ankush");
		
//		parent
		//if(driver.findElement(By.xpath("//input[@id='userEmail']/parent::div")).isDisplayed()){
			//System.out.println("present");
		//}
		
//		child
//		driver.findElement(By.xpath("//div[@id='userNumber-wrapper']/div[2]/child::input[@placeholder='Mobile Number']")).sendKeys("1234567890");
		
//		ancestors -select all ancestors((parent, grandparent, etc.) )
//		if(driver.findElement(By.xpath("//input[@id='userEmail']/ancestor::div[@id='userEmail-wrapper']")).isDisplayed()) {
//			System.out.println("Displayed");
//		};
		
//		descendant -Selects all descendants (children, grandchildren, etc.) of the current node
//		driver.findElement(By.xpath("//div[@id='userEmail-wrapper']/descendant::input[@id='userEmail']")).sendKeys("ankush");
		
//		following - Selects everything in the document after the closing tag of the current node
//		driver.findElement(By.xpath("//div[@id='genterWrapper']/following::input[@placeholder='Mobile Number']")).sendKeys("Ankush");
//		
//		preceding - Selects all nodes that appear before the current node in the document
//		if(driver.findElement(By.xpath("//button[@id='submit']/preceding::label[@id='userName-label']")).isDisplayed()){
//			System.out.println("located");
//		};
		
//		following-sibling => Selects all siblings after the current node	
//		if(driver.findElement(By.xpath("//input[@id='firstName']/parent::div/following-sibling::div[2]")).isDisplayed()){
//			System.out.println("located");
//		};
//		
//		driver.findElement(By.xpath("//input[@id='firstName']/parent::div/following-sibling::div[2]/child::input[@placeholder='Last Name']")).sendKeys("Tiwari");
		
//		preceding-sibling - Selects all siblings before the current node
//		if(driver.findElement(By.xpath("//input[@id='firstName']/parent::div/preceding-sibling::div[1]//label[@id='userName-label']")).isDisplayed()){
//			System.out.println("located");
//		};
		
		Thread.sleep(2000);
		driver.quit();
	}

}
