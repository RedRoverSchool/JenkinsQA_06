package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class GroupUkrTest {

    @Test
    public void youtubeSearchTest(){
        ChromeOptions optionsChrome = new ChromeOptions();

        optionsChrome.addArguments("user-agent='Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36'");
        optionsChrome.addArguments("--headless","--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(optionsChrome);
        driver.get("https://www.youtube.com/");

        String title = driver.getTitle();
        Assert.assertEquals("YouTube", title);

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement searchInput = driver.findElement(By.xpath("//input[@id='search']"));
        WebElement searchButton = driver.findElement(By.xpath("//button[@id='search-icon-legacy']"));

        searchInput.sendKeys("Что такое Selenium?");
        searchButton.click();

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
//
//        WebElement message = driver.findElement(By.id("message"));
//        String value = message.getText();
//        Assert.assertEquals("Received!", value);

        WebElement link = driver.findElement(By.xpath("//a[@title='Что такое Selenium?']"));
        String value = link.getText();

        Assert.assertEquals(value, "Что такое Selenium?");

        driver.quit();

    }
}
