package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ElenaTsTest {


    @Test
    public void testTitle(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.backcountry.com/");
       Assert.assertEquals(  driver.getTitle(),"Backcountry - Outdoor Gear & Clothing for Ski, Snowboard, Camp, & More" );
       driver.quit();
    }
@Test
    public void testProductSearchByBrandName() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.tradeinn.com/trekkinn/en");
    WebElement searchField = driver.findElement(By.xpath("//input [@class='ais-SearchBox-input']"));
    searchField.sendKeys("smartwool");
    searchField.sendKeys(Keys.RETURN);
   Thread.sleep(5000);
    WebElement result = driver.findElement(By.xpath("//div[@class='hit-name'][1]"));


    Assert.assertEquals(result.getText().substring(0,9), "Smartwool");
    driver.quit();
}

}
