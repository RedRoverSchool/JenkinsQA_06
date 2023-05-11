package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;

public class CreatePipelineProject8Test extends BaseTest {
    public static String name = "NewName";
    public static final By PIPELINE_FINAL_NAME = By.xpath("//h1[contains(text(),'Pipeline " + name + "')]");

    @Test
    public void testNewItemSubmit() throws InterruptedException {
        WebElement newItemBtn = getDriver().findElement(By.cssSelector("a[href='/view/all/newJob']"));
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='task-link-text' and .='New Item']")));
        newItemBtn.click();

        getDriver().findElement(By.cssSelector("#name")).sendKeys(name);
        getDriver().findElement(By.cssSelector(".org_jenkinsci_plugins_workflow_job_WorkflowJob")).click();
        getDriver().findElement(By.cssSelector("#ok-button")).click();

        getDriver().findElement(By.xpath("//button[contains(text(),'Save')]")).click();

        Assert.assertEquals(getDriver().findElement(PIPELINE_FINAL_NAME).getText(), "Pipeline " + name);
    }
}
