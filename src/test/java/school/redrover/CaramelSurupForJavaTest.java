package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CaramelSurupForJavaTest {
    @Test
    public void openWeatherMapGridContainerTest() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://openweathermap.org/");
        String title = driver.getTitle();

//        WebDriverWait waitTillVisible = new WebDriverWait(driver, 50);
        Assert.assertEquals(title, "Сurrent weather and forecast - OpenWeatherMap");

        Thread.sleep(5000);

        WebElement celsiusButton = driver.findElement(By.xpath
                ("//div[text() = 'Metric: °C, m/s']"));
        WebElement fahrenheitButton = driver.findElement(By.xpath
                ("//div[text() = 'Imperial: °F, mph']"));
        WebElement temperature = driver.findElement(By.className("heading"));
        Assert.assertTrue(temperature.getText().contains("C"));
        celsiusButton.isDisplayed();
        fahrenheitButton.click();

        Assert.assertTrue(temperature.getText().contains("F"));
        celsiusButton.click();
        Assert.assertTrue(temperature.getText().contains("C"));

        driver.quit();
    }
}
