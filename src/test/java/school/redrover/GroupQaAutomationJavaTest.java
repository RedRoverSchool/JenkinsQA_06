package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class GroupQaAutomationJavaTest extends BaseTest {
    @Test
    public void testFirst () throws InterruptedException  {
        getDriver().get("https://www.selenium.dev/selenium/web/web-form.html");
        String title = getDriver().getTitle();

        Assert.assertEquals("Web form", title);

        WebElement textBox = getDriver().findElement(By.name("my-text"));
        WebElement submitButton = getDriver().findElement(By.cssSelector("button"));
        Thread.sleep(2000);

        textBox.sendKeys("Selenium");
        Thread.sleep(2000);
        submitButton.click();

        WebElement message = getDriver().findElement(By.id("message"));
        String value = message.getText();
        Thread.sleep(2000);

        Assert.assertEquals("Received!", value);
    }
}
