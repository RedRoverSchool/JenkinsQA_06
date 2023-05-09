package school.redrover.base_abstract;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    private WebDriver driver;

    private WebDriverWait webDriverWait10;

    public WebDriver getDriver() {
        return driver;
    }

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected WebDriverWait getWait10() {
        if (webDriverWait10 == null) {
            webDriverWait10 = new WebDriverWait(driver, Duration.ofSeconds(10));
        }
        return webDriverWait10;
    }

    protected void verifyElementToBeVisible(WebElement element) {

        getWait10().until(ExpectedConditions.visibilityOf(element));
    }

    protected WebElement verifyElementToBeClickable(WebElement element) {

        return getWait10().until(ExpectedConditions.elementToBeClickable(element));
    }

    protected String getText(WebElement element) {
        return element.getText();
    }

    protected void scrollToElement(WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        jse.executeScript("arguments[0].scrollIntoView();", element);
    }

    protected void click(WebElement element) {
        verifyElementToBeVisible(element);
        verifyElementToBeClickable(element).click();
    }

    protected void input(String text, WebElement element) {

        element.sendKeys(text);
    }
}
