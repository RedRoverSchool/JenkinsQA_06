package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class VadimKachanovichTest {
    @Test
    public void testFirst(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);
        WebDriverWait waiter = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.get("https://openweathermap.org/");
        waiter.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='OpenWeather']")));
        WebElement title = driver.findElement(By.xpath("//span[text()='OpenWeather']"));

        Assert.assertEquals(title.getText(),"OpenWeather");
        driver.quit();

    }
}
