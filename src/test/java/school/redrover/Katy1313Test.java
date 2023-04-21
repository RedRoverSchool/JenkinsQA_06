package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class Katy1313Test extends BaseTest {

    @Test
    public void testGoogleSearch() {
        getDriver().get("https://google.com");

        WebElement searchBox = getDriver().findElement(By.name("q"));
        searchBox.sendKeys("dracena\n");
        WebElement text = getDriver().findElement(By.xpath("//h3[contains (text(),'How to Care for Dracaena: Types & Growing Tips')]"));

        Assert.assertEquals(text.getText(), "How to Care for Dracaena: Types & Growing Tips");
    }
}
