package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class GroupDreamTeam {
    @Test
    public void eightComponents() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        String title = driver.getTitle();
        assertEquals("Web form", title);

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement textBox = driver.findElement(By.name("my-text"));
        WebElement submitButton = driver.findElement(By.cssSelector("button"));

        textBox.sendKeys("Selenium");
        submitButton.click();

        WebElement message = driver.findElement(By.id("message"));
        String value = message.getText();
        assertEquals("Received!", value);

        driver.quit();
    }

    @Test
    public void testVerifyURL() {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        String url = "http://www.99-bottles-of-beer.net/";
        String expectedResult = "http://www.99-bottles-of-beer.net/";
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get(url);
        String actualResult = driver.getCurrentUrl();
        Assert.assertEquals(actualResult, expectedResult);
        driver.quit();
    }
    @Test
    public void testSecond() throws InterruptedException {

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

        WebElement password = driver.findElement(By.name("my-password"));
        Thread.sleep(2000);
        textBox.sendKeys("Selenium");

        password.sendKeys("WebSel1");
        Thread.sleep(2000);
        submitButton.click();
        Thread.sleep(2000);
        WebElement message = driver.findElement(By.id("message"));
        String value = message.getText();
        Assert.assertEquals("Received!", value);

        driver.quit();
    }
    @Test
    public void testSecondLilia() throws InterruptedException { 

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        String title = driver.getTitle();
        Assert.assertEquals("Web form", title);

        WebElement textBox = driver.findElement(By.name("my-text"));
        WebElement submitButton = driver.findElement(By.cssSelector("button"));
        WebElement password = driver.findElement(By.name("my-password"));
        WebElement textarea = driver.findElement(By.xpath("//textarea"));
        textBox.sendKeys("Selenium");
        password.sendKeys("WebSel1");
        textarea.sendKeys("I am working with Selenium");
        submitButton.click();

        WebElement message = driver.findElement(By.id("message"));
        String value = message.getText();
        Assert.assertEquals("Received!", value);
        driver.quit();
    }

}
