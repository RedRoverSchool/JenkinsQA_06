package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class GroupJasperAutomationTest extends BaseTest {
    @Ignore
    @Test

    public void footballua() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://football.ua/");
        WebElement element = driver.findElement(By.xpath("//*[text()='Україна']"));
        Assert.assertEquals(element.getText(), "Україна");
        driver.quit();
    }


    @Test
    public void testTitle() throws InterruptedException {
        getDriver().get("https://football.ua/");
        Thread.sleep(2000);

        String title = getDriver().getTitle();

        Assert.assertEquals(title, "Football.ua - Новости футбола - Футбол онлайн - Результаты матчей, трансляции — football.ua");
    }

    @Test
    public void testBiletskayaA() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://new.uschess.org/");

        WebElement textLink = driver.findElement(By.className("clo-image"));
        textLink.click();

        Thread.sleep(2000);

        WebElement element = driver.findElement(By.xpath("//*[text()='Search Articles']"));
        Assert.assertEquals(element.getText(), "Search Articles");

        driver.quit();
    }

    @Test
    public void testBUshakov() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://privatbank.ua/");

        WebElement textLink = driver.findElement(By.xpath("//a[contains(text(), 'Відділення')]"));
        textLink.click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://privatbank.ua/map");

        driver.quit();
    }

    @Test
    public void testFindContacts() throws InterruptedException {
        getDriver().get("https://gorodok.ua/");
        Thread.sleep(5000);

        WebElement name = getDriver().findElement(By.xpath("//*[@class='button primary is-outline is-small']"));

        Assert.assertEquals(name.getText(), "CONTACTS");
    }

    @Test
    public void testWebFormSubmission() throws InterruptedException {
        getDriver().get("https://www.selenium.dev/selenium/web/web-form.html");

        WebElement textBox = getDriver().findElement(By.name("my-text"));
        WebElement submitButton = getDriver().findElement(By.cssSelector("button"));

        textBox.sendKeys("Selenium");
        submitButton.click();

        WebElement message = getDriver().findElement(By.id("message"));
        String value = message.getText();

        Assert.assertEquals(value, "Received!");
    }

    @Ignore
    @Test
    public void testWHO() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://www.who.int/en");
        WebElement linkToGo = driver.findElement(By.xpath("//a[contains(text(), 'Contact us')]"));
        linkToGo.click();

        Thread.sleep(2000);
        WebElement textBox = driver.findElement(By.xpath("//*[@id=\"PageContent_C038_Col01\"]/div[1]/h2"));
        assertEquals(textBox.getText(), "General enquiries");
    }

    @Test
    public void discordTest() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        driver.get("https://discord.com/");

        WebElement droplist = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@class='arrowIcon-3hG4PK']")));
        droplist.click();

        WebElement choseLeng = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'text-2H5FnP textExtraSmall-2-FKT0 dropdownLanguageName-3GY1QW') " +
                "and contains(text(), 'English, USA')]")));
        choseLeng.click();

        WebElement nitro = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='link-1naFFL link-2eyudH' and @href='/nitro']")));
        nitro.click();

        WebElement droplist1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='selector-language-name']")));
        droplist1.click();
        WebElement choseLeng1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'dropdown-language-name')  and contains(text(), 'English (UK)')]")));
        choseLeng1.click();

        droplist1.click();
        choseLeng1.click();

        Thread.sleep(10000);

        WebElement unlesh = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class = 'new-h1 font-white capital']")));

        Assert.assertEquals(unlesh.getText(), "Unleash more fun with Nitro");
        driver.quit();


    }

    @Test
       public void testEnterToSite(){
        getDriver().get("https://www.saucedemo.com/");

        WebElement addLogin = getDriver().findElement(By.xpath("//input[@data-test='username']"));
        addLogin.sendKeys("standard_user");

        WebElement addPassword = getDriver().findElement(By.xpath("//input[@data-test='password']"));
        addPassword.sendKeys("secret_sauce");

        WebElement loginButton = getDriver().findElement(By.xpath("//input[@class='submit-button btn_action']"));
        loginButton.click();

        WebElement logo = getDriver().findElement(By.xpath("//div[@class='app_logo']"));
        Assert.assertEquals(logo.getText(), "Swag Labs");
    }
    @Test
    public void testTryToEnterLockedUser() {
        getDriver().get("https://www.saucedemo.com/");
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));

        WebElement loginField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id ='user-name']")));
        loginField.sendKeys("locked_out_user");

        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id ='password']")));
        passwordField.sendKeys("secret_sauce");

        WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id ='login-button']")));
        loginButton.click();

        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-test ='error']")));
        Assert.assertEquals(errorMessage.getText(),"Epic sadface: Sorry, this user has been locked out.");
    }

    @Ignore
    @Test
    public void testOrderIphone14() throws InterruptedException {
        getDriver().get("https://www.apple.com/");
        WebElement searchButton = getDriver().findElement(By.id("globalnav-menubutton-link-search"));
        searchButton.click();

        WebElement searchBox = getDriver().findElement(By.className("globalnav-searchfield-input"));
        searchBox.sendKeys("IPhone 14");
        Thread.sleep(3000);

        WebElement chooseIphone = getDriver().findElement(By.xpath("//a[@data-label = 'iPhone 14 Pro and iPhone 14 Pro Max']"));
        chooseIphone.click();

        WebElement buyIphoneButton = getDriver().findElement(By.xpath("//div[@class = 'section-content']/a[@aria-label = 'Buy iPhone 14 Pro']"));
        buyIphoneButton.click();

        WebElement chooseVersion = getDriver().findElement(By.xpath("//input[@value = '6_1inch']"));
        chooseVersion.click();

        WebElement chooseColor = getDriver().findElement(By.xpath("//input[@value = 'deeppurple']/following-sibling::label/img"));
        chooseColor.click();
        Thread.sleep(2000);

        WebElement chooseVolume = getDriver().findElement(By.xpath("//input[@value = '128gb']"));
        chooseVolume.click();

        WebElement isTradeIn = getDriver().findElement(By.xpath("//input[@id = 'noTradeIn']"));
        isTradeIn.click();
        Thread.sleep(2000);

        WebElement paymentOption = getDriver().findElement(By.xpath("//span[text() = 'Buy']"));
        paymentOption.click();
        Thread.sleep(1000);

        WebElement carrierChoose = getDriver().findElement(By.xpath("//span[text() = 'Connect to any carrier later']"));
        carrierChoose.click();
        Thread.sleep(3000);

        WebElement addCoverage = getDriver().findElement(By.xpath("//input[@id = 'applecareplus_58_noapplecare']"));
        addCoverage.click();
        Thread.sleep(1000);

        WebElement addToCartButton = getDriver().findElement(By.xpath("//button[@value = 'add-to-cart']"));
        addToCartButton.click();

        WebElement reviewBagButton = getDriver().findElement(By.xpath("//button[@name = 'proceed']"));
        reviewBagButton.click();

        WebElement orderedProductName = getDriver().findElement(By.linkText("iPhone 14 Pro 128GB Deep Purple"));
        Assert.assertEquals(orderedProductName.getText(), "iPhone 14 Pro 128GB Deep Purple");
    }

    @Test
    public void testNumberOfIphoneModelsAvailable(){
        getDriver().get("https://www.apple.com/");
        WebElement searchButton = getDriver().findElement(By.id("globalnav-menubutton-link-search"));
        searchButton.click();

        WebElement iPhonePage = getDriver().findElement(By.xpath("//a[@aria-label = 'iPhone']"));
        iPhonePage.click();

        WebElement listOfIphones = getDriver().findElement(By.cssSelector("ul.chapternav-items"));
        List<WebElement> list = listOfIphones.findElements(By.xpath("li[contains(@class, 'item-iphone-')]"));

        Assert.assertEquals(list.size(), 5);
    }
    @Test
    public void testMetanitJava() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://metanit.com/");
        Thread.sleep(5000);
        WebElement name = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/header[1]/div[3]/ul[1]/li[3]/a[1]"));
        assertEquals(name.getText(), "JAVA");
        driver.quit();
}}
