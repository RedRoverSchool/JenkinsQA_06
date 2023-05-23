package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.*;

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

//        Assert.assertEquals(getDriver().
//                findElement(By.xpath("//div[@id='breadcrumb-menu']//span[text()='People']")).getText(), "People");

        List<String> expectedText = Arrays.asList("New Item", "People", "Build History", "Manage Jenkins", "My Views");


        List<WebElement> menuList = getDriver()
                .findElements(By.xpath("//div[@id='breadcrumb-menu-target']//li[@class='yuimenuitem first-of-type']/parent::ul/li"));
//        for(WebElement el : menuList) {
//
//            Assert.assertEquals(el.getText(), expectedText);
//        }

//        for (int i = 0; i < 5; i++) {
//            Assert.assertEquals(menuList.get(i).getText(), expectedText[i]);

        }
    }
}
