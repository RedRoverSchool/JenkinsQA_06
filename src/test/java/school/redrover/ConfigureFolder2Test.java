package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import static org.testng.Assert.assertEquals;

public class ConfigureFolder2Test extends BaseTest {
    private void CreateFolder(String name){
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob'][@class='task-link ']")).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='name']")))
                .sendKeys(name);

        getDriver().findElement(By.xpath("//li[@class = 'com_cloudbees_hudson_plugins_folder_Folder']"))
                .click();

        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//button [@id='ok-button']")))
                .click();
    }

    @Test
    public void testSetDisplayName(){
        String projectName = "qwerty";
        String folderName = "folder";

        CreateFolder(projectName);

        getDriver().findElement(By.xpath("//input [@name='_.displayNameOrNull']")).sendKeys(folderName);

        getDriver().findElement(By.xpath("//button [@name='Submit']")).click();

        assertEquals(getDriver().findElement(By.xpath("//h1")).getText(), folderName);
    }
}
