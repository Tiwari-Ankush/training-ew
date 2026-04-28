package AutomationProjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BookMyShow {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize(); 
		driver.get("https://in.bookmyshow.com/");
//		
		WebElement searchbox  = driver.findElement(By.id("dummy"));
		searchbox.sendKeys("chennai");
		

		Thread.sleep(1000);
		WebElement chennaiItem = driver.findElement(By.xpath("//*[@id=\"bottomSheet-model-close\"]/div/div/div[1]/div[2]/div/div/div[1]/span"));

        chennaiItem.click();
	}

}
