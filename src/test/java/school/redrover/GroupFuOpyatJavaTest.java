package test.java.school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GroupFuOpyatJavaTest {

    @Test
    public void testCheckbox1() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://demoqa.com/");

        WebElement textE = driver.findElement(By.xpath("//h5[normalize-space()='Elements']"));
        textE.click();

        Thread.sleep(1000);

        WebElement textCB = driver.findElement(By.xpath("//span[normalize-space()='Check Box']"));
        textCB.click();

        Thread.sleep(1000);

        WebElement buttonE = driver.findElement(By.xpath("//button[@title='Expand all']"));
        buttonE.click();

        Assert.assertEquals("Notes", "Notes");

        Thread.sleep(1000);

        WebElement buttonC = driver.findElement(By.xpath("//button[@title='Collapse all']"));
        buttonC.click();

        Thread.sleep(1000);

        driver.quit();
    }


}
