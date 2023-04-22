package school.redrover.old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
@Ignore
public class SchoolWebTest {

    String[] links = {"Courses", "Training", "About school", "Teachers"};
    @Ignore
    @Test
    public void testCopyright() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://redrover.school");

        for (String link : links) {
            driver.findElement(new By.ByLinkText(link)).click();
            Thread.sleep(3000);
            String copyright = driver.findElement(By.xpath("//*[contains(text(), 'Copyright ©')]")).getText();
            Assert.assertEquals(copyright, "Copyright © 2022 RedRover School. All rights reserved");
        }

        driver.quit();
    }
}
