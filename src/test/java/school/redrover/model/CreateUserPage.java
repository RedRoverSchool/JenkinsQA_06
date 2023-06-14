package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
                .clickManageJenkinsPage()
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
                .clickManageJenkinsPage()
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

    public CreateUserPage fillUserDetails(String username, String password, String fullName, String email) {
        return this.enterUsername(username)
                .enterPassword(password)
                .enterConfirmPassword(password)
                .enterFullName(fullName)
                .enterEmail(email);
    }

    public String getUserNameExistsError() {
        clickCreateUserButton();
        return getWait2().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(text(),'Username')]/../following-sibling::div[@class='error jenkins-!-margin-bottom-2']")))
                .getText();
    }

    public String getActualIconName() {
        return getDriver().findElement(By.xpath("//li[@aria-current]")).getText().trim();
    }

    public String getInvalidEmailError() {
        clickCreateUserButton();
        return getWait2().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(text(),'E-mail address')]/../following-sibling::div[@class='error jenkins-!-margin-bottom-2']")))
                .getText();
    }
}

