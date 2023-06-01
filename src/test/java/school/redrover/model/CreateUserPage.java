package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import school.redrover.model.base.BaseMainHeaderPage;


public class CreateUserPage extends BaseMainHeaderPage<CreateUserPage> {

    public CreateUserPage(WebDriver driver) {
        super(driver);
    }

    public CreateUserPage enterUsername(String name) {
        getDriver().findElement(By.id("username")).sendKeys(name);

        return this;
    }

    public CreateUserPage enterPassword(String name) {
        getDriver().findElement(By.name("password1")).sendKeys(name);

        return this;
    }

    public CreateUserPage enterConfirmPassword(String name) {
        getDriver().findElement(By.name("password2")).sendKeys(name);

        return this;
    }

    public CreateUserPage enterFullName(String name) {
        getDriver().findElement(By.name("fullname")).sendKeys(name);

        return this;
    }

    public CreateUserPage enterEmail(String name) {
        getDriver().findElement(By.name("email")).sendKeys(name);

        return this;
    }

    public ManageUsersPage clickCreateUserButton() {
        getDriver().findElement(By.name("Submit")).click();

        return new ManageUsersPage(getDriver());
    }

    public void createUser(String username, String password, String fullName, String email) {
        new MainPage(getDriver())
                .navigateToManageJenkinsPage()
                .clickManageUsers()
                .clickCreateUser()
                .enterUsername(username)
                .enterPassword(password)
                .enterConfirmPassword(password)
                .enterFullName(fullName)
                .enterEmail(email)
                .clickCreateUserButton();

    }

    public void createUserAndReturnToMainPage(String username, String password, String fullName, String email) {
        new MainPage(getDriver())
                .navigateToManageJenkinsPage()
                .clickManageUsers()
                .clickCreateUser()
                .enterUsername(username)
                .enterPassword(password)
                .enterConfirmPassword(password)
                .enterFullName(fullName)
                .enterEmail(email)
                .clickCreateUserButton()
                .getHeader()
                .clickLogo();


    }

    public ManageUsersPage fillUserDetails(String username) {
        getDriver().findElement(By.id("username")).sendKeys(username);
        getDriver().findElement(By.name("password1")).sendKeys("1234");
        getDriver().findElement(By.name("password2")).sendKeys("1234");
        getDriver().findElement(By.name("fullname")).sendKeys("Nik Smith");
        getDriver().findElement(By.name("email")).sendKeys("nik@gmail.com");
        return new ManageUsersPage(getDriver());
    }

    public ManageUsersPage fillUserDetailsWithInvalidEmail(String username) {
        getDriver().findElement(By.id("username")).sendKeys(username);
        getDriver().findElement(By.name("password1")).sendKeys("1234");
        getDriver().findElement(By.name("password2")).sendKeys("1234");
        getDriver().findElement(By.name("fullname")).sendKeys("Nik Smith");
        getDriver().findElement(By.name("email")).sendKeys("nik.com");

        return new ManageUsersPage(getDriver());
    }
}
