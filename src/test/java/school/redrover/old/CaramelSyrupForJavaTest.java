package school.redrover.old;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Ignore
public class CaramelSyrupForJavaTest extends BaseTest {

    public void clickCustom(WebElement element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        ExpectedCondition<Boolean> elementIsClickable = arg0 -> {
            try {
                element.click();
                return true;
            } catch (Exception e) {
                return false;
            }
        };
        wait.until(elementIsClickable);
    }

    @Ignore
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
        try {
            guide.click();
        } catch (Exception e) {
            clickCustom(guide);
        }
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
        getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
        String expectedResult = "Paris, FR";
        getDriver().get("https://openweathermap.org/");
        try {
            WebElement searchLineHeader = getDriver().findElement(
                    By.xpath("//ul[@id='first-level-nav']//div//form//input[@placeholder='Weather in your city']"));
            searchLineHeader.sendKeys("Paris\n");
            WebElement paris = getDriver().findElement(By.xpath("//td//b//a[@href='/city/2988507']"));
            paris.click();
            WebElement parisText = getDriver().findElement(By.tagName("h2"));
            String actualResult = parisText.getText();
            Assert.assertEquals(actualResult, expectedResult);
        } catch (Exception e) {
            Thread.sleep(5000);
            WebElement searchLineHeader = getDriver().findElement(
                    By.xpath("//ul[@id='first-level-nav']//div//form//input[@placeholder='Weather in your city']"));
            searchLineHeader.sendKeys("Paris\n");
            WebElement paris = getDriver().findElement(By.xpath("//td//b//a[@href='/city/2988507']"));
            paris.click();
            WebElement parisText = getDriver().findElement(By.tagName("h2"));
            String actualResult = parisText.getText();
            Assert.assertEquals(actualResult, expectedResult);
        }
    }
    @Ignore
    @Test
    public void testArtyomDulyaAuthorizationText() throws InterruptedException {
        String actualResult = "Sign In To Your Account";
        getDriver().get("https://openweathermap.org/");
        try {
            WebElement signIn = getDriver().findElement
                    (By.xpath("//div[@id='desktop-menu']//ul//li[11]//a[text()='Sign in']"));
            clickCustom(signIn);
        } catch (Exception e) {
            Thread.sleep(5000);
            WebElement signIn = getDriver().findElement
                    (By.xpath("//div[@id='desktop-menu']//ul//li[11]//a[text()='Sign in']"));
            clickCustom(signIn);
        }

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
        getDriver().get("https://openweathermap.org/");
        String actualResult = getDriver().getCurrentUrl();
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Ignore
    @Test
    public void testArtyomDulyaEnterpriseGuide() throws InterruptedException {

        String expectedResult = "OpenWeather Enterprise Guide";

        getDriver().get("https://openweathermap.org/");

        WebElement guide = getDriver().findElement(By.xpath("//ul//div//ul/li//a[@href='/guide']"));
        clickCustom(guide);
        WebElement enterpriseSystem = getDriver().findElement(By.xpath("//a[text()='complex enterprise systems']"));
        clickCustom(enterpriseSystem);
        ArrayList<String> windows = new ArrayList<>(getDriver().getWindowHandles());
        getDriver().switchTo().window(windows.get(1));
        WebElement tailoredToYou = getDriver().findElement(By.xpath("//a[@href='/enterprise-approach']//u[text()='Tailored to you']"));
        clickCustom(tailoredToYou);
        WebElement banner = getDriver().findElement(By.xpath("//span[text()='OpenWeather Enterprise Guide']"));
        String actualResult = banner.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Ignore
    @Test
    public void svetaKhudova_testCheckPhoneNumbers() throws InterruptedException  {
        getDriver().get("https://winnerfit.ru/");

        WebElement fitnessTekstil = getDriver().findElement(By.xpath("//div[@class='t694__overlay '][1]"));
        fitnessTekstil.click();

        Thread.sleep(2000);

        WebElement phoneNumber1 = getDriver().findElement(By.xpath("//div[@class='t228__right_descr']/a[1]"));
        WebElement phoneNumber2 = getDriver().findElement(By.xpath("//div[@class='t228__right_descr']/a[2]"));

        Assert.assertEquals(phoneNumber1.getText(), "+7 (499) 178-60-18");
        Assert.assertEquals(phoneNumber2.getText(), "+7 (905) 714-13-70");
    }

    @Test
    public void testArtyomDulyaHeader99Bottles() {
        List<String> expectedResult = Arrays.asList(
                "https://www.99-bottles-of-beer.net/abc.html",
                "https://www.99-bottles-of-beer.net/search.html",
                "https://www.99-bottles-of-beer.net/toplist.html",
                "https://www.99-bottles-of-beer.net/guestbookv2.html",
                "https://www.99-bottles-of-beer.net/submitnewlanguage.html");

        getDriver().get("https://www.99-bottles-of-beer.net/");

        List<String> actualResult = new ArrayList<>(4);

        WebElement browseLanguage = getDriver().findElement(By.xpath("//ul[@id='menu']//a[text()='Browse Languages']"));
        browseLanguage.click();
        actualResult.add(getDriver().getCurrentUrl());
        WebElement searchLanguage = getDriver().findElement(By.xpath("//div[@id='navigation']//a[text()='Search Languages']"));
        searchLanguage.click();
        actualResult.add(getDriver().getCurrentUrl());
        WebElement topListis = getDriver().findElement(By.xpath("//ul[@id='menu']//a[text()='Top Lists']"));
        topListis.click();
        actualResult.add(getDriver().getCurrentUrl());
        WebElement guestbook = getDriver().findElement(By.xpath("//div[@id='navigation']//a[text()='Guestbook']"));
        guestbook.click();
        actualResult.add(getDriver().getCurrentUrl());
        WebElement submitNewLanguage = getDriver().findElement(By.xpath("//ul[@id='menu']//a[text()='Submit new Language']"));
        submitNewLanguage.click();
        actualResult.add(getDriver().getCurrentUrl());

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testArtyomDulyaTopListsHeader() {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
        List<String> expectedResult = Arrays.asList("Top Rated", "Top Rated Real Languages",
                "Top Rated Esoteric Languages", "Top Rated Assembly Languages", "Top Hits",
                "New Languages this month", "New Comments");

        getDriver().get("https://www.99-bottles-of-beer.net/");

        List<String> actualResult = new ArrayList<>();

        WebElement topLists = getDriver().findElement(
                By.xpath("//ul[contains(@id, 'menu')]//a[normalize-space(text())='Top Lists']"));
        topLists.click();

        WebElement topRaterText = getDriver().
                findElement(By.xpath("//div[contains(@id, main)]//h2[normalize-space(text())='Top Rated']"));
        actualResult.add(topRaterText.getText());

        WebElement topRatedReal = getDriver().findElement(By.xpath("//ul[@id='submenu']//a[text()='Top Rated Real']"));
        topRatedReal.click();
        WebElement topRatedRealText = getDriver().findElement(By.xpath("//h2[text()='Top Rated Real Languages']"));
        actualResult.add(topRatedRealText.getText());

        WebElement topRatedEsoteric = getDriver().findElement(By.xpath("//a[text()='Top Rated Esoteric']"));
        topRatedEsoteric.click();
        WebElement topRatedEsotericText = getDriver().findElement(By.xpath("//h2[text()='Top Rated Esoteric Languages']"));
        actualResult.add(topRatedEsotericText.getText());

        WebElement topRatedAssembly = getDriver().findElement(By.xpath("//a[@href='./toplist_assembly.html']"));
        topRatedAssembly.click();
        WebElement topRatedAssemblyText = getDriver().findElement(By.xpath("//h2[text()='Top Rated Assembly Languages']"));
        actualResult.add(topRatedAssemblyText.getText());

        WebElement topHits = getDriver().findElement(By.xpath("//a[text()='Top Hits']"));
        topHits.click();
        WebElement topHitsText = getDriver().findElement(By.xpath("//h2[text()='Top Hits']"));
        actualResult.add(topHitsText.getText());

        WebElement newLanguages = getDriver().findElement(By.xpath("//a[text()='New Languages this month']"));
        newLanguages.click();
        WebElement newLanguagesText = getDriver().findElement(By.xpath("//h2[text()='New Languages this month']"));
        actualResult.add(newLanguagesText.getText());

        WebElement newComments = getDriver().findElement(By.xpath("//a[text()='New Comments']"));
        newComments.click();
        WebElement newCommentsText = getDriver().findElement(By.xpath("//h2[text()='New Comments']"));
        actualResult.add(newCommentsText.getText());

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testArtyomDulyaProductToTheCart() throws InterruptedException {
        String expectedResult = "Выберите размер";
        getDriver().manage().deleteAllCookies();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
        getDriver().get("https://www.wildberries.ru/");
        Thread.sleep(5000);
        WebElement lineSearch = getDriver().findElement(By.xpath("//input[contains(@id, 'searchInput')]"));
        lineSearch.click();
        lineSearch.sendKeys("джинсы");
        WebElement buttonSearch = getDriver().findElement(By.xpath("//button[@id='applySearchBtn']"));
        clickCustom(buttonSearch);
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        Thread.sleep(5000);
        WebElement jeansBox = getDriver().findElement(By.xpath("//div[@id='c74249381']//div[@class='product-card__price price j-cataloger-price']"));
        WebElement jeans = getDriver().findElement(By.xpath("//div[@id='c74249381']//a[@href='/lk/basket']"));
        js.executeScript("window.scrollBy(0,250)");
        clickCustom(jeansBox);
        clickCustom(jeans);
        WebElement jeansSize = getDriver().findElement(By.xpath("//h2[text()='Выберите размер']"));

        String actualResult = jeansSize.getText();
        Assert.assertEquals(actualResult, expectedResult);
    }
    @Test
    public void serhiiHaponiukOpenWeatherMapGridContainerTest() throws InterruptedException {

        getDriver().get("https://openweathermap.org/");
        String title = getDriver().getTitle();

        Assert.assertEquals(title, "Сurrent weather and forecast - OpenWeatherMap");
        Thread.sleep(5000);

        WebElement celsiusButton = getDriver().findElement(By.xpath
                ("//div[text() = 'Metric: °C, m/s']"));
        WebElement fahrenheitButton = getDriver().findElement(By.xpath
                ("//div[text() = 'Imperial: °F, mph']"));
        WebElement temperature = getDriver().findElement(By.className("heading"));
        Assert.assertTrue(temperature.getText().contains("C"));
        celsiusButton.isDisplayed();
        fahrenheitButton.click();
        Assert.assertTrue(temperature.getText().contains("F"));
        celsiusButton.click();
        Assert.assertTrue(temperature.getText().contains("C"));
    }
    @Test
    public void testRykovaOW() throws InterruptedException {

        String expectedResult = "Partners and solutions";

        getDriver().get("https://openweathermap.org/");
        Thread.sleep(6000);
        WebElement partners = getDriver().findElement(By.xpath("//a [text()='Partners']"));
        partners.click();

        WebElement text = getDriver().findElement(By.xpath("//h1 [text()='Partners and solutions']"));
        String actualResult = text.getText();

        Assert.assertEquals(actualResult,expectedResult);
    }
    @Test
    public void testAbramova() {

        String expectedResult = "Welcome to 99 Bottles of Beer";

        getDriver().get("https://www.99-bottles-of-beer.net/lyrics.html");

        WebElement click = getDriver().findElement(By.xpath("//ul[@id='menu']//a[@href='/']"));
        click.click();
        WebElement res = getDriver().findElement(By.xpath("//div[@id='main']/h2"));
        String actualResult = res.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

}
