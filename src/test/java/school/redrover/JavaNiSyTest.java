package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class JavaNiSyTest {
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
}

