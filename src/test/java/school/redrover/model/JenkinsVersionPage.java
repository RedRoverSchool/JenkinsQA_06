package school.redrover.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseModel;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

public class JenkinsVersionPage extends BaseModel {

    @FindBy(xpath = "//a[text()='Jenkins 2.387.2']")
    private WebElement jenkinsVersionLink;

    @FindBy(xpath = "//h1")
    private WebElement jenkinsPageTitle;

    public JenkinsVersionPage(WebDriver driver) {
        super(driver);
    }

    public JenkinsVersionPage switchJenkinsDocPage() {
        String originalWindow = getDriver().getWindowHandle();
        assert getDriver().getWindowHandles().size() == 1;

        jenkinsVersionLink.click();

        getWait2().until(numberOfWindowsToBe(2));

        for (String winHandle : getDriver().getWindowHandles()) {
            if (!originalWindow.contentEquals(winHandle)) {
                getDriver().switchTo().window(winHandle);
                break;
            }
        }
        return this;
    }

    public String getJenkinsPageTitle() {
        return getWait10().until(ExpectedConditions.visibilityOf(jenkinsPageTitle)).getText();
    }
}
