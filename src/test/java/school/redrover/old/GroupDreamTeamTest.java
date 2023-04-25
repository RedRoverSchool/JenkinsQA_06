package school.redrover.old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;

import static org.testng.Assert.assertEquals;
@Ignore
public class GroupDreamTeamTest extends BaseTest {

    @Test
    public void eightComponents() {
        getDriver().get("https://www.selenium.dev/selenium/web/web-form.html");

        String title = getDriver().getTitle();
        assertEquals("Web form", title);

        getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement textBox = getDriver().findElement(By.xpath("//*[@name = 'my-text']"));
        WebElement submitButton = getDriver().findElement(By.cssSelector("button"));

        textBox.sendKeys("Selenium");
        submitButton.click();

        WebElement message = getDriver().findElement(By.id("message"));
        String value = message.getText();
        assertEquals("Received!", value);
    }

    @Test
    public void testVerifyURL() {
        String url = "http://www.99-bottles-of-beer.net/";
        String expectedResult = "http://www.99-bottles-of-beer.net/";

        getDriver().get(url);

        String actualResult = getDriver().getCurrentUrl();
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testRafis() throws InterruptedException {
        getDriver().get("https://www.selenium.dev/selenium/web/web-form.html");

        String title = getDriver().getTitle();
        Assert.assertEquals("Web form", title);

        Thread.sleep(1000);

        WebElement textBox = getDriver().findElement(By.name("my-text"));
        WebElement submitButton = getDriver().findElement(By.cssSelector("button"));

        textBox.sendKeys("Selenium");

        WebElement password = getDriver().findElement(By.name("my-password"));
        Thread.sleep(1000);
        textBox.sendKeys("Selenium");

        password.sendKeys("WebSel1");
        Thread.sleep(1000);
        submitButton.click();
        Thread.sleep(1000);
        WebElement message = getDriver().findElement(By.id("message"));
        String value = message.getText();
        Assert.assertEquals("Received!", value);
    }

    @Test
    public void testSecondLilia() throws InterruptedException {
        getDriver().get("https://www.selenium.dev/selenium/web/web-form.html");
        String title = getDriver().getTitle();
        Assert.assertEquals("Web form", title);

        WebElement textBox = getDriver().findElement(By.name("my-text"));
        WebElement submitButton = getDriver().findElement(By.cssSelector("button"));
        WebElement password = getDriver().findElement(By.name("my-password"));
        WebElement textarea = getDriver().findElement(By.xpath("//textarea"));
        textBox.sendKeys("Selenium");
        password.sendKeys("WebSel1");
        textarea.sendKeys("I am working with Selenium");
        submitButton.click();

        WebElement message = getDriver().findElement(By.id("message"));
        String value = message.getText();
        Assert.assertEquals("Received!", value);
    }

}
