package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;

public class BuildHistoryPage extends BaseMainHeaderPage<BuildHistoryPage> {
    public BuildHistoryPage(WebDriver driver) {
        super(driver);
    }

    public BuildPage clickPipelineProjectBuildNumber(String projectName) {

        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/job/" + projectName + "/1/']")))
                .click();

        return new BuildPage(getDriver());
    }
}
