package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseConfigPage;
import school.redrover.runner.TestUtils;

public class UserConfigPage extends BaseConfigPage<UserConfigPage,StatusUserPage> {

    public UserConfigPage(StatusUserPage statusUserPage) {
        super(statusUserPage);
    }

    public String getEmailValue(String attribute) {
        WebElement inputEmail = getDriver().findElement(By.xpath("//input[@name='email.address']"));

        return inputEmail.getAttribute(attribute);
    }

    public UserConfigPage enterEmail(String email) {
        WebElement inputEmail = getDriver().findElement(By.xpath("//input[@name='email.address']"));
        inputEmail.clear();
        inputEmail.sendKeys(email);

        return this;
    }

    public boolean isConfigUserPageOpened(){
        return getWait5().until(ExpectedConditions.titleContains("Configuration [Jenkins]"));
    }

    public UserConfigPage selectInsensitiveSearch(){
        WebElement checkboxInsensitiveSearch = getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='insensitiveSearch']")));
        if (checkboxInsensitiveSearch.getAttribute("checked") == null){
            TestUtils.scrollToElementByJavaScript(this, checkboxInsensitiveSearch);
            TestUtils.clickByJavaScript(this, checkboxInsensitiveSearch);
        }

        return this;
    }

    public StatusUserPage saveConfig(){
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='Submit']"))).click();

        return new StatusUserPage(getDriver());
    }
}
