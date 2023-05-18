package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import static org.openqa.selenium.By.xpath;

public class Pipeline9Test extends BaseTest {
    private static final By SCRIPT_BUTTON = xpath("//div[@class = 'samples']/select");
    String name = "Мой проект";

    @Test
    public void testCreateNewPipelineWithScript(){

        getDriver().findElement(xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys(name);
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath
                ("//li[@class = 'org_jenkinsci_plugins_workflow_job_WorkflowJob']"))).click();
        getDriver().findElement(xpath("//button[@id='ok-button']")).click();
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(SCRIPT_BUTTON));

        Select selectPipelineScript = new Select(getWait2().until(ExpectedConditions.visibilityOfElementLocated
                ( SCRIPT_BUTTON)));
        selectPipelineScript.selectByVisibleText("Scripted Pipeline");
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.name("Submit"))).click();

        Assert.assertEquals(getDriver().findElement(xpath("//h1[@class='job-index-headline page-headline']")).getText(),
                "Pipeline " + name);
    }
}