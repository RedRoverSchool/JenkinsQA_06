package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VladLiTest {

    private ChromeOptions chromeOptions;
    private WebDriver driver;

    @BeforeMethod
    public void beforeMethod(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        driver = new ChromeDriver(chromeOptions);
    }

    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }

    @Test
    public void YahooTest() throws InterruptedException {

        driver.get("https://www.yahoo.com/?guccounter=1");

        WebElement textBox = driver.findElement(By.name("p"));
        textBox.sendKeys("Amazon");
        textBox.sendKeys(Keys.RETURN);
        Thread.sleep(2000);

        WebElement text = driver.findElement(By.xpath("//span[text() = 'Amazon.com® Official Site - Fast Free Delivery with Prime']"));

        Assert.assertEquals(text.getText(), "Amazon.com® Official Site - Fast Free Delivery with Prime");
    }
}
