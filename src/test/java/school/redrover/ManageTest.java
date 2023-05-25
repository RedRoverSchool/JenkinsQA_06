package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.model.ManagePage;
import school.redrover.runner.BaseTest;

import java.util.List;

public class ManageTest extends BaseTest {

    @Ignore
    @Test
    public void testSearchWithNumericSymbol() {
       ManagePage noResult = new ManagePage(getDriver())
               .navigateToManagePage()
               .enterSearchQuery("1")
               .clickSearchButton();

        Assert.assertEquals(noResult.getNoResultsDisplayed(),"No results");
    }

    @Test
    public void testSearchWithLetterConfigureSystem() {
        ManagePage configurePage = new ManagePage(getDriver())
                .navigateToManagePage()
                .enterSearchQuery("m")
                .clickSearchButton()
                .selectOnTheFirstLineInDropdown();

        Assert.assertEquals(configurePage.getConfigureSystemPage(),"Configure System");
    }

    @Test
    public void testCreateNewUser() {
        ManagePage newUser = new ManagePage(getDriver())
                .navigateToManagePage()
                .navigateToManageUsersPage()
                .clickCreateUser()
                .fillUserDetails()
                .submit();

        Assert.assertTrue(newUser.assertUserCreated());
    }

    @Test
    public void testCreateNewUserWithInvalidEmail() {
        ManagePage errorEmail = new ManagePage(getDriver())
                .navigateToManagePage()
                .navigateToManageUsersPage()
                .clickCreateUser()
                .fillUserDetailsWithInvalidEmail()
                .submit();

        Assert.assertEquals(errorEmail.assertInvalidEmailError(), "Invalid e-mail address");
    }

    @Test(dependsOnMethods = "testCreateNewUser")
    public void testDeleteUser() {
        ManagePage managePage = new ManagePage(getDriver())
                .navigateToManagePage()
                .navigateToManageUsersPage()
                .clickDeleteUser()
                .submit();

        Assert.assertFalse(managePage.assertUserDeleted());
    }

    @Test
    public void testAddDescriptionToUserOnTheUserProfilePage() {
        ManagePage managePage = new ManagePage(getDriver())
                .navigateToManagePage()
                .navigateToManageUsersPage()
                .clickUserEditButton()
                .enterDescriptionText()
                .submit();

        Assert.assertEquals("Description text",managePage.getDescriptionText());
    }

    @Test
    public void testManageConfigureNumberOfExecutorsInMasterNode() {
        String number = "3";

        ManagePage managePage = new ManagePage(getDriver())

                .navigateToManagePage()
                .navigateManageNodesAndClouds()
                .clickConfigureMasterNode()
                .changeNumberOfExecutorsAndSave(number)
                .navigateToMasterNodeConfiguration();

        Assert.assertEquals(number, managePage.numberOfExecutors());
    }

    @Test
    public void testBreadcrumbNavigateManageJenkins() {

        ManagePage page = new ManagePage(getDriver())
                .navigateToDashboardIcon()
                .dropdownBreadcrumps()
                .navigateToManageJenkinsAndClick();

        Assert.assertEquals(page.verifyManageJenkinsPage(),"Manage Jenkins" );
    }

    @DataProvider(name = "KeyWordsToSearch")
    public Object[][] searchWordsKey() {
        return new Object[][]{{"manage"}, {"tool"}, {"system"}, {"sec"}, {"cre"}, {"do"}, {"scr"}, {"jen"}, {"stat"}};
    }

    @Test(dataProvider = "KeyWordsToSearch")
    public void testSearchSettingsByWord(String keyword) {

        getDriver().findElement(By.xpath("//a[@href='/manage']")).click();

        List<WebElement> listElements = getDriver().findElements(By.xpath("//div[@class='jenkins-section__item']//dt"));

        WebElement searchLink = getDriver().findElement(By.xpath("//input[@id='settings-search-bar']"));
        searchLink.click();
        searchLink.sendKeys(keyword);

        getWait5().until(ExpectedConditions.textToBePresentInElementValue(searchLink, keyword));

        List<WebElement> actualResults = getWait5().until(ExpectedConditions.presenceOfAllElementsLocatedBy
                (By.xpath("//div[@class='jenkins-search__results']//a")));

        List<WebElement> expectedSearchResults = listElements.stream()
                .filter(item -> item.getText().toLowerCase().contains(keyword))
                .toList();

        for (int i = 0; i < expectedSearchResults.size(); i++) {
            Assert.assertEquals(actualResults.get(i).getText(), expectedSearchResults.get(i).getText());
        }
    }
}
