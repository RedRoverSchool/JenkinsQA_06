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

    @Ignore
    @Test
    public void svetaKhudovaWinnerTest() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://winnerfit.ru/");

        WebElement fitnessTekstil = driver.findElement(By.xpath("//div[@class='t694__overlay '][1]"));
        fitnessTekstil.click();

        Thread.sleep(3000);

        WebElement phoneNumber1 = driver.findElement(By.xpath("//div[@class='t228__right_descr']/a[1]"));
        WebElement phoneNumber2 = driver.findElement(By.xpath("//div[@class='t228__right_descr']/a[2]"));

        Assert.assertEquals(phoneNumber1.getText(), "+7 (499) 178-60-18");
        Assert.assertEquals(phoneNumber2.getText(), "+7 (905) 714-13-70");

        driver.quit();
    }






}
