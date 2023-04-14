package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VikiShebTest {
    @Test

    public void checkDollarSymbol_TC_002_02() throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://askomdch.com/");
        Thread.sleep(1000);
        Assert.assertEquals(driver.getTitle(), "AskOmDch – Become a Selenium automation expert!");
        driver.findElement(By.xpath("//a[@class='wp-block-button__link']")).click();
        Thread.sleep(1000);
        WebElement symbol = driver.findElement(By.xpath("//span[@class='woocommerce-Price-currencySymbol']"));
        Assert.assertEquals(symbol.getText(), "$");
        Thread.sleep(1000);
        driver.quit();

    }

}
