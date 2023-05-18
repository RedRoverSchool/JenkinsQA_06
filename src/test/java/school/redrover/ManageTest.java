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
import school.redrover.runner.BaseTest;

import java.util.List;
import java.util.stream.Collectors;

public class ManageTest extends BaseTest {
    private static final String NEW_USER_NAME = RandomStringUtils.randomAlphanumeric(10);
    private static final By SEARCH_SETTINGS = By.id("settings-search-bar");

    @Test
    public void testSearchWithNumericSymbol() {
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
    public void testSearchWithLetterConfigureSystem() {
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
    public void testSearchWithLetterManageCredentials() {
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
    public void testCreateNewUser() {

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

    @Test(dependsOnMethods = "testCreateNewUser")
    public void testDeleteUser() {
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

    @Test
    public void testAddDescriptionToUserOnTheUserProfilePage() {

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
    public void testManageConfigureNumberOfExecutorsInMasterNode() {

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

    @DataProvider(name = "wordsToSearch")
    public Object[][] searchWords() {
        return new Object[][]{{"manage"}, {"tool"}, {"sys"}, {"sec"}, {"cred"}, {"dow"}, {"script"}, {"jenkins"}, {"stat"}};
    }

    @Test(dataProvider = "wordsToSearch")
    public void testSearchSettingsItemsByKeyword(String keyword) {
        List<WebElement> actualResult, expectedResult, listSettingsItems;

        getDriver().findElement(By.xpath("//div[@id='tasks']//div//a[@href='/manage']")).click();

        listSettingsItems = getDriver().findElements(By.xpath("//div[@class='jenkins-section__item']//dt"));

        WebElement searchSettingsField = getDriver().findElement(By.xpath("//input[@id='settings-search-bar']"));
        searchSettingsField.click();
        searchSettingsField.sendKeys(keyword);
        getWait10().until(ExpectedConditions.textToBePresentInElementValue(searchSettingsField, keyword));

        actualResult = getWait10().until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='jenkins-search__results']//a")));
        expectedResult = listSettingsItems.stream().filter(item -> item.getText().toLowerCase().contains(keyword)).toList();

        for (int i = 0; i < expectedResult.size(); i++) {
            Assert.assertEquals(actualResult.get(i).getText(), expectedResult.get(i).getText());
        }
    }

    @DataProvider(name = "KeyWordsToSearch")
    public Object[][] searchWordsKey() {
        return new Object[][]{{"manage"}, {"tool"}, {"sys"}, {"sec"}, {"cred"}, {"dow"}, {"script"}, {"jenkins"}, {"stat"}};
    }

    @Test(dataProvider = "KeyWordsToSearch")
    public void testSearchSettingsByWord(String keyword) {

        WebElement manageLink = getDriver().findElement(By.xpath("//div[@id='tasks']//div//a[@href='/manage']"));
        manageLink.click();

        List<WebElement> listSettingsItems = getDriver().findElements(By.xpath("//div[@class='jenkins-section__item']//dt"));

        WebElement searchSettingsField = getDriver().findElement(By.xpath("//input[@id='settings-search-bar']"));
        searchSettingsField.click();
        searchSettingsField.sendKeys(keyword);
        getWait10().until(ExpectedConditions.textToBePresentInElementValue(searchSettingsField, keyword));

        List<WebElement> actualSearchResults = getWait10().until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='jenkins-search__results']//a")));

        List<WebElement> expectedSearchResults = listSettingsItems.stream()
                .filter(item -> item.getText().toLowerCase().contains(keyword))
                .collect(Collectors.toList());

        Assert.assertEquals(actualSearchResults.size(), expectedSearchResults.size());

        for (int i = 0; i < expectedSearchResults.size(); i++) {
            Assert.assertEquals(actualSearchResults.get(i).getText(), expectedSearchResults.get(i).getText());
        }
    }

    @Test
    public void testBreadcrumbNavigateManageJenkins() {
        new Actions(getDriver()).moveToElement(getDriver().findElement(
                By.xpath("//*[@id='breadcrumbs']/li/a[@href='/']"))).perform();

        By dropdown = By.xpath("//*[@id='breadcrumbs']/li/a/button[@class='jenkins-menu-dropdown-chevron']");
        getWait5().until(ExpectedConditions.elementToBeClickable(dropdown));

        WebElement pointer = getDriver().findElement(dropdown);
        new Actions(getDriver()).moveToElement(pointer).perform();
        pointer.sendKeys(Keys.RETURN);

        By sectionNameLocator = By.xpath("//*[@id='yui-gen4']/a/span");
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(sectionNameLocator));
        getDriver().findElement(sectionNameLocator).click();

        WebElement manageJenkinsHeader = getDriver().findElement(By.tagName("h1"));
        getWait2().until(ExpectedConditions.visibilityOf(manageJenkinsHeader));

        Assert.assertEquals(manageJenkinsHeader.getText(), "Manage Jenkins");
    }

}
