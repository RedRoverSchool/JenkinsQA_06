package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.model.*;
import school.redrover.model.base.BaseMainHeaderPage;
import school.redrover.model.jobs.FreestyleProjectPage;
import school.redrover.model.jobsconfig.FreestyleProjectConfigPage;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

import java.util.Arrays;
import java.util.List;

public class BreadcrumbTest extends BaseTest {
    @Test
    public void testNavigateToManageJenkinsSection() {
        String actualResult = new MainPage(getDriver())
                .getBreadcrumb()
                .clickManageJenkinsOnDropDownMenu()
                .getActualHeader();

        Assert.assertEquals(actualResult, "Manage Jenkins");
    }

    @DataProvider (name = "subsections")
    public Object[][] provideSubsection() {
        return new Object[][]{
                {"Configure System", new ConfigureSystemPage(getDriver())},
                {"Global Tool Configuration", new GlobalToolConfigurationPage(getDriver())},
                {"Manage Plugins", new PluginsPage(getDriver())},
                {"Manage Nodes and Clouds", new ManageNodesPage(getDriver())},
                {"Configure Global Security", new ConfigureGlobalSecurityPage(getDriver())},
                {"Manage Credentials", new CredentialsPage(getDriver())},
                {"Configure Credential Providers", new ConfigureCredentialProvidersPage(getDriver())},
                {"Manage Users", new UserPage(getDriver())},
                {"System Information", new SystemInformationPage(getDriver())},
                {"System Log", new LogRecordersPage(getDriver())},
                {"Load Statistics", new LoadStatisticsPage(getDriver())},
                {"About Jenkins", new AboutJenkinsPage(getDriver())},
                {"Manage Old Data", new ManageOldDataPage(getDriver())},
                {"Jenkins CLI", new JenkinsCLIPage(getDriver())},
                {"Script Console", new ScriptConsolePage(getDriver())},
                {"Prepare for Shutdown", new PrepareForShutdownPage(getDriver())}
        };
    }

    @Test(dataProvider = "subsections")
    public <PageFromSubMenu extends BaseMainHeaderPage<PageFromSubMenu>> void testNavigateToManageJenkinsSubsection(String subsectionName, PageFromSubMenu pageFromSubMenu) {
        new MainPage(getDriver())
                .getBreadcrumb()
                .getDashboardDropdownMenu()
                .selectAnOptionFromDashboardManageJenkinsSubmenuList(subsectionName, pageFromSubMenu);

        String pageName = getDriver().findElement(By.xpath("//h1")).getText();

        switch (subsectionName) {
            case "System Log" -> Assert.assertEquals(pageName, "Log Recorders");
            case "Load Statistics" -> Assert.assertTrue(pageName.toLowerCase().contains(subsectionName.toLowerCase()));
            case "About Jenkins" -> Assert.assertTrue(pageName.contains("Jenkins\n" + "Version"));
            default -> Assert.assertTrue(subsectionName.toLowerCase().contains(pageName.toLowerCase()));
        }
    }

    @Test
    public void testReloadConfigurationFromDiskOfManageJenkinsSubmenu() {

        String expectedLoadingText = "Please wait while Jenkins is getting ready to work ...";

        new MainPage(getDriver())
                .getBreadcrumb()
                .getDashboardDropdownMenu()
                .selectAnOptionFromDashboardManageJenkinsSubmenuList("Reload Configuration from Disk", new MainPage(getDriver()))
                .getBreadcrumb()
                .clickOkOnPopUp();

        String loadingText = getDriver().findElement(By.xpath("//h1")).getText();

        Assert.assertEquals(loadingText, expectedLoadingText);
    }

    @Test
    public void testDashboardDropdownMenu() {
        final List<String> expectedMenuList = Arrays.asList("New Item", "People", "Build History", "Manage Jenkins", "My Views");

        List<String> actualMenuList = new MainPage(getDriver())
                .getBreadcrumb()
                .getDashboardDropdownMenu()
                .getMenuList();

        Assert.assertEquals(actualMenuList, expectedMenuList);
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
                .getBreadcrumb()
                .clickDashboardButton()
                .getJobName(nameProject);

        Assert.assertEquals(nameProjectOnMainPage, nameProject);
    }


    @Test
    public void testMoveFromPeoplePageToPluginsPageByDropDownMenu() {
        String actualTitle = new MainPage(getDriver())
                .clickPeopleOnLeftSideMenu()
                .getBreadcrumb()
                .selectAnOptionFromDashboardManageJenkinsSubmenuList("Manage Plugins", new PluginsPage(getDriver()))
                .getPageTitle();

        Assert.assertEquals(actualTitle, "Plugins");
    }

    @Test
    public void testMoveToPluginsPageThroughDashboardDropDownMenu() {

        String actualResult = new MainPage(getDriver())
                        .getBreadcrumb()
                        .selectAnOptionFromDashboardManageJenkinsSubmenuList("Manage Plugins", new PluginsPage(getDriver()))
                        .getPageTitle();

        Assert.assertEquals(actualResult, "Plugins");
    }

    @Test
    public void testMoveFromBuildHistoryPageToPeoplePageByDropDownMenu() {
        String actualTitle = new MainPage(getDriver())
                .clickBuildsHistoryButton()
                .getBreadcrumb()
                .openPeoplePageFromDashboardDropdownMenu()
                .getPageTitle();

        Assert.assertEquals(actualTitle, "People");
    }
}
