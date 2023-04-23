package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import javax.swing.*;

public class CaramelSyrupForJavaTest extends BaseTest {
    @Test
    public void testAbramovaHotKeys() throws InterruptedException {

        WebElement body = getDriver().findElement(By.tagName("body"));
        Thread.sleep(3000);
        body.sendKeys(Keys.chord(Keys.CONTROL, "k"));
        WebElement searchBox = getDriver().findElement(By.xpath("//input[@role]"));
        WebElement currentElement = getDriver().switchTo().activeElement();
        Thread.sleep(3000);

        Assert.assertEquals(searchBox, currentElement);

    }









}
