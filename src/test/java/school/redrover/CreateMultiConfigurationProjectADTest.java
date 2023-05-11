package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class CreateMultiConfigurationProjectADTest extends BaseTest {

    @Test
    public void testCreateMultiConfigurationProject() {

        String expectedResult = "Engineer3";

        getDriver().findElement(By.cssSelector("[href$='/newJob']")).click();
        getDriver().findElement(By.cssSelector("input[id='name']")).sendKeys("Engineer3");
        getDriver().findElement(By.cssSelector("[value$='MatrixProject'] + span")).click();
        getDriver().findElement(By.cssSelector("#ok-button")).click();
        getDriver().findElement(By.cssSelector("button[name='Submit']")).click();
        getDriver().findElement(By.cssSelector("li:nth-child(1) > a")).click();

        WebElement result = getDriver().findElement(By.cssSelector("#projectstatus"));
        Assert.assertTrue(result.isDisplayed(), "project no display");
    }
}
