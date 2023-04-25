package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class QaAutomationJavaTest extends BaseTest {
    @Test
    public void testVerifyWordIconJenkins() {
        WebElement logo = getDriver().findElement(By.id("jenkins-name-icon"));

        Assert.assertTrue(logo.isDisplayed());
    }
    @Test
    public void testvalidateJenkinsLogin() {
        WebElement welcomeElement = getDriver().findElement(By.xpath("//div[@class = 'empty-state-block']/h1"));

        Assert.assertEquals(welcomeElement.getText(), "Welcome to Jenkins!");
    }
    @Test
    public void testCreatJob() throws InterruptedException {
        Thread.sleep(1000);
        WebElement createJob = getDriver().findElement(By.xpath("//div[@id='tasks']//a[contains(@href, 'newJob')]"));
        createJob.click();

        WebElement newProjectName = getDriver().findElement(By.xpath("//input[@id='name']"));
        newProjectName.sendKeys("First");

        WebElement selectFolder = getDriver().findElement(By.xpath("//span[text()='Organization Folder']/../.."));
        selectFolder.click();

        WebElement buttonOK = getDriver().findElement(By.cssSelector("button.jenkins-button"));
        buttonOK.click();

        WebElement buttonHome = getDriver().findElement(By.id("jenkins-home-link"));
        buttonHome.click();

        WebElement fieldNameInTable = getDriver().findElement(By.xpath("//a[@class='jenkins-table__link model-link inside']//span"));

        Assert.assertEquals(fieldNameInTable.getText(), "First");
    }
}
