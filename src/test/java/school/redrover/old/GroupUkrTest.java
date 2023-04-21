package school.redrover.old;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.ArrayList;
import java.util.List;
@Ignore
public class GroupUkrTest extends BaseTest {
    @Test
    public void testToolsPageHeaders() {
        getDriver().get("https://www.postman.com/");
        String titleMainPage = getDriver().getTitle();
        Assert.assertEquals("Postman API Platform | Sign Up for Free", titleMainPage);

        WebElement menuProduct = getDriver().findElement(By.xpath("//div[contains(text(),'Product')]"));
        menuProduct.click();
        WebElement itemProductTools = getDriver().findElement(By.xpath("//a[@href='https://www.postman.com/product/tools/']"));
        itemProductTools.click();
        String titleToolPage = getDriver().getTitle();
        Assert.assertEquals("API Tools | Postman API Platform", titleToolPage);

        WebElement pageMainHeader = getDriver().findElement(By.xpath("//h1[contains(text(),'Tools')]"));
        String header = pageMainHeader.getText();
        Assert.assertEquals(header, "Tools");

        List<WebElement> pageSubHeaders = new ArrayList<>(getDriver().findElements(By.xpath("//h2")));
        String[] subHeaderTools = {"API client", "API design", "API documentation", "API testing", "Mock servers", "Monitors", "API detection"};
        for (int i = 0; i < 7; i++) {
            Assert.assertEquals(pageSubHeaders.get(i).getText(), subHeaderTools[i]);
        }
    }

    @Test
    public void testRenameBtn(){
        final String NAME = "new button name";

        getDriver().get("http://uitestingplayground.com/home");
        String title = getDriver().getTitle();
        Assert.assertEquals(title,"UI Test Automation Playground");

        WebElement textInputLink = getDriver().findElement(By.xpath("//section[@id = 'overview']//a[text() = 'Text Input']"));
        textInputLink.click();
        WebElement textField = getDriver().findElement(By.xpath("//input[@id='newButtonName']"));
        WebElement updButton = getDriver().findElement(By.xpath("//button[@id='updatingButton']"));

        textField.sendKeys(NAME);
        updButton.click();
        Assert.assertEquals(updButton.getText(), NAME);
    }


    @Test
    public void OpenElementsTab(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://demoqa.com/");
        WebElement element = driver.findElement(By.className("card-up"));
        element.click();
        WebElement header = driver.findElement(By.className("main-header"));
        Assert.assertEquals(header.getText(),"Elements");
        WebElement textTab = driver.findElement(By.className("text"));
        textTab.click();
        WebElement fullNameField = driver.findElement(By.id("userName"));
        fullNameField.sendKeys("Test User");
        WebElement emailField = driver.findElement(By.id("userEmail"));
        emailField.sendKeys("test@mail.com");
        WebElement addressField = driver.findElement(By.id("currentAddress"));
        addressField.sendKeys("394 Davis Avenue");
        WebElement submitButton = driver.findElement(By.id("submit"));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView();", submitButton);
        submitButton.click();
        WebElement output = driver.findElement(By.id("output"));
        Assert.assertTrue(output.isDisplayed(),"The for is not displayed");
        driver.quit();
    }
    @Test
    public void firstTest() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        String title = driver.getTitle();
        Assert.assertEquals("Web form", title);

        Thread.sleep(3000);

        WebElement textBox = driver.findElement(By.name("my-text"));
        WebElement submitButton = driver.findElement(By.cssSelector("button"));

        textBox.sendKeys("Selenium");
        submitButton.click();

        WebElement message = driver.findElement(By.id("message"));
        String value = message.getText();
        Assert.assertEquals("Received!", value);

        driver.quit();
    }
    @Ignore
    @Test
    public void testAuthorizationAndLogOut() throws InterruptedException {
        getDriver().get("https://www.demoblaze.com/");
        WebElement shopTitle = getDriver().findElement(By.xpath("//a[@class ='navbar-brand']"));
        Assert.assertEquals(shopTitle.getText(), "PRODUCT STORE");
        WebElement logInButton = getDriver().findElement(By.xpath("//a[@id='login2']"));
        logInButton.click();

        Thread.sleep(1000);
        WebElement inputUsernameLogInForm = getDriver().findElement(By.xpath("//input[@id='loginusername']"));
        inputUsernameLogInForm.sendKeys("TestAuthMax");
        WebElement inputPasswordLogInForm = getDriver().findElement(By.xpath("//input[@id='loginpassword']"));
        inputPasswordLogInForm.sendKeys("1234");
        WebElement logInButtonLogInForm = getDriver().findElement(By.xpath("//button[@onclick='logIn()']"));
        logInButtonLogInForm.click();
        Thread.sleep(1500);

        WebElement helloUserNameButton = getDriver()
                .findElement(By.xpath("//a[@id='nameofuser']"));
        Assert.assertEquals(helloUserNameButton.getText(), "Welcome TestAuthMax");
        Assert.assertEquals(getDriver().getCurrentUrl(),"https://www.demoblaze.com/");
        WebElement logOutButton = getDriver().findElement(By.xpath("//a[@id='logout2']"));
        logOutButton.click();
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://www.demoblaze.com/index.html");
        WebElement singUpButton = getDriver().findElement(By.xpath("//a[@id='signin2']"));
        Assert.assertEquals(singUpButton.getText(), "Sign up");
    }
    @Test
    public void testProductsDisplayInListing() throws InterruptedException {
        getDriver().get("https://www.demoblaze.com/");
        Thread.sleep(1000);
        WebElement firstProductTitle = getDriver().findElement(By.xpath("//div[@id='tbodyid']/div[1]/div/div/h4/a"));
        Assert.assertEquals(firstProductTitle.getText(), "Samsung galaxy s6");

        WebElement firstProductPrice = getDriver().findElement(By.xpath("//div[@id='tbodyid']/div[1]/div/div/h5"));
        Assert.assertEquals(firstProductPrice.getText(), "$360");

        WebElement linkLaptopSideBar = getDriver().findElement(By.xpath("//div[@class='list-group']/a[3]"));
        linkLaptopSideBar.click();

        Thread.sleep(1500);
        WebElement firstProductTitleLaptop = getDriver().findElement(By.xpath("//div[@id='tbodyid']/div[1]/div/div/h4/a"));
        Assert.assertEquals(firstProductTitleLaptop.getText(), "Sony vaio i5");
        WebElement firstProductPriceLaptop = getDriver().findElement(By.xpath("//div[@id='tbodyid']/div[1]/div/div/h5"));
        Assert.assertEquals(firstProductPriceLaptop.getText(), "$790");

        WebElement linkMonitorsSideBar = getDriver().findElement(By.xpath("//div[@class='list-group']/a[4]"));
        linkMonitorsSideBar.click();

        Thread.sleep(1500);
        WebElement firstProductTitleMonitor = getDriver().findElement(By.xpath("//div[@id='tbodyid']/div[1]/div/div/h4/a"));
        Assert.assertEquals(firstProductTitleMonitor.getText(), "Apple monitor 24");
        WebElement firstProductPriceMonitors = getDriver().findElement(By.xpath("//div[@id='tbodyid']/div[1]/div/div/h5"));
        Assert.assertEquals(firstProductPriceMonitors.getText(), "$400");
    }
    @Test
    public void testListingAndClick(){
        getDriver().get("https://www.demoblaze.com/");
        WebElement fifthItem = getDriver().findElement(By.xpath("//div[@id='tbodyid']/div[5]/div/div/h4/a"));
        Assert.assertEquals(fifthItem.getText(), "Iphone 6 32gb");

        WebElement fifthPriceItem = getDriver().findElement(By.xpath("//div[@id='tbodyid']/div[5]/div/div/h5"));
        Assert.assertEquals(fifthPriceItem.getText(), "$790");

        WebElement linkLaptop = getDriver().findElement(By.xpath("//div[@class='col-lg-3']/div/a[3]"));
        linkLaptop.click();

        WebElement sixthElementOfLaptops = getDriver().findElement(By.xpath("//div[@id='tbodyid']/div[6]/div/div/h4/a"));
        Assert.assertEquals(sixthElementOfLaptops.getText(), "MacBook Pro");

        WebElement sixthPriceItemLaptops = getDriver().findElement(By.xpath("//div[@id='tbodyid']/div[6]/div/div/h5"));
        Assert.assertEquals(sixthPriceItemLaptops.getText(), "$1100");

        WebElement linkMonitors = getDriver().findElement(By.xpath("//div[@class='col-lg-3']/div/a[4]"));
        linkMonitors.click();

        WebElement secondMonitor = getDriver().findElement(By.xpath("//div[@id='tbodyid']/div[2]/div/div/h4/a"));
        Assert.assertEquals(secondMonitor.getText(), "ASUS Full HD");

        WebElement secondPriceItemMonitors = getDriver().findElement(By.xpath("//div[@id='tbodyid']/div[2]/div/div/p"));
        Assert.assertEquals(secondPriceItemMonitors.getText(), "ASUS VS247H-P 23.6- Inch Full HD");

        WebElement linkOfMonitor = getDriver().findElement(By.xpath("//div[@id='tbodyid']/div[2]/div/div/h4/a"));
        linkOfMonitor.click();

        WebElement contentItem = getDriver().findElement(By.xpath("//div[@id='myTabContent']/div[1]/p"));
        Assert.assertEquals(contentItem.getText(), "ASUS VS247H-P 23.6- Inch Full HD");
    }
}


