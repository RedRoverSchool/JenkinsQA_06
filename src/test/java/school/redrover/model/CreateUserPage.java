package school.redrover.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;

public class CreateUserPage extends BaseMainHeaderPage<CreateUserPage> {

    @FindBy(id = "username")
    private WebElement userNameInputField;

    @FindBy(name = "password1")
    private WebElement passwordInputField;

    @FindBy(name ="password2")
    private WebElement confirmPasswordInputField;

    @FindBy(name = "fullname")
    private WebElement fullNameInputField;

    @FindBy(name = "email")
    private WebElement emailInputField;

    @FindBy(name = "Submit")
    private WebElement createUserButton;

    @FindBy(xpath = "//div[contains(text(),'Username')]/../following-sibling::div[@class='error jenkins-!-margin-bottom-2']")
    private WebElement userExistsError;

    @FindBy(xpath = "//li[@aria-current]")
    private WebElement actualIconName;

    @FindBy(xpath = "//div[contains(text(),'E-mail address')]/../following-sibling::div[@class='error jenkins-!-margin-bottom-2']")
    private WebElement invalidEmailError;

    public CreateUserPage(WebDriver driver) {
        super(driver);
    }

    public CreateUserPage enterUsername(String name) {
        userNameInputField.sendKeys(name);
        return this;
    }

    public CreateUserPage enterPassword(String name) {
        passwordInputField.sendKeys(name);
        return this;
    }

    public CreateUserPage enterConfirmPassword(String name) {
        confirmPasswordInputField.sendKeys(name);
        return this;
    }

    public CreateUserPage enterFullName(String name) {
        fullNameInputField.sendKeys(name);
        return this;
    }

    public CreateUserPage enterEmail(String name) {
        emailInputField.sendKeys(name);
        return this;
    }

    public ManageUsersPage clickCreateUserButton() {
        createUserButton.click();
        return new ManageUsersPage(getDriver());
    }

    public CreateUserPage fillUserDetails(String username, String password, String fullName, String email) {
        return this.enterUsername(username)
                .enterPassword(password)
                .enterConfirmPassword(password)
                .enterFullName(fullName)
                .enterEmail(email);
    }

    public String getUserNameExistsError() {
        clickCreateUserButton();
        return getWait2().until(ExpectedConditions.visibilityOf(userExistsError)).getText();
    }

    public String getActualIconName() {
        return actualIconName.getText().trim();
    }

    public String getInvalidEmailError() {
        clickCreateUserButton();
        return getWait2().until(ExpectedConditions.visibilityOf(invalidEmailError)).getText();
    }
}

