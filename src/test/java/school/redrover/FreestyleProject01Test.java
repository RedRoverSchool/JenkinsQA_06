package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class FreestyleProject01Test extends BaseTest {
    private static final String FREESTYLE_NAME = "Freestyle";

    @Test
    public void testCreateFreestyleProject(){
        getDriver().findElement(By.xpath("//a[@href = '/view/all/newJob']")).click();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.name("name"))).sendKeys(FREESTYLE_NAME);
        getDriver().findElement(By.xpath("//li[@class = 'hudson_model_FreeStyleProject']")).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.id("ok-button"))).click();

        getWait2().until(ExpectedConditions.elementToBeClickable(By.name("Submit"))).click();

        Assert.assertEquals(getDriver().findElement(By.tagName("h1")).getText(), "Project " + FREESTYLE_NAME);
    }

    @Test
    public void testNameAndDescAreDisplayed(){
        String desc = "Description";
        getDriver().findElement(By.xpath("//a[@href = '/view/all/newJob']")).click();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.name("name"))).sendKeys(FREESTYLE_NAME);
        getDriver().findElement(By.xpath("//li[@class = 'hudson_model_FreeStyleProject']")).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.id("ok-button"))).click();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.name("description"))).sendKeys(desc);
        getDriver().findElement(By.name("Submit")).click();

        Assert.assertEquals(getDriver().findElement(By.tagName("h1")).getText(), "Project " + FREESTYLE_NAME);
        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id = 'description']/div")).getText(), desc);
    }
}
