package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;

public class MultiConfigurationTest extends BaseTest {
    private WebDriverWait getWait(int seconds) {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(seconds));
    }
    @Test
    public void testCreateMultiConfig() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys("test");
        getDriver().findElement(By.xpath("//span[text()='Multi-configuration project']")).click();
        getDriver().findElement(By.cssSelector("#ok-button")).click();
        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys("test");
        getDriver().findElement(By.name("Submit")).click();

        Assert.assertTrue(getDriver().findElement(By.xpath("//div[@id='description']")).isDisplayed());
    }

    @Test
    public void testCheckExceptionToMultiConfigurationPage() throws InterruptedException {
        getDriver().findElement(By.linkText("New Item")).click();
        WebElement element = getWait(15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Multi-configuration project']")));
        element.click();
        String exceptionText = getDriver().findElement(By.xpath("//div[text() ='» This field cannot be empty, please enter a valid name']")).getText();

        Assert.assertEquals(exceptionText,"» This field cannot be empty, please enter a valid name");
    }

}
