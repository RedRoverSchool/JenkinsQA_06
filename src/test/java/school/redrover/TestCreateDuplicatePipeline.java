package school.redrover;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class TestCreateDuplicatePipeline extends BaseTest {

    private static final String PROJECT_NAME = RandomStringUtils.randomAlphanumeric(7);
    private static final By NEW_ITEM = By.xpath("//a[@href='/view/all/newJob']");
    private static final By INPUT_NAME = By.name("name");
    private static final By SELECT_PIPELINE = By.xpath("//span[contains(text(), 'Pipeline')]");
    private static final By OK_BUTTON = By.id("ok-button");
    private static final By SUBMIT = By.name("Submit");
    private static final By JENKINS_HEAD_ICON = By.id("jenkins-head-icon");
    private static final By ERROR_MESSAGE = By.xpath("//div[@class='input-validation-message']");
    private void createPipeline(String name) {

        getDriver().findElement(NEW_ITEM).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(INPUT_NAME));
        getDriver().findElement(INPUT_NAME).sendKeys(name);
        getDriver().findElement(SELECT_PIPELINE).click();
        getDriver().findElement(OK_BUTTON).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(SUBMIT)).click();
    }

    @Test
    public void testCreatePipelineJobWithDuplicateName() {

        createPipeline(PROJECT_NAME);

        getDriver().findElement(JENKINS_HEAD_ICON).click();
        getDriver().findElement(NEW_ITEM).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(INPUT_NAME));
        getDriver().findElement(INPUT_NAME).sendKeys(PROJECT_NAME);
        getDriver().findElement(SELECT_PIPELINE).click();

        Assert.assertEquals(getDriver().findElement(ERROR_MESSAGE).getText(), "» A job already exists with the name " + "‘" + PROJECT_NAME + "’");
    }
}
