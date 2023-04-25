package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class FooterTest extends BaseTest {

    @Test
    public void testRestAPILink() {
        getDriver().findElement(By.cssSelector("a[href='api/']")).click();

        Assert.assertEquals(getDriver().findElement(By.cssSelector("#main-panel > h1")).getText(), "REST API");
    }

    @Ignore
    @Test
    public void testJenkinsFooterLink() {
        getDriver().findElement(By.cssSelector("a[rel='noopener noreferrer']")).click();

        for(String winHandle : getDriver().getWindowHandles()) {
            getDriver().switchTo().window(winHandle);
        }

        Assert.assertEquals(getDriver().findElement(By.cssSelector("h1[class='page-title'] > span")).getText().trim(), "Jenkins");
    }
}
