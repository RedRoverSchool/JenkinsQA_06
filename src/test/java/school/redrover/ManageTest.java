package school.redrover;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.model.ManagePage;
import school.redrover.runner.BaseTest;

import java.util.List;

public class ManageTest extends BaseTest {

    @Test
    public void testSearchWithNumericSymbol() {
       ManagePage managePage = new ManagePage(getDriver())
               .navigateToManagePage()
               .enterSearchQuery("1")
               .clickSearchButton();

       managePage.assertNoResultsDisplayed();
    }

    @Test
    public void testSearchWithLetterConfigureSystem() {
        ManagePage managePage = new ManagePage(getDriver())
                .navigateToManagePage()
                .enterSearchQuery("m")
                .clickSearchButton()
                .selectOnTheFirstLineInDropdown();

        managePage.assertConfigureSystemPage();
    }

    @Test
    public void testSearchWithLetterManageCredentials() {
        ManagePage managePage = new ManagePage(getDriver())
                .navigateToManagePage()
                .enterSearchQuery("man")
                .selectManageCredentialsOption();

        managePage.assertCredentialsPage();
    }

    @Test
    public void testCreateNewUser() {
        ManagePage managePage = new ManagePage(getDriver())
                .navigateToManagePage()
                .navigateToManageUsersPage()
                .clickCreateUser()
                .fillUserDetails()
                .submit();

        managePage.assertUserCreated();
    }

    @Test
    public void testCreateNewUserWithInvalidEmail() {
        ManagePage managePage = new ManagePage(getDriver())
                .navigateToManagePage()
                .navigateToManageUsersPage()
                .clickCreateUser()
                .fillUserDetailsWithInvalidEmail()
                .submit();

        managePage.assertInvalidEmailError();
    }

    @Test(dependsOnMethods = "testCreateNewUser")
    public void testDeleteUser() {
        ManagePage managePage = new ManagePage(getDriver())
                .navigateToManagePage()
                .navigateToManageUsersPage()
                .clickDeleteUser()
                .submit();

        managePage.assertUserDeleted();
    }

    @Test
    public void testAddDescriptionToUserOnTheUserProfilePage() {
        ManagePage managePage = new ManagePage(getDriver())
                .navigateToManagePage()
                .navigateToManageUsersPage()
                .clickUserEditButton()
                .enterDescriptionText()
                .submit();

        managePage.assertDescriptionText();
    }

    @Test
    public void testManageConfigureNumberOfExecutorsInMasterNode() {
        ManagePage managePage = new ManagePage(getDriver())
                .navigateToManagePage()
                .navigateManageNodesAndClouds()
                .clickConfigureMasterNode()
                .changeNumberOfExecutorsAndSave("3")
                .navigateToMasterNodeConfiguration();

        managePage.assertNumberOfExecutors("3");
    }

    @DataProvider(name = "KeyWordsToSearch")
    public Object[][] searchWordsKey() {
        return new Object[][]{{"manage"}, {"tool"}, {"system"}, {"sec"}, {"cre"}, {"do"}, {"scr"}, {"jen"}, {"stat"}};
    }

    @Test(dataProvider = "KeyWordsToSearch")
    public void test1SearchSettingsByWord(String keyword) {

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

    @Test
    public void testBreadcrumbNavigateManageJenkins() {
        new Actions(getDriver()).moveToElement(getDriver().findElement(
                By.xpath("//div[@id='breadcrumbBar']//li[1]"))).perform();

        WebElement arrow = getDriver().findElement(By.xpath("//*[@id='breadcrumbs']/li/a/button[@class='jenkins-menu-dropdown-chevron']"));
        new Actions(getDriver()).moveToElement(arrow).perform();
        arrow.sendKeys(Keys.RETURN);

        getDriver().findElement(By.xpath("//*[@id='yui-gen4']/a/span")).click();

        Assert.assertEquals(getDriver().findElement(By.tagName("h1")).getText(), "Manage Jenkins");
    }
}
