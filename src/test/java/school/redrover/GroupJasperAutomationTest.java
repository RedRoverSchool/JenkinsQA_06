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

public class GroupJasperAutomationTest {
    @Test
    public void footballua() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://football.ua/");
        Thread.sleep(3000);

        WebElement textBox = driver.findElement(By.id("searchInput"));
        Thread.sleep(2000);
        textBox.sendKeys("Реал Мадрид");
        Thread.sleep(2000);


        textBox.sendKeys(Keys.RETURN);

        Thread.sleep(2000);


        WebElement text = driver.findElement(By.linkText("Реал Мадрид"));
        Assert.assertEquals(text.getText(), "Реал Мадрид");

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
    public void getSite() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*",  "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.google.com.ua/");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

        WebElement search = driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input"));
        search.sendKeys("redrover school");
        search.sendKeys(Keys.RETURN);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

        WebElement link = driver.findElement(By.xpath("//*[@id=\"rso\"]/div[1]/div/div/div[1]/div/div/div[1]/div/a/div/div/span"));
        Assert.assertEquals(link.getText(),"redrover.school");
        driver.quit();

    }
}
