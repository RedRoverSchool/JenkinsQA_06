package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;
import java.util.ArrayList;

public class CaramelSyrupForJavaTest extends BaseTest {

    @Test
    public void testMessengersOpenWeather() throws InterruptedException {

        String facebookUrl = "https://www.facebook.com/groups/270748973021342";
        String twitterUrl = "https://twitter.com/OpenWeatherMap";
        String linkedinUrl = "https://www.linkedin.com/company/openweathermap/";
        String mediumUrl = "https://medium.com/@openweathermap";
        String telegramUrl = "https://t.me/openweathermap";
        String githubUrl = "https://github.com/search?q=openweathermap&ref=cmdform";

        int expectedResult = 6;

        beforeMethod();
        getGetdriver().manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

        getGetdriver().get("https://openweathermap.org/");

        JavascriptExecutor js = (JavascriptExecutor) getGetdriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        WebElement facebook = getGetdriver().findElement(By.xpath("//a[@href='https://www.facebook.com/groups/270748973021342']"));
        WebElement twitter = getGetdriver().findElement(By.xpath("//a[@href='https://twitter.com/OpenWeatherMap']"));
        WebElement linkedin = getGetdriver().findElement(By.xpath("//a[@href='https://www.linkedin.com/company/9816754']"));
        WebElement medium = getGetdriver().findElement(By.xpath("//a[@href='https://medium.com/@openweathermap']"));
        WebElement telegram = getGetdriver().findElement(By.xpath("//a[@href='https://t.me/openweathermap']"));
        WebElement github = getGetdriver().findElement(By.xpath("//a[@href='https://github.com/search?q=openweathermap&ref=cmdform']"));

        WebElement[] messengers = {facebook, twitter, linkedin, medium, telegram, github};

        String[] messengersUrl = {facebookUrl, twitterUrl, linkedinUrl, mediumUrl, telegramUrl, githubUrl};

        int actualResult = 0;

        for (int i = 0; i < messengers.length; i++) {
            messengers[i].click();
            ArrayList<String> windows = new ArrayList<>(getGetdriver().getWindowHandles());
            getGetdriver().switchTo().window(windows.get(1));
            getGetdriver().getCurrentUrl();
            for (int j = 0; j < messengersUrl.length; j++) {
                if (getGetdriver().getCurrentUrl().equals(messengersUrl[j])) {
                    actualResult++;
                }
            }
            getGetdriver().switchTo().window(windows.get(0));
        }

        Assert.assertEquals(actualResult, expectedResult);

        afterMethod();
    }

    @Test
    public void testArtyomDulyaOpenWeatherGuideClick() throws InterruptedException {

        String expectedResultUrl = "https://openweather.co.uk/";
        String expectedResultBanner = "Complete spectrum of weather data solutions.";

        beforeMethod();

        getGetdriver().get("https://openweathermap.org/");
        Thread.sleep(5000);
        WebElement guide = getGetdriver().findElement(By.xpath("//ul//div//ul/li//a[@href='/guide']"));

        guide.click();

        WebElement complexEnterprise = getGetdriver().findElement(
                By.xpath("//main//div[2]/div/div/p[1]/a[text()='complex enterprise systems']"));
        complexEnterprise.click();

        ArrayList<String> windows = new ArrayList<>(getGetdriver().getWindowHandles());
        getGetdriver().switchTo().window(windows.get(1));

        WebElement text = getGetdriver().findElement(By.xpath("//h1//span[@class='white-text']"));

        String actualResultUrl = getGetdriver().getCurrentUrl();
        String actualResultBanner = text.getText();

        Assert.assertEquals(actualResultUrl, expectedResultUrl);
        Assert.assertEquals(actualResultBanner, expectedResultBanner);

        afterMethod();
    }

    @Test
    public void testArtyomDulyaSearchLineHeader() throws InterruptedException {

        String expectedResult = "Paris, FR";

        beforeMethod();

        getGetdriver().manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

        getGetdriver().get("https://openweathermap.org/");

        String selectorSearchLine = "//ul[@id='first-level-nav']//div//form//input[@placeholder='Weather in your city']";
        WebElement searchLineHeader = getGetdriver().findElement(By.xpath(selectorSearchLine));
        searchLineHeader.sendKeys("Paris\n");

        WebElement paris = getGetdriver().findElement(By.xpath("//td//b//a[@href='/city/2988507']"));
        paris.click();

        WebElement parisText = getGetdriver().findElement(By.tagName("h2"));

        String actualResult = parisText.getText();

        Assert.assertEquals(actualResult, expectedResult);

        afterMethod();
    }

    @Test
    public void testArtyomDulyaAuthorizationText() throws InterruptedException {

        String actualResult = "Sign In To Your Account";

        beforeMethod();

        getGetdriver().get("https://openweathermap.org/");
        Thread.sleep(5000);

        WebElement signIn = getGetdriver().findElement
                (By.xpath("//div[@id='desktop-menu']//ul//li[11]//a[text()='Sign in']"));
        signIn.click();

        WebElement loginText = getGetdriver().findElement(By.xpath("//h3"));

        String expectedResult = loginText.getText();

        Assert.assertEquals(actualResult, expectedResult);

        afterMethod();
    }

    @Test
    public void testArtyomDulyaWildberries() {

        int expectedResult = 26;

        beforeMethod();

        getGetdriver().manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

        getGetdriver().get("https://www.wildberries.ru/");

        WebElement burgerMenu = getGetdriver().findElement(By.xpath("//button[@data-wba-header-name='Catalog']"));
        burgerMenu.click();

        WebElement women = getGetdriver().findElement(By.xpath("//li[@data-menu-id='306']"));
        WebElement boot = getGetdriver().findElement(By.xpath("//li[@data-menu-id='629']"));
        WebElement kids = getGetdriver().findElement(By.xpath("//li[@data-menu-id='115']"));
        WebElement mens = getGetdriver().findElement(By.xpath("//li[@data-menu-id='566']"));
        WebElement hays = getGetdriver().findElement(By.xpath("//li[@data-menu-id='258']"));
        WebElement beauty = getGetdriver().findElement(By.xpath("//li[@data-menu-id='543']"));
        WebElement accessories = getGetdriver().findElement(By.xpath("//li[@data-menu-id='1']"));
        WebElement electronics = getGetdriver().findElement(By.xpath("//li[@data-menu-id='4830']"));
        WebElement toys = getGetdriver().findElement(By.xpath("//li[@data-menu-id='481']"));
        WebElement furniture = getGetdriver().findElement(By.xpath("//li[@data-menu-id='62827']"));
        WebElement adults = getGetdriver().findElement(By.xpath("//li[@data-menu-id='62057']"));
        WebElement products = getGetdriver().findElement(By.xpath("//li[@data-menu-id='10296']"));
        WebElement appliances = getGetdriver().findElement(By.xpath("//li[@data-menu-id='16107']"));
        WebElement petSupplies = getGetdriver().findElement(By.xpath("//li[@data-menu-id='6119']"));
        WebElement sports = getGetdriver().findElement(By.xpath("//li[@data-menu-id='784']"));
        WebElement autoProducts = getGetdriver().findElement(By.xpath("//li[@data-menu-id='6994']"));
        WebElement books = getGetdriver().findElement(By.xpath("//li[@data-menu-id='519']"));
        WebElement jewelry = getGetdriver().findElement(By.xpath("//li[@data-menu-id='1023']"));
        WebElement tools = getGetdriver().findElement(By.xpath("//li[@data-menu-id='17006']"));
        WebElement garden = getGetdriver().findElement(By.xpath("//li[@data-menu-id='4863']"));
        WebElement health = getGetdriver().findElement(By.xpath("//li[@data-menu-id='10326']"));
        WebElement stationery = getGetdriver().findElement(By.xpath("//li[@data-menu-id='5486']"));
        WebElement stock = getGetdriver().findElement(By.xpath("//li[@data-menu-id='2192']"));
        WebElement digitalGoods = getGetdriver().findElement(By.xpath("//li[@data-menu-id='12']"));
        WebElement madeInMoscow = getGetdriver().findElement(By.xpath("//li[@data-menu-id='130255']"));
        WebElement trips = getGetdriver().findElement(By.xpath("//li[@data-menu-id='61037']"));

        WebElement[] categories = {women, boot, kids, mens, hays, beauty, accessories, electronics, toys,
                furniture, adults, products, appliances, petSupplies, sports, autoProducts, books, jewelry,
                tools, garden, health, stationery, stock, digitalGoods, madeInMoscow, trips};

        int actualResult = 0;

        for (int i = 0; i < categories.length; i++) {
            actualResult++;
        }

        Assert.assertEquals(actualResult, expectedResult);
        afterMethod();
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




}
