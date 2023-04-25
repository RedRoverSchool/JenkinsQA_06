package school.redrover.old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
@Ignore
public class Bond4562Test extends BaseTest {

    @Ignore
    @Test
    public void testSwitchThemeYouTube() throws InterruptedException {
        getDriver().get("https://www.youtube.com/");

        WebElement topBarMenuButton = getDriver().findElement(
                By.xpath("//yt-icon[@class='style-scope ytd-topbar-menu-button-renderer']"));
        topBarMenuButton.click();

        Thread.sleep(1000);

        WebElement themeLinkButton = getDriver().findElement(
                By.xpath("//ytd-toggle-theme-compact-link-renderer"));
        themeLinkButton.click();

        WebElement darkThemeButton = getDriver().findElement(
                By.xpath("//yt-formatted-string[text() = 'Dark theme']"));
        darkThemeButton.click();

        Thread.sleep(1000);

        WebElement topBarMenuButtonNew = getDriver().findElement(
                By.xpath("//yt-icon[@class='style-scope ytd-topbar-menu-button-renderer']"));
        topBarMenuButtonNew.click();

        Thread.sleep(1000);

        WebElement darkTheme = getDriver().findElement(
                By.xpath("//ytd-toggle-theme-compact-link-renderer//div[@id='label']"));

        Assert.assertEquals(darkTheme.getText(), "Appearance: Dark");
    }

}
