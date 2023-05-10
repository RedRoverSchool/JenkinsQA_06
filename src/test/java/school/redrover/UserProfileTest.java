package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;


public class UserProfileTest extends BaseTest {

    private static final By HOME = By.id("jenkins-home-link");
    private static final By MANAGE_JENKINS = By.xpath("//a[@href='/manage']");
    private static final By MANAGE_USERS = By.xpath("//a[@href='securityRealm/']");
    private static final By CREATE_USER = By.xpath("//a[@href='addUser']");
    private static final By USERNAME_FIELD = By.id("username");
    private static final By PASSWORD_FIELD = By.xpath("//input[@name='password1']");
    private static final By CONFIRM_PASSWORD_FIELD = By.xpath("//input[@name='password2']");
    private static final By FULL_NAME_FIELD = By.xpath("//input[@name='fullname']");
    private static final By EMAIL_FIELD = By.xpath("//input[@name='email']");
    private static final By DESCRIPTION_INPUT_FIELD = By.name("description");
    private static final By ADD_DESCRIPTION_BUTTON = By.xpath("//a[@id='description-link']");
    private static final By SAVE_BUTTON = By.name("Submit");
    private static final By DESCRIPTION_DISPLAYED_TEXT =  By.xpath("//div[@id='description']/div[1]");

    public static final String username = "testuser";
    public static final String password = "p@ssword123";
    public static final String email = "test@test.com";
    public static final String fullName = "Test User";

    public void createUser() {
        getDriver().findElement(MANAGE_JENKINS).click();
        getDriver().findElement(MANAGE_USERS).click();
        getDriver().findElement(CREATE_USER).click();
        getDriver().findElement(USERNAME_FIELD).sendKeys(username);
        getDriver().findElement(PASSWORD_FIELD).sendKeys(password);
        getDriver().findElement(CONFIRM_PASSWORD_FIELD).sendKeys(password);
        getDriver().findElement(FULL_NAME_FIELD).sendKeys(fullName);
        getDriver().findElement(EMAIL_FIELD).sendKeys(email);
        getDriver().findElement(SAVE_BUTTON).click();
    }

    @Test
    public void testAddDescriptionToUser() {
        final String displayedDescriptionText = "Test User Description";

        createUser();
        getDriver().findElement(HOME).click();

        getDriver().findElement(MANAGE_JENKINS).click();
        getDriver().findElement(MANAGE_USERS).click();
        getDriver().findElement(By.xpath("//a[contains(@href, '" + username + "')]")).click();
        getDriver().findElement(ADD_DESCRIPTION_BUTTON).click();

        WebElement descriptionInputField = getDriver().findElement(DESCRIPTION_INPUT_FIELD);
        descriptionInputField.click();
        descriptionInputField.clear();
        descriptionInputField.sendKeys("Test User Description");
        getDriver().findElement(SAVE_BUTTON).click();

        String actualDisplayedDescriptionText = getDriver().findElement(DESCRIPTION_DISPLAYED_TEXT).getText();

        Assert.assertEquals(actualDisplayedDescriptionText, displayedDescriptionText);
    }
}
