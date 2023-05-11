package school.redrover;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class DeletePipelineTest extends BaseTest {

    private final String RANDOM_STRING = RandomStringUtils.randomAlphabetic(5);
    private WebElement findElementUsingTextInXpath(String searchText) {
        return getDriver().findElement(By.xpath("//*[contains(text(),'" + searchText + "')]"));
    }

    private void goBackToDashboardFromJobPage() {
        getDriver().findElement(By.id("jenkins-name-icon")).click();
    }
    private void createJob(String typeOfJob) {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys(RANDOM_STRING);
        findElementUsingTextInXpath(typeOfJob).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.name("Submit")).click();
    }

    @Test
    public void testDeletePipelineByTheLeftSidebar() {
        createJob("Pipeline");

        String projectName = getDriver()
                .findElement(By.xpath("(//li[@class= 'jenkins-breadcrumbs__list-item']/a)[2]"))
                .getText();

        goBackToDashboardFromJobPage();

        getDriver().findElement(By.xpath("//li[@href = '/']")).click();
        getDriver().findElement(By.xpath("//a[@href = '/view/all/']")).click();
        getDriver().findElement(By.xpath("//li[@href = '/view/all/']")).click();
        getDriver().findElement(By.xpath("//a[@href = '/view/all/job/" + projectName + "/']")).click();
        findElementUsingTextInXpath("Delete Pipeline").click();
        getDriver().switchTo().alert().accept();

        Assert.assertTrue(getWait2().until(ExpectedConditions
                .invisibilityOfElementWithText(By.xpath(
                        "//*[contains(text(),'" + projectName + "')]"), projectName)));
        Assert.assertTrue(getWait2().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(
                "//*[contains(text(),'" + projectName + "')]"))));
    }
    @Test
    public void testDeletePipelineByDropDown() {
        createJob("Pipeline");

        String projectName = getDriver()
                .findElement(By.xpath("(//li[@class= 'jenkins-breadcrumbs__list-item']/a)[2]"))
                .getText();

        goBackToDashboardFromJobPage();

        findElementUsingTextInXpath(projectName).click();
        findElementUsingTextInXpath("Delete Pipeline").click();
        getDriver().switchTo().alert().accept();

        Assert.assertTrue(getWait2().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(
                "//*[contains(text(),'" + projectName + "')]"))));
    }
    @Test
    public void cancelDeletingPipeline() {
        createJob("Pipeline");

        String jobName = getDriver()
                .findElement(By.xpath("(//li[@class= 'jenkins-breadcrumbs__list-item']/a)[2]"))
                .getText();

        goBackToDashboardFromJobPage();

        findElementUsingTextInXpath(jobName).click();
        findElementUsingTextInXpath("Delete Pipeline").click();

        getDriver().switchTo().alert().dismiss();

        Assert.assertTrue(findElementUsingTextInXpath(jobName).isDisplayed());
    }
}
