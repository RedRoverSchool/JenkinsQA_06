package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GroupJavaQaTeamTest {

    @Test
    public void testFirst() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        String title = driver.getTitle();
        assertEquals("Web form", title);

        WebElement textBox = driver.findElement(By.name("my-text"));
        WebElement submitButton = driver.findElement(By.cssSelector("button"));

        Thread.sleep(3000);

        textBox.sendKeys("Selenium");

        Thread.sleep(3000);

        submitButton.click();

        Thread.sleep(3000);

        WebElement message = driver.findElement(By.id("message"));
        String value = message.getText();
        assertEquals("Received!", value);

        Thread.sleep(3000);

        driver.quit();
    }

    @Test
    public void testH2TagText_WhenSearchingCityOrlando() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);

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

        Thread.sleep(1000);

        String actualResult = h2CityNameHeader.getText();
        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();

    }

    @Test
    public void testLinkSearchLanguage() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("http://www.99-bottles-of-beer.net/");

        WebElement linkSearchLanguage = driver.findElement(By.linkText("Search Languages"));
        linkSearchLanguage.click();

        WebElement headerText = driver.findElement(
                By.xpath("//div[@id = 'main']/h2[contains(.,'Search Languages')]"));

        Assert.assertEquals(headerText.getText(), "Search Languages");

        driver.quit();
    }


}
