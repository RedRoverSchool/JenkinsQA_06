package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Group35MainTest {

    @Test
    public void simpleTest_01() {
        Assert.assertEquals(2, 2);
    }
    @Test
    public void aLugovskayaTest(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.life-moon.pp.ru/");
        WebElement element = driver.findElement
                (By.cssSelector("body > section > section > article > div:nth-child(2) > ul > li:nth-child(3) > a"));
        element.click();
        WebElement header = driver.findElement
                (By.cssSelector("body > section > section > article > div:nth-child(2) > h1"));
        String value = header.getText();
        Assert.assertEquals (value, "Ваш лунный день рождения. Рассчёт лунного календаря на любой день.");
        driver.quit();
    }
}
