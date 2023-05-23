package school.redrover;

import org.apache.commons.lang3.RandomStringUtils;
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


import static org.openqa.selenium.devtools.v109.dom.DOM.getSearchResults;

public class ManageTest extends BaseTest {
    private static final String NEW_USER_NAME = RandomStringUtils.randomAlphanumeric(10);
    private static final By SEARCH_SETTINGS = By.id("settings-search-bar");

    @Test
    public void test1SearchWithNumericSymbol() {
        getDriver().findElement(By.xpath("//a[@href='/manage']")).click();

        WebElement numberWrite = getDriver().findElement(SEARCH_SETTINGS);
        numberWrite.sendKeys("1");

        getDriver().findElement(SEARCH_SETTINGS).click();

        getWait2().until(ExpectedConditions.visibilityOfAllElementsLocatedBy
                (By.xpath(" //div[@class='jenkins-search__results']")));

        Assert.assertEquals("No results", getDriver().findElement
                (By.xpath(" //div[@class='jenkins-search__results']")).getText());
    }

    @Test
    public void testSearchWithNumericSymbol() {
       ManagePage managePage = new ManagePage(getDriver());

        managePage.navigateToManagePage();
        managePage.enterSearchQuery("1");
        managePage.clickSearchButton();
        managePage.assertNoResultsDisplayed();
    }

    @Test
    public void test1SearchWithLetterConfigureSystem() {
        getDriver().findElement(By.xpath("//a[@href='/manage']")).click();

        WebElement letterWrite = getDriver().findElement(SEARCH_SETTINGS);
        letterWrite.sendKeys("m");
        getDriver().findElement(SEARCH_SETTINGS).click();

        getWait10().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".jenkins-search__results-item--selected")));

        List<WebElement> options = getDriver().findElements(By.cssSelector(".jenkins-search__results-item--selected"));
        for (WebElement option : options) {
            if (option.getText().equals("Configure System")) {
                option.click();
                break;
            }
        }

        Assert.assertEquals("Configure System",
                getDriver().findElement(By.xpath("//h1[normalize-space()='Configure System']")).getText());
    }

    @Test
    public void testSearchWithLetterConfigureSystem() {
        ManagePage managePage = new ManagePage(getDriver());

        managePage.navigateToManagePage();
        managePage.enterSearchQuery("m");
        managePage.clickSearchButton();
        managePage.selectOnTheFirstLineInDropdown();
        managePage.assertConfigureSystemPage();
    }

    @Test
    public void test1SearchWithLetterManageCredentials() {
        getDriver().findElement(By.xpath("//a[@href='/manage']")).click();

        WebElement partOfaWord = getDriver().findElement(SEARCH_SETTINGS);
        partOfaWord.sendKeys("man");

        getWait10().until(ExpectedConditions.visibilityOfAllElementsLocatedBy
                (By.xpath("//a[normalize-space()='Manage Credentials']")));

        List<WebElement> options = getDriver().findElements(By.xpath("//a[normalize-space()='Manage Credentials']"));
        for (WebElement option : options) {
            if (option.getText().equals("Manage Credentials")) {
                option.click();
                break;
            }
        }
        Assert.assertEquals("Credentials", getDriver().findElement(By.xpath("//h1[normalize-space()='Credentials']")).getText());
    }

    @Test
    public void testSearchWithLetterManageCredentials() {
        ManagePage managePage = new ManagePage(getDriver());

        managePage.navigateToManagePage();
        managePage.enterSearchQuery("man");
        managePage.selectManageCredentialsOption();
        managePage.assertCredentialsPage();
    }

    @Test
    public void test1CreateNewUser() {

        getDriver().findElement(By.xpath("//a[@href='/manage']")).click();
        getDriver().findElement(By.xpath("//a[@href='securityRealm/']")).click();
        getDriver().findElement(By.xpath("//a[normalize-space()='Create User']")).click();

        getDriver().findElement(By.id("username")).sendKeys(NEW_USER_NAME);
        getDriver().findElement(By.name("password1")).sendKeys("1234");
        getDriver().findElement(By.name("password2")).sendKeys("1234");
        getDriver().findElement(By.name("fullname")).sendKeys("Nik Smith");
        getDriver().findElement(By.name("email")).sendKeys("nik@gmail.com");

        getDriver().findElement(By.name("Submit")).click();

        List<WebElement> userList = getDriver().findElements(By.id("people"));

        for (WebElement user : userList) {
            if (user.getText().equals(NEW_USER_NAME)) {
                break;
            }
        }
        Assert.assertTrue(true, NEW_USER_NAME);
    }

    @Test
    public void testCreateNewUser() {
        ManagePage managePage = new ManagePage(getDriver());

        managePage.navigateToManagePage();
        managePage.navigateToManageUsersPage();
        managePage.clickCreateUser();
        managePage.fillUserDetails();
        managePage.submit();
        managePage.assertUserCreated();
    }

    @Test
    public void test1CreateNewUserWithInvalidEmail() {

        getDriver().findElement(By.xpath("//a[@href='/manage']")).click();
        getDriver().findElement(By.xpath("//a[@href='securityRealm/']")).click();
        getDriver().findElement(By.xpath("//a[normalize-space()='Create User']")).click();

        getDriver().findElement(By.id("username")).sendKeys(NEW_USER_NAME);
        getDriver().findElement(By.name("password1")).sendKeys("1234");
        getDriver().findElement(By.name("password2")).sendKeys("1234");
        getDriver().findElement(By.name("fullname")).sendKeys("Nik Smith");
        getDriver().findElement(By.name("email")).sendKeys("nik.com");

        getDriver().findElement(By.name("Submit")).click();

        Assert.assertEquals("Invalid e-mail address", getDriver()
                .findElement(By.xpath("//div[@class='error jenkins-!-margin-bottom-2']")).getText());

    }

    @Test
    public void testCreateNewUserWithInvalidEmail() {
        ManagePage managePage = new ManagePage(getDriver());

        managePage.navigateToManagePage();
        managePage.navigateToManageUsersPage();
        managePage.clickCreateUser();
        managePage.fillUserDetailsWithInvalidEmail();
        managePage.submit();
        managePage.assertInvalidEmailError();
    }

    @Test(dependsOnMethods = "testCreateNewUser")
    public void test1DeleteUser() {
        getDriver().findElement(By.xpath("//a[@href='/manage']")).click();
        getDriver().findElement(By.xpath("//a[@href='securityRealm/']")).click();

        getDriver().findElement(By.xpath("//a[@class='jenkins-table__button jenkins-!-destructive-color']")).click();
        getDriver().findElement(By.name("Submit")).click();

        List<WebElement> userList = getDriver().findElements(By.id("people"));

        for (WebElement user : userList) {
            if (user.getText().equals(NEW_USER_NAME)) {
                break;
            }
        }
        Assert.assertFalse(false, NEW_USER_NAME);
    }

    @Test(dependsOnMethods = "testCreateNewUser")
    public void testDeleteUser() {
        ManagePage managePage = new ManagePage(getDriver());

        managePage.navigateToManagePage();
        managePage.navigateToManageUsersPage();
        managePage.clickDeleteUser();
        managePage.submit();
        managePage.assertUserDeleted();
    }

    @Test
    public void test1AddDescriptionToUserOnTheUserProfilePage() {

        getDriver().findElement(By.xpath("//a[@href='/manage']")).click();
        getDriver().findElement(By.xpath("//a[@href='securityRealm/']")).click();
        getDriver().findElement(By.xpath("//a[@class='jenkins-table__button'][1]")).click();

        getDriver().findElement(By.name("_.description")).clear();
        getDriver().findElement(By.name("_.description")).sendKeys("Description text");
        getDriver().findElement(By.name("Submit")).click();

        Assert.assertEquals("Description text",
                getDriver().findElement(By.xpath("//div[@id='description']/div[1]")).getText());
    }

    @Test
    public void testAddDescriptionToUserOnTheUserProfilePage() {
        ManagePage managePage = new ManagePage(getDriver());

        managePage.navigateToManagePage();
        managePage.clickUserEditButton();
        managePage.enterDescriptionText();
        managePage.submit();
        managePage.assertDescriptionText();
    }


    @Test
    public void test1ManageConfigureNumberOfExecutorsInMasterNode() {

        getDriver().findElement(By.xpath("//a[@href='/manage']")).click();
        getDriver().findElement(By.xpath("//a[@href='computer']")).click();

        getDriver().findElement(By.xpath("//a[@class='jenkins-table__button']//*[name()='svg']")).click();

        WebElement changeNumber = getDriver().findElement(By.name("_.numExecutors"));
        changeNumber.clear();
        changeNumber.sendKeys("3");
        getDriver().findElement(By.name("Submit")).click();

        getDriver().findElement(By.xpath("//a[@href='/manage/computer/(built-in)/configure']")).click();

        Assert.assertEquals("3", getDriver().findElement(By.name("_.numExecutors")).getAttribute("value"));
    }

    @Test
    public void testManageConfigureNumberOfExecutorsInMasterNode() {
        ManagePage managePage = new ManagePage(getDriver());

        managePage.navigateToManagePage();
        managePage.navigateManageNodesAndClouds();
        managePage.clickConfigureMasterNode();
        managePage.changeNumberOfExecutorsAndSave("3");
        managePage.navigateToMasterNodeConfiguration();
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
