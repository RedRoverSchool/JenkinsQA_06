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

public class GroupTwentyThreeTest extends BaseTest {
    @Test
    public void testJavaTutorial() throws InterruptedException {
        getDriver().get("https://www.w3schools.com/");

        WebElement textBox = getDriver().findElement(By.id("search2"));

        textBox.sendKeys("Java Tutorial\n");

        Thread.sleep(2000);

        WebElement text = getDriver().findElement(By.xpath("//h1"));

        Assert.assertEquals(text.getText(), "Java Tutorial");
    }

    @Test
    public void testBaha1() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://demoqa.com/text-box");

        WebElement fullName = driver.findElement(By.id("userName"));
        fullName.sendKeys("Baktygul");
        Thread.sleep(2000);

        WebElement email = driver.findElement(By.id("userEmail"));
        email.sendKeys("baktygul.jekshembieva@gmail.com");
        Thread.sleep(2000);

        WebElement currentAddress = driver.findElement(By.id("currentAddress"));
        currentAddress.sendKeys("Biskek");
        Thread.sleep(2000);

        WebElement permanentAddress = driver.findElement(By.id("permanentAddress"));
        permanentAddress.sendKeys("Kerben");
        Thread.sleep(2000);

        WebElement submit = driver.findElement(By.id("submit"));
        submit.click();
        Thread.sleep(2000);

        WebElement name = driver.findElement(By.id("name"));
        Assert.assertEquals(name.getText(), "Name:Baktygul");

        driver.quit();
    }
}
