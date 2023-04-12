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

import java.time.Duration;

public class JavaNiSyTest {
    @Ignore
    @Test
    public void testNewsPageMS()  {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver browser = new ChromeDriver(chromeOptions);

        browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        browser.get("https://www.livelib.ru/news/post/118924-derzhim-v-kurse-tekuschij-status-rabot-i-problemy-na-sajte");

        WebElement btnNews = browser.findElement(By.cssSelector( "[class=\"header-card-user__class\"] b"));
        btnNews.click();

        WebElement textNews = browser.findElement(By.cssSelector(".lists h1:nth-child(2"));

        Assert.assertEquals(textNews.getText(), "Новости");
        browser.quit();
    }

    @Ignore
    @Test
    public void testButtonsMS() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver browser = new ChromeDriver(chromeOptions);

        browser.get("https://demoqa.com/radio-button");
        WebElement radioBtn = browser.findElement(By.cssSelector("[for='yesRadio']"));
        radioBtn.click();

        Assert.assertEquals(browser.findElement(By.cssSelector(".mt-3")).getText(), "You have selected Yes");
        browser.quit();
    }

    @Test
    public void testUscis() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver browser = new ChromeDriver(chromeOptions);

        browser.get("https://www.uscis.gov/");
        WebElement textBox = browser.findElement(By.id("gsc-i-id1"));

        textBox.sendKeys("citizenship and naturalization");
        textBox.sendKeys(Keys.RETURN);

        Thread.sleep(2000);

        WebElement button = browser.findElement(By.name("Search"));
        button.click();

        WebElement text = browser.findElement(By.className("gsc-selected-option"));

        Assert.assertEquals(text.getText(), "Relevance");

        browser.quit();
    }
    @Test
    public void testGoogleTransl() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://www.google.com/");

        WebElement textBox = driver.findElement(By.name("q"));
        textBox.sendKeys("гугл переводчик");
        textBox.sendKeys(Keys.RETURN);

        Thread.sleep(2000);

        WebElement text =  driver.findElement(By.xpath("//h3[@class='LC20lb MBeuO DKV0Md']"));

        Assert.assertEquals(text.getText(),"Google Переводчик");

        driver.quit();
    }

    @Test
    public void testSignUpPlotnikov() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("http://shop.bugred.ru/user/register/index");

        WebElement inputName = driver.findElement(By.id("exampleInputName"));
        inputName.sendKeys("Ivan");

        WebElement inputEmail = driver.findElement(By.id("exampleInputEmail1"));
        inputEmail.sendKeys("test10@mail.ru");                                       // При каждом запуске необходимо изменить почту

        WebElement inputPassword1 = driver.findElement(By.id("exampleInputPassword1"));
        inputPassword1.sendKeys("123@@#aA");

        WebElement inputPassword2 = driver.findElement(By.id("exampleInputPassword2"));
        inputPassword2.sendKeys("123@@#aA");

        WebElement button = driver.findElement(By.name("_csrf"));
        button.click();

        Thread.sleep(2000);

        WebElement text = driver.findElement(By.xpath("//p[text() = 'Пользователь с таким email уже зарегистрирован!']"));
        Thread.sleep(2000);
        Assert.assertEquals(text.getText() ,"Пользователь с таким email уже зарегистрирован!");

        driver.quit();

    }

    @Test
    public void testGetMessageDifferentPasswordPlotnikov() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("http://shop.bugred.ru/user/register/index");

        WebElement inputName = driver.findElement(By.id("exampleInputName"));
        inputName.sendKeys("Ivan");

        WebElement inputEmail = driver.findElement(By.id("exampleInputEmail1"));
        inputEmail.sendKeys("test12@mail.ru");                                       // При каждом запуске необходимо изменить почту

        WebElement inputPassword1 = driver.findElement(By.id("exampleInputPassword1"));
        inputPassword1.sendKeys("123@@#aA");

        WebElement inputPassword2 = driver.findElement(By.id("exampleInputPassword2"));
        inputPassword2.sendKeys("321@@#aA");

        WebElement button = driver.findElement(By.name("_csrf"));
        button.click();

        Thread.sleep(2000);

        WebElement text = driver.findElement(By.xpath("//p[text() = 'Пароль и повтор пароля не равны!']"));
        Thread.sleep(2000);
        Assert.assertEquals(text.getText() ,"Пароль и повтор пароля не равны!");

        driver.quit();

    }
}

