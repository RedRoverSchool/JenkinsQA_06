package school.redrover;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class ManageUsers2Test extends BaseTest {
    private final By userTable = By.xpath("//a[@class ='jenkins-table__link model-link inside']");
    private final By manageJenkinsTab = By.xpath("//a[@href = '/manage']");
    private final By manageUsersSection = By.xpath("//a[@href = 'securityRealm/']");
    private final By createUserBtn = By.xpath("//a[@href = 'addUser']");
    private final By userNameField = By.xpath("//input[@name = 'username']");
    private final By passwordField = By.xpath("//input[@name = 'password1']");
    private final By confirmPasswordField = By.xpath("//input[@name = 'password2']");
    private final By fullNameField = By.xpath("//input[@name = 'fullname']");
    private final By emailField = By.xpath("//input[@name = 'email']");
    private final By submitBtn = By.xpath("//button[@name = 'Submit']");
    private final By logoutIcon = By.xpath("//a[@href = '/logout']");
    private final By nameInput = By.xpath("//input[@name = 'j_username']");
    private final By passwordInput = By.xpath("//input[@name = 'j_password']");


    @Test
    public void testCreateNewUser() {
        String userName = createUser().get(0);
        List<WebElement> users = getDriver().findElements(userTable);
        Assert.assertTrue(isUserExist(users, userName));
        deleteUser(userName);
    }

    @Test
    public void testDeleteUser() {
        String userName = createUser().get(0);
        deleteUser(userName);
        List<WebElement> users = getDriver().findElements(userTable);
        Assert.assertFalse(isUserExist(users, userName));
    }

    @Test
    public void testLogInWithCredentialsOfDeletedUser() {
        List<String> credentials = createUser();
        String name = credentials.get(0);
        String password = credentials.get(1);
        deleteUser(name);

        getDriver().findElement(logoutIcon).click();
        getDriver().findElement(nameInput).sendKeys(name);
        getDriver().findElement(passwordInput).sendKeys(password);
        getDriver().findElement(submitBtn).click();

        Assert.assertEquals(getDriver().findElement(By
                        .xpath("//div[@class ='alert alert-danger']")).getText(),
                "Invalid username or password");

    }

    private List<String> createUser() {
        getWait2().until(ExpectedConditions.elementToBeClickable(manageJenkinsTab)).click();
        getDriver().findElement(manageUsersSection).click();
        getDriver().findElement(createUserBtn).click();

        String userName = generateName();
        getDriver().findElement(userNameField).sendKeys(userName);

        String password = generatePassword();
        getDriver().findElement(passwordField).sendKeys(password);

        getDriver().findElement(confirmPasswordField).sendKeys(password);
        getDriver().findElement(fullNameField).sendKeys(userName + " " + generateLastName());
        getDriver().findElement(emailField).sendKeys(generateEmail());
        getDriver().findElement(submitBtn).click();

        List<String> credentials = new ArrayList<>();
        credentials.add(userName);
        credentials.add(password);

        return credentials;
    }

    private void deleteUser(String userName) {
        WebElement trashBtn = getDriver().findElement(By
                .xpath("//a[@href = 'user/" + userName.toLowerCase() + "/delete']"));
        trashBtn.click();
        getDriver().findElement(submitBtn).click();
    }

    private String generateName() {
        Faker faker = new Faker();
        return faker.name().firstName();
    }

    private String generatePassword() {
        Faker faker = new Faker();
        return faker.internet()
                .password(5, 10, true, true, true);
    }

    private String generateLastName() {
        Faker faker = new Faker();
        return faker.name().lastName();
    }

    private String generateEmail() {
        Faker faker = new Faker();
        return faker.internet().emailAddress();
    }

    private boolean isUserExist(List<WebElement> list, String name) {
        for (WebElement el : list) {
            if (el.getText().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
