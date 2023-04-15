package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import school.redrover.runner.BaseTest;

public class AndreyPomazTest extends BaseTest {

    @Test
    public void testFirstRedRover() throws InterruptedException {
        getGetdriver().get("https://redrover.school");
        Thread.sleep(2000);

        WebElement button = getGetdriver().findElement(By.linkText("JOIN US"));
        button.click();

        WebElement textBoxEmail = getGetdriver().findElement(By.name("email"));
        textBoxEmail.sendKeys("test@gmail");

        WebElement textBoxName = getGetdriver().findElement(By.name("name"));
        textBoxName.sendKeys("Test");

        WebElement checkBox = getGetdriver().findElement(By.className("t-checkbox__indicator"));
        Thread.sleep(2000);
        checkBox.click();

        WebElement buttonW = getGetdriver().findElement(By.className("t-submit"));
        buttonW.click();
        Thread.sleep(2000);

        WebElement error = getGetdriver().findElement(By.className("t-input-error"));
        Assert.assertEquals(error.getText(), "Please enter a valid email address");
    }

    @Test
    public void testSecond_Selenium() throws InterruptedException {
        getGetdriver().get("https://www.selenium.dev/selenium/web/web-form.html");

        String title = getGetdriver().getTitle();
        Assert.assertEquals("Web form", title);
        Thread.sleep(2000);

        WebElement textBox = getGetdriver().findElement(By.name("my-text"));
        WebElement submitButton = getGetdriver().findElement(By.cssSelector("button"));

        textBox.sendKeys("Selenium");
        submitButton.click();

        WebElement message = getGetdriver().findElement(By.id("message"));
        String value = message.getText();
        Assert.assertEquals("Received!", value);
    }

    @Test
    public void testJenkins() {
        getGetdriver().get("https://www.jenkins.io/");

        WebElement But = getGetdriver().findElement(By.linkText("Documentation"));
        But.click();

        WebElement But1 = getGetdriver().findElement(By.linkText("Installing Jenkins"));
        But1.click();

        WebElement But3 = getGetdriver().findElement(By.linkText("Windows"));
        But3.click();

        WebElement text = getGetdriver().findElement(By.className("hdlist1"));

        Assert.assertEquals(text.getText(),"Step 1: Setup wizard");
    }
}
