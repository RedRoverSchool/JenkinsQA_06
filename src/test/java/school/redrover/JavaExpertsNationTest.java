package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class JavaExpertsNationTest extends BaseTest {
    @Test
    public void testManageTitle() {
        getDriver().findElement(By.cssSelector("a[href='/manage']")).click();

        WebElement manageTitle = getDriver().findElement(By.tagName("h1"));
        WebElement securityTitle = getDriver().findElement(By.cssSelector("#main-panel section:nth-child(6) h2"));

        Assert.assertEquals(manageTitle.getText(), "Manage Jenkins");
        Assert.assertEquals(securityTitle.getText(), "Security");

        WebElement systemConfigurationTitle = getDriver().
                findElement(By.xpath("//*[@id=\"main-panel\"]/section[2]/h2"));

        Assert.assertEquals(systemConfigurationTitle.getText(), "System Configuration");
    }
}
