package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class CreateFolder3Test extends BaseTest {
    private static final String FOLDER_NAME = "First Folder";
    private static final String FOLDER_NAME2 = "Second Folder";

    @Test
    public void testCreateFolder(){
        createFolder(FOLDER_NAME);
        WebElement createdFolder = getDriver().findElement(By.xpath("//table[@id='projectstatus']//td//a//span"));
        Assert.assertEquals(createdFolder.getText(),FOLDER_NAME,"can't find folder name on the dashboard");

        createdFolder.click();
        Assert.assertTrue(getDriver().getTitle().contains(FOLDER_NAME), "no folder name on the title");
    }

    @Test(dependsOnMethods = "testCreateFolder")
    public void testDeleteFolder() {
        createFolder(FOLDER_NAME2);

        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'"+FOLDER_NAME2+"')]"))).click();
        getDriver().findElement(By.xpath("//span[normalize-space()='Delete Folder']")).click();
        getDriver().findElement(By.xpath("//form[@action='doDelete']//button[@name='Submit']")).click();

        Assert.assertTrue(isElementPresent(By.xpath("//table[@id = 'projectstatus']/descendant::span[contains(text(),'"+FOLDER_NAME+"')]")),
                                                         "Other folder is not displayed");
        Assert.assertFalse(isElementPresent(By.xpath("//table[@id = 'projectstatus']/descendant::span[contains(text(),'"+FOLDER_NAME2+"')]")),
                                                          "Folder is not deleted");
    }

    private void createFolder(String folderName){
        WebElement newItem = getDriver().findElement(By.xpath("//div[@class = 'task ']//a[@href='/view/all/newJob']"));
        newItem.click();
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@class='icon-folder icon-xlg']")));

        WebElement inputField = getDriver().findElement(By.xpath("//input[@class='jenkins-input']"));
        WebElement folderOption = getDriver().findElement(By.xpath("//li[contains(@class,'folder_Folder')]"));
        WebElement okButton = getDriver().findElement(By.xpath("//button[@id='ok-button']"));
        inputField.sendKeys(folderName);
        folderOption.click();
        okButton.click();

        WebElement saveButton = getDriver().findElement(By.xpath("//button[@name='Submit']"));
        saveButton.click();
        WebElement jenkinsLogo = getDriver().findElement(By.xpath("//a[@id='jenkins-home-link']"));
        jenkinsLogo.click();
    }

    public boolean isElementPresent(By by) {
        try {
            getDriver().findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}

