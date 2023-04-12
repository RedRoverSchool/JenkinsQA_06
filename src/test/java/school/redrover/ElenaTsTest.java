package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class ElenaTsTest {


    @Test
    public void testTitle(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.ebay.com/");
       Assert.assertEquals(  driver.getTitle(),"Electronics, Cars, Fashion, Collectibles & More | eBay" );
       driver.quit();
    }
@Ignore
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

    @Test
    public void testFindProductByBrandName() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.ebay.com/");
        WebElement searchField = driver.findElement(By.xpath("//input [@class='gh-tb ui-autocomplete-input']"));
        searchField.sendKeys("Samsung");
        searchField.sendKeys(Keys.RETURN);
        Thread.sleep(3000);
        WebElement result = driver.findElement(By.xpath("(//span[@role='heading'])[2]"));
        Assert.assertEquals(result.getText().substring(0,7), "Samsung");
        driver.quit();
    }
}
