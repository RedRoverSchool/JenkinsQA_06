package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Katy1313Test {

    @Test
    public void testGoogleSearch() {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://google.com");

        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("dracena\n");

        WebElement text = driver.findElement(
                By.xpath("//h3[contains (text(),'How to Care for Dracaena: Types & Growing Tips')]"));

        Assert.assertEquals(text.getText(), "How to Care for Dracaena: Types & Growing Tips");

    }

}
