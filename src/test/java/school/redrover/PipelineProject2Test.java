package school.redrover;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class PipelineProject2Test extends BaseTest {

    private static final String PROJECT_NAME = RandomStringUtils.randomAlphanumeric(7);
    private static final By NEW_ITEM = By.xpath("//a[@href='/view/all/newJob']");
    private static final By INPUT_NAME = By.name("name");
    private static final By NEW_NAME = By.name("newName");
    private static final By SELECT_PIPELINE = By.xpath("//span[contains(text(), 'Pipeline')]");
    private static final By OK_BUTTON = By.id("ok-button");
    private static final By SUBMIT = By.name("Submit");
    private static final By JENKINS_HEAD_ICON = By.id("jenkins-head-icon");
    private static final By ERROR_MESSAGE = By.xpath("//div[@class='input-validation-message']");

    private void createPipelineProject() {

        getDriver().findElement(NEW_ITEM).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(INPUT_NAME)).sendKeys(PROJECT_NAME);
        getDriver().findElement(SELECT_PIPELINE).click();
        getDriver().findElement(OK_BUTTON).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(SUBMIT)).click();
    }

    @Test
    public void testCreateDuplicatePipelineProject() {

        createPipelineProject();

        getDriver().findElement(JENKINS_HEAD_ICON).click();
        getDriver().findElement(NEW_ITEM).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(INPUT_NAME));
        getDriver().findElement(INPUT_NAME).sendKeys(PROJECT_NAME);
        getDriver().findElement(SELECT_PIPELINE).click();

        Assert.assertEquals(getDriver().findElement(ERROR_MESSAGE).getText(), "» A job already exists with the name " + "‘" + PROJECT_NAME + "’");
    }

    @Test
    public void testRenamePipelineProject() {

        createPipelineProject();

        getDriver().findElement(JENKINS_HEAD_ICON).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[href='job/" + PROJECT_NAME + "/']"))).click();
        getDriver().findElement(By.cssSelector("a[href='/job/" + PROJECT_NAME + "/confirm-rename']")).click();
        getDriver().findElement(NEW_NAME).clear();
        getDriver().findElement(NEW_NAME).sendKeys(PROJECT_NAME + " Renamed");
        getDriver().findElement(SUBMIT).click();

        Assert.assertTrue(getDriver().findElement(By.id("main-panel")).getText().contains(PROJECT_NAME + " Renamed"));

    }
}

