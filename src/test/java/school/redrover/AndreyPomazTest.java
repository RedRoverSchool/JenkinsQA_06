package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;
import school.redrover.runner.BaseTest;

public class AndreyPomazTest extends BaseTest {

    @Test
    public void testFirstRedRover() throws InterruptedException {
        getDriver().get("https://redrover.school");
        Thread.sleep(2000);

        WebElement button = getDriver().findElement(By.linkText("JOIN US"));
        button.click();

        WebElement textBoxEmail = getDriver().findElement(By.name("email"));
        textBoxEmail.sendKeys("test@gmail");

        WebElement textBoxName = getDriver().findElement(By.name("name"));
        textBoxName.sendKeys("Test");

        WebElement checkBox = getDriver().findElement(By.className("t-checkbox__indicator"));
        Thread.sleep(2000);
        checkBox.click();

        WebElement buttonW = getDriver().findElement(By.className("t-submit"));
        buttonW.click();
        Thread.sleep(2000);

        WebElement error = getDriver().findElement(By.className("t-input-error"));
        Assert.assertEquals(error.getText(), "Please enter a valid email address");
    }

    @Test
    public void testSecond_Selenium() throws InterruptedException {
        getDriver().get("https://www.selenium.dev/selenium/web/web-form.html");

        String title = getDriver().getTitle();
        Assert.assertEquals("Web form", title);
        Thread.sleep(2000);

        WebElement textBox = getDriver().findElement(By.name("my-text"));
        WebElement submitButton = getDriver().findElement(By.cssSelector("button"));

        textBox.sendKeys("Selenium");
        submitButton.click();

        WebElement message = getDriver().findElement(By.id("message"));
        String value = message.getText();
        Assert.assertEquals("Received!", value);
    }

    @Test
    public void testJenkins() {
        getDriver().get("https://www.jenkins.io/");

        WebElement But = getDriver().findElement(By.linkText("Documentation"));
        But.click();

        WebElement But1 = getDriver().findElement(By.linkText("Installing Jenkins"));
        But1.click();

        WebElement But3 = getDriver().findElement(By.linkText("Windows"));
        But3.click();

        WebElement text = getDriver().findElement(By.className("hdlist1"));

        Assert.assertEquals(text.getText(),"Step 1: Setup wizard");
    }
}
