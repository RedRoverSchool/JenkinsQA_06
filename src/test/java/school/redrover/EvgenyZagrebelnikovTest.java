package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EvgenyZagrebelnikovTest {

    @Test
    public void testOpenweatherGoogleSearch() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "/media/hp14/10ddf784-6745-4d03-a3ef-abc9d57c9d6f/hp/QA_06_Java/ChromeDriver/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--window-size=1920,1080", "--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);

        driver.get("https://www.google.com/");

        WebElement textBox = driver.findElement(By.name("q"));

        textBox.sendKeys("openweather");
        textBox.sendKeys(Keys.RETURN);

        Thread.sleep(2000);

        WebElement text = driver.findElement(By.xpath("//h3[text() = 'OpenWeather']"));

        Assert.assertEquals(text.getText(), "OpenWeather");

        driver.quit();
    }
}
