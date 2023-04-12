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
        driver.get("https://www.ebay.com/");
       Assert.assertEquals(  driver.getTitle(),"Electronics, Cars, Fashion, Collectibles & More | eBay" );
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
