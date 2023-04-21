package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import school.redrover.runner.BaseTest;


import java.util.List;
import java.util.concurrent.TimeUnit;

public class BestAutoTeamTest extends BaseTest {
    public static final long IMPLICITLY_WAIT = 15;
    public static final long PAGE_LOAD_TIMEOUT = 15;
    public static final long SCRIPT_RUN_TIMEOUT = 15;

    private WebDriver driver;

    @BeforeMethod
    protected void beforeTest() throws InterruptedException {
        driver = getDriver();
        getDriver().manage().deleteAllCookies();
        getDriver().manage().timeouts().implicitlyWait(IMPLICITLY_WAIT,
                TimeUnit.SECONDS);
        getDriver().manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT,
                TimeUnit.SECONDS);
        getDriver().manage().timeouts().setScriptTimeout(SCRIPT_RUN_TIMEOUT,
                TimeUnit.SECONDS);
    }


    @Ignore
    @Test
    public void testFirst() throws InterruptedException {

        getDriver().get("https://www.google.com/");

        WebElement textBox = getDriver().findElement(By.name("q"));

        textBox.sendKeys("selenium");
        textBox.sendKeys(Keys.RETURN);

        WebElement text = getDriver().findElement(By.xpath("//h3[text() = 'Selenium']"));

        Assert.assertEquals(text.getText(), "Selenium");
    }


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

        getDriver().get("https://monkkee.com/en");

        WebElement Button = getDriver().findElement(
                By.xpath("//*[contains(@class, 'btn btn-primary home__donation-form-amount-btn') and text() = '5 EUR']"));
        Button.click();

        WebElement donationFormAmount = getDriver().findElement(
                By.xpath("//*[@class='form-control home__donation-form-amount-input']"));

        Assert.assertEquals(donationFormAmount.getAttribute("value"), "5");
    }


    @Test
    public void testDonation10EurButton() throws InterruptedException {

        getDriver().get("https://monkkee.com/en");

        WebElement Button = getDriver().findElement(
                By.xpath("//*[contains(@class, 'btn btn-primary home__donation-form-amount-btn') and text() = '10 EUR']"));
        Button.click();

        WebElement donationFormAmount = getDriver().findElement(
                By.xpath("//*[@class='form-control home__donation-form-amount-input']"));

        Assert.assertEquals(donationFormAmount.getAttribute("value"), "10");
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

