package school.redrover;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class GroupHighwayToAqaTest {

    private static final String BASE_URL = "https://magento.softwaretestingboard.com/";

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

    @Test
    public void testErrorMessage() {

        String expectedErrorMessage = "This is a required field.";

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://magento.softwaretestingboard.com");

        WebElement scrollByVisibleElement = driver.findElement(By.xpath("//div[@class='footer content']"));
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView(true)", scrollByVisibleElement);

        WebElement contactNavItem = driver.findElement(
                By.xpath("//a[@href='https://magento.softwaretestingboard.com/contact/']"));
        contactNavItem.click();

        driver.findElement(By.xpath("//input[@id='name']"))
                .sendKeys("Anna");
        driver.findElement(By.xpath("//input[@id='telephone']"))
                .sendKeys("8995552557");
        driver.findElement(By.xpath("//textarea[@id='comment']"))
                .sendKeys("Thank you for providing such great products and service!");
        driver.findElement(By.xpath("//span[text()='Submit']")).click();

        String actualErrorMessage = driver.findElement(
                By.xpath("//div[@id='email-error']")).getText();

        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);

        driver.quit();
    }

    @Test
    public void MLFirstTest() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get(BASE_URL);

        WebElement textBox = driver.findElement(
                By.xpath("//header//a[normalize-space(text())=\"Create an Account\"]"));
        textBox.click();

        WebElement text = driver.findElement(By.xpath("//span[@data-ui-id = \"page-title-wrapper\"]"));

        Assert.assertEquals(text.getText(), "Create New Customer Account");

        WebElement firstName = driver.findElement(By.xpath("//input[@id = \"firstname\"]"));
        firstName.sendKeys("Marina");
        WebElement lastName = driver.findElement(By.xpath("//input[@id = \"lastname\"]"));
        lastName.sendKeys("Los");
        WebElement email = driver.findElement(By.xpath("//input[@id = \"email_address\"]"));
        email.sendKeys("test@google.com");
        WebElement password1 = driver.findElement(By.xpath("//input[@id = \"password\"]"));
        password1.sendKeys("123Qwerty+");
        WebElement password2 = driver.findElement(By.xpath("//input[@id = \"password-confirmation\"]"));
        password2.sendKeys("123Qwerty+");
        WebElement submitbutton = driver.findElement(
                By.xpath("//button/span[normalize-space(text())=\"Create an Account\"]")
        );
        submitbutton.click();

        Thread.sleep(2000);

        WebElement clickHereLink=driver.findElement(
                By.xpath("//div/a[normalize-space(text())=\"click here\"]")
        );
        clickHereLink.click();

        WebElement forgotPassword = driver.findElement(
                By.xpath("//h1/span[normalize-space(text())=\"Forgot Your Password?\"]")
        );

        Assert.assertEquals(forgotPassword.getText(), "Forgot Your Password?");

        driver.quit();
    }

    @Test
    public void testTitle() {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://magento.softwaretestingboard.com/");

        String title = driver.getTitle();
        Assert.assertEquals("Home Page", title);
        
        driver.quit();
    }

    @Test
    public void testNewLinkAR(){
        String expectedPageTitle = "What's New";

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://magento.softwaretestingboard.com/");

        WebDriverWait waitForWhatsNewLink = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitForWhatsNewLink.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='ui-id-3']")));

        WebElement whatsNewLink = driver.findElement(By.xpath("//a[@id='ui-id-3']"));
        whatsNewLink.click();

        WebElement pageTitle = driver.findElement(By.xpath("//h1[@id='page-title-heading']"));
        String actualPageTitle = pageTitle.getText();

        Assert.assertEquals(actualPageTitle, expectedPageTitle);

        driver.quit();
    }

    @Test
    public void testCountShippingOptionsMenTops() {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get(BASE_URL);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ui-id-5")));

        WebElement menButton = driver.findElement(By.id("ui-id-5"));
        WebElement topsButton = driver.findElement(By.id("ui-id-17"));

        new Actions(driver).moveToElement(menButton).perform();
        wait.until(ExpectedConditions.visibilityOf(topsButton));
        topsButton.click();

        List<WebElement> listShippingOptions = driver.findElements(By.xpath("//div[@data-role='title']"));

        Assert.assertEquals(listShippingOptions.size(), 13);

        driver.quit();
    }

    @Test
    public void testRequiredFieldMessage() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        String expectedMessage = "This is a required field.";

        driver.get(BASE_URL);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement subscribeButton = driver.findElement(By.xpath("//button[@class='action subscribe primary']"));
        js.executeScript("arguments[0].scrollIntoView();", subscribeButton);

        Thread.sleep(2000);

        subscribeButton.click();
        WebElement element = driver.findElement(By.xpath("//div[@id='newsletter-error']"));
        wait.until(ExpectedConditions.visibilityOf(element));

        String actualMessage = element.getText();

        Assert.assertEquals(actualMessage, expectedMessage);

        driver.quit();
    }

    @Test
    public void searchItem() throws InterruptedException {
        ChromeOptions chromeOptions=new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://magento.softwaretestingboard.com");
        WebElement href = driver.findElement(By.xpath("//div/nav/ul/li [1]"));
        String value = href.getText();
        assertEquals(value, "What's New");
        WebElement href2 = driver.findElement(By.xpath("//div/nav/ul/li [2]"));
        String value1 = href2.getText();
        assertEquals(value1, "Women");
        WebElement href3 = driver.findElement(By.xpath("//div/nav/ul/li [3]"));
        String value2 = href3.getText();
        assertEquals(value2, "Men");
        WebElement href4 = driver.findElement(By.xpath("//div/nav/ul/li [4]"));
        String value3 = href4.getText();
        assertEquals(value3, "Gear");
        WebElement href5 = driver.findElement(By.xpath("//div/nav/ul/li [5]"));
        String value4 = href5.getText();
        assertEquals(value4, "Training");
        WebElement href6 = driver.findElement(By.xpath("//div/nav/ul/li [6]"));
        String value5 = href6.getText();
        assertEquals(value5, "Sale");
        driver.findElement(By.xpath("//div/nav/ul/li [2]")).click();
        String text= driver.getTitle();
        assertEquals(text,"Women");
        driver.findElement(By.xpath("//html/body/div[1]/div[2]/ul/li[1]/a")).click();
        String title= driver.getTitle();
        assertEquals(title,"Home Page");
        driver.quit();
    }

    @Test

    public void testH1TextInWhatIsNew() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://magento.softwaretestingboard.com/");
        Thread.sleep(2000);

        WebElement whatIsNew = driver.findElement(By.id("ui-id-3"));
        whatIsNew.click();

        WebElement h1InWhatIsNew = driver.findElement(By.xpath("//h1[@id = 'page-title-heading']/span"));

        Assert.assertEquals(h1InWhatIsNew.getText(), "What's New");

        driver.quit();

    }

    @Test
    public void testNumberOfItemsEqualsProductNumberShownPerPage() throws InterruptedException {
       ChromeOptions chromeOptions = new ChromeOptions();
       chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get(BASE_URL);
        Thread.sleep(3000);

        WebElement womenButton = driver.findElement(By.id("ui-id-4"));
        WebElement topsButton = driver.findElement(By.id("ui-id-9"));
        WebElement jacketsButton = driver.findElement(By.id("ui-id-11"));

        new Actions(driver).moveToElement(womenButton).moveToElement(topsButton)
                .moveToElement(jacketsButton).perform();
        jacketsButton.click();

      WebElement expectedNumberOfItems = driver.findElement(By
              .xpath("//span[@class='toolbar-number'][1]"));
      List<WebElement> listOfItemsShownOnPage = driver.findElements(By
              .xpath("//li[@class='item product product-item']"));

      Assert.assertEquals(listOfItemsShownOnPage.size(), Integer.parseInt(expectedNumberOfItems.getText()));
      driver.quit();
    }
}











