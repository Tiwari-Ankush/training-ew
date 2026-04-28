package Rediffmail;

import java.time.Duration;
import java.util.List;
import java.util.Scanner;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MiniProjectModular {
	
	public static WebDriverWait getWait(WebDriver driver) {
	    return new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public static void Redirection(WebDriver driver) {
		WebDriverWait wait = getWait(driver);
		try {
			WebElement createAccount = wait.until(ExpectedConditions
					.elementToBeClickable(By.cssSelector("a[title='Create new Rediffmail account']")));
			createAccount.click();
		} catch (Exception e) {
			System.out.println("Unable to click create account: " + e.getMessage());
		}
	}

	public static void fullname(WebDriver driver) {
		WebDriverWait wait = getWait(driver);
		try {
			WebElement fullName = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.cssSelector("input[placeholder='Enter your full name']")));
			fullName.sendKeys("Kamal");
		} catch (Exception e) {
			System.out.println("Unable to enter full name: " + e.getMessage());
		}

	}

	public static void email(WebDriver driver) {
		WebDriverWait wait = getWait(driver);
		try {
			WebElement rediffId = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.cssSelector("div.emailCnt input[placeholder='Enter Rediffmail ID']")));
			rediffId.clear();
			rediffId.sendKeys("kamal1234");
		} catch (Exception e) {
			System.out.println("Unable to enter Rediffmail ID: " + e.getMessage());
		}
	}

	public static void checkAvailaibility(WebDriver driver) {
		WebDriverWait wait = getWait(driver);
		try {
			WebElement checkBtn = wait.until(
					ExpectedConditions.elementToBeClickable(By.cssSelector("input[value='Check availability']")));
			checkBtn.click();
		} catch (Exception e) {
			System.out.println("Unable to click availability: " + e.getMessage());
		}
	}

	public static void suggestionSelection(WebDriver driver) {
		WebDriverWait wait = getWait(driver);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#recommend_text")));

			 WebElement first = wait.until(ExpectedConditions.elementToBeClickable(
			                By.cssSelector("#recommend_text input[type='radio']")));
			 
			String val = first.getAttribute("value");
			System.out.println("suggested email id: "+val);
			first.click();
		} catch (Exception e) {
			System.out.println("Suggestions not displayed. "+e.getMessage());
		}

	}

	public static void passwords(WebDriver driver) {
		WebDriverWait wait = getWait(driver);
		try {
			WebElement pwd = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("newpasswd")));
			pwd.clear();
			pwd.sendKeys("Kamal@1234");

			WebElement repwd = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("newpasswd1")));
			repwd.clear();
			repwd.sendKeys("Kamal@1234");
		} catch (Exception e) {
			System.out.println("Unable to enter password: " + e.getMessage());
		}

	}

	public static void checkbox(WebDriver driver) {
		WebDriverWait wait = getWait(driver);
		try {
			WebElement checkbox = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//span[@id='altid_msg']/preceding-sibling::input")));
			checkbox.click();
		} catch (Exception e) {
			System.out.println("error: "+ e.getMessage());
		}

	}

	public static void dob_day(WebDriver driver) {
		WebDriverWait wait = getWait(driver);
		try {
			WebElement day = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("select.day")));
			day.click();
			day.sendKeys("20");
			day.sendKeys(Keys.ENTER);
			day.sendKeys(Keys.ESCAPE);
		} catch (Exception e) {
			System.out.println("Unable to select Day: " + e.getMessage());
		}

	}

	public static void dob_month(WebDriver driver) {
		WebDriverWait wait = getWait(driver);
		try {
			WebElement month = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("select.month")));
			month.click();
			month.sendKeys("J");
			month.sendKeys("U");
			month.sendKeys("N");
			month.sendKeys(Keys.ENTER);
			month.sendKeys(Keys.ESCAPE);
		} catch (Exception e) {
			System.out.println("Unable to select Month: " + e.getMessage());
		}

	}

	public static void dob_year(WebDriver driver) {
		WebDriverWait wait = getWait(driver);
		try {
			WebElement year = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("select.year")));
			year.click();
			year.sendKeys("2000");
			year.sendKeys(Keys.ENTER);
			year.sendKeys(Keys.ESCAPE);
		} catch (Exception e) {
			System.out.println("Unable to select Year: " + e.getMessage());
		}

	}

	public static void country_open_dropdown(WebDriver driver) {
		WebDriverWait wait = getWait(driver);
		try {
			WebElement country = wait.until(ExpectedConditions.elementToBeClickable(By.id("country")));
			country.click();
		} catch (Exception e) {
			System.out.println("Unable to open Country dropdown: " + e.getMessage());
		}
	}

	public static void country_print(WebDriver driver) {
		WebDriverWait wait = getWait(driver);
		try {
			Select countrySelect = new Select(
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("country"))));
			List<WebElement> all = countrySelect.getOptions();
			System.out.println("All Countries: ");
			for (WebElement c : all) {
				System.out.println(c.getText());
			}
			System.out.println("Total Countries = " + all.size());
		} catch (Exception e) {
			System.out.println("Unable to list countries: " + e.getMessage());
		}

	}

	public static void country_select(WebDriver driver) {
		WebDriverWait wait = getWait(driver);
		try {
			WebElement country = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("country")));
			country.sendKeys("India");
			country.sendKeys(Keys.ENTER);
			country.sendKeys(Keys.ESCAPE);
			Select s2 = new Select(country);
			String selected = s2.getFirstSelectedOption().getText();

			if ("India".equalsIgnoreCase(selected)) {
				System.out.println("Country selected as India");
			} else {
				System.out.println("Country selection mismatch: " + selected);
			}
		} catch (Exception e) {
			System.out.println("Unable to select India: " + e.getMessage());
		}
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter which browser do you want to Automate(Chrome/Edge):");
		String browser = sc.nextLine();
		WebDriver driver;
		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else {
			System.out.println("wrong browser selected.");
			return;
		}

		driver.get("https://mail.rediff.com/cgi-bin/login.cgi");
		driver.manage().window().maximize();

		Redirection(driver);
		fullname(driver);
		email(driver);
		checkAvailaibility(driver);
		suggestionSelection(driver);
		passwords(driver);
		checkbox(driver);

		dob_day(driver);
		dob_month(driver);
		dob_year(driver);

		country_open_dropdown(driver);
		country_print(driver);
		country_select(driver);
		
		sc.close();
		driver.quit();
	}
}
