package school.redrover;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class SeleniumFirstScriptTest extends BaseTest {
    @Test
    public void testFirst() throws InterruptedException {
        getGetdriver().get("https://www.selenium.dev/selenium/web/web-form.html");

        String title = getGetdriver().getTitle();
        Assert.assertEquals("Web form", title);

        WebElement textBox = getGetdriver().findElement(By.name("my-text"));
        WebElement submitButton = getGetdriver().findElement(By.cssSelector("button"));

        textBox.sendKeys("Selenium");
        submitButton.click();

        WebElement message = getGetdriver().findElement(By.id("message"));
        String value = message.getText();
        Assert.assertEquals("Received!", value);
    }
}

