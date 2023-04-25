package school.redrover.old;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
@Ignore
public class REginaFGK {

    @Test
    public void homePage() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://www.google.com/");

        WebElement textBox = driver.findElement(By.name("q"));

        textBox.sendKeys("ubereats");
        textBox.sendKeys(Keys.RETURN);

        Thread.sleep(2000);

        WebElement firstResult = driver.findElement(By.cssSelector("div.g a"));
        firstResult.click();
        Assert.assertEquals(driver.getTitle(), "Uber Eats US | Food Delivery and Takeout | Order Online from Restaurants Near You");

        driver.quit();
    }

}
