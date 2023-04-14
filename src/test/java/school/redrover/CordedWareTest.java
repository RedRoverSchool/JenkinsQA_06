package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CordedWareTest {

    private static final String URL_BOTTLES = "https://www.99-bottles-of-beer.net/";

    @Ignore
    @Test
    public void testTitlePageBottles99() {
        WebDriver driver = new ChromeDriver();
        driver.get(URL_BOTTLES);

        String getTitle = driver.getTitle();
        assertEquals("99 Bottles of Beer | Start", getTitle);

        driver.quit();
    }
    
    @Ignore
    @Test
    public void testH2StartPageBottles99() {
        WebDriver driver = new ChromeDriver();
        driver.get(URL_BOTTLES);

        WebElement textH2 = driver.findElement(By.xpath("//div[3]/h2"));
        String getH2Text = textH2.getText();
        assertEquals("Welcome to 99 Bottles of Beer", getH2Text);

        driver.quit();
    }

    @Ignore
    @Test
    public void testPageSearchLanguages() {
        WebDriver driver = new ChromeDriver();
        driver.get(URL_BOTTLES);

        WebElement clickPageSearchLanguages = driver.findElement(By.xpath("//ul[1]/li[3]/a"));
        clickPageSearchLanguages.click();

        WebElement sendTextToInput = driver.findElement(By.name("search"));
        sendTextToInput.sendKeys("Java");

        WebElement submitButtonSearchGo = driver.findElement(By.name("submitsearch"));
        submitButtonSearchGo.click();

        WebElement tableValue = driver.findElement(By.xpath("//tr[3]/td[1]/a"));
        String getTableValue = tableValue.getText();

        assertEquals("Java", getTableValue);

        driver.quit();
    }
}
