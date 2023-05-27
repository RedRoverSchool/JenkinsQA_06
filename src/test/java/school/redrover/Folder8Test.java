package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class Folder8Test extends BaseTest {
    @Test
    public void createFolderByCreateAJobButtonOnMainPageTest() {
        String name = "MyBalcon";

        WebElement createAJob = getDriver().findElement(By.xpath("//*[@id='main-panel']/div[2]/div/section[1]/ul/li/a"));
        createAJob.click();

        WebElement inputName = getDriver().findElement(By.xpath("//*[@id='name']"));
        inputName.sendKeys(name);

        WebElement folderInList = getDriver().findElement(By.xpath("//*[@id='j-add-item-type-nested-projects']/ul/li[1]"));
        folderInList.click();

        getDriver().findElement(By.xpath("//*[@id='ok-button']")).click();

        WebElement saveButton = getDriver().findElement(By.xpath("//*[@id='bottom-sticker']/div/button[1]"));
        saveButton.click();

        WebElement dashButton = getDriver().findElement(By.xpath("//*[@id='breadcrumbs']/li[1]/a"));
        dashButton.click();

        WebElement actualResult = getDriver().findElement(By.xpath("//*[@id='job_" + name + "']/td[3]/a/span"));
        Assert.assertEquals(actualResult.getText(), name);
    }
}
