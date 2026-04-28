package UdemyScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Sel_02csslocators {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.get("https://amazon.in");
		driver.manage().window().maximize();
		
		//css locators
		// tag and ID - tag#id
		
//		WebElement tagid = driver.findElement(By.cssSelector("input#twotabsearchtextbox")); //not working
//		tagid.click();
//		tagid.sendKeys("Ankush");
//		if(driver.findElement(By.cssSelector("a#nav-logo-sprites")).isDisplayed()) {
//			System.out.println("visible");
//		};
//		
//		WebElement register1 = driver.findElement(By.xpath("//*[@id=\"nav-link-accountList-nav-line-1\"]")); //just for redirection
//		register1.click();
//		
//		driver.findElement(By.cssSelector("input#ap_email_login")).sendKeys("Ankush");
//		
//		driver.findElement(By.cssSelector("a#ab-registration-ingress-link")).click();
		
		
		
		//tag and class tag.className
//		WebElement register1 = driver.findElement(By.xpath("//*[@id=\"nav-link-accountList-nav-line-1\"]")); //just for redirection
//		register1.click();
//		driver.findElement(By.cssSelector("a.a-link-normal")).click();
//		
//		driver.findElement(By.cssSelector("input.b-form-control")).sendKeys("Ankush");
		
		
		//tag and attribute - tag[attribute='value']
//		driver.findElement(By.cssSelector("input[placeholder='Search Amazon.in']")).sendKeys("i phone");
//		driver.findElement(By.cssSelector("input#nav-search-submit-button")).click();
//		driver.findElement(By.cssSelector("input[id='nav-search-submit-button']")).click();
//		driver.findElement(By.cssSelector("#nav-search-submit-button")).click();
		
		
		
		//tag, class & attribute
		WebElement register1 = driver.findElement(By.xpath("//*[@id=\"nav-link-accountList-nav-line-1\"]")); //just for redirection
		register1.click();
		driver.findElement(By.cssSelector("input.a-input-text[name='email']")).sendKeys("Ankush");
		driver.findElement(By.cssSelector("input.a-button-input[type='submit']")).click();
		
		Thread.sleep(2000);
		driver.quit();

	}

}
