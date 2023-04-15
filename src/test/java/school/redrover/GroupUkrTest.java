package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;

public class GroupUkrTest extends BaseTest {
    @Ignore
    @Test
    public void youtubeSearchTest(){
        ChromeOptions optionsChrome = new ChromeOptions();
        optionsChrome.addArguments("--headless","--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(optionsChrome);
        driver.get("https://www.youtube.com/");

        String title = driver.getTitle();
        Assert.assertEquals("YouTube", title);

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

        WebElement searchInput = driver.findElement(By.xpath("//input[@id='search']"));
        WebElement searchButton = driver.findElement(By.xpath("//button[@id='search-icon-legacy']"));

        searchInput.sendKeys("Что такое Selenium?");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

        searchButton.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

        WebElement link = driver.findElement(By.xpath("//a[@title='Что такое Selenium?']"));
        String value = link.getText();

        Assert.assertEquals(value, "Что такое Selenium?");

        driver.quit();

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

}


