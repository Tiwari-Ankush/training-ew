package UdemyScripts;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Sel_01LocatorsDemo {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize(); 
		
		driver.get("https://amazon.in/");
		
		//navigate to register page
//		WebElement register1 = driver.findElement(By.xpath("//span[@class='nav-line-2 ']"));
//		register1.click();
		
		//redirect to home page
//		driver.findElement(By.xpath("//i[@class='a-icon a-icon-logo']")).click();
		
		//locators
		// by name and sendkeys
//		WebElement searchboxname = driver.findElement(By.name("field-keywords"));
//		searchboxname.sendKeys("i phone");
		
		//single line find by name with sendkeys
//		driver.findElement(By.name("field-keywords")).sendKeys("iphone");
		
		//by id
//		WebElement logo = driver.findElement(By.id("nav-logo-sprites"));
//		boolean status = logo.isDisplayed();
//		if(status) {
//		System.out.println("logo is displayed");}
//		
//		if(driver.findElement(By.id("nav-logo-sprites")).isDisplayed()) {
//			System.out.println("logo is displayed");
//		}
		
		//linked text and partial link text - only for link based text
//		WebElement linktext = driver.findElement(By.linkText("Bestsellers")); //preferable method
//		linktext.click();	
		
//		driver.findElement(By.partialLinkText("Bestsel")).click();
		
		
		//classname - findelement and find elements
//		WebElement register1 = driver.findElement(By.xpath("//span[@class='nav-line-2 ']"));
//		register1.click();
//		WebElement search1 = driver.findElement(By.className("a-input-text"));
//		search1.click();
//		search1.sendKeys("ankushtiwari");
		
		//list findelements
//		List<WebElement> headerLinks = driver.findElements(By.className("nav-div"));
//		System.out.println(headerLinks.size());
//		System.out.println(headerLinks.getFirst());
//		System.out.println(headerLinks.getLast());
//		
		
//		//tagname
//		Thread.sleep(2000); // wait till page load and then count links
//		List<WebElement> a_tags = driver.findElements(By.tagName("a"));
//		System.out.println(a_tags.size());
//		System.out.println(a_tags.get(2));
//		System.out.println(a_tags.getClass());
//		System.out.println(a_tags.getFirst());
//		
//		List<WebElement> imgtags = driver.findElements(By.tagName("img"));
//		System.out.println(imgtags.getFirst());
//		System.out.println(imgtags.size());
		
		
//		// findelement() vs findelements()
//		WebElement ele = driver.findElement(By.id("abc"));
//		// no such element exception
		
		List<WebElement> eles = driver.findElements(By.id("abc"));
		System.out.println(eles.size());
		//no exception will through  - it will print 0 in console
		
		
		
		 driver.quit();
}
}
