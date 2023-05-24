package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
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

    @Test(dependsOnMethods = {"testCreateFolder"})
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

    @Test(dependsOnMethods = {"testCreateFolder"})
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
    public void testMoveFolderToFolder(){
        final String folder2Name = "newTestName";

        TestUtils.createFolder(this, FOLDER_NAME, true);
        TestUtils.createFolder(this, folder2Name, true);

        new MainPage(getDriver())
                .selectMoveJobDropDownMenu(FOLDER_NAME)
                .selectDestinationFolder()
                .clickMoveButton()
                .navigateToMainPageByBreadcrumbs()
                .clickFolderName(folder2Name);

        Assert.assertTrue(
                getDriver().findElement(By.xpath("//a[contains(text(),'" + FOLDER_NAME + "')]")).isDisplayed(),
                "error was not shown moved folder");
    }
}