package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.model.base.BasePage;

public class BuildPage extends BasePage {

    @FindBy(xpath = "//td[normalize-space()='broken since this build']")
    private WebElement statusOfBuild;

    @FindBy(xpath = "//a[normalize-space()='Build']")
    private WebElement buildHistoryTitle;
    public BuildPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getBuildHeader() {
        return getDriver().findElement(By.xpath("//h1"));
    }

    public String getStatusMessageText() {

        return getText(statusOfBuild);
    }

    public BuildPage scrollToIconElement() {
        scrollToElementByJavaScript(buildHistoryTitle);
        return new BuildPage(getDriver());
    }
}
