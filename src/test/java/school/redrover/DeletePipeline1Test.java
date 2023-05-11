package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class DeletePipeline1Test extends BaseTest {
    public static String name = "My New Pipeline Project";
    public static final By NEW_ITEM = By.xpath("//a[@href='/view/all/newJob']");
    public static final By INPUT_NAME = By.id("name");
    public static final By PIPELINE_PROJECT_TYPE = By.xpath("//img[@src='/plugin/workflow-job/images/pipelinejob.svg']");
    public static final By OK_BUTTON = By.id("ok-button");
    public static final By SAVE_BUTTON = By.xpath("//button[normalize-space()='Save']");
    public static final By JENKINS_LOGO = By.id("jenkins-name-icon");
    public static final By PROJECT_IN_DASHBOARD_TABLE = By.xpath("//span[normalize-space()='" + name + "']");
    public static final By DELETE_BUTTON = By.xpath("//span[contains(text(),'Delete Pipeline')]");
    public static final By MAIN_PAGE_MESSAGE = By.xpath("//h1[normalize-space()='Welcome to Jenkins!']");

    @Test
    public void testDeletePipelineProject() {
        getDriver().findElement(NEW_ITEM).click();
        getDriver().findElement(INPUT_NAME).sendKeys(name);
        getDriver().findElement(PIPELINE_PROJECT_TYPE).click();
        getDriver().findElement(OK_BUTTON).click();
        getDriver().findElement(SAVE_BUTTON).click();

        getDriver().findElement(JENKINS_LOGO).click();
        getDriver().findElement(PROJECT_IN_DASHBOARD_TABLE).click();

        getDriver().findElement(DELETE_BUTTON).click();
        getDriver().switchTo().alert().accept();

        Assert.assertEquals(getDriver().findElement(MAIN_PAGE_MESSAGE).getText(), "Welcome to Jenkins!");
    }
}
