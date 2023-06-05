package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.MainPage;
import school.redrover.runner.BaseTest;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class DashboardTest extends BaseTest {

    private MainPage createFreestyleProjectWithDefaultConfigurations(String name) {

        return new MainPage(getDriver())
                .clickCreateAJobArrow()
                .enterItemName(name)
                .selectFreestyleProjectAndOk()
                .getHeader()
                .clickLogo();
    }

    @Test()
    public void testMenuIsShownWhenClickingButtonNearProjectName() {
        final String projectName = UUID.randomUUID().toString();
        final List<String> expectedMenus = List.of(
                "Changes",
                "Workspace",
                "Build Now",
                "Configure",
                "Delete Project",
                "Rename"
        );

        List<String> listOfMenus = createFreestyleProjectWithDefaultConfigurations(projectName)
               .getListOfProjectMenuItems(projectName);

        Assert.assertEquals(listOfMenus, expectedMenus);
    }

    @Test
    public void testReturnToDashboardPage() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys("One");
        getDriver().findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']")).click();
        getDriver().findElement(By.id("ok-button")).click();

        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='jenkins-button jenkins-button--primary ']"))).click();
        getDriver().findElement(By.xpath("//a[@class='model-link'][contains(text(),'Dashboard')]")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//a[@href='job/One/']")).getText(), "One");
    }

    @Test
    public void testMoveFromPeoplePageToPluginsPageByDropDownMenu() {
        String actualTitle = new MainPage(getDriver())
                .clickPeopleOnLeftSideMenu()
                .getHeader()
                .openPluginsPageFromDashboardDropdownMenu()
                .getPageTitle();

        Assert.assertEquals(actualTitle, "Plugins");
    }

    @Test
    public void testDashboardDropdownMenu() {
        new Actions(getDriver())
                .moveToElement(getDriver()
                        .findElement(By.xpath("//a[@href='/']/button")))
                .click()
                .build()
                .perform();
        List<WebElement> menuList = getDriver().findElements(By.cssSelector("#breadcrumb-menu>div:first-child>ul>li"));
        List<String> expectedList = Arrays.asList("New Item", "People", "Build History", "Manage Jenkins", "My Views");
        for (WebElement el : menuList) {
            Assert.assertTrue(expectedList.contains(el.getAttribute("innerText")));
        }
    }

    @Test
    public void testVerifyDashboardDropdownMenuOptionsName() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        WebElement dashboardLink = getDriver()
                .findElement(By.xpath("//li[@class='jenkins-breadcrumbs__list-item']"));

        new Actions(getDriver())
                .moveToElement(dashboardLink)
                .perform();

        WebElement dashboardDropdownMenuButton = getDriver()
                .findElement(By.xpath("//div[@id='breadcrumbBar']//button[@class='jenkins-menu-dropdown-chevron']"));
        js.executeScript("arguments[0].click();", dashboardDropdownMenuButton);

        String[] expectedText = {"New Item", "People", "Build History", "Manage Jenkins", "My Views"};

        List<WebElement> menuOptions = getDriver()
                .findElements(By.xpath("//div[@id='breadcrumb-menu-target']//li[@class='yuimenuitem first-of-type']/parent::ul/li"));

        for (int i = 0;  i < menuOptions.size(); i++) {

            Assert.assertEquals(menuOptions.get(i).getText(), expectedText[i]);
        }
    }
}
