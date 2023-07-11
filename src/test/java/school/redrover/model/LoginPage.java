package school.redrover.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.model.base.BaseModel;

public class  LoginPage extends BaseModel {

    @FindBy(xpath = "//input[@name='j_username']")
    private WebElement inputUserNameField;

    @FindBy (xpath = "//input[@name='j_password']")
    private WebElement inputPasswordField;

    @FindBy(xpath = "//div[text()='Invalid username or password']")
    private WebElement incorrectUserNameOrPassword;

    @FindBy (xpath = "//button[@name='Submit']")
    private WebElement signInButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage enterUsername(String userName) {
        inputUserNameField.sendKeys(userName);
        return this;
    }

    public LoginPage enterPassword(String password) {
        inputPasswordField.sendKeys(password);
        return this;
    }

    public <Page extends BaseModel> Page enterSignIn(Page page) {
        signInButton.click();
        return page;
    }

    public String getTextAlertIncorrectUsernameOrPassword() {
       return incorrectUserNameOrPassword.getText();
    }

    public boolean isSignInButtonPresent() {
        return signInButton.isDisplayed();
    }
}
