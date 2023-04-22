package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;

public class PipelineTest extends BaseTest {
    private static final String PIPELINE_NAME = "pipeline1";

    @Test
    public void TestCreatePipelineWithoutParameters() throws InterruptedException {
        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.id("name")).click();
        getDriver().findElement(By.id("name")).sendKeys(PIPELINE_NAME);
        getDriver().findElement(By.xpath("//span[text() = 'Pipeline']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        new WebDriverWait(getDriver(), Duration.ofMillis(2000)).until(ExpectedConditions.elementToBeClickable(By.
                xpath("//button[contains(@class,'jenkins-button jenkins-button--primary')]"))).click();
        getDriver().findElement(By.id("jenkins-name-icon")).click();

        String actualResult = getDriver().findElement(By.xpath("//tr[@id = 'job_" + PIPELINE_NAME + "']//a[@href='job/"+PIPELINE_NAME+"/']")).getText();

        Assert.assertEquals(actualResult, PIPELINE_NAME);

    }
}
