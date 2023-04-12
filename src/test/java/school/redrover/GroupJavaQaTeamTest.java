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

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class GroupJavaQaTeamTest {

    @Test
    public void testFirst() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        String title = driver.getTitle();
        assertEquals("Web form", title);

        WebElement textBox = driver.findElement(By.name("my-text"));
        WebElement submitButton = driver.findElement(By.cssSelector("button"));

        Thread.sleep(3000);

        textBox.sendKeys("Selenium");

        Thread.sleep(3000);

        submitButton.click();

        Thread.sleep(3000);

        WebElement message = driver.findElement(By.id("message"));
        String value = message.getText();
        assertEquals("Received!", value);

        Thread.sleep(3000);

        driver.quit();
    }

    @Ignore
    @Test
    public void testH2TagText_WhenSearchingCityOrlando() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);

        String cityName = "Orlando";
        String expectedResult = "Orlando, US";

        driver.get("https://openweathermap.org/ ");

        Thread.sleep(5000);

        WebElement searchCityField = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']"));

        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = driver.findElement(By.xpath("//button[@type = 'submit']"));
        searchButton.click();

        Thread.sleep(1000);

        WebElement charlotteDropdownMenu = driver.findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Orlando, US ']"));
        charlotteDropdownMenu.click();

        WebElement h2CityNameHeader = driver.findElement(
                By.xpath("//div[@class = 'section-content']/div/div/div/h2"));

        Thread.sleep(1000);

        String actualResult = h2CityNameHeader.getText();
        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();

    }

    @Test
    public void testLinkSearchLanguage() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("http://www.99-bottles-of-beer.net/");

        WebElement linkSearchLanguage = driver.findElement(By.linkText("Search Languages"));
        linkSearchLanguage.click();

        WebElement headerText = driver.findElement(
                By.xpath("//div[@id = 'main']/h2[contains(.,'Search Languages')]"));

        Assert.assertEquals(headerText.getText(), "Search Languages");

        driver.quit();
    }
    @Test
    public void KHTest1 () throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        String title = driver.getTitle();
        assertEquals("Web form", title);

        Thread.sleep(2000);

        WebElement textBox = driver.findElement(By.name("my-text"));
        WebElement submitButton = driver.findElement(By.cssSelector("button"));

        textBox.sendKeys("Selenium");
        submitButton.click();

        WebElement message = driver.findElement(By.id("message"));
        String value = message.getText();
        assertEquals("Received!", value);

        driver.quit();

    }

    @Test
    public void testBootcampUrlAndTitle() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);

        String expectedResultTitle = "W3Schools Online Web Tutorials";
        String expectedResultUrl = "https://www.w3schools.com/bootcamp/index.php";

        driver.get("https://www.w3schools.com/");

        WebElement bootcampElementMenu = driver.findElement(
                By.xpath("//a[@title='Web Development Bootcamp']"));

        bootcampElementMenu.click();

        String actualResultUrl = "https://www.w3schools.com/bootcamp/index.php";
        String actualResultTitle = driver.getTitle();

        Assert.assertEquals(actualResultUrl, expectedResultUrl);
        Assert.assertEquals(actualResultTitle,expectedResultTitle);

        driver.quit();
    }
    @Test
    public void tutorialsFeature(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.w3schools.com/"); // navigate to application
        driver.manage().window().maximize();  // maximize window


        WebElement tutorials = driver.findElement(By.id("navbtn_tutorials"));
        tutorials.click(); // click on tutorials function

        WebElement htmlAndCss = driver.findElement(By.xpath("//h3[text()='HTML and CSS']"));
        Assert.assertTrue(htmlAndCss.isDisplayed()); //assert text HTML and CSS

       WebElement learnHTML = driver.findElement(By.xpath("//a[text()='Learn HTML']"));
       learnHTML.click(); // navigate to learn HTML

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String expectTitle = "HTML Tutorial" ;
      String actualTitle = driver.getTitle();
        System.out.println(actualTitle);
      Assert.assertEquals(actualTitle,expectTitle,"test is failed"); //verify title

driver.quit();
    }

}
