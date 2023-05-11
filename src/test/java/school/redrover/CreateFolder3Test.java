package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class CreateFolder3Test extends BaseTest {
    private final String FOLDER_NAME = "New Folder";

    @Test
    public void testCreateFolder3(){
        WebElement newItem = getDriver().findElement(By.xpath("//div[@class = 'task ']//a[@href='/view/all/newJob']"));
        newItem.click();

        WebElement inputField = getDriver().findElement(By.xpath("//input[@class='jenkins-input']"));
        WebElement folderOption = getDriver().findElement(By.xpath("//li[contains(@class,'folder_Folder')]"));
        WebElement okButton = getDriver().findElement(By.xpath("//button[@id='ok-button']"));
        inputField.sendKeys(FOLDER_NAME);
        folderOption.click();
        okButton.click();

        WebElement saveButton = getDriver().findElement(By.xpath("//button[@name='Submit']"));
        saveButton.click();
        WebElement jenkinsLogo = getDriver().findElement(By.xpath("//a[@id='jenkins-home-link']"));
        jenkinsLogo.click();

        WebElement createdFolder = getDriver().findElement(By.xpath("//table[@id='projectstatus']//span[normalize-space()='New Folder']"));
        Assert.assertEquals(createdFolder.getText(),FOLDER_NAME,"can't find folder name on the dashboard");

        createdFolder.click();
        Assert.assertTrue(getDriver().getTitle().contains(FOLDER_NAME), "no folder name on the title");

    }
}
