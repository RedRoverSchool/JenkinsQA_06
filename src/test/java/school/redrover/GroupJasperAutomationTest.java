package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

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
    public void testImdb() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://www.imdb.com/");

        WebElement textBox = driver.findElement(By.name("q"));

        textBox.sendKeys("Everything Everywhere All At Once");
        textBox.sendKeys(Keys.RETURN);
        WebElement link = driver.findElement(By.xpath("//a[contains(text(), 'Всё везде и сразу')]"));
        link.click();

        Thread.sleep(5000);

        WebElement textBox2 = driver.findElement(By.xpath("//*[@id=\"__next\"]/main/div/section[1]/section/div[3]/section/section/div[2]/div[1]/div"));
        assertEquals(textBox2.getText(), "Original title: Everything Everywhere All at Once");
    }
}
