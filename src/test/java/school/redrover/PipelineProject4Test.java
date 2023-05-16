package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class PipelineProject4Test extends BaseTest {
    private static final By JENKINS_ICON = By.xpath("//img[@id='jenkins-name-icon']");

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
        getDriver().findElement(JENKINS_ICON).click();
    }

    @Test
    public void testRenameFromPipelinePage() {
        final String newName = "First Test";
        testCreateNewPipelineProject();

        getDriver().findElement(By.xpath("//span[normalize-space()='Test']")).click();
        getDriver().findElement(By.xpath("//a[@href='/job/Test/confirm-rename']")).click();
        WebElement nameField = getDriver().findElement(By.xpath("//input[@name='newName']"));
        nameField.clear();
        nameField.sendKeys(newName);
        getDriver().findElement(By.xpath("//button[normalize-space()='Rename']")).click();
        getDriver().findElement(JENKINS_ICON).click();

        WebElement jobName =  getDriver().findElement(By.xpath("//span[normalize-space()='First Test']"));
        Assert.assertEquals(jobName.getText(), newName);
    }
}
