package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;

public class FreestyleProject3Test extends BaseTest {

    public void createFreestyleProject() {
        getDriver().findElement(By.xpath("//div[@id='tasks']//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.xpath("//input[@id='name']")).sendKeys("Engineer");
        getDriver().findElement(By.cssSelector("[value='hudson.model.FreeStyleProject'] + span")).click();
        getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();
        getDriver().findElement(By.xpath("//ol[@id='breadcrumbs']/li[1]")).click();
    }

    @Test
    public void testCreatedNewBuild() {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
        createFreestyleProject();

        getDriver().findElement(By.cssSelector("[href$='Engineer/']")).click();
        getDriver().findElement(By.cssSelector("[href*='build?']")).click();
        getDriver().findElement(By.cssSelector("[href$='console']")).click();

        WebElement result = getDriver().findElement(By.cssSelector(".jenkins-icon-adjacent"));

        Assert.assertTrue(result.isDisplayed());
    }
}
