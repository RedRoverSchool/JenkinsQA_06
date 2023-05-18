package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class FreestyleProject01Test extends BaseTest {

    @Test
    public void testCreateFreestyleProject(){
        String name = "Freestyle";
        getDriver().findElement(By.xpath("//a[@href = '/view/all/newJob']")).click();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.name("name"))).sendKeys(name);
        getDriver().findElement(By.xpath("//li[@class = 'hudson_model_FreeStyleProject']")).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.id("ok-button"))).click();

        getWait2().until(ExpectedConditions.elementToBeClickable(By.name("Submit"))).click();

        Assert.assertEquals(getDriver().findElement(By.tagName("h1")).getText(), "Project " + name);
    }
}
