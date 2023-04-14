package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;

public class CaramelSyrupForJavaTest {

    @Test
    public void testMessengersOpenWeather() throws InterruptedException {

        String facebookUrl = "https://www.facebook.com/groups/270748973021342";
        String twitterUrl = "https://twitter.com/OpenWeatherMap";
        String linkedinUrl = "https://www.linkedin.com/company/openweathermap/";
        String mediumUrl = "https://medium.com/@openweathermap";
        String telegramUrl = "https://t.me/openweathermap";
        String githubUrl = "https://github.com/search?q=openweathermap&ref=cmdform";

        int expectedResult = 6;

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

        driver.get("https://openweathermap.org/");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        WebElement facebook = driver.findElement(By.xpath("//a[@href='https://www.facebook.com/groups/270748973021342']"));
        WebElement twitter = driver.findElement(By.xpath("//a[@href='https://twitter.com/OpenWeatherMap']"));
        WebElement linkedin = driver.findElement(By.xpath("//a[@href='https://www.linkedin.com/company/9816754']"));
        WebElement medium = driver.findElement(By.xpath("//a[@href='https://medium.com/@openweathermap']"));
        WebElement telegram = driver.findElement(By.xpath("//a[@href='https://t.me/openweathermap']"));
        WebElement github = driver.findElement(By.xpath("//a[@href='https://github.com/search?q=openweathermap&ref=cmdform']"));

        WebElement[] messengers = {facebook, twitter, linkedin, medium, telegram, github};

        String[] messengersUrl = {facebookUrl, twitterUrl, linkedinUrl, mediumUrl, telegramUrl, githubUrl};

        int actualResult = 0;

        for (int i = 0; i < messengers.length; i++) {
            messengers[i].click();
            ArrayList<String> windows = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(windows.get(1));
            driver.getCurrentUrl();
            for (int j = 0; j < messengersUrl.length; j++) {
                if (driver.getCurrentUrl().equals(messengersUrl[j])) {
                    actualResult++;
                }
            }
            driver.switchTo().window(windows.get(0));
        }

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    @Test
    public void testArtyomDulyaOpenWeatherGuideClick() throws InterruptedException {

        String expectedResultUrl = "https://openweather.co.uk/";
        String expectedResultBanner = "Complete spectrum of weather data solutions.";

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();

        driver.get("https://openweathermap.org/");
        Thread.sleep(5000);
        WebElement guide = driver.findElement(By.xpath("//ul//div//ul/li//a[@href='/guide']"));

        guide.click();

        WebElement complexEnterprise = driver.findElement(
                By.xpath("//main//div[2]/div/div/p[1]/a[text()='complex enterprise systems']"));
        complexEnterprise.click();

        ArrayList<String> windows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windows.get(1));

        WebElement text = driver.findElement(By.xpath("//h1//span[@class='white-text']"));

        String actualResultUrl = driver.getCurrentUrl();
        String actualResultBanner = text.getText();

        Assert.assertEquals(actualResultUrl, expectedResultUrl);
        Assert.assertEquals(actualResultBanner, expectedResultBanner);

        driver.quit();
    }

    @Test
    public void testArtyomDulyaSearchLineHeader() throws InterruptedException {

        String expectedResult = "Paris, FR";

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

        driver.get("https://openweathermap.org/");

        String selectorSearchLine = "//ul[@id='first-level-nav']//div//form//input[@placeholder='Weather in your city']";
        WebElement searchLineHeader = driver.findElement(By.xpath(selectorSearchLine));
        searchLineHeader.sendKeys("Paris\n");

        WebElement paris = driver.findElement(By.xpath("//td//b//a[@href='/city/2988507']"));
        paris.click();

        WebElement parisText = driver.findElement(By.tagName("h2"));

        String actualResult = parisText.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    @Test
    public void testArtyomDulyaAuthorizationText() throws InterruptedException {

        String actualResult = "Sign In To Your Account";

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://openweathermap.org/");
        Thread.sleep(5000);

        WebElement signIn = driver.findElement
                (By.xpath("//div[@id='desktop-menu']//ul//li[11]//a[text()='Sign in']"));
        signIn.click();

        WebElement loginText = driver.findElement(By.xpath("//h3"));

        String expectedResult = loginText.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    @Test
    public void testArtyomDulyaWildberries() {

        int expectedResult = 26;

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

        driver.get("https://www.wildberries.ru/");

        WebElement burgerMenu = driver.findElement(By.xpath("//button[@data-wba-header-name='Catalog']"));
        burgerMenu.click();

        WebElement women = driver.findElement(By.xpath("//li[@data-menu-id='306']"));
        WebElement boot = driver.findElement(By.xpath("//li[@data-menu-id='629']"));
        WebElement kids = driver.findElement(By.xpath("//li[@data-menu-id='115']"));
        WebElement mens = driver.findElement(By.xpath("//li[@data-menu-id='566']"));
        WebElement hays = driver.findElement(By.xpath("//li[@data-menu-id='258']"));
        WebElement beauty = driver.findElement(By.xpath("//li[@data-menu-id='543']"));
        WebElement accessories = driver.findElement(By.xpath("//li[@data-menu-id='1']"));
        WebElement electronics = driver.findElement(By.xpath("//li[@data-menu-id='4830']"));
        WebElement toys = driver.findElement(By.xpath("//li[@data-menu-id='481']"));
        WebElement furniture = driver.findElement(By.xpath("//li[@data-menu-id='62827']"));
        WebElement adults = driver.findElement(By.xpath("//li[@data-menu-id='62057']"));
        WebElement products = driver.findElement(By.xpath("//li[@data-menu-id='10296']"));
        WebElement appliances = driver.findElement(By.xpath("//li[@data-menu-id='16107']"));
        WebElement petSupplies = driver.findElement(By.xpath("//li[@data-menu-id='6119']"));
        WebElement sports = driver.findElement(By.xpath("//li[@data-menu-id='784']"));
        WebElement autoProducts = driver.findElement(By.xpath("//li[@data-menu-id='6994']"));
        WebElement books = driver.findElement(By.xpath("//li[@data-menu-id='519']"));
        WebElement jewelry = driver.findElement(By.xpath("//li[@data-menu-id='1023']"));
        WebElement tools = driver.findElement(By.xpath("//li[@data-menu-id='17006']"));
        WebElement garden = driver.findElement(By.xpath("//li[@data-menu-id='4863']"));
        WebElement health = driver.findElement(By.xpath("//li[@data-menu-id='10326']"));
        WebElement stationery = driver.findElement(By.xpath("//li[@data-menu-id='5486']"));
        WebElement stock = driver.findElement(By.xpath("//li[@data-menu-id='2192']"));
        WebElement digitalGoods = driver.findElement(By.xpath("//li[@data-menu-id='12']"));
        WebElement madeInMoscow = driver.findElement(By.xpath("//li[@data-menu-id='130255']"));
        WebElement trips = driver.findElement(By.xpath("//li[@data-menu-id='61037']"));

        WebElement[] categories = {women, boot, kids, mens, hays, beauty, accessories, electronics, toys,
                furniture, adults, products, appliances, petSupplies, sports, autoProducts, books, jewelry,
                tools, garden, health, stationery, stock, digitalGoods, madeInMoscow, trips};

        int actualResult = 0;

        for (int i = 0; i < categories.length; i++) {
            actualResult++;
        }

        Assert.assertEquals(actualResult, expectedResult);
        driver.quit();
    }

    @Test
    public void dimaKFirstTest() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        String exp = "One Call API 3.0 - OpenWeatherMap";

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://openweathermap.org");
        Thread.sleep(5000);
        WebElement oneCallApi = driver.findElement(By.xpath("//div [@class='section']//h2/a"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");
        Thread.sleep(3000);
        oneCallApi.click();

        String act = driver.getTitle();

        Assert.assertEquals(exp, act);

        driver.quit();
    }
@Ignore
    @Test
    public void testArtyomDulyaThehostbest() throws InterruptedException {

        String expectedResult = "https://thehostbest.ru/business-card-site/";

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);

        JavascriptExecutor js = (JavascriptExecutor) driver;

        driver.get("https://thehostbest.ru/");

        WebElement button = driver.findElement(By.xpath("//a[text()='Визитка']"));
        js.executeScript("window.scrollBy(0,200)");
        button.click();

        String actualResult = driver.getCurrentUrl();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }
@Ignore
    @Test
    public static void testArtyomDulyaHeaderButton() throws InterruptedException {

        int expectedResult = 3;

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://openweathermap.org/");
        Thread.sleep(5000);

        WebElement guide = driver.findElement(By.xpath("//ul//div//ul/li//a[@href='/guide']"));
        WebElement api = driver.findElement(By.xpath("//div[@id='desktop-menu']//ul//li[2]//a[@href='/api']"));
        WebElement dashboard = driver.findElement((By.xpath("//div[@id='desktop-menu']//ul//li[3]//a[@href='/weather-dashboard']")));

        WebElement[] headers = {guide, api, dashboard};

        ArrayList<String> urls = new ArrayList<>(10);
        urls.add("https://openweathermap.org/guide");
        urls.add("https://openweathermap.org/api");
        urls.add("https://openweathermap.org/weather-dashboard");

        int count = 0;

        for (int i = 0; i < headers.length; i++) {
            headers[i].click();
            driver.getCurrentUrl();
            for (int j = 0; j < urls.size(); j++) {
                if (driver.getCurrentUrl().equals(urls.get(j))) {
                    count++;
                }
            }
            driver.navigate().back();
        }

        int actualResult = count;

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }


}
