package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class FreestyleProject9Test extends BaseTest {
    public static final By NEW_ITEM_BUTTON = By.xpath("//a[@href='/view/all/newJob']");
    public static final By ITEM_NAME_FIELD = By.xpath("//input[@name='name']");
    public static final By FREESTYLE_PROJECT_BUTTON = By.xpath("//li[@class='hudson_model_FreeStyleProject']");
    public static final By OK_BUTTON = By.xpath("//button[@id='ok-button']");
    public static final By SAVE_BUTTON = By.xpath("//button[@name='Submit']");
    public static final By JENKINS_IMAGE_HEADER = By.xpath("//img[@alt='Jenkins']");

    @Test
    public void testDisplayFreestyleProjectOnDashboard() {
        getDriver().findElement(NEW_ITEM_BUTTON).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(ITEM_NAME_FIELD)).sendKeys("testFreestyleProject");
        getDriver().findElement(FREESTYLE_PROJECT_BUTTON).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(OK_BUTTON)).click();

        getWait2().until(ExpectedConditions.elementToBeClickable(SAVE_BUTTON)).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(JENKINS_IMAGE_HEADER)).click();
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[@id='job_testFreestyleProject']")));
        WebElement createdFreestyleProject = getDriver().findElement(By.xpath("//a[@href='job/testFreestyleProject/']"));

        Assert.assertEquals(createdFreestyleProject.getText(), "testFreestyleProject");
    }
}
