package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import org.testng.annotations.Ignore;

import java.util.concurrent.TimeUnit;

public class BestAutoTeamTest {
    public static final long IMPLICITLYWAIT = 15;
    public static final long PAGELOADTIMEOUT = 15;
    public static final long SCRIPTRUMTIMEOUT = 15;

    protected WebDriver driver;
    @Ignore
    @BeforeTest
    public void beforeTest() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        driver = new ChromeDriver(chromeOptions);
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(IMPLICITLYWAIT,
                TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(PAGELOADTIMEOUT,
                TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(SCRIPTRUMTIMEOUT,
                TimeUnit.SECONDS);
    }
    @Ignore
    @AfterTest
    public void afterTest() throws InterruptedException {
        driver.quit();
    }
    @Ignore
    @Test
    public void testFirst() throws InterruptedException {

        driver.get("https://www.google.com/");

        WebElement textBox = driver.findElement(By.name("q"));

        textBox.sendKeys("selenium");
        textBox.sendKeys(Keys.RETURN);

        WebElement text = driver.findElement(By.xpath("//h3[text() = 'Selenium']"));

        Assert.assertEquals(text.getText(), "Selenium");

    }
    @Ignore
    @Test
    public void testLoginButton() throws InterruptedException {

        driver.get("https://monkkee.com/en");

        WebElement Button = driver.findElement(By.xpath("//div[@class='user-menu']/a[@href='/app/']"));
        Button.click();

        WebElement Header = driver.findElement(By.xpath("//h1[@class='login__heading']"));

        Assert.assertEquals(Header.getText(), "Login");
    }

    @Test
    public void testDonation5EurButton() throws InterruptedException {

        driver.get("https://monkkee.com/en");

        WebElement Button = driver.findElement(By.xpath("//*[contains(@class, 'btn btn-primary home__donation-form-amount-btn') and text() = '5 EUR']"));
        Button.click();

        WebElement element = driver.findElement(By.xpath("//*[@class='form-control home__donation-form-amount-input']"));

        Assert.assertEquals(element.getAttribute("value"), "5");
    }
    @Test
    public void testDonation10EurButton() throws InterruptedException {

        driver.get("https://monkkee.com/en");

        WebElement Button = driver.findElement(By.xpath("//*[contains(@class, 'btn btn-primary home__donation-form-amount-btn') and text() = '10 EUR']"));
        Button.click();

        WebElement element = driver.findElement(By.xpath("//*[@class='form-control home__donation-form-amount-input']"));

        Assert.assertEquals(element.getAttribute("value"), "10");
    }
}

