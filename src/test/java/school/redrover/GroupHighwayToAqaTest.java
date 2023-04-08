package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GroupHighwayToAqaTest {

    @Test
    public void openContactUsPageTest() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://magento.softwaretestingboard.com/");
        driver.findElement(By.xpath("//footer/div/ul[@class='footer links']//a[text()='Contact Us']")).click();
        WebElement pageTitle = driver.findElement(By.xpath("//span[@data-ui-id='page-title-wrapper']"));

        Assert.assertEquals(driver.getCurrentUrl(), "https://magento.softwaretestingboard.com/contact/");
        Assert.assertEquals(pageTitle.getText(), "Contact Us");

        driver.quit();
    }
}
