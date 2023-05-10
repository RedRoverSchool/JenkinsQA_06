package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class CreateFreestyleProjectADTest extends BaseTest {

    @Test
    public void testCreateFreestyleProject() {
        String expectedResult = "Engineer2";

        getDriver().findElement(By.xpath("//div[@id='tasks']//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.xpath("//input[@id='name']")).sendKeys("Engineer2");
        getDriver().findElement(By.cssSelector("[value='hudson.model.FreeStyleProject'] + span")).click();
        getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();
        getDriver().findElement(By.cssSelector("#breadcrumbs > li ")).click();

        String actualResult = getDriver().findElement(By.cssSelector("[href$='Engineer2/']")).getText();

        Assert.assertEquals(actualResult, expectedResult);
    }
}
