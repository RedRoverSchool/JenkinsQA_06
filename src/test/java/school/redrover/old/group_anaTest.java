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


@Ignore
public class group_anaTest {

    @Test
    public  void  testTatyanaA() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://www.google.com/");

        WebElement textBox = driver.findElement(By.name("q"));

        textBox.sendKeys("selenium");
        textBox.sendKeys(Keys.RETURN);

        Thread.sleep(2000);

        WebElement text = driver.findElement(By.xpath("//h3[text() = 'Selenium']"));

        Assert.assertEquals(text.getText(), "Selenium");

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

        WebElement message = driver.findElement(By.id("message"));
        String value = message.getText();
        Assert.assertEquals("Received!", value);

        driver.quit();

    }


    String url = "https://openweathermap.org/";
    @Test
    public void testTitle( ) throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get(url);
        WebElement guide = driver.findElement(By.xpath("//a[@href='/guide']"));
        Thread.sleep(5000);
        guide.click();
        String title = driver.getTitle();
        Assert.assertEquals(title, "OpenWeatherMap API guide - OpenWeatherMap");
        driver.quit();
    }


}

