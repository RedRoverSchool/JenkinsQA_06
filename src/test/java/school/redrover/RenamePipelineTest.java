package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class RenamePipelineTest extends BaseTest {

    private static final By NEW_ITEM = By.xpath("//a[@href='/view/all/newJob']");
    private static final By TEXT_FIELD = By.id("name");
    private static final By PIPELINE_PROJECT = By.xpath("//span[normalize-space()='Pipeline']");
    private static final By OK_BUTTON = By.id("ok-button");
    private static final By SAVE_BUTTON = By.xpath("//button[normalize-space()='Save']");
    private static final By RENAME_BUTTON = By.xpath("//a[@href='/job/Java/confirm-rename']");
    private static final By RENAME_TEXT_FIELD = By.name("newName");
    private static final By CONFIRM_RENAME_BUTTON = By.name("Submit");
    private static final By UPDATED_PROJECT_NAME_DISPLAYED = By.xpath("//h1[@class='job-index-headline page-headline']");
    private static final String PROJECT_NAME = "Java";
    private static final String UPDATED_PROJECT_NAME = "Java1";

    @Test
    public void testRenamePipelineTest() {
        getDriver().findElement(NEW_ITEM).click();
        getDriver().findElement(TEXT_FIELD).sendKeys(PROJECT_NAME);
        getDriver().findElement(PIPELINE_PROJECT).click();
        getDriver().findElement(OK_BUTTON).click();
        getDriver().findElement(SAVE_BUTTON).click();
        getDriver().findElement(RENAME_BUTTON).click();
        getDriver().findElement(RENAME_TEXT_FIELD).clear();
        getDriver().findElement(RENAME_TEXT_FIELD).sendKeys(UPDATED_PROJECT_NAME);
        getDriver().findElement(CONFIRM_RENAME_BUTTON).click();

        Assert.assertTrue(getDriver().findElement(UPDATED_PROJECT_NAME_DISPLAYED).getText().contains("Pipeline "+UPDATED_PROJECT_NAME));
    }
}
