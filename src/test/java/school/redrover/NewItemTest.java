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

public class NewItemTest extends BaseTest {

    @Test
    public void testNewItemHeader() {
        getDriver().findElement(By.linkText("New Item")).click();

        WebElement h3Header = new WebDriverWait(getDriver(), Duration.ofMillis(3000))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@class = 'h3']")));
        String actualResult = h3Header.getText();

        Assert.assertEquals(actualResult, "Enter an item name");
    }
}
