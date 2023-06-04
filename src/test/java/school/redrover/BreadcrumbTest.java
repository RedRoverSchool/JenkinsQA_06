package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.MainPage;
import school.redrover.model.ManageJenkinsPage;
import school.redrover.runner.BaseTest;


public class BreadcrumbTest extends BaseTest {
    @Test
    public void testNavigateToManageJenkinsSection() {
        ManageJenkinsPage manageJenkinsPage = new MainPage(getDriver())
                .clickManageJenkinsOnDropDown();

        Assert.assertEquals(manageJenkinsPage.getActualHeader(), "Manage Jenkins");
    }
    @Test
    public void testNavigateToConfigureSystemSubsection() {
        String subsectionHeader = new MainPage(getDriver())
                .hoverToManageJenkinsItemInDashboardBreadcrumbs()
                .clickOnConfigureSystemSubsectionInBreadcrumbs()
                .getTitle();
        Assert.assertEquals(subsectionHeader, "Configure System");
    }
    @Test
    public void testSelectReloadConfigurationFromDisk(){
        String alertText = new MainPage(getDriver())
                .hoverToManageJenkinsItemInDashboardBreadcrumbs()
                .getBreadcrumbsSubsectionAlertMessage();

        Assert.assertEquals(alertText, "Reload Configuration from Disk: are you sure?");
    }
    @Test
    public void testNavigateToGlobalToolConfigurationSubsection(){
        String subsectionHeader = new MainPage(getDriver())
                .hoverToManageJenkinsItemInDashboardBreadcrumbs()
                .clickOnGlobalToolConfigurationSubsectionInBreadcrumbs()
                .getTitle();

        Assert.assertEquals(subsectionHeader, "Global Tool Configuration");
    }
    @Test
    public void testNavigateToManagePluginsSubsection(){
        String subsectionHeader = new MainPage(getDriver())
                .hoverToManageJenkinsItemInDashboardBreadcrumbs()
                .clickOnManagePluginsSubsectionInBreadcrumbs()
                .getTitle();

        Assert.assertEquals(subsectionHeader, "Plugins");
    }
    @Test
    public void testNavigateToManageNodesSubsection(){
        String subsectionHeader = new MainPage(getDriver())
                .hoverToManageJenkinsItemInDashboardBreadcrumbs()
                .clickOnManageNodesSubsectionInBreadcrumbs()
                .getTitle();

        Assert.assertEquals(subsectionHeader, "Manage nodes and clouds");
    }
    @Test
    public void testNavigateToConfigureGlobalSecuritySubsection(){
        String subsectionHeader = new MainPage(getDriver())
                .hoverToManageJenkinsItemInDashboardBreadcrumbs()
                .clickOnConfigureGlobalSecuritySubsectionInBreadcrumbs()
                .getTitle();

        Assert.assertEquals(subsectionHeader, "Configure Global Security");
    }
    @Test
    public void testNavigateToCredentialsSubsection(){
        String subsectionHeader = new MainPage(getDriver())
                .hoverToManageJenkinsItemInDashboardBreadcrumbs()
                .clickOnCredentialsSubsectionInBreadcrumbs()
                .getTitle();

        Assert.assertEquals(subsectionHeader, "Credentials");
    }
    @Test
    public void testNavigateToCredentialProvidersSubsection(){
        String subsectionHeader = new MainPage(getDriver())
                .hoverToManageJenkinsItemInDashboardBreadcrumbs()
                .clickOnCredentialProvidersSubsectionInBreadcrumbs()
                .getTitle();

        Assert.assertEquals(subsectionHeader, "Configure Credential Providers");
    }
    @Test
    public void testNavigateToManageUsersSubsection(){
        String subsectionHeader = new MainPage(getDriver())
                .hoverToManageJenkinsItemInDashboardBreadcrumbs()
                .clickOnManageUsersSubsectionInBreadcrumbs()
                .getTitle();
        Assert.assertEquals(subsectionHeader, "Users");
    }
    @Test
    public void testNavigateToSystemInformationSubsection(){
        String subsectionHeader = new MainPage(getDriver())
                .hoverToManageJenkinsItemInDashboardBreadcrumbs()
                .clickOnSystemInformationSubsectionInBreadcrumbs()
                .getTitle();

        Assert.assertEquals(subsectionHeader, "System Information");
    }
    @Test
    public void testNavigateToSystemLogSubsection(){
        String subsectionHeader = new MainPage(getDriver())
                .hoverToManageJenkinsItemInDashboardBreadcrumbs()
                .clickOnSystemLogSubsectionInBreadcrumbs()
                .getTitle();

        Assert.assertEquals(subsectionHeader, "Log Recorders");
    }
    @Test
    public void testNavigateToLoadStatisticsSubsection(){
        String subsectionHeader = new MainPage(getDriver())
                .hoverToManageJenkinsItemInDashboardBreadcrumbs()
                .clickOnLoadStatisticsSubsectionInBreadcrumbs()
                .getTitle();

        Assert.assertEquals(subsectionHeader, "Load statistics: Jenkins");
    }
    @Test
    public void testNavigateToAboutJenkinsSubsection(){
        String subsectionHeader = new MainPage(getDriver())
                .hoverToManageJenkinsItemInDashboardBreadcrumbs()
                .clickOnAboutJenkinsSubsectionInBreadcrumbs()
                .getTitle();

        Assert.assertEquals(subsectionHeader, "Jenkins\n" +
                "Version\n" +
                "2.387.2");
    }
    @Test
    public void testNavigateToManageOldDataSubsection(){
        String subsectionHeader = new MainPage(getDriver())
                .hoverToManageJenkinsItemInDashboardBreadcrumbs()
                .clickOnManageOldDataSubsectionInBreadcrumbs()
                .getTitle();

        Assert.assertEquals(subsectionHeader, "Manage Old Data");
    }
    @Test
    public void testNavigateToJenkinsCLISubsection(){
        String subsectionHeader = new MainPage(getDriver())
                .hoverToManageJenkinsItemInDashboardBreadcrumbs()
                .clickOnJenkinsCLISubsectionInBreadcrumbs()
                .getTitle();

        Assert.assertEquals(subsectionHeader, "Jenkins CLI");
    }
    @Test
    public void testNavigateToScriptConsoleSubsection(){
        String subsectionHeader = new MainPage(getDriver())
                .hoverToManageJenkinsItemInDashboardBreadcrumbs()
                .clickOnScriptConsoleSubsectionInBreadcrumbs()
                .getTitle();

        Assert.assertEquals(subsectionHeader, "Script Console");
    }
    @Test
    public void testNavigateToPrepareForShutdownSubsection(){
        String subsectionHeader = new MainPage(getDriver())
                .hoverToManageJenkinsItemInDashboardBreadcrumbs()
                .clickOnPrepareForShutdownSubsectionInBreadcrumbs()
                .getTitle();

        Assert.assertEquals(subsectionHeader, "Prepare for Shutdown");
    }
}
