package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.model.FolderConfigPage;
import school.redrover.model.FolderPage;
import school.redrover.model.MainPage;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

public class Folder4Test extends BaseTest {

    final String FOLDER_NAME = "Test";

    @Test
    public void testCreateFolder() {
        new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(FOLDER_NAME)
                .selectFolderAndOk()
                .clickDashboard();

        Assert.assertTrue(new MainPage(getDriver()).getJobWebElement(FOLDER_NAME).isDisplayed(),
                "error was not show name folder");
        Assert.assertTrue(getDriver().findElement(By.cssSelector("svg[title='Folder']")).isDisplayed(),
                "error was not shown icon folder");
    }

    @Test(dependsOnMethods = "testCreateFolder")
    public void testCreateNewViewInFolder() {
        final String viewName = "Test View";

        new MainPage(getDriver())
                .clickFolderName(FOLDER_NAME)
                .newView()
                .interViewName(viewName)
                .selectMyViewAndClickCreate()
                .clickAll();

        WebElement newView = getDriver().findElement(By.linkText(viewName));
        Assert.assertTrue(newView.isDisplayed(), "error was not shown created view");
    }

    @Test(dependsOnMethods = "testCreateFolder")
    public void testAddDisplayNameAndDescription() {
        final String displayName = "TestDisplayName";
        final String description = "TestDescription";

        new MainPage(getDriver())
                .selectConfigureJobDropDownMenu(FOLDER_NAME)
                .enterDisplayName(displayName)
                .enterDescription(description)
                .saveProjectAndGoToFolderPage();

        Assert.assertEquals(new FolderPage(getDriver()).getFolderDisplayName(), displayName);
        Assert.assertEquals(new FolderPage(getDriver()).getFolderDescription(), description);
    }

    @Test(dependsOnMethods = {"testCreateFolder", "testCreateNewViewInFolder", "testAddDisplayNameAndDescription"})
    public void testRenameFolder() {
        final String newName = "newTestName";

        new MainPage(getDriver())
                .selectRenameJobDropDownMenu(FOLDER_NAME)
                .enterNewName(newName)
                .SubmitNewNameFolder()
                .navigateToMainPageByBreadcrumbs();

        Assert.assertTrue(new MainPage(getDriver()).getJobWebElement(FOLDER_NAME).isDisplayed(),
                "error was not show new name folder");
    }

    @Test
    public void testDeleteFolder() {
        TestUtils.createFolder(this, FOLDER_NAME, true);
        new MainPage(getDriver())
                .selectDeleteFolderDropDownMenu(FOLDER_NAME)
                .clickYes();

        Assert.assertTrue(new MainPage(getDriver()).getWelcomeWebElement().isDisplayed(),
                "error was not show Welcome to Jenkins!");
    }

    @Test
    public void testMoveFolderToFolder(){
        final String folder2Name = "test2";

        TestUtils.createFolder(this, FOLDER_NAME, true);
        TestUtils.createFolder(this, folder2Name, true);

        new MainPage(getDriver())
                .selectMoveJobDropDownMenu(FOLDER_NAME)
                .selectDestinationFolder()
                .clickMoveButton()
                .navigateToMainPageByBreadcrumbs()
                .clickFolderName(folder2Name);

        Assert.assertTrue(new FolderPage(getDriver()).getNestedFolder(FOLDER_NAME).isDisplayed(),
                "error was not shown moved folder");
    }
}