import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AleksieievaTest {

    @Test


    public void testDemoqaUrl() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://demoqa.com/");

        Assert.assertEquals(driver.getCurrentUrl(), "https://demoqa.com/");

        driver.quit();
    }


    @Test
    public void testDemoqaButton(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://demoqa.com/books");

        driver.findElement(By.id("login")).click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://demoqa.com/login");

        driver.quit();
    }
}




