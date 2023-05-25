package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateUserPage extends MainPage {

    public CreateUserPage(WebDriver driver) {
        super(driver);
    }

    public CreateUserPage setUsername(String name) {
        getDriver().findElement(By.id("username")).sendKeys(name);

        return this;
    }

    public CreateUserPage setPassword(String name) {
        getDriver().findElement(By.name("password1")).sendKeys(name);

        return this;
    }

    public CreateUserPage confirmPassword(String name) {
        getDriver().findElement(By.name("password2")).sendKeys(name);

        return this;
    }

    public CreateUserPage setFullName(String name) {
        getDriver().findElement(By.name("fullname")).sendKeys(name);

        return this;
    }

    public CreateUserPage setEmail(String name) {
        getDriver().findElement(By.name("email")).sendKeys(name);

        return this;
    }

    public CreateUserPage clickCreateUserButton() {
        getDriver().findElement(By.name("Submit")).click();

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
