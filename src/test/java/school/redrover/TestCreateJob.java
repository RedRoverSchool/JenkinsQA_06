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
}