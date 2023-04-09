package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class MyFirstTest {
    @Test
    public void testFirst_RedRover() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://redrover.school");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));

        WebElement button = driver.findElement(By.linkText("JOIN US"));
        button.click();

        WebElement textBoxEmail = driver.findElement(By.name("email"));
        textBoxEmail.sendKeys("test@gmail");

        WebElement textBoxName = driver.findElement(By.name("name"));
        textBoxName.sendKeys("Test");

        WebElement checkBox = driver.findElement(By.className("t-checkbox__indicator"));
        Thread.sleep(2000);
        checkBox.click();

        WebElement buttons = driver.findElement(By.className("t-submit"));
        buttons.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));

        WebElement error = driver.findElement(By.className("t-input-error"));
        Assert.assertEquals(error.getText(), "Please enter a valid email address");
        driver.quit();
    }
}
