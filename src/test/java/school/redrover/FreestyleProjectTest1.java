package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class FreestyleProjectTest1 extends BaseTest {

    @Test
    public void testDisplayFreestyleProjectOnDashboard() {
        WebElement newItemButton = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
        newItemButton.click();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'Enter an item name')]")));

        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='name']")));

        WebElement itemNameField = getDriver().findElement(By.xpath("//input[@name='name']"));
        itemNameField.sendKeys("testFreestyleProject");

        WebElement freestyleProjectButton = getDriver().findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']"));
        Actions actions = new Actions(getDriver());
        actions.moveToElement(freestyleProjectButton).build().perform();
        freestyleProjectButton.click();

        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='ok-button']")));

        WebElement okButton = getDriver().findElement(By.xpath("//button[@id='ok-button']"));
        okButton.click();

        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='Submit']")));

        WebElement saveButton = getDriver().findElement(By.xpath("//button[@name='Submit']"));
        saveButton.click();

        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='Jenkins']")));

        WebElement jenkinsImageHeader = getDriver().findElement(By.xpath("//img[@alt='Jenkins']"));
        jenkinsImageHeader.click();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[@id='job_testFreestyleProject']")));

        WebElement createdFreestyleProject = getDriver().findElement(By.xpath("//a[@href='job/testFreestyleProject/']"));

        Assert.assertEquals(createdFreestyleProject.getText(), "testFreestyleProject");
    }
}
