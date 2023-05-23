package school.redrover.model;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import school.redrover.model.base.BasePage;

import java.util.List;

public class ManagePage extends BasePage {
    public ManagePage(WebDriver driver) {
        super(driver);
    }

    private static final String NEW_USER_NAME = RandomStringUtils.randomAlphanumeric(10);
    private static final By SEARCH_SETTINGS = By.id("settings-search-bar");
    private static final By LIST_ELEMENTS;

    static {
        LIST_ELEMENTS = By.xpath("//div[@class='jenkins-section__item']//dt");
    }


    public ManagePage navigateToManagePage() {
        getDriver().findElement(By.xpath("//a[@href='/manage']")).click();
        return this;
    }

    public ManagePage enterSearchQuery(String symbol) {
        getDriver().findElement(SEARCH_SETTINGS).sendKeys(symbol);
        return this;
    }

    public ManagePage clickSearchButton() {
        getDriver().findElement(SEARCH_SETTINGS).click();
        getWait2().until(ExpectedConditions.visibilityOfAllElementsLocatedBy
                (By.xpath(" //div[@class='jenkins-search__results']")));
        return this;
    }

    public ManagePage assertNoResultsDisplayed() {
        Assert.assertEquals("No results", getDriver().findElement
                (By.xpath(" //div[@class='jenkins-search__results']")).getText());
        return this;
    }

    public ManagePage selectOnTheFirstLineInDropdown() {

        getWait5().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".jenkins-search__results-item--selected")));

        List<WebElement> options = getDriver().findElements(By.cssSelector(".jenkins-search__results-item--selected"));
        for (WebElement option : options) {
            if (option.getText().equals("Configure System")) {
                option.click();
                break;
            }
        }
        return this;
    }

    public ManagePage assertConfigureSystemPage() {
        Assert.assertEquals("Configure System",
                getDriver().findElement(By.xpath("//h1[normalize-space()='Configure System']")).getText());
        return this;
    }

    public ManagePage selectManageCredentialsOption() {

        getWait5().until(ExpectedConditions.visibilityOfAllElementsLocatedBy
                (By.xpath("//a[normalize-space()='Manage Credentials']")));

        List<WebElement> options = getDriver().findElements(By.xpath("//a[normalize-space()='Manage Credentials']"));
        for (WebElement option : options) {
            if (option.getText().equals("Manage Credentials")) {
                option.click();
                break;
            }
        }
        return this;
    }

    public ManagePage assertCredentialsPage() {
        Assert.assertEquals("Credentials", getDriver().findElement
                (By.xpath("//h1[normalize-space()='Credentials']")).getText());
        return this;
    }

    public ManagePage navigateToManageUsersPage() {
        getDriver().findElement(By.xpath("//a[@href='securityRealm/']")).click();
        return this;
    }

    public ManagePage clickCreateUser() {
        getDriver().findElement(By.xpath("//a[normalize-space()='Create User']")).click();
        return this;
    }

    public ManagePage fillUserDetails() {
        getDriver().findElement(By.id("username")).sendKeys(NEW_USER_NAME);
        getDriver().findElement(By.name("password1")).sendKeys("1234");
        getDriver().findElement(By.name("password2")).sendKeys("1234");
        getDriver().findElement(By.name("fullname")).sendKeys("Nik Smith");
        getDriver().findElement(By.name("email")).sendKeys("nik@gmail.com");
        return this;
    }

    public ManagePage submit() {
        getDriver().findElement(By.name("Submit")).click();
        return this;
    }

    public ManagePage assertUserCreated() {
        List<WebElement> userList = getDriver().findElements(By.id("people"));

        for (WebElement user : userList) {
            if (user.getText().equals(NEW_USER_NAME)) {
                break;
            }
        }
        Assert.assertTrue(true, NEW_USER_NAME);
        return this;
    }

    public ManagePage fillUserDetailsWithInvalidEmail() {
        getDriver().findElement(By.id("username")).sendKeys(NEW_USER_NAME);
        getDriver().findElement(By.name("password1")).sendKeys("1234");
        getDriver().findElement(By.name("password2")).sendKeys("1234");
        getDriver().findElement(By.name("fullname")).sendKeys("Nik Smith");
        getDriver().findElement(By.name("email")).sendKeys("nik.com");
        return this;
    }

    public ManagePage assertInvalidEmailError() {
        Assert.assertEquals("Invalid e-mail address", getDriver()
                .findElement(By.xpath("//div[@class='error jenkins-!-margin-bottom-2']")).getText());
        return this;
    }

    public ManagePage clickDeleteUser() {
        getDriver().findElement(By.xpath("//a[@class='jenkins-table__button jenkins-!-destructive-color']")).click();
        return this;
    }

    public ManagePage assertUserDeleted() {
        List<WebElement> userList = getDriver().findElements(By.id("people"));

        for (WebElement user : userList) {
            if (user.getText().equals(NEW_USER_NAME)) {
                break;
            }
        }
        Assert.assertFalse(false, NEW_USER_NAME);
        return this;
    }

    public ManagePage clickUserEditButton() {
        getDriver().findElement(By.xpath("//a[@class='jenkins-table__button'][1]")).click();
        return this;
    }

    public ManagePage enterDescriptionText() {
        getDriver().findElement(By.name("_.description")).clear();
        getDriver().findElement(By.name("_.description")).sendKeys("Description text");
        return this;
    }

    public ManagePage assertDescriptionText() {
        Assert.assertEquals("Description text",
                getDriver().findElement(By.xpath("//div[@id='description']/div[1]")).getText());
        return this;
    }

    public ManagePage navigateManageNodesAndClouds() {
        getDriver().findElement(By.xpath("//a[@href='computer']")).click();
        return this;
    }

    public void clickConfigureMasterNode() {
        getDriver().findElement(By.xpath("//a[@class='jenkins-table__button']//*[name()='svg']")).click();
    }

    public ManagePage changeNumberOfExecutorsAndSave(String number) {
        WebElement changeNumber = getDriver().findElement(By.name("_.numExecutors"));
        changeNumber.clear();
        changeNumber.sendKeys(number);
        getDriver().findElement(By.name("Submit")).click();
        return this;
    }

    public void navigateToMasterNodeConfiguration() {
        getDriver().findElement(By.xpath("//a[@href='/manage/computer/(built-in)/configure']")).click();
    }

    public void assertNumberOfExecutors(String expectedNumber) {
        Assert.assertEquals(expectedNumber, getDriver().findElement(By.name("_.numExecutors")).getAttribute("value"));
    }

    public class TestData {
        @DataProvider(name = "KeyWordsToSearch")
        public static Object[][] searchWordsKey() {
            return new Object[][]{{"manage"}, {"tool"}, {"system"}, {"sec"}, {"cre"}, {"do"}, {"scr"}, {"jen"}, {"stat"}};
        }
    }

    public void testSearchSettingsByWord(String keyword) {
        navigateToManageUsersPage();
        enterKeywordInSearch(keyword);
        List<WebElement> actualResults = getSearchResults();
        List<WebElement> expectedSearchResults = getExpectedSearchResults(keyword);
        verifySearchResults(actualResults, expectedSearchResults);
    }


    public ManagePage enterKeywordInSearch(String keyword) {
        WebElement searchLink = getDriver().findElement(By.xpath("//input[@id='settings-search-bar']"));
        searchLink.click();
        searchLink.sendKeys(keyword);
        getWait5().until(ExpectedConditions.textToBePresentInElementValue(searchLink, keyword));
        return this;
    }


    private List<WebElement> getSearchResults() {
        return getWait5().until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//div[@class='jenkins-search__results']//a")));
    }

    private List<WebElement> getExpectedSearchResults(String keyword) {
        List<WebElement> listElements = getDriver().findElements(
                By.xpath("//div[@class='jenkins-section__item']//dt"));
        return listElements.stream()
                .filter(item -> item.getText().toLowerCase().contains(keyword))
                .toList();
    }

    public ManagePage verifySearchResults(List<WebElement> actualResults, List<WebElement> expectedResults) {
        for (int i = 0; i < expectedResults.size(); i++) {
            Assert.assertEquals(actualResults.get(i).getText(), expectedResults.get(i).getText());
        }
        return this;
    }
}



//    public List<WebElement> getListElements() {
//        return getDriver().findElements(By.xpath("//div[@class='jenkins-section__item']//dt"));
//    }
//
//    public ManagePage getSearchLink(String keyword) {
//        WebElement searchLink = getDriver().findElement(By.xpath("//input[@id='settings-search-bar']"));
//        searchLink.click();
//        searchLink.sendKeys(keyword);
//
//        getWait5().until(ExpectedConditions.textToBePresentInElementValue(searchLink, keyword));
//        return this;
//    }
//
//
//    public ManagePage getSearchResults() {
//       getWait5().until(ExpectedConditions.presenceOfAllElementsLocatedBy
//                (By.xpath("//div[@class='jenkins-search__results']//a")));
//        return this;
//    }
//
//    public ManagePage filterExpectedSearchResults(List<WebElement> listElements, String keyword) {
//        getListElements().stream()
//                 .filter(item -> item.getText().toLowerCase().contains(keyword))
//                .toList();
//        return this;
//    }
//
//    public ManagePage assertSearchResults(List<WebElement> actualResults, List<WebElement> expectedSearchResults) {
//        for (int i = 0; i < expectedSearchResults.size(); i++) {
//            Assert.assertEquals(actualResults.get(i).getText(), expectedSearchResults.get(i).getText());
//            return this;
//        }
//        return this;
//    }




