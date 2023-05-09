package school.redrover;

import org.openqa.selenium.By;
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

        String actualResult = getDriver().findElement(By.cssSelector("[href$='Engineer3/']")).getText();
        Assert.assertEquals(actualResult, expectedResult);
    }
}
