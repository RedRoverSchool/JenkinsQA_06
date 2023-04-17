package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GoogleTest {

    @Test
    public void testGoogleMainPageTitle() {
        // Set the path to the chromedriver executable
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");

        // Create a new WebDriver instance
        WebDriver driver = new ChromeDriver();

        // Navigate to the Google homepage
        driver.get("https://www.google.com/");

        // Find the first line text element on the page
        WebElement textElement = driver.findElement(By.xpath("//div[@id='hplogo']//span"));

        // Verify that the first line text element contains the text "Google"
        String actualText = textElement.getText();
        String expectedText = "Google";
        Assert.assertEquals(actualText, expectedText);

        // Quit the driver and close all windows
        driver.quit();
    }
}
