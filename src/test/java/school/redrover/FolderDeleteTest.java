package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class FolderDeleteTest extends BaseTest {

    private void newFolder(String name) {
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/view/all/newJob']"))).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='name']"))).sendKeys(name);
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='j-add-item-type-nested-projects']//div[@class='desc']"))).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='ok-button']"))).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='Submit']"))).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='breadcrumbs']//a"))).click();
    }

    @Test
    public void testNewFolderCreate() {
        String name = "FolderTest";
        newFolder(name);

        WebElement title = getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//td//a//span[1]")));

        Assert.assertEquals(title.getText(), name);
    }
}