package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class Folder3Test extends BaseTest {

    @Ignore
    @Test
    public void testCreateFolder() {

        WebElement newItem = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
        newItem.click();

        WebElement field = getDriver().findElement(By.xpath("//input[@id='name']"));
        field.sendKeys("folder");

        WebElement checkboxFolder = getDriver().findElement(By.xpath
                ("//li[@class='com_cloudbees_hudson_plugins_folder_Folder']"));
        checkboxFolder.click();

        WebElement tabOk = getDriver().findElement(By.xpath("//button[@id='ok-button']"));
        tabOk.click();

        WebElement tabSave = getDriver().findElement(By.xpath("//button[normalize-space()='Save']"));
        tabSave.click();

        WebElement dashboard = getDriver().findElement(By.xpath("//a[normalize-space()='Dashboard']"));
        dashboard.click();

        WebElement textFolder = getDriver().findElement(By.xpath("//span[normalize-space()='folder']"));

        Assert.assertEquals(textFolder.getText(), "folder");

        WebElement folder = getDriver().findElement(By.xpath("//span[normalize-space()='folder']"));
        folder.click();
    }
}
