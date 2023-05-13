package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class MultiConfigurationProject1Test extends BaseTest {

    @Test
    public void testCreateMultiConfiguration() {

        final String projectName = "test";

        getDriver().findElement(By.xpath("//span[text()='New Item']/../..")).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name = 'name']"))).sendKeys(projectName);
        getDriver().findElement(By.xpath("//span[text()='Multi-configuration project']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.xpath("//button[@formNoValidate='formNoValidate']")).click();
        getDriver().findElement(By.cssSelector("#breadcrumbBar > ol > li")).click();

        String actualResult = getDriver().findElement(By.cssSelector(" [href='job/test/']")).getText();

        Assert.assertEquals(actualResult, projectName);
    }
    @Test(dependsOnMethods = {"testCreateMultiConfiguration"})

    public void testAddDescription() {

        String expectedResult = "There is the test project";

        WebElement testButton = getDriver().findElement(By.cssSelector(" [href='job/test/']"));
        new Actions(getDriver()).moveToElement(testButton).click(testButton).build().perform();
        getDriver().findElement(By.xpath("//a[@id = 'description-link']")).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea"))).sendKeys(expectedResult);
        getDriver().findElement(By.xpath("(//button[@formnovalidate = 'formNoValidate'])[1]")).click();

        String actualResult = getDriver().findElement(By.xpath("//div[text() = 'There is the test project'] ")).getText();

        Assert.assertEquals(actualResult, expectedResult);
    }
    @Test(dependsOnMethods = {"testCreateMultiConfiguration"})
    public void testDisableProject() {

        String expectedResult = "This project is currently disabled";

        WebElement testButton = getDriver().findElement(By.cssSelector(" [href='job/test/']"));
        new Actions(getDriver()).moveToElement(testButton).click(testButton).build().perform();
        getDriver().findElement(By.xpath("//form/button[@formnovalidate = 'formNoValidate']")).click();
        String actualResult = getDriver().findElement(By.xpath("//form[@id = 'enable-project']")).getText();

        Assert.assertTrue(actualResult.contains(expectedResult));
    }
}
