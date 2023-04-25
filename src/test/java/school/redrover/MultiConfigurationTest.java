package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class MultiConfigurationTest extends BaseTest {
   @Ignore
    @Test
    public void testCreateMultiConfig() {
       WebElement newItem = getDriver().findElement(By.xpath("//span[text()='New Item']"));

       newItem.getText();

       Assert.assertEquals(newItem, "New Item");
    }

    @Test
    public void testSimple() {
        WebElement welcomeElement = getDriver().findElement(By.xpath("//div[@class = 'empty-state-block']/h1"));

        Assert.assertEquals(welcomeElement.getText(), "Welcome to Jenkins!");
    }
}
