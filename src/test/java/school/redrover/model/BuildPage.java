package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.model.base.BasePage;

public class BuildPage extends BasePage {

    public BuildPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getBuildHeader() {
        return getDriver().findElement(By.xpath("//h1"));
    }

    public String getStatusMessageText() {

        WebElement statusOfBuild = getDriver().findElement(By.xpath("//td[normalize-space()='broken since this build']"));
        return getText(statusOfBuild);
    }

    public BuildPage scrollToIconElement() {

        WebElement buildHistoryTitle = getDriver().findElement(By.xpath("//a[normalize-space()='Build']"));
        scrollToElementByJavaScript(buildHistoryTitle);
        return new BuildPage(getDriver());
    }
}
