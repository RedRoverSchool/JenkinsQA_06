package school.redrover.old;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;
@Ignore
public class HelloWorldTest extends BaseTest {

    @Ignore
    @Test
    public void testOnlinerLogo() {
        getDriver().get("https://catalog.onliner.by/");
        WebElement logo = getDriver().findElement(By.className("onliner_logo"));
        logo.click();
        String url = getDriver().getCurrentUrl();
        Assert.assertEquals(url, "https://www.onliner.by/");
    }

    @Test
    public void wikiSeleniumTest() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://ru.wikipedia.org/");
        WebElement search = driver.findElement(By.xpath("//*[@id='searchInput']"));
        search.sendKeys("selenium");

        WebElement searchBtn = driver.findElement(By.xpath("//*[@id='searchButton']"));
        searchBtn.click();
        WebElement selPage = driver.findElement(By.xpath("//*[@id='firstHeading']/span"));

        Assert.assertEquals(selPage.getText(), "Selenium");
        driver.quit();
    }

    @Test

    public void wikTest() {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.wikipedia.org/");
        WebElement textBox = driver.findElement(By.name("search"));
        textBox.sendKeys("Xpath");
        textBox.sendKeys(Keys.RETURN);

        WebElement text = driver.findElement(By.xpath("//*[@id=\"firstHeading\"]/span"));
        Assert.assertEquals(text.getText(), "XPath");

        driver.quit();
    }

    @Test
    public void firstTest() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        //chromeOptions.addArguments("--remote-allow-origins=*", "--start-maximized");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://askomdch.com/");
        Assert.assertEquals(driver.getCurrentUrl(), "https://askomdch.com/");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[@class=\"wp-block-button__link\"]")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://askomdch.com/store");

        Thread.sleep(3000);
        driver.quit();
    }

    @Test
    public void testZoolegendaTitle() throws InterruptedException {
        getDriver().get("https://zoolegenda.ru/");
        WebElement button = getDriver().findElement(By.cssSelector("[href='/catalog/koshki']"));
        Thread.sleep(2000);
        button.click();

        WebElement text = getDriver().findElement(By.tagName("h1"));
        Assert.assertEquals(text.getText(), "КОШКИ");
    }

    @Ignore
    @Test
    public void youtubeTest() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(options);

        driver.get("https://www.youtube.com");
        driver.findElement(By.xpath("//input[@id='search']")).sendKeys("Nyan Cat");
        driver.findElement(By.xpath("//button[@id='search-icon-legacy']")).click();

        Thread.sleep(2000);

        String actualText = driver.findElement(By.xpath("//h3[contains(@class, 'title')]")).getText();
        Assert.assertEquals(actualText, "Nyan Cat [original]");
        driver.quit();
    }

    @Test
    public void testSearchTitle() {

        getDriver().get("https://askomdch.com/");
        WebElement shopNowButton = getDriver().findElement(By.xpath("//*[contains(text(),'Shop Now')]"));
        shopNowButton.click();
        WebElement searchField = getDriver().findElement(By.xpath("//input"));
        searchField.sendKeys("shirt");
        searchField.sendKeys(Keys.ENTER);
        WebElement searchResultTitle = getDriver().findElement(By.className("woocommerce-products-header"));
        Assert.assertEquals(searchResultTitle.getText(), "Search results: “shirt”");
    }

    @Test
    public void testYandexSignInError() throws InterruptedException {
        getDriver().get("https://passport.yandex.ru/registration?retpath=https%3A%2F%2Fsso.passport.yandex.ru%2Fprepare%3Fuuid%3Db258193d-7833-453a-882c-4c071ee1a3a9%26goal%3Dhttps%253A%252F%252Fya.ru%252F%26finish%3Dhttps%253A%252F%252Fpassport.yandex.ru%252F&process_uuid=6e0d6f36-3d71-4915-bbc6-18cf579d4abe");
        Thread.sleep(1000);

        WebElement firstname = getDriver().findElement(By.cssSelector("[data-t=\"field:input-firstname\"]"));
        firstname.sendKeys(" " + "\n");

        WebElement error = getDriver().findElement(By.cssSelector("[class=\"error-message\"]"));
        Thread.sleep(1000);
        Assert.assertEquals(error.getText(), "Пожалуйста, укажите имя");

        WebElement lastname = getDriver().findElement(By.cssSelector("[data-t=\"field:input-lastname\"]"));
        lastname.sendKeys(" ");
        lastname.click();

        WebElement error2 = getDriver().findElement(By.cssSelector("[class=\"error-message\"]"));
        Thread.sleep(1000);
        Assert.assertEquals(error2.getText(), "Пожалуйста, укажите фамилию");

        WebElement login = getDriver().findElement(By.cssSelector("[data-t=\"field:input-login\"]"));
        login.sendKeys(" ");
        login.click();
        Thread.sleep(1000);

        WebElement error3 = getDriver().findElement(By.cssSelector("[data-t=\"login-error\"]"));
        Assert.assertEquals(error3.getText(), "Необходимо выбрать логин");
    }

    @Test
    public void TestSlackSignupErrorAleksE() {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        driver.get("https://www.slack.com/");
        driver.findElement(By.xpath("//header//a[text()='Try for free']")).click();

        driver.findElement(By.xpath("//button[text()='Continue']")).click();

        WebElement error = driver.findElement(By.xpath("//div[@id='creator_signup_email_error']/span"));
        Assert.assertEquals(error.getText(), "This is required — you’ll need to enter an email.");

    }

    @Test
    public void testPageTitle() throws InterruptedException {
        getDriver().get("https://www.wikipedia.org/");

        WebElement searchField = getDriver().findElement(By.name("search"));
        searchField.sendKeys("API");
        searchField.sendKeys(Keys.RETURN);

        Thread.sleep(3000);

        WebElement part = getDriver().findElement(By.xpath("//span[@class = \"mw-page-title-main\"]"));
        Assert.assertEquals(part.getText(), "API");
    }

    @Ignore
    @Test
    public void testDoubleClickButton() throws InterruptedException {
        getDriver().get("https://demoqa.com/");

        WebElement fieldElements = getDriver().findElement(By.xpath("//h5[text() = 'Elements']"));
        fieldElements.click();

        WebElement fieldButtons = getDriver().findElement(By.xpath("//span[text() = 'Buttons']"));
        fieldButtons.click();

        WebElement dblClickBtn = getDriver().findElement(By.xpath("//button[text() = 'Double Click Me']"));
        Actions action = new Actions(getDriver());
        Actions dblClick = action.doubleClick(dblClickBtn);
        dblClick.perform();

        Thread.sleep(1000);

        WebElement dblClickMessage = getDriver().findElement(By.xpath("//p[text() = 'You have done a double click']"));
        Assert.assertEquals(dblClickMessage.getText(), "You have done a double click");
    }
}
