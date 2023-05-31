package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import school.redrover.model.base.BaseMainHeaderPage;

public class ConfigureUserPage extends BaseMainHeaderPage<ConfigureUserPage> {

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
        return this;
    }

    public StatusUserPage clickSaveButton() {
        WebElement inputEmail = getDriver().findElement(By.name("Submit"));
        inputEmail.click();
        return new StatusUserPage(getDriver());
    }

}
