package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class PetroMatsiuraTest {

    @Test
    public void googleStoreTest() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.google.com/");
        driver.findElement(By.linkText("Store")).click();
        driver.navigate().refresh();
        Thread.sleep(5000);
        Assert.assertEquals(driver.getTitle(), "Google Store for Google Made Devices & Accessories");
        driver.quit();
    }

    @Test
    public void googleAppsTest() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.google.com/");
        String [] result = {
                "Google Account",
                "Google",
                "Google Maps",
                "YouTube",
                "Android Apps on Google Play",
                "Google News",
                "Gmail",
                "Google Meet: Online Video Meetings and Calls | Google Workspace"
        };
        for (int i = 1; i <= result.length; i++) {
            driver.navigate().refresh();
            driver.findElement(By.cssSelector("a[aria-label='Google apps']")).click();
            driver.switchTo().frame("app").findElement(By.xpath("//div[1]/ul/li[" + i + "]")).click();
            Thread.sleep(5000);
            Assert.assertEquals(driver.getTitle(), "" + result[i - 1] + "");
            driver.navigate().back();
        }
        driver.quit();
    }
}
