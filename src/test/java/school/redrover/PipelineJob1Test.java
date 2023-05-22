package school.redrover;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

public class PipelineJob1Test extends BaseTest {
    private final String RANDOM_STRING = RandomStringUtils.randomAlphabetic(5);

    private String getProjectNameFromRandomString() {
        return getDriver()
                .findElement(By.xpath("(//li[@class= 'jenkins-breadcrumbs__list-item']/a)[2]"))
                .getText();
    }

    @Test
    public void testCreatePipeline() {
        TestUtils.createPipeline(this, RANDOM_STRING, false);

        String projectName = getProjectNameFromRandomString();
        getDriver().findElement(By.id("jenkins-name-icon")).click();

        Assert.assertTrue(getDriver().findElement(By.xpath("//a[@href= 'job/" + projectName + "/']//span")).isDisplayed());
    }
    @Test
    public void testDisablePipeline() {
        TestUtils.createPipeline(this, RANDOM_STRING, false);

        getDriver().findElement(By.xpath("//form[@id='disable-project']/button")).click();

        Assert.assertTrue(getDriver().findElement(By.xpath("//form[@id='enable-project']")).isDisplayed());
    }

    @Test
    public void testDeletePipelineByTheLeftSidebar() {
        TestUtils.createPipeline(this, RANDOM_STRING, false);

        String projectName = getProjectNameFromRandomString();

        getDriver().findElement(By.name("Submit")).click();
        getDriver().findElement(By.id("jenkins-name-icon")).click();
        getDriver().findElement(By.xpath("//li[@href = '/']")).click();
        getDriver().findElement(By.xpath("//a[@href = '/view/all/']")).click();
        getDriver().findElement(By.xpath("//li[@href = '/view/all/']")).click();
        getDriver().findElement(By.xpath("//a[@href = '/view/all/job/" + projectName + "/']")).click();
        getDriver().findElement(By.xpath("//span[contains(text(), 'Delete Pipeline')]")).click();
        getDriver().switchTo().alert().accept();

        Assert.assertTrue(getWait2().until(ExpectedConditions
                .invisibilityOfElementWithText(By.xpath(
                        "//*[contains(text(),'" + projectName + "')]"), projectName)));

        Assert.assertTrue(getWait2().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(
                "//*[contains(text(),'" + projectName + "')]"))));
    }

    @Test
    public void testDeletePipelineByDropDown() {
        TestUtils.createPipeline(this, RANDOM_STRING, false);

        String projectName = getProjectNameFromRandomString();

        getDriver().findElement(By.id("jenkins-name-icon")).click();
        getDriver().findElement(By.xpath("//span[contains(text(),'" + projectName + "')]")).click();
        getDriver().findElement(By.xpath("//span[contains(text(), 'Delete Pipeline')]")).click();
        getDriver().switchTo().alert().accept();

        Assert.assertTrue(getWait2().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(
                "//*[contains(text(),'" + projectName + "')]"))));
    }
}
