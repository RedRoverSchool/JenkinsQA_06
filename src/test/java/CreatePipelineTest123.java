import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class CreatePipelineTest123 extends BaseTest {

@Test

    public void testCreatePipeline123() {

    String expectedPipelineName = "NewPipe";

    getDriver().findElement(By.xpath("//span[text()='New Item']/../..")).click();
    WebElement inputField = getDriver().findElement(By.xpath("//input[@name = 'name']"));
    inputField.sendKeys("NewPipe");

    getDriver().findElement
            (By.xpath("//li[@class='org_jenkinsci_plugins_workflow_job_WorkflowJob']")).click();
    getDriver().findElement(By.id("ok-button")).click();
    getDriver().findElement(By.xpath("//button[@formNoValidate='formNoValidate']")).click();
    getDriver().findElement(By.xpath("//a[text()='Dashboard']")).click();

    String actualPipelineName = getDriver().findElement
            (By.xpath("//a[@class='jenkins-table__link model-link inside']")).getText();

    Assert.assertEquals(actualPipelineName,expectedPipelineName);
}

@Test

    public void testCreatePipelinesWithTheSameName123 () {

    getDriver().findElement(By.xpath("//span[text()='New Item']/../..")).click();
    WebElement inputField1 = getDriver().findElement(By.xpath("//input[@name = 'name']"));
    inputField1.sendKeys("NewPipe");

    getDriver().findElement
            (By.xpath("//li[@class='org_jenkinsci_plugins_workflow_job_WorkflowJob']")).click();
    getDriver().findElement(By.id("ok-button")).click();
    getDriver().findElement(By.xpath("//button[@formNoValidate='formNoValidate']")).click();
    getDriver().findElement(By.xpath("//a[text()='Dashboard']")).click();
    getDriver().findElement(By.linkText("NewPipe")).isDisplayed();

    getDriver().findElement(By.xpath("//span[text()='New Item']/../..")).click();
    WebElement inputField2 = getDriver().findElement(By.xpath("//input[@name = 'name']"));
    inputField2.sendKeys("NewPipe");

    String expectedErrorMassage = "» A job already exists with the name ‘NewPipe’";
    String actualErrorMassage =
            getDriver().findElement(By.xpath("//div[@class='input-validation-message']")).getText();

    Assert.assertEquals(actualErrorMassage, expectedErrorMassage);

}
}
