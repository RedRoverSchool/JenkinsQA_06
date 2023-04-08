package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AlexLeoEpicGroupTest {
    @Test
    public void titleOfTheHomePageCheckedTest() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        driver.get("https://askomdch.com/");
        Assert.assertEquals(driver.getTitle(), "AskOmDch – Become a Selenium automation expert!");
        driver.findElement(By.xpath("//a[@class='wp-block-button__link']")).click();
        WebElement icon = driver.findElement(By.xpath("//span[@class='onsale']"));
        Assert.assertEquals(icon.getText(), "Sale!");
        driver.quit();
    }

    @Test
    public void Test_TC_001_01() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.manage().window().maximize();
        driver.get("https://askomdch.com/");
        Assert.assertEquals(driver.getTitle(), "AskOmDch – Become a Selenium automation expert!");

        String fieldValidation = driver.findElement(By.xpath("//h2[@class=\"has-text-align-center\"]")).getText();
        Assert.assertEquals(fieldValidation, "Featured Products");
        Thread.sleep(2000);

        String saleTag = driver.findElement(By.xpath("//span[@class=\"onsale\"]")).getText();
        Assert.assertEquals(saleTag, "Sale!");
        driver.quit();


    }

}
