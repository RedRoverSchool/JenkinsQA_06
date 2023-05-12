package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class MultiConfigurationProject1Test extends BaseTest {

    @Test
    public void testCreateMultiConfiguration() {

        String expectedResult = "test";

        getDriver().findElement(By.xpath("//span[text()='New Item']/../..")).click();
        WebElement itemName = getDriver().findElement(By.xpath("//input[@name = 'name']"));
        itemName.click();
        itemName.sendKeys("test");

        getDriver().findElement(By.xpath("//span[text()='Multi-configuration project']")).click();
        getDriver().findElement(By.id("ok-button")).click();

        getDriver().findElement(By.xpath("//button[@formNoValidate='formNoValidate']")).click();
        getDriver().findElement(By.cssSelector("#breadcrumbBar > ol > li")).click();

        String actualResult = getDriver().findElement(By.cssSelector(" [href='job/test/']")).getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test(dependsOnMethods = {"testCreateMultiConfiguration"})

    public void testAddDescription() {

        String expectedResult = "There is the test project";
        WebElement testButton = getDriver().findElement(By.cssSelector(" [href='job/test/']"));
        new Actions(getDriver()).moveToElement(testButton).click().build().perform();
        getDriver().findElement(By.xpath("//a[@id = 'description-link']")).click();
        getDriver().findElement(By.xpath("//textarea")).sendKeys(expectedResult);
        getDriver().findElement(By.xpath("(//button[@formnovalidate = 'formNoValidate'])[1]")).click();

        String actualResult = getDriver().findElement(By.cssSelector("#description>div:first-child ")).getText();

        Assert.assertEquals(actualResult, expectedResult);
    }
}
