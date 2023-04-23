package school.redrover;

import org.openqa.selenium.By;
<<<<<<< HEAD
import org.openqa.selenium.support.ui.ExpectedConditions;
=======
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
>>>>>>> 72be5498 (New class and testCreatePipeline() added (#919))
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;

public class PipelineTest extends BaseTest {
<<<<<<< HEAD
    private static final String PIPELINE_NAME = "pipeline1";

    @Test
    public void testCreatePipelineWithoutParameters() throws InterruptedException {
        getDriver().findElement(By.linkText("New Item")).click();
        new WebDriverWait(getDriver(), Duration.ofMillis(1500)).until(ExpectedConditions.elementToBeClickable(By.id("name"))).sendKeys(PIPELINE_NAME);
        getDriver().findElement(By.xpath("//span[text() = 'Pipeline']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        new WebDriverWait(getDriver(), Duration.ofMillis(2000)).until(ExpectedConditions.elementToBeClickable(By.
                xpath("//button[contains(@class,'jenkins-button jenkins-button--primary')]"))).click();
        getDriver().findElement(By.id("jenkins-name-icon")).click();

        String actualResult = getDriver().findElement(By.xpath("//tr[@id = 'job_" + PIPELINE_NAME + "']//a[@href='job/"+PIPELINE_NAME+"/']")).getText();

        Assert.assertEquals(actualResult, PIPELINE_NAME);

=======

    public WebDriverWait webDriverWait10;

    public void scrollByElement(By by) throws InterruptedException {
        WebElement scroll =getDriver().findElement(by);
        new Actions(getDriver())
                .scrollToElement(scroll)
                .perform();
    }

    public final WebDriverWait getWait10() {
        if (webDriverWait10 == null) {
            webDriverWait10 = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        }
        return webDriverWait10;
    }
    @Test
    public void testCreatePipeline() throws InterruptedException {

        WebElement jobName = getDriver().findElement(By.cssSelector(".content-block__link>span"));
        jobName.click();

        WebElement field = getDriver().findElement(By.cssSelector("#name"));
        field.sendKeys("Pipe");

        WebElement pipeline = getDriver().findElement(By.xpath("//span[text()='Pipeline']"));
        pipeline.click();

        WebElement buttonOk = getDriver().findElement(By.cssSelector("#ok-button"));
        buttonOk.click();

        scrollByElement(By.cssSelector(".page-footer__links.page-footer__links--white.jenkins_ver>a"));
        WebElement save = getDriver().findElement(By.cssSelector(".jenkins-button.jenkins-button--primary"));
        save.click();

        WebElement pipelinePipe= getDriver().findElement(By.cssSelector("#main-panel>h1"));

        Assert.assertEquals(pipelinePipe.getText(), "Pipeline Pipe");
>>>>>>> 72be5498 (New class and testCreatePipeline() added (#919))
    }
}
