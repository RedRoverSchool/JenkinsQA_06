package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.MainPage;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

public class Folder4Test extends BaseTest {

    final String FOLDER_NAME = "Test";
    final String VIEW_NAME = "Test View";

    private void projectDropDownMenu(String nameProject, String nameItemMenu) {
        Actions actions = new Actions(getDriver());
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        WebElement nameFolder = getDriver().findElement(By.xpath("//span[contains(text(),'" + nameProject + "')]"));
        actions.moveToElement(nameFolder).perform();
        WebElement arrow = getDriver().findElement(By.cssSelector("a[href='job/" + nameProject + "/']>button"));
        js.executeScript("arguments[0].click();", arrow);
        getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[contains(text(), '" + nameItemMenu + "')]"))).click();
    }

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
        projectDropDownMenu(FOLDER_NAME, "Rename");

        WebElement inputFieldNewName = getDriver().findElement(By.xpath("//input[@name='newName']"));
        inputFieldNewName.clear();
        inputFieldNewName.sendKeys(newName);
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        getDriver().findElement(By.linkText("Dashboard")).click();
        WebElement nameRenamedFolder = getDriver().findElement(By.cssSelector("a[href='job/" + newName + "/']>span"));
        new Actions(getDriver()).moveToElement(nameRenamedFolder).click(nameRenamedFolder).perform();

        Assert.assertEquals(getDriver().findElement(By.cssSelector("#main-panel>h1")).getText(), newName);
        Assert.assertTrue(getDriver().findElement(By.cssSelector("svg[title='Folder']")).isDisplayed(),
                "error was not shown icon folder");
    }

    @Test
    public void testMoveFolderToFolder(){
        TestUtils.createFolder(this, FOLDER_NAME, true);
        TestUtils.createFolder(this, FOLDER_NAME + " 2", true);

        projectDropDownMenu(FOLDER_NAME, "Move");

        WebElement selectInput = getDriver().findElement(By.xpath("//select"));
        new Select(selectInput).selectByVisibleText("Jenkins » Test 2");
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();
        getDriver().findElement(By.xpath("//a[contains(text(),'" + FOLDER_NAME + " 2" + "')]")).click();

        Assert.assertTrue(
                getDriver().findElement(By.xpath("//a[contains(text(),'" + FOLDER_NAME + "')]")).isDisplayed(),
                "error was not shown moved folder");
    }
}