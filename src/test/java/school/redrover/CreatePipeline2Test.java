package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class CreatePipeline2Test extends BaseTest {

    @Test
    public void testCreatePipeline() {

        final String name = "Sample project";

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.xpath("//input[@id='name']")).sendKeys(name);
        getDriver().findElement(By.xpath("//span[normalize-space()='Pipeline']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.cssSelector("button[name='Submit']")).click();

        Assert.assertEquals(
                getDriver().findElement(By.xpath("//h1[normalize-space()='Pipeline "+ name +"']")).getText(),
                "Pipeline "+ name);
    }
}