import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MansurovaTest {

    @Test

    public void testMansurova_SearchingCityCountry() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        driver.get(url);

        Thread.sleep(5000);

        WebElement searchCityField = driver.findElement(
                By.xpath("//div[@id='weather-widget']//input[@placeholder='Search city']")
        );

        searchCityField.click();

        Thread.sleep(5000);

        searchCityField.sendKeys(cityName);

        WebElement searchButton = driver.findElement(
                By.xpath("//div[@id='weather-widget']//button[@type='submit']")
        );

        searchButton.click();

        Thread.sleep(2000);

        WebElement parisFRChoiceInDropdownMenu = driver.findElement(
                By.xpath("//ul[@class='search-dropdown-menu']/li/span[text() = 'Paris, FR ']")
        );

        parisFRChoiceInDropdownMenu.click();

        WebElement h2CityCountryHeader = driver.findElement(
                By.xpath("//div[@id='weather-widget']//h2")
        );
        Thread.sleep(2000);


        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);


        driver.quit();
    }
}
