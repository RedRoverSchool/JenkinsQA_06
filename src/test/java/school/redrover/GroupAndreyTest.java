package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static org.testng.Assert.assertEquals;

public class GroupAndreyTest {
    @Test
    public void testAndreyD() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://openweathermap.org/");

        Thread.sleep(5000);

        WebElement button = driver.findElement(By.xpath("//*[@id=\"desktop-menu\"]/ul/li[1]/a"));
        button.click();

        assertEquals(driver.getTitle(), "OpenWeatherMap API guide - OpenWeatherMap");

        driver.quit();
    }

    @Test
    public void testArtemTextOnSuggestsButton() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://openweathermap.org/");

        Thread.sleep(5000);

        WebElement textBox = driver.findElement(By.name("q"));
        textBox.sendKeys("omsk");
        textBox.sendKeys(Keys.RETURN);

        Thread.sleep(5000);

        WebElement suggestButton = driver.findElement(By.xpath("//*[@class=\"tab-pane active\"]/div/table/tbody/tr/td/b/a"));
        WebElement suggestButton2 = driver.findElement(By.xpath("//*[@id=\"forecast_list_ul\"]/table/tbody/tr[2]/td[2]/b[1]/a"));

        assertEquals(suggestButton.getText(), "Omsk, RU");
        assertEquals(suggestButton2.getText(), "Omskaya Oblast’, RU");

        driver.quit();
    }

    @Test
    public void testArtemTextOnCityPage() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://openweathermap.org/");

        Thread.sleep(5000);

        WebElement textBox = driver.findElement(By.name("q"));
        textBox.sendKeys("omsk");
        textBox.sendKeys(Keys.RETURN);

        Thread.sleep(5000);

        WebElement button = driver.findElement(By.xpath("//*[@class=\"tab-pane active\"]/div/table/tbody/tr/td/b/a"));
        assertEquals(button.getText(), "Omsk, RU");
        button.click();

        Thread.sleep(5000);

        WebElement text = driver.findElement(By.xpath("//*[@class=\"grid-container grid-4-5\"]/div/div/h2"));

        assertEquals(text.getText(), "Omsk, RU");

        driver.quit();
    }
    @Ignore
    @Test
    public void testArtemLocalDateOnCityPage() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://openweathermap.org/");

        Thread.sleep(5000);

        WebElement textBox = driver.findElement(By.name("q"));
        textBox.sendKeys("omsk");
        textBox.sendKeys(Keys.RETURN);

        Thread.sleep(5000);

        WebElement button = driver.findElement(By.xpath("//*[@class=\"tab-pane active\"]/div/table/tbody/tr/td/b/a"));
        assertEquals(button.getText(), "Omsk, RU");
        button.click();

        Thread.sleep(5000);

        WebElement text = driver.findElement(By.xpath("//*[@class=\"grid-container grid-4-5\"]/div/div/span"));

        Date currentDate = new Date();
        SimpleDateFormat formatDate = new SimpleDateFormat("MMM d, hh:mma", Locale.US);
        formatDate.setTimeZone(TimeZone.getTimeZone("GMT+06"));
        String expectedDate = formatDate.format(currentDate).replace("AM", "am").replace("PM", "pm");

        assertEquals(text.getText(), expectedDate);

        driver.quit();
    }

    @Test
    public void testArtemSliderMove() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        WebElement slider = driver.findElement(By.xpath("/html/body/main/div/form/div/div[3]/label[3]/input"));

        Actions move = new Actions(driver);
        move.moveToElement(slider).clickAndHold().moveByOffset(25, 0).release().perform();

        Thread.sleep(3500);

        WebElement button = driver.findElement(By.xpath("/html/body/main/div/form/div/div[2]/button"));
        button.click();

        Thread.sleep(3500);

        String url = driver.getCurrentUrl();
        int expected;
        if (url.contains("my-range=6")) {
            expected = 1;
        } else {
            expected = 2;
        }

        assertEquals(expected, 1);

        driver.quit();
    }
}

