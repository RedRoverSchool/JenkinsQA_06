package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
import java.util.ArrayList;

import static org.testng.Assert.assertEquals;

public class GroupJavaQaTeamTest extends BaseTest {

    @Test
    public void testFirst() throws InterruptedException {

        getDriver().get("https://www.selenium.dev/selenium/web/web-form.html");

        String title = getDriver().getTitle();
        assertEquals("Web form", title);

        WebElement textBox = getDriver().findElement(By.name("my-text"));
        WebElement submitButton = getDriver().findElement(By.cssSelector("button"));

        Thread.sleep(3000);

        textBox.sendKeys("Selenium");

        Thread.sleep(3000);

        submitButton.click();

        Thread.sleep(3000);

        WebElement message = getDriver().findElement(By.id("message"));
        String value = message.getText();
        assertEquals("Received!", value);

        Thread.sleep(3000);

    }

    @Ignore
    @Test
    public void testH2TagText_WhenSearchingCityOrlando() throws InterruptedException {

        String cityName = "Orlando";
        String expectedResult = "Orlando, US";

        getDriver().get("https://openweathermap.org/ ");

        Thread.sleep(5000);

        WebElement searchCityField = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']"));

        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = getDriver().findElement(By.xpath("//button[@type = 'submit']"));
        searchButton.click();

        Thread.sleep(3000);

        WebElement charlotteDropdownMenu = getDriver().findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Orlando, US ']"));
        charlotteDropdownMenu.click();

        WebElement h2CityNameHeader = getDriver().findElement(
                By.xpath("//div[@class = 'section-content']/div/div/div/h2"));

        Thread.sleep(3000);

        String actualResult = h2CityNameHeader.getText();
        Assert.assertEquals(actualResult, expectedResult);

    }

    @Test
    public void testLinkSearchLanguage() {

        getDriver().get("http://www.99-bottles-of-beer.net/");

        WebElement linkSearchLanguage = getDriver().findElement(By.linkText("Search Languages"));
        linkSearchLanguage.click();

        WebElement headerText = getDriver().findElement(
                By.xpath("//div[@id = 'main']/h2[contains(.,'Search Languages')]"));

        Assert.assertEquals(headerText.getText(), "Search Languages");

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
    public void testBookingTitle() {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);


        driver.get("https://www.booking.com");


        Assert.assertTrue(driver.getTitle().contains("Booking.com"));


        driver.quit();
    }

    @Ignore
    @Test
    public void testBootcampUrlAndTitle() {

        String expectedResultTitle = "W3Schools Online Web Tutorials";
        String expectedResultUrl = "https://www.w3schools.com/bootcamp/index.php";

        getDriver().get("https://www.w3schools.com/");

        WebElement bootcampElementMenu = getDriver().findElement(
                By.xpath("//a[@title='Web Development Bootcamp']"));

        bootcampElementMenu.click();

        String actualResultUrl = "https://www.w3schools.com/bootcamp/index.php";
        String actualResultTitle = getDriver().getTitle();

        Assert.assertEquals(actualResultUrl, expectedResultUrl);
        Assert.assertEquals(actualResultTitle,expectedResultTitle);

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

    @Test
    public void testClickOnTheLogoAndCheckTheLinkNotChange() {

        String url = "https://www.w3schools.com/";

        getDriver().get(url);

        WebElement imageBanner = getDriver().findElement(By.xpath("//i[@class ='fa fa-logo']"));
        imageBanner.click();

        String expectedResult = "https://www.w3schools.com/";
        String actualResult = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResult,expectedResult);

    }

    @Test
    public void testReceivedAnswerResult() {

        String url = "https://www.w3schools.com/";
        String expectedResult = "Correct!";

        getDriver().get(url);

        WebElement searchField = getDriver().findElement(
                By.xpath("//input[@placeholder ='Search our tutorials, e.g. HTML']"));
        searchField.sendKeys("Java Tutorial");
        searchField.sendKeys(Keys.RETURN);

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        WebElement inputField1 = getDriver().findElement(By.xpath("//input[@name ='ex1']"));
        inputField1.sendKeys("System");

        WebElement inputField2 = getDriver().findElement(By.xpath("//input[@name ='ex2']"));
        inputField2.sendKeys("out");

        WebElement inputField3 = getDriver().findElement(By.xpath("//input[@name ='ex3']"));
        inputField3.sendKeys("println");

        WebElement findSubmitButton = getDriver().findElement(By.xpath("//button[@type= 'submit']"));
        findSubmitButton.click();

        ArrayList<String> words = new ArrayList<>(getDriver().getWindowHandles());
        getDriver().switchTo().window(words.get(1));

        WebElement submitAnswer = getDriver().findElement(By.xpath("//button[@id ='answerbutton']"));
        submitAnswer.click();

        WebElement result = getDriver().findElement(By.xpath("//h2[text() = 'Correct!']"));
        String actualResult = result.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }
}
