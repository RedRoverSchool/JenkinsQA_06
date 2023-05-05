package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class FolderTest2 extends BaseTest {
    private static final By NEW_ITEM = By.xpath("//a[@href='/view/all/newJob']");
    private static final By NAME = By.id("name");
    private static final By FOLDER = By.xpath("//li[@class='com_cloudbees_hudson_plugins_folder_Folder']");
    private static final By OK_BUTTON = By.cssSelector("#ok-button");
    private static final By DISPLAY_NAME = By.name("_.displayNameOrNull");
    private static final By DESCRIPTION = By.name("_.description");
    private static final By SUBMIT = By.name("Submit");

    @Test
    public void testNewItemFolder() {
        String nameProject = "New Project";
        String displayName = "NewTest";
        String descriptionText = "Test project to QA Redrover.school";

        getDriver().findElement(NEW_ITEM).click();
        getDriver().findElement(NAME).sendKeys(nameProject);
        getDriver().findElement(FOLDER).click();
        getDriver().findElement(OK_BUTTON).click();
        getDriver().findElement(DISPLAY_NAME).sendKeys(displayName);
        getDriver().findElement(DESCRIPTION).sendKeys(descriptionText);
        getDriver().findElement(SUBMIT).click();

        Assert.assertEquals("NewTest", getDriver().findElement(By.cssSelector("div[id='main-panel'] h1")).getText());
    }
}
