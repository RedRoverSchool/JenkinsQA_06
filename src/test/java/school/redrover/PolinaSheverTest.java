package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.*;

import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.Select;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class PolinaSheverTest {

    @Test
    public void iGoToLookupZIPPageByAddress() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");


        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.usps.com/");


        WebElement mailAndSnip = driver.findElement(By.xpath("//a[@id='mail-ship-width']"));
        new Actions(driver).moveToElement(mailAndSnip).perform();

        Thread.sleep(2000);


        driver.findElement(By.xpath("//li[@class='tool-zip']//a")).click();
        driver.findElement(By.xpath("//a[contains(@class,'zip-code-address')]")).click();

        String zip = "10005";
        String street = "11 Wall St";
        String city = "New York";
        String state = "NY";

        WebElement stateElement = driver.findElement(By.xpath("//select[@id='tState']"));
        new Select(stateElement).selectByValue(state);

        driver.findElement(By.xpath("//input[@id='tAddress']")).sendKeys(street);
        driver.findElement(By.xpath("//input[@id='tCity']")).sendKeys(city);
        driver.findElement(By.xpath("//a[@id='zip-by-address']")).click();

        driver.findElement(By.xpath("//div[@id='zipByAddressDiv']"));
        Thread.sleep(3000);

        List<WebElement> list = driver.findElements(By.xpath("//*[@class='zipcode-result-address']"));
        for (WebElement item : list) {
            String itemText = item.getText();
            System.out.println(item.getText());
            System.out.println(list.size());

            Assert.assertTrue(itemText.contains(zip), "zip");
        }

        driver.close();

    }

}
