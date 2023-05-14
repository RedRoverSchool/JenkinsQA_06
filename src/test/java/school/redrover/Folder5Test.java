package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import org.openqa.selenium.JavascriptExecutor;

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
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        WebElement folder = getDriver().findElement(By.xpath("(//a[@class='jenkins-table__link model-link inside'])[1]"));
        new Actions(getDriver()).moveToElement(folder).perform();

        WebElement dropdown = getDriver().findElement(By.xpath("(//button[@class='jenkins-menu-dropdown-chevron'])[5]"));
        js.executeScript("arguments[0].click();", dropdown);

        getWait2().until(ExpectedConditions.elementToBeClickable(By
                .xpath("//a[@href='/job/New%20folder/confirm-rename']"))).click();

        WebElement input = getDriver().findElement(By.xpath("//input[@name='newName']"));
        input.clear();
        input.sendKeys("New name folder");
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        getDriver().findElement(DASHBOARD).click();

        WebElement newFolderName = getDriver().findElement(By.xpath("(//a[@class='jenkins-table__link model-link inside'])[1]"));

        Assert.assertEquals(newFolderName.getText(), "New name folder");
    }
}
