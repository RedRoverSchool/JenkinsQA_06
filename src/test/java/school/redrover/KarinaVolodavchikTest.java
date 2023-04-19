package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

 public class KarinaVolodavchikTest extends BaseTest {

        @Test
        public void testFirst () throws InterruptedException {

            driver.get("https://www.selenium.dev/selenium/web/web-form.html");

            String title = driver.getTitle();
            Assert.assertEquals("Web form", title);

            WebElement textBox = driver.findElement(By.name("my-text"));
            WebElement submitButton = driver.findElement(By.cssSelector("button"));
            Thread.sleep(2000);

            textBox.sendKeys("Selenium");
            Thread.sleep(2000);
            submitButton.click();

            WebElement message = driver.findElement(By.id("message"));
            String value = message.getText();
            Thread.sleep(2000);
            Assert.assertEquals("Received!", value);

        }
        @Test
        public void testSecond() {
            driver.get("https://petstore.octoperf.com/actions/Catalog.action");
            String title = driver.getTitle();
            Assert.assertEquals("JPetStore Demo", title);
        }
 }


