package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class YuliyaKTest {
    @Test
    public void testH2TagText_WhenSearchingCityCharlotte() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver();

        String cityName = "Orlando";
        String expectedResult = "Orlando, US";

        driver.get("https://openweathermap.org/ ");

        Thread.sleep(5000);

        WebElement searchCityField = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']"));

        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = driver.findElement(By.xpath("//button[@type = 'submit']"));
        searchButton.click();

        Thread.sleep(1000);

        WebElement charlotteDropdownMenu = driver.findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Orlando, US ']"));
        charlotteDropdownMenu.click();

        WebElement h2CityNameHeader = driver.findElement(
                By.xpath("//div[@class = 'section-content']/div/div/div/h2"));
        Thread.sleep(2000);

        String actualResult = h2CityNameHeader.getText();
        Assert.assertEquals(actualResult, expectedResult);

        Thread.sleep(2000);

        driver.quit();

    }
}
