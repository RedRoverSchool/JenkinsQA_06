package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import java.time.Duration;
public class CaramelSyrupForJavaTest extends BaseTest {
    @Test
    public void testAbramovaHotKeys() {

        WebElement body = getDriver().findElement(By.tagName("body"));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        body.sendKeys(Keys.chord(Keys.CONTROL, "k"));
        WebElement searchBox = getDriver().findElement(By.xpath("//input[@role]"));
        WebElement currentElement = getDriver().switchTo().activeElement();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        Assert.assertEquals(searchBox, currentElement);
    }

}


