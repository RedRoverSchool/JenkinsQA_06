package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;

import java.util.ArrayList;
import java.util.List;

public class TimelinePage extends BaseMainHeaderPage<TimelinePage> {

    @FindBy(css = "[href$='console']")
    private WebElement buildIcon;

    public TimelinePage(WebDriver driver) {
        super(driver);
    }

    public ConsoleOutputPage clickBuildIcon() {
        getWait5().until(ExpectedConditions.elementToBeClickable(buildIcon)).click();

        return new ConsoleOutputPage(getDriver());
    }

    public List<String> getBuildNumbers(int numbersOfBuild) {
        List<String> buildNumber = new ArrayList<>();
        for (int i = 1; i <= numbersOfBuild; i++) {
            buildNumber.add(getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[href='" + i + "/']"))).getText());
        }
        return buildNumber;
    }
}
