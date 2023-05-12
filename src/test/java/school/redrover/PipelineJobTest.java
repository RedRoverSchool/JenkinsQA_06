package school.redrover;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.List;

public class PipelineJobTest extends BaseTest {

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
        createJob("The name", "Pipeline");

        String projectName = getDriver()
                .findElement(By.xpath("(//li[@class= 'jenkins-breadcrumbs__list-item']/a)[2]"))
                .getText();

        goBackToDashboardByJenkinsIcon();

        Assert.assertEquals(
                getDriver().findElement(By.xpath("//*[contains(text(),'" + projectName + "')]")).getText(),
                projectName);
    }

    @Test(dependsOnMethods = "testCreateMethod")
    public void testCancelDeleting() {
        String projectName = getDriver()
                .findElement(By.xpath("//a[@class = 'jenkins-table__link model-link inside']"))
                .getText();

        WebElement createdJob = getDriver().findElement(By.xpath("//*[contains(text(),'" + projectName + "')]"));

        new Actions(getDriver())
                .moveToElement(getDriver()
                .findElement(By.xpath("//*[@id='job_The name']//button")))
                .click()
                .perform();

        List<WebElement> dropDown = getDriver().findElements(By.xpath("//ul[@class = 'first-of-type']/descendant::li"));
        dropDown.get(3).click();

//        getWait2().until(ExpectedConditions.visibilityOf(getDriver()
//                .findElement(By.xpath("//li//a//span[contains(text(), 'Delete Pipeline')]")))).click();
//
//        getDriver().switchTo().alert().dismiss();
//        Assert.assertTrue(createdJob.isDisplayed());
    }

    @Test(dependsOnMethods = "testCancelDeleting")
    public void testDeletePipelineByDropDown() {
        String projectName = getDriver()
                .findElement(By.xpath("//a[@class = 'jenkins-table__link model-link inside']"))
                .getText();

        new Actions(getDriver())
                .moveToElement(getDriver()
                        .findElement(By.xpath("//tr[@class][1]//button[@class = 'jenkins-menu-dropdown-chevron']")))
                .click()
                .perform();

        getDriver().findElement(By.xpath("//li//a//span[contains(text(), 'Delete Pipeline')]")).click();

        getDriver().switchTo().alert().accept();

        Assert.assertTrue(getWait2().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(
                "//*[contains(text(),'" + projectName + "')]"))));
    }

    @Test
    public void testDeletePipelineByTheLeftSidebar() {
        createJob("The name", "Pipeline");

        String projectName = getDriver()
                .findElement(By.xpath("(//li[@class= 'jenkins-breadcrumbs__list-item']/a)[2]"))
                .getText();

        goBackToDashboardByJenkinsIcon();

        new Actions(getDriver())
                .moveToElement(getDriver()
                        .findElement(By.xpath("//span[contains(text(),'" + projectName + "')]")), 10, 0)
                .click()
                .perform();

        getDriver().findElement(By.xpath("//span[contains(text(), 'Delete Pipeline')]")).click();

        getDriver().switchTo().alert().accept();

        Assert.assertTrue(getWait2().until(ExpectedConditions
                .invisibilityOfElementWithText(By.xpath(
                        "//*[contains(text(),'" + projectName + "')]"), projectName)));
        Assert.assertTrue(getWait2().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(
                "//*[contains(text(),'" + projectName + "')]"))));
    }
}
