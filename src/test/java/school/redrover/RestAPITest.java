package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;

public class RestAPITest extends BaseTest{
    @Test
    public void restApiTest() {
        String apiLink = "REST API";

        WebElement restApiLink = getDriver().findElement(By.xpath("//div[@class='page-footer__flex-row']/div[2]/a"));
        restApiLink.click();

        WebElement restApiText = getDriver().findElement(By.xpath("//h1[1]"));

        Assert.assertEquals(restApiText.getText(), apiLink);
    }
}
