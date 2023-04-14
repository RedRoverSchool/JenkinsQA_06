package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GroupSomeGroupTest {

    @Test
    public void testEmailContactPage() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://askomdch.com/");

        driver.findElement(By.cssSelector("#ast-desktop-header  a[href$='/contact-us/']")).click();

        WebElement email = driver.findElement(
                By.xpath("//p[contains(text(), 'Email')]/strong"));

        Assert.assertEquals(email.getText(), "askomdch@gmail.com");

        driver.quit();
    }

    @Test
    public void testVerifyTitle() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("http://w3schools.com");

        WebElement textBox = driver.findElement(By.id("search2"));
        WebElement submitButton = driver.findElement(By.cssSelector("button"));

        textBox.sendKeys("JS");
        submitButton.click();

        driver.manage().window().maximize();
        String title = driver.getTitle();

        Assert.assertEquals(title, "JavaScript Tutorial");
    }
}