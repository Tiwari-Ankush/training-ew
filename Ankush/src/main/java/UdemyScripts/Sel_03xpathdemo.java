package UdemyScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Sel_03xpathdemo {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		
		
//		driver.get("https://www.google.com/"); //website changed from xpath with starts-with()
		driver.manage().window().maximize();
		//xpath with single attribute
//		driver.findElement(By.xpath("//textarea[@class='gLFyf']")).sendKeys("https://ankushtiwari.dev");
//		WebElement aitext = driver.findElement(By.xpath("//span[text()='AI Mode']"));
//		aitext.click();
		
		//xpath with multiple attributes
//		driver.findElement(By.xpath("//textarea[@id='APjFqb'][@name='q']")).sendKeys("Ankush Tiwari");
		
		
//		xpath with 'and' 'or' operator
		// driver.findElement(By.xpath("//textarea[@title='Search' and @role='combobox']")).sendKeys("Ankush");
//		driver.findElement(By.xpath("//textarea[@jsname='yZiJbe' or @name='q']")).sendKeys("Ankush");
		
		
//		xpath with text() - inner text
//		driver.findElement(By.xpath("//*[text()='Gmail']")).click();
		
//		String value = driver.findElement(By.xpath("//a[text()='Gmail']")).getText();
//		System.out.println(value); //return string Gmail
		
		
//		xpath with contains()
//		driver.findElement(By.xpath("//img[contains(@title,'Figure')]")).click();
		
		driver.get("https://demoqa.com/automation-practice-form");

		//		xpath with starts-with()
//		driver.manage().window().maximize();
//		driver.findElement(By.xpath("//input[starts-with(@placeholder,'First')]")).sendKeys("ankush.py");
//		
		
//		xpath with chained xpath
		driver.findElement(By.xpath("//div[@id='userEmail-wrapper']/div[2]/input[@placeholder='name@example.com']")).sendKeys("ankushgt25@gmail.com");
		
		Thread.sleep(2000);
		driver.quit();
	}

}
