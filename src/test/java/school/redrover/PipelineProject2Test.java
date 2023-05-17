package school.redrover;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

public class PipelineProject2Test extends BaseTest {

    private static final String PROJECT_NAME = RandomStringUtils.randomAlphanumeric(7);
    private static final By INPUT_NAME = By.name("name");
    private static final By NEW_NAME = By.name("newName");


    @Test
    public void testCreatePipelineProject() {

        TestUtils.createPipeline(this, PROJECT_NAME, false);
        getDriver().findElement(By.xpath(" //h1[@class= \"job-index-headline page-headline\"]")).getText();
        Assert.assertEquals(getDriver().findElement(By.xpath(" //h1[@class= \"job-index-headline page-headline\"]")).getText(), "Pipeline " + PROJECT_NAME);
    }

    @Test
    public void testCreateDuplicatePipelineProject() {

        TestUtils.createPipeline(this, PROJECT_NAME, true);

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(INPUT_NAME));
        getDriver().findElement(INPUT_NAME).sendKeys(PROJECT_NAME);
        getDriver().findElement(By.xpath("//span[contains(text(), 'Pipeline')]")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@class='input-validation-message']")).getText(), "» A job already exists with the name " + "‘" + PROJECT_NAME + "’");
    }

    @Test
    public void testRenamePipelineProject() {

        TestUtils.createPipeline(this, PROJECT_NAME, true);

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[href='job/" + PROJECT_NAME + "/']"))).click();
        getDriver().findElement(By.cssSelector("a[href='/job/" + PROJECT_NAME + "/confirm-rename']")).click();
        getDriver().findElement(NEW_NAME).clear();
        getDriver().findElement(NEW_NAME).sendKeys(PROJECT_NAME + " Renamed");
        getDriver().findElement(By.name("Submit")).click();

        Assert.assertTrue(getDriver().findElement(By.id("main-panel")).getText().contains(PROJECT_NAME + " Renamed"));

    }

    @Test
    public void testDisablePipelineProject() {

        TestUtils.createPipeline(this, PROJECT_NAME, true);

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[href='job/" + PROJECT_NAME + "/']"))).click();
        getDriver().findElement(By.name("Submit")).click();

        Assert.assertTrue(getDriver().findElement(By.id("enable-project")).getText().contains("This project is currently disabled"));
    }
}



