package school.redrover.old;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;

import static org.testng.AssertJUnit.assertEquals;
@Ignore
public class GroupILoveBugsTest extends BaseTest {
    WebDriver driver;
    Faker faker = new Faker();
    private String firstName = faker.internet().uuid();
    private String lastName = faker.internet().uuid();
    private String postCode = faker.address().zipCode();

    private static final By firstNameField = By.xpath("//input[@placeholder='First Name']");
    private static final By lastNameField = By.xpath("//input[@placeholder='Last Name']");
    private static final By postCodeField = By.xpath("//input[@placeholder='Post Code']");
    private static final By homeButton = By.xpath("//*[@ng-click='home()']");
    private static final By addCustomerRegistrationButton = By.xpath("//button[@type='submit']");

    @Test
    public void testAdFirst() throws InterruptedException {

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
    public void testSteam() {
        final String MAIN_PAGE = "https://store.steampowered.com/";
        final By LOGIN_BUTTON = By.xpath("//a[@class='global_action_link']");
        final By SIGN_IN_BUTTON = By.cssSelector(".newlogindialog_SubmitButton_2QgFE");

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(4));

        getDriver().get(MAIN_PAGE);
        Assert.assertEquals(getDriver().getCurrentUrl(), MAIN_PAGE, "The page opened is not 'store.steampowered.com' or the URL is invalid");

        wait.until(ExpectedConditions.elementToBeClickable(LOGIN_BUTTON));
        getDriver().findElement(LOGIN_BUTTON).click();

        wait.until(ExpectedConditions.elementToBeClickable(SIGN_IN_BUTTON));
        Assert.assertTrue(getDriver().findElement(SIGN_IN_BUTTON).isDisplayed(), "The bottom 'Sign In' is not displayed.");
    }

    @Test
    public void testSwagLabs() {
        getDriver().get("https://www.saucedemo.com/");

        WebElement nameInput = getDriver().findElement(By.xpath("//*[@placeholder = 'Username']"));
        nameInput.sendKeys("standard_user");

        WebElement passwordInput = getDriver().findElement(By.id("password"));
        passwordInput.sendKeys("secret_sauce");

        WebElement loginButton = getDriver().findElement(By.name("login-button"));
        loginButton.click();

        WebElement firstItem = getDriver().findElement(By.xpath("//*[text() = 'Sauce Labs Backpack']"));
        String firstItemName = firstItem.getText();

        Assert.assertEquals(firstItemName, "Sauce Labs Backpack", "First Item is not Sauce Labs Backpack");

        WebElement addToCartButton = getDriver().findElement(By.id("add-to-cart-sauce-labs-backpack"));
        addToCartButton.click();

        WebElement cartButton = getDriver().findElement(By.className("shopping_cart_link"));
        cartButton.click();

        WebElement cartItem = getDriver().findElement(By.className("inventory_item_name"));
        String cartItemName = cartItem.getText();

        Assert.assertEquals(cartItemName, firstItemName);
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

    @Test
    public void testAddCastomerGlobalsqa() throws InterruptedException {
        getDriver().get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager/addCust");
        Thread.sleep(1000);

        fillField(firstName, firstNameField);
        fillField(lastName, lastNameField);
        fillField(postCode, postCodeField);
        getDriver().findElement(addCustomerRegistrationButton).click();

        getDriver().switchTo().alert().accept();
        getDriver().findElement(homeButton).click();
    }

    private void fillField(String userData, By locator) {
        getDriver().findElement(locator).click();
        getDriver().findElement(locator).sendKeys(userData);
    }
}
