package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GroupTwentyThreeTest {
    @Test
    public void testJavaTutorial() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.w3schools.com/");

        WebElement textBox = driver.findElement(By.id("search2"));

        textBox.sendKeys("Java Tutorial\n");

        Thread.sleep(2000);

        WebElement text = driver.findElement(By.xpath("//h1"));

        Assert.assertEquals(text.getText(), "Java Tutorial");

        driver.quit();
    }

    @Test
    public void testBaku11() throws InterruptedException {
//
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://demoqa.com/text-box");

        Thread.sleep(2000);

        WebElement fullName = driver.findElement(By.id("userName"));
        fullName.sendKeys("Baktygul");

        Thread.sleep(2000);

        WebElement email = driver.findElement(By.id("userEmail"));
        email.sendKeys("baktygul.jekshembieva@gmail.com");

        Thread.sleep(2000);

        WebElement currentAddress = driver.findElement(By.id("currentAddress"));
        currentAddress.sendKeys("Bishkek");

        Thread.sleep(2000);

        WebElement permanentAddres = driver.findElement(By.id("permanentAddress"));
        permanentAddres.sendKeys("Kerben");

        Thread.sleep(2000);

        WebElement submit = driver.findElement(By.id("submit"));
        submit.click();

        Thread.sleep(2000);

        WebElement name = driver.findElement(By.id("name"));
        String value =name.getText();
        assertEquals("Name:Baktygul", value);

        Thread.sleep(2000);

        driver.quit();
    }
}
