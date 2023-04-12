package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class Group99BottlesTest {

    @Test
    public void testTitleBasePage() {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://petstore.octoperf.com/actions/Catalog.action");

        Assert.assertEquals(driver.getTitle(), "JPetStore Demo");

        driver.quit();
    }

    @Test
    public void testCheckboxesHeadHerokuApp() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://formy-project.herokuapp.com/");

        driver.findElement(By.xpath("//li/a[@href='/checkbox']")).click();

        Thread.sleep(3000);

        WebElement text = driver.findElement(By.xpath("//h1"));

        Assert.assertEquals(text.getText(), "Checkboxes");

        driver.quit();
    }

    @Test
    public void testHeaderH1TextOnWomenPage() {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://askomdch.com/");

        driver.findElement(By.id("menu-item-1229")).click();

        WebElement headerText = driver.findElement(
                By.xpath("//header[@class = 'woocommerce-products-header']/h1"));

        Assert.assertEquals(headerText.getText(), "Women");

        driver.quit();
    }

    @Test
    public void testWorkshopHeaderText () {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://corporatetrainingmaterials.com/");

        driver.findElement(By.xpath("//ul[@id='AccessibleNav']/li[2]/a")).click();

        WebElement headerH1Text = driver.findElement(
                By.xpath("//div[@id='ProductSection']//div[1]/h1"));

        Assert.assertEquals(headerH1Text.getText(), "WORKSHOP LIBRARY");

        driver.quit();

    }

    @Test
    public void testH2Text_WhenSearchingCityCountry() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        driver.get(url);
        Thread.sleep(5000);

        WebElement searchCityField = driver.findElement(
                By.xpath("//div[@id='weather-widget']//input[@placeholder='Search city']")
        );
        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = driver.findElement(
                By.xpath("//button[@type='submit']")
        );
        searchButton.click();
        Thread.sleep(1000);


        WebElement parisFRChoiceDropdownMenu = driver.findElement(
                By.xpath("//ul[@class='search-dropdown-menu']/li/span[text()='Paris, FR ']")
        );
        parisFRChoiceDropdownMenu.click();

        WebElement h2CityNameHeader = driver.findElement(
                By.xpath(
                        "//div[@id='weather-widget']//h2")
        );

        Thread.sleep(2000);
        String actualResult = h2CityNameHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    @Test
    public void testTelerikHeaderText() {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.telerik.com/");

        WebElement headerH1Text = driver.findElement(
                By.xpath("//div[@id='ContentPlaceholder1_C339_Col00']/h1"));

        Assert.assertEquals(headerH1Text.getText(), "Modern UI Made Easy");

        driver.quit();
    }

    @Test
    public void testTelerikTitleURLDemosPage() {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.telerik.com/");

        driver.manage().window().maximize();

        driver.findElement(By.xpath("//nav[@id='js-tlrk-nav']//ul[@class='TK-Context-Menu TK-Menu']/li[1]/a")).click();

        Assert.assertEquals(driver.getTitle(), "Telerik Product Demos, Examples and Tutorials for all Telerik products");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.telerik.com/support/demos");

        driver.quit();
    }
}
