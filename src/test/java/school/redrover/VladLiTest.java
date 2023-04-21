package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VladLiTest {
    @Test
    // Commit and push assignment
    public void YahooTest() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");


        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://www.yahoo.com/?guccounter=1");

        WebElement textBox = driver.findElement(By.name("p"));

        textBox.sendKeys("Amazon");
        textBox.sendKeys(Keys.RETURN);

        Thread.sleep(2000);

        WebElement text = driver.findElement(By.xpath("//span[text() = 'Amazon.com® Official Site - Fast Free Delivery with Prime']"));

        Assert.assertEquals(text.getText(), "Amazon.com® Official Site - Fast Free Delivery with Prime");


        driver.quit();
    }

}
