package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class AndreiAfanasevTest {

    @Ignore
    @Test
    public void testRepairRequest() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://amazing-repair.com/free-quote.html");

        WebElement textBox = driver.findElement(By.name("custom_U496"));
        textBox.sendKeys("NameTest");

        textBox = driver.findElement(By.name("Email"));
        textBox.sendKeys("TestEmail@Test.com");

        textBox = driver.findElement(By.name("custom_U492"));
        textBox.sendKeys("7047047047");

        textBox = driver.findElement(By.name("custom_U540"));
        textBox.sendKeys("Test, Test, NC, 28105");

        WebElement checkBox = driver.findElement(By.id("widgetu448"));
        checkBox.click();

        textBox = driver.findElement(By.name("custom_U444"));
        textBox.sendKeys("Yulia, just ignore this email. DON`T SEND TO SPAM. Test request");

        WebElement submitButton = driver.findElement(By.id("u456-17"));
        submitButton.click();
        Thread.sleep(500);

        WebElement statusMessage = driver.findElement(By.id("u466-4"));
        Assert.assertEquals(statusMessage.getText(), "SEND ok");



        driver.quit();
    }

}
