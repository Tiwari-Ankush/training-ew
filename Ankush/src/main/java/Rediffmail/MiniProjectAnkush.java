
package Rediffmail;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MiniProjectAnkush {

    public static void main(String[] args) {

        // driver setup
        WebDriver driver = null;
        try {
//          driver = new EdgeDriver();
        	driver = new ChromeDriver();
            driver.manage().window().maximize();
            System.out.println("[INFO] Browser launched and maximized.");
        } catch (Exception e) {
            System.out.println("[ERROR] Unable to launch browser: " + e.getMessage());
            }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {

            // open url
            try {
                String baseUrl = "https://mail.rediff.com/cgi-bin/login.cgi";
                driver.get(baseUrl);
                System.out.println("[INFO] Navigated to URL: " + baseUrl);
                Thread.sleep(2000);
            } catch (Exception e) {
                System.out.println("[ERROR] Unable to open URL: " + e.getMessage());
            }

            // click on create new rediff email id
            try {
                WebElement createAccount = wait.until(
                        ExpectedConditions.elementToBeClickable(By.linkText("Get a new Rediffmail ID"))
                );
                createAccount.click();
                System.out.println("[INFO] Clicked 'Get a new Rediffmail ID'");
                Thread.sleep(2000);
            } catch (Exception e) {
                System.out.println("[ERROR] Unable to click create account: " + e.getMessage());
            }

            // fullname
            try {
                WebElement fullName = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("input[placeholder='Enter your full name']"))
                );
                fullName.sendKeys("Kamal");
                
                Thread.sleep(2000);         
                
                System.out.println("[INFO] Entered Full Name.");
            } catch (Exception e) {
                System.out.println("[ERROR] Unable to enter full name: " + e.getMessage());
            }

            // rediff mail id
            try {
                WebElement rediffId = driver.findElement(
                        By.cssSelector("div.emailCnt input[placeholder='Enter Rediffmail ID']")
                );
                rediffId.clear();
                rediffId.sendKeys("kamal1234");
                
                Thread.sleep(2000);
                
                System.out.println("[INFO] Entered Rediffmail ID.");
            } catch (Exception e) {
                System.out.println("[ERROR] Unable to enter Rediffmail ID: " + e.getMessage());
            }

            // check availability
            try {
                WebElement checkBtn = driver.findElement(
                        By.xpath("/html/body/div[2]/div[2]/form/div/div[4]/input")
                );
                checkBtn.click();
                System.out.println("[INFO] Clicked 'Check Availability'");
                Thread.sleep(3000);
            } catch (Exception e) {
                System.out.println("[ERROR] Unable to click availability: " + e.getMessage());
            }

            // handle suggestions and choose any one
            try {
                WebElement sugg = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#recommend_text"))
                );
                WebElement first = sugg.findElement(By.cssSelector("input[type='radio']"));
                String val = first.getAttribute("value");
                first.click();
                System.out.println("[INFO] Selected Suggested ID: " + val);
                Thread.sleep(3000);
            } catch (Exception e) {
                System.out.println("[INFO] Suggestions not displayed.");
            }

            // enter passwords
            try {
                WebElement pwd = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("newpasswd")));
                pwd.clear();
                pwd.sendKeys("Kamal@1234");
                Thread.sleep(2000);

                WebElement repwd = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("newpasswd1")));
                repwd.clear();
                repwd.sendKeys("Kamal@1234");
                Thread.sleep(2000);

                System.out.println("[INFO] Password and Retype Password entered.");
            } catch (Exception e) {
                System.out.println("[ERROR] Unable to enter password: " + e.getMessage());
            }

            // click checkbox
            try {
                WebElement checkbox = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath("//span[contains(text(), \"Click if you don't have an alternate ID\")]/preceding-sibling::input")
                        )
                );
                
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",checkbox);
                checkbox.click();
                
                System.out.println("[INFO] Checkbox clicked.");
                Thread.sleep(2000);
            } catch (Exception e) {
                System.out.println("[ERROR] Unable to click checkbox: " + e.getMessage());
            }

            // DOB dropdowns handling

            // DAY
            try {
                WebElement day = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("select.day")));
                day.click();

                Thread.sleep(2000);
                day.sendKeys("20");

                Thread.sleep(2000);
                day.sendKeys(Keys.ENTER);
                day.sendKeys(Keys.ESCAPE);
                System.out.println("[INFO] Selected Day = 20");
            } catch (Exception e) {
                System.out.println("[ERROR] Unable to select Day: " + e.getMessage());
            }

            // MONTH
            try {
                WebElement month = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("select.month")));
                month.click();
                
                Thread.sleep(2000);
                month.sendKeys("J");
                month.sendKeys("U");
                month.sendKeys("N");

                Thread.sleep(2000);
                month.sendKeys(Keys.ENTER);
                month.sendKeys(Keys.ESCAPE);
                System.out.println("[INFO] Selected Month = JUN");
            } catch (Exception e) {
                System.out.println("[ERROR] Unable to select Month: " + e.getMessage());
            }

            // YEAR
            try {
                WebElement year = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("select.year")));
                year.click();
                
                Thread.sleep(2000);
                year.sendKeys("2000");

                Thread.sleep(2000);
                year.sendKeys(Keys.ENTER);
                year.sendKeys(Keys.ESCAPE);
                System.out.println("[INFO] Selected Year = 2000");
            } catch (Exception e) {
                System.out.println("[ERROR] Unable to select Year: " + e.getMessage());
            }

            // printing dob
            try {
                Select dd = new Select(driver.findElement(By.cssSelector("select.day")));
                Select mm = new Select(driver.findElement(By.cssSelector("select.month")));
                Select yy = new Select(driver.findElement(By.cssSelector("select.year")));

                System.out.println("[INFO] Final DOB = "
                        + dd.getFirstSelectedOption().getText() + "-"
                        + mm.getFirstSelectedOption().getText() + "-"
                        + yy.getFirstSelectedOption().getText());
            } catch (Exception e) {
                System.out.println("[ERROR] Unable to print DOB: " + e.getMessage());
            }

            // Coutry dropdown handling

            // open dropdown
            try {
                WebElement country = wait.until(ExpectedConditions.elementToBeClickable(By.id("country")));
                country.click();
                System.out.println("[INFO] Country dropdown opened.");
            } catch (Exception e) {
                System.out.println("[ERROR] Unable to open Country dropdown: " + e.getMessage());
            }

            // printing all countries in console
            try {
                Select countrySelect = new Select(driver.findElement(By.id("country")));
                List<WebElement> all = countrySelect.getOptions();
                System.out.println("All Countries: ");
                for (WebElement c : all) {
                    System.out.println(c.getText());
                }
                System.out.println("Total Countries = " + all.size());
            } catch (Exception e) {
                System.out.println("[ERROR] Unable to list countries: " + e.getMessage());
            }

            // selecting india
            try {
                WebElement country = driver.findElement(By.id("country"));
                country.sendKeys("India");
                country.sendKeys(Keys.ENTER);
                
                Thread.sleep(2000);
                country.sendKeys(Keys.ESCAPE);
                
                Thread.sleep(2000);
                
                Select s2 = new Select(country);
                String selected = s2.getFirstSelectedOption().getText();

                if ("India".equalsIgnoreCase(selected)) {
                    System.out.println("[PASS] Country selected as India");
                } else {
                    System.out.println("[FAIL] Country selection mismatch: " + selected);
                }
            } catch (Exception e) {
                System.out.println("[ERROR] Unable to select India: " + e.getMessage());
            }

            System.out.println("[DONE] Automation flow completed.");

        } finally {
        	//exit the browser
            driver.quit();
            System.out.println("[INFO] Browser closed successfully.");
        }
    }
}
