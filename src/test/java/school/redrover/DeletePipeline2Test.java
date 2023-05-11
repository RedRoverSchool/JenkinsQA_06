package school.redrover;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class DeletePipeline2Test extends BaseTest {

    private static final By NEW_ITEM = By.xpath("//a[@href='/view/all/newJob']");
    private static final By TEXT_FIELD = By.id("name");
    private static final By PIPELINE_PROJECT = By.xpath("//span[normalize-space()='Pipeline']");
    private static final By MULTI_CONFIG_PROJECT = By.xpath("//span[normalize-space()='Multi-configuration project']");
    private static final By OK_BUTTON = By.id("ok-button");
    private static final By SAVE_BUTTON = By.xpath("//button[normalize-space()='Save']");

    private static final By DELETE_BUTTON = By.xpath("//span[contains(text(),'Delete Pipeline')]");
    private static final By DASHBOARD = By.xpath("//a[normalize-space()='Dashboard']");
    private static final String PIPELINE_NAME = RandomStringUtils.randomAlphanumeric(10);
    private static final String MULTI_CONFIG_PIPELINE_NAME = RandomStringUtils.randomAlphanumeric(10);
    private static final By SCREEN = By.xpath("//a[@class='jenkins-table__link model-link inside']");


    public void createPipeline() {
        getDriver().findElement(NEW_ITEM).click();

        getDriver().findElement(TEXT_FIELD).sendKeys(PIPELINE_NAME);
        getDriver().findElement(PIPELINE_PROJECT).click();
        getDriver().findElement(OK_BUTTON).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(SAVE_BUTTON));
        getDriver().findElement(SAVE_BUTTON).click();
    }

    public void createMultiConfigProject() {
        getDriver().findElement(NEW_ITEM).click();

        getDriver().findElement(TEXT_FIELD).sendKeys(MULTI_CONFIG_PIPELINE_NAME);
        getDriver().findElement(MULTI_CONFIG_PROJECT).click();
        getDriver().findElement(OK_BUTTON).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(SAVE_BUTTON));
        getDriver().findElement(SAVE_BUTTON).click();
    }
    @Test
    public void testDeletePipeline() {
        createMultiConfigProject();
        getDriver().findElement(DASHBOARD).click();
        createPipeline();
        getWait5().until(ExpectedConditions.elementToBeClickable(DELETE_BUTTON));
        getDriver().findElement(DELETE_BUTTON).click();
        getDriver().switchTo().alert().accept();

        Assert.assertFalse(getDriver().findElements(SCREEN).contains(PIPELINE_NAME));
    }
}
