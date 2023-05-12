package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CreateMultiConfigurationProjectTest extends BaseTest {
    private static final By NEW_ITEM = By.xpath("//a[@href='/view/all/newJob']");
    private static final By SET_ITEM_NAME = By.id("name");
    private static final By MULTI_CONFIGURATION_PROJECT = By.xpath("//span[text()='Multi-configuration project']");
    private static final By OK_BUTTON = By.xpath("//*[@id='ok-button']");
    private static final By ADD_DESCRIPTION = By.xpath("//a[@href='editDescription']");
    private static final By TEXTAREA_DESCRIPTION = By.xpath("//textarea[@name='description']");
    private static final By SAVE_BUTTON = By.name("Submit");


    @Test
    public void testMultiConfigurationProject() {
        WebElement newItem = getDriver().findElement(By.xpath("//*[@id='tasks']/div[1]/span/a"));
        newItem.click();

        getWait2().until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.xpath("//input[@id='name']"))))
                .sendKeys("My Multi configuration project");

        WebElement MultiProject = getDriver().findElement(By.xpath("//span[text() = 'Multi-configuration project']"));
        MultiProject.click();

        WebElement okButton = getDriver().findElement(By.id("ok-button"));
        okButton.click();

        WebElement buttonSave = getDriver().findElement(By.cssSelector("button[formnovalidate='formNoValidate' ]"));
        buttonSave.click();

        WebElement nameProject = getDriver().findElement(By.xpath("//h1[text() = 'Project My Multi configuration project']"));

        Assert.assertEquals(nameProject.getText(), "Project My Multi configuration project");
    }

    @Test
    public void testAddDescriptionToMultiConfigurationProject() {
        final String expectedDescription = "Web-application project";

        WebElement selectNewItem = getDriver().findElement(NEW_ITEM);
        selectNewItem.click();

        WebElement setNewItemName = getDriver().findElement(SET_ITEM_NAME);
        setNewItemName.sendKeys("Project_MultiConfigJob");

        WebElement selectMultiConfigProject = getWait5().
                until(ExpectedConditions.elementToBeClickable(MULTI_CONFIGURATION_PROJECT));
        selectMultiConfigProject.click();

        WebElement okButton = getWait5().until(ExpectedConditions.elementToBeClickable(OK_BUTTON));
        okButton.click();

        WebElement scrollBySubmitButton = getDriver().findElement(SAVE_BUTTON);
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        jse.executeScript("arguments[0].scrollIntoView(true)", scrollBySubmitButton);
        scrollBySubmitButton.click();

        WebElement addDescription = getDriver().findElement(ADD_DESCRIPTION);
        addDescription.click();

        WebElement textAreaDescription = getWait2().until(ExpectedConditions.elementToBeClickable(TEXTAREA_DESCRIPTION));
        textAreaDescription.clear();
        textAreaDescription.sendKeys("Web-application project");

        WebElement saveButton = getDriver().findElement(SAVE_BUTTON);
        saveButton.click();

        WebElement actualDescription = getDriver().findElement(By.xpath("//div[@id = 'description']/div[1]"));

        Assert.assertEquals(actualDescription.getText(), expectedDescription);
    }

    @Test
    public void testCreateMultiConfigurationProjectInFolder() {
        final String expectedFolderHeader = "Multi-configuration projects Folder";
        final String expectedProjectInFolder = "First Multi-configuration project";

        WebElement selectNewItem = getDriver().findElement(NEW_ITEM);
        selectNewItem.click();
        WebElement setNewItemName = getDriver().findElement(SET_ITEM_NAME);
        setNewItemName.sendKeys("Multi-configuration projects Folder");
        getDriver().findElement(By.xpath("//li[@class='com_cloudbees_hudson_plugins_folder_Folder']"))
                .click();
        WebElement okButton = getWait5().until(ExpectedConditions.elementToBeClickable(OK_BUTTON));
        okButton.click();
        WebElement scrollBySubmitButton = getDriver().findElement(SAVE_BUTTON);
        scrollBySubmitButton.click();

        WebElement actualFolderHeader = getDriver().findElement(By.xpath("//h1"));

        Assert.assertEquals(actualFolderHeader.getText(), expectedFolderHeader);

        getDriver().findElement(By.xpath("//div[@id='tasks']/div[3]")).click();
        WebElement setNewProjectName = getDriver().findElement(SET_ITEM_NAME);
        setNewProjectName.sendKeys("First Multi-configuration project");
        WebElement selectMultiConfigProject = getWait5().
                until(ExpectedConditions.elementToBeClickable(MULTI_CONFIGURATION_PROJECT));
        selectMultiConfigProject.click();
        WebElement okBtn = getWait5().until(ExpectedConditions.elementToBeClickable(OK_BUTTON));
        okBtn.click();
        WebElement submitButton = getDriver().findElement(SAVE_BUTTON);
        submitButton.click();

        getDriver().findElement(By.xpath("//a[@href='/job/Multi-configuration%20projects%20Folder/']")).click();
        WebElement actualProjectInFolder = getDriver().findElement(By.xpath("//a[@href='job/First%20Multi-configuration%20project/']"));

        Assert.assertEquals(actualProjectInFolder.getText(), expectedProjectInFolder);
    }
}
