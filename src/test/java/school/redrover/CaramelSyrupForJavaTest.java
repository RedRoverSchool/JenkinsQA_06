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

        getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

        getDriver().get("https://openweathermap.org/");

        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        WebElement facebook = getDriver().findElement(By.xpath("//a[@href='https://www.facebook.com/groups/270748973021342']"));
        WebElement twitter = getDriver().findElement(By.xpath("//a[@href='https://twitter.com/OpenWeatherMap']"));
        WebElement linkedin = getDriver().findElement(By.xpath("//a[@href='https://www.linkedin.com/company/9816754']"));
        WebElement medium = getDriver().findElement(By.xpath("//a[@href='https://medium.com/@openweathermap']"));
        WebElement telegram = getDriver().findElement(By.xpath("//a[@href='https://t.me/openweathermap']"));
        WebElement github = getDriver().findElement(By.xpath("//a[@href='https://github.com/search?q=openweathermap&ref=cmdform']"));

        WebElement[] messengers = {facebook, twitter, linkedin, medium, telegram, github};

        String[] messengersUrl = {facebookUrl, twitterUrl, linkedinUrl, mediumUrl, telegramUrl, githubUrl};

        int actualResult = 0;

        for (int i = 0; i < messengers.length; i++) {
            messengers[i].click();
            ArrayList<String> windows = new ArrayList<>(getDriver().getWindowHandles());
            getDriver().switchTo().window(windows.get(1));
            getDriver().getCurrentUrl();
            for (int j = 0; j < messengersUrl.length; j++) {
                if (getDriver().getCurrentUrl().equals(messengersUrl[j])) {
                    actualResult++;
                }
            }
            getDriver().switchTo().window(windows.get(0));
        }

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testArtyomDulyaOpenWeatherGuideClick() throws InterruptedException {

        String expectedResultUrl = "https://openweather.co.uk/";
        String expectedResultBanner = "Complete spectrum of weather data solutions.";

        getDriver().get("https://openweathermap.org/");
        Thread.sleep(5000);
        WebElement guide = getDriver().findElement(By.xpath("//ul//div//ul/li//a[@href='/guide']"));

        guide.click();

        WebElement complexEnterprise = getDriver().findElement(
                By.xpath("//main//div[2]/div/div/p[1]/a[text()='complex enterprise systems']"));
        complexEnterprise.click();

        ArrayList<String> windows = new ArrayList<>(getDriver().getWindowHandles());
        getDriver().switchTo().window(windows.get(1));

        WebElement text = getDriver().findElement(By.xpath("//h1//span[@class='white-text']"));

        String actualResultUrl = getDriver().getCurrentUrl();
        String actualResultBanner = text.getText();

        Assert.assertEquals(actualResultUrl, expectedResultUrl);
        Assert.assertEquals(actualResultBanner, expectedResultBanner);
    }

    @Ignore
    @Test
    public void testArtyomDulyaSearchLineHeader() throws InterruptedException {

        String expectedResult = "Paris, FR";

        getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

        getDriver().get("https://openweathermap.org/");

        String selectorSearchLine = "//ul[@id='first-level-nav']//div//form//input[@placeholder='Weather in your city']";
        WebElement searchLineHeader = getDriver().findElement(By.xpath(selectorSearchLine));
        searchLineHeader.sendKeys("Paris\n");

        WebElement paris = getDriver().findElement(By.xpath("//td//b//a[@href='/city/2988507']"));
        paris.click();

        WebElement parisText = getDriver().findElement(By.tagName("h2"));

        String actualResult = parisText.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Ignore
    @Test
    public void testArtyomDulyaAuthorizationText() throws InterruptedException {

        String actualResult = "Sign In To Your Account";

        getDriver().get("https://openweathermap.org/");
        Thread.sleep(5000);

        WebElement signIn = getDriver().findElement
                (By.xpath("//div[@id='desktop-menu']//ul//li[11]//a[text()='Sign in']"));
        signIn.click();

        WebElement loginText = getDriver().findElement(By.xpath("//h3"));

        String expectedResult = loginText.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testArtyomDulyaWildberries() {

        int expectedResult = 26;

        getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

        getDriver().get("https://www.wildberries.ru/");

        WebElement burgerMenu = getDriver().findElement(By.xpath("//button[@data-wba-header-name='Catalog']"));
        burgerMenu.click();

        WebElement women = getDriver().findElement(By.xpath("//li[@data-menu-id='306']"));
        WebElement boot = getDriver().findElement(By.xpath("//li[@data-menu-id='629']"));
        WebElement kids = getDriver().findElement(By.xpath("//li[@data-menu-id='115']"));
        WebElement mens = getDriver().findElement(By.xpath("//li[@data-menu-id='566']"));
        WebElement hays = getDriver().findElement(By.xpath("//li[@data-menu-id='258']"));
        WebElement beauty = getDriver().findElement(By.xpath("//li[@data-menu-id='543']"));
        WebElement accessories = getDriver().findElement(By.xpath("//li[@data-menu-id='1']"));
        WebElement electronics = getDriver().findElement(By.xpath("//li[@data-menu-id='4830']"));
        WebElement toys = getDriver().findElement(By.xpath("//li[@data-menu-id='481']"));
        WebElement furniture = getDriver().findElement(By.xpath("//li[@data-menu-id='62827']"));
        WebElement adults = getDriver().findElement(By.xpath("//li[@data-menu-id='62057']"));
        WebElement products = getDriver().findElement(By.xpath("//li[@data-menu-id='10296']"));
        WebElement appliances = getDriver().findElement(By.xpath("//li[@data-menu-id='16107']"));
        WebElement petSupplies = getDriver().findElement(By.xpath("//li[@data-menu-id='6119']"));
        WebElement sports = getDriver().findElement(By.xpath("//li[@data-menu-id='784']"));
        WebElement autoProducts = getDriver().findElement(By.xpath("//li[@data-menu-id='6994']"));
        WebElement books = getDriver().findElement(By.xpath("//li[@data-menu-id='519']"));
        WebElement jewelry = getDriver().findElement(By.xpath("//li[@data-menu-id='1023']"));
        WebElement tools = getDriver().findElement(By.xpath("//li[@data-menu-id='17006']"));
        WebElement garden = getDriver().findElement(By.xpath("//li[@data-menu-id='4863']"));
        WebElement health = getDriver().findElement(By.xpath("//li[@data-menu-id='10326']"));
        WebElement stationery = getDriver().findElement(By.xpath("//li[@data-menu-id='5486']"));
        WebElement stock = getDriver().findElement(By.xpath("//li[@data-menu-id='2192']"));
        WebElement digitalGoods = getDriver().findElement(By.xpath("//li[@data-menu-id='12']"));
        WebElement madeInMoscow = getDriver().findElement(By.xpath("//li[@data-menu-id='130255']"));
        WebElement trips = getDriver().findElement(By.xpath("//li[@data-menu-id='61037']"));

        WebElement[] categories = {women, boot, kids, mens, hays, beauty, accessories, electronics, toys,
                furniture, adults, products, appliances, petSupplies, sports, autoProducts, books, jewelry,
                tools, garden, health, stationery, stock, digitalGoods, madeInMoscow, trips};

        int actualResult = 0;

        for (int i = 0; i < categories.length; i++) {
            actualResult++;
        }

        Assert.assertEquals(actualResult, expectedResult);
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
    @Test
    public void testAnastasiyaAbramova() {
        String expectedResult = "https://openweathermap.org/";
        beforeMethod();
        getDriver().get("https://openweathermap.org/");
        String actualResult = getDriver().getCurrentUrl();
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testArtyomDulyaEnterpriseGuide() throws InterruptedException {

        String expectedResult = "OpenWeather Enterprise Guide";

        getDriver().get("https://openweathermap.org/");
        Thread.sleep(5000);

        WebElement guide = getDriver().findElement(By.xpath("//ul//div//ul/li//a[@href='/guide']"));
        guide.click();
        WebElement enterpriseSystem = getDriver().findElement(By.xpath("//a[text()='complex enterprise systems']"));
        enterpriseSystem.click();
        ArrayList<String> windows = new ArrayList<>(getDriver().getWindowHandles());
        getDriver().switchTo().window(windows.get(1));
        WebElement tailoredToYou = getDriver().findElement(By.xpath("//a[@href='/enterprise-approach']//u[text()='Tailored to you']"));
        tailoredToYou.click();
        WebElement banner = getDriver().findElement(By.xpath("//span[text()='OpenWeather Enterprise Guide']"));
        String actualResult = banner.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

}
