package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GroupILoveBugsTest {
    @Test
    public void ADFirstTest() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://guitar.com/");

        Thread.sleep(2000);

        WebElement latestNews = driver.findElement(By.xpath("//h2[@class='posts-block-title']"));

        Assert.assertEquals(latestNews.getText(), "LATEST NEWS");

        driver.quit();
    }
}
