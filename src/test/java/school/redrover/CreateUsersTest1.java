package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class CreateUsersTest1 extends BaseTest {
    private static final By MANAGE_JENKINS_XPATH = By.xpath("//a[@href='/manage']");
    private static final By MANAGE_USERS_XPATH = By.xpath("//dt[contains(text(),'Manage Users')]");
    private static final By CREATE_USERS_XPATH = By.xpath("//a[@href='addUser']");
    private static final By USERNAME_BAR_XPATH = By.xpath("//input[@name='username']");
    private static final By PASSWORD_BAR_XPATH = By.xpath("//input[@name='password1']");
    private static final By CONFIRM_PASSWORD_BAR_XPATH = By.xpath("//input[@name='password2']");
    private static final By FULL_NAME_BAR_XPATH = By.xpath("//input[@name='fullname']");
    private static final By EMAIL_ADDRESS_BAR_XPATH = By.xpath("//input[@name='email']");
    private static final String USER_NAME = "User";
    private static final String PASSWORD = "12345";
    private static final String FULL_NAME = "User";
    private static final String EMAIL_ADDRESS = "user@gmail.com";


    @Test
    public void testCreateNewUser(){
        String expectedResult = "";

        WebElement manageJenkinsLink = getDriver().findElement(MANAGE_JENKINS_XPATH);
        manageJenkinsLink.click();

        WebElement manageUsersLink = getDriver().findElement(MANAGE_USERS_XPATH);
        manageUsersLink.click();

        WebElement createUserButton = getDriver().findElement(CREATE_USERS_XPATH);
        createUserButton.click();

        WebElement usernameBar = getDriver().findElement(USERNAME_BAR_XPATH);
        usernameBar.click();
        usernameBar.sendKeys(USER_NAME);

        WebElement passwordBar = getDriver().findElement(PASSWORD_BAR_XPATH);
        passwordBar.click();
        passwordBar.sendKeys(PASSWORD);

        WebElement confirmPasswordBar = getDriver().findElement(CONFIRM_PASSWORD_BAR_XPATH);
        confirmPasswordBar.click();
        confirmPasswordBar.sendKeys(PASSWORD);

        WebElement fullNameBar = getDriver().findElement(FULL_NAME_BAR_XPATH);
        fullNameBar.click();
        fullNameBar.sendKeys(FULL_NAME);


    }
}
