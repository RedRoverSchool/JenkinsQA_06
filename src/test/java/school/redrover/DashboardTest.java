package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.MainPage;
import school.redrover.runner.BaseTest;

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
}
