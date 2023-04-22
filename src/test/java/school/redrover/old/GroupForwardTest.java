package school.redrover.old;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.List;

import static org.testng.Assert.assertEquals;
@Ignore
public class GroupForwardTest extends BaseTest {

    @Test
    public void testCommonComponents() throws InterruptedException {

        getDriver().get("https://yummymarket.com/");
        Thread.sleep(3000);

        WebElement searchField = getDriver().findElement(By.xpath("//input[@type = 'search']"));
        WebElement label = getDriver().findElement(By.xpath("//img[@alt= 'Yummy Market']"));
        WebElement navigationBar = getDriver().findElement(By.xpath("//nav[@id = 'site-navigation']"));
        WebElement copyrightInformation = getDriver().findElement(By.xpath("//div[@class='copyright-bar']"));

        assertEquals(getDriver().getTitle(), "Home - Yummy Market");
        Assert.assertTrue(searchField.isDisplayed());
        Assert.assertTrue(label.isDisplayed());
        Assert.assertTrue(navigationBar.isDisplayed());
        assertEquals(copyrightInformation.getText(), "© 2023 Yummy Market inc. All Rights Reserved.");
        Assert.assertTrue(copyrightInformation.isDisplayed());

    }

    @Test
    public void testAbleToSearch() throws InterruptedException {

        getDriver().get("https://yummymarket.com/");
        Thread.sleep(3000);
        WebElement searchField = getDriver().findElement(By.xpath("//input[@type = 'search']"));
        searchField.click();
        searchField.sendKeys("menu");

        WebElement searchButton = getDriver().findElement(By.xpath("//button[@class = 'is-search-submit']"));
        searchButton.click();
        Thread.sleep(3000);

        WebElement searchHeader = getDriver().findElement(By.xpath("//header[@class = 'page-header']/h1"));

        assertEquals(getDriver().getCurrentUrl(), "https://yummymarket.com/?s=menu&id=5067");
        assertEquals(searchHeader.getText(), "Search Results for: menu");

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

        assertEquals(getDriver().getCurrentUrl(), link + endpoint_inventory);
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
    public void testSearchIplayAmerica() {
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

        Assert.assertTrue(languageIList.size() > 0);

        for (WebElement element : languageIList) {
            Assert.assertTrue(element.getText().toUpperCase().startsWith("I"));
        }
    }

    @Test
    public void testSearchLanguages() {
        getDriver().get("https://www.99-bottles-of-beer.net/");

        WebElement searchLanguageTab = getDriver().findElement(By.xpath("//a[@href = '/search.html']"));
        searchLanguageTab.click();
        WebElement searchField = getDriver().findElement(By.xpath("//input[@name = 'search']"));
        searchField.sendKeys("ruby");
        WebElement goButton = getDriver().findElement(By.xpath("//input[@type = 'submit']"));
        goButton.click();
        List<WebElement> languageList = getDriver().findElements(By.xpath("//tr//a"));

        Assert.assertTrue(languageList.size() > 0);

        for (WebElement element: languageList) {
            Assert.assertTrue(element.getText().toLowerCase().contains("ruby"));
        }
    }

    @Test
    public void testOrchidCareIndoors(){

        getDriver().get("https://www.google.com/");

        WebElement textBox = getDriver().findElement(By.name("q"));
        textBox.sendKeys("orchid care indoors\n");
        WebElement text = getDriver().findElement(By.xpath
                ("//h3 [text() = 'How To Take Care Of Orchid Plants Indoors']"));

        Assert.assertEquals(text.getText(),"How To Take Care Of Orchid Plants Indoors");

    }
    @Test
    public void testMaimonidesMedicalCenter() {

        getDriver().get("https://www.google.com/");

        WebElement textBox = getDriver().findElement(By.name("q"));
        textBox.sendKeys("Maimonides Medical Center");
        textBox.sendKeys(Keys.RETURN);

        WebElement text = getDriver().findElement(By.xpath("//*[@class='g']/div[1]/div/div/div/div/div[1]/a/h3"));

        Assert.assertEquals(text.getText(), "Maimonides Medical Center | Brooklyn, New York Hospital");

    }

    @Test
    public void testSearchIplayAmerica1() {

        getDriver().get("https://www.google.com/");

        WebElement textBox = getDriver().findElement(By.name("q"));
        textBox.sendKeys("i play america");
        textBox.sendKeys(Keys.RETURN);

        WebElement text = getDriver().findElement(By.xpath("//h3[contains(text(),\"Home - iPlay America" +
                " - NJ's Premier Indoor Amuseme\")]"));

        Assert.assertEquals(text.getText(), "Home - iPlay America - NJ's Premier Indoor Amusement Park");

    }

    @Test
    public void testFirst1() throws InterruptedException {

        getDriver().get("https://www.selenium.dev/selenium/web/web-form.html");

        WebElement textBox = getDriver().findElement(By.cssSelector("#my-text-id"));
        textBox.sendKeys("Selenium");
        Thread.sleep(2000);

        WebElement submitButton = getDriver().findElement(By.cssSelector("button"));
        submitButton.click();
        Thread.sleep(2000);

        WebElement message = getDriver().findElement(By.id("message"));

        assertEquals(getDriver().getTitle(),"Web form - target page");
        assertEquals(message.getText(), "Received!");

    }
}

