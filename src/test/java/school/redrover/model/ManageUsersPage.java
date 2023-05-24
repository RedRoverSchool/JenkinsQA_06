package school.redrover.model;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BasePage;

import java.util.List;

public class ManageUsersPage extends BasePage {
    @FindBy(xpath = "//a[@class ='jenkins-table__link model-link inside']")
    private WebElement usersTable;
    @FindBy(xpath = "//a[@href = '/manage']")
    private WebElement manageJenkinsTab;
    @FindBy(xpath = "//a[@href = 'securityRealm/']")
    private WebElement manageUsersSection;
    @FindBy(xpath = "//input[@name = 'username']")
    private WebElement userNameField;
    @FindBy(xpath = "//input[@name = 'password1']")
    private WebElement passwordField;
    @FindBy(xpath = "//input[@name = 'password2']")
    private WebElement confirmPasswordField;
    @FindBy(xpath = "//input[@name = 'fullname']")
    private WebElement fullNameField;
    @FindBy(xpath = "//input[@name = 'email']")
    private WebElement emailField;
    @FindBy(xpath = "//button[@name = 'Submit']")
    private WebElement submitBtn;
    @FindBy(xpath = "//a[@href='addUser']")
    private WebElement createUser;

    @FindBy(linkText = "Configure")
    private WebElement configureUserLink;
    private final String password = generatePassword();
    private final String userName = generateName();

    public ManageUsersPage(WebDriver driver) {
        super(driver);
    }

    public CreateUserPage clickCreateUser() {
        createUser.click();
        return new CreateUserPage(getDriver());
    }

    public ManageUsersPage clickUserIDName(String userName) {
        WebElement userIDNameLink = getWait2()
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[@href='user/" + userName + "/']")));
        userIDNameLink.click();

        return this;
    }

    public String getUserIDName(String userName) {
        WebElement userIDNameLink = getDriver()
                .findElement(By.xpath("//a[@href='user/" + userName + "/']"));
        userIDNameLink.getText();

        return userIDNameLink.getText();
    }

    public ManageUsersPage openUsersPage() {
        getWait2().until(ExpectedConditions.elementToBeClickable(manageJenkinsTab)).click();
        manageUsersSection.click();
        return this;
    }

    public ManageUsersPage clickCreateUserBtn() {
        createUser.click();
        return this;
    }

    public ManageUsersPage inputUsername() {
        getWait5().until(ExpectedConditions.visibilityOf(userNameField));
        userNameField.sendKeys(userName);
        return this;
    }

    public ManageUsersPage inputPassword() {
        passwordField.sendKeys(password);
        return this;
    }

    public ManageUsersPage inputConfirmPassword() {
        confirmPasswordField.sendKeys(password);
        return this;
    }

    public ManageUsersPage inputFullName() {
        fullNameField.sendKeys(generateLastName());
        return this;
    }

    public ManageUsersPage inputEmail() {
        emailField.sendKeys(generateEmail());
        return this;
    }

    public ManageUsersPage clickSubmitBtn() {
        submitBtn.click();
        return this;
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

    public boolean isUserExist() {
        List<WebElement> users = getDriver().findElements(By
                .xpath("//a[@class ='jenkins-table__link model-link inside']"));
        for (WebElement el : users) {
            if (el.getText().equals(userName)) {
                return true;
            }
        }
        return false;
    }

}
