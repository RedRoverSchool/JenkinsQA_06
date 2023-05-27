package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import school.redrover.model.base.BaseModel;

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

        return getText(getStatusOfBuild());
    }

    public BuildPage scrollToIconElement() {
        scrollToElementByJavaScript(getBuildHistoryTitle());
        return new BuildPage(getDriver());
    }
}
