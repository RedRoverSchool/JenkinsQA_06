package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class GroupForwardTest extends BaseTest {

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

        assertEquals(actualResultTitle, "Home - Yummy Market");
        Assert.assertTrue(searchField.isDisplayed());
        Assert.assertTrue(label.isDisplayed());
        Assert.assertTrue(navigationBar.isDisplayed());
        assertEquals(copyrightInformation.getText(), "© 2023 Yummy Market inc. All Rights Reserved.");
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


        assertEquals(driver.getCurrentUrl(), "https://yummymarket.com/?s=menu&id=5067");

        assertEquals(searchHeader.getText(), "Search Results for: menu");

        driver.quit();
    }

    @Test
    public void sauceDemoLoginSuccessTest() {

        String link = "https://www.saucedemo.com/";
        String endpoint_inventory = "inventory.html";
        getDriver().get(link);

        WebElement username = getDriver().findElement(By.cssSelector("#user-name"));
        username.sendKeys("standard_user");
        WebElement password = getDriver().findElement(By.cssSelector("#password"));
        password.sendKeys("secret_sauce");
        WebElement loginButton = getDriver().findElement(By.cssSelector("#login-button"));
        loginButton.sendKeys(Keys.ENTER);

        assertEquals(getDriver().getCurrentUrl(), link+endpoint_inventory);
      }

      @Test
    public void TestDemo() throws InterruptedException {

          ChromeOptions chromeOptions = new ChromeOptions();
          chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

          WebDriver driver = new ChromeDriver(chromeOptions);
          driver.get("https://www.selenium.dev/selenium/web/web-form.html");

          String title = driver.getTitle();
          assertEquals("Web form", title);

          Thread.sleep(3000);

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
    public void testSearchIplayAmerica()  {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.google.com/");

        WebElement textBox = driver.findElement(By.name("q"));
        textBox.sendKeys("i play america");
        textBox.sendKeys(Keys.RETURN);

        WebElement text = driver.findElement(By.xpath("//h3[contains(text(),\"Home - iPlay America - NJ's Premier Indoor Amuseme\")]"));

        assertEquals(text.getText(), "Home - iPlay America - NJ's Premier Indoor Amusement Park");

        driver.quit();
    }


    @Test

    public void testFirst() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        String title = driver.getTitle();
        assertEquals("Web form", title);


        WebElement textBox = driver.findElement(By.cssSelector("#my-text-id"));
        WebElement submitButton = driver.findElement(By.cssSelector("button"));
        Thread.sleep(2000);

        textBox.sendKeys("Selenium");

        Thread.sleep(2000);

        submitButton.click();

        Thread.sleep(2000);

        WebElement message = driver.findElement(By.id("message"));
        String value = message.getText();
        assertEquals("Received!", value);

        Thread.sleep(2000);

        driver.quit();
    }

    @Test
    public void testBrowseLanguagesLanguageI() {
        getDriver().get("https://www.99-bottles-of-beer.net/");

        WebElement browseLanguageTab = getDriver().findElement(By.xpath("//a[@href = '/abc.html'][1]"));
        browseLanguageTab.click();
        WebElement languageLetterI = getDriver().findElement(By.xpath("//a[@href = 'i.html']"));
        languageLetterI.click();
        List<WebElement> languageIList = getDriver().findElements(By.xpath("//table[@id = 'category']/tbody/tr/td[1]/a"));

        Assert.assertTrue(languageIList.size()>0);

        for (WebElement element : languageIList) {
            Assert.assertTrue(element.getText().toUpperCase().startsWith("I"));
        }
    }
}

