package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class GroupJavaQaTeamTest {
    @Ignore
    @Test
    public void testCheckTheCartIsEmpty() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);

        String expectedResult = "There's Nothing Here Yet";

        driver.get("https://www.homedepot.com/");

        WebElement searchButtonCart = driver.findElement(By.xpath("//span[@class ='MyCart__label' ]"));
        searchButtonCart.click();

        WebElement searchText = driver.findElement(
                By.xpath("//div[@class='empty-cart__message empty-cart__message--primary' and text()=\"There's Nothing Here Yet\"]"));

        Thread.sleep(2000);

        String actualResult = searchText.getText();
        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }
}
