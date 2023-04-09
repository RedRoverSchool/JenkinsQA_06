package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class KarinaVolodavchikTest {

        @Test
        public void testFirst () throws InterruptedException {
            // WebDriverManager.chromedriver().setup();
            // System.setProperty("webdriver.chrome.driver","/path/to/chromedriver");

            WebDriver driver = new ChromeDriver();
            driver.get("https://www.selenium.dev/selenium/web/web-form.html");

            String title = driver.getTitle();
            Assert.assertEquals("Web form", title);

            // driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

            WebElement textBox = driver.findElement(By.name("my-text"));
            WebElement submitButton = driver.findElement(By.cssSelector("button"));
            Thread.sleep(2000); // замедляет действие

            textBox.sendKeys("Selenium");
            Thread.sleep(2000); // замедляет действие
            submitButton.click();

            WebElement message = driver.findElement(By.id("message"));
            String value = message.getText();
            Thread.sleep(2000); // замедляет действие
            Assert.assertEquals("Received!", value);

            driver.quit();
        }
    }


