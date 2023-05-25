package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.Keys.ENTER;

public class ConfigureUserPage extends MainPage {

    public ConfigureUserPage(WebDriver driver) {
        super(driver);
    }

    public String getEmailValue(String attribute) {
        WebElement inputEmail = getDriver().findElement(By.xpath("//input[@name='email.address']"));

        return inputEmail.getAttribute(attribute);
    }

    public ConfigureUserPage setEmail(String email) {
        WebElement inputEmail = getDriver().findElement(By.xpath("//input[@name='email.address']"));
        inputEmail.clear();
        inputEmail.sendKeys(email);
        inputEmail.sendKeys(ENTER);
        return this;
    }
}
