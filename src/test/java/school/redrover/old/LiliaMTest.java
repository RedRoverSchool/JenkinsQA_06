package school.redrover.old;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
@Ignore
public class LiliaMTest extends BaseTest {
    @Test
    public void myFirstTest() {
        getDriver().get("https://www.google.com/");
        WebElement textBox = getDriver().findElement(By.name("q"));
        textBox.sendKeys("selenium");
        textBox.sendKeys(Keys.RETURN);
        WebElement text = getDriver().findElement(By.xpath("//h3[text() = 'Selenium']"));
        Assert.assertEquals(text.getText(), "Selenium");
    }
    @Test
    public void testSecond() {
        getDriver().get("https://www.selenium.dev/selenium/web/web-form.html");
        String title = getDriver().getTitle();
        Assert.assertEquals("Web form", title);

        WebElement textBox = getDriver().findElement(By.name("my-text"));
        WebElement submitButton = getDriver().findElement(By.cssSelector("button"));
        WebElement password = getDriver().findElement(By.name("my-password"));
        textBox.sendKeys("Selenium");
        password.sendKeys("WebSel1");
        submitButton.click();
        WebElement message = getDriver().findElement(By.id("message"));
        String value = message.getText();
        Assert.assertEquals("Received!", value);
    }
}
