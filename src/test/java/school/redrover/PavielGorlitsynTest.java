package school.redrover;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class PavielGorlitsynTest {

    @Ignore
    @Test
    public void firstTest() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.google.com/");
        Thread.sleep(2000);
        WebElement textBox= driver.findElement(By.xpath("//*[@name= 'q']"));
        Thread.sleep(2000);
        textBox.sendKeys("Youtube");
        Thread.sleep(2000);
        WebElement search= driver.findElement(By.xpath("//*[@name= 'btnK']"));
        Thread.sleep(2000);
        search.click();
        WebElement header = driver.findElement(By.xpath("//h3[@class=\"LC20lb MBeuO DKV0Md\"]"));
        Thread.sleep(6000);
        header.click();
        String expectedURL= "https://www.youtube.com/";
        Thread.sleep(3000);
        String actualURL= driver.getCurrentUrl();
        Thread.sleep(2000);
        Assert.assertEquals(actualURL,expectedURL);
        driver.quit();


    }
}

