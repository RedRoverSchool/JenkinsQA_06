package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseProjectPage;

public class PipelinePage extends BaseProjectPage<PipelinePage> {


    public PipelinePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public PipelineConfigPage clickConfigure() {
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href, 'configure')]"))).click();
        return new PipelineConfigPage(this);
    }

    public PipelinePage clickDeletePipeline() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@data-url,'/doDelete')]"))).click();
        return this;
    }

    public MainPage acceptAlert() {
        getDriver().switchTo().alert().accept();
        return new MainPage(getDriver());
    }

    public String getProjectNameSubtitle() {
        String projectName = getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='main-panel']"))).getText();
        String subStr = projectName.substring(projectName.indexOf(':') + 2);
        return subStr.substring(0, subStr.indexOf("Add")).trim();
    }

    public PipelinePage clickBuildNow() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id = 'tasks']/div[3]//a")))
                .click();
        getWait10().until(ExpectedConditions.presenceOfElementLocated(By
                .xpath("//div[@class='build-icon']//a[@tooltip='Success > Console Output']")));
        return this;
    }

    public String getStage() {
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".stage-header-name-0"))).getText();
    }

    public PipelinePage clickBuildIcon() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.cssSelector(".build-icon"))).click();
        return this;
    }

    public String getConsoleOutputField() {
        return getWait5().until(ExpectedConditions.elementToBeClickable(By.cssSelector(".console-output"))).getText();
    }

    public BuildPage click1BuildHistory() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text() ,'#1')]"))).sendKeys(Keys.ENTER);
        return new BuildPage(getDriver());
    }

    public TimelinePage clickTrend() {
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#buildHistory>div>div>span>div>:nth-child(2)"))).click();
        return new TimelinePage(getDriver());
    }

    public ChangesPage<PipelinePage> clickChangeOnLeftSideMenu() {
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href, 'changes')]"))).click();
        return new ChangesPage<>(this);
    }

    public PipelinePage checkWarningMessage() {
        getWait2().until(ExpectedConditions.textToBePresentInElement(getDriver().findElement(By.id("enable-project")), "This project is currently disabled"));
        return this;
    }
}
