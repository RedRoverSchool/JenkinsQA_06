package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;

public class CaramelSyrupForJava {

    @Test
    public void artyomDulyaOpenWeatherGuideClick() throws InterruptedException {

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
    public void artyomDulyaThehostbest() throws InterruptedException {

        String expectedResult = "https://thehostbest.ru/business-card-site/";

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        driver.get("https://thehostbest.ru/");

        WebElement button = driver.findElement(By.xpath("//div[@data-id='ee672f3']//div//div//a"));
        js.executeScript("window.scrollBy(0,200)");
        button.click();

        String actualResult = driver.getCurrentUrl();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    @Test
    public void artyomDulyaSearchLineHeader() throws InterruptedException {

        String expectedResult = "Paris, FR";

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
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
    public static void artyomDulyaHeaderButtonTest() throws InterruptedException {

        int expectedResult = 3;

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();

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

    @Test
    public void artyomDulyaSupportMenuButtonTest() throws InterruptedException {

        String expectedResultFAQ = "https://openweathermap.org/faq";
        String expectedResultHowToStart = "https://openweathermap.org/appid";
        String expectedResultAskAQuestion = "https://home.openweathermap.org/questions";

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();

        driver.get("https://openweathermap.org/");
        Thread.sleep(5000);

        WebElement support = driver.findElement(By.xpath("//div[@id='support-dropdown']"));
        WebElement supportFAQ = driver.findElement(By.xpath("//ul[@id='support-dropdown-menu']//li//a[@href='/faq']"));
        WebElement supportHowToStart = driver.findElement(By.xpath("//ul[@id='support-dropdown-menu']//li[2]//a[@href='/appid']"));
        WebElement supportAsk = driver.findElement(By.xpath("//ul[@id='support-dropdown-menu']" +
                "//li[3]//a[@href='https://home.openweathermap.org/questions']"));

        support.click();
        supportFAQ.click();
        String actualResultFAQ = driver.getCurrentUrl();
        driver.navigate().back();
        support.click();
        supportHowToStart.click();
        String actualResultHowToStart = driver.getCurrentUrl();
        driver.navigate().back();
        support.click();
        supportAsk.click();
        ArrayList<String> windows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(windows.get(1));
        String actualResultAsk = driver.getCurrentUrl();

        Assert.assertEquals(actualResultFAQ, expectedResultFAQ);
        Assert.assertEquals(actualResultHowToStart, expectedResultHowToStart);
        Assert.assertEquals(actualResultAsk, expectedResultAskAQuestion);

        driver.quit();
    }
    @Test
    public void artyomDulyaAuthorizationText() throws InterruptedException {

        String actualResult = "Sign In To Your Account";

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();

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


}
