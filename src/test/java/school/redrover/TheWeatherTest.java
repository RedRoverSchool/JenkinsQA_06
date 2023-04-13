package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TheWeatherTest {
    @Test
    public void testWeather () throws InterruptedException   {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        String query = "Воркута ";

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.gismeteo.ru/");
        Thread.sleep(2000);
        WebElement textBox = driver.findElement(By.xpath("//div//input[@type = 'search']"));
        textBox.clear();
        textBox.sendKeys(query);
        textBox.click();
        Thread.sleep(1000);
        WebElement item = driver.findElement(By.xpath("//a[@href='/weather-vorkuta-3960/']"));
        System.out.println("found weather path");
        item.click();
        WebElement textTitle = driver.findElement(By.xpath("//div[@class='page-title']//h1"));
        Assert.assertEquals(textTitle.getText(), "Погода в Воркуте");
        driver.close();

    }

    @Test
    public void testWeatherFor10Days () throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.gismeteo.ru/weather-vorkuta-3960/");
        Thread.sleep(2000);
        WebElement itemLink = driver.findElement(By.xpath("//div[@class = 'subnav-menu header-subnav-menu']/a[2]"));
        itemLink.click();
        Thread.sleep(1000);
        WebElement textTitle = driver.findElement(By.xpath("//div[@class='page-title']//h1"));
        Assert.assertEquals(textTitle.getText(), "Погода в Воркуте на завтра");
        driver.close();

    }
}
