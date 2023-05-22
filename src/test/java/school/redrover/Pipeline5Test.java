package school.redrover;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

public class Pipeline5Test extends BaseTest {

    private static final String NAME_PROJECT = RandomStringUtils.randomAlphanumeric(10);

    @Test
    public void testCreatePipeline() {
        TestUtils.createPipeline(this, NAME_PROJECT,false);

        Assert.assertEquals("Pipeline " + NAME_PROJECT,
                getDriver().findElement(By.cssSelector(".job-index-headline.page-headline")).getText());
    }

    @Test
    public void testRenamePipeline() {
        TestUtils.createPipeline(this, NAME_PROJECT,false);

        getDriver().findElement(By.xpath("//a[@href='/job/" + NAME_PROJECT + "/confirm-rename']")).click();

        getDriver().findElement(By.xpath("//input[@name='newName']")).clear();

        WebElement renameName = getDriver().findElement(By.xpath("//input[@name='newName']"));
        renameName.sendKeys("Pipeline1");

        getDriver().findElement(By.name("Submit")).click();

        Assert.assertEquals("Pipeline " + "Pipeline1",
                getDriver().findElement(By.cssSelector(".job-index-headline.page-headline")).getText());
    }

    @Test
    public void testSetDescriptionPipeline() {
        TestUtils.createPipeline(this, NAME_PROJECT,false);

        getDriver().findElement(By.xpath("//a[@href='/job/" + NAME_PROJECT + "/configure']")).click();

        getDriver().findElement(By.name("description")).sendKeys("Pipeline text");

        getDriver().findElement(By.name("Submit")).click();

        Assert.assertEquals("Pipeline " + NAME_PROJECT,
                getDriver().findElement(By.cssSelector(".job-index-headline.page-headline")).getText());
    }

    @Test
    public void testDiscardOldBuildsPipeline() {
        TestUtils.createPipeline(this, NAME_PROJECT,false);

        getDriver().findElement(By.xpath("//a[@href='/job/" + NAME_PROJECT + "/configure']")).click();
        getDriver().findElement(By.xpath("//label[normalize-space()='Discard old builds']")).click();

        getDriver().findElement(By.name("_.daysToKeepStr")).sendKeys("2");
        getDriver().findElement(By.xpath("//input[@name='_.numToKeepStr']")).sendKeys("30");

        getDriver().findElement(By.name("Submit")).click();

        Assert.assertEquals("Pipeline " + NAME_PROJECT,
                getDriver().findElement(By.cssSelector(".job-index-headline.page-headline")).getText());
    }
}
