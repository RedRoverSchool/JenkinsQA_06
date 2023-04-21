package school.redrover.old;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;


@Ignore
public class OlhaKTest {
    @Test
    public void FirstTest() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
            driver.get("https://www.selenium.dev/selenium/web/web-form.html");

            String title = driver.getTitle();
            Assert.assertEquals("Web form", title);

            WebElement textBox = driver.findElement(By.name("my-text"));
            WebElement submitButton = driver.findElement(By.cssSelector("button"));

            textBox.sendKeys("Selenium");
            submitButton.click();

            Thread.sleep(2000);

            WebElement message = driver.findElement(By.id("message"));
            String value = message.getText();
            Assert.assertEquals("Received!", value);

            driver.quit();
        }

    }


