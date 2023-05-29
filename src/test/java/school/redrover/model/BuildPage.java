package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseModel;
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

    public String getStatusMessageText() {

        return TestUtils.getText(this, getStatusOfBuild());
    }

    public BuildPage scrollToIconElement() {
        TestUtils.scrollToElementByJavaScript(this, getBuildHistoryTitle());
        return new BuildPage(getDriver());
    }

    public boolean isDisplayedGreenIconV() {

        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[@class='build-status-icon__outer']//*[local-name()='svg']"))).isDisplayed();
    }

    public boolean isDisplayedBuildTitle() {

        return getBuildHeader().getText().contains("Build #1");
    }
}
