package school.redrover;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Group99BottlesTest {

    @Test
    public void testTitleBasePage() {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://petstore.octoperf.com/actions/Catalog.action");

        Assert.assertEquals(driver.getTitle(), "JPetStore Demo");

        driver.quit();
    }
}
