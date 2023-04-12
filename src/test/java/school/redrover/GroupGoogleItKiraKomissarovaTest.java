package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GroupGoogleItKiraKomissarovaTest {

    @Test
     public void bottlesTest() throws InterruptedException {
        String expectedResult = "Kotlin";

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://www.99-bottles-of-beer.net/");
        WebElement searchButton = driver.findElement(By.xpath("//a[@href = '/search.html']"));

        searchButton.click();

        WebElement searchBar = driver.findElement(By.xpath("//input[@name = 'search']"));

        Thread.sleep(3000);
        searchBar.sendKeys("Kotlin");

        WebElement goButton = driver.findElement(By.name("submitsearch"));
        goButton.click();

        WebElement kotlinInTable = driver.findElement(By.xpath("//a[@href = '/language-kotlin-2901.html']"));

        Assert.assertEquals(kotlinInTable.getText(),expectedResult);
        driver.quit();








    }
}
