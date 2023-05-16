package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class PipelineJobTest extends BaseTest {
    private static final By DASHBOARD = By.xpath("//*[@id=\"breadcrumbs\"]/li[1]/a");
    private static final By NEW_ITEM = By.xpath("//*[@id='tasks']/div[1]/span/a");
    private static final By ITEM_NAME_ENTER = By.name("name");
    private static final By OK_BUTTON = By.xpath("//*[@id='ok-button']");
    private static final By SAVE_BUTTON = By.name("Submit");
    private static final By PROJECT_NAME = By.xpath("//*[@id=\"job_RedRover\"]/td[3]/a/span");
    private static final By PIPELINE_PROJECT = By.xpath("//*[@id='j-add-item-type-standalone-projects']/ul/li[2]/div[2]");
    private static final By BUTTON_ADVANCED = By.xpath("//*[@id='main-panel']/form/div[1]/div[6]/div[2]/div[1]/button");
    private static final By DISPLAY_NAME = By.xpath("//*[@id='main-panel']/form/div[1]/div[6]/div[3]/div/div[2]/input");
    private static final By BUILD_NOW_PIPELINE = By.xpath("//*[@id='tasks']/div[3]/span/a");
    private static final By OPTIONS_DROPDOWN = By.xpath("//*[@id='main-panel']/form/div[1]/div[7]/div[3]/div/div/div[2]/div[2]/div/div[1]/select/option[2]");

    private void backToDashboard() {
        getDriver().findElement(DASHBOARD).click();
    }


    private void CreatePipelineProjectJob(String nameProject, String displayName) {
        getDriver().findElement(NEW_ITEM).click();
        getDriver().findElement(ITEM_NAME_ENTER).sendKeys(nameProject);
        getDriver().findElement(PIPELINE_PROJECT).click();
        getDriver().findElement(OK_BUTTON).click();

        new Actions(getDriver())
                .scrollByAmount(0, 600)
                .click(getDriver().findElement(BUTTON_ADVANCED))
                .perform();
        getWait5().until(ExpectedConditions.elementToBeClickable(DISPLAY_NAME)).sendKeys(displayName);
        getWait5().until(ExpectedConditions.elementToBeClickable(OPTIONS_DROPDOWN)).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(SAVE_BUTTON)).click();

        getWait5().until(ExpectedConditions.elementToBeClickable(BUILD_NOW_PIPELINE)).click();
        backToDashboard();
    }

    @Test
    public void testCreatePipelineProjectJob() {
        CreatePipelineProjectJob("RedRover","R&R");

        Assert.assertEquals(getWait10().until(ExpectedConditions.elementToBeClickable(PROJECT_NAME)).getText(),"R&R");
    }
}
