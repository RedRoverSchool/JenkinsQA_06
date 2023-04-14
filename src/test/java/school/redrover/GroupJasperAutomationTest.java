package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class GroupJasperAutomationTest {
    @Test

        public void footballua() throws InterruptedException {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments( "--headless", "--window-size=1920,1080");

            WebDriver driver = new ChromeDriver(chromeOptions);
            driver.get("https://football.ua/");
            WebElement element = driver.findElement(By.xpath("//*[text()='Україна']"));
            Assert.assertEquals(element.getText(), "Україна");
            driver.quit();
        }


    @Test
    public void testTitle() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://football.ua/");
        Thread.sleep(3000);

        String title = driver.getTitle();
        Assert.assertEquals(title, "Football.ua - Новости футбола - Футбол онлайн - Результаты матчей, трансляции — football.ua");

        driver.quit();
    }

    @Test
    public void testBiletskayaA() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://new.uschess.org/");

        WebElement textLink = driver.findElement(By.className("clo-image"));
        textLink.click();

        Thread.sleep(2000);

        WebElement element = driver.findElement(By.xpath("//*[text()='Search Articles']"));
        Assert.assertEquals(element.getText(), "Search Articles");

        driver.quit();
    }

    @Test
    public void testBUshakov() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://privatbank.ua/");

        WebElement textLink = driver.findElement(By.xpath("//a[contains(text(), 'Відділення')]"));
        textLink.click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://privatbank.ua/map");

        driver.quit();
    }

    @Test
    public void getSite() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://gorodok.ua/");
        Thread.sleep(5000);
        WebElement name = driver.findElement(By.xpath("//*[@id=\"masthead\"]/div[1]/div[4]/ul/li[3]/div/a[2]/span"));
        Assert.assertEquals(name.getText(), "CONTACTS");
        driver.quit();

    }

    @Test
    public void testOlgaMinina() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        WebElement textBox = driver.findElement(By.name("my-text"));
        WebElement submitButton = driver.findElement(By.cssSelector("button"));

        textBox.sendKeys("Selenium");
        submitButton.click();

        WebElement message = driver.findElement(By.id("message"));
        String value = message.getText();
        Assert.assertEquals(value, "Received!");

        driver.quit();
    }

    @Ignore
    @Test
    public void testWHO() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://www.who.int/en");
        WebElement linkToGo = driver.findElement(By.xpath("//a[contains(text(), 'Contact us')]"));
        linkToGo.click();

        Thread.sleep(2000);
        WebElement textBox = driver.findElement(By.xpath("//*[@id=\"PageContent_C038_Col01\"]/div[1]/h2"));
        assertEquals(textBox.getText(),"General enquiries");
    }

    @Test
    public void discordTest() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        driver.get("https://discord.com/");

        WebElement droplist = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@class='arrowIcon-3hG4PK']")));
        droplist.click();

        WebElement choseLeng = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'text-2H5FnP textExtraSmall-2-FKT0 dropdownLanguageName-3GY1QW') " +
                "and contains(text(), 'English, USA')]")));
        choseLeng.click();

        WebElement nitro = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='link-1naFFL link-2eyudH' and @href='/nitro']")));
        nitro.click();

        WebElement droplist1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='selector-language-name']")));
        droplist1.click();
        WebElement choseLeng1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'dropdown-language-name')  and contains(text(), 'English (UK)')]")));
        choseLeng1.click();

        droplist1.click();
        choseLeng1.click();

        Thread.sleep(10000);

        WebElement unlesh = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class = 'new-h1 font-white capital']")));

        Assert.assertEquals(unlesh.getText(), "Unleash more fun with Nitro");
        driver.quit();


    }

}
