package school.redrover;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class RenamePipelineTest extends BaseTest {

    private static final By SAVE = By.name("Submit");
    private static final By OK_BUTTON = By.id("ok-button");
    private static final By DASHBOARD = By.id("jenkins-name-icon");
    private static final By NEW_ITEM = By.xpath("//a[@href='/view/all/newJob']");



    public void createPipelineProject(String jobName) {
        getDriver().findElement(NEW_ITEM).click();
        getDriver().findElement(By.id("name")).sendKeys(jobName);
        getDriver().findElement(By.xpath("//li[@class='org_jenkinsci_plugins_workflow_job_WorkflowJob']")).click();
        getDriver().findElement(OK_BUTTON).click();
        getDriver().findElement(SAVE).click();

    }

    @Test
    public void testRenamePipelineProject() {
        String jobName = RandomStringUtils.randomAlphanumeric(7);
        String rename = RandomStringUtils.randomAlphanumeric(7);

        createPipelineProject(jobName);

        getDriver().findElement(DASHBOARD).click();
        String link = getDriver().findElement(By.xpath("//*[@id=\"job_" + jobName + "\"]/td[3]/a")).getAttribute("href");
        getDriver().get(link);

        getDriver().findElement(By.xpath("//span[contains(text(),'Rename')]/ancestor::span")).click();
        WebElement inputField = getDriver().findElement(By.xpath("//input[@name='newName']"));
        inputField.clear();
        inputField.sendKeys(rename);

        getDriver().findElement(SAVE).click();
        getDriver().findElement(DASHBOARD).click();
        Assert.assertEquals(getDriver().findElement(By.xpath("//span[normalize-space()='" + rename + "']")).getText(), rename);


    }


}
