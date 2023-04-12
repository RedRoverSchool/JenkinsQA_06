package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class BugsBustersTest {
    @Ignore
    @Test
    public void testAngelinaGiftCardSelection() {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.broadway.com/");

        WebElement giftCardTab = driver.findElement(By.linkText("Gift Cards"));
        giftCardTab.click();

        WebElement buyNowButton = driver.findElement(By.linkText("Buy Now"));
        buyNowButton.click();

//        WebElement continueToDeliveryButton = driver.findElement(By.xpath("//button[@type='submit']"));
//        continueToDeliveryButton.click();
//
//        WebElement text = driver.findElement(By.xpath("//div[@data-qa='subtotal-amount']//strong"));
//        Assert.assertEquals(text.getText(),"$100.00");

        driver.quit();
    }
    @Test
    public void testSergeyConvert() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.unitconverters.net/weight-and-mass/lbs-to-kg.htm");

        WebElement textBox = driver.findElement(By.cssSelector("input#ucfrom.ucdcinput"));
        textBox.sendKeys("1");

        WebElement convert = driver.findElement(By.cssSelector("input.ucdcsubmit"));
        convert.click();

        WebElement text = driver.findElement(By.cssSelector("div#ucresult"));
        Assert.assertEquals(text.getText(), "Result: 1 pound = 0.45359237 kilogram");

        driver.quit();
    }

    @Ignore
    @Test
    public void testAngelinaSearchResults() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.broadway.com/");

        WebElement searchBox = driver.findElement(By.id("nav-typeahead-js"));
        searchBox.sendKeys("the phantom of the opera\n");

        WebElement text = driver.findElement(By.xpath("//a[@href=\"/shows/the-phantom-of-the-opera/\"]/h3"));
        Assert.assertEquals(text.getText(), "The Phantom of the Opera");

        driver.quit();
    }

    @Test
    public void YuliyaTFirstTest(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
//        chromeOptions.addArguments("--remote-allow-origins=*", "--window-size=1920,1080");


        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://www.selenium.dev/");

        WebElement readMoreButton = driver.findElement(By.xpath("//a[@href='/documentation/webdriver/']"));
        readMoreButton.click();

        WebElement getStatedButton = driver.findElement(By.xpath("//a[@href='/documentation/webdriver/getting_started/']"));
        getStatedButton.click();

        WebElement text = driver.findElement(By.xpath("//h1"));
        Assert.assertEquals(text.getText(), "Getting started");

        driver.quit();
    }

    @Test
    public void testMS() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();

        driver.get("https://www.funartcolorado.com/");
        Assert.assertEquals(driver.getTitle(), "Home | Funart");

        WebElement artTab = driver.findElement(By.id("comp-jnlr6x4r1label"));
        artTab.click();
        Thread.sleep(2000);
        WebElement mosaicArtHeader = driver.findElement(By.cssSelector("div[id='comp-jbrwxjbr'] span"));
        Assert.assertEquals(mosaicArtHeader.getText(), "About my art:");

        WebElement classesTab = driver.findElement(By.id("comp-jnlr6x4r2label"));
        classesTab.click();
        Thread.sleep(2000);
        WebElement classesHeader = driver.findElement(By.cssSelector("h1[class='font_0'] span"));
        Assert.assertEquals(classesHeader.getText(), "ONLINE course \"Fun Art with Tempered glass\"");

        driver.quit();
    }
}
