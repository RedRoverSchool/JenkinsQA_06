package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GroupForwardTest {
    @Test
    public void testPageHasCommonComponents() throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.get("https://yummymarket.com/");
        Thread.sleep(5000);


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

        Assert.assertEquals(driver.getTitle(), "Home - Yummy Market");

        Assert.assertTrue(searchField.isDisplayed());
        Assert.assertTrue(label.isDisplayed());
        Assert.assertTrue(navigationBar.isDisplayed());

        Assert.assertEquals(copyrightInformation.getText(), "© 2023 Yummy Market inc. All Rights Reserved.");
        Assert.assertTrue(copyrightInformation.isDisplayed());

        driver.quit();
    }

}
