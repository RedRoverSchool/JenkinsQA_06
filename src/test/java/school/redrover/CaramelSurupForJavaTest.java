package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class CaramelSurupForJavaTest {




    @Test
    public void serhiiHaponiukFirstSeleniumTest() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://www.selenium.dev/");
        String title = driver.getTitle();
        Assert.assertEquals(title, "Selenium");

        Thread.sleep(5000);
        WebElement SeleniumWebDriverButton = driver.findElement(By.xpath
                ("//*[contains(@class, 'selenium-button selenium-webdriver')]"));
        SeleniumWebDriverButton.click();

        Thread.sleep(5000);
        Assert.assertEquals(driver.getTitle(), "WebDriver | Selenium");
        driver.quit();
    }
}
