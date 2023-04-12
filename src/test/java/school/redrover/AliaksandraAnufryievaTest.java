package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;


public class AliaksandraAnufryievaTest {

    @Test
    public void weatherTest() throws InterruptedException{
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://www.foxcarolina.com/");

        WebElement element1 = driver.findElement(By.linkText("Weather"));
        element1.click();

        WebElement element2 = driver.findElement(By.className("form-control"));

        element2.sendKeys("29365");

        Thread.sleep(3000);

        WebElement text = driver.findElement(By.className("location-name"));

        System.out.println(text.getText());

        Assert.assertEquals(text.getText(), "LYMAN, SC");

        driver.quit();
    }
}
