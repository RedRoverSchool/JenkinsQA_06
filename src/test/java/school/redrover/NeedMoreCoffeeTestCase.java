package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import static org.testng.Assert.assertEquals;

public class NeedMoreCoffeeTestCase extends BaseTest {

    @Test
    public void testBozhok() throws InterruptedException {

        getDriver().get("https://www.selenium.dev/selenium/web/web-form.html");

        String title = getDriver().getTitle();
        assertEquals("Web form", title);

        Thread.sleep(2000);

        WebElement textBox = getDriver().findElement(By.name("my-text"));
        WebElement submitButton = getDriver().findElement(By.cssSelector("button"));

        textBox.sendKeys("Selenium");
        submitButton.click();

        WebElement message = getDriver().findElement(By.id("message"));
        String value = message.getText();
        assertEquals("Received!", value);

    }

    @Test
    public void testElBaron() throws InterruptedException {

        getDriver().get("https://www.ivi.tv");

        Thread.sleep(3000);

        WebElement ButtonMovie = getDriver().findElement(By.cssSelector("#headerTop > div > div > div > " +
                "div.headerTop__content > div.headerTop__wideArea > " +
                "div.headerTop__additionalButton.headerTop__additionalButton_isAbTest > div > div"));

        ButtonMovie.click();

        Thread.sleep(3000);
        WebElement text = getDriver().findElement(By.cssSelector("#root > div > div.subscribeLanding__wrapper-inner > " +
                "div.subscribeLanding__content > h1"));

        Assert.assertEquals(text.getText(), "Подписка Иви");

    }
}


