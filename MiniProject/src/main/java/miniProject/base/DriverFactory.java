package miniProject.base;


import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class DriverFactory {

    public static WebDriver createInstance(String browser, boolean headless) {
        if (browser == null) browser = "chrome";
        switch (browser.toLowerCase()) {
            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions eOpts = new EdgeOptions();
                if (headless) {
                    eOpts.addArguments("--headless=new");
                }
                eOpts.addArguments("--disable-gpu", "--no-sandbox", "--disable-dev-shm-usage");
                return new EdgeDriver(eOpts);

            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions cOpts = new ChromeOptions();
                if (headless) {
                    cOpts.addArguments("--headless=new");
                }
                cOpts.addArguments("--disable-gpu", "--no-sandbox", "--disable-dev-shm-usage");
                return new ChromeDriver(cOpts);
        }
    }

    public static void commonSetup(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        driver.manage().window().maximize();
    }
}