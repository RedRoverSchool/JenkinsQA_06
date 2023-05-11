import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class DeleteTheOnlyCreatedPipelineTest extends BaseTest {
    private void createPipeline() {
        getDriver().findElement(By.xpath("//a[@href = '/view/all/newJob']")).click();
        getWait5().until(ExpectedConditions.presenceOfElementLocated(By.id("name")));
        getDriver().findElement(By.className("jenkins-input")).sendKeys("TestProject");
        getDriver().findElement(By.xpath("//li[contains(@class, 'WorkflowJob')]")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.name("Submit")).click();

    }
    @Test
    public void testDeleteTheOnlyCreatedPipeline() {
        createPipeline();

        getDriver().findElement(By.id("jenkins-name-icon")).click();

        WebElement pipeline = getDriver().findElement(By.xpath("//span[text() = 'TestProject']"));

        new Actions(getDriver()).moveToElement(pipeline).perform();
        getDriver().findElement(By.xpath("//a[@href = 'job/TestProject/']/button")).click();

        By.xpath("//span[contains(text(), 'Delete Pipeline')]").findElement(getDriver()).click();
        getDriver().switchTo().alert().accept();

        Assert.assertTrue(getWait2().until(ExpectedConditions.invisibilityOf(pipeline)));
    }
}
