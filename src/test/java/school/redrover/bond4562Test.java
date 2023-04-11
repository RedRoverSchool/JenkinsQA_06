package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class bond4562Test {

    @Ignore
    @Test
    public void testSwitchThemeYouTube() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://www.youtube.com/");

        WebElement topBarMenuButton = driver.findElement(
                By.xpath("//yt-icon[@class='style-scope ytd-topbar-menu-button-renderer']")
        );
        topBarMenuButton.click();

        Thread.sleep(1000);

        WebElement themeLinkButton = driver.findElement(
                By.xpath("//ytd-toggle-theme-compact-link-renderer")
        );
        themeLinkButton.click();

        WebElement darkThemeButton = driver.findElement(
                By.xpath("//yt-formatted-string[text() = 'Dark theme']")
        );
        darkThemeButton.click();

        Thread.sleep(1000);

        WebElement topBarMenuButtonNew = driver.findElement(
                By.xpath("//yt-icon[@class='style-scope ytd-topbar-menu-button-renderer']")
        );
        topBarMenuButtonNew.click();

        Thread.sleep(1000);

        WebElement darkTheme = driver.findElement(
                By.xpath("//ytd-toggle-theme-compact-link-renderer//div[@id='label']")
        );

        Assert.assertEquals(darkTheme.getText(), "Appearance: Dark");

        driver.quit();
    }

}
