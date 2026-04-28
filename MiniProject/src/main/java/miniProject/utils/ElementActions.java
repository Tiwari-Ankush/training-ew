package miniProject.utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class ElementActions {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public ElementActions(WebDriver driver) {
        int timeout = ConfigReader.getInt("timeout", 10);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }

    public WebElement waitVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void click(By locator) {
        waitClickable(locator).click();
    }

    public void type(By locator, String text, boolean clear) {
        WebElement el = waitVisible(locator);
        if (clear) el.clear();
        el.sendKeys(text);
    }

    public void press(By locator, Keys key) {
        waitVisible(locator).sendKeys(key);
    }

    public void selectByVisibleText(By locator, String text) {
        Select select = new Select(waitVisible(locator));
        select.selectByVisibleText(text);
    }

    public void selectBySendKeys(By locator, String text) {
        WebElement el = waitClickable(locator);
        el.click();
        el.sendKeys(text);
        el.sendKeys(Keys.ENTER);
        el.sendKeys(Keys.ESCAPE);
    }

    public List<WebElement> getOptions(By locator) {
        Select select = new Select(waitVisible(locator));
        return select.getOptions();
    }

    public String getFirstSelectedOptionText(By locator) {
        Select select = new Select(waitVisible(locator));
        return select.getFirstSelectedOption().getText();
    }
}