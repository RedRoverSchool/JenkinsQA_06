package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class FolderTest3 extends BaseTest {
    public void createBaseFolder () {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        WebElement nameinput=getDriver().findElement(By.name("name"));
        getWait2().until(ExpectedConditions.visibilityOf(nameinput));
        nameinput.sendKeys("folder1");

        getDriver().findElement(By.xpath("//li[@class='com_cloudbees_hudson_plugins_folder_Folder']")).click();
        WebElement okbutton = getDriver().findElement(By.id("ok-button"));
        okbutton.click();

        getWait2().until(ExpectedConditions.textToBe(By.xpath("//h1[text()='Configuration']"), "Configuration"));
    }
    @Test
    public void testCreateFolder (){
        createBaseFolder();

        getDriver().findElement(By.xpath("//a[normalize-space()='folder1']")).click();
        Assert.assertEquals(getDriver().findElement(By.xpath("//a[normalize-space()='folder1']")).getText(),"folder1");
    }
}
