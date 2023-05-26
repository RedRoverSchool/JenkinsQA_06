package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import school.redrover.model.base.BasePage;

public class BuildPage extends BasePage {

    private static final By statusOfBuild = By.xpath("//td[normalize-space()='broken since this build']");

    private static final By buildHistoryTitle = By.xpath("//a[normalize-space()='Build']");

    public BuildPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getBuildHeader() {
        return getDriver().findElement(By.xpath("//h1"));
    }

    public String getStatusMessageText() {

        return getText((WebElement) statusOfBuild);
    }

    public BuildPage scrollToIconElement() {
        scrollToElementByJavaScript((WebElement) buildHistoryTitle);
        return new BuildPage(getDriver());
    }
}

