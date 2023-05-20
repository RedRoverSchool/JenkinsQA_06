package school.redrover;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PipelineProject2Test extends BaseTest {

    private static final String PROJECT_NAME = RandomStringUtils.randomAlphanumeric(7);
    private static final By INPUT_NAME = By.name("name");
    private static final By NEW_NAME = By.name("newName");
    private static final By HOME_PAGE = By.xpath("//h1[@class= 'job-index-headline page-headline']");

    @Test
    public void testCreatePipelineProject() {

        TestUtils.createPipeline(this, PROJECT_NAME, false);
        getDriver().findElement(HOME_PAGE).getText();
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
    @Test
    public void testSortingPipelineProjectAplhabetically() {

        TestUtils.createPipeline(this, "SProject", false);
        WebElement projectName1 = getDriver().findElement(HOME_PAGE);
        String p1 = projectName1.getText().substring(9);
        getDriver().findElement(By.id("jenkins-head-icon")).click();

        TestUtils.createPipeline(this, "AProject", false);
        WebElement projectName2 = getDriver().findElement(HOME_PAGE);
        String p2 = projectName2.getText().substring(9);
        getDriver().findElement(By.id("jenkins-head-icon")).click();

        TestUtils.createPipeline(this, "UProject", false);
        WebElement projectName3 = getDriver().findElement(HOME_PAGE);
        String p3 = projectName3.getText().substring(9);

        List<String> expectedNames = new ArrayList<>();
        expectedNames.add(p1);
        expectedNames.add(p2);
        expectedNames.add(p3);

        ArrayList<String> sortedExpectedProjectNames = new ArrayList<>();
        sortedExpectedProjectNames.addAll(expectedNames);
        Collections.sort(sortedExpectedProjectNames);
        System.out.println(sortedExpectedProjectNames);

        getDriver().findElement(By.id("jenkins-head-icon")).click();
        getDriver().findElement(By.xpath("//th[@initialsortdir='down']")).click();

        WebElement findProjectName1 = getDriver().findElement(By.xpath("//body[1]/div[3]/div[2]/div[2]/table[1]/tbody[1]/tr[1]"));
        String actualProjectName1 = findProjectName1.getText().substring(0, 8);
        WebElement findProjectName2 = getDriver().findElement(By.xpath("//body[1]/div[3]/div[2]/div[2]/table[1]/tbody[1]/tr[2]"));
        String actualProjectName2 = findProjectName2.getText().substring(0, 8);
        WebElement findProjectName3 = getDriver().findElement(By.xpath("//body[1]/div[3]/div[2]/div[2]/table[1]/tbody[1]/tr[3]"));
        String actualProjectName3 = findProjectName3.getText().substring(0, 8);

        List<String> actualProjectNames = new ArrayList<>();
        actualProjectNames.add(actualProjectName1);
        actualProjectNames.add(actualProjectName2);
        actualProjectNames.add(actualProjectName3);
        System.out.println(actualProjectNames);

        Assert.assertEquals(actualProjectNames, sortedExpectedProjectNames);
    }
}



