package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class AnnaWTest {
    @Test
    public void firstTest(){
        String test = "First Test";
        Assert.assertEquals("First Test", test);
    }
    @Test
    public void findJavaTutorial() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.w3schools.com/");

        Thread.sleep(2000);

        WebElement textBox = driver.findElement(By.xpath("//*[@id=\"search2\"]"));
        WebElement submitButton = driver.findElement(By.cssSelector("button"));

        textBox.sendKeys("Java Tutorial");
        submitButton.click();

        driver.quit();
    }
}
