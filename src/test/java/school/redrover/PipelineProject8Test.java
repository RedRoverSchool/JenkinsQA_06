package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;


public class PipelineProject8Test extends BaseTest {

    @Test
    public void TestCreatePipeline() {
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
        getWait2().until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.name("Submit")))).click();
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

        getWait2().until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[@class ='jenkins-table__link model-link inside']/button[@class='jenkins-menu-dropdown-chevron']")))
                .sendKeys(Keys.RETURN);

        getWait5().until(ExpectedConditions.presenceOfElementLocated(By.linkText("Delete Pipeline"))).click();

        getWait2().until(ExpectedConditions.alertIsPresent()).accept();

        Assert.assertFalse(getDriver().findElement(By.id("main-panel")).getText().contains(PIPELINE_NAME),"Pipeline is not deleted");
    }

    @Test
    public void testCreatePipelineProjectIncorrectName() {
        String name = "Pipeline";
        List<String> symbol = Arrays.asList("!", "@", "#", "?", "$", "%", "^", "&", "*", "[", "]", "\\", "|", "/");

        for (String s : symbol) {
            WebElement newItem = getDriver().findElement(By.xpath("//div[@id='tasks']//a[@href='/view/all/newJob']"));
            newItem.click();
            WebElement itemName = getDriver().findElement(By.id("name"));
            itemName.sendKeys(name + s);
            WebElement typeProject = getDriver().findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob"));
            typeProject.click();

            Assert.assertEquals(getDriver().findElement(By.id("itemname-invalid")).getText(),
                    "» ‘" + s + "’ is an unsafe character");

            getDriver().findElement(By.id("ok-button")).click();

            getWait2().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//div[@id='main-panel']/h1"))));
            Assert.assertEquals((getDriver().findElement(By.xpath("//div[@id='main-panel']/h1"))).
                    getText(), "Error");
            Assert.assertEquals((getDriver().findElement(By.xpath("//div[@id='main-panel']/p"))).
                    getText(), "‘" + s + "’ is an unsafe character");
            getDriver().findElement(By.xpath("//a[contains(text(),'All')]")).click();
        }
    }
}
