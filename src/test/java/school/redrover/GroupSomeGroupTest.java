package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class GroupSomeGroupTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void init() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        driver = new ChromeDriver(chromeOptions);
        wait=new WebDriverWait(driver, Duration.ofSeconds(2));
    }

    @AfterTest
    public void end() {
        driver.quit();
    }

    @Test
    public void testEmailContactPage() {
        driver.get("https://askomdch.com/");
        driver.findElement(By.cssSelector("#ast-desktop-header  a[href$='/contact-us/']")).click();

        WebElement email = driver.findElement(
                By.xpath("//p[contains(text(), 'Email')]/strong"));

        Assert.assertEquals(email.getText(), "askomdch@gmail.com");
    }

    @Test
    public void testVerifyTitle() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("http://w3schools.com");

        WebElement textBox = driver.findElement(By.id("search2"));
        WebElement submitButton = driver.findElement(By.cssSelector("button"));

        textBox.sendKeys("JS");
        submitButton.click();

        driver.manage().window().maximize();
        String title = driver.getTitle();

        Assert.assertEquals(title, "JavaScript Tutorial");
        driver.quit();
    }

    @Test
    public void testCartCounter() {
        driver.get("https://askomdch.com/");
        driver.findElement(By.cssSelector("#ast-desktop-header  a[href$='/store/']")).click();

        driver.findElement(
                By.xpath("//a[@href='?add-to-cart=1198']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".ast-site-header-cart-data  ul")));
        WebElement counter = driver.findElement(By.xpath("//div[@id='ast-desktop-header']//span[@class='count']"));
        Assert.assertEquals(counter.getText().trim(), "1");
    }
}