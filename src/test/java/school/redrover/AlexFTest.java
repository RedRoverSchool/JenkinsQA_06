package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class AlexFTest {
    @Test
    public void titleOfTheHomePageCheckedTest() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        driver.get("https://askomdch.com/");
        Assert.assertEquals(driver.getTitle(), "AskOmDch – Become a Selenium automation expert!");
        driver.findElement(By.xpath("//a[@class='wp-block-button__link']")).click();
        WebElement icon = driver.findElement(By.xpath("//span[@class='onsale']"));
        Assert.assertEquals(icon.getText(), "Sale!");
        driver.quit();
    }

    @Test
    public void testOfTextFieldAcceptance() throws InterruptedException {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        WebElement elementTextInput = driver.findElement(By.id("my-text-id"));
        elementTextInput.sendKeys("We're all learning Java");

        WebElement elementPassword = driver.findElement(By.name("my-password"));
        elementPassword.sendKeys("Google0000!");

        WebElement elementTextArea = driver.findElement(By.name("my-textarea"));
        elementTextArea.sendKeys("To get better result you need to spend no less than 2 hours daily with exercises and challenges.");

        Select selector1 = new Select(driver.findElement(By.name("my-select")));
        selector1.selectByIndex(2);

        driver.quit();
    }
}
