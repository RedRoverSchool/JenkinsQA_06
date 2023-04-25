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

import java.util.ArrayList;

@Ignore
public class DmTest {

    @Test
    public void testThird() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.w3schools.com/");

        WebElement search;
        search = driver.findElement(By.xpath("//form/input[@id = \"search2\"]"));
        search.sendKeys("HTML Tutorial");
        search.sendKeys(Keys.RETURN);

        Thread.sleep(5000);

        WebElement textBox;
        textBox = driver.findElement(By.xpath("//input[@name = \"ex1\"]"));
        textBox.sendKeys("title");

        Thread.sleep(5000);

        WebElement submitConfirm;
        submitConfirm = driver.findElement(By.xpath("//*[@id=\"w3-exerciseform\"]/div/button"));
        submitConfirm.click();

        Thread.sleep(5000);
        ArrayList<String> words = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(words.get(1));

        WebElement submit = driver.findElement(By.xpath("//button[@id='answerbutton']"));
        submit.click();

        WebElement result;
        result = driver.findElement(By.xpath("//*[@id=\"assignmentCorrect\"]/h2"));

        Assert.assertEquals(result.getText(), "Correct!");

        driver.quit();
    }
}
