package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GroupCAT {
    @Test
    public void textVerification() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(options);

        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        WebElement mouseHoverOver = driver.findElement(By.id("mousehover"));

        String actualText = mouseHoverOver.getText();
        String expectedText = "Mouse Hover";

        Assert.assertEquals(actualText, expectedText, "Text verification is FAILED");

        driver.quit();

    }

    @Test
    public void dragAndDrop() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(options);

        driver.get("https://formy-project.herokuapp.com/dragdrop");

        WebElement image = driver.findElement(By.id("image"));

        WebElement box = driver.findElement(By.id("box"));

        Actions actions = new Actions(driver);

        actions.dragAndDrop(image, box).perform();

        driver.quit();

    }

    @Test

    public void testVerifyRedirectFromJetLabel() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.jetbrains.com/");

        Thread.sleep(3000);

        WebElement label = driver.findElement(By.xpath("//a[@aria-label='Navigate to main page']"));
        label.click();

        WebElement title = driver.findElement(By.xpath("//h1[@class='rs-h1 rs-h1_theme_dark home-page__title']"));

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.jetbrains.com/");
        Assert.assertEquals(title.getText(), "Essential tools for software developers and teams");

        driver.quit();

    }

    @Test

    public void testVerifyLink() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://smartbear.com/");

        Thread.sleep(3000);

        WebElement carrer = driver.findElement(By.cssSelector("li:nth-child(4) > a"));
        carrer.click();

        Assert.assertEquals(driver.getCurrentUrl(),"https://smartbear.com/company/careers/");

        driver.quit();
    }
}
