package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class Folder4Test extends BaseTest {

    final String NAME_FOLDER = "Test";

    @Test
    public void testCreateFolder() throws InterruptedException {
        getDriver().findElement(By.linkText("New Item")).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='name']")))
                .sendKeys(NAME_FOLDER);
        getDriver().findElement(By.cssSelector(".com_cloudbees_hudson_plugins_folder_Folder")).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.id("ok-button"))).click();
        getDriver().findElement(By.xpath("//button[@name=\"Submit\"]")).click();

        getDriver().findElement(By.linkText("Dashboard")).click();
        getDriver().findElement(By.cssSelector("a[href='job/" + NAME_FOLDER + "/']>span")).click();

        Assert.assertEquals(getDriver().findElement(By.cssSelector("#main-panel>h1")).getText(), NAME_FOLDER);
        Assert.assertTrue(getDriver().findElement(By.cssSelector("svg[title='Folder']")).isDisplayed(),
                "error was not shown icon folder");
    }
}