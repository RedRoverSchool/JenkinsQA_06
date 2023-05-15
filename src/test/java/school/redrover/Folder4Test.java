package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

public class Folder4Test extends BaseTest {

    final String NAME_FOLDER = "Test";
    final String NAME_VIEW = "Test View";

    @Test
    public void testCreateFolder(){
        TestUtils.createFolder(this, NAME_FOLDER, true);

        WebElement nameFolder = getDriver().findElement(By.xpath("//span[contains(text(),'" + NAME_FOLDER + "')]"));
        new Actions(getDriver()).moveToElement(nameFolder).click(nameFolder).perform();

        Assert.assertEquals(getDriver().findElement(By.cssSelector("#main-panel>h1")).getText(), NAME_FOLDER);
        Assert.assertTrue(getDriver().findElement(By.cssSelector("svg[title='Folder']")).isDisplayed(),
                "error was not shown icon folder");
    }

    @Test(dependsOnMethods = {"testCreateFolder"})
    public void testCreateNewViewInFolder(){
        Actions actions = new Actions(getDriver());
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        WebElement nameFolder = getDriver().findElement(By.xpath("//span[contains(text(),'" + NAME_FOLDER + "')]"));
        actions.moveToElement(nameFolder).click(nameFolder).perform();
        getDriver().findElement(By.xpath("//div[@class='tab']")).click();

        getDriver().findElement(By.id("name")).sendKeys(NAME_VIEW);
        WebElement myView = getDriver().findElement(By.xpath("//fieldset/div[last()]/input"));
        js.executeScript("arguments[0].scrollIntoView();", myView);
        actions.moveToElement(myView).click(myView).perform();
        getDriver().findElement(By.id("ok")).click();
        getDriver().findElement(By.linkText("All")).click();
        WebElement newView = getDriver().findElement(By.linkText(NAME_VIEW));

        Assert.assertTrue(newView.isDisplayed(), "error was not shown created view");
    }

    @Test(dependsOnMethods = {"testCreateFolder"})
    public void testRenameFolder(){
        final String newName = "newTestName";
        Actions actions = new Actions(getDriver());
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        WebElement nameFolder = getDriver().findElement(By.xpath("//span[contains(text(),'" + NAME_FOLDER + "')]"));
        actions.moveToElement(nameFolder).perform();
        WebElement arrow = getDriver().findElement(By.cssSelector("a[href='job/" + NAME_FOLDER + "/']>button"));
        js.executeScript("arguments[0].click();", arrow);
        getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@href='/job/" + NAME_FOLDER + "/confirm-rename']"))).click();

        WebElement inputFieldNewName = getDriver().findElement(By.xpath("//input[@name='newName']"));
        inputFieldNewName.clear();
        inputFieldNewName.sendKeys(newName);
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        getDriver().findElement(By.linkText("Dashboard")).click();
        WebElement nameRenamedFolder = getDriver().findElement(By.cssSelector("a[href='job/" + newName + "/']>span"));
        actions.moveToElement(nameRenamedFolder).click(nameRenamedFolder).perform();

        Assert.assertEquals(getDriver().findElement(By.cssSelector("#main-panel>h1")).getText(), newName);
        Assert.assertTrue(getDriver().findElement(By.cssSelector("svg[title='Folder']")).isDisplayed(),
                "error was not shown icon folder");
    }
}