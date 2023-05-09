package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class CreateUserTest extends BaseTest {

    public static String userName = "TestFullName";
    public static String password = "TestPassword";
    public static final By MANAGE_JENKINS_BUTTON = By.xpath("//div[@id = 'tasks']//div[4]//a");
    public static final By MANAGE_USERS_BUTTON = By.xpath("//a[@href = 'securityRealm/']//dt");
    public static final By CREATE_USER_BUTTON_APPBAR = By.xpath("//a[@class = 'jenkins-button jenkins-button--primary']");
    public static final By USER_NAME_FIELD = By.xpath("//input[@name = 'username']");
    public static final By PASSWORD_FIELD = By.xpath("//input[@name = 'password1']");
    public static final By CONFIRM_PASSWORD_FIELD = By.xpath("//input[@name = 'password2']");
    public static final By FULL_NAME_FIELD =  By.xpath("//input[@name = 'fullname']");
    public static final By EMAIL_ADDRESS_FIELD = By.xpath("//input[@name = 'email']");
    public static final By CREATE_USER_BUTTON_BOTTOM = By.xpath("//button[@class = 'jenkins-button jenkins-button--primary ']");
    public static final By LOG_OUT_BUTTON = By.xpath("//a[@href = '/logout']");
    public static final By USER_NAME_LOGIN_FIELD = By.id("j_username");
    public static final By PASSWORD_LOGIN_FIELD = By.name("j_password");
    public static final By SIGN_IN_BUTTON = By.name("Submit");
    public static final By USER_NAME_HEADER = By.xpath("//div[@class = 'login page-header__hyperlinks']/a[1]/span");

    @Test
    public void testCreateUser(){

        getDriver().findElement(MANAGE_JENKINS_BUTTON).click();
        getDriver().findElement(MANAGE_USERS_BUTTON).click();
        getDriver().findElement(CREATE_USER_BUTTON_APPBAR).click();
        getDriver().findElement(USER_NAME_FIELD).sendKeys(userName);
        getDriver().findElement(PASSWORD_FIELD).sendKeys(password);
        getDriver().findElement(CONFIRM_PASSWORD_FIELD).sendKeys(password);
        getDriver().findElement(FULL_NAME_FIELD).sendKeys("TestFullName");
        getDriver().findElement(EMAIL_ADDRESS_FIELD).sendKeys("testEmail@test.test");
        getDriver().findElement(CREATE_USER_BUTTON_BOTTOM).click();
        getDriver().findElement(LOG_OUT_BUTTON).click();
        getDriver().findElement(USER_NAME_LOGIN_FIELD).sendKeys(userName);
        getDriver().findElement(PASSWORD_LOGIN_FIELD).sendKeys(password);
        getDriver().findElement(SIGN_IN_BUTTON).click();

        String expectedResultTitle = "Dashboard [Jenkins]";
        String expectedResultUser = userName;

        String actualResultTitle = getDriver().getTitle();
        String actualResultUser = getDriver().findElement(USER_NAME_HEADER).getText();

        Assert.assertEquals(actualResultTitle, expectedResultTitle);
        Assert.assertEquals(actualResultUser, expectedResultUser);
    }
}
