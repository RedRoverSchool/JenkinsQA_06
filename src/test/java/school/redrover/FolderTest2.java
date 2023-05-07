package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class FolderTest2 extends BaseTest {

    private static final By NEW_ITEM = By.xpath("//a[@href='/view/all/newJob']");
    private static final By NAME = By.id("name");
    private static final By FOLDER = By.xpath("//span[@class='label'][text()='Folder']");
    private static final By BUTTON = By.cssSelector("#ok-button");
    private static final By DISPLAY_NAME = By.name("_.displayNameOrNull");
    private static final By DESCRIPTION = By.name("_.description");
    private static final By SUBMIT = By.name("Submit");
    private static final By FOLDER_PAGE = By.cssSelector("div[id='main-panel'] h1");
    private static final By FOLDER_DASHBOARD = By.xpath("//a[@class='jenkins-table__link model-link inside']//span");
    private  static final By DASHBOARD = By.xpath("//a[normalize-space()='Dashboard']");

    private static  final By CONFIGURE = By.xpath("//a[@href='/job/newProject/configure']");

    String name = "newProject";
    String displayNameText = "NewTest";
    String descriptionText = "Test project to QA Redrover.school";
    String renameText = "FolderOne";

    @Test
    public void testCreateNewItemFolderAndRenameFolder() {

        getDriver().findElement(NEW_ITEM).click();
        getDriver().findElement(NAME).sendKeys(name);
        getDriver().findElement(FOLDER).click();
        getDriver().findElement(BUTTON).click();
        getDriver().findElement(DISPLAY_NAME).sendKeys(displayNameText);
        getDriver().findElement(DESCRIPTION).sendKeys(descriptionText);
        getDriver().findElement(SUBMIT).click();
        getDriver().findElement(DASHBOARD).click();
        getDriver().findElement(FOLDER_DASHBOARD).click();

        Assert.assertEquals(displayNameText, getDriver().findElement(FOLDER_PAGE).getText());

        getDriver().findElement(CONFIGURE).click();
        getDriver().findElement(DISPLAY_NAME).clear();
        getDriver().findElement(DISPLAY_NAME).sendKeys(renameText);
        getDriver().findElement(SUBMIT).click();

        Assert.assertEquals(renameText, getDriver().findElement(FOLDER_PAGE).getText());
    }
}