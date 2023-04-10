package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ComradesAdelanteTest {
    @Test
    public void testMap() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://www.google.com//");

        WebElement textBox = driver.findElement(By.className("gLFyf"));
        textBox.sendKeys("гугл карты");
        textBox.sendKeys(Keys.RETURN);

        WebElement search = driver.findElement(By.className("qLRx3b"));

        search.click();

        Thread.sleep(2000);
        WebElement searchBox = driver.findElement(
                By.xpath("//input[@id='searchboxinput']"));
        searchBox.sendKeys("Бердск");
        searchBox.sendKeys(Keys.RETURN);
        Thread.sleep(5000);

        WebElement text =  driver.findElement(By.xpath("//*[@id='QA0Szd']/div/div/div[1]/div[2]/div/div[1]/div/div/div[2]/div/div[1]/div[1]/h1"));

        String expectedResult = "Бердск";
        String actualResult = text.getText();

        Assert.assertEquals(actualResult,expectedResult);

        driver.quit();
    }
}
