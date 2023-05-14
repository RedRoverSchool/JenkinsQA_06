package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class Pipeline6Test extends BaseTest {

    final String name = "MyFirstJenkinsProject";


    @Test
    public void testCreatePipelineProject() {
        getDriver().findElement(By.xpath("//span[contains(text(),'Create a job')]")).click();

        getDriver().findElement(By.xpath("//input[@id='name']")).sendKeys(name);
        getDriver().findElement(By.xpath("//span[normalize-space()='Pipeline']")).click();

        getWait2().until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.id("ok-button")))).click();

        getDriver().findElement(By.xpath("//button[contains(text(),'Save')]")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//h1[contains(text(),'Pipeline " + name + "')]")).getText(), "Pipeline " + name);
    }
}
