package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class CreateFolderTest extends BaseTest {

    @Test
    public void testCreateFolder() {

        WebElement createNewItem = getDriver().findElement(
                By.xpath("//*[@id='tasks']/div[1]/span/a"));
        createNewItem.click();

        WebElement inputName = getDriver().findElement(By.name("name"));
        inputName.sendKeys("Folder_TC_04_01_02");

        WebElement buttonFolder = getDriver().findElement(
                By.xpath("//*[@id='j-add-item-type-standalone-projects']/ul/li[1]/div[1]"));
        buttonFolder.click();

        WebElement submitButton = getDriver().findElement(
                By.xpath("//*[@id='ok-button']"));
        submitButton.click();

        WebElement buttonDashboard = getDriver().findElement(
                By.xpath("//*[@id='breadcrumbs']/li[1]/a"));
        buttonDashboard.click();

        WebElement folderName = getDriver().findElement(
                By.xpath("//*[@id='job_Folder_TC_04_01_02']/td[3]/a/span"));

        String actualResult = folderName.getText();
        folderName.click();

        Assert.assertEquals(getDriver().findElement(
                By.xpath("//*[@id='main-panel']/h1")).getText(), "Project Folder_TC_04_01_02");
        Assert.assertEquals(actualResult, "Folder_TC_04_01_02");
    }
}