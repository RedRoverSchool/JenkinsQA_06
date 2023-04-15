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
    @Test
    public void testReturnToMainPage() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.ebay.com/");
        WebElement menuButton = driver.findElement(By.xpath("//li[@data-currenttabindex ='0']"));
        menuButton.click();
        Thread.sleep(3000);
        WebElement siteIcon = driver.findElement(By.xpath("//a[@id='gh-la']"));
        siteIcon.click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.ebay.com/");
    }
    @Test
public void testAddProductToCart() throws InterruptedException {
ChromeOptions chromeOptions=new ChromeOptions();
chromeOptions.addArguments("--headless", "--window-size=1920,1080");
WebDriver driver = new ChromeDriver(chromeOptions);
driver.get("https://www.bergfreunde.eu/");
WebElement searchIcon = driver.findElement(By.xpath("//input[@title='Start your search!']"));
        searchIcon.click();
 WebElement searchField = driver.findElement(By.xpath("//input[@class='searchfield show-for-small-only']"));
searchField.sendKeys("Bottle");
searchField.sendKeys(Keys.RETURN);
WebElement productImg = driver.findElement(By.xpath("(//img[@class='product-image'])[1]"));
productImg.click();
WebElement addToCurtButton = driver.findElement(By.xpath("//button[contains(@id,'addToCartButton')]"));
addToCurtButton.click();
Thread.sleep(3000);
WebElement quantityOfProducts = driver.findElement(By.xpath("//td[text()>=1]"));

    Assert.assertEquals(quantityOfProducts.getText(), "1");
    driver.quit();
}
}
