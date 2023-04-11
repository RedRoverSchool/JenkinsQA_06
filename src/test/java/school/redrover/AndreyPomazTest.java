package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
public class AndreyPomazTest {
    @Test
    public void testFirst_RedRover() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://redrover.school");
        Thread.sleep(2000);

        WebElement button = driver.findElement(By.linkText("JOIN US"));
        button.click();

        WebElement textBoxEmail = driver.findElement(By.name("email"));
        textBoxEmail.sendKeys("test@gmail");

        WebElement textBoxName = driver.findElement(By.name("name"));
        textBoxName.sendKeys("Test");

        WebElement checkBox = driver.findElement(By.className("t-checkbox__indicator"));
        Thread.sleep(2000);
        checkBox.click();

        WebElement buttonW = driver.findElement(By.className("t-submit"));
        buttonW.click();
        Thread.sleep(2000);

        WebElement error = driver.findElement(By.className("t-input-error"));
        Assert.assertEquals(error.getText(), "Please enter a valid email address");
        driver.quit();
    }
    @Test
    public void testSecond_Selenium() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        String title = driver.getTitle();
        Assert.assertEquals("Web form", title);
        Thread.sleep(2000);

        WebElement textBox = driver.findElement(By.name("my-text"));
        WebElement submitButton = driver.findElement(By.cssSelector("button"));

        textBox.sendKeys("Selenium");
        submitButton.click();

        WebElement message = driver.findElement(By.id("message"));
        String value = message.getText();
        Assert.assertEquals("Received!", value);
        driver.quit();
    }

    @Test
    public void testJenkins() {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.jenkins.io/");

        WebElement But = driver.findElement(By.linkText("Documentation"));
        But.click();

        WebElement But1 = driver.findElement(By.linkText("Installing Jenkins"));
        But1.click();

        WebElement But3 = driver.findElement(By.linkText("Windows"));
        But3.click();

        WebElement text = driver.findElement(By.className("hdlist1"));

        Assert.assertEquals(text.getText(),"Step 1: Setup wizard");
        driver.quit();
    }
}
