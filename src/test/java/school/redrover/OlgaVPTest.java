package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OlgaVPTest {

    @Test
    public void testOpenWeatherGuide() throws InterruptedException{

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);

        String baseUrl = "https://openweathermap.org/";
        String buttonName = "Guide";
        String expectedResultUrl = "https://openweathermap.org/guide";
        String expectedResultTitle = "OpenWeatherMap API guide - OpenWeatherMap";

        driver.get(baseUrl);
        Thread.sleep(5000);

        WebElement guideButton = driver.findElement(By.xpath("//a[@href = '/guide']"));
        guideButton.click();

        String actualResultUrl = driver.getCurrentUrl();
        String actualResultTitle = driver.getTitle();

        Assert.assertEquals(actualResultUrl, expectedResultUrl);
        Assert.assertEquals(actualResultTitle, expectedResultTitle);

        driver.quit();
    }


    @Test
    public void testThirtyButtons() throws InterruptedException{

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);

        String baseUrl = "https://openweathermap.org/";
        int expectedResultOrangeButtons = 30;

        driver.get(baseUrl);
        Thread.sleep(5000);

        WebElement apiMenu = driver.findElement(By.xpath("//*[@id=\"desktop-menu\"]//li//a[@href='/api']"));
        apiMenu.click();

        int actualResultOrangeButtons = driver.findElements(By.xpath("//a[contains(@class, 'orange')]")).size();
        Assert.assertEquals(actualResultOrangeButtons, expectedResultOrangeButtons);

        driver.quit();
    }
}
