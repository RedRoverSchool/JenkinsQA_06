package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;



public class NeedMoreCoffeeTest {
    @Test
    public void testB() {
       ChromeOptions chromeOptions = new ChromeOptions();
       chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.google.com/");
        WebElement textBox = driver.findElement(By.name("q"));
        textBox.sendKeys("vk");
        //textBox.sendKeys(Keys.RETURN);

        //WebElement text = driver.findElement(By.xpath("//*[@href='https://vk.com/login]"));
        //Assert.assertEquals(text.getText(), "ВКонтакте");

        driver.quit();
    }

    @Test
    public void testBut() {
       ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.google.com/");
        WebElement textBox = driver.findElement(By.name("q"));
        textBox.sendKeys("ozon");
        textBox.sendKeys(Keys.RETURN);
        driver.quit();



    }

    @Test
    public void testHello() {
        System.out.println("Hello World!");
    }


    @Test
    public void testButonaeva() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://www.kinopoisk.ru");

        WebElement textBox = driver.findElement(By.name("kp_query"));

        textBox.sendKeys("Король и Шут");
        textBox.sendKeys(Keys.RETURN);

        Thread.sleep(3000);
        WebElement text = driver.findElement(By.xpath("//*[@id=\"block_left_pad\"]/div/div[2]/div/div[2]/p/a"));
        Thread.sleep(3000);
        Assert.assertEquals(text.getText(), "Король и Шут (сериал)");


        driver.quit();


    }
}




