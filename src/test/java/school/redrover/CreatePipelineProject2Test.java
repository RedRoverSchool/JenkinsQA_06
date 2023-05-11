package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class CreatePipelineProject2Test extends BaseTest {

    public static String name = "MyFirstJenkinsProject";
    public static final By NEW_JOB = By.xpath("//span[contains(text(),'Create a job')]");
    public static final By INPUT_NAME_FIELD = By.xpath("//input[@id='name']");
    public static final By PROJECT_NAME = By.xpath("//span[normalize-space()='Pipeline']");
    public static final By OK_BUTTON = By.id("ok-button");
    public static final By SAVE_BUTTON = By.xpath("//button[contains(text(),'Save')]");
    public static final By PAGE_TITLE = By.xpath("//h1[contains(text(),'Pipeline " + name + "')]");

    @Test
    public void testCreatePipelineProject() {
        getDriver().findElement(NEW_JOB).click();
        getDriver().findElement(INPUT_NAME_FIELD).sendKeys(name);
        getDriver().findElement(PROJECT_NAME).click();
        getDriver().findElement(OK_BUTTON).click();
        getDriver().findElement(SAVE_BUTTON).click();

        Assert.assertEquals(getDriver().findElement(PAGE_TITLE).getText(), "Pipeline " + name);
    }
}
