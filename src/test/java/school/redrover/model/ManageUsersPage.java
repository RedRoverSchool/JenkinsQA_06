package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


import java.util.List;

public class ManageUsersPage extends MainPage {

    public ManageUsersPage(WebDriver driver) {
        super(driver);
    }

    public CreateUserPage clickCreateUser() {
        getDriver().findElement(By.xpath("//a[@href='addUser']")).click();

        return new CreateUserPage(getDriver());
    }

    public ManageUsersPage clickUserIDName(String userName) {
        WebElement userIDNameLink = getWait2()
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[@href='user/" + userName + "/']")));
        userIDNameLink.click();

        return this;
    }

    public ManageUsersPage clickUserIDDropDownMenu(String userName){
        getDriver()
                .findElement(By.xpath("//a[@href='user/" + userName + "/']/button[@class='jenkins-menu-dropdown-chevron']"))
                        .sendKeys(Keys.ENTER);
        return this;
    }

    public ManageUsersPage selectConfigureUserIDDropDownMenu() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), 'Configure')]"))).click();

        return this;
    }

    public ManageUsersPage openUsersPage() {
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href = '/manage']"))).click();
                getDriver().findElement(By.xpath("//a[@href = 'securityRealm/']")).click();
        return this;
    }

    public ManageUsersPage clickCreateUserBtn() {
        getDriver().findElement(By.xpath("//a[@href='addUser']")).click();
        return this;
    }

    public ManageUsersPage inputUsername(String userName) {
        getWait5()
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name = 'username']")))
                .sendKeys(userName);

        return this;
    }

    public ManageUsersPage inputPassword(String password) {
        getDriver()
                .findElement(By.xpath("//input[@name = 'password1']"))
                .sendKeys(password);
        return this;
    }

    public ManageUsersPage inputConfirmPassword(String password) {
        getDriver()
                .findElement(By.xpath("//input[@name = 'password2']"))
                .sendKeys(password);
        return this;
    }

    public ManageUsersPage inputFullName(String lastName) {
        getDriver()
                .findElement(By.xpath("//input[@name = 'fullname']"))
                .sendKeys(lastName);
        return this;
    }

    public ManageUsersPage inputEmail(String email) {
        getDriver()
                .findElement(By.xpath("//input[@name = 'email']"))
                .sendKeys(email);
        return this;
    }

    public ManageUsersPage clickSubmitBtn() {
        getDriver().findElement(By.xpath("//button[@name = 'Submit']")).click();
        return this;
    }

    public boolean isUserExist(String userName) {
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
