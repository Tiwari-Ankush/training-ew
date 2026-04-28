
package Rediffmail;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MiniProjectWithoutExceptions {
    public static void main(String[] args) {
        // driver setup
        WebDriver driver = null;
//          driver = new EdgeDriver();
        	driver = new ChromeDriver();
            driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
                String baseUrl = "https://mail.rediff.com/cgi-bin/login.cgi";
                driver.get(baseUrl);
           
            // click on create new rediff email id
            
                WebElement createAccount = wait.until(
                		ExpectedConditions.elementToBeClickable(By.cssSelector("a[title='Create new Rediffmail account']"))
//                        ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Create new Rediffmail account']"))
                );
                createAccount.click();
            

            // fullname
                WebElement fullName = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Enter your full name']")));
                fullName.sendKeys("Kamal");       
           

            // rediff mail id
                WebElement rediffId = driver.findElement(By.cssSelector("input[placeholder='Enter Rediffmail ID']"));
                rediffId.clear();
                rediffId.sendKeys("kamal1234");           

            // check availability
                WebElement checkBtn = driver.findElement(
                		By.cssSelector("input[value='Check availability']")
                );
                checkBtn.click();

            // handle suggestions and choose any one
                WebElement sugg = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#recommend_text"))
                );
                WebElement first = sugg.findElement(By.cssSelector("input[type='radio']"));
//                String val = first.getAttribute("value");
                first.click();
           

            // enter passwords
                WebElement pwd = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("newpasswd")));
                pwd.clear();
                pwd.sendKeys("Kamal@1234");

                WebElement repwd = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("newpasswd1")));
                repwd.clear();
                repwd.sendKeys("Kamal@1234");

            
            // click checkbox
                WebElement checkbox = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath("//span[@id='altid_msg']/preceding-sibling::input")
                        )
                );
               checkbox.click();

            // DOB dropdowns handling

            // DAY
                WebElement day = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("select.day")));
                day.click();
                day.sendKeys("20");

                day.sendKeys(Keys.ENTER);
                day.sendKeys(Keys.ESCAPE);

            // MONTH
                WebElement month = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("select.month")));
                month.click();
                month.sendKeys("J");
                month.sendKeys("U");
                month.sendKeys("N");

                month.sendKeys(Keys.ENTER);
                month.sendKeys(Keys.ESCAPE);

            // YEAR
                WebElement year = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("select.year")));
                year.click();
                year.sendKeys("2000");

                year.sendKeys(Keys.ENTER);
                year.sendKeys(Keys.ESCAPE);

            // Coutry dropdown handling

            // open dropdown
                WebElement country = wait.until(ExpectedConditions.elementToBeClickable(By.id("country")));
                country.click();
                System.out.println("[INFO] Country dropdown opened.");
           
            // printing all countries in console
                Select countrySelect = new Select(driver.findElement(By.id("country")));
                List<WebElement> all = countrySelect.getOptions();
                System.out.println("All Countries: ");
                
                for (WebElement c : all) {
                    System.out.println(c.getText());
                }
                System.out.println("Total Count of countries = " + all.size());
            

            // selecting india
                WebElement country1 = driver.findElement(By.id("country"));
                country1.sendKeys("India");
                country1.sendKeys(Keys.ENTER);
                country1.sendKeys(Keys.ESCAPE);
                
                Select s2 = new Select(country1);
                String selected = s2.getFirstSelectedOption().getText();

                if ("India".equalsIgnoreCase(selected)) {
                    System.out.println("Country selected as India");
                } else {
                    System.out.println("Country selection mismatch: " + selected);
                }
                System.out.println("Automation flow completed.");

        } finally {
            driver.quit();
            System.out.println("Browser closed successfully.");
        }
        }
}
