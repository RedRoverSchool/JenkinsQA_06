package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class QaAutomationJavaTest extends BaseTest {
    @Test
    public void testOne() throws InterruptedException {
        WebElement welcomeElement = getDriver().findElement(By.xpath("//div[@class = 'empty-state-block']/h1"));
        Thread.sleep(2000);

        Assert.assertEquals(welcomeElement.getText(), "Добро пожаловать в Jenkins!");
    }
}
