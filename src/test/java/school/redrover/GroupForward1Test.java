package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GroupForward1Test {

    @Test
    public void testCommonComponents() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://yummymarket.com/");
        Thread.sleep(3000);
        String actualResultTitle = driver.getTitle();

        WebElement searchField = driver.findElement(
                By.xpath("//input[@type = 'search']")
        );
        WebElement label = driver.findElement(
                By.xpath("//img[@alt= 'Yummy Market']")
        );
        WebElement navigationBar = driver.findElement(
                By.xpath("//nav[@id = 'site-navigation']")
        );
        WebElement copyrightInformation = driver.findElement(
                By.xpath("//div[@class='copyright-bar']")
        );

        Assert.assertEquals(actualResultTitle, "Home - Yummy Market");
        Assert.assertTrue(searchField.isDisplayed());
        Assert.assertTrue(label.isDisplayed());
        Assert.assertTrue(navigationBar.isDisplayed());
        Assert.assertEquals(copyrightInformation.getText(), "© 2023 Yummy Market inc. All Rights Reserved.");
        Assert.assertTrue(copyrightInformation.isDisplayed());

        driver.quit();
    }
}
