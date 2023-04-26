package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;

public class AutoTest extends BaseTest {
    public WebDriverWait webDriverWait10;
    public final WebDriverWait getWait10() {
        if (webDriverWait10 == null) {
            webDriverWait10 = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        }
        return webDriverWait10;
    }
    public final void verifyElementVisible(WebElement element) {

        getWait10().until(ExpectedConditions.visibilityOf(element));
    }

    @Test
    public void testCreateMultibranchPipeline() {
        getDriver().manage().window().maximize();
        WebElement createJob = getDriver().findElement(By.cssSelector(".content-block__link>span"));
        verifyElementVisible(createJob);
        createJob.click();
        WebElement jobName = getDriver().findElement(By.cssSelector(".jenkins-input"));
        jobName.sendKeys("Free");
        verifyElementVisible(jobName);
        WebElement freeStyle = getDriver().findElement(By.xpath("//span[text()='Freestyle project']"));
        freeStyle.click();
        WebElement clickOkButton = getDriver().findElement(By.id("ok-button"));
        verifyElementVisible(clickOkButton);
        clickOkButton.click();
        WebElement clickApplyButton = getDriver().findElement(By.xpath("//button[@class='jenkins-button apply-button']"));
        verifyElementVisible(clickApplyButton );
        clickApplyButton.click();
        verifyElementVisible(clickApplyButton );
        WebElement clickDashboard = getDriver().findElement(By.linkText("Dashboard"));
        clickDashboard.click();
        String getJobNames = getDriver().findElement(By.cssSelector(".jenkins-table__link.model-link.inside")).getText();

        Assert.assertTrue(getJobNames.contains("Free"));
    }
}
