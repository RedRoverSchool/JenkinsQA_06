package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.Arrays;
import java.util.List;

public class DashboardBreadcrumbsTest extends BaseTest {
    private final By dashboardButtonChevron = By.xpath("//a[@href='/']/button");
    private final By dashboardDropdownMenu = By.cssSelector("#breadcrumb-menu>div:first-child>ul>li");

    @Test
    public void testDashboardDropdownMenu() {
        new Actions(getDriver())
                .moveToElement(getDriver()
                .findElement(dashboardButtonChevron))
                .click()
                .build()
                .perform();
        List<WebElement> menuList = getDriver().findElements(dashboardDropdownMenu);
        List<String> expectedList = Arrays.asList("New Item", "People", "Build History", "Manage Jenkins", "My Views");
        for (WebElement el : menuList) {
            Assert.assertTrue(expectedList.contains(el.getAttribute("innerText")));
        }
    }
}
