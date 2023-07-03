package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import school.redrover.model.*;
import school.redrover.model.jobs.FreestyleProjectPage;
import school.redrover.model.jobsconfig.FreestyleProjectConfigPage;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class HeaderTest extends BaseTest {

    @Test
    public void testHeaderLogoIcon() {
        boolean logoIcon = new MainPage(getDriver())
                .getHeader()
                .isDisplayedLogoIcon();

        boolean logoText = new MainPage(getDriver())
                .getHeader()
                .isDisplayedLogoText();

        Assert.assertTrue(logoIcon);
        Assert.assertTrue(logoText);
    }

    @Test
    public void testSearchTextField() {
        String placeholder = new MainPage(getDriver())
                .getHeader()
                .getAttributeFromSearchBox();

        boolean helpIcon = new MainPage(getDriver())
                .getHeader()
                .isDisplayedHelpIcon();

        boolean searchIcon = new MainPage(getDriver())
                .getHeader()
                .isDisplayedSearchBoxIcon();

        Assert.assertEquals(placeholder, "Search (CTRL+K)");
        Assert.assertTrue(helpIcon);
        Assert.assertTrue(searchIcon);
    }

    @Test
    public void testSecurityButton() {
        boolean securityButton = new MainPage(getDriver())
                .getHeader()
                .getSecurityButtonOnHeader();

        Assert.assertTrue(securityButton);

        String background = new MainPage(getDriver())
                .getHeader()
                .getBackgroundSecurityButton();

        Assert.assertEquals(background, "rgba(64, 64, 64, 1)");
    }

    @Test
    public void testAdminButtonBackgroundColorIsPresentWhenMouseOver() {
        String actualAdminButtonBackgroundColor = new MainPage(getDriver())
                .getHeader()
                .hoverOverAdminButton()
                .getAdminButtonBackgroundColor();

        Assert.assertEquals(actualAdminButtonBackgroundColor, "rgba(64, 64, 64, 1)");
    }

    @Test
    public void testExitButton() {
        boolean exitButtonIcon = new MainPage(getDriver())
                .getHeader()
                .iconExitButton();

        Assert.assertTrue(exitButtonIcon);

        String getUnderLineExitIcon = new MainPage(getDriver())
                .getHeader()
                .getUnderLineExitButton();

        String getBackgroundExitIcon = new MainPage(getDriver())
                .getHeader()
                .getBackgroundExitButton();

        Assert.assertEquals(getBackgroundExitIcon, "rgba(64, 64, 64, 1)");
        Assert.assertEquals(getUnderLineExitIcon, "underline");
    }

    @Test
    public void testReturnToDashboardFromPeoplePage() {
        String textTitle = new MainPage(getDriver())
                .clickPeopleOnLeftSideMenu()
                .getHeader()
                .clickLogo()
                .getTitle();

        String textFromMainPage = new MainPage(getDriver())
                .getWelcomeText();

        Assert.assertEquals(textTitle, "Dashboard [Jenkins]");
        Assert.assertEquals(textFromMainPage, "Welcome to Jenkins!");
    }

    @Test
    public void testSearchField() {
        String textPageFromSearchBox = new MainPage(getDriver())
                .getHeader()
                .sendSearchBox()
                .getTitle();

        Assert.assertEquals(textPageFromSearchBox,"Built-In Node");
    }

    @Test
    public void testLogOutButtonTransfersBackToLoginPaged() {
        boolean signInButtonPresence = new MainPage(getDriver())
                .getHeader()
                .clickLogOUTButton()
                .isSignInButtonPresent();

        Assert.assertTrue(signInButtonPresence, "Sign In button is not displayed after logout");
    }

    @Test
    public void testNotificationAndSecurityIcon() {
        String expectedManageJenkinsPageHeader = "Manage Jenkins";

        String backgroundColorBefore = new MainPage(getDriver())
                .getHeader()
                .getBackgroundColorNotificationIcon();

        String backgroundColorAfter = new MainPage(getDriver())
                .getHeader()
                .clickNotificationIcon()
                .getNotificationIconBackgroundColor();

        String actualManageJenkinsPageHeader = new ManageJenkinsPage(getDriver())
                .clickManageJenkinsLink()
                .getActualHeader();

        Assert.assertNotEquals(backgroundColorBefore, backgroundColorAfter, " The color of icon is not changed");
        Assert.assertEquals(actualManageJenkinsPageHeader, expectedManageJenkinsPageHeader, " The page is not correct");
    }

    @Test
    public void testReturnToTheDashboardPageAfterCreatingTheItem() {
        final List<String> listItemName = new ArrayList<>(List.of("Test Item", "Second"));

        TestUtils.createJob(this, listItemName.get(0), TestUtils.JobType.FreestyleProject, true);
        TestUtils.createJob(this, listItemName.get(1), TestUtils.JobType.FreestyleProject, false);

        boolean isPageOpen = new FreestyleProjectConfigPage(new FreestyleProjectPage(getDriver()))
                .getHeader()
                .clickLogo()
                .isMainPageOpen();

        Assert.assertTrue(isPageOpen, "Wrong title or wrong page");

        List<String> listJob = new MainPage(getDriver())
                .getJobList();

        SoftAssert softAssert = new SoftAssert();
        for (int i = 0; i < listJob.size(); i++) {
            softAssert.assertTrue(listJob.contains(listItemName.get(i)),
                    "The result list doesn't contain the item " + listItemName.get(i));
        }
        softAssert.assertAll();
    }

    @Test
    public void testOpenBuildsTabFromDropdownMenu() {
        boolean page = new MainPage(getDriver())
                .getHeader()
                .clickAdminDropdownMenu()
                .openBuildsTabFromAdminDropdownMenuIsDisplayed();

        Assert.assertTrue(page, "Page should be displayed");
    }

    @Test
    public void testAdminPageIsAvailable() {
        String adminPageSign = new MainPage(getDriver())
                .getHeader()
                .clickOnAdminButton()
                .getTitleText();

        assertEquals(adminPageSign,"Jenkins User ID: admin");
    }

    @Test
    public void testButtonNotificationsWorks() {
        String getTitle = new MainPage(getDriver())
                .getHeader()
                .clickNotificationIcon()
                .getTextFromHeaderManageJenkins();

        Assert.assertEquals(getTitle, "Manage Jenkins");
    }

    @Test
    public void testOfNotificationIconColorChange() {
        String notificationIconColorBefore = new MainPage(getDriver())
                .getHeader()
                .getNotificationIconBackgroundColor();

        String notificationIconColorAfter = new MainPage(getDriver())
                .getHeader()
                .hoverOverNotificationIcon()
                .getNotificationIconBackgroundColor();

        Assert.assertNotEquals(notificationIconColorAfter, notificationIconColorBefore,
                "The Notification icon background has not changed");
    }

    @Test
    public void testOfAdminButtonColorChange() {
        String adminButtonColorBefore = new MainPage(getDriver())
                .getHeader()
                .getAdminButtonBackgroundColor();

        String adminButtonColorAfter = new MainPage(getDriver())
                .getHeader()
                .hoverOverAdminButton()
                .getAdminButtonBackgroundColor();

        Assert.assertNotEquals(adminButtonColorAfter, adminButtonColorBefore,
                "The Admin button background has not changed");
    }

    @Test
    public void testOfLogOutButtonColorChange() {
        String logOutButtonColorBefore = new MainPage(getDriver())
                .getHeader()
                .getLogOutButtonBackgroundColor();

        String logOutButtonColorAfter = new MainPage(getDriver())
                .getHeader()
                .hoverOverLogOutButton()
                .getLogOutButtonBackgroundColor();

        Assert.assertNotEquals(logOutButtonColorAfter, logOutButtonColorBefore,
                "The LogOut button background has not changed");
    }

    @Test
    public void testAppearanceOfPopUpMenuWhenClickingOnNotificationIcon() {
        boolean isPopUpScreenDisplayed = new MainPage(getDriver())
                .getHeader()
                .clickNotificationIcon()
                .isPopUpNotificationScreenDisplayed();

        Assert.assertTrue(isPopUpScreenDisplayed, "The pop-up Notification icon screen is not displayed");
    }

    @Test
    public void testAppearanceOfPopUpMenusWhenClickingOnAdminIcon() {
        boolean isPopUpScreenDisplayed = new MainPage(getDriver())
                .getHeader()
                .clickAdminDropdownMenu()
                .isAdminDropdownScreenDisplayed();

        Assert.assertTrue(isPopUpScreenDisplayed, "The pop-up Admin icon screen is not displayed");
    }

    @Ignore
    @Test
    public void testOpenTheLinkOfManageJenkinsLinkFromThePopUpScreen() {
        String screenManageFromPopUp = new MainPage(getDriver())
                .getHeader()
                .clickNotificationIcon()
                .clickManageLinkFromPopUp()
                .getActualHeader();

        Assert.assertEquals(screenManageFromPopUp,"Manage Jenkins");
    }

    @Test
    public void testAdminButtonIsUnderlinedWhenMouseOver() {
        String textUnderlineAfter = new MainPage(getDriver())
                .getHeader()
                .hoverOverAdminButton()
                .getAdminTextDecorationValue();

        Assert.assertTrue(textUnderlineAfter.contains("underline"));
    }

    @Test
    public void testLogoutButtonColorChange() {
        String expectedColor = "rgba(245, 245, 245, 1)";

        String actualColorLogOutButton = new MainPage(getDriver())
                .getHeader()
                .hoverOverLogOutButton()
                .getLogOutTextDecorationValue();

        assertEquals(actualColorLogOutButton, expectedColor);
    }

    @Test
    public void testConfigureTabFromDropdownMenu() {
        boolean isPageOpened = new MainPage(getDriver())
                .getHeader()
                .clickAdminDropdownMenu()
                .openConfigureTabFromAdminDropdownMenu()
                .isConfigUserPageOpened();

        Assert.assertTrue(isPageOpened, "Page should be displayed");
    }

    @Test
    public void testMyViewsTabFromDropdownMenu() {
        boolean page = new MainPage(getDriver())
                .getHeader()
                .clickAdminDropdownMenu()
                .openMyViewsTabFromAdminDropdownMenuIsDisplayed();

        Assert.assertTrue(page, "Page should be displayed");
    }

    @Test
    public void testCredentialsTabFromDropdownMenu() {
        boolean page = new MainPage(getDriver())
                .getHeader()
                .clickAdminDropdownMenu()
                .openCredentialsTabFromAdminDropdownMenuIsDisplayed();

        Assert.assertTrue(page, "Page should be displayed");
    }

    @Test
    public void testNotificationAndSecurityIconsButtonsChangeColorWhenMouseover() {
        List<WebElement> buttonsChangeColorWhenMouseover = getDriver()
                .findElements(By.xpath("//div[contains(@class,'am-container')]"));
        for (int i = 0; i < buttonsChangeColorWhenMouseover.size(); i++) {

            WebElement iconButtons = buttonsChangeColorWhenMouseover.get(i);

            String backgroundColor = iconButtons.getCssValue("color");
            new Actions(getDriver()).moveToElement(iconButtons).perform();
            String hoverColor = iconButtons.getCssValue("background-color");

            assertNotEquals(backgroundColor, hoverColor);
        }
    }
}
