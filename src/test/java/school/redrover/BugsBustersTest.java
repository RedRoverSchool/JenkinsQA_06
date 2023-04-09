package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
//import org.testng.Assert;
import org.testng.annotations.Test;

public class BugsBustersTest {
    @Test
    public void testAngelinaGiftCardSelection() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://www.broadway.com/");

        WebElement giftCardTab = driver.findElement(By.linkText("Gift Cards"));
        giftCardTab.click();

        WebElement buyNowButton = driver.findElement(By.linkText("Buy Now"));
        buyNowButton.click();

//        WebElement continueToDeliveryButton = driver.findElement(By.xpath("//button[@type=\"submit\"]"));
//        continueToDeliveryButton.click();

        Thread.sleep(3000);

//        WebElement text = driver.findElement(By.xpath("//div[@data-qa='subtotal-amount']//strong"));
//        Assert.assertEquals(text.getText(),"$100.00");

        driver.quit();
    }
}
