package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;


public class CaramelSurupForJavaTest {

    @Test
    public void artyomDulyaOpenWeatherGuideClick() throws InterruptedException {

        String expectedResultUrl = "https://openweather.co.uk/";
        String expectedResultBanner = "Complete spectrum of weather data solutions.";

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://openweathermap.org/");
        Thread.sleep(5000);
        WebElement guide = driver.findElement(By.xpath("//ul//div//ul/li//a[@href='/guide']"));

        guide.click();

        WebElement complexEnterprise = driver.findElement(
                By.xpath("//main//div[2]/div/div/p[1]/a[text()='complex enterprise systems']"));
        complexEnterprise.click();

        ArrayList<String> windows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(windows.get(1));

        WebElement text = driver.findElement(By.xpath("//h1//span[@class='white-text']"));

        String actualResultUrl = driver.getCurrentUrl();
        String actualResultBanner = text.getText();

        Assert.assertEquals(actualResultUrl, expectedResultUrl);
        Assert.assertEquals(actualResultBanner, expectedResultBanner);

        driver.quit();
    }
}
