package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class Folder5Test extends BaseTest {

    private static final By DASHBOARD = By.xpath("//img[@id='jenkins-head-icon']");

    @Test
    public void testCreateNewFolder() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        getDriver().findElement(By.id("name")).sendKeys("New folder");
        getDriver().findElement(By.xpath("//span[text() = 'Folder']/..")).click();
        getDriver().findElement(By.id("ok-button")).click();

        getDriver().findElement(DASHBOARD).click();
        WebElement folderName = getDriver().findElement(By.xpath("//span[text() = 'New folder']"));

        Assert.assertEquals(folderName.getText(), "New folder");
    }

    @Test(dependsOnMethods = "testCreateNewFolder")
    public void testRenameFolder() {
        WebElement dropdown = getDriver().findElement(By.xpath("//a[@href='job/New%20folder/']/button"));
        new Actions(getDriver()).moveToElement(dropdown).perform();
        dropdown.sendKeys(Keys.RETURN);
        getWait2().until(ExpectedConditions.elementToBeClickable(By
                .xpath("//a[@href='/job/New%20folder/confirm-rename']"))).click();

        WebElement input = getDriver().findElement(By.xpath("//input[@name='newName']"));
        input.clear();
        input.sendKeys("New name folder");
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        getDriver().findElement(DASHBOARD).click();
        WebElement newFolderName = getDriver().findElement(By.xpath("//a[@href='job/New%20name%20folder/']/span"));

        Assert.assertEquals(newFolderName.getText(), "New name folder");
    }
}
