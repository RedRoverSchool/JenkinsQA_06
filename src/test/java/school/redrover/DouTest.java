package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class DouTest {

    @Test

    public void kyivLinkInDou(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://dou.ua/");

        WebElement button = driver.findElement(By.xpath("//a[text() = 'Робота']"));
        button.click();

        button = driver.findElement(By.xpath("//a[text() = 'QA']"));
        button.click();

        driver.quit();
    }
}
