package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import school.redrover.model.base.BaseModel;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class LoginPage extends BaseModel {
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

    public MainPage enterSignIn() {
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();
        return new MainPage(getDriver());
    }

    public String getTextAlertIncorrectUsernameOrPassword() {
        return getDriver().findElement(By.xpath("//div[text()='Invalid username or password']")).getText();
    }
}
