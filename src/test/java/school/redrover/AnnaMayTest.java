package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class AnnaMayTest {

    @Test
    public void uspsTitle() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        // Set up the WebDriver instance
        WebDriver driver = new ChromeDriver(chromeOptions);

        // Navigate to the test URL
        driver.get("https://www.usps.com/");

        String actualResult;
        actualResult = driver.getTitle();
        String expectedResult = "Welcome | USPS";
        Assert.assertEquals(actualResult, expectedResult);
        driver.close();
    }

    @Test
    public void uspsSearchTest() throws InterruptedException {


        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.usps.com/");


        WebElement searchTextBox = driver.findElement(By.xpath("//input[@id='home-input']"));

        searchTextBox.sendKeys("stamps");
        searchTextBox.sendKeys(Keys.RETURN);

        Thread.sleep(2000); //this wait needed for handling processing result and going to another page

        WebElement resultText = driver.findElement(By.xpath("//span[@id='recommendedResults']"));

        String actualResult;
        actualResult = resultText.getText();
        String expectedResult = "Most Relevant Results for stamps";
        Assert.assertEquals(actualResult, expectedResult);

        driver.close();
    }

    @Ignore
    @Test
    public void uspsHoverOnSubElementTest() {


        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        // Create a new instance of the ChromeDriver
        WebDriver driver = new ChromeDriver(chromeOptions);

        // Navigate to the webpage
        driver.get("https://www.usps.com/");

        // Locate the element to be hovered over
        WebElement navBarQuickTools = driver.findElement(By.xpath("//a[@class='nav-first-element menuitem']"));

        // Create an instance of Actions class and pass the driver instance to its constructor
        Actions action = new Actions(driver);

        // Use the moveToElement() method to move the mouse cursor to the element
        action.moveToElement(navBarQuickTools).build().perform();

        // After hovering over the element, perform some action
        // For example, click on a sub-element that appears after hovering
        WebElement trackAPackage = driver.findElement(By.xpath("//p[contains(text(),'Track a Package')]"));
        trackAPackage.click();

        //view Tracking tab on a right corner
        WebElement resultTextTrackAPackage = driver.findElement(By.cssSelector("div.subheader_links a:nth-child(1)"));
        String actualResult = resultTextTrackAPackage.getText();
        String expectedResult = "Tracking";

        Assert.assertEquals(actualResult, expectedResult);
        driver.close();
    }
}
