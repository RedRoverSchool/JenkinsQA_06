package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.AssertJUnit.assertEquals;

public class GroupILoveBugsTest {
    @Test
    public void ADFirstTest() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://guitar.com/");

        Thread.sleep(2000);

        WebElement latestNews = driver.findElement(By.xpath("//h2[@class='posts-block-title']"));

        Assert.assertEquals(latestNews.getText(), "LATEST NEWS");

        driver.quit();
    }

    @Test
    public void SteamTest(){
        final String MAIN_PAGE = "https://store.steampowered.com/";
        final By LOGIN_BUTTON = By.xpath("//a[@class='global_action_link']");
        final By SIGN_IN_BUTTON = By.cssSelector(".newlogindialog_SubmitButton_2QgFE");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));

        driver.get(MAIN_PAGE);
        Assert.assertEquals(driver.getCurrentUrl(), MAIN_PAGE, "The page opened is not 'store.steampowered.com' or the URL is invalid");

        wait.until(ExpectedConditions.elementToBeClickable(LOGIN_BUTTON));
        driver.findElement(LOGIN_BUTTON).click();

        wait.until(ExpectedConditions.elementToBeClickable(SIGN_IN_BUTTON));
        Assert.assertTrue(driver.findElement(SIGN_IN_BUTTON).isDisplayed(), "The bottom 'Sign In' is not displayed.");

        driver.quit();
    }
    @Test
    public void swagLabsTest() {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.saucedemo.com/");


        WebElement nameInput = driver.findElement(By.xpath("//*[@placeholder = 'Username']"));
        nameInput.sendKeys("standard_user");

        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("secret_sauce");

        WebElement loginButton = driver.findElement(By.name("login-button"));
        loginButton.click();

        WebElement firstItem = driver.findElement(By.xpath("//*[text() = 'Sauce Labs Backpack']"));
        String firstItemName = firstItem.getText();

        Assert.assertEquals(firstItemName, "Sauce Labs Backpack", "First Item is not Sauce Labs Backpack");

        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        addToCartButton.click();

        WebElement cartButton = driver.findElement(By.className("shopping_cart_link"));
        cartButton.click();

        WebElement cartItem = driver.findElement(By.className("inventory_item_name"));
        String cartItemName = cartItem.getText();

        Assert.assertEquals(cartItemName, "Sauce Labs Backpack");

        driver.quit();
    }
    @Test
    public void eightComponentsTest() {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        String title = driver.getTitle();
        assertEquals("Web form", title);

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement textBox = driver.findElement(By.name("my-text"));
        WebElement submitButton = driver.findElement(By.cssSelector("button"));

        textBox.sendKeys("Selenium");
        submitButton.click();

        WebElement message = driver.findElement(By.id("message"));
        String value = message.getText();
        assertEquals("Received!", value);

        driver.quit();
    }
}
