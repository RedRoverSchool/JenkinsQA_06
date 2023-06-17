package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.model.base.BaseModel;

public class  LoginPage extends BaseModel {

    @FindBy(xpath = "//h1")
    private WebElement welcomeJenkins;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage enterUsername(String userName) {
        getDriver().findElement(By.xpath("//input[@name='j_username']")).sendKeys(userName);
        return this;
    }

    public LoginPage enterPassword(String password) {
        getDriver().findElement(By.xpath("//input[@name='j_password']")).sendKeys(password);
        return this;
    }

    public <Page extends BaseModel> Page enterSignIn(Page page) {
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();
        return page;
    }

    public String getTextAlertIncorrectUsernameOrPassword() {
       return getDriver().findElement(By.xpath("//div[text()='Invalid username or password']")).getText();
    }

    public String  getWelcomeText() {
        return welcomeJenkins.getText();
    }
}
