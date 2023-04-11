package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GroupCodePro {

    @Test
    public void testKravisRegistration() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.kravis.org//");

        WebElement buttonLogin = driver
                .findElement(By.xpath("//a[@id='calendar']"));
        buttonLogin.click();
        Thread.sleep(2000);

        WebElement email = driver.findElement(By.id("EMAIL"));
        email.sendKeys("mail1@gmail.com");
        WebElement password = driver.findElement(By.id("PASSWORD"));
        password.sendKeys("mail123");
        WebElement firstName = driver.findElement(By.id("FIRST"));
        firstName.sendKeys("Anna");
        WebElement lastName = driver.findElement(By.id("LAST"));
        lastName.sendKeys("Smith");
        WebElement address = driver.findElement(By.id("ADDRESS1"));
        address.sendKeys("West 123");
        WebElement city = driver.findElement(By.id("CITY"));
        city.sendKeys("West Palm Beach");
        WebElement state = driver.findElement(By.id("STATE"));
        state.sendKeys("FL");
        WebElement zip = driver.findElement(By.id("ZIP"));
        zip.sendKeys("33124");
        WebElement phone = driver.findElement(By.id("DAY_PHONE"));
        phone.sendKeys("2482676867");
        WebElement buttonSubmit = driver
                .findElement(By.xpath("//input[@id='GUEST_INFO']"));
        buttonSubmit.click();
        Thread.sleep(2000);

        WebElement pageEvents = driver
                .findElement(By.xpath("//button[@id='tab-event-list']"));

        Assert.assertEquals(pageEvents.getText(), "EVENT LIST");

        driver.quit();
    }
}
