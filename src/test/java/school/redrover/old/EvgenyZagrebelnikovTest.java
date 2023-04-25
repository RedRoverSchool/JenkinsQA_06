package school.redrover.old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
@Ignore
public class EvgenyZagrebelnikovTest {
    private WebDriver driver;

    @BeforeMethod
    private void beforeMethod() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        driver = new ChromeDriver(options);
    }
    @AfterMethod
    private void afterMethod() {
        driver.quit();
    }
    @Test
    public void testOpenWeatherGoogleSearch() throws InterruptedException {
        driver.get("https://www.google.com/");

        WebElement textBox = driver.findElement(By.name("q"));
        textBox.sendKeys("openweather\n");
        Thread.sleep(2000);

        WebElement text = driver.findElement(By.xpath("//h3[text() = 'OpenWeather']"));

        Assert.assertEquals(text.getText(), "OpenWeather");
    }
}
