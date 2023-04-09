package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ComradesAdelanteNikolayMkvTest {

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

        String expectedResult = "https://www.google.com/maps/place/%D0%91%D0%B5%D1%80%D0%B4%D1%81%D0%BA,+%D0%9D%D0%BE%D0%B2%D0%BE%D1%81%D0%B8%D0%B1%D0%B8%D1%80%D1%81%D0%BA%D0%B0%D1%8F+%D0%BE%D0%B1%D0%BB./@54.7646848,83.0277085,12z/data=!3m1!4b1!4m6!3m5!1s0x42dfcf8ff0ea4713:0x977d7d9948dc1705!8m2!3d54.7750638!4d83.0800315!16zL20vMDc2MXZt?hl=RU";
        String actualResult = driver.getCurrentUrl();;

        Assert.assertEquals(actualResult,expectedResult);

        driver.quit();
    }
}
