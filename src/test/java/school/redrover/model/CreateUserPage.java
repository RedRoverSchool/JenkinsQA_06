package school.redrover.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateUserPage extends MainPage {

    @FindBy(id = "username")
    private WebElement username;

    @FindBy(name = "password1")
    private WebElement password;

    @FindBy(name = "password2")
    private WebElement confirmPassword;

    @FindBy(name = "fullname")
    private WebElement fullName;

    @FindBy(name = "email")
    private WebElement email;

    @FindBy(name = "Submit")
    private WebElement createUserButton;

    public CreateUserPage(WebDriver driver) {
        super(driver);
    }

    public CreateUserPage setUsername(String name) {
        username.sendKeys(name);

        return this;
    }

    public CreateUserPage setPassword(String name) {
        password.sendKeys(name);

        return this;
    }

    public CreateUserPage confirmPassword(String name) {
        confirmPassword.sendKeys(name);

        return this;
    }

    public CreateUserPage setFullName(String name) {
        fullName.sendKeys(name);

        return this;
    }

    public CreateUserPage setEmail(String name) {
        email.sendKeys(name);

        return this;
    }

    public CreateUserPage clickCreateUserButton() {
        createUserButton.click();

        return new CreateUserPage(getDriver());
    }

    public void createUser(String username, String password, String fullName, String email)  {
        new MainPage(getDriver())
                .navigateToManageJenkinsPage()
                .clickManageUsers()
                .clickCreateUser()
                .setUsername(username)
                .setPassword(password)
                .confirmPassword(password)
                .setFullName(fullName)
                .setEmail(email)
                .clickCreateUserButton();
    }
}
