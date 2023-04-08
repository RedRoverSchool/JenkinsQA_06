package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GroupAndreyTest {
    @Test
    public void testAndreyD() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://openweathermap.org/");

        Thread.sleep(5000);

        WebElement button = driver.findElement(By.xpath("//*[@id=\"desktop-menu\"]/ul/li[1]/a"));
        button.click();

        assertEquals(driver.getTitle(), "OpenWeatherMap API guide - OpenWeatherMap");

        driver.quit();
    }
}
