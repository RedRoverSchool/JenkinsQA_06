package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import school.redrover.model.base.BaseModel;
import school.redrover.model.base.BasePage;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

public class BuildPage extends BaseModel {

    public BuildPage(WebDriver driver) {
        super(driver);
    }

    private WebElement getStatusOfBuild() {
        return getDriver().findElement(By.xpath("//td[normalize-space()='broken since this build']"));
    }

    private WebElement getBuildHistoryTitle() {
        return getDriver().findElement(By.xpath("//a[normalize-space()='Build']"));
    }

    public WebElement getBuildHeader() {
        return getDriver().findElement(By.xpath("//h1"));
    }

    public String getStatusMessageText(BaseTest baseTest) {

        return TestUtils.getText(baseTest, getStatusOfBuild());
    }

    public BuildPage scrollToIconElement(BaseTest baseTest) {
        TestUtils.scrollToElementByJavaScript(baseTest, getBuildHistoryTitle());
        return new BuildPage(getDriver());
    }
}
