package school.redrover.runner;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PolinaPavlidi {

    @Test
    public void testFindMaimonidesMedicalCenter() {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.google.com/");

        WebElement textBox = driver.findElement(By.name("q"));
        textBox.sendKeys("Maimonides Medical Center");
        textBox.sendKeys(Keys.RETURN);

        WebElement text = driver.findElement(By.xpath("//*[@class='g']/div[1]/div/div/div/div/div[1]/a/h3"));

        Assert.assertEquals(text.getText(), "Maimonides Medical Center | Brooklyn, New York Hospital");

        driver.quit();

    }


}
