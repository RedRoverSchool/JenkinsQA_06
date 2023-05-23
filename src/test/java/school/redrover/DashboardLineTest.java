package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class DashboardLineTest extends BaseTest {

    @Test
    public void testVerifyDashboardDropdownMenuOptionsName() {

        WebElement dashboardLink = getDriver()
                .findElement(By.xpath("//li[@class='jenkins-breadcrumbs__list-item']"));

        new Actions(getDriver())
                .moveToElement(dashboardLink)
                .perform();

        WebElement dashboardDropdownMenu = getDriver()
                .findElement(By.xpath("//div[@id='breadcrumbBar']//button[@class='jenkins-menu-dropdown-chevron']"));

        getWait2().until(ExpectedConditions.elementToBeClickable(dashboardDropdownMenu));

        dashboardDropdownMenu.click();

        Assert.assertEquals(getDriver().
                findElement(By.xpath("//div[@id='breadcrumb-menu']//span[text()='People']")).getText(), "People");
    }
}
