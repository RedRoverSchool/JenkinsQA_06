package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BasePage;

public class FreestyleProjectPage extends BasePage {

    public FreestyleProjectPage(WebDriver driver) {
        super(driver);
    }

    public FreestyleProjectPage selectBuildNow() {
        getDriver().findElement(By.cssSelector("[href*='build?']")).click();
        return this;
    }

    public FreestyleProjectPage selectBuildHistory() {
        Actions actions = new Actions(getDriver());
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, 'buildHistory')]")));
        actions.moveToElement(getDriver()
                .findElement(By.xpath("//a[contains(@href, 'buildHistory')]")))
                .click().perform();
        return this;
    }

    public BuildPage selectBuildItemTheHistoryOnBuildPage() {

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[href$='console']"))).click();

        return new BuildPage(getDriver());
    }
}
