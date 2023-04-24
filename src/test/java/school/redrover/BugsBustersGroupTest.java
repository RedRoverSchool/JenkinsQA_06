package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;


public class BugsBustersGroupTest extends BaseTest {

    @Test
    public void testAngelinaHeadIconIsDisplayed() {
        WebElement icon = getDriver().findElement(By.id("jenkins-head-icon"));

        Assert.assertTrue(icon.isDisplayed());
    }

    @Test
    public void testAngelinaDashboardButtonIsDisplayed() {
        WebElement dashboardButton = getDriver().findElement(By.xpath("//button[@class='jenkins-menu-dropdown-chevron']"));

        Assert.assertTrue(dashboardButton.isDisplayed());
    }
}