package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class MarianaGTest {


    @Test
    void testFirstMariana() throws InterruptedException {


        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com/");
        WebElement textBox = driver.findElement(By.name("q"));
        textBox.findElements(By.name("Cat Breeds | Types of Cats"));
        driver.quit();





    }
}