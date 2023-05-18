package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class Folder3Test extends BaseTest {

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

        WebElement folderName = getDriver().findElement(By.xpath("(//span[normalize-space()='folder'])[1]"));

        Assert.assertEquals(folderName.getText(), "folder");
    }

    @Test(dependsOnMethods = "testCreateFolder")
    public void testCreateFreestyleProjectInFolder() {

        getDriver().findElement(By.xpath("//span[normalize-space()='folder']")).click();

        getDriver().findElement(By.xpath("//a[@href='/job/folder/newJob']")).click();

        getDriver().findElement(By.xpath("//input[@id='name']")).sendKeys("new project");

        getDriver().findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']")).click();

        getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();

        getDriver().findElement(By.xpath("//button[normalize-space()='Save']")).click();

        getDriver().findElement(By.xpath("//a[normalize-space()='folder']")).sendKeys(Keys.RETURN);

        WebElement projectName = getDriver().findElement(By.xpath("//span[normalize-space()='new project']"));

        Assert.assertEquals(projectName.getText(), "new project");
    }
}
