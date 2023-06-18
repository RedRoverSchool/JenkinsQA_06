package school.redrover.model.jobs;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.*;
import school.redrover.model.jobsconfig.PipelineConfigPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.model.base.BaseProjectPage;

public class PipelinePage extends BaseProjectPage<PipelinePage> {
    @FindBy(xpath = "//a[contains(@data-url,'/doDelete')]")
    private WebElement delete;

    @FindBy(xpath = "//div[@id='main-panel']")
    private WebElement mainPanel;

    @FindBy(xpath = "//div[@id = 'tasks']/div[3]//a")
    private WebElement buildNow;

    @FindBy(xpath = "//div[@class='build-icon']//a[@tooltip='Success > Console Output']")
    private WebElement buildIcon;

    @FindBy(css = ".stage-header-name-0")
    private WebElement stage;

    @FindBy(css = ".build-icon")
    private WebElement build;

    @FindBy(css = ".console-output")
    private WebElement consoleOutput;

    @FindBy(xpath = "//a[contains(text() ,'#1')]")
    private WebElement build1;

    @FindBy(css = "#buildHistory>div>div>span>div>:nth-child(2)")
    private WebElement trend;

    @FindBy(xpath = "//a[contains(@href, 'changes')]")
    private WebElement change;

    @FindBy(id = "enable-project")
    private WebElement enable;

    public PipelinePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public PipelineConfigPage clickConfigure() {
        setupClickConfigure();
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
        String projectName = mainPanel.getText();
        String subStr = projectName.substring(projectName.indexOf(':') + 2);
        return subStr.substring(0, subStr.indexOf("Add")).trim();
    }

    public PipelinePage clickBuildNow() {
        buildNow.click();
        buildIcon.isDisplayed();
        return this;
    }

    public String getStage() {
        return stage.getText();
    }

    public PipelinePage clickBuildIcon() {
        build.click();
        return this;
    }

    public String getConsoleOutputField() {
        return consoleOutput.getText();
    }

    public BuildPage click1BuildHistory() {
        build1.sendKeys(Keys.ENTER);
        return new BuildPage(getDriver());
    }

    public TimelinePage clickTrend() {
        trend.click();
        return new TimelinePage(getDriver());
    }

    public ChangesPage<PipelinePage> clickChangeOnLeftSideMenu() {
        change.click();
        return new ChangesPage<>(this);
    }

    public PipelinePage checkWarningMessage() {
        enable.getText().contains("This project is currently disabled");
        return this;
    }
}
