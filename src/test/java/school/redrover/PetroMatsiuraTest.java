package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PetroMatsiuraTest {

    @Test
    public void storeTest() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.google.com/");
        driver.findElement(By.linkText("Store")).click();
        Thread.sleep(5000);
        Assert.assertEquals(driver.getTitle(), "Google Store for Google Made Devices & Accessories");
        driver.quit();
    }
}
