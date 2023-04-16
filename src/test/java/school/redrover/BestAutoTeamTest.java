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


import java.util.List;
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

    @Test
    public void findLinkOfBlog() throws InterruptedException {

        driver.get("https://monkkee.com/en");
        WebElement link = driver.findElement(By.xpath("//div[@class='col-sm-4'][2]//ul//li[2]"));
        Assert.assertEquals(link.getText(), "Blog", "Link is not found");
    }

    @Test
    public void countOgArticles() throws InterruptedException {

        driver.get("https://monkkee.com/en");
        WebElement link = driver.findElement(By.xpath("//div[@class='col-sm-4'][2]//ul//li[2]"));
        link.click();
        WebElement article = driver.findElement(By.xpath("//h2[@class='blog__heading']"));
        List<WebElement> articles = driver.findElements(By.xpath("//h2[@class='blog__heading']"));
        int countOfArticles = articles.size();
        Assert.assertEquals(countOfArticles, 27, "Count is different");
    }

    @Test
    public void clickSupportLink() throws InterruptedException {

        driver.get("https://monkkee.com/en");
        WebElement link = driver.findElement(By.xpath("//div[@class='col-sm-4'][1]//ul//li[2]"));
        link.click();
        WebElement knowlegeLink = driver.findElement(By.xpath("//h3//a"));
        knowlegeLink.click();
        WebElement firstLanguage = driver.findElement(By.xpath("//span//a[1]"));
        List<WebElement> languages = driver.findElements(By.xpath("//span//a"));
        String actualFirstLanguage = languages.get(0).getText();
        String expectedFirstLanguage = firstLanguage.getText();
        Assert.assertEquals(actualFirstLanguage, expectedFirstLanguage, "There are different languages");
    }

    @Test
    public void registerNewUserWithInvalidPasswordConfirmation() throws InterruptedException {

        driver.get("https://monkkee.com/en");
        WebElement registerButton = driver.findElement(By.xpath("//div[@id='about']//div//a"));
        registerButton.click();
        WebElement emailInput = driver.findElement(By.xpath("//input[@id='registration_email']"));
        emailInput.sendKeys("ooo@com.ru");
        WebElement passwordInput = driver.findElement(By.xpath("//input[@id='registration_password']"));
        passwordInput.sendKeys("12345678Olja");
        WebElement passwordConfirmInput = driver.findElement(By.xpath("//input[@id='registration_password_confirmation']"));
        passwordConfirmInput.sendKeys("1234567Olja");
        WebElement passwordHintInput = driver.findElement(By.xpath("//input[@id='registration_password_hint']"));
        passwordHintInput.sendKeys("1");
        WebElement firstCheckbox = driver.findElement(By.xpath("//div[@class=' form-group'][4]//input[2]"));
        firstCheckbox.click();
        WebElement secondCheckbox = driver.findElement(By.xpath("//div[@class=' form-group'][5]//input[2]"));
        secondCheckbox.click();
        WebElement okButton = driver.findElement(By.xpath("//div[@class='form-group'][2]//button"));
        okButton.click();
        WebElement message = driver.findElement(By.xpath("//div[@class='alert alert-danger']"));
        Assert.assertEquals(message.getText(), "Registration not successful", "There isn't massage");

    }
}

