package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class CreatePipelineProject3Test extends BaseTest {
    public static String name = "My New Pipeline Project";
    public static final By NEW_ITEM = By.xpath("//a[@href='/view/all/newJob']");
    public static final By INPUT_NAME = By.id("name");
    public static final By PIPELINE_PROJECT_TYPE = By.xpath("//img[@src='/plugin/workflow-job/images/pipelinejob.svg']");
    public static final By OK_BUTTON = By.id("ok-button");
    public static final By SAVE_BUTTON = By.xpath("//button[normalize-space()='Save']");
    public static final By JENKINS_LOGO = By.id("jenkins-name-icon");
    public static final By PROJECT_IN_DASHBOARD_TABLE = By.xpath("//span[normalize-space()='" + name + "']");

    @Test
    public void testCreatePipelineProject() {
        getDriver().findElement(NEW_ITEM).click();
        getDriver().findElement(INPUT_NAME).sendKeys(name);
        getDriver().findElement(PIPELINE_PROJECT_TYPE).click();
        getDriver().findElement(OK_BUTTON).click();
        getDriver().findElement(SAVE_BUTTON).click();
        getDriver().findElement(JENKINS_LOGO).click();

        Assert.assertEquals(getDriver().findElement(PROJECT_IN_DASHBOARD_TABLE).getText(), name);

        getDriver().findElement(PROJECT_IN_DASHBOARD_TABLE).click();

        Assert.assertEquals(getDriver().findElement(
                        By.xpath("//h1[normalize-space()='Pipeline " + name + "']"))
                .getText(), "Pipeline " + name);
    }

    @Test
    public void testPipelineNameUnsafeChar() {
        String[] testStr = {"*", "&", "^", "%", "$", "#", "@", "!"};

        getDriver().findElement(NEW_ITEM).click();
        for (int i = 0; i < testStr.length; i++) {
            String name = testStr[i];
            getDriver().findElement(INPUT_NAME).sendKeys(name);

            getWait2();
            getDriver().findElement(PIPELINE_PROJECT_TYPE).click();
            getWait2();
            WebElement message = getDriver().findElement(By.xpath("//div[@id='itemname-invalid']"));

            Assert.assertEquals(message.getText(), "»" + " ‘" + name + "’ " + "is an unsafe character");
            getWait2();
            getDriver().findElement(INPUT_NAME).clear();
        }
    }
}