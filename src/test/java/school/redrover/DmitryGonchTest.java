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

public class DmitryGonchTest extends BaseTest {

    @Test
    public void testFindElement() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://7745.by/");

        WebElement texBox = driver.findElement(By.name("keys"));
        texBox.sendKeys("процессор");
        texBox.sendKeys(Keys.RETURN);

        WebElement text = driver.findElement(By.xpath("//h1[text() = 'Процессоры для компьютеров']"));

        Assert.assertEquals(text.getText(), "Процессоры для компьютеров");
        driver.quit();
    }
}
