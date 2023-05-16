package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class PipelineJobTest extends BaseTest {
    private static final By DASHBOARD = By.xpath("//*[@id='breadcrumbs']/li[1]/a");
    private static final By NEW_ITEM = By.xpath("//*[@id='tasks']/div[1]/span/a");
    private static final By ITEM_NAME_ENTER = By.name("name");
    private static final By OK_BUTTON = By.xpath("//*[@id='ok-button']");
    private static final By SAVE_BUTTON = By.name("Submit");
    private static final By BUILD_NAME = By.xpath("//*[@class='model-link inside build-link display-name']");
    private static final By CONSOLE_OUT = By.xpath("//*[@id='main-panel']/pre");
    private static final By PIPELINE_PROJECT = By.xpath("//*[@id='j-add-item-type-standalone-projects']/ul/li[2]/div[2]");
    private static final By BUTTON_ADVANCED = By.xpath("//*[@id='main-panel']/form/div[1]/div[6]/div[2]/div[1]/button");
    private static final By DISPLAY_NAME = By.xpath("//*[@id='main-panel']/form/div[1]/div[6]/div[3]/div/div[2]/input");
    private static final By BUILD_NOW_PIPELINE = By.xpath("//*[@id='tasks']/div[3]/span/a");
    private static final By CONSOLE_PIPELINE = By.xpath("//*[@id='job_RedRover']/td[4]/a");
    private static final By CONSOLE_OUT_PIPELINE = By.xpath("//*[@id='yui-gen2']/a");
    private static final By JENKINS_MENU_DROPDOWN_CHEVRON = By.xpath("//*[@id='job_RedRover']/td[4]/a/button");
    private static final By OPTIONS_BUTTON =
            By.xpath("//*[@id='main-panel']/form/div[1]/div[7]/div[3]/div/div/div[2]/div[2]/div/div[1]/select/option[2]");

    private void backToDashboard() {
        getDriver().findElement(DASHBOARD).click();
    }

    public void scroll(int deltaY) {
        new Actions(getDriver())
                .scrollFromOrigin(WheelInput.ScrollOrigin.fromViewport(), 0, deltaY)
                .perform();
    }

    private void CreatePipelineProjectJob(String nameProject, String displayName) {
        getDriver().findElement(NEW_ITEM).click();
        getDriver().findElement(ITEM_NAME_ENTER).sendKeys(nameProject);
        getDriver().findElement(PIPELINE_PROJECT).click();
        getDriver().findElement(OK_BUTTON).click();

        new Actions(getDriver())
                .scrollByAmount(0, 800)
                .click(getDriver().findElement(BUTTON_ADVANCED))
                .perform();
        getWait5().until(ExpectedConditions.elementToBeClickable(DISPLAY_NAME)).sendKeys(displayName);

        new Actions(getDriver())
                .scrollByAmount(800, 900)
                .click(getWait5().until(ExpectedConditions.elementToBeClickable(DISPLAY_NAME)))
                .perform();

        getWait5().until(ExpectedConditions.elementToBeClickable(SAVE_BUTTON)).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(BUILD_NOW_PIPELINE)).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(BUILD_NAME)).click();

        backToDashboard();

        new Actions(getDriver())
                .moveToElement(getDriver().findElement(CONSOLE_PIPELINE))
                .click(getDriver().findElement(JENKINS_MENU_DROPDOWN_CHEVRON))
                .perform();
        getWait10().until(ExpectedConditions.elementToBeClickable(CONSOLE_OUT_PIPELINE)).click();
    }

    @Test
    public void testCreatePipelineProjectJob() {
        CreatePipelineProjectJob("RedRover","R&R");

        Assert.assertTrue(getDriver().findElement(CONSOLE_OUT).getText().contains("Finished: SUCCESS"));
    }
}
