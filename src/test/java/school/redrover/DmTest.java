package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class DmTest {

        @Test
        public void testThird() throws InterruptedException {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
            WebDriver driver = new ChromeDriver();
            driver.get("http://www.consultant.ru");

            WebElement textBox;
            textBox = driver.findElement(By.name("q"));
            textBox.sendKeys("Федеральный закон №116");
            textBox.sendKeys(Keys.RETURN);

            WebElement item;
            item = driver.findElement(By.className("search-results__link-inherit"));
            item.click();

            Thread.sleep(4000);

//          WebElement button;
//          button = driver.findElement((By.xpath("//button[@class = 'full-text__button']")));
//          button.click();

            driver.quit();
        }
    }
