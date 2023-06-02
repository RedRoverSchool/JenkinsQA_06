package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;

public class ConfigureUserPage extends BaseMainHeaderPage<ConfigureUserPage> {

    public ConfigureUserPage(WebDriver driver) {
        super(driver);
    }

    public String getEmailValue(String attribute) {
        WebElement inputEmail = getDriver().findElement(By.xpath("//input[@name='email.address']"));

        return inputEmail.getAttribute(attribute);
    }

    public ConfigureUserPage enterEmail(String email) {
        WebElement inputEmail = getDriver().findElement(By.xpath("//input[@name='email.address']"));
        inputEmail.clear();
        inputEmail.sendKeys(email);

        return this;
    }
    public ConfigureUserPage clickConfigureSideMenu() {
        getWait5().until(ExpectedConditions.elementToBeClickable(
                getDriver().findElement(By.cssSelector("[href$='/configure']")))).click();

        return new ConfigureUserPage(getDriver());
    }

    public StatusUserPage clickSaveButton() {
        WebElement inputEmail = getDriver().findElement(By.name("Submit"));
        inputEmail.click();
        return new StatusUserPage(getDriver());
    }

}
