package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class CreateNewFolderTest extends BaseTest {

    private static final By NEW_ITEM = By.xpath("//a[@href='/view/all/newJob']");
    private static final By TEXT_FIELD = By.id("name");
    private static final By FOLDER_PROJECT = By.xpath("//span[normalize-space()='Pipeline']");
    private static final By OK_BUTTON = By.id("ok-button");
    private static final By SAVE_BUTTON = By.xpath("//button[normalize-space()='Save']");
    private static final By UPDATED_PROJECT_NAME_DISPLAYED = By.id("main-panel");
    private String folderName = "Project1234";

    @Test
    public void testRenameFolder() {
        getDriver().findElement(NEW_ITEM).click();
        getDriver().findElement(TEXT_FIELD).sendKeys(folderName);
        getDriver().findElement(FOLDER_PROJECT).click();
        getDriver().findElement(OK_BUTTON).click();
        getDriver().findElement(SAVE_BUTTON).click();
        Assert.assertTrue(getDriver().findElement(UPDATED_PROJECT_NAME_DISPLAYED).getText().contains(folderName));
    }
}