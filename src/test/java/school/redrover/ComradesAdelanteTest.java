package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.List;

public class ComradesAdelanteTest {

    @Ignore
    @Test
    public void testMap() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://www.google.com//");

        WebElement textBox = driver.findElement(By.className("gLFyf"));
        textBox.sendKeys("гугл карты");
        textBox.sendKeys(Keys.RETURN);

        WebElement search = driver.findElement(By.className("qLRx3b"));

        search.click();

        Thread.sleep(2000);
        WebElement searchBox = driver.findElement(
                By.xpath("//input[@id='searchboxinput']"));
        searchBox.sendKeys("Бердск");
        searchBox.sendKeys(Keys.RETURN);
        Thread.sleep(5000);

        WebElement text = driver.findElement(By.xpath("//*[@id='QA0Szd']/div/div/div[1]/div[2]/div/div[1]/div/div/div[2]/div/div[1]/div[1]/h1"));

        String expectedResult = "Бердск";
        String actualResult = text.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    @Ignore
    @Test
    public void testHeaderOpenWeather() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(
                "--remote-allow-origins=*", "--headless", "--window-size=1920,1080"
        );
        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://openweathermap.org");

        WebElement textHeader = driver.findElement(
                By.xpath("//h1/span[@class='white-text']")
        );
        Thread.sleep(1500);

        String expectedResult = "OpenWeather";
        String actualResult = textHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }
    @Ignore
    @Test
    public void testHeaderSignInPage() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(
                "--remote-allow-origins=*", "--headless", "--window-size=1920,1080"
        );
        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://openweathermap.org");

        WebElement linkSignIn = driver.findElement(
                By.xpath("//div/ul/li[@class='user-li']")
        );
        linkSignIn.click();
        Thread.sleep(1500);

        WebElement textHeader = driver.findElement(
                By.xpath("//h3[@class='first-child']")
        );
        Thread.sleep(500);

        String expectedResult = "Sign In To Your Account";
        String actualResult = textHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    @Test
    public void MariaLuchnikovaTest() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.saucedemo.com");

        WebElement login = driver.findElement(By.id("user-name"));
        login.sendKeys("standard_user");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("secret_sauce");

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        //Thread.sleep(3000);

        WebElement Backpack = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        Backpack.click();

        WebElement tShirt = driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt"));
        tShirt.click();

        WebElement onesie = driver.findElement(By.id("add-to-cart-sauce-labs-onesie"));
        onesie.click();

        WebElement cart = driver.findElement(By.className("shopping_cart_link"));
        cart.click();

        List<WebElement> items = driver.findElements(By.className("cart_item"));

        int countItems = items.size();
        Assert.assertEquals(countItems, 3);

        driver.quit();

    }
}
