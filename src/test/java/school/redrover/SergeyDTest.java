package school.redrover;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SergeyDTest {

    @Test
    public void test() throws InterruptedException {

        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.google.com/");

        WebElement textBox = driver.findElement(By.name("q"));

        textBox.sendKeys("selenium");
        textBox.sendKeys(Keys.RETURN);

        Thread.sleep(2000);

        WebElement text = driver.findElement(By.xpath("//h3[text() = 'Selenium']"));

        Assert.assertEquals(text.getText(), "Selenium");

        driver.quit();
    }
}
