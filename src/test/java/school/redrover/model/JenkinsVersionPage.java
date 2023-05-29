package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseModel;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

public class JenkinsVersionPage extends BaseModel {
    public JenkinsVersionPage(WebDriver driver) {
        super(driver);
    }

    public JenkinsVersionPage switchJenkinsDocPage () {
        String originalWindow = getDriver().getWindowHandle();
        assert getDriver().getWindowHandles().size() == 1;

        getDriver().findElement(By.xpath("//a[text()='Jenkins 2.387.2']")).click();

        getWait2().until(numberOfWindowsToBe(2));

        for (String winHandle : getDriver().getWindowHandles()) {
            if (!originalWindow.contentEquals(winHandle)) {
                getDriver().switchTo().window(winHandle);
                break;
            }
        }
        return this;
    }

    public WebElement jenkinsPage () {
       return getWait10().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//h1"))));
    }
}
