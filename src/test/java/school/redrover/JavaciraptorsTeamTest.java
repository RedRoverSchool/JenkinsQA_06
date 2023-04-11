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
}
