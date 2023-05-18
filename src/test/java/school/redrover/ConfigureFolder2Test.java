package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import static org.testng.Assert.assertEquals;

public class ConfigureFolder2Test extends BaseTest {

    private static final By NEW_ITEM = By.xpath("//a[@href='/view/all/newJob'][@class='task-link ']");
    private static final By PROJECT_NAME = By.xpath("//input[@name='name']");
    private static final By FOLDER_PROJECT = By.xpath("//li[@class = 'com_cloudbees_hudson_plugins_folder_Folder']");
    private static final By CREATE_PROJECT_BUTTON = By.xpath("//button [@id='ok-button']");
    private static final By FOLDER_NAME_FIELD = By.xpath("//input [@name='_.displayNameOrNull']");
    private static final By FOLDER_SUBMIT_BUTTON = By.xpath("//button [@name='Submit']");

    private void CreateFolder(String name){
        getDriver().findElement(NEW_ITEM).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(PROJECT_NAME)).sendKeys(name);

        getDriver().findElement(FOLDER_PROJECT).click();

        getWait2().until(ExpectedConditions.elementToBeClickable(CREATE_PROJECT_BUTTON)).click();
    }

    @Test
    public void testSetDisplayName(){
        String projectName = "qwerty";
        String folderName = "folder";

        CreateFolder(projectName);

        getDriver().findElement(FOLDER_NAME_FIELD).sendKeys(folderName);
        getDriver().findElement(FOLDER_SUBMIT_BUTTON).click();

        assertEquals(getDriver().findElement(By.xpath("//h1")).getText(), folderName);
    }
}
