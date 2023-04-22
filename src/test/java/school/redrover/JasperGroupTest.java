package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;

public class JasperGroupTest extends BaseTest {

    @Test
    public void jenkinsFirstTest() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        WebElement searchField = getDriver().findElement(By.xpath("//input[@name = 'q']"));
        searchField.sendKeys("admin");
        searchField.sendKeys(Keys.RETURN);

        WebElement actualResult = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"main-panel\"]/div[2]")));
        Assert.assertEquals(actualResult.getText(), "Jenkins User ID: admin");
    }
}
