package school.redrover.model.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    private WebDriverWait wait2;
    private WebDriverWait wait5;
    private WebDriverWait wait10;

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
    protected void click(WebElement element) {

        getWait10().until(ExpectedConditions.visibilityOf(element));
        getWait10().until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    protected void clear(WebElement element) {

        getWait5().until(ExpectedConditions.elementToBeClickable(element)).clear();
    }

    protected void input(String text, WebElement element) {

        element.sendKeys(text);
    }

    protected void sendTextToInput(WebElement element, String text) {
        click(element);
        clear(element);
        input(text, element);
    }

    protected String getText(WebElement element) {

        return element.getText();
    }

}
