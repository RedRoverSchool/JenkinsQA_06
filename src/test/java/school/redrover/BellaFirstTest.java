package school.redrover;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class BellaFirstTest {

    @Test
    public void verificationOfTitle(){

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
       driver.manage().window().maximize();

        driver.get("https://askomdch.com");
        String expectResult = "AskOmDch – Become a Selenium automation expert!";
       String actualResult = driver.getTitle();
        Assert.assertEquals(actualResult,expectResult,"test is failed");

    }
}
