package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ManageUsersPage extends MainPage {

    @FindBy(xpath = "//a[@href='addUser']")
    private WebElement createUser;

    @FindBy(linkText = "Configure")
    private WebElement configureUserLink;

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


}
