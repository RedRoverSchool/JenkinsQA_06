package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;


public class IrinaITest {
    @Test

    public void testIrinaI() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.jetbrains.com/");

        Thread.sleep(3000);

        WebElement label= driver.findElement(By.xpath("//a[@aria-label='Navigate to main page']"));
        label.click();

        WebElement title = driver.findElement(By.xpath("//h1[@class='rs-h1 rs-h1_theme_dark home-page__title']"));

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.jetbrains.com/");
        Assert.assertEquals(title.getText(), "Essential tools for software developers and teams");

        driver.quit();

    }
}
