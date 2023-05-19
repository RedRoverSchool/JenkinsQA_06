package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

public class FreestyleProject01Test extends BaseTest {
    private static final String FREESTYLE_NAME = "Freestyle";

    @Test
    public void testCreateFreestyleProject(){
        getDriver().findElement(By.xpath("//a[@href = '/view/all/newJob']")).click();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.name("name"))).sendKeys(FREESTYLE_NAME);
        getDriver().findElement(By.xpath("//li[@class = 'hudson_model_FreeStyleProject']")).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.id("ok-button"))).click();

        getWait2().until(ExpectedConditions.elementToBeClickable(By.name("Submit"))).click();

        Assert.assertEquals(getDriver().findElement(By.tagName("h1")).getText(), "Project " + FREESTYLE_NAME);
    }

    @Test
    public void testNameAndDescAreDisplayed(){
        String desc = "Description";
        getDriver().findElement(By.xpath("//a[@href = '/view/all/newJob']")).click();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.name("name"))).sendKeys(FREESTYLE_NAME);
        getDriver().findElement(By.xpath("//li[@class = 'hudson_model_FreeStyleProject']")).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.id("ok-button"))).click();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.name("description"))).sendKeys(desc);
        getDriver().findElement(By.name("Submit")).click();

        Assert.assertEquals(getDriver().findElement(By.tagName("h1")).getText(), "Project " + FREESTYLE_NAME);
        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id = 'description']/div")).getText(), desc);
    }

    @Test
    public void testDisableFreestyleProject(){
        String message = "This project is currently disabled";
        TestUtils.createFreestyleProject(this, FREESTYLE_NAME, false);

        getWait2().until(ExpectedConditions.elementToBeClickable(By.name("Submit"))).click();
        String projectIsDisableMessage = getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.id("enable-project"))).getText();

        projectIsDisableMessage = projectIsDisableMessage.substring(0, message.length());

        Assert.assertEquals(projectIsDisableMessage, message);

        getDriver().findElement(By.xpath("//a[text() = 'Dashboard']")).click();

        String attrOfProjectElem = getWait2().until(ExpectedConditions.visibilityOfElementLocated(
                            By.id("job_" + FREESTYLE_NAME))).getAttribute("class");

        Assert.assertEquals(attrOfProjectElem, "disabledJob job-status-disabled");
    }

    @Test
    public void testEnableFreestyleProject(){
        TestUtils.createFreestyleProject(this, FREESTYLE_NAME, false);

        getWait2().until(ExpectedConditions.elementToBeClickable(By.name("Submit"))).click();
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.name("Submit"))).click();

        getDriver().findElement(By.xpath("//a[text() = 'Dashboard']")).click();

        String attrOfProjectElem = getWait2().until(ExpectedConditions.visibilityOfElementLocated(
                By.id("job_" + FREESTYLE_NAME))).getAttribute("class");

        Assert.assertEquals(attrOfProjectElem, " job-status-nobuilt");
    }
}
