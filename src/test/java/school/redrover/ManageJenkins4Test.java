package school.redrover;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.List;

public class ManageJenkins4Test extends BaseTest {
    private static final String NAME = RandomStringUtils.randomAlphanumeric(10);
    private static final String FULL_NAME_TEXT = RandomStringUtils.randomAlphanumeric(10);
    private static final By MANAGE_ICON = By.xpath("//a[@href='/manage']");
    private static final By SEARCH_SETTINGS = By.id("settings-search-bar");
    private static final By DROPDOWN = By.xpath(" //div[@class='jenkins-search__results']");
    private static final By DROPDOWN_SELECT = By.cssSelector(".jenkins-search__results-item--selected");
    private static final By DROPDOWN_ELEMENT = By.xpath("//a[normalize-space()='Manage Credentials']");
    private static final By CONFIGURE_BUTTON = By.xpath("//a[@class='jenkins-search__results-item--selected'][1]");
    private static final By ASSERT_ICON = By.xpath("//h1");
    private static final By MANAGE_USER_ICON = By.xpath("//a[@href='securityRealm/']");
    private static final  By CREATE_USER = By.xpath("//a[normalize-space()='Create User']");
    private static final By USERNAME = By.id("username");
    private static final By PASSWORD = By.name("password");
    private static final By CONFIRM_PASSWORD = By.name("password2");
    private static final By FULL_NAME = By.name("fullname");
    private static final By EMAIL = By.name("email");



    private static final String number = "1";
    private static final String noResults = "No results";
    private static final String letter = "m";
    private static final String letterNew = "man";
    private static final String element_dropdown = "Configure System";
    private static final String manageCredentials = "Manage Credentials";
    private static final String password = "password";
    private static final String fullName = "Max Smith";


    @Test
    public void testSearchWithNumericSymbol() {
        getDriver().findElement(MANAGE_ICON).click();

        getDriver().findElement(SEARCH_SETTINGS).sendKeys(number);

        getDriver().findElement(SEARCH_SETTINGS).click();
        getWait2().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(SEARCH_SETTINGS));

        Assert.assertEquals(noResults, getDriver().findElement(DROPDOWN).getText());
    }

    @Test
    public void testSearchWithLetterConfigureSystem() {
        getDriver().findElement(MANAGE_ICON).click();

        getDriver().findElement(SEARCH_SETTINGS).sendKeys(letter);

        getDriver().findElement(SEARCH_SETTINGS).click();
        getWait10().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(SEARCH_SETTINGS));

        List<WebElement> options = getDriver().findElements(DROPDOWN_SELECT);
        for (WebElement option : options) {
            if (option.getText().equals(element_dropdown)) {
                option.click();
                break;
            }
        }

        Assert.assertEquals(element_dropdown, getDriver().findElement(ASSERT_ICON).getText());
    }

    @Test
    public void testSearchWithLetterManageCredentials() throws InterruptedException {
        getDriver().findElement(MANAGE_ICON).click();

        getDriver().findElement(SEARCH_SETTINGS).sendKeys(letterNew);

        getWait10().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(SEARCH_SETTINGS));
        Thread.sleep(5000);
        List<WebElement> options = getDriver().findElements(DROPDOWN_ELEMENT);
        for (WebElement option : options) {
            if (option.getText().equals(manageCredentials)) {
                option.click();
                break;
            }
        }
        Assert.assertEquals("Credentials", getDriver().findElement(ASSERT_ICON).getText());
    }

    @Test
    public void testManageUser() {
        getDriver().findElement(MANAGE_ICON).click();
        getDriver().findElement(MANAGE_USER_ICON).click();
        getDriver().findElement(CREATE_USER).click();

        getDriver().findElement(USERNAME).sendKeys(NAME);
        getDriver().findElement(PASSWORD).sendKeys(password);
        getDriver().findElement(CONFIRM_PASSWORD).sendKeys(password);
        getDriver().findElement(FULL_NAME).sendKeys(fullName);
        getDriver().findElement(EMAIL).sendKeys();



    }
}
