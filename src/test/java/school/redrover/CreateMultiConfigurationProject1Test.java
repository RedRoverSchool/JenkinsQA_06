package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class CreateMultiConfigurationProject1Test extends BaseTest {

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


}
