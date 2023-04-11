package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GroupForwardTest {

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

    @Test
    public void testAbleToSearch() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://yummymarket.com/");

        WebElement searchField = driver.findElement(
                By.xpath("//input[@type = 'search']")
        );
        searchField.click();
        searchField.sendKeys("menu");

        WebElement searchButton = driver.findElement(
                By.xpath("//button[@class = 'is-search-submit']")
        );
        searchButton.click();
        Thread.sleep(3000);

        WebElement searchHeader = driver.findElement(
                By.xpath("//header[@class = 'page-header']/h1")
        );


        Assert.assertEquals(driver.getCurrentUrl(), "https://yummymarket.com/?s=menu&id=5067");

        Assert.assertEquals(searchHeader.getText(), "Search Results for: menu");

        driver.quit();
    }
    
    @Test
    public void SauceDemoLoginSuccessTest() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*","--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(options);
        String link = "https://www.saucedemo.com/";
        String endpoint_inventory = "inventory.html";
        driver.get(link);

        WebElement username = driver.findElement(By.cssSelector("#user-name"));
        username.sendKeys("standard_user");
        WebElement password = driver.findElement(By.cssSelector("#password"));
        password.sendKeys("secret_sauce");
        WebElement loginButton = driver.findElement(By.cssSelector("#login-button"));
        loginButton.sendKeys(Keys.ENTER);
        Assert.assertEquals(driver.getCurrentUrl(), link+endpoint_inventory);

        driver.quit();
      }

}

