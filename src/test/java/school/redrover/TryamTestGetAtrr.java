package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TryamTestGetAtrr {
    @Test
    public void getAtrr(){

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.google.com/");
        WebElement button = driver.findElement(By.name("btnK"));
        Assert.assertEquals(button.getAttribute("value"),"Поиск в Google");
    }
}
