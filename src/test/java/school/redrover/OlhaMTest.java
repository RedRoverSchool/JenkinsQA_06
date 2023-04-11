package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class OlhaMTest {
    @Test
    public void OlhaMykhailovaTest() throws InterruptedException{
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--windows-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.youtube.com/");
        Thread.sleep(3000);

        String title = driver.getTitle();

        WebElement button = driver.findElement(By.id("start"));

        Thread.sleep(3000);

        WebElement button1 = driver.findElement(By.id("guide-icon"));
        button.click();

        Thread.sleep(3000);
        driver.quit();
    }
}

