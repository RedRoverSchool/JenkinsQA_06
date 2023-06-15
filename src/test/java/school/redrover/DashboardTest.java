package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.FreestyleProjectConfigPage;
import school.redrover.model.FreestyleProjectPage;
import school.redrover.model.MainPage;
import school.redrover.model.PluginsPage;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class DashboardTest extends BaseTest {

    private MainPage createFreestyleProjectWithDefaultConfigurations(String name) {

        return new MainPage(getDriver())
                .clickCreateAJobArrow()
                .enterItemName(name)
                .selectJobType(TestUtils.JobType.FreestyleProject)
                .clickOkButton(new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())))
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
    public void testReturnToDashboardPageFromProjectPage() {
        final String nameProject = "One";

        String nameProjectOnMainPage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(nameProject)
                .selectJobType(TestUtils.JobType.FreestyleProject)
                .clickOkButton(new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver())))
                .clickSaveButton()
                .clickDashboard()
                .getProjectNameMainPage(nameProject);

        Assert.assertEquals(nameProjectOnMainPage, nameProject);
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
        final List<String> expectedMenuList = Arrays.asList("New Item", "People", "Build History", "Manage Jenkins", "My Views");

        List<String> actualMenuList = new MainPage(getDriver())
                .getHeader()
                .clickDashboardDropdownMenu()
                .getMenuList();

        Assert.assertEquals(actualMenuList, expectedMenuList);
    }

    @Test
    public void testMoveFromBuildHistoryPageToPeoplePageByDropDownMenu() {
        String actualTitle = new MainPage(getDriver())
                .clickBuildsHistoryButton()
                .getHeader()
                .openPeoplePageFromDashboardDropdownMenu()
                .getPageTitle();

        Assert.assertEquals(actualTitle, "People");
    }

    @Test
    public void testMoveToPluginsPageThroughDashboardDropDownMenu() {

        String actualResult =
                new MainPage(getDriver())
                        .getBreadcrumb()
                        .openDashboardDropdownMenu()
                        .selectAnOptionFromDashboardManageJenkinsSubmenuList(
                                "Manage Plugins", new PluginsPage(getDriver()))
                        .getPageTitle();

        Assert.assertEquals(actualResult, "Plugins");
    }
}

