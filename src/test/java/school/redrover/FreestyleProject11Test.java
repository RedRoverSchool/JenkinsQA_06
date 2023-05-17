package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class FreestyleProject11Test extends BaseTest {

    @Test
    public void testCreateFreestyleProject(){
        String nameProject = "Test";

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.xpath("//input [@id='name']")).sendKeys(nameProject);
        getDriver().findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']")).click();

        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='ok-button']"))).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='Submit']"))).click();

        Assert.assertTrue(getDriver().findElement(By.xpath("//h1")).isDisplayed());
    }
}
