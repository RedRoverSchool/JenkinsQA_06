package school.redrover.old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import static org.openqa.selenium.Keys.RETURN;
@Ignore
public class ElenaKarevaTest extends BaseTest {

    @Test
    public void testFirst() throws InterruptedException {
        getDriver().get("https://www.google.com/");

        WebElement textBox = getDriver().findElement(By.name("q"));
        textBox.sendKeys("selenium");
        textBox.sendKeys(RETURN);

        Thread.sleep(2000);

        WebElement text = getDriver().findElement(By.xpath("//h3[text()='Selenium']"));

        Assert.assertEquals(text.getText(), "Selenium");
    }
}
