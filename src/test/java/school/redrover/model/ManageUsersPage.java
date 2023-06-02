package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;


import java.util.List;

public class ManageUsersPage extends BaseMainHeaderPage<ManageUsersPage> {

    public ManageUsersPage(WebDriver driver) {
        super(driver);
    }

    public ManageJenkinsPage navigateToManageJenkinsPage() {
        getDriver().findElement(By.xpath("//a[@href='/manage']")).click();

        return new ManageJenkinsPage(getDriver());
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


    public ManageUsersPage clickYesButton() {
        getDriver().findElement(By.name("Submit")).click();

        return this;
    }


    public String getInvalidEmailError() {
        return getWait2().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//div[@class='error jenkins-!-margin-bottom-2']"))).getText();
    }

    public ManageUsersPage clickDeleteUser() {
        getDriver().findElement(
                By.xpath("//a[@class='jenkins-table__button jenkins-!-destructive-color']")).click();

        return this;
    }

    public boolean getUserDeleted(String username) {
        List<WebElement> userList = getDriver().findElements(By.id("people"));

        for (WebElement user : userList) {
            if (user.getText().equals(username)) {
                break;
            }
        }

        return false;
    }

    public ManageUsersPage clickUserEditButton() {
        getDriver().findElement(By.xpath("//a[@class='jenkins-table__button'][1]")).click();

        return this;
    }

    public ManageUsersPage enterDescriptionText() {
        getDriver().findElement(By.name("_.description")).clear();
        getDriver().findElement(By.name("_.description")).sendKeys("Description text");

        return this;
    }

    public String getDescriptionText() {

        return getWait2().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//div[@id='description']/div[1]"))).getText();
    }
}
