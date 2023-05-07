package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class PipelineTest2 extends BaseTest {

    private static final By NEW_ITEM = By.xpath("//a[@href='/view/all/newJob']");
    private static final By NAME = By.id("name");
    private static final By PIPELINE = By.xpath("//li[@class='org_jenkinsci_plugins_workflow_job_WorkflowJob']");
    private static final By BUTTON = By.cssSelector("#ok-button");
    private static final By DESCRIPTION = By.name("description");
    private static final By SUBMIT = By.name("Submit");
    private static final By TEXT_PIPELINE = By.cssSelector(".job-index-headline.page-headline");

    String name = "PipeLine";
    String descriptionText = "Pipeline text";

    @Test
    public void testCreatePipelineAndRename () {

        getDriver().findElement(NEW_ITEM).click();
        getDriver().findElement(NAME).sendKeys(name);
        getDriver().findElement(PIPELINE).click();
        getDriver().findElement(BUTTON).click();
        getDriver().findElement(DESCRIPTION).sendKeys(descriptionText);
        getDriver().findElement(SUBMIT).click();

        Assert.assertEquals("Pipeline " + name, getDriver().findElement(TEXT_PIPELINE).getText());
    }
}
