package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

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

    private final Map<Integer, WebDriverWait> waitMap = new HashMap<>();

    protected WebDriverWait getWait(int seconds) {
        return waitMap.computeIfAbsent(seconds, duration -> new WebDriverWait(getDriver(), Duration.ofSeconds(duration)));
    }

    @Test
    public void testCreatePipelineWithoutParameters() {
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
