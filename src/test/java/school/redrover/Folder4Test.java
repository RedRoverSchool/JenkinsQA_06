package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.model.MainPage;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

public class Folder4Test extends BaseTest {

    final String FOLDER_NAME = "Test";
    final String VIEW_NAME = "Test View";

    @Test
    public void testCreateFolder() {
        new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(FOLDER_NAME)
                .selectFolderAndOk()
                .clickDashboard();

        Assert.assertTrue(new MainPage(getDriver()).getJobInList(FOLDER_NAME).isDisplayed(),
                "error was not show name folder");
        Assert.assertTrue(getDriver().findElement(By.cssSelector("svg[title='Folder']")).isDisplayed(),
                "error was not shown icon folder");
    }

    @Test(dependsOnMethods = {"testCreateFolder"})
    public void testCreateNewViewInFolder() {

        new MainPage(getDriver())
                .clickFolderName(FOLDER_NAME)
                .newView()
                .interViewName(VIEW_NAME)
                .selectMyViewAndClickCreate()
                .clickAll();

        WebElement newView = getDriver().findElement(By.linkText(VIEW_NAME));
        Assert.assertTrue(newView.isDisplayed(), "error was not shown created view");
    }

    @Test(dependsOnMethods = {"testCreateFolder"})
    public void testRenameFolder() {
        final String newName = "newTestName";

        new MainPage(getDriver())
                .selectJobDropDownMenuRename(FOLDER_NAME)
                .enterNewName(newName)
                .SubmitNewNameFolder()
                .navigateToMainPageByBreadcrumbs();

        Assert.assertTrue(new MainPage(getDriver()).getJobInList(FOLDER_NAME).isDisplayed(),
                "error was not show new name folder");
    }

    @Ignore
    @Test
    public void testMoveFolderToFolder(){
        TestUtils.createFolder(this, FOLDER_NAME, true);
        TestUtils.createFolder(this, FOLDER_NAME + " 2", true);

        WebElement selectInput = getDriver().findElement(By.xpath("//select"));
        new Select(selectInput).selectByVisibleText("Jenkins » Test 2");
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();
        getDriver().findElement(By.xpath("//a[contains(text(),'" + FOLDER_NAME + " 2" + "')]")).click();

        Assert.assertTrue(
                getDriver().findElement(By.xpath("//a[contains(text(),'" + FOLDER_NAME + "')]")).isDisplayed(),
                "error was not shown moved folder");
    }
}