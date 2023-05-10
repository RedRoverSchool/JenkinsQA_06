package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import java.time.Duration;

public class TestCreateJob extends BaseTest {

    @Test
    public void testCreateFreestyleProject() {
        WebElement createJobButton = getDriver().findElement(By.linkText("New Item"));
        createJobButton.click();

        new WebDriverWait(getDriver(), Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value = 'hudson.model.FreeStyleProject']//parent::label")));
        WebElement jobNameInput = getDriver().findElement(By.xpath("//input[@id = 'name']"));
        jobNameInput.sendKeys("TestJob");
        WebElement freestyleProjectSelect = getDriver().findElement(By.xpath("//input[@value = 'hudson.model.FreeStyleProject']//parent::label"));
        freestyleProjectSelect.click();
        WebElement createJobOkButton = getDriver().findElement(By.xpath("//button[@id = 'ok-button']"));
        createJobOkButton.click();

        WebElement jobDescriptionText = getDriver().findElement(By.xpath("//textarea[@name = 'description']"));
        jobDescriptionText.sendKeys("This is test job");
        WebElement jobSaveButton = getDriver().findElement(By.xpath("//button[@name = 'Submit']"));
        jobSaveButton.click();

        WebElement dashboardButton = getDriver().findElement(By.linkText("Dashboard"));
        dashboardButton.click();

        WebElement jobListTable = getDriver().findElement(By.xpath("//table[@id = 'projectstatus']"));
        WebElement jobNameLink = jobListTable.findElement(By.linkText("TestJob"));

        boolean actual = jobNameLink.isDisplayed();

        Assert.assertTrue(actual);
    }
    @Test
    public void testCreateFreestyleProjectFirst() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

        By createJob = By.xpath("//div//a[@href='newJob']");
        WebElement createJobButton = getDriver().findElement(createJob);
        wait.until(ExpectedConditions.visibilityOfElementLocated(createJob));
        createJobButton.click();

        By jenkinsInput = By.xpath("//div//input[@class='jenkins-input']");
        WebElement jenkinsInputButton = getDriver().findElement(jenkinsInput);
        wait.until(ExpectedConditions.visibilityOfElementLocated(jenkinsInput));
        jenkinsInputButton.sendKeys("First_Jenkins_Job");
        WebElement freeConfigurationButton = getDriver().findElement(By.cssSelector("[class*='hudson_model']"));
        freeConfigurationButton.click();

        WebElement okButton = getDriver().findElement(By.id("ok-button"));
        okButton.click();

        By freeTextIn = By.cssSelector("textarea[name='description']");
        WebElement freeTextInput = getDriver().findElement(freeTextIn);
        wait.until(ExpectedConditions.visibilityOfElementLocated(freeTextIn));
        freeTextInput.sendKeys("First_description is here!");

        WebElement submitButton = getDriver().findElement(By.cssSelector("button[name='Submit']"));
        submitButton.click();

        getDriver().get("http://localhost:8080/");
        WebElement actualResult = getDriver().findElement(By.xpath("//div//td/a/span"));
        String expectedResult = "First_Jenkins_Job";

        Assert.assertEquals(actualResult.getText(),expectedResult);
    }
    @Test
    public void testCreateMultibranchPipeline() {
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