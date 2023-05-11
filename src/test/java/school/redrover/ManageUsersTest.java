package school.redrover;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import java.util.List;
public class ManageUsersTest extends BaseTest {
    private static final By MANAGE_JENKINS = By.xpath("//a[@href='/manage']");
    private static final By MANAGE_USERS = By.xpath("//a[@href='securityRealm/']");
    private static final By CREATE_USER = By.xpath("//a[@href = 'addUser']");
    private static final By USERNAME_FIELD = By.id("username");
    private static final By PASSWORD_FIELD = By.xpath("//input[@name='password1']");
    private static final By CONFIRM_PASSWORD_FIELD = By.xpath("//input[@name='password2']");
    private static final By FULL_NAME_FIELD = By.xpath("//input[@name='fullname']");
    private static final By EMAIL_FIELD = By.xpath("//input[@name='email']");
    private static final By CREATE_USER_BUTTON = By.xpath("//button[@name='Submit']");
    private static final By LOG_OUT_BUTTON = By.xpath("//a[@href = '/logout']");
    private static final By LOG_IN_USERNAME_FIELD = By.id("j_username");
    private static final By LOG_IN_PASSWORD_FIELD = By.xpath("//input[@name='j_password']");
    private static final By SUBMIT_BUTTON = By.xpath("//button[@name='Submit']");
    public static final String username = "Mr_Churchill";
    public static final String password = "Churchill2023";
    @Test
    public void testDeleteUserFromUserList() {
        createUser();
        getDriver().findElement(MANAGE_JENKINS).click();
        getDriver().findElement(MANAGE_USERS).click();
        getDriver().findElement(By.xpath("//a[@href='user/mr_churchill/delete']")).click();
        getDriver().findElement(SUBMIT_BUTTON).click();

        List<WebElement> UserNames = getDriver().findElements(By.xpath("//a[contains(@class, 'link model-link inside')]"));

        Assert.assertFalse(isUserDisplayed(UserNames, "Churchill"));
    }
    @Test (dependsOnMethods = "testDeleteUserFromUserList")
    public void testLogInWithDeletedUserCredentialsImpossible() {
        getDriver().findElement(LOG_OUT_BUTTON).click();
        getDriver().findElement(LOG_IN_USERNAME_FIELD).sendKeys(username);
        getDriver().findElement(LOG_IN_PASSWORD_FIELD).sendKeys(password);
        getDriver().findElement(SUBMIT_BUTTON).click();

        Assert.assertEquals(getDriver().findElement(By
                .xpath("//div[@class ='alert alert-danger']")).getText(),
                "Invalid username or password");
    }
    @Test
    public void testMakeChangesToUserProfile() {
        JavascriptExecutor js = (JavascriptExecutor)getDriver();
        createUser();
        getDriver().findElement(MANAGE_JENKINS).click();
        getDriver().findElement(MANAGE_USERS).click();

        WebElement user = getDriver().findElement(By.xpath("//a[@href='user/mr_churchill/']"));
        new Actions(getDriver()).moveToElement(user).perform();
        WebElement chevron = getDriver().findElement(By
                .xpath("//a[@href='user/mr_churchill/']/button"));
        js.executeScript("arguments[0].click();", chevron);

        getWait2().until(ExpectedConditions.elementToBeClickable(By
            .xpath("//a[@href='/user/mr_churchill/configure']"))).click();

        WebElement fullNameField = getDriver().findElement(By.xpath("//input[@name='_.fullName']"));
        fullNameField.clear();
        fullNameField.sendKeys("Chepchik");
        getDriver().findElement(SUBMIT_BUTTON).click();

        Assert.assertEquals(getDriver().findElement(By
                .xpath("//h1")).getText(), "Chepchik");
    }
    public void createUser() {
        getDriver().findElement(MANAGE_JENKINS).click();
        getDriver().findElement(MANAGE_USERS).click();
        getDriver().findElement(CREATE_USER).click();
        getDriver().findElement(USERNAME_FIELD).sendKeys(username);
        getDriver().findElement(PASSWORD_FIELD).sendKeys(password);
        getDriver().findElement(CONFIRM_PASSWORD_FIELD).sendKeys(password);
        getDriver().findElement(FULL_NAME_FIELD).sendKeys("Churchill");
        getDriver().findElement(EMAIL_FIELD).sendKeys("test@test.com");
        getDriver().findElement(CREATE_USER_BUTTON).click();
        getDriver().findElement(By.xpath("//img[@id='jenkins-name-icon']")).click();
    }
    public boolean isUserDisplayed(List<WebElement> list, String name) {
        for(WebElement element : list) {
            if (element.getText().equals(name)) {
                return true;
            }
        }
        return false;
    }
}











