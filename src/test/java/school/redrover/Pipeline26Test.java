package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class Pipeline26Test extends BaseTest{
    private static final String NAME_PIPELINE_PROJECT = "pipeline";
    @Test
    public void testCreate() {

        getDriver().findElement(By.xpath("//div[@class='task '][1]")).click();

        WebElement nameField = getDriver().findElement(By.xpath("//input[@id='name']"));
        nameField.sendKeys(NAME_PIPELINE_PROJECT);

        getDriver().findElement(By.xpath("//*[@class='org_jenkinsci_plugins_workflow_job_WorkflowJob']")).click();

        getDriver().findElement(By.id("ok-button")).click();

        getDriver().findElement(By.name("Submit")).click();

        WebElement pipelineElement = getDriver().findElement(By.xpath("//h1[@class='job-index-headline page-headline']"));
        Assert.assertEquals(pipelineElement.getText(), "Pipeline " + NAME_PIPELINE_PROJECT);
    }
}
