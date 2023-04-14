package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SergeiLebedevTest {
    @Test
    public void testWeatherSL() throws InterruptedException {
        //Chrome options
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless", "--window-size=1920,1080");

        //Open start page
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://openweathermap.org/");
        Thread.sleep(5000);

        //Click on the button
        WebElement button = driver.findElement(By.xpath("//a[@href='/guide']"));
        button.click();
        Thread.sleep(5000);

        //Check page url
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(currentURL, "https://openweathermap.org/guide");

        //Check page title
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, "OpenWeatherMap API guide - OpenWeatherMap");

        //quite browser
        driver.quit();

    }
}
