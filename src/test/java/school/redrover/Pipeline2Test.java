package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;


public class Pipeline2Test extends BaseTest {

    @Test
    public void TestCreatePipeline(){
        WebDriver driver = getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        WebElement projectName = driver.findElement(By.className("jenkins-input"));
        final String itemName = "My Pipeline";
        final String desc = "Description of the Pipeline";
        projectName.sendKeys(itemName);

        WebElement projectType = driver.findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob"));
        projectType.click();

        WebElement projectOk = driver.findElement(By.id("ok-button"));
        projectOk.click();

        WebElement newDescription = driver.findElement(By.xpath("//div[@class='setting-main']/textarea[@name='description']"));
        newDescription.sendKeys(desc);

        WebElement submitDesc = driver.findElement(By.name("Submit"));
        submitDesc.click();

        WebElement nameVal = driver.findElement(By.xpath("//div[@id='main-panel']/h1"));
        Assert.assertEquals(nameVal.getText(), "Pipeline " + itemName);

        WebElement descriptionVal = driver.findElement(By.xpath("//div[@id='main-panel']/div[@id='description']/div[not( contains( @class, 'jenkins' ) )]"));
        Assert.assertEquals(descriptionVal.getText(), desc);
    }

    @Test
    public void testCreatePipelineProjectCorrectName() {
        WebElement newItem = getDriver().findElement(By.xpath("//div[@id='tasks']//a[@href='/view/all/newJob']"));
        newItem.click();

        WebElement itemName = getDriver().findElement(By.id("name"));
        itemName.sendKeys("PipelineProject");

        WebElement typeProject = getDriver().findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob"));
        typeProject.click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.name("Submit")).click();
        getDriver().findElement(By.xpath("//div[@id='breadcrumbBar']//a[@href='/']")).click();

        WebElement projectExist = getDriver().findElement(By.xpath("//td/a[@class='jenkins-table__link model-link inside']"));
        Assert.assertEquals(projectExist.getText(), "PipelineProject");
    }

    @Test
    public void testCreatePipelineWithSpaceInsteadOfName() {
        WebElement newItem = getDriver().findElement(By.xpath("//div[@id='tasks']//a[@href='/view/all/newJob']"));
        newItem.click();

        WebElement itemName = getDriver().findElement(By.id("name"));
        itemName.sendKeys("  ");

        WebElement typeProject = getDriver().findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob"));
        typeProject.click();
        getDriver().findElement(By.id("ok-button")).click();

        Assert.assertEquals((getDriver().findElement(By.xpath("//div[@id='main-panel']/h1"))).
                getText(), "Error");
        Assert.assertEquals((getDriver().findElement(By.xpath("//div[@id='main-panel']/p"))).
                getText(), "No name is specified");
    }
}
