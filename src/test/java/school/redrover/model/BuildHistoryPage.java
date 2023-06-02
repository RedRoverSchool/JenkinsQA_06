package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;
import school.redrover.runner.TestUtils;

public class BuildHistoryPage extends BaseMainHeaderPage<BuildHistoryPage> {
    public BuildHistoryPage(WebDriver driver) {
        super(driver);
    }

    public BuildPage clickPipelineProjectBuildNumber(String projectName) {

        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/job/" + projectName + "/1/']")))
                .click();

        return new BuildPage(getDriver());
    }
    public ConsoleOutputPage clickProjectBuildConsole(String projectBuildName){
        getDriver().findElement(By.xpath("//a[contains(@href, '" + projectBuildName + "')  and contains(@href, 'console') and not(contains(@href, 'default'))]")).click();
        return new ConsoleOutputPage(getDriver());
    }

    public String getStatusMessageText() {
        return TestUtils.getText(this, getDriver().findElement(By.xpath("//td[normalize-space()='broken since this build']")));
    }
}
