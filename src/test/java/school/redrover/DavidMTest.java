package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DavidMTest {
    private ChromeOptions chromeOptions;
    private WebDriver driver;


    private void beforeMethod(){
        chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        driver = new ChromeDriver(chromeOptions);
    }

    private void afterMethod(){
        driver.quit();
    }


public class DavidMTest extends BaseTest {

    @Test
    public void testFirst() throws InterruptedException {


        driver.get("https://www.google.com");
        WebElement textBox = driver.findElement(By.name("q"));
        getDriver().get("https://www.google.com");
        WebElement textBox = getDriver().findElement(By.name("q"));

        textBox.sendKeys("selenium");
        textBox.sendKeys(Keys.RETURN);

        Thread.sleep(2000);

        WebElement text = driver.findElement(By.xpath("//h3[text() = 'Selenium']"));
        WebElement text = getDriver().findElement(By.xpath("//h3[text() = 'Selenium']"));
        Assert.assertEquals(text.getText(),"Selenium");

         }
        }

    @Test
    public void testSecond() throws InterruptedException {
    public void TestSecond() throws InterruptedException {

        getDriver().get("https://www.selenium.dev/selenium/web/web-form.html");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        String title = driver.getTitle();
        Assert.assertEquals("Web form", title);



        Thread.sleep(2000);

        WebElement textBox = driver.findElement(By.name("my-text"));
        WebElement submitButton = driver.findElement(By.cssSelector("button"));

        textBox.sendKeys("Selenium");
        submitButton.click();

        WebElement message = driver.findElement(By.id("message"));
        String value = message.getText();
        Assert.assertEquals("Received!", value);
    }
    }
        }
  }