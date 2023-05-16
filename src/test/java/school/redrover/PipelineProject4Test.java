package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class PipelineProject4Test extends BaseTest {

    @Test
    public void testCreateNewPipelineProject() {
        final String testData = "Test";

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.xpath("//input[@id='name']")).sendKeys("Test");
        getDriver().findElement(By.xpath("//span[normalize-space()='Pipeline']")).click();
        getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();
        getDriver().findElement(By.xpath("//button[normalize-space()='Save']")).click();

        WebElement pipelineName = getDriver().findElement(By.xpath("//h1[normalize-space()='Pipeline Test']"));
        Assert.assertEquals(pipelineName.getText(), "Pipeline " + testData );

    }
}
