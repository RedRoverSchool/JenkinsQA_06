package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;

public class PipelineTest extends BaseTest {

    private static final String PIPELINE_NAME = "pipeline1";

    private final By newItem = By.linkText("New Item");
    private final By name = By.id("name");
    private final By pipeline = By.xpath("//span[text() = 'Pipeline']");
    private final By okButton = By.id("ok-button");
    private final By saveButton = By.xpath("//button[contains(@class,'jenkins-button jenkins-button--primary')]");
    private final By jenkinsIconHeader = By.id("jenkins-name-icon");
    private final By textAreaDescription = By.xpath("//textarea[@name='description']");
    private final By pipelineDescription = By.xpath("//div[@id = 'description']/div[1]");

    private WebDriverWait getWait(int seconds) {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(seconds));
    }

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

    @Ignore
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
    }

    @Test
    public void testCreatedPipelineIsDisplayedOnDashboard() {
        getDriver().findElement(newItem).click();
        getWait(1).until(ExpectedConditions.elementToBeClickable(name)).sendKeys(PIPELINE_NAME);
        getDriver().findElement(pipeline).click();
        getDriver().findElement(okButton).click();
        getWait(2).until(ExpectedConditions.elementToBeClickable(saveButton)).click();
        getDriver().findElement(jenkinsIconHeader).click();

        String actualResult = getDriver().findElement(By.xpath("//tr[@id = 'job_" + PIPELINE_NAME + "']//a[@href='job/" + PIPELINE_NAME + "/']")).getText();

        Assert.assertEquals(actualResult, PIPELINE_NAME);
    }

    @Test
    public void testCreatePipelineWithDescription() {
        String pipelineDescriptionText = "description text";
        getDriver().findElement(newItem).click();
        getWait(1).until(ExpectedConditions.elementToBeClickable(name)).sendKeys(PIPELINE_NAME);
        getDriver().findElement(pipeline).click();
        getDriver().findElement(okButton).click();
        getWait(2).until(ExpectedConditions.elementToBeClickable(textAreaDescription)).click();
        getDriver().findElement(textAreaDescription).sendKeys(pipelineDescriptionText);
        getDriver().findElement(saveButton).click();

        Assert.assertEquals(getDriver().findElement(pipelineDescription).getText(), pipelineDescriptionText);
    }
}
