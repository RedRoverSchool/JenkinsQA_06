package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class LiliaMTest extends BaseTest {
    @Test
    public void myFirstTest() throws InterruptedException {
        getDriver().get("https://www.saucedemo.com/");
        WebElement userName = getDriver().findElement(By.xpath("//input[@id='user-name']"));
        userName.sendKeys("standard_user");
        WebElement password = getDriver().findElement(By.xpath("//input[@id='password']"));
        password.sendKeys("secret_sauce");
        WebElement loginButton = getDriver().findElement(By.xpath("//input[@id='login-button']"));
        loginButton.click();

    }
    @Test
    public void testSecond() throws InterruptedException {
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
