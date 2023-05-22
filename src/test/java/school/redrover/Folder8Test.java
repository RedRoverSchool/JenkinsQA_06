package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class Folder8Test extends BaseTest {

    @Test
    public void testCreateFolderWithoutDescription() {
        final String expectedFolderName = "First folder";

        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/view/all/newJob']"))).click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='name']"))).sendKeys(expectedFolderName);
        getDriver().findElement(By.xpath("//span[text()='Folder']")).click();

        getWait2().until(ExpectedConditions.elementToBeClickable(By.id("ok-button"))).click();

        getWait5().until(ExpectedConditions.elementToBeClickable(By.name("Submit"))).click();

        WebElement titleName = getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1")));
        Assert.assertEquals(titleName.getText(), expectedFolderName);
    }
}
