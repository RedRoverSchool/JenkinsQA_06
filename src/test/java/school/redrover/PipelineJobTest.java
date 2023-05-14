package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import java.util.List;

public class PipelineJobTest extends BaseTest {

    String projectName = "ProjectName";

    private void createJob(String nameOfProject, String jobType) {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        WebElement fieldName = getDriver().findElement(By.id("name"));
        getWait2().until(ExpectedConditions.visibilityOf(fieldName)).sendKeys(nameOfProject);
        getDriver().findElement(By.xpath("//span[contains(text(), '" + jobType + "')]")).click();
        getDriver().findElement(By.id("ok-button")).click();

        getWait2().until(ExpectedConditions.elementToBeClickable(By.name("Submit"))).click();
    }

    private void goBackToDashboardByJenkinsIcon() {
        getWait2().until(ExpectedConditions.elementToBeClickable(By.id("jenkins-name-icon"))).click();
    }

    @Test
    public void testCreateMethod() {
        createJob(projectName, "Pipeline");

        goBackToDashboardByJenkinsIcon();

        Assert.assertEquals(
                getDriver().findElement(By.xpath("//*[contains(text(),'" + projectName + "')]")).getText(),
                projectName);
    }

    @Test(dependsOnMethods = "testCreateMethod")
    public void testCancelDeleting() {

        WebElement createdJob = getDriver().findElement(By.xpath("//*[contains(text(),'" + projectName + "')]"));

        new Actions(getDriver())
                .moveToElement(getDriver()
                    .findElement(By.xpath("//a[@href='job/"+projectName+"/']//button")))
                .click()
                .perform();

        List<WebElement> dropDown = getDriver().findElements(By.xpath("//ul[@class='first-of-type']/li"));
        dropDown.get(3).click();

        getDriver().switchTo().alert().dismiss();

        Assert.assertTrue(createdJob.isDisplayed());
    }

    @Test(dependsOnMethods = "testCancelDeleting")
    public void testDeletePipelineByDropDown() {

        new Actions(getDriver())
                .moveToElement(getDriver()
                        .findElement(By.xpath("//a[@href='job/"+projectName+"/']//button")))
                .click()
                .perform();

        List<WebElement> dropDown = getDriver().findElements(By.xpath("//ul[@class='first-of-type']/li"));
        dropDown.get(3).click();

        getDriver().switchTo().alert().accept();

        Assert.assertTrue(getWait2().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(
                "//*[contains(text(),'" + projectName + "')]"))));
    }

    @Test
    public void testDeletePipelineByTheLeftSidebar() {
        createJob(projectName, "Pipeline");

        goBackToDashboardByJenkinsIcon();

        new Actions(getDriver())
                .moveToElement(getDriver()
                .findElement(By.xpath("//span[contains(text(),'"+projectName+"')]")), 10, 0)
                .click()
                .perform();

        getDriver().findElement(By.xpath("//span[contains(text(), 'Delete Pipeline')]")).click();

        getDriver().switchTo().alert().accept();

        Assert.assertTrue(getWait2().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(
                "//*[contains(text(),'"+projectName+"')]"))));
    }
}
