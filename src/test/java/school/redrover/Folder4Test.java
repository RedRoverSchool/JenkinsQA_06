package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class Folder4Test extends BaseTest {

    @Test
    public void testCreateFolder(){
        final String name = "Test";
        getDriver().findElement(By.linkText("New Item")).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='name']")))
                .sendKeys(name);
        getDriver().findElement(By.cssSelector(".com_cloudbees_hudson_plugins_folder_Folder")).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.id("ok-button"))).click();
        getDriver().findElement(By.xpath("//button[@name=\"Submit\"]")).click();
        getDriver().findElement(By.linkText("Dashboard")).click();

        WebElement iconFolder = getDriver().findElement(By.cssSelector("#job_" + name + ">td>div>span"));
        Assert.assertEquals(iconFolder.getText(),"Folder");

        WebElement nameFolder = getDriver().findElement(By.linkText(name));
        Assert.assertTrue(nameFolder.isDisplayed(), "error was not shown Folder name");
    }
}