package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GroupAnaBelsTest {

    @Test
    public void testStasM() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.yahoo.com/");

        WebElement submitButton = driver.findElement(By.xpath("//*[@id='ybarAccountProfile']/a"));
        submitButton.click();

        String title = driver.getTitle();
        Assert.assertEquals("Yahoo", title);
    }
}
