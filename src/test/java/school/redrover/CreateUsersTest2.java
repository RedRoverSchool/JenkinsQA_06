package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class CreateUsersTest2 extends BaseTest {

    @Test
    public void testCreateUser() {
        final String userName = "Kira";
        final String password1 = "12345";
        final String password2 = "12345";
        final String fullName = "Kira Knightly";
        final String email = "testv5494@gmail.com";

        WebElement manageJenkinsMenuItem = getDriver().findElement(By.xpath("//a[@href='/manage']"));
        manageJenkinsMenuItem.click();
        WebElement manageUsersMenuItem = getDriver().findElement(By.xpath("//a[@href = 'securityRealm/']"));
        manageUsersMenuItem.click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//h1")).getText(), "Users");

        WebElement createUserLink = getDriver().findElement(By.xpath("//a[@href = 'addUser']"));
        createUserLink.click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//h1")).getText(), "Create User");

        WebElement userNameField = getDriver().findElement(By.id("username"));
        userNameField.sendKeys(userName);

        WebElement passwordField = getDriver().findElement(By.name("password1"));
        passwordField.sendKeys(password1);

        WebElement confirmPasswordField = getDriver().findElement(By.name("password2"));
        confirmPasswordField.sendKeys(password2);

        WebElement fullNameField = getDriver().findElement(By.name("fullname"));
        fullNameField.sendKeys(fullName);

        WebElement emailField = getDriver().findElement(By.name("email"));
        emailField.sendKeys(email);

        WebElement createUserButton = getDriver().findElement(By.name("Submit"));
        createUserButton.click();

        WebElement userRecord = getDriver().findElement(By.xpath("//a[@href = 'user/kira/']"));

        Assert.assertTrue(userRecord.isDisplayed());
    }
}
