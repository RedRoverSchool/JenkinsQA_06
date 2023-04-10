package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Group99BottlesTest {

    @Test
    public void testTitleBasePage() {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://petstore.octoperf.com/actions/Catalog.action");

        Assert.assertEquals(driver.getTitle(), "JPetStore Demo");

        driver.quit();
    }

    @Test
    public void testCheckboxesHeadHerokuApp() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://formy-project.herokuapp.com/");

        driver.findElement(By.xpath("//li/a[@href='/checkbox']")).click();

        Thread.sleep(3000);

        WebElement text = driver.findElement(By.xpath("//h1"));

        Assert.assertEquals(text.getText(), "Checkboxes");

        driver.quit();
    }

    @Test
    public void testHeaderH1TextOnWomenPage() {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://askomdch.com/");

        driver.findElement(By.id("menu-item-1229")).click();

        WebElement headerText = driver.findElement(
                By.xpath("//header[@class = 'woocommerce-products-header']/h1"));

        Assert.assertEquals(headerText.getText(), "Women");

        driver.quit();
    }

    @Test
    public void testWorkshopHeaderText () {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://corporatetrainingmaterials.com/");

        driver.findElement(By.xpath("//ul[@id='AccessibleNav']/li[2]/a")).click();

        WebElement headerH1Text = driver.findElement(
                 By.xpath("//div[@id='ProductSection']//div[1]/h1"));

        Assert.assertEquals(headerH1Text.getText(), "WORKSHOP LIBRARY");

        driver.quit();

    }
}
