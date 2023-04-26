package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class ComradesAdelanteGroupTest extends BaseTest {

    @Test
    public void CreateJobJenkinsTest() {
        WebElement createJobLink = getDriver().findElement(By.xpath("//span[contains(text(), 'Create a job')]"));
        createJobLink.click();

        WebElement nameJobField = getDriver().findElement(By.xpath("//*[@class = 'jenkins-input']"));
        nameJobField.sendKeys("First jenkins job");
        nameJobField.sendKeys(Keys.RETURN);

        WebElement selectTypeOfJob = getDriver().findElement(By.xpath("//*[@class = 'hudson_model_FreeStyleProject']"));
        selectTypeOfJob.click();

        WebElement buttonConfirmation = getDriver().findElement(By.xpath("//*[@class = 'jenkins-button jenkins-button--primary jenkins-buttons-row--equal-width']"));
        buttonConfirmation.click();

        WebElement generalInfoField = getDriver().findElement(By.xpath("//*[@class = 'jenkins-app-bar__content']/child::h2"));
        Assert.assertEquals(generalInfoField.getText(),"General");
    }
}