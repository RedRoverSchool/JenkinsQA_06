package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import static org.testng.Assert.assertEquals;

public class ConfigureFolder2Test extends BaseTest {
    private static final String PROJECT_NAME = "qwerty";


    private static final By NEW_ITEM = By.xpath("//a[@href='/view/all/newJob'][@class='task-link ']");
    private static final By PROJECT_NAME_FIELD = By.xpath("//input[@name='name']");
    private static final By FOLDER_PROJECT = By.xpath("//li[@class = 'com_cloudbees_hudson_plugins_folder_Folder']");
    private static final By CREATE_PROJECT_BUTTON = By.xpath("//button [@id='ok-button']");
    private static final By FOLDER_NAME_FIELD = By.xpath("//input [@name='_.displayNameOrNull']");
    private static final By FOLDER_SUBMIT_BUTTON = By.xpath("//button [@name='Submit']");

    private void createFolder(){
        getDriver().findElement(NEW_ITEM).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(PROJECT_NAME_FIELD)).sendKeys(PROJECT_NAME);

        getDriver().findElement(FOLDER_PROJECT).click();

        getWait2().until(ExpectedConditions.elementToBeClickable(CREATE_PROJECT_BUTTON)).click();
    }

    @Test
    public void testSetDisplayName(){
        createFolder();
        String FOLDER_NAME = "folder";

        getDriver().findElement(FOLDER_NAME_FIELD).sendKeys(FOLDER_NAME);
        getDriver().findElement(FOLDER_SUBMIT_BUTTON).click();

        assertEquals(getDriver().findElement(By.xpath("//h1")).getText(), FOLDER_NAME);
    }

    @Test
    public void testAddDescription(){
        createFolder();
        String fodlerDescription = "description of the Folder Project";

        getDriver().findElement(By.name("_.description")).sendKeys(fodlerDescription);
        getDriver().findElement(FOLDER_SUBMIT_BUTTON).click();

        assertEquals(getDriver().findElement(By.xpath("//div[@id='view-message']")).getText(), fodlerDescription);
    }
}
