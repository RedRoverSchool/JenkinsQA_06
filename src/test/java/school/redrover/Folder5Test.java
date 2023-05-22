package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

public class Folder5Test extends BaseTest {

    private static final By DASHBOARD = By.linkText("Dashboard");

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

    @Test
    public void testMoveFolderToFolder() {
        TestUtils.createFolder(this, "Folder1", true);
        TestUtils.createFolder(this, "Folder2", true);

        WebElement dropdown = getDriver().findElement(By.xpath("//a[@href='job/Folder2/']/button"));
        new Actions(getDriver()).moveToElement(dropdown).perform();
        dropdown.sendKeys(Keys.RETURN);

        getWait2().until(ExpectedConditions.elementToBeClickable(By
                .xpath("//a[@href='/job/Folder2/move']"))).click();

        getDriver().findElement(By.xpath("//select[@name='destination']")).click();
        getDriver().findElement(By.xpath("//option[@value='/Folder1']")).click();
        getDriver().findElement(By.xpath("//button[@formnovalidate='formNoValidate']")).click();

        getDriver().findElement(DASHBOARD).click();
        WebElement folder1 = getDriver().findElement(By.xpath("//a[@href='job/Folder1/']"));
        new Actions(getDriver()).moveToElement(folder1).perform();
        folder1.click();

        WebElement folder2 = getDriver().findElement(By.xpath("//a[@href='job/Folder2/']"));
        new Actions(getDriver()).moveToElement(folder2).perform();
        folder2.click();

        WebElement h1 = getDriver().findElement(By.xpath("//h1"));

        Assert.assertEquals(h1.getText(), "Folder2");
    }
}
