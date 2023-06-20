package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;


import java.util.List;

public class ManageUsersPage extends BaseMainHeaderPage<ManageUsersPage> {

    @FindBy(xpath = "//a[@href='addUser']")
    private WebElement createUser;

    @FindBy(xpath = "//span[contains(text(), 'Configure')]")
    private WebElement configureInDropDownMenu;

    @FindBy(xpath = "//a[@class ='jenkins-table__link model-link inside']")
    private List<WebElement> users;

    @FindBy(xpath = "//a[@class='jenkins-table__button'][1]")
    private List<WebElement> configureUserButton;

    @FindBy(xpath = "//a[@class='jenkins-table__button jenkins-!-destructive-color']")
    private WebElement deleteButton;

    @FindBy(name = "_.description")
    private WebElement addEditDescriptionButton;

    @FindBy(xpath = "//a[@class='jenkins-table__button'][1]")
    private WebElement configureAdminUser;

    public ManageUsersPage(WebDriver driver) {
        super(driver);
    }

    public CreateUserPage clickCreateUser() {
        createUser.click();

        return new CreateUserPage(getDriver());
    }
    public String getButtonText() {
        return createUser.getText().trim();
    }

    public ManageUsersPage clickUserIDName(String userName) {
        WebElement userIDNameLink = getWait2()
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[@href='user/" + userName + "/']")));
        userIDNameLink.click();

        return this;
    }

    public ManageUsersPage openUserIDDropDownMenu(String userName){
        getDriver()
                .findElement(By.xpath("//a[@href='user/" + userName + "/']/button[@class='jenkins-menu-dropdown-chevron']"))
                        .sendKeys(Keys.ENTER);
        return this;
    }

    public ManageUsersPage selectConfigureUserIDDropDownMenu() {
        getWait5().until(ExpectedConditions.elementToBeClickable(configureInDropDownMenu)).click();

        return this;
    }

    public boolean isUserExist(String userName) {
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

    public DeletePage<ManageUsersPage> clickDeleteUser() {
        deleteButton.click();

        return new DeletePage<>(this);
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

    public UserConfigPage clickUserConfigureButton(String username) {
        getDriver().findElement(By.xpath("//a[@href='user/" + username + "/']")).click();

        return new UserConfigPage(new StatusUserPage(getDriver()));
    }

    public UserConfigPage clickUserEditButton() {
        configureAdminUser.click();

        return new UserConfigPage(new StatusUserPage(getDriver()));
    }
}

