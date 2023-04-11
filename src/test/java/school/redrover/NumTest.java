package school.redrover;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class NumTest {

    @Test
    public  void wikiTest() /*throws InterruptedException */{
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
        driver.get("https://wikipedia.org");
        //Thread.sleep(2000);
        Assert.assertEquals(driver.getTitle(),"Wikipedia");
        driver.quit();
    }
}
