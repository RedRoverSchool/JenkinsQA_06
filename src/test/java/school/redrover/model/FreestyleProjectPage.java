package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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

    public BuildPage selectBuildItemTheHistoryOnBuildPage() {
        getWait5().until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("[href$='console']"))).click();
        return new BuildPage(getDriver());
    }
}
