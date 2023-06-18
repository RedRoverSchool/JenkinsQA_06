package school.redrover;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
        return new Object[][]{{"//div[@id='submenu0']/div/ul/li/a[@href='/manage/configure']/span", "Configure System"},
                {"//div[@id='submenu0']/div/ul/li/a[@href='/manage/configureTools']/span", "Global Tool Configuration"},
                {"//div[@id='submenu0']/div/ul/li/a[@href='/manage/pluginManager']/span", "Plugins"},
                {"//div[@id='submenu0']/div/ul/li/a[@href='/manage/computer']/span", "Manage nodes and clouds"},
                {"//div[@id='submenu0']/div/ul/li/a[@href='/manage/configureSecurity']/span", "Configure Global Security"},
                {"//div[@id='submenu0']/div/ul/li/a[@href='/manage/credentials']/span", "Credentials"},
                {"//div[@id='submenu0']/div/ul/li/a[@href='/manage/configureCredentials']/span", "Configure Credential Providers"},
                {"//div[@id='submenu0']/div/ul/li/a[@href='/manage/securityRealm/']/span", "Users"},
                {"//div[@id='submenu0']/div/ul/li/a[@href='/manage/systemInfo']/span", "System Information"},
                {"//div[@id='submenu0']/div/ul/li/a[@href='/manage/log']/span", "Log Recorders"},
                {"//div[@id='submenu0']/div/ul/li/a[@href='/manage/load-statistics']/span", "Load statistics: Jenkins"},
                {"//div[@id='submenu0']/div/ul/li/a[@href='/manage/about']/span", "Jenkins\n" +
                        "Version\n" +
                        "2.387.2"},
                {"//div[@id='submenu0']/div/ul/li/a[@href='/manage/administrativeMonitor/OldData/']/span", "Manage Old Data"},
                {"//div[@id='submenu0']/div/ul/li/a[@href='#' and @class='yuimenuitemlabel']/span",
                        "Reload Configuration from Disk: are you sure?"},
                {"//div[@id='submenu0']/div/ul/li/a[@href='/manage/cli']/span", "Jenkins CLI"},
                {"//div[@id='submenu0']/div/ul/li/a[@href='/manage/script']/span", "Script Console"},
                {"//div[@id='submenu0']/div/ul/li/a[@href='/manage/prepareShutdown']/span", "Prepare for Shutdown"}};
    }

    @Test(dataProvider = "subsections")
    public void testNavigateToManageJenkinsSubsection(String locator, String subsectionName) {
        new Actions(getDriver()).moveToElement(getDriver().findElement(
                By.xpath("//*[@id='breadcrumbs']/li/a[@href='/']"))).perform();

        By pointerLocator =
                By.xpath("//*[@id='breadcrumbs']/li/a/button[@class='jenkins-menu-dropdown-chevron']");
        getWait5().until(ExpectedConditions.elementToBeClickable(pointerLocator));
        WebElement pointer = getDriver().findElement(pointerLocator);
        new Actions(getDriver()).moveToElement(pointer).perform();
        pointer.sendKeys(Keys.RETURN);

        By sectionNameLocator = By.xpath("//*[@id='yui-gen4']/a/span");
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(sectionNameLocator));
        new Actions(getDriver()).moveToElement(getDriver().findElement(sectionNameLocator)).perform();

        if (locator.contains("@class='yuimenuitemlabel'") ||
                locator.contains("/cli") || locator.contains("/script") || locator.contains("/prepareShutdown")) {
            new Actions(getDriver()).sendKeys(Keys.ARROW_RIGHT).perform();
            for (int i = 0; i < 16; i++) {
                new Actions(getDriver()).sendKeys(Keys.ARROW_DOWN).perform();
            }
        }
        By subsectionNameLocator = By.xpath(locator);
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(subsectionNameLocator));
        WebElement subSection = getDriver().findElement(subsectionNameLocator);
        subSection.click();

        if (locator.contains("@class='yuimenuitemlabel'")) {
            Alert alert = getWait5().until(ExpectedConditions.alertIsPresent());
            String text = alert.getText();
            alert.dismiss();
            Assert.assertEquals(text, "Reload Configuration from Disk: are you sure?");
        } else {
            getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
            Assert.assertEquals(getDriver().findElement(By.tagName("h1")).getText(), subsectionName);
        }
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
                .clickDashboard()
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
