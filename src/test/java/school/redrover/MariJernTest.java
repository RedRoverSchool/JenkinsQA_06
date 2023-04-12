package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MariJernTest {
    @Test
    public void testH2PopUpTextClickCookiesButton() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://rus.delfi.lv/");

        Thread.sleep(2000);

        WebElement otherCookiesButton = driver.findElement(
                By.xpath("//div[@id='qc-cmp2-ui'] //button[@mode='secondary']")
        );

        otherCookiesButton.click();

        WebElement popupH2Text = driver.findElement(
                By.xpath("//div[@id ='qc-cmp2-ui']//h2")
        );

        Assert.assertEquals(popupH2Text.getTagName(), "h2");
        Assert.assertEquals(popupH2Text.getText(), "Мы с уважением относимся к вашей конфиденциальности");

        driver.quit();
    }
}