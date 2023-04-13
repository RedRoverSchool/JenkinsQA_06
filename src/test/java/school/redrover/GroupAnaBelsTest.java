package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GroupAnaBelsTest {


    @Test
    public void StasMtest(){

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.yahoo.com/");

        WebElement submitButton = driver.findElement(By.className("_yb_pbrc7"));
        submitButton.click();

        String title = driver.getTitle();
        assertEquals("Yahoo", title);

        driver.quit();
    }
}
