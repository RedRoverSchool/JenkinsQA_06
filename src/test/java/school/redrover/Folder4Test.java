package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class Folder4Test extends BaseTest {

    final String NAME_FOLDER = "Test";
    final String NAME_VIEW = "Test2";

    @Test
    public void testCreateFolder() throws InterruptedException {
        getDriver().findElement(By.linkText("New Item")).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='name']")))
                .sendKeys(NAME_FOLDER);
        getDriver().findElement(By.cssSelector(".com_cloudbees_hudson_plugins_folder_Folder")).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.id("ok-button"))).click();
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        getDriver().findElement(By.linkText("Dashboard")).click();
        WebElement nameFolder = getDriver().findElement(By.cssSelector("a[href='job/" + NAME_FOLDER + "/']>span"));
        new Actions(getDriver()).moveToElement(nameFolder).click(nameFolder).build().perform();

        Assert.assertEquals(getDriver().findElement(By.cssSelector("#main-panel>h1")).getText(), NAME_FOLDER);
        Assert.assertTrue(getDriver().findElement(By.cssSelector("svg[title='Folder']")).isDisplayed(),
                "error was not shown icon folder");
    }

    @Test(dependsOnMethods = {"testCreateFolder"})
    public void testCreateNewViewInFolder(){
        Actions actions = new Actions(getDriver());
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        WebElement nameFolder = getDriver().findElement(By.cssSelector("a[href='job/" + NAME_FOLDER + "/']>span"));
        actions.moveToElement(nameFolder).click(nameFolder).build().perform();
        getDriver().findElement(By.xpath("//div[@class='tab']")).click();

        getDriver().findElement(By.id("name")).sendKeys(NAME_VIEW);
        WebElement myView = getDriver().findElement(By.xpath("//fieldset/div[last()]/input"));
        js.executeScript("arguments[0].scrollIntoView();", myView);
        actions.moveToElement(myView).click(myView).build().perform();

        getDriver().findElement(By.id("ok")).click();

        getDriver().findElement(By.linkText("All")).click();

        WebElement newView = getDriver().findElement(By.linkText(NAME_FOLDER));
        Assert.assertTrue(newView.isDisplayed(),"error was not shown created view");
    }
}