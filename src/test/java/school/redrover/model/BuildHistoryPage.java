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
    public ConsoleOutputPage clickProjectBuildConsole(String projectBuildName){
        getDriver().findElement(By.xpath("//a[contains(@href, '" + projectBuildName + "')  and contains(@href, 'console') and not(contains(@href, 'default'))]")).click();
        return new ConsoleOutputPage(getDriver());
    }

    public String getStatusMessageText() {
        getDriver().navigate().refresh();
        return getDriver().findElement(By.xpath("//table[@id='projectStatus']/tbody/tr/td[4]")).getText();
    }

    public BuildHistoryPage clickBuildNameOnTimeline(String projectBuildName) {
        getDriver().findElement(By.xpath("//div[contains(text(), '" + projectBuildName + "')]")).click();
        return this;
    }

    public String getBubbleTitleOnTimeline() {
        getWait5().until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@class='simileAjax-bubble-contentContainer simileAjax-bubble-contentContainer-pngTranslucent']")));
        return getDriver().findElement(By.xpath("//div[@class='timeline-event-bubble-title']/a")).getText();
    }


}
