package school.redrover;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

    @Test
    public void testDeletePipeline() {
        final String PIPELINE_NAME = "My_pipeline";

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        getWait2().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.id("name")))).sendKeys(PIPELINE_NAME);

        WebElement pipelineType = getDriver().findElement(By.cssSelector(".org_jenkinsci_plugins_workflow_job_WorkflowJob"));
        pipelineType.click();

        getDriver().findElement(By.id("ok-button")).click();

        getWait5().until(ExpectedConditions.elementToBeClickable(By.name("Submit"))).click();

        getWait2().until(ExpectedConditions.textToBePresentInElement(getDriver().findElement(By.tagName("h1")), PIPELINE_NAME));

        WebElement dashboardLink = getDriver().findElement(By.xpath("//a[@href='/'][@class='model-link']"));
        dashboardLink.click();

        WebElement pipelineInList = getDriver().findElement(
                By.xpath("//a[@class ='jenkins-table__link model-link inside']/button[@class='jenkins-menu-dropdown-chevron']"));
        pipelineInList.sendKeys(Keys.RETURN);

        getWait5().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[@id='yui-gen4']/a[@href='#']")));

        WebElement deletePipelineDropdownList = getDriver().findElement(By.xpath("//li[@id='yui-gen4']/a[@href='#']"));
        deletePipelineDropdownList.click();

        Alert alert = getDriver().switchTo().alert();
        alert.accept();

        Assert.assertFalse(getDriver().findElement(By.id("main-panel")).getText().contains(PIPELINE_NAME),"Pipeline is not shown");
    }
}
