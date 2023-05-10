package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class CreatePipelineProjectTest extends BaseTest {

    @Test
    public void testCreatePipelineProjectTest () {
        String projectName = "New Project";

        WebElement newItemButton = getDriver().findElement(By.xpath("//a[@href = '/view/all/newJob']"));
        newItemButton.click();

        WebElement inputField = getDriver().findElement(By.id("name"));
        inputField.sendKeys(projectName);

        WebElement jobType = getDriver()
                .findElement(By.xpath("//*[@id='j-add-item-type-standalone-projects']/ul/li[2]"));
        jobType.click();

        WebElement okButton = getDriver().findElement(By.id("ok-button"));
        okButton.click();

        WebElement saveButton = getDriver().findElement(By.xpath("//*[@name = 'Submit']"));
        saveButton.click();


        Assert.assertEquals(getDriver().findElement(By.xpath("//*[@id = 'main-panel']/h1")).getText(),
                "Pipeline " + projectName);
    }

    @Test
    public void testVerifyPipelineProjectOnDashboard () {
        testCreatePipelineProjectTest();

        WebElement dashboardButton = getDriver().findElement(By.xpath("//*[@id='breadcrumbs']/li[1]/a"));
        dashboardButton.click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//*[@id='job_New Project']/td[3]/a/span"))
                .getText(), "New Project");
    }

    @Test
    public void testCreatePipelineViaCreateAJobLink() {
        getDriver().findElement(By.xpath("//span[text() = 'Create a job']")).click();
        getDriver().findElement(By.id("name")).sendKeys("UserProject");
        getDriver().findElement(By.xpath("//li[contains(@class, 'WorkflowJob')]")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
        getDriver().findElement(By.name("Submit")).click();
        getWait5().until(ExpectedConditions.presenceOfElementLocated(By.tagName("h1")));

        Assert.assertEquals(getDriver().getTitle(), "UserProject [Jenkins]");
    }
}
