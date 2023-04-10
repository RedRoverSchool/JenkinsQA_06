package school.redrover;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;

public class GroupJavaJitsu {

    @Test
    public void testCarServiceOptions(){

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.homesteadhyundai.net/");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

        WebElement serviceMenu = driver.findElement(By.xpath("//span[text()='Service/Parts']"));
        serviceMenu.click();

        List<WebElement> options = serviceMenu.findElements(By.tagName("li"));
        Assert.assertEquals(options.size(), 14);

        driver.quit();
    }

    @Test
    public void testAriumFindACommunity() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://ariumliving.com/");

        WebElement inputElement = driver.findElement(By.name("search"));
        inputElement.sendKeys("Arium Seaglass");
        inputElement.sendKeys(Keys.ENTER);
        Assert.assertEquals(driver.getCurrentUrl(), "https://ariumliving.com/find-a-community/?search=Arium+Seaglass");

        driver.quit();

    }

    @Test
    public void tema_openCartRegistrationTest() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://demo.opencart.com/index.php?route=account/register&language=en-gb");
        driver.findElement(By.id("input-firstname")).sendKeys("Tema");
        driver.findElement(By.id("input-lastname")).sendKeys("Shvets");
        driver.findElement(By.id("input-email")).sendKeys("temaemail123@gmail.com");
        driver.findElement(By.id("input-password")).sendKeys("123xyz");
        driver.quit();
    }
    @Test
    public void testFirst_RedRover() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://redrover.school");
        Thread.sleep(2000);

        WebElement button = driver.findElement(By.linkText("JOIN US"));
        button.click();

        WebElement textBoxEmail = driver.findElement(By.name("email"));
        textBoxEmail.sendKeys("test@gmail");

        WebElement textBoxName = driver.findElement(By.name("name"));
        textBoxName.sendKeys("Test");

        WebElement checkBox = driver.findElement(By.className("t-checkbox__indicator"));
        Thread.sleep(2000);
        checkBox.click();

        WebElement buttonW = driver.findElement(By.className("t-submit"));
        buttonW.click();
        Thread.sleep(2000);

        WebElement error = driver.findElement(By.className("t-input-error"));
        Assert.assertEquals(error.getText(), "Please enter a valid email address");
        driver.quit();
    }
    @Test
    public void testJenkins() {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.jenkins.io/");

        WebElement But = driver.findElement(By.linkText("Documentation"));
        But.click();

        WebElement But1 = driver.findElement(By.linkText("Installing Jenkins"));
        But1.click();

        WebElement But3 = driver.findElement(By.linkText("Windows"));
        But3.click();

        WebElement text = driver.findElement(By.className("hdlist1"));

        Assert.assertEquals(text.getText(),"Step 1: Setup wizard");
        driver.quit();
    }
}
