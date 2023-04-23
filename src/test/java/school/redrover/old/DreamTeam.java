package school.redrover.old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
@Ignore
public class DreamTeam {
    @Test
    public void AndreiG_TC_001_04() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://askomdch.com");
        driver.manage().window().maximize();
        WebElement saleSign = driver.findElement(By.className("onsale"));
        Assert.assertEquals(saleSign.getText(), "Sale!");

        driver.quit();

    }
}
