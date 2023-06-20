package school.redrover;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.model.*;
import school.redrover.model.jobs.FreestyleProjectPage;
import school.redrover.model.jobsconfig.FreestyleProjectConfigPage;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

import java.util.Arrays;
import java.util.List;

public class BreadcrumbTest extends BaseTest {
    @Test
    public void testNavigateToManageJenkinsSection() {
        ManageJenkinsPage manageJenkinsPage = new MainPage(getDriver())
                .clickManageJenkinsOnDropDown();

        Assert.assertEquals(manageJenkinsPage.getActualHeader(), "Manage Jenkins");
    }

    @DataProvider(name = "subsections")
    public Object[][] provideSubsections() {
        return new Object[][] {
                {"//a[@href='/manage/configure']/span", "Configure System [Jenkins]", "/manage/configure"},
                {"//a[@href='/manage/configureTools']/span", "Global Tool Configuration [Jenkins]", "/manage/configureTools/"},
                {"//a[@href='/manage/pluginManager']/span", "Updates - Plugin Manager [Jenkins]", "/manage/pluginManager/"},
                {"//a[@href='/manage/computer']/span", "Nodes [Jenkins]", "/manage/computer/"},
                {"//a[@href='/manage/configureSecurity']/span", "Configure Global Security [Jenkins]", "/manage/configureSecurity/"},
                {"//a[@href='/manage/credentials']/span", "Jenkins » Credentials [Jenkins]", "/manage/credentials/"},
                {"//a[@href='/manage/configureCredentials']/span", "Configure Credential Providers [Jenkins]", "/manage/configureCredentials/"},
                {"//a[@href='/manage/securityRealm/']/span", "Users [Jenkins]", "/manage/securityRealm/"},
                {"//a[@href='/manage/systemInfo']/span", "System Information [Jenkins]", "/manage/systemInfo"},
                {"//a[@href='/manage/log']/span", "Log [Jenkins]", "/manage/log/"},
                {"//a[@href='/manage/load-statistics']/span", "Jenkins Load Statistics [Jenkins]", "/manage/load-statistics"},
                {"//a[@href='/manage/about']/span", "About Jenkins 2.387.2 [Jenkins]", "/manage/about/"},
                {"//a[@href='/manage/administrativeMonitor/OldData/']/span", "Manage Old Data [Jenkins]", "/manage/administrativeMonitor/OldData/manage"},
                {"//a[@href='/manage/cli']/span", "Jenkins", "/manage/cli/"},
                {"//a[@href='/manage/script']/span", "Script Console [Jenkins]", "/manage/script"},
                {"//a[@href='/manage/prepareShutdown']/span", "Prepare for Shutdown [Jenkins]", "/manage/prepareShutdown/"}
        };
    }

    @Test(dataProvider = "subsections")
    public void testNavigateToManageJenkinsSubsection(String locator, String subsectionName, String url) {

        new MainPage(getDriver())
                .getBreadcrumb()
                .getDashboardDropdownMenu()
                .moveToManageJenkinsLink()
                .clickManageJenkinsSubmenu(locator);

        Assert.assertEquals(getDriver().getTitle(), subsectionName);
        Assert.assertEquals(getDriver().getCurrentUrl().substring(21), url);
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
                .getProjectNameMainPage(nameProject);

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
