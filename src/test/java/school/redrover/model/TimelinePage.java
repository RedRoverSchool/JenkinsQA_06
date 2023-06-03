package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;

public class TimelinePage extends BaseMainHeaderPage<TimelinePage> {

    public TimelinePage(WebDriver driver) {
        super(driver);
    }

    public ConsoleOutputPage clickBuildIcon() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.cssSelector("[href$='console']"))).click();
        return new ConsoleOutputPage(getDriver());
    }
}
