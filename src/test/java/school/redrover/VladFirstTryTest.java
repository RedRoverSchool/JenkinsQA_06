package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class VladFirstTryTest extends BaseTest {

    @Test
    public void testVlad() {

        WebElement element = getDriver().findElement(By.xpath("//div[@class = 'empty-state-block']/h1"));

        Assert.assertEquals(element.getText(),"Welcome to Jenkins!");

    }
}
