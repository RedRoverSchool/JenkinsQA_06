package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class JavaciraptorsTeamTest {
    @Test
    public void testReadOnlyInput() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        WebElement readOnlyInput = driver.findElement(By.name("my-readonly"));
        String initial = readOnlyInput.getAttribute("value");

        readOnlyInput.click();
        readOnlyInput.sendKeys("Hello World!");

        Assert.assertEquals(readOnlyInput.getAttribute("value"), initial);
        driver.quit();
    }

    @Test
    public void testSavichev() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        WebElement textInput = driver.findElement(By.name("my-text"));
        textInput.click();
        textInput.sendKeys("Hello World!");
        String expText = textInput.getAttribute("value");

        Assert.assertEquals(expText, "Hello World!");

        Thread.sleep(2000);

        WebElement passInput = driver.findElement(By.name("my-password"));
        passInput.click();
        passInput.sendKeys("222222");
        String expPass = passInput.getAttribute("value");

        Assert.assertEquals(expPass, "222222");

        Thread.sleep(2000);

        WebElement checkBox = driver.findElement(By.id("my-check-2"));
        checkBox.click();

        WebElement submitButton = driver.findElement(By.cssSelector("button"));
        submitButton.click();

        WebElement message = driver.findElement(By.id("message"));
        String value = message.getText();
        Assert.assertEquals("Received!", value);

        driver.quit();
    }
}
