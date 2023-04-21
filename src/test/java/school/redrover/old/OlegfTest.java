package school.redrover.old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.List;
@Ignore
public class OlegfTest {

    @Test
    public void dishesTest() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://www.canvashomestore.com/");

        driver.findElement(By.name("q")).sendKeys("plate\n");
        List<WebElement> platesList = driver.findElements(By.xpath("//main//div[@class='grid__item']"));

        String finalPlate;
        for (int i = 0; i < platesList.size(); i++) {
            finalPlate = platesList.get(i).getText();
            assert finalPlate.contains("Blue");
        }
        driver.quit();
    }
}
