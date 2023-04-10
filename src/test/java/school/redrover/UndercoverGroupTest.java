package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UndercoverGroupTest {

    @Test
    public void firstTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(options);

        driver.get("https://google.com");

        WebElement searchField = driver.findElement(By.name("q"));
        searchField.sendKeys("Selenium");
        searchField.sendKeys(Keys.RETURN);

        WebElement actual = driver.findElement(By.xpath("//h3[text() = \"Selenium\"]"));

        Assert.assertEquals(actual.getText(), "Selenium");

        driver.quit();
    }
    @Test
    public void testSearch(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*", "--headless", "--window-size=800,600");
        WebDriver driver = new ChromeDriver(options);

        driver.get("https://www.google.com/");
        WebElement searchbox = driver.findElement(By.name("q"));
        searchbox.sendKeys("selenium\n");

        WebElement text = driver.findElement(By.xpath("//h3[text() = 'Selenium']"));
        Assert.assertEquals(text.getText(), "Selenium");

        driver.quit();
    }
}
