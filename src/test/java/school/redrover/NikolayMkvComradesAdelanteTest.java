package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class NikolayMkvComradesAdelanteTest {

    @Test
    public void testMaps() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://ya.ru/");

        WebElement textBox = driver.findElement(By.id("text"));

        textBox.click();

        WebElement iconMaps = driver.findElement(By.xpath("//a[@data-id='maps']/div"));

        iconMaps.click();

        ArrayList<String> words = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(words.get(1));
        WebElement search = driver.findElement(By.xpath("//input"));

        search.click();
        search.sendKeys("Бердск");
        search.sendKeys(Keys.RETURN);

        Thread.sleep(3000);
        WebElement text = driver.findElement(By.xpath("//div[@class='sidebar-container']//h1"));

        String actualResult = text.getText();
        String expectedResult = "Бердск";
        Assert.assertEquals(actualResult,expectedResult);

        driver.quit();
    }
}
