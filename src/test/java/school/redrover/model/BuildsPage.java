package school.redrover.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.model.base.BasePage;

public class BuildsPage extends BasePage {

    @FindBy(xpath = "//td[normalize-space()='broken since this build']")
    private WebElement statusOfBuild;

    @FindBy(xpath = "//div[@class='jenkins-app-bar__content']")
    private WebElement buildHistoryTitle;

    public String getStatusMessageText() {

        return getText(statusOfBuild);
    }

    public BuildsPage scrollToIconElement() {
        scrollToElementByJavaScript(buildHistoryTitle);
        return new BuildsPage(getDriver());
    }

    public BuildsPage(WebDriver driver) {
        super(driver);
    }
}
