package school.redrover;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class TestCreateDuplicatePipeline extends BaseTest {

    @Test
    public void testCreatePipelineJobWithDuplicateName() {

        final String PROJECT_NAME = "Test1";

        WebElement newItem = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
        newItem.click();

        WebElement inputName = getWait5().until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.name("name"))));
        inputName.sendKeys(PROJECT_NAME);

        WebElement pipeline = getDriver().findElement(By.xpath("//span[contains(text(), 'Pipeline')]"));
        pipeline.click();

        WebElement okButton = getDriver().findElement(By.id("ok-button"));
        okButton.click();

        WebElement submit = getWait5().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.name("Submit"))));
        submit.click();

        WebElement jenkinsHeadIcon = getDriver().findElement(By.id("jenkins-head-icon"));
        jenkinsHeadIcon.click();

        WebElement newItem2 = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
        newItem2.click();

        WebElement inputName2 = getDriver().findElement(By.id("name"));
        inputName2.sendKeys(PROJECT_NAME);

        WebElement pipeline2 = getDriver().findElement(By.xpath("//span[contains(text(), 'Pipeline')]"));
        pipeline2.click();

        WebElement errorMessage = getDriver().findElement(By.xpath("//div[@class='input-validation-message']"));

        Assert.assertEquals(errorMessage.getText(), "» A job already exists with the name ‘Test1’");
    }
}
