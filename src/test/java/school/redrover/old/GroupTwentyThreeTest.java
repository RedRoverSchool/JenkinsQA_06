package school.redrover.old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
@Ignore
public class GroupTwentyThreeTest extends BaseTest {
    @Test
    public void testJavaTutorial() throws InterruptedException {
        getDriver().get("https://www.w3schools.com/");

        WebElement textBox = getDriver().findElement(By.id("search2"));

        textBox.sendKeys("Java Tutorial\n");

        Thread.sleep(2000);

        WebElement text = getDriver().findElement(By.xpath("//h1"));

        Assert.assertEquals(text.getText(), "Java Tutorial");
    }


    @Test
    public void testMansurovaElena() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expResult = "Paris, FR";

        driver.get(url);

        Thread.sleep(5000);

        WebElement searchCityField = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']")
        );

        searchCityField.click();

        searchCityField.sendKeys(cityName);

        WebElement searchButton = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']")
        );
        searchButton.click();
        Thread.sleep(3000);

        WebElement parisFRChoiceInDropdownMenu = driver.findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']")
        );
        parisFRChoiceInDropdownMenu.click();

        WebElement h2CityCountryHeader = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );
        Thread.sleep(2000);
        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expResult);

        driver.quit();
    }

    @Test
    public void testBaha1() throws InterruptedException {

        
        getDriver().get("https://demoqa.com/text-box");

        WebElement fullName = getDriver().findElement(By.id("userName"));
        fullName.sendKeys("Baktygul");
        Thread.sleep(2000);

        WebElement email = getDriver().findElement(By.id("userEmail"));
        email.sendKeys("baktygul.jekshembieva@gmail.com");
        Thread.sleep(2000);

        WebElement currentAddress = getDriver().findElement(By.id("currentAddress"));
        currentAddress.sendKeys("Biskek");
        Thread.sleep(2000);

        WebElement permanentAddress = getDriver().findElement(By.id("permanentAddress"));
        permanentAddress.sendKeys("Kerben");
        Thread.sleep(2000);

        WebElement submit = getDriver().findElement(By.id("submit"));
        submit.click();
        Thread.sleep(2000);

        WebElement name = getDriver().findElement(By.id("name"));
        Assert.assertEquals(name.getText(), "Name:Baktygul");

    }

    @Test
    public void testShurminDenis() throws InterruptedException {

        getDriver().get("https://translate.google.com/");

        WebElement mainMenuButton = getDriver().findElement(By.xpath("//div[@class = 'gb_Kc']"));
        mainMenuButton.click();

        WebElement privacyTermsButton = getDriver().findElement(By.xpath("//div/a[normalize-space()='Privacy & Terms']"));
        privacyTermsButton.click();
        Thread.sleep(2000);

        WebElement text = getDriver().findElement(By.xpath("//h2[normalize-space()='Terms of Service']"));

        Assert.assertEquals(text.getText(), "Terms of Service");

    }

}

