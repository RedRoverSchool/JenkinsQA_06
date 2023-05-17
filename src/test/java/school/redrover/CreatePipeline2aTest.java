package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class CreatePipeline2aTest extends BaseTest {

    @Test
    public void testCreatePipeline() {
        String namePipeline = "PipelineTest";

        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//a [@href = '/view/all/newJob']"))).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//input [@name = 'name']")))
                .sendKeys(namePipeline);
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@class = 'org_jenkinsci_plugins_workflow_job_WorkflowJob']")))
                .click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//button [@id = 'ok-button']"))).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@name = 'description']")))
                .sendKeys(namePipeline);
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//button [@class = 'jenkins-button jenkins-button--primary ']")))
                .click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/'][@class = 'model-link']")))
                .click();

        String namePipelaneFakt = getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class = 'jenkins-table__link model-link inside']")))
                .getText();
        Assert.assertEquals(namePipeline, namePipelaneFakt);
        }
}
