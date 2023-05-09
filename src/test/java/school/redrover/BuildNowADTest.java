package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;

public class BuildNowADTest extends BaseTest {
    @Test
    public void testBuildNow()  {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));

        getDriver().findElement(By.xpath("//div[@id='tasks']//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.xpath("//input[@id='name']")).sendKeys("Engineer");
        getDriver().findElement(By.cssSelector("[value='hudson.model.FreeStyleProject'] + span")).click();
        getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();
        getDriver().findElement(By.cssSelector("#breadcrumbs > li ")).click();

        getDriver().findElement(By.cssSelector("[href$='Engineer/']")).click();
        getDriver().findElement(By.cssSelector("[href*='build?']")).click();
        getDriver().findElement(By.cssSelector("[href$='console']")).click();

        WebElement result = getDriver().findElement(By.cssSelector(".jenkins-icon-adjacent"));

        Assert.assertTrue(result.isDisplayed());
    }
}
