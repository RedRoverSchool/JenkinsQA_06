package school.redrover;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class IuliiaTest extends BaseTest{
    @Test
    public void testThird(){


        WebElement icon = getDriver().findElement(By.id("jenkins-head-icon"));
        Assert.assertTrue(icon.isDisplayed());
    }
}

