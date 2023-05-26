package school.redrover.model.base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import school.redrover.model.MultibranchPipelineConfigPage;

import java.time.Duration;

public abstract class BasePage {

    private WebDriverWait wait2;
    private WebDriverWait wait5;
    private WebDriverWait wait10;

    private WebDriverWait wait20;
    private final WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected WebDriver getDriver() {
        return driver;
    }

    protected WebDriverWait getWait5() {
        if (wait5 == null) {
            wait5 = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        }
        return wait5;
    }

    protected WebDriverWait getWait2() {
        if (wait2 == null) {
            wait2 = new WebDriverWait(getDriver(), Duration.ofSeconds(2));
        }
        return wait2;
    }

    protected WebDriverWait getWait10() {
        if (wait10 == null) {
            wait10 = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        }
        return wait10;
    }

    protected WebDriverWait getWait20() {
        if (wait20 == null) {
            wait20 = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        }
        return wait20;
    }

    protected void click(WebElement element) {

        wait10ElementToBeVisible(element);
        wait10ElementToBeClickable(element).click();
    }

    protected void clear(WebElement element) {

        wait20ElementToBeClickable(element).clear();
    }

    protected void input(String text, WebElement element) {

        click(element);
        element.sendKeys(text);
    }

    protected void sendTextToInput(WebElement element, String text) {
        click(element);
        clear(element);
        input(text, element);
    }

    protected WebElement wait10ElementToBeClickable(WebElement element) {

        return getWait10().until(ExpectedConditions.elementToBeClickable(element));
    }

    protected WebElement wait20ElementToBeClickable(WebElement element) {

        return getWait20().until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void wait10ElementToBeVisible(WebElement element) {
        getWait10().until(ExpectedConditions.visibilityOf(element));
    }

    protected void wait20ElementToBeVisible(WebElement element) {
        getWait20().until(ExpectedConditions.visibilityOf(element));
    }

    protected String getText(WebElement element) {

        if (!element.getText().isEmpty()) {
            wait20ElementToBeVisible(element);
        }
        return element.getText();
    }

    protected void scrollToElementByJavaScript(WebElement element) {
        JavascriptExecutor jsc = (JavascriptExecutor) getDriver();
        jsc.executeScript("arguments[0].scrollIntoView();", wait20ElementToBeClickable(element));
    }

    protected void clickByJavaScript(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        executor.executeScript("arguments[0].click();", element);
    }
}

