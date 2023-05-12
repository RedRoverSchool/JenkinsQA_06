package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class CreatePipeline2aTest extends BaseTest {
    @Test
    public void testCreatePipline() {
        String name = "TestPipeline";

        getDriver().findElement(By.xpath("//a[@href ='/view/all/newJob'][@class = 'task-link ']")).click();
        getDriver().findElement(By.xpath("//form [@method = 'post']//input[@name = 'name']"))
                .sendKeys(name);
        getDriver().findElement(By.xpath("//div[@id= 'items']//li[@class = 'org_jenkinsci_plugins_workflow_job_WorkflowJob']"))
                .click();
        getDriver().findElement(By.xpath("//button[@class = 'jenkins-button jenkins-button--primary jenkins-buttons-row--equal-width'][@id = 'ok-button']"))
                .click();
        getDriver().findElement(By.xpath("//textarea[@name = 'description'][@rows = '5']"))
                .sendKeys("Opisanie");
        getDriver().findElement(By.xpath("//button [@formnovalidate='formNoValidate'][@name='Submit']")).click();
        getDriver().findElement(By.xpath("//div[@id = 'breadcrumbBar']//li[@class = 'jenkins-breadcrumbs__list-item'][1]")).click();

        String nameFakt = getDriver().findElement(By.xpath("//a[@class = 'jenkins-table__link model-link inside']")).getText();

        Assert.assertEquals(name, nameFakt);
    }
}
