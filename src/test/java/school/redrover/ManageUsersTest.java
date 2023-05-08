package school.redrover;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Test
    public void testDeleteUserFromUserList() {
        createUser();
        getDriver().findElement(MANAGE_JENKINS).click();
        getDriver().findElement(MANAGE_USERS).click();
        getDriver().findElement(By.xpath("//a[@href='user/mr_churchill/delete']")).click();
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        List<WebElement> UserNames = getDriver().findElements(By.xpath("//a[contains(@class, 'link model-link inside')]"));

        Assert.assertFalse(isUserDisplayed(UserNames, "Churchill"));
    }
    public void createUser() {
        getDriver().findElement(MANAGE_JENKINS).click();
        getDriver().findElement(MANAGE_USERS).click();
        getDriver().findElement(CREATE_USER).click();
        getDriver().findElement(USERNAME_FIELD).sendKeys("Mr_Churchill");
        getDriver().findElement(PASSWORD_FIELD).sendKeys("Churchill2023");
        getDriver().findElement(CONFIRM_PASSWORD_FIELD).sendKeys("Churchill2023");
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











